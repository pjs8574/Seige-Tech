package com.shawric.SiegeTech;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;

public class BasicPandaNadeEntity extends PandaNadeEntity {
	
	
	private int itemTier;
	private float nadeForce;
	private float baseNadeDmg = 1.5F;
	
	
	public BasicPandaNadeEntity(World par1World, double par2, double par4, double par6, int tier)
	{
	super(par1World, par2, par4, par6, tier);
	
	this.itemTier = tier;
	
	this.nadeForce = (baseNadeDmg * tier);
	
	}
	
	public BasicPandaNadeEntity(World par1World, EntityLivingBase par2EntityLivingBase, int tier)
	{
	super(par1World, par2EntityLivingBase, tier);
	
	this.itemTier = tier;
	
	this.nadeForce = (baseNadeDmg * tier);
	
	}
	public BasicPandaNadeEntity(World par1World)
	{
	super(par1World);
	}
	

public int getTier()
	{
	return this.itemTier;
	}



}