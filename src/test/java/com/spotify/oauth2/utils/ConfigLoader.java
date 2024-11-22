package com.spotify.oauth2.utils;

import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    private  final Properties properties;
    private static ConfigLoader configloader;

    private ConfigLoader() throws IOException {
        properties = PropertyUtils.propertyLoader("src/test/resources/config.properties");
    }

    public static ConfigLoader getInstance() throws IOException {
        if(configloader == null){
            configloader= new ConfigLoader();
        }
        return configloader;
    }

    public String  getClientId()
    {
        String prop = properties.getProperty("client_id");
        if(prop!=null) return prop;
        else throw new RuntimeException("property client-id is not specified in the config.properties file");
    }

    public String getClientSecret()
    {
        String prop = properties.getProperty("client_secret");
        if(prop!=null) return prop;
        else throw new RuntimeException("property client_secret is not specified in the config.properties file");
    }

    public String getrefresh_token()
    {
        String prop = properties.getProperty("refresh_token");
        if(prop!=null) return prop;
        else throw new RuntimeException("property refresh_token is not specified in the config.properties file");
    }

    public String getgrant_type()
    {
        String prop = properties.getProperty("grant_type");
        if(prop!=null) return prop;
        else throw new RuntimeException("property grant_type is not specified in the config.properties file");
    }

    public String getuser_id()
    {
        String prop = properties.getProperty("user_id");
        if(prop!=null) return prop;
        else throw new RuntimeException("property user_id is not specified in the config.properties file");
    }
}
