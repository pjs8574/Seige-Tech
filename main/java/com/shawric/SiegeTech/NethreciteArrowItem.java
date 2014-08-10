package com.shawric.SiegeTech;

import net.minecraft.item.Item;

public class NethreciteArrowItem extends Item {

	int itemTier;
	
	
	public NethreciteArrowItem(String name, int tier) {
		
		this.setUnlocalizedName(name); //Sets the name of this item, Has to be unique!
		this.setCreativeTab(SiegeTech.tabMyMod);; //This Item will be in the Combat Creative Tab!
		this.setTextureName(SiegeTech.modid + ":" + this.getUnlocalizedName().substring(5)); //The texture for this item is the Grenade!

		this.itemTier = tier;
	}

	
	
}
