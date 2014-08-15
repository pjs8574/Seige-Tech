package com.shawric.SiegeTech;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.Item;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class Client extends Common
{
	

	
@Override
public void registerRenderGrenade(Class entityClass, Item grenade)
{		
//OLD CODE - always renders a basic nade
//RenderingRegistry.registerEntityRenderingHandler(PandaNadeEntity.class, new RenderSnowball(SeigeTech.basicPandaNade));
	
RenderingRegistry.registerEntityRenderingHandler(entityClass, new RenderPandaNade(grenade));
}

@Override
public void registerRenderExplosive(Class expEntityClass, Block blockRender, int tier)
{		
		
RenderingRegistry.registerEntityRenderingHandler(expEntityClass, new RenderSeitersonicExplosiveEntityPrimed(blockRender, tier));
}

public void registerRenderArrow(Class entityClass)
{
	RenderingRegistry.registerEntityRenderingHandler(entityClass, new RenderNethreciteArrow());
}


@Override
public void registerSounds() {}
}
