package com.shawric.SiegeTech;

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
//RenderingRegistry.registerEntityRenderingHandler(BasicPandaNadeEntity.class, new RenderSnowball(SeigeTech.basicPandaNade));			
RenderingRegistry.registerEntityRenderingHandler(entityClass, new RenderSnowball(grenade));
}

@Override
public void registerRenderExplosive()
{		
		
RenderingRegistry.registerEntityRenderingHandler(BasicSeitersonicExplosiveEntityPrimed.class, new RenderBasicSeitersonicExplosiveEntityPrimed());
}




@Override
public void registerSounds() {}
}
