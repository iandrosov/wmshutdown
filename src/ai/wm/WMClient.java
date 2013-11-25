package ai.wm;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author  Igor Androsov
 * @version 2.0
 */

import com.wm.app.b2b.client.Context;
import com.wm.lang.ns.NSName;
import com.wm.util.Values;
import java.io.*;
import java.net.*;
import java.util.*;

// Referenced classes of package ai.wm:
//            RemoteServiceException

public class WMClient
{

    public WMClient()
    {

    }

    public static final Hashtable executeService(String server, String port, String user, String password, String service, Hashtable params)
        throws RemoteServiceException
    {
        Context context = new Context();
        Values values = new Values();
        Values out = null;
        Hashtable map = new Hashtable();
        Enumeration enum = null;
        String key = null;
        try
        {
            values = values.copyFrom(params);
            context = new Context();
            context.connect(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(server)))).append(":").append(port))), user, password);
            out = (Values)context.invoke(NSName.create(service), values);
            enum = out.keys();
            Object obj_temp = null;
            do
            {
                if(!enum.hasMoreElements())
                    break;
                key = (String)enum.nextElement();
                obj_temp = out.get(key);
                if(obj_temp != null)
                    map.put(key, obj_temp);

            } while(true);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw new RemoteServiceException(e.getMessage());
        }
        finally
        {
            if(context != null)
                context.disconnect();
        }
        return map;
    }
}