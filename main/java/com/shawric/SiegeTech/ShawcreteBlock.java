package com.shawric.SiegeTech;

import java.util.Hashtable;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ShawcreteBlock extends Block {

	
	private int baseShawcreteHP;
	private int blockTier;
	
	@SideOnly(Side.CLIENT)
	private IIcon normalHP;
	@SideOnly(Side.CLIENT)
	private IIcon damagedHP;
	
	boolean blockExploded;
	
	public ShawcreteBlock(String name, int tier, int baseHP) {
		super(Material.rock);
		this.setHardness(2);
		this.setResistance(10);
		this.setCreativeTab(SiegeTech.tabMyMod);
		this.baseShawcreteHP = baseHP;
		
		this.setBlockName(name);
		this.blockTier = tier;
	}
	@Override
	 public void onBlockAdded(World world, int x, int y, int z)
	    {
	        	//set the Meta to "max" hp which is a meta int of 15
			world.setBlockMetadataWithNotify(x, y, z, 15, 2);
	        	//Minecraft.getMinecraft().thePlayer.sendChatMessage("New block is places with HP of " + world.getBlockMetadata(x, y, z));
	    }

	//sets the block texture stored in \src\main\resources\assets\shawric_seigetech\textures\blocks
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister icReg)
	{	
	normalHP = icReg.registerIcon(SiegeTech.modid + ":" + this.getUnlocalizedName().substring(5));
	damagedHP = icReg.registerIcon(SiegeTech.modid + ":" + this.getUnlocalizedName().substring(5) + "Damaged");	
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta)
	{
		
	if((meta <= 7) && (meta > 0))
	{	
	return damagedHP;
	}else return normalHP;
	
	
	}
	
	//concrete won't drop itself from being exploded.
	@Override
	public boolean canDropFromExplosion(Explosion p_149659_1_)
    {
        return false;
    }

	//tells the game what to drop when this thing is dug
	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {    
		return Item.getItemFromBlock(this);
    }


	//WHERE THE MAGIC HAPPENS	
	public void onBlockExploded(World par1World, int par2, int par3, int par4, Explosion par5Explosion)
    {
        if (!par1World.isRemote)
        {
        	//turn the exploding entityclass into a string for checking
        	//THIS HAD SOME TICKING ENTITY ISSUE WITH GHAST FIREBALLS
        	//fixed it by making the block more resistant so teh ghast ball diesnt kill it 
        	//Might come back to haunt me, dunno howto fix. Try catch block maybe?
        	
        	String explodeCause = par5Explosion.exploder.getClass().getSimpleName();
        	
        	int exploderTier = 0;        	
        	if(par5Explosion.exploder instanceof SeitersonicExplosiveEntityPrimed){
        	exploderTier = ((SeitersonicExplosiveEntityPrimed) par5Explosion.exploder).getTier();
        	}
        	
        	if(par5Explosion.exploder instanceof PandaNadeEntity )
        		{
            	exploderTier = ((PandaNadeEntity) par5Explosion.exploder).getTier();
            	}
        	/*send info my checking method to see if the shawcrete block gets destroyed or not. 
        	* pass in the world object, name of the entity that cause the explosion, x y z of the block that was blown up, and the Metadata of that block
        	*/
        	
        	//Minecraft.getMinecraft().thePlayer.sendChatMessage("Passing meta" + par1World.getBlockMetadata(par2, par3, par4));
        	this.shawcreteExploded(par1World, explodeCause, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), exploderTier);
        
        }
        
        
    
    }
	
	
	/*cleans up the code by making a list of things that break the shawcrete and just checking them. 
	* pass in the world object, string of the  exploding entity, coordinates of the destroyed block X Y Z
	*/
	
	public void shawcreteExploded(World par1World, String exploder,int blockX, int blockY, int blockZ, int meta, int exploderTier)
    {

		//list of valid exploders and the damage they do (In porcess of making it Minimum damage, which is then adjusted by tier+1)
		Hashtable validExploderEntitysDamage = new Hashtable();
		validExploderEntitysDamage.put("EntityTNTPrimed", new Integer(10));
		validExploderEntitysDamage.put("EntityCreeper", new Integer(15));
		validExploderEntitysDamage.put("PandaNadeEntity", new Integer(7));
		validExploderEntitysDamage.put("SeitersonicExplosiveEntityPrimed", new Integer(15));
		
	
		//server or no?
		if (!par1World.isRemote)
        {
			
			//if exploder is valid, then do the roll, otherwise replace the block
			if(validExploderEntitysDamage.containsKey(exploder))
			{
				int newHP;
				Random rand = new Random();
				
				int exploderMaxDmg = ((exploderTier+1)*(Integer)validExploderEntitysDamage.get(exploder));
				int tierDiff = Math.abs((this.blockTier+1)-(exploderTier+1));	
				//Minimum damage is always the Tier + 1
				double dmg = (rand.nextInt(exploderMaxDmg)+(exploderTier+1));
				// the difference in tier grants a damage bonus
				
				//tier difference is always a min of 1 for multiplication purposes.
				if(tierDiff == 0){tierDiff=1;}
				
				dmg = dmg*tierDiff;
				
				Minecraft.getMinecraft().thePlayer.sendChatMessage("Damage Dealt: " + dmg);
				
				//find percentage of meta
				float percentAdj = (float)meta/(float)15;
				
				//find max HP
				int maxHP = ((this.blockTier+1)*baseShawcreteHP);

				//adjust HP for the current meta percentage
				int currentHP;
				currentHP = (int) Math.floor((maxHP*percentAdj));
				
				
				//Minecraft.getMinecraft().thePlayer.sendChatMessage("Max HP: " + maxHP);
				//Minecraft.getMinecraft().thePlayer.sendChatMessage("Current Block HP: " + currentHP);
				
				//deal damage to that HP
				newHP = (int) (currentHP-dmg);
				
				Minecraft.getMinecraft().thePlayer.sendChatMessage("HP After Dmg: " + newHP);
				
				//turn it back into metadata value, rounded down
				int newMeta;
				
				float metaAdj = (float)newHP/(float)maxHP;
				
				//Minecraft.getMinecraft().thePlayer.sendChatMessage("metaAdj: " + metaAdj);
				
				float x = (metaAdj*15);
				
				if (x - Math.floor(x) >=0.5) { 
					newMeta = (int) Math.ceil(x); 
					//Minecraft.getMinecraft().thePlayer.sendChatMessage("Your attack appears ineffective against this tier of Shawcrete.");
					}else{ 
						newMeta = (int) Math.floor(x); 
					} 
				
				//newMeta = (int)(metaAdj*15);
				
				//Minecraft.getMinecraft().thePlayer.sendChatMessage("New Metadata for block is " + newMeta);
				
				if(newMeta > 0)
				{
				
				par1World.setBlock(blockX, blockY, blockZ, this);
				par1World.setBlockMetadataWithNotify(blockX, blockY, blockZ, newMeta, 2);
				
				//Minecraft.getMinecraft().thePlayer.sendChatMessage("Block is still alive with Meta of: " + par1World.getBlockMetadata(blockX, blockY, blockZ));
				
				}
				else if(newMeta <=0 && newMeta >= (-15)) //if block receives so much damage that its HP is 0 or less, but greater than its negative  full HP turn it into cobble
				{
					//Minecraft.getMinecraft().thePlayer.sendChatMessage("Shawcrete degrades to cobblestone.");
					
					par1World.setBlock(blockX, blockY, blockZ, Blocks.cobblestone);
					
				}else{
					/*if block receives so much damage that its  at negative max HP, do nothing. The block was utterly destroyed*/
					//Minecraft.getMinecraft().thePlayer.sendChatMessage("Block ahniliated.");
					par1World.setBlockToAir(blockX, blockY, blockZ);
					}		
			}else
			{
				
				//Minecraft.getMinecraft().thePlayer.sendChatMessage("ENTITY UNKNOWN, DAMAG DENIED " + exploder);
				//Not a valid explosion entity, replace the shawcrete block.
				par1World.setBlock(blockX, blockY, blockZ, this);
			}
        }
    }
}
