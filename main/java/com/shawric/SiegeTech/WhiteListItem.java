package com.shawric.SiegeTech;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;


public class WhiteListItem extends Item{

	private int itemTier;
	private String nameList=" ";
	
	public WhiteListItem(String name, int tier) {
		
		this.setUnlocalizedName(name); //Sets the name of this item, Has to be unique!
		this.setCreativeTab(SiegeTech.tabMyMod);; //This Item will be in the Siege Tech
		this.setTextureName(SiegeTech.modid + ":" + this.getUnlocalizedName().substring(5)); //The texture for this item

		this.setMaxStackSize(1);
		
		this.itemTier = tier;

		this.setMaxDamage((itemTier*4));
		
	}
	
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
	par3List.add("Tier: " + this.itemTier + "A List of Names" );
	par3List.add("Crouch+Left Click on a player to add thier name." );
	
	
		if( par1ItemStack.stackTagCompound == null )
		{
		    
			//System.out.println("TAGCOMPOUND = NULL CREATING NBT COMPOUND");
			par1ItemStack.setTagCompound( new NBTTagCompound( ) );
		
		    String s = "";
		    s = s + par2EntityPlayer.getDisplayName();
		
		    par1ItemStack.stackTagCompound.setString( "Created", s );
		    
		    
		    for(int i = 0; i<this.itemTier; i++)
		    {
		    	String index = ""+i;
		    	//System.out.println("On Create Index: "+ index);
		    par1ItemStack.stackTagCompound.setString( index , "" );
		    }
		}
	}
	
	
	private String getToolTipList() {
		// TODO Auto-generated method stub
		return nameList;
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity target)
	{
	    	World wld =target.worldObj;

	    	if (player.isSneaking())
    		{
	    	
	    	if(!wld.isRemote)
	    	{
	    	if(target instanceof EntityPlayerMP)
	    	{

	    	ArrayList<String> whiteList = new ArrayList<String>(this.itemTier);
	    	
		    	String targetName = ((EntityPlayer) target).getDisplayName();

		    			for(int i = 0; i<this.itemTier; i++)
		    		    {	
		    				String index = ""+i;
		    				whiteList.add( stack.stackTagCompound.getString( index ));
		    				
		    				//System.out.println("Whitelist being filled");
		    		    }
		    			
		    			if (whiteList.contains(targetName)==false)
		    			{
		    				//System.out.println("Targetname not in whitelist");
		    				for(int i = 0; i<this.itemTier; i++)
			    		    {	
			    				String index = ""+i;

			    				if (stack.stackTagCompound.getString(index).equals(""))
				    			{
			    					player.addChatMessage(new ChatComponentText(targetName+" Has been added to the list."));
			    				    this.nameList = nameList+targetName+" ";
			    				    player.addChatMessage(new ChatComponentText("the list:" + this.nameList));
			    					stack.stackTagCompound.setString( index , targetName );
			    				i = (this.itemTier+1);

				    			}
			    		    }
		    		
		    			}
				 
		    		}

		    			return true;
		    			
		    	}else{return false;}
	    	}else{return false;}
			
	}
	
	public ItemStack onItemRightClick(ItemStack itmStck, World wrld, EntityPlayer player)
    {
		
		if(wrld.isRemote){
		
		String nameListToShow="";
			for(int i = 0; i<this.itemTier; i++)
		    {	
				String index = ""+i;
				itmStck.stackTagCompound.getString( index );
				nameListToShow = nameListToShow+itmStck.stackTagCompound.getString( index )+",";
		    }
		player.addChatMessage(new ChatComponentText("The list:" + nameListToShow));
		
		}
		return itmStck;
    }
	
	
}
