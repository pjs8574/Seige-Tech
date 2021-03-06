package com.shawric.SiegeTech;


import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "shawric_siegetech", name = "Siege Tech Mod", version = "1.1.0 MC_1.7.10")

public class SiegeTech {

	
	@SidedProxy(clientSide = "com.shawric.SiegeTech.Client", serverSide = "com.shawric.SiegeTech.Common")
	public static Common proxy;
	
	int entIDCount = 50;
	
	public static final String modid = "shawric_siegetech";

	public static boolean explProtConfig;
	public static CreativeTabs tabMyMod = new SiegetechCreativeTab("tabSiegeTech");
	
	public static Block claimBlock;
	
	
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
	
	public static Item huntersCompass;
	public static Item whiteList;

	public static Item nethreciteArrow;
	public static Item nethreciteBow;
	
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
	
		
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		explProtConfig = config.get(Configuration.CATEGORY_GENERAL, "Enable_Explosion_Protection_From_ClaimBlocks", false).getBoolean(false);
		config.save();
		
		//Blocks
		
		//create blocks of shawcrete, takes string - Name, Into Tier, int Base HP
		basicShawcrete = new ShawcreteBlock("basicShawcrete", 1, 15);
		improvedShawcrete = new ShawcreteBlock("improvedShawcrete", 2, 30);
		advancedShawcrete = new ShawcreteBlock("advancedShawcrete", 3, 60);
		eliteShawcrete = new ShawcreteBlock("eliteShawcrete", 4, 90);
		
		claimBlock = new ClaimBlock("claimBlock", 1, 200);
		
		//Create Explosive blocks -- Name and Tier
		basicSeitersonicExplosive = new SeitersonicExplosiveBlock("basicSeitersonicExplosive", 1);
		improvedSeitersonicExplosive = new SeitersonicExplosiveBlock("improvedSeitersonicExplosive", 2);
		advancedSeitersonicExplosive = new SeitersonicExplosiveBlock("advancedSeitersonicExplosive", 3);
		eliteSeitersonicExplosive = new SeitersonicExplosiveBlock("eliteSeitersonicExplosive", 4);
		
		
		//Create Useable  Items -- Name and Tier
		basicPandaNade = new PandaNadeItem("basicPandaNade", 1);		
		improvedPandaNade = new PandaNadeItem("improvedPandaNade", 2);
		advancedPandaNade = new PandaNadeItem("advancedPandaNade", 3);
		elitePandaNade = new PandaNadeItem("elitePandaNade", 4);
		
		nethreciteArrow = new NethreciteArrowItem("nethreciteArrow", 2);
		nethreciteBow = new NethreciteBowItem("nethreciteBow", 2);
		
		huntersCompass = new HuntersCompassItem("huntersCompass", 2);
		whiteList = new WhiteListItem("whiteList", 2);
	
	
		//create Crafting Only items -- Name and Tier
		tierCraftingItemNethrecite = new TierCraftingItem("tierCraftingItemNethrecite",2);
		tierCraftingItemEndrite = new TierCraftingItem("tierCraftingItemEndrite",3);
		tierCraftingItemAstralium = new TierCraftingItem("tierCraftingItemAstralium",4);
		
		
		
		//Register the Blocks
		
		GameRegistry.registerBlock(basicShawcrete, modid + (basicShawcrete.getUnlocalizedName().substring(5)));
		GameRegistry.registerBlock(improvedShawcrete, modid + (improvedShawcrete.getUnlocalizedName().substring(5)));
		GameRegistry.registerBlock(advancedShawcrete, modid + (advancedShawcrete.getUnlocalizedName().substring(5)));
		GameRegistry.registerBlock(eliteShawcrete, modid + (eliteShawcrete.getUnlocalizedName().substring(5)));
		
		GameRegistry.registerBlock(claimBlock, modid + (claimBlock.getUnlocalizedName().substring(5)));
		
		GameRegistry.registerBlock(basicSeitersonicExplosive, modid + (basicSeitersonicExplosive.getUnlocalizedName().substring(5)));
		GameRegistry.registerBlock(improvedSeitersonicExplosive, modid + (improvedSeitersonicExplosive.getUnlocalizedName().substring(5)));
		GameRegistry.registerBlock(advancedSeitersonicExplosive, modid + (advancedSeitersonicExplosive.getUnlocalizedName().substring(5)));
		GameRegistry.registerBlock(eliteSeitersonicExplosive, modid + (eliteSeitersonicExplosive.getUnlocalizedName().substring(5)));
			
		
		
		//Register the Items
		GameRegistry.registerItem(basicPandaNade, modid + (basicPandaNade.getUnlocalizedName().substring(5)));
		GameRegistry.registerItem(improvedPandaNade, modid + (improvedPandaNade.getUnlocalizedName().substring(5)));
		GameRegistry.registerItem(advancedPandaNade, modid + (advancedPandaNade.getUnlocalizedName().substring(5)));	
		GameRegistry.registerItem(elitePandaNade, modid + (elitePandaNade.getUnlocalizedName().substring(5)));
		
