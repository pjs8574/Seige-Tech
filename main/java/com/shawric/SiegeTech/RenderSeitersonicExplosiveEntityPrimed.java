package com.shawric.SiegeTech;


import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;

import com.shawric.SiegeTech.SeitersonicExplosiveEntityPrimed;

import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSeitersonicExplosiveEntityPrimed extends Render
{

	private RenderBlocks blockRenderer = new RenderBlocks();
    private static final String __OBFID = "CL_00001030";
    private Block blockToRender;
    static int entityTier;

    
    
    public RenderSeitersonicExplosiveEntityPrimed(Block blockRender, int tier)
    {
        this.shadowSize = 0.5F;   
        this.blockToRender =  blockRender;

        this.entityTier = tier;
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    
  
    public void doRender(SeitersonicExplosiveEntityPrimed entityToRender, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
    	
    	
    	GL11.glPushMatrix();
        GL11.glTranslatef((float)p_76986_2_, (float)p_76986_4_, (float)p_76986_6_);
        float f2;

        if ((float)entityToRender.fuse - p_76986_9_ + 1.0F < 10.0F)
        {
            f2 = 1.0F - ((float)entityToRender.fuse - p_76986_9_ + 1.0F) / 10.0F;

            if (f2 < 0.0F)
            {
                f2 = 0.0F;
            }

            if (f2 > 1.0F)
            {
                f2 = 1.0F;
            }

            f2 *= f2;
            f2 *= f2;
            float f3 = 1.0F + f2 * 0.3F;
            GL11.glScalef(f3, f3, f3);
        }

        f2 = (1.0F - ((float)entityToRender.fuse - p_76986_9_ + 1.0F) / 100.0F) * 0.8F;
        this.bindEntityTexture(entityToRender);
        
       String entName = entityToRender.toString();
       //System.out.println("ENTITY INFOO>>>>>"+entName);
       System.out.println("ENTITY TIER>>>>>"+this.entityTier);
      
        this.blockRenderer.renderBlockAsItem(SiegeTech.getSSEBlockToRender(this.entityTier), 0, entityToRender.getBrightness(p_76986_9_));

        if (entityToRender.fuse / 5 % 2 == 0)
        {
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_DST_ALPHA);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, f2);
            this.blockRenderer.renderBlockAsItem(this.blockToRender, 0, 1.0F);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
        }

        GL11.glPopMatrix();
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(SeitersonicExplosiveEntityPrimed explEnt)
    {
    	 return TextureMap.locationBlocksTexture;
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity p_110775_1_)
    {
        
    	//System.out.println("GETTING THIS ENT TEXTURE>>>>" + this.getEntityTexture((SeitersonicExplosiveEntityPrimed)p_110775_1_));
    	//System.out.println("FOR THIS ENTITY>>>>" + p_110775_1_);
    	return this.getEntityTexture((SeitersonicExplosiveEntityPrimed)p_110775_1_);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.doRender((SeitersonicExplosiveEntityPrimed)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }
    
    public static void setBlockTier(int i)
    {
    	entityTier=i;
    }
    
    
    
}