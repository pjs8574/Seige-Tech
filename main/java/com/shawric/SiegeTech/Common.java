package com.shawric.SiegeTech;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class Common
{
	
public void registerRenderGrenade(Class entityClass, Item grenadeToRender) {}
public void registerRenderExplosive(Class entityClass, Block blockRender, int tier) {}
public void registerSounds() {}
public void registerRenderArrow(Class entityClass) {}
public void registerTileEntities() {
	 
    GameRegistry.registerTileEntity(ClaimBlockTileEntity.class, ClaimBlockTileEntity.publicName);
}


}
