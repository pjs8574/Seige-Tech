package com.shawric.SiegeTech;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BasicSeitersonicExplosiveBlock extends Block {

	
	@SideOnly(Side.CLIENT)
	private IIcon top;
	
	@SideOnly(Side.CLIENT)
	private IIcon bottom;
	
	@SideOnly(Side.CLIENT)
	private IIcon bottom2;
	
	@SideOnly(Side.CLIENT)
	private IIcon bottom3;
	
	
	public BasicSeitersonicExplosiveBlock() {
		super(Material.tnt);
		this.setHardness(2);
		this.setResistance(10);
		this.setCreativeTab(SiegeTech.tabMyMod);
	
	}	

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		
		switch(side)
 		{
 			case 0: //bottom of the block
 				return this.bottom;

 			case 1: //Top of the block
 				return this.top;

 			case 2: //
 				return this.blockIcon;
		
 			case 3: //South of the block
 				return this.bottom2;
 			case 4: //West of the block
 				return this.bottom3;
 			case 5: //
 		}
		
		return this.blockIcon;
	
	}
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister Ireg)
	{
	this.blockIcon = Ireg.registerIcon(SiegeTech.modid + ":" + this.getUnlocalizedName().substring(5) + "side");
	this.top = Ireg.registerIcon(SiegeTech.modid + ":" + this.getUnlocalizedName().substring(5) + "top");
	this.bottom = Ireg.registerIcon(SiegeTech.modid + ":" + this.getUnlocalizedName().substring(5) + "bottom");
	
	
	this.bottom2 = Ireg.registerIcon(SiegeTech.modid + ":" + this.getUnlocalizedName().substring(5) + "bottom2");
	this.bottom3 = Ireg.registerIcon(SiegeTech.modid + ":" + this.getUnlocalizedName().substring(5) + "bottom3");
	}
	
}
