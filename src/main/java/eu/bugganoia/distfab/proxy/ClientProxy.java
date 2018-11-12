package eu.bugganoia.distfab.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;


public class ClientProxy extends CommonProxy 
{
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
