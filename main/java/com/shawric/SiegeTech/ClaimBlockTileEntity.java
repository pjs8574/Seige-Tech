package com.shawric.SiegeTech;

import net.minecraft.block.material.Material;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class ClaimBlockTileEntity extends TileEntity{
	
	
	
	
	private Chunk placedChunk;
	private String owner;
	private int blockTier;
	private int baseClaimBlockHP;
	
	
	public ClaimBlockTileEntity(Chunk placedChnk, String name, int tier, int baseHP) {
		
		
		this.baseClaimBlockHP = baseHP;
		this.owner=name;
		this.blockTier = tier;
		this.placedChunk = placedChnk;
	}
	
   private void setBlockName(String name) {
		// TODO Auto-generated method stub
		
	}

@Override
   public void writeToNBT(NBTTagCompound par1)
   {
      super.writeToNBT(par1);
   
   
      par1.setString("Owner", owner);
   
   }

   @Override
   public void readFromNBT(NBTTagCompound par1)
   {
      super.readFromNBT(par1);
      this.owner = par1.getString("Owner");
   }
}