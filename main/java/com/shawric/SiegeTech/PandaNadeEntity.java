package com.shawric.SiegeTech;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;

public class PandaNadeEntity extends EntityThrowable {
	
	
	private int itemTier;
	private float nadeForce;
	private float baseNadeDmg = 1.5F;
	
	
	public PandaNadeEntity(World par1World, double par2, double par4, double par6, int tier)
	{
	super(par1World, par2, par4, par6);
	
	this.itemTier = tier;
	
	this.nadeForce = (baseNadeDmg * tier);
	
	}
	
	public PandaNadeEntity(World par1World, EntityLivingBase par2EntityLivingBase, int tier)
	{
	super(par1World, par2EntityLivingBase);
	
	this.itemTier = tier;
	
	this.nadeForce = (baseNadeDmg * tier);
	
	}
	public PandaNadeEntity(World par1World)
	{
	super(par1World);
	}
	
	
	
	 @Override
	 protected void entityInit()
	 {
	        
		 //System.out.println("<<<<<DATA WATCHER SET>>>>" + this.itemTier);
		 this.dataWatcher.addObject(17, new Integer(this.itemTier));
	 }

	
	@Override
	/**
	     * Called when this EntityThrowable hits a block or entity.
	     */
	protected void onImpact(MovingObjectPosition mop)
	{
	       
		
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
	         	*
	         	*HAD A PROBLEM WITH EXPLOSIONS NOT BREAKING BLOCKS WHEN HIT FROM A SPECIFIC ANGLE
	         	*Fixed it by using switch statement to put the source of the explosion inside the block it hit, thus guaranteeing the explosions starts there
	         	*HENCE THE NESTED IF STATEMENT IN EACH CASE
	         	*/
	        	 	switch(mop.sideHit)
	        	 		{
	        	 			case 0: //BOTTOM
	        	 				mop.blockY--;
	        	 				
	        	 				//System.out.println("Entity landed Bottom");
	        	 				
	        	 				if (!this.worldObj.isRemote)
	        		         	{
	        	        	 		//fix for the  tier 1 grenade, note the additional Coord value:
	        	 					if(this.itemTier==1)
	        	        	 		{
	        	 					this.worldObj.newExplosion(this, mop.blockX, (mop.blockY+1), mop.blockZ, nadeForce, false, true);
	        	        	 		} else{this.worldObj.newExplosion(this, mop.blockX, mop.blockY, mop.blockZ, nadeForce, false, true);}
	        		         	}
	        	 				
	        	 				break;
	        	 			case 1: //TOP
	        	 				mop.blockY++;
	        	 				
	        	 				//System.out.println("Entity landed Top");
	        	 				
	        	 				if (!this.worldObj.isRemote)
	        		         	{
	        	        	 		
	        	 					//fix for the  tier 1 grenade, note the additional Coord value:
	        	 					if(this.itemTier==1)
	        	        	 		{
	        	 					this.worldObj.newExplosion(this, mop.blockX, (mop.blockY-1), mop.blockZ, nadeForce, false, true);
	        	        	 		} else{this.worldObj.newExplosion(this, mop.blockX, mop.blockY, mop.blockZ, nadeForce, false, true);}

	        		         	}
	        	 				
	        	 				break;
	        	 			case 2: //EAST
	        	 				mop.blockZ--;
	        	 				
	        	 				//System.out.println("Entity landed East");
	        	 				
	        	 				if (!this.worldObj.isRemote)
	        		         	{
	        	        	 		
	        	 					//fix for the  tier 1 grenade, note the additional Coord value:
	        	 					if(this.itemTier==1)
	        	        	 		{
	        	 					this.worldObj.newExplosion(this, mop.blockX, mop.blockY, (mop.blockZ+1), nadeForce, false, true);
	        	        	 		} else{this.worldObj.newExplosion(this, mop.blockX, mop.blockY, mop.blockZ, nadeForce, false, true);}
	        	 					
	        		         	}
	        	 				
	        	 				
	        	 				break;
	        	 			case 3: //WEST
	        	 				mop.blockZ++;
	        	 				
	        	 				//System.out.println("Entity landed West");
	        	 				
	        	 				if (!this.worldObj.isRemote)
	        		         	{
	        	        	 		
	        	 					//fix for the  tier 1 grenade, note the additional Coord value:
	        	 					if(this.itemTier==1)
	        	        	 		{
	        	 					this.worldObj.newExplosion(this, mop.blockX, mop.blockY, (mop.blockZ-1), nadeForce, false, true);
	        	        	 		} else{this.worldObj.newExplosion(this, mop.blockX, mop.blockY, mop.blockZ, nadeForce, false, true);}
	        	 					
	        		         	}
	        	 				
	        	 				break;
	        	 			case 4: //NORTH
	        	 				mop.blockX--;
	        	 				
	        	 				//System.out.println("Entity landed North");
	        	 				
	        	 				if (!this.worldObj.isRemote)
	        		         	{
	        	 					//fix for the  tier 1 grenade, note the additional Coord value:
	        	 					if(this.itemTier==1)
	        	        	 		{
	        	 					this.worldObj.newExplosion(this, (mop.blockX+1), mop.blockY, mop.blockZ, nadeForce, false, true);
	        	        	 		} else{this.worldObj.newExplosion(this, mop.blockX, mop.blockY, mop.blockZ, nadeForce, false, true);}
	        	 				
	        		         	}
	        	 				
	        	 				break;
	        	 			case 5: //SOUTH
	        	 				mop.blockX++;
	        	 				
	        	 				//System.out.println("Entity landed South");
	        	 				
	        	 				if (!this.worldObj.isRemote)
	        		         	{
	        	        	 		
	        	 					//fix for the  tier 1 grenade, note the additional Coord value:
	        	 					if(this.itemTier==1)
	        	        	 		{
	        	 					this.worldObj.newExplosion(this, (mop.blockX-1), mop.blockY, mop.blockZ, nadeForce, false, true);
	        	        	 		} else{this.worldObj.newExplosion(this, mop.blockX, mop.blockY, mop.blockZ, nadeForce, false, true);}
	        	 					
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
	



public void setTier(int i)
{
	this.dataWatcher.updateObject(17, new Integer(i));
}


public int getTier()
{
	return this.dataWatcher.getWatchableObjectInt(17);
	
}

}