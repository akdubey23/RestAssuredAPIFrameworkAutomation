
package com.spotify.oauth2.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Value
//@Data
//@Getter @Setter
@Jacksonized
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)



//@Data
public class Item {

    @JsonProperty("collaborative")
     Boolean collaborative;
    @JsonProperty("description")
     String description;
    @JsonProperty("external_urls")
     ExternalUrls externalUrls;
    @JsonProperty("href")
     String href;
    @JsonProperty("id")
     String id;
    @JsonProperty("images")
     Object images;
    @JsonProperty("name")
     String name;
    @JsonProperty("owner")
     Owner owner;
    @JsonProperty("primary_color")
     Object primaryColor;
    @JsonProperty("public")
     Boolean _public;
    @JsonProperty("snapshot_id")
     String snapshotId;
    @JsonProperty("tracks")
     Tracks tracks;
    @JsonProperty("type")
     String type;
    @JsonProperty("uri")
     String uri;

}
