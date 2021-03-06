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
			
			if(!world.isRemote){
				this.owner = ((EntityPlayer) placer).getDisplayName();
				//((EntityPlayer) placer).addChatMessage(new ChatComponentText("The chunk "+ placedChunk.toString() + " has been claimed by " + this.owner));
					ClaimBlockTileEntity tile = (ClaimBlockTileEntity) world.getTileEntity(x, y, z);
						tile.setOwner(owner);
			}
	    
		}
	
	@Override
	public void onBlockPreDestroy(World wrld, int x, int y, int z, int p_149664_5_) {
		
		System.out.println("!!!!CLAIM BLOCK DESTORYED!!!! REMOVING FROM LIST!!!");
		
		if(!wrld.isRemote){
		ClaimBlockTileEntity tile = (ClaimBlockTileEntity) wrld.getTileEntity(x, y, z);
		tile.claimBlockDestroyed();
		}
		
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
			//Minecraft.getMinecraft().thePlayer.sendChatMessage("Tile Ent created.");
			return new ClaimBlockTileEntity();
		
		}

		
		@Override
		 public boolean onBlockActivated(World wrld, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
		    {
		        
			ClaimBlockTileEntity tileentityClaimBlock = (ClaimBlockTileEntity)wrld.getTileEntity(x, y, z);
 
				if(!wrld.isRemote)
				{
					
					ItemStack heldItem = player.getHeldItem();
					
					//if its the whitelist item then get the list of names from it
					if(heldItem!=null){
						
						//player.addChatMessage(new ChatComponentText("UNLOCAL: "+ heldItem.getUnlocalizedName()));
						
						if(heldItem.getUnlocalizedName().equalsIgnoreCase("item.whiteList")){

							String nameListToShow="";
							
							for(int i = 0; i<4; i++)
						    {	
								String index = ""+i;
								heldItem.stackTagCompound.getString( index );
								nameListToShow = nameListToShow + heldItem.stackTagCompound.getString( index ) + ",";
								
								ClaimBlockTileEntity cbTE =  (ClaimBlockTileEntity) wrld.getTileEntity(x, y, z);
									
									if(heldItem.stackTagCompound.getString( index )!=null){
										cbTE.addPlayerToWhitelist(heldItem.stackTagCompound.getString( index ));
									}
						    }
							
							player.addChatMessage(new ChatComponentText("Added "+ nameListToShow + " to claim block ownership."));
						
						}
					}else{player.addChatMessage(new ChatComponentText("This is owned by: "+tileentityClaimBlock.getOwner()));}
					
				
				}   
		            return true;
      
		    }
	

}
