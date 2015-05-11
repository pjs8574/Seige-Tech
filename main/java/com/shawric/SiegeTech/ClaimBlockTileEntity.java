package com.shawric.SiegeTech;

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
    private String name = "claimlockTileEntity";

	
	public NBTTagCompound stackTagCompound;
	private Chunk placedChunk;
	private String owner;
	private int blockTier;
	private int baseClaimBlockHP;
	
	
	public ClaimBlockTileEntity(String player) {
		
		
		
		
		Minecraft.getMinecraft().thePlayer.sendChatMessage("TE Constructer fired.");
		if( this.stackTagCompound == null )
		{
			System.out.println("NBT null, creating new");
			this.stackTagCompound = new NBTTagCompound( );
		}
		
		this.owner=player;
		
		
		
	}
	
   private void setBlockName(String name) {
		// TODO Auto-generated method stub
		
	}

   @Override
   public void writeToNBT(NBTTagCompound par1)
   {
      
      par1.setString("Owner", owner);
      super.writeToNBT(par1);
   
   }

   @Override
   public void readFromNBT(NBTTagCompound par1)
   {
      
      this.owner = par1.getString("Owner");
      super.readFromNBT(par1);
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

	   return this.stackTagCompound.getString("Owner");
   }
   
   public void setOwner(String playerName){
	   
	
	if( this.stackTagCompound == null )
	{
		System.out.println("NBT null, creating new");
		this.stackTagCompound = new NBTTagCompound( );
	}
	
	   this.stackTagCompound.setString("Owner", playerName);
	   this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
   
   }

   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
}