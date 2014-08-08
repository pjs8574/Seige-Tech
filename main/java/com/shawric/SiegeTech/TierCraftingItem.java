package com.shawric.SiegeTech;

import net.minecraft.item.Item;

public class TierCraftingItem extends Item {

	int itemTier;
	
	
	public TierCraftingItem(String name, int tier)
	{
	this.setUnlocalizedName(name); //Sets the name of this item, Has to be unique!
	this.setCreativeTab(SiegeTech.tabMyMod);; //This Item will be in the Combat Creative Tab!
	
	System.out.println("CRAFTING ITEM TO TEXTURE>>>>>>>>" + SiegeTech.modid + ":" + this.getUnlocalizedName().substring(5));
	this.setTextureName(SiegeTech.modid + ":" + this.getUnlocalizedName().substring(5)); //The texture for this item!
	this.itemTier = tier;
	}
	
	
}
