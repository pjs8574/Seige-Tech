package com.shawric.SiegeTech;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClaimBlock extends Block {

	
	private int baseClaimBlockHP;
	private int blockTier;
	
	@SideOnly(Side.CLIENT)
	private IIcon normalHP;
	@SideOnly(Side.CLIENT)
	private IIcon damagedHP;
	
	boolean blockExploded;
	
	public ClaimBlock(String name, int tier, int baseHP) {
		super(Material.rock);
		this.setHardness(5);
		this.setResistance(10);
		this.setCreativeTab(SiegeTech.tabMyMod);
		this.baseClaimBlockHP = baseHP;
		
		this.setBlockName(name);
		this.blockTier = tier;
	}
	@Override
	 public void onBlockAdded(World world, int x, int y, int z)
	    {
	        	//set the Meta to "max" hp which is a meta int of 15
			world.setBlockMetadataWithNotify(x, y, z, 15, 2);
	        	//Minecraft.getMinecraft().thePlayer.sendChatMessage("New block is places with HP of " + world.getBlockMetadata(x, y, z));
	    }
	
	
	//sets the block texture stored in \src\main\resources\assets\shawric_seigetech\textures\blocks
		@SideOnly(Side.CLIENT)
		@Override
		public void registerBlockIcons(IIconRegister icReg)
		{	
		normalHP = icReg.registerIcon(SiegeTech.modid + ":" + this.getUnlocalizedName().substring(5));
		damagedHP = icReg.registerIcon(SiegeTech.modid + ":" + this.getUnlocalizedName().substring(5) + "Damaged");	
		}

		@SideOnly(Side.CLIENT)
		@Override
		public IIcon getIcon(int side, int meta)
		{
			
		if((meta <= 7) && (meta > 0))
		{	
		return damagedHP;
		}else return normalHP;
		
		
		}
	
	
	

}