		GameRegistry.registerItem(nethreciteArrow, modid + (nethreciteArrow.getUnlocalizedName().substring(5)));
		GameRegistry.registerItem(nethreciteBow, modid + (nethreciteBow.getUnlocalizedName().substring(5)));
		GameRegistry.registerItem(huntersCompass, modid + (huntersCompass.getUnlocalizedName().substring(5)));
		GameRegistry.registerItem(whiteList, modid + (whiteList.getUnlocalizedName().substring(5)));
		
		GameRegistry.registerItem(tierCraftingItemNethrecite, modid + (tierCraftingItemNethrecite.getUnlocalizedName().substring(5)));
		GameRegistry.registerItem(tierCraftingItemEndrite, modid + (tierCraftingItemEndrite.getUnlocalizedName().substring(5)));
		GameRegistry.registerItem(tierCraftingItemAstralium, modid + (tierCraftingItemAstralium.getUnlocalizedName().substring(5)));
				
		//register the Entitys
		GameRegistry.registerTileEntity(ClaimBlockTileEntity.class, "ClaimBlockID"+this.getUniqeEntID());
		
		EntityRegistry.registerModEntity(PandaNadeEntity.class, modid + "." +(basicPandaNade.getUnlocalizedName().substring(5)) + "Entity", this.getUniqeEntID(), this, 80, 3, true);
		EntityRegistry.registerModEntity(PandaNadeEntity.class, modid + "." +(improvedPandaNade.getUnlocalizedName().substring(5)) + "Entity", this.getUniqeEntID(), this, 80, 3, true);
		EntityRegistry.registerModEntity(PandaNadeEntity.class, modid + "." +(advancedPandaNade.getUnlocalizedName().substring(5)) + "Entity", this.getUniqeEntID(), this, 80, 3, true);
		EntityRegistry.registerModEntity(PandaNadeEntity.class, modid + "." +(elitePandaNade.getUnlocalizedName().substring(5)) + "Entity", this.getUniqeEntID(), this, 80, 3, true);

		EntityRegistry.registerModEntity(NethreciteArrowEntity.class, modid + "." + (nethreciteArrow.getUnlocalizedName().substring(5)) + "Entity", this.getUniqeEntID(), this, 80, 10, true);
		
