package psa.frmkLogging.utils;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.sun.management.VMOption.Origin;
// --- <<IS-END-IMPORTS>> ---

public final class string

{
	// ---( internal utility methods )---

	final static string _instance = new string();

	static string _newInstance() { return new string(); }

	static string _cast(Object o) { return (string)o; }

	// ---( server methods )---




	public static final void getInputString (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getInputString)>> ---
		// @sigtype java 3.5
		// [i] field:1:required keys
		// [i] field:1:required values
		// [o] field:0:required inputString
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String[]	keys = IDataUtil.getStringArray( pipelineCursor, "keys" );
			String[]	values = IDataUtil.getStringArray( pipelineCursor, "values" );
		pipelineCursor.destroy();
		
		StringBuffer sb=new StringBuffer("");
		if (keys!=null && keys.length!=0){
			for (int i=0;i<keys.length;i++){
				String key=keys[i];
				String value="null";
				try{
					value=values[i];
				}catch(Exception e){}
				
				if (value==null)
					sb.append("[").append(key).append(":").append("null]");
				else
					sb.append("[").append(key).append(":").append(value).append("]");
			}
		}
		
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "inputString", sb.toString() );
		pipelineCursor_1.destroy();
			
		// --- <<IS-END>> ---

                
	}



	public static final void getLongDescString (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getLongDescString)>> ---
		// @sigtype java 3.5
		// [i] field:0:required service
		// [i] field:0:required inputString
		// [i] field:0:required codeErreur
		// [i] field:0:required msgErreur
		// [o] field:0:required longDescString
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String	service = IDataUtil.getString( pipelineCursor, "service" );
			String	inputString = IDataUtil.getString( pipelineCursor, "inputString" );
			String	codeErreur = IDataUtil.getString( pipelineCursor, "codeErreur" );
			String	msgErreur = IDataUtil.getString( pipelineCursor, "msgErreur" );
		pipelineCursor.destroy();
		
		StringBuffer sb=new StringBuffer("");
		if (service==null || service.equals(""))
			service="Service inconnu";
		if (codeErreur==null || codeErreur.equals(""))
			codeErreur="ERR_UNKNOWN_ESB";
		if (inputString==null || inputString.equals(""))
			inputString="[N/A]";
		if (msgErreur==null || msgErreur.equals(""))
			msgErreur="Erreur vide";
		
		sb.append("Erreur : ").append(codeErreur);
		sb.append("\r\nService : ").append(service);
		sb.append("\r\nInputs : ").append(inputString);
		sb.append("\r\nLog Erreur : ").append(msgErreur);
		
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "longDescString", sb.toString() );
		pipelineCursor_1.destroy();
			
			
		// --- <<IS-END>> ---

                
	}



	public static final void getMsgContextString (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getMsgContextString)>> ---
		// @sigtype java 3.5
		// [i] recref:0:required logHeader psa.frmkLogging.docs:LogHeader
		// [o] field:0:required msgContext
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		
			// logHeader
			IData	logHeader = IDataUtil.getIData( pipelineCursor, "logHeader" );
			StringBuffer sb=new StringBuffer("");
			if ( logHeader != null)
			{
				sb.append("Infos Context : ");
				IDataCursor logHeaderCursor = logHeader.getCursor();
					String	origin = IDataUtil.getString( logHeaderCursor, "origin" );
					String	requestID = IDataUtil.getString( logHeaderCursor, "requestID" );
					String	countryCode = IDataUtil.getString( logHeaderCursor, "countryCode" );
					String	senderID = IDataUtil.getString( logHeaderCursor, "senderID" );
					String	language = IDataUtil.getString( logHeaderCursor, "language" );
					String	project = IDataUtil.getString( logHeaderCursor, "project" );
					String	service = IDataUtil.getString( logHeaderCursor, "service" );
				logHeaderCursor.destroy();
				
				if(origin!=null)
					sb.append("[origin:").append(origin).append("]");
				else
					sb.append("[origin:null]");
				if(requestID!=null)
					sb.append("[requestID:").append(requestID).append("]");
				else
					sb.append("[requestID:null]");
				if(countryCode!=null)
					sb.append("[countryCode:").append(countryCode).append("]");
				else
					sb.append("[countryCode:null]");
				if(senderID!=null)
					sb.append("[senderID:").append(senderID).append("]");
				else
					sb.append("[senderID:null]");
				if(language!=null)
					sb.append("[language:").append(language).append("]");
				else
					sb.append("[language:null]");
				if(project!=null)
					sb.append("[project:").append(project).append("]");
				else
					sb.append("[project:null]");
				if(service!=null)
					sb.append("[service:").append(service).append("]");
				else
					sb.append("[service:null]");
			}else{
				sb.append("[Infos Context non disponibles]");
			}
		pipelineCursor.destroy();
		
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "msgContext", sb.toString() );
		pipelineCursor_1.destroy();
			
			
		// --- <<IS-END>> ---

                
	}



	public static final void getShortDescString (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getShortDescString)>> ---
		// @sigtype java 3.5
		// [i] field:0:required service
		// [i] field:0:required codeErreur
		// [o] field:0:required shortDescString
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String	service = IDataUtil.getString( pipelineCursor, "service" );
			String	codeErreur = IDataUtil.getString( pipelineCursor, "codeErreur" );
		pipelineCursor.destroy();
		
		StringBuffer sb = new StringBuffer("");
		if (service==null || service.equals(""))
			service="Service inconnu";
		if (codeErreur==null || codeErreur.equals(""))
			codeErreur="ERR_UNKNOWN_ESB";
		
		sb.append(service).append(" - ").append(codeErreur);
		
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "shortDescString", sb.toString() );
		pipelineCursor_1.destroy();
		// --- <<IS-END>> ---

                
	}



	public static final void replaceAccent (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(replaceAccent)>> ---
		// @sigtype java 3.5
		// [i] field:0:required stringIn
		// [o] field:0:required stringOut
		IDataCursor pipelineCursor_in = pipeline.getCursor();
		String stringIn = IDataUtil.getString(pipelineCursor_in, "stringIn");
		pipelineCursor_in.destroy();
		
		String stringOut=null;
		
		if (stringIn!=null)
		{
			// Majuscule :
			stringOut=stringIn.replaceAll("[\u00C0,\u00C1,\u00C2,\u00C3,\u00C4]", "A");
			stringOut=stringOut.replaceAll("[\u00C6]", "AE");
			stringOut=stringOut.replaceAll("[\u00C7]", "C");
			stringOut=stringOut.replaceAll("[\u00C8,\u00C9,\u00CA,\u00CB]", "E");
			stringOut=stringOut.replaceAll("[\u00CC,\u00CD,\u00CE,\u00CF]", "I");
			stringOut=stringOut.replaceAll("[\u00D1]", "N");
			stringOut=stringOut.replaceAll("[\u00D2,\u00D3,\u00D4,\u00D5,\u00D6]", "O");
			stringOut=stringOut.replaceAll("[\u0152]", "OE");
			stringOut=stringOut.replaceAll("[\u00D9,\u00DA,\u00DB,\u00DC]", "U");
			stringOut=stringOut.replaceAll("[\u00DD,\u0178]", "Y");
		
			// Minuscule :
			stringOut=stringOut.replaceAll("[\u00E0,\u00E1,\u00E2,\u00E3,\u00E4]", "a");
			stringOut=stringOut.replaceAll("[\u00E6]", "ae");
			stringOut=stringOut.replaceAll("[\u00E7]", "c");
			stringOut=stringOut.replaceAll("[\u00E8,\u00E9,\u00EA,\u00EB]", "e");
			stringOut=stringOut.replaceAll("[\u00EC,\u00ED,\u00EE,\u00EF]", "i");
			stringOut=stringOut.replaceAll("[\u00F1]", "n");
			stringOut=stringOut.replaceAll("[\u00F2,\u00F3,\u00F4,\u00F5,\u00F6]", "o");
			stringOut=stringOut.replaceAll("[\u0153]", "oe");
			stringOut=stringOut.replaceAll("[\u00F9,\u00FA,\u00FB,\u00FC]", "u");
			stringOut=stringOut.replaceAll("[\u00FD,\u00FF]", "y");
		}
		
		IDataCursor pipelineCursor_out = pipeline.getCursor();
		IDataUtil.put(pipelineCursor_out, "stringOut", stringOut);
		pipelineCursor_out.destroy();
		// --- <<IS-END>> ---

                
	}



	public static final void replaceSautsDeLigne (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(replaceSautsDeLigne)>> ---
		// @sigtype java 3.5
		// [i] field:0:required stringIn
		// [o] field:0:required stringOut
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String	stringIn = IDataUtil.getString( pipelineCursor, "stringIn" );
		pipelineCursor.destroy();
		String stringOut=null;
		
		if (stringIn!=null){			
			stringOut=stringIn.replaceAll("\r\n", "|");
			stringOut=stringOut.replaceAll("\n", "|");
			stringOut=stringOut.replaceAll("\r", "|");
		}
		
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "stringOut", stringOut );
		pipelineCursor_1.destroy();
			
			
		// --- <<IS-END>> ---

                
	}



	public static final void supprSautsDeLigne (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(supprSautsDeLigne)>> ---
		// @sigtype java 3.5
		// [i] field:0:required stringIn
		// [o] field:0:required stringOut
		
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String	stringIn = IDataUtil.getString( pipelineCursor, "stringIn" );
		pipelineCursor.destroy();
		String stringOut=null;
		
		if (stringIn!=null){
			stringOut=stringIn.replaceAll("\r\n", " ");
		}
		
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "stringOut", stringOut );
		pipelineCursor_1.destroy();
		// --- <<IS-END>> ---

                
	}
}

