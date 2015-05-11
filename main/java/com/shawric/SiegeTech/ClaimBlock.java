package com.shawric.SiegeTech;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClaimBlock extends Block {

	
	private int baseClaimBlockHP;
	private int blockTier;
	
	@SideOnly(Side.CLIENT)
	private IIcon normalHP;
	@SideOnly(Side.CLIENT)
	private IIcon damagedHP;
	
	private Chunk placedChunk;
	private String owner;
	
	boolean blockExploded;
	
	
	//constructor
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
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack itmStk)
		{
	        	//set the Meta to "max" hp which is a meta int of 15
			world.setBlockMetadataWithNotify(x, y, z, 15, 2);
	        	//Minecraft.getMinecraft().thePlayer.sendChatMessage("New block is places with HP of " + world.getBlockMetadata(x, y, z));
			
			placedChunk = world.getChunkFromChunkCoords(x, z);
			
			owner = ((EntityPlayer) placer).getDisplayName();
			
			((EntityPlayer) placer).addChatMessage(new ChatComponentText("The chunk "+ placedChunk.toString() + " has been claimed by " + this.owner));
    	    
			
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
	
		public TileEntity createTileEntity(World world, int metadata)
		{
		   return new ClaimBlockTileEntity(this.placedChunk, this.owner, this.blockTier, this.baseClaimBlockHP);
		}
	

}
