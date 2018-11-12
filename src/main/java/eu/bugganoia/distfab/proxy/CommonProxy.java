package eu.bugganoia.distfab.proxy;

import net.minecraft.item.Item;


public class CommonProxy 
{
	// --- Register
	public void registerModel( Item item, int metadata, String id ) 
	{
	}
	
	
	// --- Detection-Helper
	public boolean IsRemote() { return true; }
	
	public Boolean IsWorldRemote()
	{
		return new Boolean( IsRemote() );
	}
}
