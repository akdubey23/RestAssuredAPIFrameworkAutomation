package com.spotify.oauth2.api;

import com.spotify.oauth2.utils.ConfigLoader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;

import static com.spotify.oauth2.api.RestResource.postAccount;
import static com.spotify.oauth2.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

public class TokenManager {

    static String access_token;
    static Instant expiry_time;

    public synchronized static String getToken()
    {
        try {

            if (access_token == null || Instant.now().isAfter(expiry_time)) {
                System.out.println("Renewing the access token ..");
                Response response = renewToken();
                access_token = response.path("access_token");
                int expiryDurationInSeconds = response.path("expires_in");
                expiry_time = Instant.now().plusSeconds(expiryDurationInSeconds - 300);
            }
            else
            {
                System.out.println("Token is good to use");
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException("ABORT!! Renew Token failed");
        }

        return access_token;

    }

    private static Response renewToken() throws IOException {
        HashMap<String,String> formParamater = new HashMap<String,String>();
        formParamater.put("client_id", ConfigLoader.getInstance().getClientId());
        formParamater.put("client_secret", ConfigLoader.getInstance().getClientSecret());
        formParamater.put("refresh_token", ConfigLoader.getInstance().getrefresh_token());
        formParamater.put("grant_type", ConfigLoader.getInstance().getgrant_type());

        Response response = postAccount(formParamater);

        if(response.statusCode()!=200)
        {
            throw new RuntimeException("ABORT!! Renew Token failed");
        }
        return  response;

    }
}
