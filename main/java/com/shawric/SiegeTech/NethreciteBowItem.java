package com.shawric.SiegeTech;

import java.util.List;

import javax.swing.Icon;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.command.ICommandSender;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

public class NethreciteBowItem extends ItemBow{
	
	private int itemTier;
	public static final String[] bowPullIconNameArray = new String[] {"pulling_0", "pulling_1", "pulling_2"};
    @SideOnly(Side.CLIENT)
    private IIcon[] iconArray;
	private int arrowTypeToUse=1;
	
	
	
	public NethreciteBowItem(String name, int tier) {
		this.setUnlocalizedName(name); //Sets the name of this item, Has to be unique!
		this.setCreativeTab(SiegeTech.tabMyMod);; //This Item will be in the Combat Creative Tab!
		this.setTextureName(SiegeTech.modid + ":" + this.getUnlocalizedName().substring(5)); //The texture for this item is the Grenade!

		this.itemTier = tier;
		
	}

	@Override
	 public ItemStack onItemRightClick(ItemStack itmStck, World wld1, EntityPlayer entyPlyr1)
	    {

			ArrowNockEvent event = new ArrowNockEvent(entyPlyr1, itmStck);
	        MinecraftForge.EVENT_BUS.post(event);
	        if (event.isCanceled())
	        {
	            return event.result;
	        }

	        if (entyPlyr1.capabilities.isCreativeMode)
	        {
	        	entyPlyr1.setItemInUse(itmStck, this.getMaxItemUseDuration(itmStck));
	        	
	        }else 
	        	{
	        	
	        		if (entyPlyr1.inventory.hasItem(SiegeTech.nethreciteArrow) && !entyPlyr1.inventory.hasItem(Items.arrow)) 
	        			{
	        			
	        			//if(wld1.isRemote)
    			    	//{
	        				
	        				if(this.arrowTypeToUse == 2){
	        							
	        					IChatComponent msg = new ChatComponentText("No Normal Arrows detected, Setting Arrow Mode to Nethrecite Arrow.");
	        					entyPlyr1.addChatMessage(msg);
	        					
	        				this.arrowTypeToUse = 1;
	        				}
    			    	//}
    					
    					entyPlyr1.setItemInUse(itmStck, this.getMaxItemUseDuration(itmStck));
	        			
	        			
	        			}else 
	        			{
	        				if(entyPlyr1.inventory.hasItem(Items.arrow) && !entyPlyr1.inventory.hasItem(SiegeTech.nethreciteArrow))
	        				{	
	        					
	        					//if(wld1.isRemote)
	        			    	//{
	        						
	        						if(this.arrowTypeToUse == 1){
	        							
	        							IChatComponent msg = new ChatComponentText("No Nethrecite Arrows detected, Setting Arrow Mode to Normal Arrow.");
	    	        					entyPlyr1.addChatMessage(msg);
	        							
	        							this.arrowTypeToUse = 2;
	        						}
	        			    	//}
	        					
	        					entyPlyr1.setItemInUse(itmStck, this.getMaxItemUseDuration(itmStck));
	        					
	        					
	        				}else{
	        					if(entyPlyr1.inventory.hasItem(Items.arrow) && entyPlyr1.inventory.hasItem(SiegeTech.nethreciteArrow))
	        						{
	        						entyPlyr1.setItemInUse(itmStck, this.getMaxItemUseDuration(itmStck));}
	        						}
	        				
	        		}
	        }

	        return itmStck;
	    }
	
	
	@Override
	public void onPlayerStoppedUsing(ItemStack itmStck, World wrld1, EntityPlayer entPlyr, int p_77615_4_)
    {
        int j = this.getMaxItemUseDuration(itmStck) - p_77615_4_;

        ArrowLooseEvent event = new ArrowLooseEvent(entPlyr, itmStck, j);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled())
        {
            return;
        }
        j = event.charge;

