package com.shawric.SiegeTech;

import java.util.ArrayList;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class ClaimBlockTileEntity extends TileEntity{
	
	
	public static final String publicName = "claimBlockTileEntity";
    private String name = "claimBlockTileEntity";

	
	
	private Chunk placedChunk;
	private String owner;
	private int blockTier;
	private int baseClaimBlockHP;
	private ArrayList<String> whiteList;
	private boolean sentToEventList;
	private int timeCheck=0;
	
	public ClaimBlockTileEntity() {

		
		System.out.println("---!!!this.sentToEventList!!!---"+this.sentToEventList);
		System.out.println("---!!!CLAIM BLOCK CONSTRUCTOR TRIGGERED!!!---");
		this.timeCheck = 0;
	
		
	}
	
   private void setBlockName(String name) {
		// TODO Auto-generated method stub
		
	}

   @Override
   public void updateEntity(){
	   
	   if(!this.worldObj.isRemote){
	  // System.out.println("---!!!TIMECHECK!!!---"+this.timeCheck);
	   
	   if (this.timeCheck < 30){
		   ++this.timeCheck;
		   this.markDirty();
	   }else{  
				   System.out.println("---!!!SENDING UPDATE TO EH!!!---");
				   ClaimBlockEventHandler.addClaimBlockToList(this.owner,this.worldObj.getChunkFromBlockCoords(xCoord, zCoord));  
				   ++this.timeCheck;
				   this.markDirty();
	        }  
	   }
	   
	   if (this.timeCheck > 30){this.timeCheck=0;}
   }
   
   
   @Override
   public void writeToNBT(NBTTagCompound par1)
   {
	   super.writeToNBT(par1);
      par1.setString("Owner", owner);
      par1.setBoolean("updated",sentToEventList);

   }

   @Override
   public void readFromNBT(NBTTagCompound par1)
   {
      
	   super.readFromNBT(par1);
	   this.owner = par1.getString("Owner");
	   this.sentToEventList = par1.getBoolean("updated");
    
   }
   
   
   @Override
   public Packet getDescriptionPacket()
   {
       NBTTagCompound syncData = new NBTTagCompound();
       writeToNBT(syncData);
       return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, syncData);
   }
   
   @Override
   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
   {
	   readFromNBT(pkt.func_148857_g());
   }
   
   
   public String getOwner(){

	   return this.owner;
   }
   
   public void setOwner(String playerName){
	   
	   if(!this.worldObj.isRemote){
		   this.owner = playerName;
		   this.placedChunk = this.worldObj.getChunkFromBlockCoords(xCoord, zCoord);
		   this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		  
		   if(this.owner != null){
				System.out.println("Owner is NOT NULL, telling the event handler.");
				ClaimBlockEventHandler.addClaimBlockToList(owner,placedChunk);
			}
	   }
   }

   public Chunk getClaimedChunk(){

	   return this.placedChunk;
   }
   
   public void  addPlayerToWhitelist(String playerName){

	   if (this.whiteList.contains(playerName)){
		      //already on the whitelist, do nothing
	   }else{this.whiteList.add(playerName);}
	   
   }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
}