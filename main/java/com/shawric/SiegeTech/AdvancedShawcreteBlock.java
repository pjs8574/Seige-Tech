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
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdvancedShawcreteBlock extends Block {

	boolean blockExploded;
	
	public AdvancedShawcreteBlock() {
		super(Material.rock);
		this.setHardness(2);
		this.setResistance(10);
		this.setCreativeTab(SiegeTech.tabMyMod);

	}
	

	//sets the block texture stored in \src\main\resources\assets\shawric_seigetech\textures\blocks
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister ireg)
	{
	blockIcon = ireg.registerIcon(SiegeTech.modid + ":" + this.getUnlocalizedName().substring(5));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta)
	{
	return blockIcon;
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
	public void onBlockDestroyedByExplosion(World par1World, int par2, int par3, int par4, Explosion par5Explosion)
    {
        if (!par1World.isRemote)
        {
        	//turn the exploding entityclass into a string for checking
        	//THIS HAD SOME TICKING ENTITY ISSUE WITH GHAST FIREBALLS
        	//fixed it by making the block more resistant so teh ghast ball diesnt kill it 
        	//Might come back to haunt me, dunno howto fix. Try catch block maybe?
        	String explodeCause = par5Explosion.exploder.getClass().getSimpleName();

        	//send info my checking method to see if the block gets destroyed or not
        	this.shawcreteExploded(par1World, explodeCause, par2, par3, par4);
        
        }
        
        
    
    }
	
	
	/*cleans up the code by making a list of things that break the shawcrete and just checking them. 
	* pass in the world object, string of the  exploding entity, coordinates of the destroyed block X Y Z
	*/
	
	public void shawcreteExploded(World par1World, String exploder,int blockX, int blockY, int blockZ)
    {

		//list of valid exploders and the chance of them breaking the Advanced shawcrete
		Hashtable validExploderEntitys = new Hashtable();
		validExploderEntitys.put("EntityTNTPrimed", new Integer(0));
		validExploderEntitys.put("BasicPandaNadeEntity", new Integer(0));
		validExploderEntitys.put("EntityCreeper", new Integer(5));
		validExploderEntitys.put("AdvancedPandaNadeEntity", new Integer(5));
		validExploderEntitys.put("BasicSeitersonicExplosive", new Integer(10));
		
		
		//server or no?
		if (!par1World.isRemote)
        {
			
			//if exploder is valid, then do the roll, otherwise replace the block
			if(validExploderEntitys.containsKey(exploder))
			{
		
				//i had this variable for some reason i forget why
				this.blockExploded = true;
		
				Random rand = new Random(); 
				int i = rand.nextInt(100); 
				int threshold = (Integer)validExploderEntitys.get(exploder);
				
				//See if the roll beats the shawcrete, if so, then the shawcrete degrades to cobble stone
				if(i < threshold)
				{
					par1World.setBlock(blockX, blockY, blockZ, Blocks.cobblestone);
					Minecraft.getMinecraft().thePlayer.sendChatMessage("Adv Shawcrete Damaged By: " + exploder + " Roll was: " + i + " had to beat " + threshold);
				}
				else
				{
					//the roll fails, reset the block to shawcrete
					par1World.setBlock(blockX, blockY, blockZ, this);
					this.blockExploded = false;
					
					Minecraft.getMinecraft().thePlayer.sendChatMessage("Adv Shawcrete NOT Dmged By: " + exploder + " Roll was: " + i + " had to beat " + threshold);
					
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
