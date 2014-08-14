package com.shawric.SiegeTech;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class BasicSeitersonicExplosiveEntityPrimed extends SeitersonicExplosiveEntityPrimed
{
    
	
	/** How long the fuse is */
    public int fuse = 80;
    private EntityLivingBase explosivePlacedBy;

    private int explosionDirection;  //KEY : 0 bottom, 1 top , 2 North. 3 South, 4 West ,5 East
    private float explosionPower;
    private int baseExplosionOffset = 3;
	private static float baseExplosionPower = 1.75F;
	private int blockTier;
	float tierAdjsutedExplosionPower;
	private String blockText;
    

    public BasicSeitersonicExplosiveEntityPrimed(World world1, double x, double y, double z, EntityLivingBase placingEntity, int explDir, int tier)
    {
    	this(world1);

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
        
        this.blockTier = tier;

        
        Minecraft.getMinecraft().thePlayer.sendChatMessage("Creating the Entity! TIER IS:" + this.blockTier);
        
    }

    
    public BasicSeitersonicExplosiveEntityPrimed(World world1)
    {
        super(world1);
        this.preventEntitySpawning = true;
        this.setSize(0.98F, 0.98F);
        this.yOffset = this.height / 2.0F;
        Minecraft.getMinecraft().thePlayer.sendChatMessage("PTHER CONT HIT");
        
    }
    
    
}
