package eu.bugganoia.distfab.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;


public class ClientProxy extends CommonProxy 
{
	// --- Register
	@Override
	public void registerModel( Item item, int metadata, String id ) 
	{
		ModelLoader.setCustomModelResourceLocation(item, metadata,  
				new ModelResourceLocation( item.getRegistryName(), id ) );
	}
	

	
	// --- Detection-Helper
	@Override
	public boolean IsRemote() { return false; }
	
	@Override
	public Boolean IsWorldRemote()
	{
		World world = Minecraft.getMinecraft().world;
    	
		return ( world == null  
					? null
					: ( world.isRemote  ? true : false ) );
	}
}
