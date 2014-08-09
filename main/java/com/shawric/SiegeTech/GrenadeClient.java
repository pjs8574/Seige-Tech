package com.shawric.SiegeTech;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.Item;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class GrenadeClient extends GrenadeCommon
{
	

	
@Override
public void registerRenderGrenade(Class entityClass, Item grenade)
{		
//OLD CODE - always renders a basic nade
//RenderingRegistry.registerEntityRenderingHandler(PandaNadeEntity.class, new RenderSnowball(SeigeTech.basicPandaNade));			
RenderingRegistry.registerEntityRenderingHandler(entityClass, new RenderSnowball(grenade));
}

@Override
public void registerRenderExplosive(Class expEntityClass, Block blockRender)
{		
		
RenderingRegistry.registerEntityRenderingHandler(expEntityClass, new RenderBasicSeitersonicExplosiveEntityPrimed(blockRender));
}




@Override
public void registerSounds() {}
}
