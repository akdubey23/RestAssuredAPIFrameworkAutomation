package com.spotify.oauth2.api;

import com.spotify.oauth2.pojo.Item;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static com.spotify.oauth2.api.Route.API;
import static com.spotify.oauth2.api.Route.TOKEN;
import static com.spotify.oauth2.api.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class RestResource {


    // Creating reusable methods, which can be used across all API's

    public static Response post(String path, String token, Object requestPlayist)
    {
       return given(getRequestSpec())
                .body(requestPlayist)
               .auth().oauth2(token)
//                .header("Authorization", "Bearer " + token)
                .when().post(path)  // Removed unnecessary '?='
                .then().spec(getResponseSpec())
                .extract()
                .response(); //Desrialisation
    }


    //Method created for Get

    public static Response get(String path,String token)
    {
        return given(getRequestSpec())
                .auth().oauth2(token)
                .when().get(path)  // Removed unnecessary '?='
                .then().spec(getResponseSpec())
                .contentType(ContentType.JSON)
                .extract().response();
    }

    public static Response put(Object requestPlayist, String path, String token)
    {
        return given(getRequestSpec())
                .auth().oauth2(token)
                .body(requestPlayist)
                .when().put(path)  // Removed unnecessary '?='
                .then().spec(getResponseSpec())
                .extract().response();
    }

    //    Renew Access token

    public static Response postAccount(HashMap<String, String> formParamater)
    {
        return given(getAccountRequestSpec())
                .formParams(formParamater).
                when()
                        .post(API + TOKEN).
                then()
                        .spec(getResponseSpec())
                        .extract().response();
    }
}

