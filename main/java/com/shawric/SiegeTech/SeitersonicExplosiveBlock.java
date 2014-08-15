package com.shawric.SiegeTech;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import com.shawric.SiegeTech.RenderSeitersonicExplosiveEntityPrimed;

public class SeitersonicExplosiveBlock extends Block{

	
	@SideOnly(Side.CLIENT)
	private IIcon topN;
	@SideOnly(Side.CLIENT)
	private IIcon bottom;
	@SideOnly(Side.CLIENT)
	private IIcon topS;
	@SideOnly(Side.CLIENT)
	private IIcon topE;
	@SideOnly(Side.CLIENT)
	private IIcon topW;
	@SideOnly(Side.CLIENT)
	private IIcon blockSide;
	
	private int blockTier;
	
	private int blockDirectionInt; // SIDE KEY : 0 bottom, 1 top , 2 North. 3 South, 4 West ,5 East
	
	
	public SeitersonicExplosiveBlock(String name, int tier) {
		super(Material.tnt);
		this.setHardness(2);
		this.setResistance(10);
		this.setCreativeTab(SiegeTech.tabMyMod);
		this.setBlockName(name);
		this.blockTier = tier;
	}	
	
	 @Override
	 public void onBlockAdded(World world, int i, int j, int k)
	    {
	        //super.onBlockAdded(world, i, j, k);
	        
	        //Minecraft.getMinecraft().thePlayer.sendChatMessage("added:");

	        // ALL THIS SHIT GETS THE DIRECTION THE PLAYER IS FACING WHEN PLACING THE BLOCK AND SAVES IT
	        int l = MathHelper.floor_double((double)((Minecraft.getMinecraft().thePlayer.rotationYaw * 4F) / 360F) + 0.5D) & 3;
	        if(l == 0)
	    	{
	        	world.setBlockMetadataWithNotify(i, j, k, 3, 2);
	        	this.setblockDirectionInt(3); //SOUTH
	        	//Minecraft.getMinecraft().thePlayer.sendChatMessage("BD is South: " + blockDirectionInt);
	    	}
	    	if(l == 1)
	    	{
	        	world.setBlockMetadataWithNotify(i, j, k, 4, 2);
	        	this.setblockDirectionInt(4);//WEST
	        	//Minecraft.getMinecraft().thePlayer.sendChatMessage("BD is West: " + blockDirectionInt);
	    	}
	    	if(l == 2)
	    	{
	        	world.setBlockMetadataWithNotify(i, j, k, 2, 2);
	        	this.setblockDirectionInt(2);//NORTH
	        	//Minecraft.getMinecraft().thePlayer.sendChatMessage("BD is North: " + blockDirectionInt);
	    	}
	    	if(l == 3)
	    	{
	        	world.setBlockMetadataWithNotify(i, j, k, 5, 2);
	        	this.setblockDirectionInt(5);//EAST
	        	//Minecraft.getMinecraft().thePlayer.sendChatMessage("BD is East: " + blockDirectionInt);
	    	}
	    }
	/*
	 * for Set setBlockMetadataWithNotify  -- its the 3 coordinates of the block, the MNetadata number adn the last number is a Flag.
	 * Flag 1 will cause a block update. Flag 2 will send the change to clients (you almost always want this). Flag 4 prevents the
    * block from being re-rendered, if this is a client world. Flags can be added together.
    */
	
	private void setblockDirectionInt(int i)
	{
		this.blockDirectionInt = i;
	
	}
	
	private int getblockDirectionInt()
	{
		return this.blockDirectionInt;
	
	}
	
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		//Minecraft.getMinecraft().thePlayer.sendChatMessage("Side: " + side + "  Meta: " + meta);
		
		// Keep so we know how to do multi sided blocks
		// SIDE KEY : 0 bottom, 1 top , 2 North. 3 South, 4 West ,5 East
		// I stored the direction that the block faces in the meta data. this corresponds to the side key
		// so metadata 2 is indicates the direction the block should face is North etc
		
