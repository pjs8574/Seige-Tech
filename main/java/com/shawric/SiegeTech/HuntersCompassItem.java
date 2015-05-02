
/*

ON use of the compass

get a list of all players currently on the server

determine which is the closest using  Xp = x coord of player, xt is X coord of target etc
For loop through the list of playr entities returned. Get their x y z coordinates and comapre them to current player  
Sqrt(square(xp-xt)+square(yp-yt)+square(zp-zt))

Once you have the player object that is closest, take the coordinates of that playerObj and then compare it to 
current player 

Target X is Lower, target is West of Player,  
target X is Higher, Target is East of Player
Target Z is lower, target  is North of player
target Z is higher, Target is South of PLayer


tolerance for determining direction: + or - 10

Check the difference between thevalues to determine HOW North or South they are.

so abs(Xp-Xt) = toleracne to include the direction. East or West  

Check the difference between thevalues to determine HOW east or West they are.

so abs(Zp-Zt) = Tolerance to include Direction north or south

ex

Tolerence is 10
Player is at (10,0)
Target is at (-10,10)

X absolute difference is 20, which is outside our tolerancedo 10 blocks. We know Target X is less than PLayer X, 
  so target is west of player. So the word "West" is included in the statemeent to the user of the compass
Z absolute difference is 10, which is within tolerance.  We know the target is south of the player, but,
  becuase its within the tolerance range, it is not included i teh message to teh user of the compass.

Tell the using compass using PLayer which direction to go.

if the Target player is within 36 blocks of the PLayer using the compass, they target gets a 
"You feel like you are being watched" message if the PLayer uses the compass again

*/


package com.shawric.SiegeTech;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

