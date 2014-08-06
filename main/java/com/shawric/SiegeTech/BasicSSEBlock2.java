package com.shawric.SiegeTech;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BasicSSEBlock2 extends Block 
	{
	protected BasicSSEBlock2(Material p_i45394_1_) {
		super(Material.tnt);
		
	}
	//normal block code snipped
	
	  public int getBlockTexture(IBlockAccess par1IBlockAccess, int x, int y, int z, int side)
	    {
	    
	    	TileEntity blockEntity = par1IBlockAccess.getTileEntity(x, y, z);
	    	int meta = par1IBlockAccess.getBlockMetadata(x, y, z); 
	    	if(side == 1)
	        {
	 			 switch(meta)
	 			 {
	 			 case 0: return 1;
	 			 default: return 1;
	 			 }
	        }
	        if(side == ((BasicSSETileEntity)blockEntity).getFacingDireciton())
	        {
	        	switch(meta)
	        	{
	        	case 0: return 1;
	        	default: return 1;
	        	}
	        }
			return 1;
	    }
	  
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving)
	    {
	        int var6 = MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
	        TileEntity blockEntity = (TileEntity) par1World.getTileEntity(par2, par3, par4);
	        //0-4 are directions the player is facing on the compass, though i don't know which is which, but trial and error got me the write answers
	        if (var6 == 0)
	        {
	           ((BasicSSETileEntity)blockEntity).setFacingDireciton(2);
	        }

	        if (var6 == 1)
	        {
	        	((BasicSSETileEntity)blockEntity).setFacingDireciton(5);
	        }

	        if (var6 == 2)
	        {
	        	((BasicSSETileEntity)blockEntity).setFacingDireciton(3);
	        }

	        if (var6 == 3)
	        {
	        	((BasicSSETileEntity)blockEntity).setFacingDireciton(4);
	        }
	       
	    }
	public TileEntity getBlockEntity(int meta)
	    {
	        switch(meta)
	        {    
	        case 0: return new BasicSSETileEntity();
	        }
			return null;
	    }

}
