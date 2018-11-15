package eu.bugganoia.distfab.blocks;

import eu.bugganoia.distfab.DistFab;
import eu.bugganoia.distfab.init.BlockInit;
import eu.bugganoia.distfab.init.ItemInit;
import eu.bugganoia.distfab.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;


public class BlockBase extends Block implements IHasModel
{
	// --- Constructor
	public BlockBase( String name, Material material, CreativeTabs tab ) 
	{
		super( material );
		
		setUnlocalizedName( name );
		setRegistryName( name );
		setCreativeTab( tab );
		
		BlockInit.BLOCKS.add( this );
		ItemInit.ITEMS.add( new ItemBlock( this ).setRegistryName(this.getRegistryName() ) );
	}

	
	// --- Register
	@Override
	public void registerModel() 
	{
		DistFab.registerModel( Item.getItemFromBlock( this ), 0, "inventory" );
	}
}
