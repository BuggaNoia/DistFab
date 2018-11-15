package eu.bugganoia.distfab.util.handlers;

import eu.bugganoia.distfab.init.BlockInit;
import eu.bugganoia.distfab.init.ItemInit;
import eu.bugganoia.distfab.util.interfaces.IHasModel;
import net.minecraft.block.Block;

// import com.jcraft.jorbis.Block;

// import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


@EventBusSubscriber
public class RegistryHandler 
{
	@SubscribeEvent
	public static void onItemRegister( RegistryEvent.Register<Item> event )
	{
		event.getRegistry().registerAll( ItemInit.ITEMS.toArray( new Item[0] ) );
	}
	
	@SubscribeEvent
	public static void onBlockRegister( RegistryEvent.Register<Block> event )
	{
		event.getRegistry().registerAll( BlockInit.BLOCKS.toArray( new Block[0] ) );
		
		/*
		TileEntityHandler.registerTileEntities();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCopperChest.class, new RenderCopperChest());
		*/
	}
	
	/*
	@SubscribeEvent
	public static void onEnchantmentRegister( RegistryEvent.Register<Enchantment> event )
	{
		event.getRegistry().registerAll(EnchantmentInit.ENCHANTMENTS.toArray( new Enchantment[0] ) );
	}
	*/
	
	@SubscribeEvent
	public static void onModelRegister( ModelRegistryEvent event )
	{		
		// DistFab.registerModel( Item.getItemFromBlock( BlockInit.COPPER_CHEST ), 0, "inventory" );
		
		for( Item item : ItemInit.ITEMS )
		{
			if( item instanceof IHasModel )
			{
				( (IHasModel) item ).registerModel();
			}
		}
		
		for( Block block : BlockInit.BLOCKS )
		{
			if( block instanceof IHasModel )
			{
				( (IHasModel) block ).registerModel();
			}
		}
	}
	
	public static void preInit( FMLPreInitializationEvent event )
	{
		/*
		FluidInit.registerFluids();
		
		GameRegistry.registerWorldGenerator(new WorldGenCustomOres(), 0);
		GameRegistry.registerWorldGenerator(new WorldGenCustomStructures(), 0);
		GameRegistry.registerWorldGenerator(new WorldGenCustomTrees(), 0);
		
		BiomeInit.registerBiomes();
		DimensionInit.registerDimensions();
		EntityInit.registerEntities();
		RenderHandler.registerEntityRenders();
		ModConfiguration.registerConfig(event);
		*/
	}
	
	public static void init( FMLInitializationEvent event )
	{
		/*
		NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
		SoundsHandler.registerSounds();		
		*/
	}
	
	public static void postInit( FMLPostInitializationEvent event )
	{
		/*
		WorldType COPPER = new WorldTypeCopper();
		*/
	}
	
	public static void server( FMLServerStartingEvent event )
	{
		/*
		event.registerServerCommand(new CommandTeleportDimension());
		*/
	}
}