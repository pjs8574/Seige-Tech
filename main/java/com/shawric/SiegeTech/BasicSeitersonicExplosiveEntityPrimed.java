package com.shawric.SiegeTech;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class BasicSeitersonicExplosiveEntityPrimed extends Entity
{
    /** How long the fuse is */
    public int fuse = 80;
    private EntityLivingBase explosivePlacedBy;
    private static final String __OBFID = "CL_00001681";
    private int explosionDirection;  //KEY : 0 bottom, 1 top , 2 North. 3 South, 4 West ,5 East
    private float explosionPower = 3.5F;
    
    
    public BasicSeitersonicExplosiveEntityPrimed(World world1)
    {
        super(world1);
        this.preventEntitySpawning = true;
        this.setSize(0.98F, 0.98F);
        this.yOffset = this.height / 2.0F;
    }

    public BasicSeitersonicExplosiveEntityPrimed(World wordl1, double x, double y, double z, EntityLivingBase placingEntity, int explDir)
    {
        this(wordl1);
        this.setPosition(x, y, z);
        float f = (float)(Math.random() * Math.PI * 2.0D);
        this.motionX = (double)(-((float)Math.sin((double)f)) * 0.02F);
        this.motionY = 0.20000000298023224D;
        this.motionZ = (double)(-((float)Math.cos((double)f)) * 0.02F);
        this.fuse = 80;
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
        this.explosivePlacedBy = placingEntity;
        
        this.explosionDirection = explDir;
        
        //Minecraft.getMinecraft().thePlayer.sendChatMessage("Creating the Entity!");
        
    }

    protected void entityInit() {}

    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
     * prevent them from trampling crops
     */
    protected boolean canTriggerWalking()
    {
        return false;
    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith()
    {
        return !this.isDead;
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.motionY -= 0.03999999910593033D;
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.9800000190734863D;
        this.motionY *= 0.9800000190734863D;
        this.motionZ *= 0.9800000190734863D;

        if (this.onGround)
        {
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
            this.motionY *= -0.5D;
        }

        if (this.fuse-- <= 0)
        {
            this.setDead();

            if (!this.worldObj.isRemote)
            {
                this.explode();
            }
        }
        else
        {
            this.worldObj.spawnParticle("smoke", this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
        }
    }

    private void explode()
    {
    	 // SIDE KEY : 0 bottom, 1 top , 2 North. 3 South, 4 West ,5 East
    	 // Pos X is east Neg X is West Ned Z is north Pos Z is south
    	/*
    	if(this.explosionDirection == 2)
    	{
    		Minecraft.getMinecraft().thePlayer.sendChatMessage("Creating the explosion NORTH");
    		this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ-10, this.explosionPower, true);	
    	}
    	
    	if(this.explosionDirection == 3)
    	{
    		Minecraft.getMinecraft().thePlayer.sendChatMessage("Creating the explosion South");
    		this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ+10, this.explosionPower, true);
    	}
    	
    	if(this.explosionDirection == 4)
    	{
    		Minecraft.getMinecraft().thePlayer.sendChatMessage("Creating the explosion West");
    		this.worldObj.createExplosion(this, this.posX-10, this.posY, this.posZ, this.explosionPower, true);
    	}
    	
    	if(this.explosionDirection == 5)
    	{
    		Minecraft.getMinecraft().thePlayer.sendChatMessage("Creating the explosion East");
    		this.worldObj.createExplosion(this, this.posX+10, this.posY, this.posZ, this.explosionPower, true);	
    	}
    	
    	*/
    	
    	switch(this.explosionDirection)
    	{
    	    case 2: 
    	    Minecraft.getMinecraft().thePlayer.sendChatMessage("Creating the explosion NORTH");
    		this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, this.explosionPower, true);
    		this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ-3, this.explosionPower, true);
    	        break;
    	    case 3:  
    	    Minecraft.getMinecraft().thePlayer.sendChatMessage("Creating the explosion South");
    		this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, this.explosionPower, true);
    		this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ+3, this.explosionPower, true);
    	        break;
    	    case 4: 
    	    Minecraft.getMinecraft().thePlayer.sendChatMessage("Creating the explosion West");
    		this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, this.explosionPower, true);
    		this.worldObj.createExplosion(this, this.posX-3, this.posY, this.posZ, this.explosionPower, true);
    	        break;
    	    case 5:  
    	    Minecraft.getMinecraft().thePlayer.sendChatMessage("Creating the explosion East");
    		this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, this.explosionPower, true);
    		this.worldObj.createExplosion(this, this.posX+3, this.posY, this.posZ, this.explosionPower, true);
    	        break;
    	}
    	
    	
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    protected void writeEntityToNBT(NBTTagCompound tagCompound)
    {
    	tagCompound.setByte("Fuse", (byte)this.fuse);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    protected void readEntityFromNBT(NBTTagCompound tagCompound)
    {
        this.fuse = tagCompound.getByte("Fuse");
    }

    @SideOnly(Side.CLIENT)
    public float getShadowSize()
    {
        return 0.0F;
    }

    /**
     * returns null or the entityliving it was placed or ignited by
     */
    public EntityLivingBase getExplosivePlacedBy()
    {
        return this.explosivePlacedBy;
    }
}
