package eu.bugganoia.distfab.init;

import java.util.ArrayList;
import java.util.List;

import eu.bugganoia.distfab.blocks.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;


public class BlockInit 
{
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block FABRICATOR01 
		= new BlockBase( "fabricator01", Material.IRON, CreativeTabs.BUILDING_BLOCKS ); 
}
