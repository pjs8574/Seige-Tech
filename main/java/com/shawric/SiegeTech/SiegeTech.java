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
	public static Block improvedShawcrete;
	
	public static Block basicSeitersonicExplosive;
	
	public static Item basicPandaNade;
	public static Item improvedPandaNade;
	
	public static Item tierCraftingItemNethercite;
	public static Item tierCraftingItemEndrite;
	public static Item tierCraftingItemAstralium;
	
	public static final int basicPandaNadeEntityId = 1337;
	public static final int improvedPandaNadeEntityId = 1338;
	public static final int basicSeitersonicExplosiveEntityPrimedId = 1339;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
	
		//Blocks
		basicShawcrete = new BasicShawcreteBlock().setBlockName("basicShawcrete");
		improvedShawcrete = new ImprovedShawcreteBlock().setBlockName("improvedShawcrete");
		basicSeitersonicExplosive = new BasicSeitersonicExplosiveBlock().setBlockName("basicSeitersonicExplosive");
		
		//items
		basicPandaNade = new BasicPandaNadeItem();		
		improvedPandaNade = new ImprovedPandaNadeItem();
	
		tierCraftingItemNethercite = new TierCraftingItem("tierCraftingItemNethercite",2);
		tierCraftingItemEndrite = new TierCraftingItem("tierCraftingItemEndrite",3);
		tierCraftingItemAstralium = new TierCraftingItem("tierCraftingItemAstralium",4);
		
		//Register the Blocks
		GameRegistry.registerBlock(basicShawcrete, modid + (basicShawcrete.getUnlocalizedName().substring(5)));
		GameRegistry.registerBlock(improvedShawcrete, modid + (improvedShawcrete.getUnlocalizedName().substring(5)));
		GameRegistry.registerBlock(basicSeitersonicExplosive, modid + (basicSeitersonicExplosive.getUnlocalizedName().substring(5)));
				
		//Register the Items
		GameRegistry.registerItem(basicPandaNade, modid + (basicPandaNade.getUnlocalizedName().substring(5)));
		GameRegistry.registerItem(improvedPandaNade, modid + (improvedPandaNade.getUnlocalizedName().substring(5)));
				
		GameRegistry.registerItem(tierCraftingItemNethercite, modid + (tierCraftingItemNethercite.getUnlocalizedName().substring(5)));
		GameRegistry.registerItem(tierCraftingItemEndrite, modid + (tierCraftingItemEndrite.getUnlocalizedName().substring(5)));
		GameRegistry.registerItem(tierCraftingItemAstralium, modid + (tierCraftingItemAstralium.getUnlocalizedName().substring(5)));
				
		//register the Entitys
		EntityRegistry.registerModEntity(BasicPandaNadeEntity.class, modid + (basicPandaNade.getUnlocalizedName().substring(5)) + "entity", basicPandaNadeEntityId, this, 80, 3, true);
		EntityRegistry.registerModEntity(ImprovedPandaNadeEntity.class, modid + (improvedPandaNade.getUnlocalizedName().substring(5)) + "entity", improvedPandaNadeEntityId, this, 80, 3, true);
		EntityRegistry.registerModEntity(BasicSeitersonicExplosiveEntityPrimed.class, modid + (basicSeitersonicExplosive.getUnlocalizedName().substring(5)) + "entity", basicSeitersonicExplosiveEntityPrimedId, this, 80, 3, true);
		
	}
	
	
	@EventHandler
	public void Init(FMLInitializationEvent event){
		
		ItemStack gravelStack = new ItemStack(Blocks.gravel);
		ItemStack sandStack = new ItemStack(Blocks.sand);
		ItemStack cobblestoneStack = new ItemStack(Blocks.cobblestone);
		ItemStack basicShawcreteStack = new ItemStack(SiegeTech.basicShawcrete);
		ItemStack obsidianStack = new ItemStack(Blocks.obsidian);
		ItemStack flintStack =new ItemStack(Items.flint);
		ItemStack stringStack =new ItemStack(Items.string);
		ItemStack tntStack = new ItemStack(Blocks.tnt);
		ItemStack basicPandaNadeStack =new ItemStack(SiegeTech.basicPandaNade);
		ItemStack blazerodStack =new ItemStack(Items.blaze_rod);
		ItemStack diamondStack = new ItemStack(Items.diamond);
		ItemStack netherQuartzStack = new ItemStack(Items.quartz);
		ItemStack enderPearlStack = new ItemStack(Items.ender_pearl);
		ItemStack endstoneStack = new ItemStack(Blocks.end_stone);
		ItemStack nethreciteStack = new ItemStack(SiegeTech.tierCraftingItemNethercite);
		ItemStack endriteStack = new ItemStack(SiegeTech.tierCraftingItemEndrite);
		ItemStack netherstarStack = new ItemStack(Items.nether_star);
		
		//Tier Crafting Materials
		//Nethercite
		GameRegistry.addShapedRecipe(new ItemStack(SiegeTech.tierCraftingItemNethercite,8), new Object[] {"BON", "ODO", "BON", 'D', diamondStack, 'O', obsidianStack, 'B', blazerodStack, 'N', netherQuartzStack});
		//Endrite
		GameRegistry.addShapedRecipe(new ItemStack(SiegeTech.tierCraftingItemEndrite,8), new Object[] {"EOP", "ONO", "EOP", 'N', nethreciteStack, 'O', obsidianStack, 'E', endstoneStack, 'P', enderPearlStack});
		//Astralium
		GameRegistry.addShapedRecipe(new ItemStack(SiegeTech.tierCraftingItemAstralium,8), new Object[] {"NNN", "NSE", "EEE", 'N', nethreciteStack, 'E', endriteStack, 'S', netherstarStack});
		
		//recipe for basic shawcrete
		GameRegistry.addShapedRecipe(new ItemStack(SiegeTech.basicShawcrete,8), new Object[] {"XCY", "XCY", "XCY", 'X', gravelStack, 'C', cobblestoneStack, 'Y', sandStack});
	   
		//recipe for advanced shawcrete 
		
		System.out.println(basicShawcreteStack.toString());
		GameRegistry.addShapedRecipe(new ItemStack(SiegeTech.improvedShawcrete,8), new Object[] {"RRR", "RNR", "RRR", 'R', basicShawcreteStack, 'N', nethreciteStack});
	   
		//recipe for basic PandaNade
		GameRegistry.addShapedRecipe(new ItemStack(SiegeTech.basicPandaNade,2), new Object[] {" S ", "FTF", "FFF", 'T', tntStack, 'F', flintStack, 'S', stringStack});
		
		//recipe for advanced PandaNade
		GameRegistry.addShapedRecipe(new ItemStack(SiegeTech.improvedPandaNade,2), new Object[] {"POP", "PNP", "POP", 'P', basicPandaNadeStack, 'O', obsidianStack, 'N', nethreciteStack});
		
		//recipe for basic Seitersonciexplosive
		GameRegistry.addShapedRecipe(new ItemStack(SiegeTech.basicSeitersonicExplosive,1), new Object[] {"TTT", "OBO", "TTT", 'T', tntStack, 'O', obsidianStack, 'B', blazerodStack});
		
		

		proxy.registerRenderGrenade(BasicPandaNadeEntity.class, basicPandaNade);
		proxy.registerRenderGrenade(ImprovedPandaNadeEntity.class, improvedPandaNade);
		proxy.registerRenderExplosive();
		proxy.registerSounds();
		
	}

}
