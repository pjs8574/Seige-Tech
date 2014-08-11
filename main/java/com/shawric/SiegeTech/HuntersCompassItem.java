
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

import java.util.List;

import javax.swing.Icon;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

public class HuntersCompassItem extends Item{
	
	private int itemTier;
	
	
	public HuntersCompassItem(String name, int tier) {
		
		this.setUnlocalizedName(name); //Sets the name of this item, Has to be unique!
		this.setCreativeTab(SiegeTech.tabMyMod);; //This Item will be in the Combat Creative Tab!
		this.setTextureName(SiegeTech.modid + ":" + this.getUnlocalizedName().substring(5)); //The texture for this item is the Grenade!

		this.itemTier = tier;
	}
	
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
	par3List.add("Tier: " + this.itemTier + "The lidless eye seeks Players." );
	}
	
	@Override
	 public ItemStack onItemRightClick(ItemStack itemStk, World world1, EntityPlayer triggerPLayer)
	    {
	    
	    
	    String huntingPlayer = triggerPlayer.getName();
	    
	    int playerX = (int)triggerPLayer.x;
	    int playerY = (int)triggerPLayer.y;
	    int playerZ = (int)triggerPLayer.z;
	    
	    String closestTargetName;
	    
	    EntityPlayer closestTargetPlayer;
	    int closestPlayerDistence = 10000;
	    
	    String DirNS;
	    String DirEW;
	    String huntingMessage;
	    
	    //get a list of players on the server
	    List pList = minecraftServer().world1.getPlayerList();
	    
	    
	    //iterate through the players
	      for(int i=0; i<pList.length(); i++)
	      {
	      
	      //parse the plist entry -- if its aplayer entityh then awesome
	      EntityPlayer targetPlayer = pList(i);
	      
	      if(targetPlayer.getName().notEqual(huntingPlayer))
	        {
	          int targetX = (int)targetPlayer.x;
	          int targetY = (int)targetPlayer.y;
	          int targetZ = (int)targetPlayer.z;

	          float pDistence = Math.Sqrt(Math.square(playerX-targetX)+Math.square(playerY-targetY)+Math.square(playerZ-targetZ));
	      
	            if(pDistence <= closestPlayerDistence)
	            {
	              closestTargetPlayer = targetPlayer;
	              closestPlayerDistence = pDistence;
	            }
	        }
	      
	        
	        if(closestTargetPlayer.x < triggerPlayer.x)
	          {dirEW = " West"; }else{dirEW = " East"}
	        
	        
	        if(closestTargetPlayer.z < triggerPlayer.z)
	          {dirNS = " North";}else{dirNS = " South";}
	        

          northSouthTolerance = abs(triggerPLayer.x-closestTargetPlayer.z)
	        if(northSouthTolerance<8)
	        {huntingMessage.append(dirNS);}
	        eastWestTolerance = abs(triggerPLayer.x-closestTargetPlayer.x)
	        if(eastWestTolerance<8)
	        {huntingMessage.append(dirEW)}
	        
	        Minecraft.getMinecraft().thePlayer.sendChatMessage("The eye looks to the" + huntingMessage + ".");
	        
	      }
	    
	      
	    }
	    
	
	
	
}
