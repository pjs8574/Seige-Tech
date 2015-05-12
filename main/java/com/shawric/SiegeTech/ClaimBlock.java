package com.shawric.SiegeTech;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClaimBlock extends Block implements ITileEntityProvider{

	
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

			this.owner = ((EntityPlayer) placer).getDisplayName();
			
			//((EntityPlayer) placer).addChatMessage(new ChatComponentText("The chunk "+ placedChunk.toString() + " has been claimed by " + this.owner));
    	   
				ClaimBlockTileEntity tile = (ClaimBlockTileEntity) world.getTileEntity(x, y, z);
				
					tile.setOwner(owner);		
					
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


		@Override
		public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
			Minecraft.getMinecraft().thePlayer.sendChatMessage("Tile Ent created.");
			return new ClaimBlockTileEntity();
		
		}

		
		@Override
		 public boolean onBlockActivated(World wrld, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
		    {
		        
			ClaimBlockTileEntity tileentityClaimBlock = (ClaimBlockTileEntity)wrld.getTileEntity(p_149727_2_, p_149727_3_, p_149727_4_);
 
				if(wrld.isRemote)
				{
					player.addChatMessage(new ChatComponentText("(Client side) This is owned by: "+tileentityClaimBlock.getOwner()));
				}else{
					player.addChatMessage(new ChatComponentText("(Server Side) This is owned by: "+tileentityClaimBlock.getOwner()));
				}   
		            return true;
       
		    }
	

}
