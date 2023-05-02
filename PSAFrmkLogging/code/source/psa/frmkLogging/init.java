package psa.frmkLogging;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.lang.ns.NSName;
import java.io.File;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.LogManager;
// --- <<IS-END-IMPORTS>> ---

public final class init

{
	// ---( internal utility methods )---

	final static init _instance = new init();

	static init _newInstance() { return new init(); }

	static init _cast(Object o) { return (init)o; }

	// ---( server methods )---




	public static final void logConfig (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(logConfig)>> ---
		// @sigtype java 3.5
		// [i] field:0:required log4jConfigLocation
		//get input parameters
		IDataCursor pipelineCursor = pipeline.getCursor();
		String	log4jConfigLocation = IDataUtil.getString(pipelineCursor,"log4jConfigLocation");
		
		//Variable message 
		String message = "";
		
		//get log4j.properties
		File log4jConfigFichier = new File(log4jConfigLocation);
		
		if (log4jConfigFichier.exists()) {
			try {
				org.apache.logging.log4j.core.LoggerContext loggerContext = (org.apache.logging.log4j.core.LoggerContext)org.apache.logging.log4j.LogManager.getContext(false);
				loggerContext.setConfigLocation(log4jConfigFichier.toURI());
		         message = "Log4j properties file configuration used is : " + log4jConfigLocation;
				  myLog(message); 
			} catch (Exception e) {
				message = "Log4j.propeties not found  - Use default configuration";
				myLog(message);
				e.printStackTrace();
			}
		}
		else {
		  //Use default configuration
		  //BasicConfigurator.configure();
		  message = "Log4j.propeties not found  - Use default configuration";
		  myLog(message);
		}
		
		//destroy cursor
		pipelineCursor.destroy();
			
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	/**Nom du journal de la configuration du Log4j*/
	private static final String DEFAULT_LOGGER = "psa.DEFAULT_LOG";
	
	/**
	 * Allo to log to server.log
	 * @param
	 * 	   log message 
	 */
	private static void myLog(String message){
		NSName debugLog = NSName.create("pub.flow:debugLog");
		IData in = IDataFactory.create();
		IDataCursor cursorin = in.getCursor();
		IDataUtil.put(cursorin, "message", message);
		IDataUtil.put(cursorin, "level", "Info");
		try {
			Service.doInvoke(debugLog, in);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
		
		
		
		
		
		
		
	// --- <<IS-END-SHARED>> ---
}

