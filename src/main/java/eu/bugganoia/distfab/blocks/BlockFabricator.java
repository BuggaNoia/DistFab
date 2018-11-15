package eu.bugganoia.distfab.blocks;

import eu.bugganoia.distfab.blocks.tileentities.TileEntityFabricator;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class BlockFabricator extends BlockBase
{
	// TODO: --> BlockFaced.FACING
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	
	public static final PropertyBool BURNING = PropertyBool.create( "burning" );
	
	protected int _number;

	
	public BlockFabricator( int number, String name ) 
	{
		super( name, Material.IRON, CreativeTabs.BUILDING_BLOCKS  );
		_number = number;

		setSoundType( SoundType.METAL );

		setDefaultState( 
			blockState.getBaseState()
					.withProperty( FACING, EnumFacing.NORTH )
					.withProperty( BURNING, false )
		/*, 0*/ );
		
		setHardness( 5.0F );
		setResistance( 1000F );
		setHarvestLevel( "pickaxe", 2 );
		
		int lightLevel = ( /*getBlockState( BURNING ) ? 13 :*/ 0 );
		setLightLevel( lightLevel );
		// setLightOpacity( 1 );
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) 
	{
		return EnumBlockRenderType.MODEL;
	}
	
	
	
	// TODO: --> BlockStated.setState()
	@Override
	protected BlockStateContainer createBlockState() 
	{
		return new BlockStateContainer( this, new IProperty[] { BURNING, FACING } );
	}
	
	// TODO: --> BlockStated.getStateFromMeta()
	@Override
	public IBlockState getStateFromMeta( int meta ) 
	{
		EnumFacing facing = EnumFacing.getFront( meta );
		if( facing.getAxis() == EnumFacing.Axis.Y ) 
		{
			facing = EnumFacing.NORTH;
		}
		
		return getDefaultState().withProperty( FACING, facing );
	}
	
	// TODO: --> BlockStated.getMetaFromState()
	@Override
	public int getMetaFromState( IBlockState state ) 
	{
		return ( (EnumFacing) state.getValue( FACING ) ).getIndex();
	} 	
	
	
	// TODO: --> BlockStated.setState()
	public static void setState( boolean active, World worldIn, BlockPos pos ) 
	{
		// IBlockState state = worldIn.getBlockState( pos );
		//if(active) worldIn.setBlockState(pos, BlockInit.SINTERING_FURNACE.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(BURNING, true), 3);
		//else worldIn.setBlockState(pos, BlockInit.SINTERING_FURNACE.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(BURNING, false), 3);
		
		TileEntity tileentity = worldIn.getTileEntity( pos );
		if( tileentity != null ) 
		{
			tileentity.validate();
			worldIn.setTileEntity( pos, tileentity );
		}
	}
	
	
	// TODO: --> BlockFaced.getStateForPlacement()
	@Override
	public IBlockState getStateForPlacement( 
		World world, BlockPos pos, EnumFacing facing, 
		float hitX, float hitY, float hitZ, int meta, 
		EntityLivingBase placer, EnumHand hand ) 
	{
		return getDefaultState()
				.withProperty( FACING, placer.getHorizontalFacing().getOpposite() );
	}
	
	// TODO: --> BlockFaced.onBlockPlacedBy()
	@Override
	public void onBlockPlacedBy(
		World world, BlockPos pos, 
		IBlockState state, EntityLivingBase placer, ItemStack stack ) 
	{
		world.setBlockState( pos, 
			getDefaultState()
				.withProperty( FACING, placer.getHorizontalFacing().getOpposite() )
			//	.withProperty( BURNING, false )
		, 2 );
	}
	
	// TODO: --> BlockFaced.onBlockAdded()
	@Override
	public void onBlockAdded( World world, BlockPos pos, IBlockState state ) 
	{
		if( !world.isRemote ) 
        {
			EnumFacing facing = (EnumFacing) state.getValue( FACING );
			/* {
				IBlockState north = world.getBlockState( pos.north() );
	            IBlockState south = world.getBlockState( pos.south() );
	            IBlockState west  = world.getBlockState( pos.west() );
	            IBlockState east  = world.getBlockState( pos.east() );
	            
	            if 		( facing == EnumFacing.NORTH && north.isFullBlock() && !south.isFullBlock() ) 
	            	facing = EnumFacing.SOUTH;
	            else if ( facing == EnumFacing.SOUTH && south.isFullBlock() && !north.isFullBlock() ) 
	            	facing = EnumFacing.NORTH;
	            else if ( facing == EnumFacing.WEST  && west.isFullBlock()  && !east.isFullBlock() ) 
	            	facing = EnumFacing.EAST;
	            else if ( facing == EnumFacing.EAST  && east.isFullBlock()  && !west.isFullBlock() ) 
	            	facing = EnumFacing.WEST;
			} */

            world.setBlockState( pos, 
            	state
            		.withProperty( FACING, facing )
    			//	.withProperty( BURNING, false )
            , 2 );
        }
	}
	
	
	// TODO: --> BlockFaced.withMirror()
	@Override
	public IBlockState withRotation( IBlockState state, Rotation rot )
	{
		return 
			state
				.withProperty( FACING, rot.rotate( (EnumFacing) state.getValue( FACING ) ) );
	}
	
	// TODO: --> BlockFaced.withMirror()
	@Override
	public IBlockState withMirror( IBlockState state, Mirror mirrorIn ) 
	{
		return state.withRotation( mirrorIn.toRotation( (EnumFacing) state.getValue( FACING ) ) );
	}
	
	
	// TODO: --> BlockWithTileEntity
	@Override
	public boolean hasTileEntity( IBlockState state ) 
	{
		return true;
	}
	
	// TODO: --> BlockWithTileEntity
	@Override
	public TileEntity createTileEntity( World world, IBlockState state ) 
	{
		return new TileEntityFabricator( /*_number*/ );
	}
}
