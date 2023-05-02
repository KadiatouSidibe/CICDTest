package psa.frmkLogging.utils.config;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class java

{
	// ---( internal utility methods )---

	final static java _instance = new java();

	static java _newInstance() { return new java(); }

	static java _cast(Object o) { return (java)o; }

	// ---( server methods )---




	public static final void makeStringFomStringList (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(makeStringFomStringList)>> ---
		// @sigtype java 3.5
		// [i] field:1:required stringListIn
		// [i] field:0:required separator
		// [o] field:0:required stringOut
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String[]	stringListIn = IDataUtil.getStringArray( pipelineCursor, "stringListIn" );
			String	separator = IDataUtil.getString( pipelineCursor, "separator" );
		pipelineCursor.destroy();
		
		StringBuffer s=new StringBuffer();
		
		for (int i=0;i<stringListIn.length;i++){
			s.append(stringListIn[i]);
			if (i!=stringListIn.length-1)
				s.append(separator);
		}
		
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "stringOut", s.toString() );
		pipelineCursor_1.destroy();
			
		// --- <<IS-END>> ---

                
	}
}

