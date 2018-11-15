package eu.bugganoia.distfab.init;

import java.util.ArrayList;
import java.util.List;

import eu.bugganoia.distfab.blocks.BlockBase;
import eu.bugganoia.distfab.blocks.BlockFabricator;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;


public class BlockInit 
{
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	// Fabricators
	public static final Block FABRICATOR01 
		= new BlockFabricator( 1, "fabricator01" ); 
}