		EntityRegistry.registerModEntity(SeitersonicExplosiveEntityPrimed.class, modid + "." + (basicSeitersonicExplosive.getUnlocalizedName().substring(5)) + "Entity", this.getUniqeEntID(), this, 80, 3, true);
		EntityRegistry.registerModEntity(SeitersonicExplosiveEntityPrimed.class, modid + "." + (improvedSeitersonicExplosive.getUnlocalizedName().substring(5)) + "Entity", this.getUniqeEntID(), this, 80, 3, true);
		EntityRegistry.registerModEntity(SeitersonicExplosiveEntityPrimed.class, modid + "." + (advancedSeitersonicExplosive.getUnlocalizedName().substring(5)) + "Entity", this.getUniqeEntID(), this, 80, 3, true);
		EntityRegistry.registerModEntity(SeitersonicExplosiveEntityPrimed.class, modid + "." + (eliteSeitersonicExplosive.getUnlocalizedName().substring(5)) + "Entity", this.getUniqeEntID(), this, 80, 3, true);
		
		
	}
	
	
	@EventHandler
	public void Init(FMLInitializationEvent event){
		
		//initialize the claim even handler
		registerEventListeners();
		
		//Creating item stacks for recipe creations
		ItemStack gravelStack = new ItemStack(Blocks.gravel);
		ItemStack sandStack = new ItemStack(Blocks.sand);
		ItemStack cobblestoneStack = new ItemStack(Blocks.cobblestone);
		
		ItemStack obsidianStack = new ItemStack(Blocks.obsidian);
		ItemStack flintStack =new ItemStack(Items.flint);
		ItemStack stringStack =new ItemStack(Items.string);
		ItemStack tntStack = new ItemStack(Blocks.tnt);
		
		ItemStack blazerodStack =new ItemStack(Items.blaze_rod);
		ItemStack diamondStack = new ItemStack(Items.diamond);
		ItemStack netherQuartzStack = new ItemStack(Blocks.quartz_block);
		ItemStack glowstoneBlockStack = new ItemStack(Blocks.glowstone);
		ItemStack redstoneBlockStack = new ItemStack(Blocks.redstone_block);
		
		ItemStack enderEyeStack =new ItemStack(Items.ender_eye);
		ItemStack compassStack =new ItemStack(Items.compass);
		
		ItemStack arrowStack =new ItemStack(Items.arrow);
		ItemStack bowStack =new ItemStack(Items.bow);
		
		ItemStack paperStack =new ItemStack(Items.paper);
		ItemStack boneMealStack =new ItemStack(Items.dye,0,15);
		ItemStack inkSacStack =new ItemStack(Items.dye,0);
		
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
		
		/* RECIPES */
		
		//Tier Crafting Materials
		//Nethercite
		GameRegistry.addShapedRecipe(new ItemStack(SiegeTech.tierCraftingItemNethrecite, 4), new Object[] {"GBN", "ODO", "GBN", 'D', diamondStack, 'O', obsidianStack, 'B', blazerodStack, 'N', netherQuartzStack, 'G', glowstoneBlockStack});
		//Endrite
		GameRegistry.addShapedRecipe(new ItemStack(SiegeTech.tierCraftingItemEndrite, 4), new Object[] {"EOP", "ONO", "EOP", 'N', nethreciteStack, 'O', obsidianStack, 'E', endstoneStack, 'P', enderPearlStack});
		//Astralium
		GameRegistry.addShapedRecipe(new ItemStack(SiegeTech.tierCraftingItemAstralium, 4), new Object[] {"NNN", "NSE", "EEE", 'N', nethreciteStack, 'E', endriteStack, 'S', netherstarStack});
		
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
		
		//Hunter's Compass
		GameRegistry.addShapedRecipe(new ItemStack(SiegeTech.huntersCompass,1), new Object[] {"NCN", "OEO", "NCN", 'E', enderEyeStack, 'O', obsidianStack, 'N', nethreciteStack, 'C', compassStack});
		
		//Claim Block
		GameRegistry.addShapedRecipe(new ItemStack(SiegeTech.claimBlock,1), new Object[] {"DSD", "SES", "DSD", 'S', basicShawcreteStack, 'E', enderEyeStack, 'D', diamondStack});
		
		//white list
		GameRegistry.addShapedRecipe(new ItemStack(SiegeTech.whiteList,1), new Object[] {"   ", "IPB", "   ", 'P', paperStack, 'B', boneMealStack,'I', inkSacStack});
		
		
		//recipe for Seitersonciexplosive
		GameRegistry.addShapedRecipe(new ItemStack(SiegeTech.basicSeitersonicExplosive,1), new Object[] {"TOT", "ORO", "TOT", 'T', tntStack, 'O', obsidianStack, 'R', redstoneBlockStack});
		GameRegistry.addShapedRecipe(new ItemStack(SiegeTech.improvedSeitersonicExplosive,1), new Object[] {"TOT", "ORO", "TOT", 'T', tntStack, 'O', obsidianStack, 'R', nethreciteStack});
		GameRegistry.addShapedRecipe(new ItemStack(SiegeTech.advancedSeitersonicExplosive,1), new Object[] {"TOT", "ORO", "TOT", 'T', tntStack, 'O', obsidianStack, 'R', endriteStack});
		GameRegistry.addShapedRecipe(new ItemStack(SiegeTech.eliteSeitersonicExplosive,1), new Object[] {"TOT", "ORO", "TOT", 'T', tntStack, 'O', obsidianStack, 'R', astraliumStack});
		
		
		//Nethrecite Bow and Arrow
		GameRegistry.addShapedRecipe(new ItemStack(SiegeTech.nethreciteArrow,1), new Object[] {"NNN", "NAN", "NNN", 'A', arrowStack, 'N', nethreciteStack});
		GameRegistry.addShapedRecipe(new ItemStack(SiegeTech.nethreciteBow,1), new Object[] {" N ", "NBN", " N ", 'B', bowStack, 'N', nethreciteStack});
		
		
		
		//Render registration for entities
		proxy.registerRenderGrenade(PandaNadeEntity.class, basicPandaNade);
		proxy.registerRenderArrow(NethreciteArrowEntity.class);
		proxy.registerRenderExplosive(SeitersonicExplosiveEntityPrimed.class, basicSeitersonicExplosive, 1);
		
		//proxy.registerTileEntities();
		
		proxy.registerSounds();
		
	}

	public static Block getExplosiveBlock(int tier)
	{
		
		if(tier == 1)
		{
			return basicSeitersonicExplosive;
		}
		if(tier == 2)
		{
			return improvedSeitersonicExplosive;
		}
		if(tier == 3)
		{
			return advancedSeitersonicExplosive;
		}
		if(tier == 4)
		{
			return eliteSeitersonicExplosive;
		}
		
		return null;
	}
	
	public static Item getGrenade(int tier)
	{
		
		if(tier == 1)
		{
			return basicPandaNade;
		}
		if(tier == 2)
		{
			return improvedPandaNade;
		}
		if(tier == 3)
		{
			return advancedPandaNade;
		}
		if(tier == 4)
		{
			return elitePandaNade;
		}
		
		return null;
	}
	
	
	
	//used to generate a unique ID for all of my mod entities.
	public int getUniqeEntID()
	{
		this.entIDCount++;
		
	return this.entIDCount;
	}
	
	
	public void registerEventListeners() 
	{
	    // DEBUG
	    System.out.println("Registering event listeners");

	    MinecraftForge.EVENT_BUS.register(new ClaimBlockEventHandler());
	   
	}
	
	
}
