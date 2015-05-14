package com.shawric.SiegeTech;

import java.util.Hashtable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
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
			    System.out.println("PLACING BLOCK IN "+chunkToCheck.xPosition+","+chunkToCheck.zPosition);
	    }
    }
    
	public boolean checkList(Chunk chunkToCheck, EntityPlayer theBreaker){

		if(!chunkToCheck.worldObj.isRemote){
		
        String chunkLoc = "chunkat"+","+chunkToCheck.xPosition+","+chunkToCheck.zPosition;
        String breakerName =theBreaker.getDisplayName();
        
        theBreaker.addChatMessage(new ChatComponentText("Current claimblocklist: "+claimBlockList.toString()));
        
        if(this.claimBlockList.containsKey(chunkLoc)){
        	
        	String chunkWhitelist = (String) this.claimBlockList.get(chunkLoc);
        	
        	if(chunkWhitelist.contains(breakerName)){
        		theBreaker.addChatMessage(new ChatComponentText("You have access to this chunk."));
        		return false;
        	}else{
        		theBreaker.addChatMessage(new ChatComponentText("You DO NOT have access to this chunk."));
        		return true;
        	}
			
			}else{theBreaker.addChatMessage(new ChatComponentText("Name not in the list."));
				  theBreaker.addChatMessage(new ChatComponentText("You have access to this chunk."));
					return false;}
		
		
		}else{return false;}
		
		
		}
	
	
	
	
	
	
	public static void addClaimBlockToList(String owner, Chunk placedChunk) {
		
		if(!placedChunk.worldObj.isRemote){
			
			String ownerList = owner;
			String chunkLoc = "chunkat"+","+placedChunk.xPosition+","+placedChunk.zPosition;
			
			if(claimBlockList.containsKey(chunkLoc)){
				System.out.println("-----CHUNK ALREADY CLAIMED----");
				
			}else{
			claimBlockList.put(chunkLoc,ownerList);
			}
			
		}
		
	} 
	
	public static void addAddtionalNameToClaimBlock(String anotherName, Chunk placedChunk) {
		
		String ownerList = anotherName;
		
		if(claimBlockList.contains(placedChunk)){
			
			ownerList = (String) claimBlockList.get(placedChunk);
			
			if(ownerList.contains(anotherName)){
				System.out.println("Owner already on the chunk list");
			}else{
				ownerList = ownerList + "," + anotherName;
				claimBlockList.put(placedChunk,ownerList);
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
