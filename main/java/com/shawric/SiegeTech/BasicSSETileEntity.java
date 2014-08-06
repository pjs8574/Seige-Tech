package com.shawric.SiegeTech;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class BasicSSETileEntity extends TileEntity {
	 private int facing;
	public int getFacingDireciton()
	{
		return this.facing;
	}   
	public void setFacingDireciton(int side)
	{
		this.facing = side;
	}
	 public void writeToNBT(NBTTagCompound par1NBTTagCompound)
	    {
	        super.writeToNBT(par1NBTTagCompound);
	        par1NBTTagCompound.setInteger("facing", (int)this.facing);
	        
	    }
	 public void readFromNBT(NBTTagCompound par1NBTTagCompound)
	    {
	        super.readFromNBT(par1NBTTagCompound);
	        this.facing = par1NBTTagCompound.getInteger("facing");
	    } 	

}