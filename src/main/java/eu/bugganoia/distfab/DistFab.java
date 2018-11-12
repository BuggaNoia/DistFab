package eu.bugganoia.distfab;

import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
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


@Mod( modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION )
public class DistFab
{
    @Instance( Reference.MOD_ID)
    private static DistFab _instance;
    
    @SidedProxy( clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS )
    public static CommonProxy _proxy;
    
    private static DfLog _logger;

    
    @EventHandler
    public void preInit( FMLPreInitializationEvent event )
    {
    	_logger = new DfLog( event.getModLog() );
    	_logger.info( Reference.MOD_NAME + " v" + Reference.VERSION + ": preInit()" );
    	
    	LogSiding();
    	
    	// _proxy.preInit( _registry );
    }

    @EventHandler
    public void init( FMLInitializationEvent event )
    {
    	_logger.info( /*Reference.MOD_NAME + " v" + Reference.VERSION + ": " +*/ "init()" );

    	LogSiding();
    	
    	// some example code
    	_logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
    
    @EventHandler
    public void postInit( FMLPostInitializationEvent event )
    {
    	_logger.info( /*Reference.MOD_NAME + " v" + Reference.VERSION + ": " +*/ "postInit()" );
    	
    	LogSiding();
    }

    protected void LogSiding()
    {

    	// Side Test
    	_logger.info( "_proxy.isRemote=" 
    			+ ( _proxy == null 
    				? "(null)"
    				: ( _proxy.IsRemote() ? "true" : "false" ) ) ); 
    	
    	if( _proxy != null && !_proxy.IsRemote() )
    	{
    		_logger.info( "Minecraft.getMinecraft() will crash ? " );
    		
    		// $TODO --> ClientProxy.java
	    	/*
    		World world = Minecraft.getMinecraft().world;
	    	_logger.info( "world.isRemote=" 
	    			+ ( world == null  
						? "(null)"
						: ( world.isRemote  ? "true" : "false" ) ) );
	    	*/
    	}
    }
}
