package ai.config;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author  Igor Androsov
 * @version 1.0
 */

import java.util.Properties;

public class ConfigManager
{

    public ConfigManager()
    {
    }

    public static final synchronized void initialize(Properties prop)
    {
        if(!isInitialized)
            properties = prop;
    }

    public static final String getProperty(String key)
    {
        return properties.getProperty(key);
    }

    private static Properties properties;
    private static boolean isInitialized = false;

}