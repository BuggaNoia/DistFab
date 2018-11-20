package eu.bugganoia.distfab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import eu.bugganoia.distfab.proxy.CommonProxy;
import eu.bugganoia.distfab.reference.Reference;
import eu.bugganoia.distfab.util.DfLog;
import eu.bugganoia.distfab.util.handlers.PlayerHandler;
import eu.bugganoia.distfab.util.handlers.RegistryHandler;



@Mod( modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION )
public class DistFab
{
	// --- Singleton
    @Instance( Reference.MOD_ID )
    private static DistFab _instance;
    
    public static DistFab getInstance()
    {
    	return _instance;
    }

    
    // --- Helper
    protected static DfLog _logger;
    public static DfLog getLogger() { return _logger; }

    
    // --- Proxy
    @SidedProxy( clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS )
    protected static CommonProxy _proxy;
    
	// -- Delegate: Register
	public static void registerModel( Item item, int metadata, String id ) 
	{
	    _proxy.registerModel( item, metadata, id );
	}
    

    
    // --- CreativeTab
    // public static final CreativeTabs FABRICATORS = new DistFabTab();

    
    
    // --- Init
    @EventHandler
    public void preInit( FMLPreInitializationEvent event )
    {
    	// --- Log
    	_logger = new DfLog( event.getModLog() );
    	_logger.info( Reference.MOD_NAME + " v" + Reference.VERSION + ": preInit()" );
    	LogSiding();
    	
    	MinecraftForge.EVENT_BUS.register( new PlayerHandler() );
    	
    	// --- Delegate
    	// _proxy.preInit( event );
    	RegistryHandler.preInit( event );
    }

    @EventHandler
    public void init( FMLInitializationEvent event )
    {
    	// --- Log
    	_logger.info( /*Reference.MOD_NAME + " v" + Reference.VERSION + ": " +*/ "init()" );
    	LogSiding();
    	
    	
    	// --- Delegate
    	// _proxy.init( event );
    	RegistryHandler.init( event );

    	
    	// some example code
    	_logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
    
    @EventHandler
    public void postInit( FMLPostInitializationEvent event )
    {
    	_logger.info( /*Reference.MOD_NAME + " v" + Reference.VERSION + ": " +*/ "postInit()" );
    	LogSiding();
    
    	// --- Delegate
    	// _proxy.postInit( event );
    	RegistryHandler.postInit( event );
   }
    

    // --- Logging-Helper
    protected void LogSiding()
    {
    	// Side Test
    	_logger.info( "_proxy.isRemote=" 
    			+ ( _proxy == null 
    				? "(null)"
    				: ( _proxy.IsRemote() ? "true" : "false" ) ) ); 
    	
    	if( _proxy != null )
    	{
    		Boolean isWorldRemote = _proxy.IsWorldRemote();
	    	_logger.info( "world.isRemote=" 
	    			+ (  isWorldRemote == null  
						? "(null)"
						: ( isWorldRemote  ? "true" : "false" ) ) );
    	}
    }
}
