package eu.bugganoia.distfab.blocks;

import eu.bugganoia.distfab.blocks.tileentities.TileEntityFabricator;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
// import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumBlockRenderType;
// import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;


public class BlockFabricator extends BlockBase implements ITileEntityProvider
{
	// --- Members
	// TODO: --> BlockFaced.FACING
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	// TODO: --> BlockState.ACTIVE
	public static final PropertyBool BURNING = PropertyBool.create( "burning" );
	
	protected int _number;

	
	// --- Constructor, Prop-Overloads
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
	public EnumBlockRenderType getRenderType( IBlockState state ) 
	{
		return EnumBlockRenderType.MODEL;
	}
	
	
	// --- TEST
	@Override
	public boolean onBlockActivated(
			World worldIn, BlockPos pos, 
			IBlockState state, EntityPlayer playerIn, 
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ ) 
	{
		if( !worldIn.isRemote && playerIn != null )
		{
			if( playerIn != null )
			{
				/*playerIn.openGui( DistFab.getInstance(), GuiInit.GUI_Fabricator, worldIn, 
					pos.getX(), pos.getY(), pos.getZ() ); */
				
				playerIn.sendMessage( new TextComponentString( 
					String.format( "BLOCKCLASS: number: %d"
										/* + ", facing: %s, burning: %s"*/ ,
						_number /*, 
						getBlockState().getBaseState().getValue( FACING ).toString(), 
						getBlockState().getBaseState().getValue( BURNING ).toString() */
					) ) );
			}
			
			TileEntity tileEntity = worldIn.getTileEntity( pos );
			if( tileEntity != null && tileEntity instanceof TileEntityFabricator ) 
			{
				TileEntityFabricator tileEntityFabricator = (TileEntityFabricator) tileEntity;
				
				playerIn.sendMessage( new TextComponentString( 
					String.format( "TILEENTITY: number: %d, facing: %s",
						tileEntityFabricator.getNumber(), 
						tileEntityFabricator.getState().getValue( FACING ).toString() ) ) );
			}
		}
		
		return true;
	}	
	
	
	// --- Stated-Block
	// TODO: --> BlockStated.setState(), List<IProperty> this._properties;
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
		
		TileEntity tileEntity = worldIn.getTileEntity( pos );
		if( tileEntity != null ) 
		{
			tileEntity.validate();
			worldIn.setTileEntity( pos, tileEntity );
		}
	}
	
	
	// --- Faced-Block
	// TODO: --> BlockFaced.getStateForPlacement()
	@Override
	public IBlockState getStateForPlacement( 
		World worldIn, BlockPos pos, 
		EnumFacing facing, 
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
	
	
	// --- ITileEntityProvider
	// TODO: --> BlockTileEntityProvider
	@Override
	public boolean hasTileEntity( IBlockState state ) 
	{
		return true;
	}
	
	// TODO: --> BlockTileEntityProvider
	@Override
	public TileEntity createTileEntity( World worldIn, IBlockState state ) 
	{
		return new TileEntityFabricator( _number, state );
	}

	// TODO: --> BlockTileEntityProvider
	@Override
	public TileEntity createNewTileEntity( World worldIn, int meta ) 
	{
		return new TileEntityFabricator( _number, getStateFromMeta( meta ) );
	}
}
