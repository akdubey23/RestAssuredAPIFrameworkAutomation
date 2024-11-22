package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.api.applicationApi.PlaylistAPI;
import com.spotify.oauth2.pojo.Error;
import com.spotify.oauth2.api.SpecBuilder;
import com.spotify.oauth2.pojo.Item;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.DataLoader;
import io.qameta.allure.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.spotify.oauth2.api.SpecBuilder.getRequestSpec;
import static com.spotify.oauth2.api.SpecBuilder.getResponseSpec;
import static com.spotify.oauth2.utils.fakerUtils.generateDescription;
import static com.spotify.oauth2.utils.fakerUtils.generateName;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Epic("Spotify Oauth 2.0")
@Feature("Playlist API")
public class PlaylistTests extends BaseTest{
    @Story("Create a Playlist story")
    @Description("Description:- Should be able to create a Playlist")
    @Link("https://example.org/mylink")
    @Link(name = "Website", type = "mylink")
    @Issue("Issue-123")
    @TmsLink("TestCase-456")
    @Test(description = "Should be able to create a Playlist")
    public void ShouldBeAbleToCreatePlaylist() throws IOException {
        Item requestPlayist = playlistBuilder(
                generateName(),
                generateDescription(),
                false);

        Response response = PlaylistAPI.post(requestPlayist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_201);
        asserPlaylistEqual(response.as(Item.class),requestPlayist);

    }

    @Test
    public void ShouldBeAbleToGetThePlaylist() throws IOException {

        Item requestPlayist = playlistBuilder(
                "New Playlist Akanksha_Update01 ",
                "New playlist description Navarati",
                true);


        Response response = PlaylistAPI.get(DataLoader.getInstance().getPlaylistId());
        assertStatusCode(response.statusCode(),StatusCode.CODE_200);
        asserPlaylistEqual(response.as(Item.class),requestPlayist);

    }

    @Test
    public void ShouldBeAbleToUpdateThePlaylist() throws IOException {
        Item requestPlayist = playlistBuilder(
                generateName(),
                generateDescription(),
                false);
        Response response = PlaylistAPI.put(requestPlayist, DataLoader.getInstance().updatePlaylistId());
        assertStatusCode(response.statusCode(),StatusCode.CODE_200);

    }

    @Story("Create a Playlist story")
    @Test
    public void ShouldNotBeAbleToCreatePlaylist() throws IOException {
        Item requestPlayist = playlistBuilder(
                "",
                generateDescription(),
                false);

        Response response = PlaylistAPI.post(requestPlayist);
        errorAssertion(response.as(Error.class),StatusCode.CODE_400);

    }

    @Story("Create a Playlist story")
    @Test
    public void ShouldNotBeAbleToCreatePlaylistWithExpiredToken() throws IOException {
        String invalid_token = "123456";
        Item requestPlayist = playlistBuilder(
                generateName(),
                generateDescription(),
                false);


        Response response = PlaylistAPI.post(invalid_token,requestPlayist);
        errorAssertion(response.as(Error.class),StatusCode.CODE_401);

    }

    @Step
    public Item playlistBuilder(String name, String Description,  boolean _public)
    {

        return Item.builder()
                .name(name)
                .description(Description)
                ._public(_public)
                .build();
    }
    @Step
    public void asserPlaylistEqual(Item responsePlaylist, Item requestPlayist)
    {
        assertThat(responsePlaylist.getName(), equalTo(requestPlayist.getName()));
        assertThat(responsePlaylist.getDescription(),equalTo(requestPlayist.getDescription()));
        assertThat(responsePlaylist.get_public(),equalTo(requestPlayist.get_public()));
    }

    @Step
    public void assertStatusCode(int actualStatusCode, StatusCode statusCode)
    {
        assertThat(actualStatusCode, equalTo(statusCode.code));
    }

    @Step
    public void errorAssertion(Error error1, StatusCode statusCode)
    {
        assertThat(error1.getError().getStatus(), equalTo(statusCode.code));
        assertThat(error1.getError().getMessage(), equalTo(statusCode.msg));
    }

}
