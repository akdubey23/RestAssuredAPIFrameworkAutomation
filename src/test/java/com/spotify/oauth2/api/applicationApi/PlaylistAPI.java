package com.spotify.oauth2.api.applicationApi;

import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.pojo.Item;
import com.spotify.oauth2.utils.ConfigLoader;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


import java.io.IOException;

import static com.spotify.oauth2.api.Route.PLAYLISTS;
import static com.spotify.oauth2.api.Route.USERS;
import static com.spotify.oauth2.api.TokenManager.getToken;


public class PlaylistAPI {

//    static String access_token = "BQA-FgJP9LiclBYNnjYzwPRWRrZy19TxcPmqV2eUXXr4xirSgfxWuE3m5qoBRuV_gQC4y4rVKaldUtXhmbrkLlstVbbSc_ZqePMZMv8vlCnaRK2pPHv9K8Zw4X2SQccZtCRazDkAHRjtpaAF6K-7B0hoYlkqm9JQOxEZnEkr37x8kGbBtXfsT6bHPFEDQi6kjUi2fuqm4zOmLwJuM2Y9pWc5dI-Li8MWLQwApQiXcPyGxBXIqRvDJxNopSRFYeuFJTeZMTaMdFVFRvc4aIWzyK8c";
    //Method created for Post, Get and passing parameters as argument,
    // also In the argument we are giving classes as we are using Pojo

    @Step
    public static Response post(Item requestPlayist) throws IOException {
        return RestResource.post(USERS+ "/" +
                ConfigLoader.getInstance().getuser_id() +
                PLAYLISTS, getToken(), requestPlayist);
    }
    public static Response post(String Token, Item requestPlayist) throws IOException {
        return RestResource.post(USERS+ "/" +
                ConfigLoader.getInstance().getuser_id() +
                PLAYLISTS, Token, requestPlayist);

    }

    //Method created for Get

    public static Response get(String playlistID)
    {
        return  RestResource.get(PLAYLISTS + "/" +  playlistID, getToken());
    }

    public static Response put(Item requestPlayist, String playlistID)
    {
        return RestResource.put(requestPlayist,PLAYLISTS + "/" + playlistID, getToken());

    }
}

