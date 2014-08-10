package com.shawric.SiegeTech;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class PandaNadeItem extends Item
{
	
	private int itemTier;
	
	
public PandaNadeItem(String name, int tier)
{
this.setUnlocalizedName(name); //Sets the name of this item, Has to be unique!
this.setCreativeTab(SiegeTech.tabMyMod);; //This Item will be in the Combat Creative Tab!
this.setTextureName(SiegeTech.modid + ":" + this.getUnlocalizedName().substring(5)); //The texture for this item is the Grenade!

this.itemTier = tier;
}


/*
 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
 */
public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
{
	if (!par2World.isRemote)
	{
	/* This method in World spawn in an entity,
	* You can use with anything that extends
	* the Entity class, in this case it's
	* the EntityGrenade class
	*/
	par2World.spawnEntityInWorld(new PandaNadeEntity(par2World, par3EntityPlayer,itemTier));
	//Decrease an item from the stack because you just used it!
	         --par1ItemStack.stackSize;
	}
	
return par1ItemStack;
}

public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
{
par3List.add("Tier " + this.itemTier + " handheld explosive." );
}









}