package eu.bugganoia.distfab.blocks.tileentities;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.tileentity.TileEntity;


public class TileEntityFabricator extends TileEntity implements ITickable
{
	// --- Member
	protected int _number;
	protected IBlockState _state; 
	
	public int getNumber() { return _number; }
	public IBlockState getState() { return _state; }

	
	// --- Constructor
	public TileEntityFabricator( int number, IBlockState state )
	{
		_number = number;
		_state = state;
	}
	
	
	@Override
	public void tick() 
	{
		// TODO Auto-generated method stub
		
	}

}
