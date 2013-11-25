package ai.config;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author  Igor Androsov
 * @version 1.0
 */

import java.io.*;
import java.util.Hashtable;
import java.util.Properties;

// Referenced classes of package ai.config:
//            ConfigManager

public final class Config
{
    static class MissingConfigurationException extends Exception
    {

        public MissingConfigurationException()
        {
        }

        public MissingConfigurationException(String msg)
        {
            super(msg);
        }
    }


    public Config()
    {
    }

    public static final synchronized void InitConfig(String config_file)
        throws MissingConfigurationException, IOException, FileNotFoundException, ClassNotFoundException
    {
        FileInputStream fin = null;
        if(!isInitialized)
        {
            try
            {
                fin = new FileInputStream(new File(config_file));
                Properties config = new Properties();
                config.load(fin);
                String value = null;
                value = (String)config.get("wm_port");
                if(value == null || value == "")
                    throw new MissingConfigurationException("Missing property : wm_port");
                value = (String)config.get("wm_server");
                if(value == null || value == "")
                    throw new MissingConfigurationException("Missing property : wm_server");
                value = (String)config.get("wm_user");
                if(value == null || value == "")
                    throw new MissingConfigurationException("Missing property : wm_user");
                value = (String)config.get("wm_pws");
                if(value == null || value == "")
                    throw new MissingConfigurationException("Missing property : wm_pws");
                ConfigManager.initialize(config);
            }
            catch(MissingConfigurationException e)
            {
                throw e;
            }
            catch(Exception e)
            {
                System.out.println("Warning : configuration file not found");
                throw new FileNotFoundException("File not found");
            }
            finally
            {
                if(fin != null)
                    fin.close();
            }
            isInitialized = true;
        }
    }

    static boolean isInitialized = false;

}