		switch(side)
		{		
			case 0:
				return bottom;
			case 1:
				if(meta == 2){return topN;}
				if(meta == 3){return topS;}
				if(meta == 4){return topW;}
				if(meta == 5){return topE;}
				if(meta != 2 && meta != 3 && meta != 4 && meta != 5){return bottom;}
			case 2:
				return blockSide;
			case 3:
				return blockSide;
			case 4:
				return blockSide;
			case 5:
				return blockSide;
			default: return bottom;
		}

	}
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister Ireg)
	{
	this.blockSide = Ireg.registerIcon(SiegeTech.modid + ":" + this.getUnlocalizedName().substring(5) + "Side");
	this.topN = Ireg.registerIcon(SiegeTech.modid + ":" + this.getUnlocalizedName().substring(5) + "TopN");
	this.topS = Ireg.registerIcon(SiegeTech.modid + ":" + this.getUnlocalizedName().substring(5) + "TopS");
	this.topE = Ireg.registerIcon(SiegeTech.modid + ":" + this.getUnlocalizedName().substring(5) + "TopE");
	this.topW = Ireg.registerIcon(SiegeTech.modid + ":" + this.getUnlocalizedName().substring(5) + "TopW");
	this.bottom = Ireg.registerIcon(SiegeTech.modid + ":" + this.getUnlocalizedName().substring(5) + "Bottom");
	
	/* old side texture code, for unique sides for later if we want
	this.sideN = Ireg.registerIcon(SiegeTech.modid + ":" + this.getUnlocalizedName().substring(5) + "sideN");
	this.sideS = Ireg.registerIcon(SiegeTech.modid + ":" + this.getUnlocalizedName().substring(5) + "sideS");
	this.sideW = Ireg.registerIcon(SiegeTech.modid + ":" + this.getUnlocalizedName().substring(5) + "sideW");
	this.sideE = Ireg.registerIcon(SiegeTech.modid + ":" + this.getUnlocalizedName().substring(5) + "sideE");
	*/
	
	}
	
	
	public boolean canDropFromExplosion(Explosion expl)
    {
        return false;
    }
	
	
	public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_)
    {
        if (p_149695_1_.isBlockIndirectlyGettingPowered(p_149695_2_, p_149695_3_, p_149695_4_))
        {
            this.onBlockDestroyedByPlayer(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_, 1);
            p_149695_1_.setBlockToAir(p_149695_2_, p_149695_3_, p_149695_4_);
        }
    }
	
	
	
	public boolean onBlockActivated(World world1, int x, int y, int z, EntityPlayer playerEnt, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
        if (playerEnt.getCurrentEquippedItem() != null && playerEnt.getCurrentEquippedItem().getItem() == Items.flint_and_steel)
        {
            this.blockActivatedBySomthing(world1, x, y, z, 1, playerEnt);
            world1.setBlockToAir(x, y, z);
            playerEnt.getCurrentEquippedItem().damageItem(1, playerEnt);
            return true;
        }
        else
        {
            return super.onBlockActivated(world1, x, y, z, playerEnt, p_149727_6_, p_149727_7_, p_149727_8_, p_149727_9_);
        }
    }
	
	
	//Called right before the block is destroyed by a player.  Args: world, x, y, z, metaData
	public void onBlockDestroyedByPlayer(World world1, int x, int y, int z, int meta)
    {
		this.blockActivatedBySomthing(world1, x, y, z, meta, (EntityLivingBase)null);
    }

    public void blockActivatedBySomthing(World world1, int x, int y, int z, int meta, EntityLivingBase p_150114_6_)
    {
        if (!world1.isRemote)
        {
            	
            	if (meta == 1)
                {
            		//Minecraft.getMinecraft().thePlayer.sendChatMessage("EXPLOSIVE ACTIVATED META IS: " + world1.getBlockMetadata(x, y, z));
            		
            	SeitersonicExplosiveEntityPrimed entitytntprimed = new SeitersonicExplosiveEntityPrimed(world1, (double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), p_150114_6_, world1.getBlockMetadata(x, y, z), this.blockTier);
            	
            	//System.out.println("SETTING TIER TO ENTITY: "+this.blockTier);
            	entitytntprimed.setTier(this.blockTier);
            	
            	world1.spawnEntityInWorld(entitytntprimed);
                world1.playSoundAtEntity(entitytntprimed, "game.tnt.primed", 1.0F, 1.0F);
                }
        }
        
    }
	
    public void onBlockExploded(World world1, int x, int y, int z, Explosion Expl) 
    {	
    	if (!world1.isRemote)
        {
    			
    		//System.out.println("MAKING ENTITY PASSING TIER: "+this.blockTier);
    		//Minecraft.getMinecraft().thePlayer.sendChatMessage("Activated by explosion! Metadata is: " + world1.getBlockMetadata(x, y, z));
            	
    	
    		
    			SeitersonicExplosiveEntityPrimed entitytntprimed = new SeitersonicExplosiveEntityPrimed(world1, (double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), Expl.getExplosivePlacedBy(), world1.getBlockMetadata(x, y, z) , this.blockTier);
                
    			entitytntprimed.setTier(this.blockTier);
    			
    			world1.spawnEntityInWorld(entitytntprimed);
                world1.playSoundAtEntity(entitytntprimed, "game.tnt.primed", 1.0F, 1.0F);
                world1.setBlockToAir(x, y, z);
         
        }
    	
    }
	
	
@Override
public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {    
		return Item.getItemFromBlock(this);
    }	
	

public int getBlockTier()
{
	
	return this.blockTier;
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
