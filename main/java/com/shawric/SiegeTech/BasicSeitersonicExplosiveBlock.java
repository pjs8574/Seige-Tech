package com.shawric.SiegeTech;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTNT;
import net.minecraft.block.material.Material;

public class BasicSeitersonicExplosiveBlock extends Block {

	public BasicSeitersonicExplosiveBlock() {
		super(Material.tnt);
		this.setHardness(2);
		this.setResistance(10);
		this.setCreativeTab(SiegeTech.tabMyMod);

	}	

}
