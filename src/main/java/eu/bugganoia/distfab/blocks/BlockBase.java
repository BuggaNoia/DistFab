package eu.bugganoia.distfab.blocks;

import java.util.Random;

import eu.bugganoia.distfab.DistFab;
import eu.bugganoia.distfab.init.BlockInit;
import eu.bugganoia.distfab.init.ItemInit;
import eu.bugganoia.distfab.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


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
	
	
	// --- Item
	@Override
	public Item getItemDropped( IBlockState state, Random rand, int fortune ) 
	{
		return Item.getItemFromBlock( this );
	}
	
	@Override
	public ItemStack getItem( World worldIn, BlockPos pos, IBlockState state )
	{
		return new ItemStack( this );
	}
}
