package com.shawric.SiegeTech;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;

public class ImprovedPandaNadeEntity extends EntityThrowable {
	/*
	* If you're wondering why I have all of
	* these constructors for no reason, It's
	* used by Forge to use this class, If you
	* don't have these, your minecraft is going
	* to crash!
	*/
	public ImprovedPandaNadeEntity(World par1World, double par2, double par4, double par6)
	{
	super(par1World, par2, par4, par6);
	}
	public ImprovedPandaNadeEntity(World par1World, EntityLivingBase par2EntityLivingBase)
	{
	super(par1World, par2EntityLivingBase);
	}
	public ImprovedPandaNadeEntity(World par1World)
	{
	super(par1World);
	}
	@Override
	/**
	     * Called when this EntityThrowable hits a block or entity.
	     */
	protected void onImpact(MovingObjectPosition mop)
	{
	    
		//set the Force of The explosion
	    float nadeForce = 2.5F;     
		
	    
	    //If the grenade DIRECTly hits an entity, do a bunch of damage to it - no explosion though for some reason
	    if (mop.entityHit != null)
        {
	    	mop.entityHit.attackEntityFrom(DamageSource.setExplosionSource(this.worldObj.newExplosion(this, mop.blockX, (mop.blockY+1), mop.blockZ, nadeForce, false, true)), (nadeForce*5));
        }
		
	       //If this hit's a block, continue
	         if((mop.typeOfHit == MovingObjectType.BLOCK) || (mop.typeOfHit == MovingObjectType.ENTITY) || (mop.typeOfHit == MovingObjectType.MISS))
	         	{
	        	 /*
	        	  * You might be wondering what
	         	* all these case and break are
	         	* These are use to switch the number
	         	* mop.sideHit
	         	*
	         	* Example:
	         	* If mop.sideHit == 3 whatever is in
	         	* case 3 Happens!
	         	*
	         	* This method creates the explosion!
	         	* It uses the entity (Can be null)
	         	* the three coordinates, the strength
	         	* and if it should spawn smoke particles
	         	* around after exploding, the last parameter
	         	* is if it should set neighboring blocks on fire
	         	*
	         	*
	         	*
	         	*HAD A PROBLEM WITH EXPLOSIONS NOT BREAKING BLOCKS WHEN HIT FROM A SPECIFIC ANGLE
	         	*Fixed it by using switch statement to put the source of the explosion inside the block it hit, thus guaranteeing the explosions starts there
	         	* ADVACNED NADE NOTE  Removed the Inside explosion adjustment for advanced nade. the explsion seem big enough to work properly
	         	*
	         	*
	         	*/
	        	 	switch(mop.sideHit)
	        	 		{
	        	 			case 0: //BOTTOM
	        	 				mop.blockY--;
	        	 				
	        	 				System.out.println("Entity landed Bottom");
	        	 				
	        	 				if (!this.worldObj.isRemote)
	        		         	{
	        	        	 		this.worldObj.newExplosion(this, mop.blockX, mop.blockY, mop.blockZ, nadeForce, false, true);
	        		         	}
	        	 				
	        	 				break;
	        	 			case 1: //TOP
	        	 				mop.blockY++;
	        	 				
	        	 				System.out.println("Entity landed Top");
	        	 				
	        	 				if (!this.worldObj.isRemote)
	        		         	{
	        	        	 		this.worldObj.newExplosion(this, mop.blockX, mop.blockY, mop.blockZ, nadeForce, false, true);
	        		         	}
	        	 				
	        	 				break;
	        	 			case 2: //EAST
	        	 				mop.blockZ--;
	        	 				
	        	 				System.out.println("Entity landed East");
	        	 				
	        	 				if (!this.worldObj.isRemote)
	        		         	{
	        	        	 		this.worldObj.newExplosion(this, mop.blockX, mop.blockY, mop.blockZ, nadeForce, false, true);
	        		         	}
	        	 				
	        	 				
	        	 				break;
	        	 			case 3: //WEST
	        	 				mop.blockZ++;
	        	 				
	        	 				System.out.println("Entity landed West");
	        	 				
	        	 				if (!this.worldObj.isRemote)
	        		         	{
	        	        	 		this.worldObj.newExplosion(this, mop.blockX, mop.blockY, mop.blockZ, nadeForce, false, true);
	        		         	}
	        	 				
	        	 				break;
	        	 			case 4: //NORTH
	        	 				mop.blockX--;
	        	 				
	        	 				System.out.println("Entity landed North");
	        	 				
	        	 				if (!this.worldObj.isRemote)
	        		         	{
	        	        	 		this.worldObj.newExplosion(this, mop.blockX, mop.blockY, mop.blockZ, nadeForce, false, true);
	        		         	}
	        	 				
	        	 				break;
	        	 			case 5: //SOUTH
	        	 				mop.blockX++;
	        	 				
	        	 				System.out.println("Entity landed South");
	        	 				
	        	 				if (!this.worldObj.isRemote)
	        		         	{
	        	        	 		this.worldObj.newExplosion(this, mop.blockX, mop.blockY, mop.blockZ, nadeForce, false, true);
	        		         	}
	        	 				
	        	 				break;
	        	 		}

	         } 
	        
	         
	         //If the Server is online and works, kill this entity
	         if (!this.worldObj.isRemote)
	         	{
	        	 this.setDead();
	         	}
         
	}
	}