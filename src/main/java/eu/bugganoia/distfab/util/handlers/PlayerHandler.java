package eu.bugganoia.distfab.util.handlers;

import eu.bugganoia.distfab.DistFab;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;



@EventBusSubscriber
public class PlayerHandler 
{
	@SubscribeEvent
    public void onPlayerInteract( PlayerInteractEvent.RightClickBlock event )
    {
    	if( 	event.getHand() == EnumHand.MAIN_HAND
    	/*	&&	event.getAction() == Action.RIGHT_CLICK_BLOCK*/  )
    	{
    		EntityPlayer playerIn = event.getEntityPlayer();
    		
    		
    		playerIn.sendMessage( new TextComponentString( 
				String.format( "EVENT: Pos=(%s), Side=%s",
							event.getPos().toString(),
							event.getSide().toString()
				) ) );
    		/*
    		playerIn.sendMessage( new TextComponentString( 
				String.format( "EVENT_ITEM: %s/%s / \"%s\"",
							event.getItemStack(),
							event.getItemStack().getDisplayName(),
							event.getItemStack().getItem().getRegistryName()
				) ) );
    		*/
    		ItemStack heldItem = playerIn.getHeldItem( event.getHand() );
    		ResourceLocation heldItemName = heldItem.getItem().getRegistryName();
    		/*
    		playerIn.sendMessage( new TextComponentString( 
				String.format( "HELD_ITEM: %s/%s / \"%s\"",
							playerIn.getHeldItem( event.getHand() ), 
							playerIn.getHeldItem( event.getHand() ).getDisplayName(), 
							playerIn.getHeldItem( event.getHand() ).getItem().getRegistryName()
				) ) );
    		
    		playerIn.sendMessage( new TextComponentString( 
   				String.format( "BIOME: %s/%s / \"%s\"",
						event.getWorld().getBiome( event.getPos() ).getBiomeName(), 
						event.getWorld().getBiome( event.getPos() ).getBiomeClass(), 
						event.getWorld().getBiome( event.getPos() ).getRegistryName() 
				) ) );
    		*/
    		
    		if( event.isCancelable() && heldItemName.toString().startsWith( "extra" ) )
    		{
    			if( event.getSide() == Side.CLIENT )
    			{
	    			String typeStr = "";
	    			NBTBase typeTag = heldItem.getTagCompound().getTag( "Type" );
	    			if( typeTag instanceof NBTTagString )
	    			{
	    				typeStr = ( (NBTTagString) typeTag ).getString();
	    			}
	    			if( typeStr.equalsIgnoreCase( "extrautils2:generator_tnt" ) )
	    			{
	    	    		playerIn.sendMessage( new TextComponentString( "NO BOOMERATOR HERE!" ) ); 
	    			}
	    			
	    			String msg = String.format( "Sorry no placement from %s in %s",
									event.getItemStack().getDisplayName(),
	    							event.getWorld().getBiome( event.getPos() ).getBiomeName() );
	        		
	    			playerIn.sendMessage( new TextComponentString( msg ) );
	        		DistFab.getLogger().info( "onPlayerInteract(): " + msg );
    			}
    			
    			event.setCanceled( true );
    		}
    	}
    }
    
    /*
    @EventHandler
    public void onItemRightClick( World worldIn, EntityPlayer playerIn, EnumHand handIn )
    {
    } */

}
