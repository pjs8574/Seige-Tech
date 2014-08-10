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

@Mod(modid = "shawric_siegetech", name = "Siege Tech Mod", version = "0.8.5 MC_1.7.10")

public class SiegeTech {

	
	@SidedProxy(clientSide = "com.shawric.SiegeTech.Client", serverSide = "com.shawric.SiegeTech.Common")
	public static Common proxy;
	
	
	public static final String modid = "shawric_siegetech";
	public static CreativeTabs tabMyMod = new SiegetechCreativeTab("tabSiegeTech");
	
	public static Block basicShawcrete;
	public static Block improvedShawcrete;
	public static Block advancedShawcrete;
	public static Block eliteShawcrete;
	
	public static Block basicSeitersonicExplosive;
	public static Block improvedSeitersonicExplosive;
	public static Block advancedSeitersonicExplosive;
	public static Block eliteSeitersonicExplosive;
	
	public static Item basicPandaNade;
	public static Item improvedPandaNade;
	public static Item advancedPandaNade;
	public static Item elitePandaNade;
	
	public static Item tierCraftingItemNethrecite;
	public static Item tierCraftingItemEndrite;
	public static Item tierCraftingItemAstralium;
	
	public static final int basicPandaNadeEntityId = 1337;
	public static final int improvedPandaNadeEntityId = 1338;
	public static final int advancedPandaNadeEntityId = 1339;
	public static final int elitePandaNadeEntityId = 1340;
	
	public static final int basicSeitersonicExplosiveEntityPrimedId = 1341;
	public static final int improvedSeitersonicExplosiveEntityPrimedId = 1342;
	public static final int advancedSeitersonicExplosiveEntityPrimedId = 1343;
	public static final int eliteSeitersonicExplosiveEntityPrimedId = 1344;
	
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
	
		//Blocks
		
		//create blocks of shawcrete, takes string - Name, Into Tier, int Base HP
		basicShawcrete = new ShawcreteBlock("basicShawcrete", 1, 15);
		improvedShawcrete = new ShawcreteBlock("improvedShawcrete", 2, 30);
		advancedShawcrete = new ShawcreteBlock("advancedShawcrete", 3, 60);
		eliteShawcrete = new ShawcreteBlock("eliteShawcrete", 4, 90);
		
		
		basicSeitersonicExplosive = new SeitersonicExplosiveBlock("basicSeitersonicExplosive", 1);
		improvedSeitersonicExplosive = new SeitersonicExplosiveBlock("improvedSeitersonicExplosive", 2);
		advancedSeitersonicExplosive = new SeitersonicExplosiveBlock("advancedSeitersonicExplosive", 3);
		eliteSeitersonicExplosive = new SeitersonicExplosiveBlock("eliteSeitersonicExplosive", 4);
		
		
		//items
		basicPandaNade = new PandaNadeItem("basicPandaNade", 1);		
		improvedPandaNade = new PandaNadeItem("improvedPandaNade", 2);
		advancedPandaNade = new PandaNadeItem("advancedPandaNade", 3);
		elitePandaNade = new PandaNadeItem("elitePandaNade", 4);
		
	
		tierCraftingItemNethrecite = new TierCraftingItem("tierCraftingItemNethrecite",2);
		tierCraftingItemEndrite = new TierCraftingItem("tierCraftingItemEndrite",3);
		tierCraftingItemAstralium = new TierCraftingItem("tierCraftingItemAstralium",4);
		
		//Register the Blocks
		GameRegistry.registerBlock(basicShawcrete, modid + (basicShawcrete.getUnlocalizedName().substring(5)));
		GameRegistry.registerBlock(improvedShawcrete, modid + (improvedShawcrete.getUnlocalizedName().substring(5)));
		GameRegistry.registerBlock(advancedShawcrete, modid + (advancedShawcrete.getUnlocalizedName().substring(5)));
		GameRegistry.registerBlock(eliteShawcrete, modid + (eliteShawcrete.getUnlocalizedName().substring(5)));
		
		GameRegistry.registerBlock(basicSeitersonicExplosive, modid + (basicSeitersonicExplosive.getUnlocalizedName().substring(5)));
		GameRegistry.registerBlock(improvedSeitersonicExplosive, modid + (improvedSeitersonicExplosive.getUnlocalizedName().substring(5)));
		GameRegistry.registerBlock(advancedSeitersonicExplosive, modid + (advancedSeitersonicExplosive.getUnlocalizedName().substring(5)));
		GameRegistry.registerBlock(eliteSeitersonicExplosive, modid + (eliteSeitersonicExplosive.getUnlocalizedName().substring(5)));
			
		
		
