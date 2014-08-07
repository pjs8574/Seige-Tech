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

public class BasicShawcreteBlock extends Block {

	
	private int blockHP;
	
	@SideOnly(Side.CLIENT)
	private IIcon normalHP;
	@SideOnly(Side.CLIENT)
	private IIcon damagedHP;
	
	boolean blockExploded;
	
	public BasicShawcreteBlock() {
		super(Material.rock);
		this.setHardness(2);
		this.setResistance(10);
		this.setCreativeTab(SiegeTech.tabMyMod);
		this.blockHP = 15;
	}
	@Override
	 public void onBlockAdded(World world, int x, int y, int z)
	    {
	        	world.setBlockMetadataWithNotify(x, y, z, blockHP, 2);
	        	Minecraft.getMinecraft().thePlayer.sendChatMessage("New block is places with HP of " + world.getBlockMetadata(x, y, z));
	    }

	//sets the block texture stored in \src\main\resources\assets\shawric_seigetech\textures\blocks
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister icReg)
	{
		
		
	normalHP = icReg.registerIcon(SiegeTech.modid + ":" + this.getUnlocalizedName().substring(5));
	damagedHP = icReg.registerIcon(SiegeTech.modid + ":" + this.getUnlocalizedName().substring(5) + "damaged");
	
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
        	
        	//send info my checking method to see if the block gets destroyed or not
        	this.shawcreteExploded(par1World, explodeCause, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4));
        
        }
        
        
    
    }
	
	
	/*cleans up the code by making a list of things that break the shawcrete and just checking them. 
	* pass in the world object, string of the  exploding entity, coordinates of the destroyed block X Y Z
	*/
	
	public void shawcreteExploded(World par1World, String exploder,int blockX, int blockY, int blockZ, int HP)
    {

		//list of valid exploders and the chance of them breaking the Basic shawcrete
		Hashtable validExploderEntitys = new Hashtable();
		validExploderEntitys.put("EntityTNTPrimed", new Integer(10));
		validExploderEntitys.put("BasicPandaNadeEntity", new Integer(5));
		validExploderEntitys.put("EntityCreeper", new Integer(20));
		validExploderEntitys.put("AdvancedPandaNadeEntity", new Integer(20));
		validExploderEntitys.put("BasicSeitersonicExplosiveEntityPrimed", new Integer(50));
		
		//server or no?
		if (!par1World.isRemote)
        {
			
			//if exploder is valid, then do the roll, otherwise replace the block
			if(validExploderEntitys.containsKey(exploder))
			{
				
				int newHP;
				Random rand = new Random(); 
				int i = rand.nextInt((Integer)validExploderEntitys.get(exploder));
				
				Minecraft.getMinecraft().thePlayer.sendChatMessage("i roll was: " + i);
				
				
				newHP = (HP-i);
				
				Minecraft.getMinecraft().thePlayer.sendChatMessage("New Hp for block is " + newHP);
				
				if(newHP > 0)
				{
				
				par1World.setBlock(blockX, blockY, blockZ, this);
				par1World.setBlockMetadataWithNotify(blockX, blockY, blockZ, newHP, 2);
				
				Minecraft.getMinecraft().thePlayer.sendChatMessage("newly place block health is " + par1World.getBlockMetadata(blockX, blockY, blockZ));
				}
				else if(newHP <=0)
				{
					//the roll fails, reset the block to shawcrete
					
					Minecraft.getMinecraft().thePlayer.sendChatMessage("Block Health below zero, becomes cobble.");
					
					par1World.setBlock(blockX, blockY, blockZ, Blocks.cobblestone);
					
					//this was to fix a potential rendering thing with grenades, might not need it anymore
					//Minecraft.getMinecraft().renderGlobal.markBlockForUpdate(blockX, blockY, blockZ);
				}		
			}else
			{
				//Not a valid explosion entity, replace the shawcrete block.
				par1World.setBlock(blockX, blockY, blockZ, this);
			}
        }
    }
}
