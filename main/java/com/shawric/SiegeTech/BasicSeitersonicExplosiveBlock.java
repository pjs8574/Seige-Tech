package com.shawric.SiegeTech;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BasicSeitersonicExplosiveBlock extends Block {

	
	@SideOnly(Side.CLIENT)
	private IIcon topN;
	@SideOnly(Side.CLIENT)
	private IIcon bottom;
	@SideOnly(Side.CLIENT)
	private IIcon topS;
	@SideOnly(Side.CLIENT)
	private IIcon topE;
	@SideOnly(Side.CLIENT)
	private IIcon topW;
	@SideOnly(Side.CLIENT)
	private IIcon blockSide;
	
	private int blockDirectionInt; // SIDE KEY : 0 bottom, 1 top , 2 North. 3 South, 4 West ,5 East
	private String blockDirectionString;
	
	
	public BasicSeitersonicExplosiveBlock() {
		super(Material.tnt);
		this.setHardness(2);
		this.setResistance(10);
		this.setCreativeTab(SiegeTech.tabMyMod);
	
	}	
	
	 public void onBlockAdded(World world, int i, int j, int k)
	    {
	        super.onBlockAdded(world, i, j, k);
	        Minecraft.getMinecraft().thePlayer.sendChatMessage("added:");
	        
	        
	        
	        // ALL THIS SHIT GETS THE DIRECTION THE PLAYER IS FACING WHEN PLACING THE BLOCK AND SAVES IT
	        int l = MathHelper.floor_double((double)((Minecraft.getMinecraft().thePlayer.rotationYaw * 4F) / 360F) + 0.5D) & 3;
	        if(l == 0)
	    	{
	        	world.setBlockMetadataWithNotify(i, j, k, 3, 2);
	        	blockDirectionInt = 3; //SOUTH
	        	Minecraft.getMinecraft().thePlayer.sendChatMessage("BD is South: " + blockDirectionInt);
	    	}
	    	if(l == 1)
	    	{
	        	world.setBlockMetadataWithNotify(i, j, k, 4, 2);
	        	blockDirectionInt = 4;//WEST
	        	Minecraft.getMinecraft().thePlayer.sendChatMessage("BD is West: " + blockDirectionInt);
	    	}
	    	if(l == 2)
	    	{
	        	world.setBlockMetadataWithNotify(i, j, k, 2, 2);
	        	blockDirectionInt = 2;//NORTH
	        	Minecraft.getMinecraft().thePlayer.sendChatMessage("BD is North: " + blockDirectionInt);
	    	}
	    	if(l == 3)
	    	{
	        	world.setBlockMetadataWithNotify(i, j, k, 5, 2);
	        	blockDirectionInt = 5;//EAST
	        	Minecraft.getMinecraft().thePlayer.sendChatMessage("BD is East: " + blockDirectionInt);
	    	}
	    	
	    	
	    	switch(blockDirectionInt)
			{
	    	case 2: blockDirectionString = "N";
	    	case 3: blockDirectionString = "S";
	    	case 4: blockDirectionString = "E";
	    	case 5: blockDirectionString = "W";
			}
	    	
	    	//   Minecraft.getMinecraft().thePlayer.sendChatMessage("Roation " + this.rotateBlock(world, i, j, k, ForgeDirection.getOrientation(blockDirectionInt)));
	    }
	/*
	 * for Set setBlockMetadataWithNotify  -- its the 3 coordinates of the block, the MNetadata number adn the last number is a Flag.
	 * Flag 1 will cause a block update. Flag 2 will send the change to clients (you almost always want this). Flag 4 prevents the
    * block from being re-rendered, if this is a client world. Flags can be added together.
    */
	
	
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		//Minecraft.getMinecraft().thePlayer.sendChatMessage("Side: " + side + "  Meta: " + meta);
		
		// Keep so we know how to do multi sided blocks
		// SIDE KEY : 0 bottom, 1 top , 2 North. 3 South, 4 West ,5 East
		// I stored the direction that the block faces in the meta data. this corresponds to the side key
		// so metadata 2 is indicates the direction the block should face is North etc
		
		switch(side)
		{		
			case 0:
				return bottom;
				break;
			case 1:
				if(meta == 2){return topN;}
				if(meta == 3){return topS;}
				if(meta == 4){return topW;}
				if(meta == 5){return topE;}
				break;
			case 2:
				return blockSide;
				break;
			case 3:
				return blockSide;
				break;
			case 4:
				return blockSide;
				break;
			case 5:
				return blockSide;
				break;
		}
		
				return blockSide;
	
	}
	
	
	
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister Ireg)
	{
	this.blockSide = Ireg.registerIcon(SiegeTech.modid + ":" + this.getUnlocalizedName().substring(5) + "side");
	this.topN = Ireg.registerIcon(SiegeTech.modid + ":" + this.getUnlocalizedName().substring(5) + "topN");
	this.topS = Ireg.registerIcon(SiegeTech.modid + ":" + this.getUnlocalizedName().substring(5) + "topS");
	this.topE = Ireg.registerIcon(SiegeTech.modid + ":" + this.getUnlocalizedName().substring(5) + "topE");
	this.topW = Ireg.registerIcon(SiegeTech.modid + ":" + this.getUnlocalizedName().substring(5) + "topW");
	this.bottom = Ireg.registerIcon(SiegeTech.modid + ":" + this.getUnlocalizedName().substring(5) + "bottom");
	
	/* old side texture code, for unique sides for later if we want
	this.sideN = Ireg.registerIcon(SiegeTech.modid + ":" + this.getUnlocalizedName().substring(5) + "sideN");
	this.sideS = Ireg.registerIcon(SiegeTech.modid + ":" + this.getUnlocalizedName().substring(5) + "sideS");
	this.sideW = Ireg.registerIcon(SiegeTech.modid + ":" + this.getUnlocalizedName().substring(5) + "sideW");
	this.sideE = Ireg.registerIcon(SiegeTech.modid + ":" + this.getUnlocalizedName().substring(5) + "sideE");
	*/
	
	}
	
}