		//Register the Items
		GameRegistry.registerItem(basicPandaNade, modid + (basicPandaNade.getUnlocalizedName().substring(5)));
		GameRegistry.registerItem(improvedPandaNade, modid + (improvedPandaNade.getUnlocalizedName().substring(5)));
		GameRegistry.registerItem(advancedPandaNade, modid + (advancedPandaNade.getUnlocalizedName().substring(5)));	
		GameRegistry.registerItem(elitePandaNade, modid + (elitePandaNade.getUnlocalizedName().substring(5)));
		
		GameRegistry.registerItem(tierCraftingItemNethrecite, modid + (tierCraftingItemNethrecite.getUnlocalizedName().substring(5)));
		GameRegistry.registerItem(tierCraftingItemEndrite, modid + (tierCraftingItemEndrite.getUnlocalizedName().substring(5)));
		GameRegistry.registerItem(tierCraftingItemAstralium, modid + (tierCraftingItemAstralium.getUnlocalizedName().substring(5)));
				
		//register the Entitys
		EntityRegistry.registerModEntity(PandaNadeEntity.class, modid + "." +(basicPandaNade.getUnlocalizedName().substring(5)) + "Entity", basicPandaNadeEntityId, this, 80, 3, true);
		EntityRegistry.registerModEntity(PandaNadeEntity.class, modid + "." +(improvedPandaNade.getUnlocalizedName().substring(5)) + "Entity", improvedPandaNadeEntityId, this, 80, 3, true);
		EntityRegistry.registerModEntity(PandaNadeEntity.class, modid + "." +(advancedPandaNade.getUnlocalizedName().substring(5)) + "Entity", advancedPandaNadeEntityId, this, 80, 3, true);
		EntityRegistry.registerModEntity(PandaNadeEntity.class, modid + "." +(elitePandaNade.getUnlocalizedName().substring(5)) + "Entity", elitePandaNadeEntityId, this, 80, 3, true);

		
		EntityRegistry.registerModEntity(SeitersonicExplosiveEntityPrimed.class, modid + "." + (basicSeitersonicExplosive.getUnlocalizedName().substring(5)) + "Entity", basicSeitersonicExplosiveEntityPrimedId, this, 80, 3, true);
		EntityRegistry.registerModEntity(SeitersonicExplosiveEntityPrimed.class, modid + "." + (improvedSeitersonicExplosive.getUnlocalizedName().substring(5)) + "Entity", improvedSeitersonicExplosiveEntityPrimedId, this, 80, 3, true);
		EntityRegistry.registerModEntity(SeitersonicExplosiveEntityPrimed.class, modid + "." + (advancedSeitersonicExplosive.getUnlocalizedName().substring(5)) + "Entity", advancedSeitersonicExplosiveEntityPrimedId, this, 80, 3, true);
		EntityRegistry.registerModEntity(SeitersonicExplosiveEntityPrimed.class, modid + "." + (eliteSeitersonicExplosive.getUnlocalizedName().substring(5)) + "Entity", eliteSeitersonicExplosiveEntityPrimedId, this, 80, 3, true);
		
	}
	
	
	@EventHandler
	public void Init(FMLInitializationEvent event){
		
		ItemStack gravelStack = new ItemStack(Blocks.gravel);
		ItemStack sandStack = new ItemStack(Blocks.sand);
		ItemStack cobblestoneStack = new ItemStack(Blocks.cobblestone);
		
		ItemStack obsidianStack = new ItemStack(Blocks.obsidian);
		ItemStack flintStack =new ItemStack(Items.flint);
		ItemStack stringStack =new ItemStack(Items.string);
		ItemStack tntStack = new ItemStack(Blocks.tnt);
		
		ItemStack blazerodStack =new ItemStack(Items.blaze_rod);
		ItemStack diamondStack = new ItemStack(Items.diamond);
		ItemStack netherQuartzStack = new ItemStack(Items.quartz);
		ItemStack redstoneBlockStack = new ItemStack(Blocks.redstone_block);
		
		ItemStack enderPearlStack = new ItemStack(Items.ender_pearl);
		ItemStack endstoneStack = new ItemStack(Blocks.end_stone);
		ItemStack netherstarStack = new ItemStack(Items.nether_star);
		
		ItemStack basicPandaNadeStack =new ItemStack(SiegeTech.basicPandaNade);
		ItemStack improvedPandaNadeStack =new ItemStack(SiegeTech.improvedPandaNade);
		ItemStack advancedPandaNadeStack =new ItemStack(SiegeTech.advancedPandaNade);
		
		ItemStack basicShawcreteStack = new ItemStack(SiegeTech.basicShawcrete);
		ItemStack improvedShawcreteStack = new ItemStack(SiegeTech.improvedShawcrete);
		ItemStack advancedShawcreteStack = new ItemStack(SiegeTech.advancedShawcrete);

		ItemStack nethreciteStack = new ItemStack(SiegeTech.tierCraftingItemNethrecite);
		ItemStack endriteStack = new ItemStack(SiegeTech.tierCraftingItemEndrite);
		ItemStack astraliumStack = new ItemStack(SiegeTech.tierCraftingItemAstralium);
		
		//Tier Crafting Materials
		//Nethercite
		GameRegistry.addShapedRecipe(new ItemStack(SiegeTech.tierCraftingItemNethrecite,8), new Object[] {"BON", "ODO", "BON", 'D', diamondStack, 'O', obsidianStack, 'B', blazerodStack, 'N', netherQuartzStack});
		//Endrite
		GameRegistry.addShapedRecipe(new ItemStack(SiegeTech.tierCraftingItemEndrite,8), new Object[] {"EOP", "ONO", "EOP", 'N', nethreciteStack, 'O', obsidianStack, 'E', endstoneStack, 'P', enderPearlStack});
		//Astralium
		GameRegistry.addShapedRecipe(new ItemStack(SiegeTech.tierCraftingItemAstralium,8), new Object[] {"NNN", "NSE", "EEE", 'N', nethreciteStack, 'E', endriteStack, 'S', netherstarStack});
		
		//recipes for shawcrete
		GameRegistry.addShapedRecipe(new ItemStack(SiegeTech.basicShawcrete,8), new Object[] {"XCY", "XCY", "XCY", 'X', gravelStack, 'C', cobblestoneStack, 'Y', sandStack});
		GameRegistry.addShapedRecipe(new ItemStack(SiegeTech.improvedShawcrete,16), new Object[] {"RRR", "RNR", "RRR", 'R', basicShawcreteStack, 'N', nethreciteStack});
		GameRegistry.addShapedRecipe(new ItemStack(SiegeTech.advancedShawcrete,32), new Object[] {"RRR", "RER", "RRR", 'R', improvedShawcreteStack, 'E', endriteStack});
		GameRegistry.addShapedRecipe(new ItemStack(SiegeTech.eliteShawcrete,64), new Object[] {"RRR", "RNR", "RRR", 'R', advancedShawcreteStack, 'N', astraliumStack});
		   
		//recipe for PandaNades
		GameRegistry.addShapedRecipe(new ItemStack(SiegeTech.basicPandaNade,2), new Object[] {"FSF", "FTF", "FFF", 'T', tntStack, 'F', flintStack, 'S', stringStack});
		GameRegistry.addShapedRecipe(new ItemStack(SiegeTech.improvedPandaNade,2), new Object[] {"POP", "PNP", "POP", 'P', basicPandaNadeStack, 'O', obsidianStack, 'N', nethreciteStack});
		GameRegistry.addShapedRecipe(new ItemStack(SiegeTech.advancedPandaNade,2), new Object[] {"POP", "PNP", "POP", 'P', improvedPandaNadeStack, 'O', obsidianStack, 'N', endriteStack});
		GameRegistry.addShapedRecipe(new ItemStack(SiegeTech.elitePandaNade,2), new Object[] {"POP", "PNP", "POP", 'P', advancedPandaNadeStack, 'O', obsidianStack, 'N', astraliumStack});
		
		
		
		//recipe for basic Seitersonciexplosive
		GameRegistry.addShapedRecipe(new ItemStack(SiegeTech.basicSeitersonicExplosive,1), new Object[] {"TOT", "ORO", "TOT", 'T', tntStack, 'O', obsidianStack, 'R', redstoneBlockStack});
		GameRegistry.addShapedRecipe(new ItemStack(SiegeTech.improvedSeitersonicExplosive,1), new Object[] {"TOT", "ORO", "TOT", 'T', tntStack, 'O', obsidianStack, 'R', nethreciteStack});
		GameRegistry.addShapedRecipe(new ItemStack(SiegeTech.advancedSeitersonicExplosive,1), new Object[] {"TOT", "ORO", "TOT", 'T', tntStack, 'O', obsidianStack, 'R', endriteStack});
		GameRegistry.addShapedRecipe(new ItemStack(SiegeTech.eliteSeitersonicExplosive,1), new Object[] {"TOT", "ORO", "TOT", 'T', tntStack, 'O', obsidianStack, 'R', astraliumStack});
		
		

		proxy.registerRenderGrenade(PandaNadeEntity.class, basicPandaNade);
		proxy.registerRenderGrenade(PandaNadeEntity.class, improvedPandaNade);
		proxy.registerRenderGrenade(PandaNadeEntity.class, advancedPandaNade);
		proxy.registerRenderGrenade(PandaNadeEntity.class, elitePandaNade);
		
		proxy.registerRenderExplosive(SeitersonicExplosiveEntityPrimed.class, basicSeitersonicExplosive);
		proxy.registerRenderExplosive(SeitersonicExplosiveEntityPrimed.class, improvedSeitersonicExplosive);
		proxy.registerRenderExplosive(SeitersonicExplosiveEntityPrimed.class, advancedSeitersonicExplosive);
		proxy.registerRenderExplosive(SeitersonicExplosiveEntityPrimed.class, eliteSeitersonicExplosive);
		
		
		proxy.registerSounds();
		
	}

}