import javax.sound.sampled.EnumControl.Type;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class HuntersCompassItem extends Item{
	
	private int itemTier;
	
	
	public HuntersCompassItem(String name, int tier) {
		
		this.setUnlocalizedName(name); //Sets the name of this item, Has to be unique!
		this.setCreativeTab(SiegeTech.tabMyMod);; //This Item will be in the Combat Creative Tab!
		this.setTextureName(SiegeTech.modid + ":" + this.getUnlocalizedName().substring(5)); //The texture for this item is the Grenade!

		this.setMaxStackSize(1);
		
		this.setMaxDamage(8);
		
		this.itemTier = tier;
		
	}
	
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
	par3List.add("Tier: " + this.itemTier + " The lidless eye seeks Players." );

	
		if( par1ItemStack.stackTagCompound == null )
		{
		    
			System.out.println("TAGCOMPOUND = NULL CREATING NBT COMPOUND");
			par1ItemStack.setTagCompound( new NBTTagCompound( ) );
		
		    String s = "";
		    s = s + par2EntityPlayer.getDisplayName();
		
		    par1ItemStack.stackTagCompound.setString( "Created", s );
		    
		    
		    for(int i = 0; i<this.itemTier; i++)
		    {
		    	String index = ""+i;
		    	//System.out.println("On Create Index: "+ index);
		    par1ItemStack.stackTagCompound.setString( index , "z" );
		    }
		}
	}
	
	@Override
	 public ItemStack onItemRightClick(ItemStack itemStk, World world1, EntityPlayer triggerPlayer)
	    {
	    
	    
	    String huntingPlayer = triggerPlayer.getDisplayName();
	    
	    int playerX = (int)triggerPlayer.posX;
	    int playerY = (int)triggerPlayer.posY;
	    int playerZ = (int)triggerPlayer.posZ;
	    
	    String closestTargetName;
	    
	    //EntityPlayer closestTargetPlayer = world1.getClosestPlayerToEntity(triggerPlayer, 1000);
	    EntityPlayer closestTargetPlayer = null;
	    
	    String dirNS;
	    String dirEW;
	    StringBuilder huntingMessage = new StringBuilder();
	    int closestPlayerDistence = 1000;
	    boolean alone = false;
	    ArrayList<String> whiteList = new ArrayList<String>(this.itemTier);
	      
	    if(world1.isRemote){
	    
	    for(int i1 = 0; i1<this.itemTier; i1++){	
				String index = ""+i1;
				
				System.out.println("Load search array Index: "+ index);
				System.out.println("STRING added to search array: "+itemStk.stackTagCompound.getString(index));

				whiteList.add( itemStk.stackTagCompound.getString( index ));
				
				System.out.println(whiteList);
		 }

	      for (int i = 0; i < world1.playerEntities.size(); ++i){
	      		
	    	  EntityPlayer targetPlayer = (EntityPlayer)world1.playerEntities.get(i);

	      		if( !(targetPlayer.getDisplayName().equals(huntingPlayer)) && !(whiteList.contains(targetPlayer.getDisplayName())) ){
	          		int targetX = (int)targetPlayer.posX;
	          		int targetY = (int)targetPlayer.posY;
	          		int targetZ = (int)targetPlayer.posZ;

	          		float pDistence = (float) Math.sqrt(Math.pow((playerX-targetX),2)+Math.pow((playerY-targetY),2)+Math.pow((playerZ-targetZ),2));
	      
	            	if(pDistence <= closestPlayerDistence){
	              	closestTargetPlayer = targetPlayer;
	              	closestPlayerDistence = (int) pDistence;
	            	}
	        	}
	        }
	        
	      		
	  if(closestTargetPlayer == null || whiteList.contains(closestTargetPlayer.getDisplayName())){
	    	if(world1.isRemote){
	    		triggerPlayer.addChatMessage(new ChatComponentText("The eye simply stares at you."));
	    	}
		
	  }else{
		  if(!closestTargetPlayer.getDisplayName().equalsIgnoreCase(huntingPlayer)){
			  int northSouthTolerance = (int) Math.abs(triggerPlayer.posZ-closestTargetPlayer.posZ);
			  int eastWestTolerance = (int) Math.abs(triggerPlayer.posX-closestTargetPlayer.posX);
		  

		  		if(closestTargetPlayer.posX < triggerPlayer.posX){
		  			dirEW = " West"; 
	      		}else{dirEW = " East";}
	      		
		  		if(eastWestTolerance < 4){
		  			dirEW ="";
		  		}

	      		if(closestTargetPlayer.posZ < triggerPlayer.posZ){
	      			dirNS = " North";
	      		}else {dirNS = " South";}
	      		
	      		if(northSouthTolerance < 4){
	      			dirNS = "";
	      		}

	      		if(dirNS.equals("") && dirEW.equals("")){
	      			
	      			if(world1.isRemote){
	      				triggerPlayer.addChatMessage(new ChatComponentText("The eye spins around. Your enemy is close."));
	    	    	}
	      			
	      		}else{
	      			huntingMessage.append(dirNS);
	      			huntingMessage.append(dirEW);
	      		}
	      			
	      		if(world1.isRemote && !huntingMessage.equals(""))
	    	    	{
	      			triggerPlayer.addChatMessage(new ChatComponentText("The eye looks to the" + huntingMessage.toString() + "."));
	    	    	}
			
	    	}else{
	    			if(world1.isRemote)
	    			{
	    				Minecraft.getMinecraft().thePlayer.sendChatMessage("Somthing dun fucked");
	    			}
    	    	
    	    	}
	    	

	       // return itemStk;
	        
	    }
	    }
	  	
	  	
	  	//compass takes damage
	  itemStk.damageItem(1, triggerPlayer);
	  	
	  	return itemStk;
	    }
	
	
	
	  //Create a white list by shift left clicking on a player
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity target)
	{

	    	World wld =target.worldObj;
	    	//System.out.println("Schwing");
	    	//System.out.println(target);
	    	//System.out.println("isremote: "+ wld.isRemote);
	    	//System.out.println("Raget instance of EntityplayerMP: " + (target instanceof EntityPlayerMP));
    		
	    	//if (Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
    		//{
	    	
	    	if(!wld.isRemote)
	    	{
	    		
	    		
	    	if(target instanceof EntityPlayerMP)
	    	{
	    	
	    		
	    	//System.out.println("It is an instance of EntityPlayerMP");
	    	
	    	
	    	ArrayList<String> whiteList = new ArrayList<String>(this.itemTier);
	    	
		    	String targetName = ((EntityPlayer) target).getDisplayName();
		    	
		    	System.out.println("Target Name "+targetName);
		    			
		    			for(int i = 0; i<this.itemTier; i++)
		    		    {	
		    				String index = ""+i;
		    				whiteList.add( stack.stackTagCompound.getString( index ));
		    				
		    				//System.out.println("Whitelist being filled");
		    		    }
		    			
		    			if (whiteList.contains(targetName)==false)
		    			{
		    				System.out.println("Targetname not in whitelist");
		    				for(int i = 0; i<this.itemTier; i++)
			    		    {	
			    				String index = ""+i;
			    				
			    				//System.out.println("in the forloop");
			    				//System.out.println("Getstring of NBT: " + stack.stackTagCompound.getString(index));
			    				
			    				
			    				if (stack.stackTagCompound.getString(index).equals("z"))
				    			{
			    					
			    					System.out.println("Adding target name to whitelist");
			    					stack.stackTagCompound.setString( index , targetName );
			    				i = (this.itemTier+1);
			    				
			    				
			    				
				    			}
			    		    }
		    		
		    			}
		    			
		    			
		    			
		    			
	    		    				 
		    		//}

		    			return true;
		    			
		    	}else{return false;}
	    	}else{return false;}
			
	}
	
	
	
	
	
}
