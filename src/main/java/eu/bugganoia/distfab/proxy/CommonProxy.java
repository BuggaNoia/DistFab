package eu.bugganoia.distfab.proxy;


public class CommonProxy 
{
	public boolean IsRemote() { return true; }
	
	public Boolean IsWorldRemote()
	{
		return new Boolean( IsRemote() );
	}
}
