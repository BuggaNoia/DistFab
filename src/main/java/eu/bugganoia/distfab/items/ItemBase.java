package eu.bugganoia.distfab.items;

import eu.bugganoia.distfab.DistFab;
import eu.bugganoia.distfab.init.ItemInit;
import eu.bugganoia.distfab.util.interfaces.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;


public class ItemBase extends Item implements IHasModel
{
	// --- Constructor
	public ItemBase(String name, CreativeTabs tab) 
	{
		setUnlocalizedName( name );
		setRegistryName( name );
		setCreativeTab( tab );
		
		ItemInit.ITEMS.add( this );
	}

	
	// --- Register
	@Override
	public void registerModels() 
	{
		DistFab.registerModel( this, 0, "inventory" );
	}
}
