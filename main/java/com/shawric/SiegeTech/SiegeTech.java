package com.shawric.SiegeTech;


import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "SiegeTech", name = "Siege Tech Mod", version = "0.7 MC_1.7.10")

public class SiegeTech {

	
	@SidedProxy(clientSide = "com.shawric.SiegeTech.GrenadeClient", serverSide = "com.shawric.SiegeTech.GrenadeCommon")
	public static GrenadeCommon proxy;
	
	
	public static final String modid = "Shawric_Siegetech";
	public static CreativeTabs tabMyMod = new SiegetechCreativeTab("tabSiegeTech");
	public static Block basicShawcrete;
	public static Block advancedShawcrete;
	
	public static Block basicSeitersonicExplosive;
	
	public static Item basicPandaNade;
	public static Item advancedPandaNade;
	
	public static final int basicPandaNadeEntityId = 1337;
	public static final int advancedPandaNadeEntityId = 1338;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
	
		//Blocks
		basicShawcrete = new BasicShawcreteBlock().setBlockName("basicShawcrete");
		advancedShawcrete = new AdvancedShawcreteBlock().setBlockName("advancedShawcrete");
		
		basicSeitersonicExplosive = new BasicSeitersonicExplosiveBlock().setBlockName("basicSeitersonicExplosive");
		
		//items
		basicPandaNade = new BasicPandaNadeItem();		
		advancedPandaNade = new AdvancedPandaNadeItem();
	
		
		
		
	}
	
	
	@EventHandler
	public void Init(FMLInitializationEvent event){
	
		//Blocks
		GameRegistry.registerBlock(basicShawcrete, modid + (basicShawcrete.getUnlocalizedName().substring(5)));
		GameRegistry.registerBlock(advancedShawcrete, modid + (advancedShawcrete.getUnlocalizedName().substring(5)));
		GameRegistry.registerBlock(basicSeitersonicExplosive, modid + (basicSeitersonicExplosive.getUnlocalizedName().substring(5)));
		
	    //Items
		GameRegistry.registerItem(basicPandaNade, modid + (basicPandaNade.getUnlocalizedName().substring(5)));
		EntityRegistry.registerModEntity(BasicPandaNadeEntity.class, modid + (basicPandaNade.getUnlocalizedName().substring(5)) + "entity", basicPandaNadeEntityId, this, 80, 3, true);
		
		GameRegistry.registerItem(advancedPandaNade, modid + (advancedPandaNade.getUnlocalizedName().substring(5)));
		EntityRegistry.registerModEntity(AdvancedPandaNadeEntity.class, modid + (advancedPandaNade.getUnlocalizedName().substring(5)) + "entity", advancedPandaNadeEntityId, this, 80, 3, true);
		
		//recipe for basic shawcrete
		ItemStack gravelStack = new ItemStack(Blocks.gravel);
		ItemStack sandStack = new ItemStack(Blocks.sand);
		ItemStack cobblestoneStack = new ItemStack(Blocks.cobblestone);
	
		GameRegistry.addShapedRecipe(new ItemStack(SiegeTech.basicShawcrete,8), new Object[] {"XCY", "XCY", "XCY", 'X', gravelStack, 'C', cobblestoneStack, 'Y', sandStack});
	   
		//recipe for advanced shawcrete 
		ItemStack basicShawcreteStack = new ItemStack(SiegeTech.basicShawcrete);
		ItemStack obsidianStack = new ItemStack(Blocks.obsidian);
		
		GameRegistry.addShapedRecipe(new ItemStack(SiegeTech.basicShawcrete,8), new Object[] {"SOS", "SOS", "SOS", 'S', basicShawcreteStack, 'O', obsidianStack});
	   
		//recipe for basic PandaNade
		ItemStack flintStack =new ItemStack(Items.flint);
		ItemStack stringStack =new ItemStack(Items.string);
		ItemStack tntStack = new ItemStack(Blocks.tnt);
		
		GameRegistry.addShapedRecipe(new ItemStack(SiegeTech.basicPandaNade,2), new Object[] {" S ", "FTF", "FFF", 'T', tntStack, 'F', flintStack, 'S', stringStack});
		
		//recipe for advanced PandaNade
		ItemStack basicPandaNadeStack =new ItemStack(SiegeTech.basicPandaNade);
		ItemStack blazerodStack =new ItemStack(Items.blaze_rod);
		
		GameRegistry.addShapedRecipe(new ItemStack(SiegeTech.advancedPandaNade,2), new Object[] {"POP", "PBP", "POP", 'P', basicPandaNadeStack, 'O', obsidianStack, 'B', blazerodStack});
		
		//recipe for basic Seitersonciexplosive
		GameRegistry.addShapedRecipe(new ItemStack(SiegeTech.basicSeitersonicExplosive,1), new Object[] {"TTT", "OBO", "TTT", 'T', tntStack, 'O', obsidianStack, 'B', blazerodStack});
		
		proxy.registerRenderThings(BasicPandaNadeEntity.class, basicPandaNade);
		proxy.registerRenderThings(AdvancedPandaNadeEntity.class, advancedPandaNade);
		proxy.registerSounds();
		
	}

}
