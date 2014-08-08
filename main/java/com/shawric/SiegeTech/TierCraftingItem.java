package com.shawric.SiegeTech;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TierCraftingItem extends Item {

	int itemTier;
	
	
	public TierCraftingItem(String name, int tier)
	{
	this.setUnlocalizedName(name); //Sets the name of this item, Has to be unique!
	this.setCreativeTab(SiegeTech.tabMyMod);; //This Item will be in the Combat Creative Tab!
	
	//System.out.println("CRAFTING ITEM TO TEXTURE>>>>>>>>" + SiegeTech.modid + ":" + this.getUnlocalizedName().substring(5));
	this.setTextureName(SiegeTech.modid + ":" + this.getUnlocalizedName().substring(5)); //The texture for this item!
	this.itemTier = tier;
	}
	
	
	@SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack)
    {
	    
		if (this.itemTier == 4)
		{
		return true;
		}
		else{return false;}
		}
	
}
