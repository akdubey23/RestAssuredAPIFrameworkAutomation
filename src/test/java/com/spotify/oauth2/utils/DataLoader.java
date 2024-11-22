package com.spotify.oauth2.utils;

import java.io.IOException;
import java.util.Properties;

public class DataLoader {
    private  final Properties properties;
    private static DataLoader dataLoader;

    private DataLoader() throws IOException {
        properties = PropertyUtils.propertyLoader("src/test/resources/data.properties");
    }

    public static DataLoader getInstance() throws IOException {
        if(dataLoader == null){
            dataLoader= new DataLoader();
        }
        return dataLoader;
    }

    public String  getPlaylistId()
    {
        String prop = properties.getProperty("get_playlist_id");
        if(prop!=null) return prop;
        else throw new RuntimeException("property get_playlist_id is not specified in the config.properties file");
    }

    public String  updatePlaylistId()
    {
        String prop = properties.getProperty("update_playlist_id");
        if(prop!=null) return prop;
        else throw new RuntimeException("property update_playlist_id is not specified in the config.properties file");
    }

}
