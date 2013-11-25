/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author  Igor Androsov
 * @version 2.0
 */

import ai.config.Config;
import ai.config.ConfigManager;
import ai.wm.WMClient;
import java.io.PrintStream;
import java.util.Hashtable;

public class wmprobe
{

    public wmprobe()
    {
    }

    public static void main(String args[])
    {
        try
        {
            wmprobe wmprobe1 = new wmprobe();
            int cnt = args.length;
            if(cnt < 1)
            {
                System.out.println("Property file missing!");
                System.exit(1);
            }
            String config_file = args[0];
            Config.InitConfig(config_file);
            String server  = ConfigManager.getProperty("wm_server");
            String port    = ConfigManager.getProperty("wm_port");
            String user    = ConfigManager.getProperty("wm_user");
            String pwd     = ConfigManager.getProperty("wm_pws");
            String service = ConfigManager.getProperty("wm_service");
            String restart = ConfigManager.getProperty("wm_restart");
			String option  = ConfigManager.getProperty("wm_option");
			String timeout = ConfigManager.getProperty("wm_timeout");

            Hashtable parms = new Hashtable();
            parms.put("status", "Success");
            if (option != null)
				parms.put("option",option);
			if (restart != null)
				parms.put("bounce",restart);
			if (timeout != null)
				parms.put("timeout",timeout);

            parms = WMClient.executeService(server, port, user, pwd, service, parms);

            String str = (String)parms.get("status");
            String msg = (String)parms.get("message");

            System.out.println(msg);
            if(str.equals("Success"))
                System.exit(0);
            else
            	System.exit(1);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}