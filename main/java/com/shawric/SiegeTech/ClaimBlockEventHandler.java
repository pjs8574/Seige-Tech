package com.shawric.SiegeTech;

import java.util.Hashtable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;


public class ClaimBlockEventHandler {
	
	private static Hashtable claimBlockList = new Hashtable();
	
	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public void onEvent(BlockEvent.BreakEvent event)
    {
        //checking the block break, see if player has acces to chunk
		if(!event.world.isRemote)
    	{
			EntityPlayer theBreaker = event.getPlayer();
			Chunk chunkToCheck = event.world.getChunkFromBlockCoords(event.x, event.z);
	        event.setCanceled(this.checkList(chunkToCheck,theBreaker));	
	    }
    }
       
	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
	public void onEvent(BlockEvent.PlaceEvent event)
    {
        //checking the block place, see if player has access to chunk
		if(!event.world.isRemote)
    	{
				EntityPlayer theBreaker = event.player;
				Chunk chunkToCheck = event.world.getChunkFromBlockCoords(event.x, event.z);
			    event.setCanceled(this.checkList(chunkToCheck,theBreaker));
			    //System.out.println("PLACING BLOCK IN "+chunkToCheck.xPosition+","+chunkToCheck.zPosition);
	    }
    }
    
	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
	public void onEvent(PlayerInteractEvent event)
    {
        //checking the block access, see if player has access to chunk
		if(!event.world.isRemote)
    	{
				EntityPlayer theBreaker = event.entityPlayer;
				Chunk chunkToCheck = event.world.getChunkFromBlockCoords(event.x, event.z);
			    event.setCanceled(this.checkList(chunkToCheck,theBreaker));
			    //System.out.println("ATTEMPTING TO INTERACT AT "+chunkToCheck.xPosition+","+chunkToCheck.zPosition);
	    }
    }
	
	
	
	public boolean checkList(Chunk chunkToCheck, EntityPlayer theBreaker){

		if(!chunkToCheck.worldObj.isRemote){
		
        String chunkLoc = "chunkat"+","+chunkToCheck.xPosition+","+chunkToCheck.zPosition;
        String breakerName =theBreaker.getDisplayName();
        
        //theBreaker.addChatMessage(new ChatComponentText("Current claimblocklist: "+claimBlockList.toString()));
        
        if(this.claimBlockList.containsKey(chunkLoc)){
        	
        	String chunkWhitelist = (String) this.claimBlockList.get(chunkLoc);
        	
        	if(chunkWhitelist.contains(breakerName)){
        		//theBreaker.addChatMessage(new ChatComponentText("You have access to this chunk."));
        		return false;
        	}else{
        		theBreaker.addChatMessage(new ChatComponentText("You DO NOT have access to this chunk."));
        		return true;
        	}
			
			}else{ //theBreaker.addChatMessage(new ChatComponentText("Name not in the list."));
				  //theBreaker.addChatMessage(new ChatComponentText("You have access to this chunk."));
					return false;}
		
		
		}else{return false;}
		
		
		}
	
	
	
	
	
	
	public static void addClaimBlockToList(String potentialOwner, Chunk placedChunk) {
		
		if(!placedChunk.worldObj.isRemote){
			
			String ownerList = potentialOwner;
			String chunkLoc = "chunkat"+","+placedChunk.xPosition+","+placedChunk.zPosition;
			
			if(claimBlockList.containsKey(chunkLoc)){
				//System.out.println("-----CHUNK ALREADY CLAIMED AT " + chunkLoc + "----Adding Names?");
				
				String actualOwner = (String) claimBlockList.get(chunkLoc);
				//System.out.println("Name to Check -"+actualOwner);
				//has the owner name, but might have others
				if(potentialOwner.contains(actualOwner)){
					//System.out.println("Contains the owner");
					//if its Different than owner name(has more names) then add it
					if(!actualOwner.equalsIgnoreCase(potentialOwner)){
						
						//System.out.println("if its Different than owner name(has more names) then add it");
						
						claimBlockList.put(chunkLoc,ownerList);
					}
				}
				
			}else{
			claimBlockList.put(chunkLoc,ownerList);
			}
			
		}
		
	} 
	


	public static void claimBlockDestroyed(Chunk chunkToRemove, String owner) {
		
		String chunkLoc = "chunkat"+","+chunkToRemove.xPosition+","+chunkToRemove.zPosition;
		
		if(claimBlockList.containsKey(chunkLoc)){
			if(claimBlockList.get(chunkLoc)==owner){
				claimBlockList.remove(chunkLoc);	
			}
		} 

	}
	
}
