package psa.frmkLogging.priv.log;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.util.ArrayList;
import java.util.List;
import com.wm.lang.ns.NSName;
import org.apache.log4j.MDC;
import org.apache.logging.log4j.*;
// --- <<IS-END-IMPORTS>> ---

public final class java

{
	// ---( internal utility methods )---

	final static java _instance = new java();

	static java _newInstance() { return new java(); }

	static java _cast(Object o) { return (java)o; }

	// ---( server methods )---




	public static final void checkLevelLogging (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(checkLevelLogging)>> ---
		// @sigtype java 3.5
		// [i] field:0:required logName
		// [i] field:0:required level
		// [o] object:0:required toLog
		// Get input parameters
		final IDataCursor pipelineCursor = pipeline.getCursor();
		String logName = IDataUtil.getString(pipelineCursor, "logName");
		final String level = IDataUtil.getString(pipelineCursor, "level");
		  
		// Get the default log name
		if (logName == null){
			logName = DEFAULT_LOG_NAME;
		} 
		 
		Logger logger = LogManager.getLogger(logName);
		
		boolean isEnabled=false;
		if (level != null && level.trim().equals(LEVEL_WARN) && logger.isWarnEnabled()) {
			isEnabled=true;
		}
		else if (level != null && level.trim().equals(LEVEL_INFO) && logger.isInfoEnabled()){
			isEnabled=true;
		}
		else if (level != null && level.trim().equals(LEVEL_ERROR) && logger.isErrorEnabled()){
			isEnabled=true;
		}
		else if (level != null && level.trim().equals(LEVEL_FATAL) && logger.isFatalEnabled()){
			isEnabled=true;
		}
		else if (level != null && level.trim().equals(LEVEL_TRACE) && logger.isTraceEnabled()){
			isEnabled=true;
		}
		else if (level != null && level.trim().equals(LEVEL_DEBUG) && logger.isDebugEnabled()){
			isEnabled=true;
		}
			
		// pipeline
		IDataCursor pipelineSortie = pipeline.getCursor();
		IDataUtil.put(pipelineSortie, "toLog", ""+isEnabled);
		pipelineSortie.destroy();
			
			
		// --- <<IS-END>> ---

                
	}



	public static final void logMessage (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(logMessage)>> ---
		// @sigtype java 3.5
		// [i] recref:0:required logEvent psa.frmkLogging.docs:LogEvent
		//Retrieve input parameters from the pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		
		IData logEvent = IDataUtil.getIData(pipelineCursor,"logEvent");
		
		String	logName = DEFAULT_LOG_NAME;
		String	level = "INFO";
		String	message = "";
		IData commonHeaders = null ;
		List<String> tabKeys = new ArrayList<String>();
		
		try {
			if (logEvent != null){
				//Get cursor
				IDataCursor logEventCursor = logEvent.getCursor(); 
		
				//get data from input
				logName = IDataUtil.getString(logEventCursor,"logName");
				level = IDataUtil.getString(logEventCursor,"level");
				message = IDataUtil.getString(logEventCursor,"message");
				commonHeaders = IDataUtil.getIData(logEventCursor,"header");
		
				//add header http to Log4j MDC
				if (commonHeaders != null){
					IDataCursor commonHeadersCursor =  commonHeaders.getCursor();
					//On parcours l'enteteHttp pour recuperer les champs \u00E0 ajouter dans la log
					while(commonHeadersCursor.hasMoreData()){
						commonHeadersCursor.next();		
						//add field to the log
						if(commonHeadersCursor.getKey()!= null){
							tabKeys.add(commonHeadersCursor.getKey());
							if(commonHeadersCursor.getValue() != null){
								addInfoLog(commonHeadersCursor.getKey(), commonHeadersCursor.getValue());
							}else{
								addInfoLog(commonHeadersCursor.getKey(), "");
							}
						}
					}
					//Destroy pipeline
					commonHeadersCursor.destroy();
				}
		
			}
			//Layout Level with 4 characters
			if (level.length() == 4) {
				level += " ";
			}
		
			//Get default log name
			if (logName == null){
				logName = DEFAULT_LOG_NAME;
			}
			org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger(logName);
		
			// write message if log4j level is enabled
			if (level.trim().equals(LEVEL_WARN) && logger.isWarnEnabled()) {
				logger.log(org.apache.logging.log4j.Level.WARN, message);
			}
			else if (level.trim().equals(LEVEL_INFO) && logger.isInfoEnabled()){
				logger.log(org.apache.logging.log4j.Level.INFO, message);
			}
			else if (level.trim().equals(LEVEL_ERROR) && logger.isErrorEnabled()){
				logger.log(org.apache.logging.log4j.Level.ERROR, message);
			}
			else if (level.trim().equals(LEVEL_FATAL) && logger.isFatalEnabled()){
				logger.log(org.apache.logging.log4j.Level.FATAL, message);
			}
			else if (level.trim().equals(LEVEL_TRACE) && logger.isTraceEnabled()){
				logger.log(org.apache.logging.log4j.Level.TRACE, message);
			}
			else if (level.trim().equals(LEVEL_DEBUG) && logger.isDebugEnabled()){
				logger.log(org.apache.logging.log4j.Level.DEBUG, message);
			}
		
			//delete infos from Log
			deleteInfoLog();
		} catch (RuntimeException e) {
			myLog("Erreur lors de la tentative de trace du message (" + level + ") (" + logName+ "): " + message + "/");
		}
			
			
			
			
			
			
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	
	/**Default Log Name*/
	private static final String DEFAULT_LOG_NAME = "psa.LOG_DEFAULT";
	/** Level INFO**/
	private static final String LEVEL_INFO = "INFO";
	/** Level ERROR**/
	private static final String LEVEL_ERROR = "ERROR";
	/** Level WARN**/
	private static final String LEVEL_WARN = "WARN";
	/** Level FATAL**/
	private static final String LEVEL_FATAL = "FATAL";
	/** Level TRACE**/
	private static final String LEVEL_TRACE = "TRACE";
	/** Level DEBUG**/
	private static final String LEVEL_DEBUG = "DEBUG";
	/** Key Origin**/
	private static final String KEY_ORIGIN = "origin";	
	/** Key requestID**/
	private static final String KEY_REQUEST_ID = "requestID";	
	/** Key countryCode**/
	private static final String KEY_COUNTRY_CODE = "countryCode";	
	/** Key senderID**/
	private static final String KEY_SENDER_ID = "senderID";	
	
	
	/**
	 * Allow to add information to MDC
	 * @param String key 
	 * 		  Object Info (Information \u00E0 ajouter dans la trace)
	 */
	private static void addInfoLog(String key, Object info){
		//Ajout des champs dans  MDC du log4j
			MDC.put(key , info);		
	}
	
	/**
	 * Allow to delete data to MDC
	 * @param
	 * 
	 */
	private static void deleteInfoLog(){
		MDC.remove(KEY_ORIGIN);
		MDC.remove(KEY_COUNTRY_CODE);
		MDC.remove(KEY_REQUEST_ID);
		MDC.remove(KEY_SENDER_ID);
	}
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
		try {
			Service.doInvoke(debugLog, in);
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}	
		
		
		
		
		
		
	// --- <<IS-END-SHARED>> ---
}

