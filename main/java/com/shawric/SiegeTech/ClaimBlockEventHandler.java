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
        // Register extended entity properties
		
		EntityPlayer theBreaker = event.getPlayer();

        if (theBreaker instanceof EntityPlayerMP)
        {
        	theBreaker.addChatMessage(new ChatComponentText("Block Broken By MP Player: "+theBreaker.getDisplayName()));
        }else{
        	theBreaker.addChatMessage(new ChatComponentText("Block Broken By Other Player: "+theBreaker.getDisplayName()));
        }
        
        String Loc = ("X: "+event.x +" Y: "+ event.y +" Z: "+ event.z);
        
        theBreaker.addChatMessage(new ChatComponentText("Block location: " + Loc));
        
        Chunk chunkToCheck = event.world.getChunkFromBlockCoords(event.x, event.z);
        String chunkLoc = "chunkat"+"|"+chunkToCheck.xPosition+"|"+chunkToCheck.zPosition;
        String breakerName =theBreaker.getDisplayName();
        
        theBreaker.addChatMessage(new ChatComponentText("claimblocklist: "+claimBlockList.toString()));
        
        if(this.claimBlockList.containsKey(chunkLoc)){
        	
        	String chunkWhitelist = (String) this.claimBlockList.get(chunkLoc);
        	
        	if(chunkWhitelist.contains(breakerName)){
        		theBreaker.addChatMessage(new ChatComponentText("You have access to this chunk."));
        	}else{
        		theBreaker.addChatMessage(new ChatComponentText("You DO NOT have access to this chunk."));
        		event.setCanceled(true);
        	}
        	
        }
         
    }

	public static void addClaimBlockToList(String owner, Chunk placedChunk) {
		
		
			String ownerList = owner;
			String chunkLoc = "chunkat"+placedChunk.xPosition+placedChunk.zPosition;
			
			if(claimBlockList.contains(chunkLoc)){
			
			ownerList = (String) claimBlockList.get(chunkLoc);
			
				if(ownerList.contains(owner)){
					System.out.println("Owner already on the chunk list");
				}
	
			}else{
			claimBlockList.put(chunkLoc,ownerList);
			}
		
	} 
	
	public static void addAddtionalNameToClaimBlock(String anotherName, Chunk placedChunk) {
		
		String ownerList = anotherName;
		
		if(claimBlockList.contains(placedChunk)){
			
			ownerList = (String) claimBlockList.get(placedChunk);
			
			if(ownerList.contains(anotherName)){
				System.out.println("Owner already on the chunk list");
			}else{
				ownerList = ownerList + "|" + anotherName;
				claimBlockList.put(placedChunk,ownerList);
			}
		
		}
		
	}

	public static void claimBlockDestroyed(Chunk chunkToRemove) {
		
		String chunkLoc = "chunkat"+"|"+chunkToRemove.xPosition+"|"+chunkToRemove.zPosition;

		claimBlockList.remove(chunkLoc);
		
	} 

}