        boolean flag = entPlyr.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, itmStck) > 0;

        if (flag || entPlyr.inventory.hasItem(SiegeTech.nethreciteArrow) || entPlyr.inventory.hasItem(Items.arrow))
        {
            float f = (float)j / 20.0F;
            f = (f * f + f * 2.0F) / 3.0F;

            if ((double)f < 0.1D)
            {
                return;
            }

            if (f > 1.0F)
            {
                f = 1.0F;
            }
            
            //determine which Arrow type to use
            if(arrowTypeToUse==1)
            {
            	
            	NethreciteArrowEntity entityarrow = new NethreciteArrowEntity(wrld1, entPlyr, f * 2.0F).setShooter(entPlyr);
            
            	//determine arrow damage
            	if (f == 1.0F)
            	{
            		entityarrow.setIsCritical(true);
            	}

            	int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, itmStck);

            	if (k > 0)
            	{
            		entityarrow.setDamage(entityarrow.getDamage() + (double)k * 0.5D + 0.5D);
            	}

            	int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, itmStck);

            	if (l > 0)
            	{
            		entityarrow.setKnockbackStrength(l);
            	}

            	if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, itmStck) > 0)
            	{
            		entityarrow.setFire(100);
            	}

            	itmStck.damageItem(1, entPlyr);
            	wrld1.playSoundAtEntity(entPlyr, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

            	if (flag)
            	{
            		entityarrow.canBePickedUp = 2;
            	}
            	else
            	{
            		if(arrowTypeToUse==1)
            		{
            	
            		entPlyr.inventory.consumeInventoryItem(SiegeTech.nethreciteArrow);
            		
            		}else if(arrowTypeToUse==2)
            		{
            	
            		entPlyr.inventory.consumeInventoryItem(Items.arrow);
            		}
            	
            	}

            	if (!wrld1.isRemote)
            	{
            		wrld1.spawnEntityInWorld(entityarrow);
            	}
            	
            	
            	
            	
            	
            	
            }else{
            	if(arrowTypeToUse==2)
            	{
            	
            	EntityArrow entityarrow = new EntityArrow(wrld1, entPlyr, f * 2.0F);
            	
            	
            	//determine arrow damage
            	if (f == 1.0F)
            	{
            		entityarrow.setIsCritical(true);
            	}

            	int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, itmStck);

            	if (k > 0)
            	{
            		entityarrow.setDamage(entityarrow.getDamage() + (double)k * 0.5D + 0.5D);
            	}

            	int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, itmStck);

            	if (l > 0)
            	{
            		entityarrow.setKnockbackStrength(l);
            	}

            	if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, itmStck) > 0)
            	{
            		entityarrow.setFire(100);
            	}

            	itmStck.damageItem(1, entPlyr);
            	wrld1.playSoundAtEntity(entPlyr, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

            	if (flag)
            	{
            		entityarrow.canBePickedUp = 2;
            	}
            	else
            	{
            	//Determine which arrow type is consumed	
            		if(arrowTypeToUse==1)
            		{
            	
            		entPlyr.inventory.consumeInventoryItem(SiegeTech.nethreciteArrow);
            		
            		}else if(arrowTypeToUse==2)
            		{
            	
            		entPlyr.inventory.consumeInventoryItem(Items.arrow);
            		}
            	}

            	if(!wrld1.isRemote)
            	{
            		wrld1.spawnEntityInWorld(entityarrow);
            	}
            	}
            }
        }	
            	
            	
            
            
            
    
        }
    

	
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister p_94581_1_)
    {
        this.itemIcon = p_94581_1_.registerIcon(this.getIconString() + "_standby");
        this.iconArray = new IIcon[bowPullIconNameArray.length];

        for (int i = 0; i < this.iconArray.length; ++i)
        {
            
        	this.iconArray[i] = p_94581_1_.registerIcon(this.getIconString() + "_" + bowPullIconNameArray[i]);
        }
    }

    /**
     * used to cycle through icons based on their used duration, i.e. for the bow
     */
    @SideOnly(Side.CLIENT)
    public IIcon getItemIconForUseDuration(int p_94599_1_)
    {
    	//Minecraft.getMinecraft().thePlayer.sendChatMessage("sending icon array" + this.iconArray[p_94599_1_]);
    	return this.iconArray[p_94599_1_];
    }
	
	
    @Override
    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining)
    {
    		 int j = getMaxItemUseDuration(stack) - useRemaining;
    		 if (j >= 18)
    		 {
    				 return getItemIconForUseDuration(2);
    		 }

    		 if (j > 13)
    		 {
    				 return getItemIconForUseDuration(1);
    		 }

    		 return getItemIconForUseDuration(0);
    }
	
	
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
    par3List.add("Tier: " + this.itemTier + " Bow with Flame 1. Shift+Leftclick to swtich mode." );
    }
    
    public void onUpdate(ItemStack itemstack, World par2World, Entity par3Entity, int par4, boolean par5)
    {
    	if(itemstack.isItemEnchanted() == false)
    	{
    		itemstack.addEnchantment(Enchantment.flame, 1);
    	}
    	
    	
    	
    }
    
    
    
    
    @Override
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
    {

    	World wld =entityLiving.worldObj;
    	
    	if(wld.isRemote)
    	{
    		
    		if (Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
    		{
    			switch(arrowTypeToUse)
                {
                case 1:	
    				
                	IChatComponent msg = new ChatComponentText("Setting Arrow Mode to Normal Arrow.");
    				((EntityPlayerSP) entityLiving).addChatMessage(msg);
                	
                	//Minecraft.getMinecraft().thePlayer.sendChatMessage("Setting Arrow Mode to Normal Arrow.");
    				
    				
    				this.arrowTypeToUse = 2;
    				break;
    			case 2:		
    				IChatComponent msg1 = new ChatComponentText("Setting Arrow Mode to Nethrecite Arrow.");
    				((EntityPlayerSP) entityLiving).addChatMessage(msg1);
    				
    			//Minecraft.getMinecraft().thePlayer.sendChatMessage("Setting Arrow Mode to Nethrecite Arrow.");
    				this.arrowTypeToUse = 1;
    				break;
                }
    			return true;
    		}else{return false;}
    	}else{return false;}
	
    }
    
   
	
}
