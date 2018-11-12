package eu.bugganoia.distfab.util;

import org.apache.logging.log4j.Logger;

public class DfLog 
{
	protected static Logger _apacheLogger;
	// protected static LogHelper _l;
	
	public DfLog(Logger logger)
	{
		_apacheLogger = logger;
	}
	
	public void info( String message, Object... p0 )
	{
		if( _apacheLogger != null )
		{
			_apacheLogger.info( message, p0 );
		}
		
	}
}
