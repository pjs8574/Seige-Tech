package com.shawric.SiegeTech;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class NethreciteArrowItem extends Item {

	int itemTier;
	
	
	public NethreciteArrowItem(String name, int tier) {
		
		this.setUnlocalizedName(name); //Sets the name of this item, Has to be unique!
		this.setCreativeTab(SiegeTech.tabMyMod);; //This Item will be in the Combat Creative Tab!
		this.setTextureName(SiegeTech.modid + ":" + this.getUnlocalizedName().substring(5)); //The texture for this item is the Grenade!

		this.itemTier = tier;
	}

	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
	par3List.add("Tier: " + this.itemTier + " Anti-Obsidian arrow." );
	}
	
}
