
package com.spotify.oauth2.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
//@Getter @Setter
//@Data
@Value
//In case of Value, we can remove the private access modifer for the variables
@Jacksonized
@Builder
public class Playlist {

    @JsonProperty("href")
     String href;
    @JsonProperty("limit")
     Integer limit;
    @JsonProperty("next")
     Object next;
    @JsonProperty("offset")
     Integer offset;
    @JsonProperty("previous")
     Object previous;
    @JsonProperty("total")
     Integer total;
    @JsonProperty("items")
     List<Item> items;

    Playlist(String href, Integer limit, Object next, Integer offset, Object previous, Integer total, List<Item> items) {
        this.href = href;
        this.limit = limit;
        this.next = next;
        this.offset = offset;
        this.previous = previous;
        this.total = total;
        this.items = items;
    }

    public static PlaylistBuilder builder() {
        return new PlaylistBuilder();
    }


    public static class PlaylistBuilder {
        private String href;
        private Integer limit;
        private Object next;
        private Integer offset;
        private Object previous;
        private Integer total;
        private List<Item> items;

        PlaylistBuilder() {
        }

        public PlaylistBuilder href(String href) {
            this.href = href;
            return this;
        }

        public PlaylistBuilder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

        public PlaylistBuilder next(Object next) {
            this.next = next;
            return this;
        }

        public PlaylistBuilder offset(Integer offset) {
            this.offset = offset;
            return this;
        }

        public PlaylistBuilder previous(Object previous) {
            this.previous = previous;
            return this;
        }

        public PlaylistBuilder total(Integer total) {
            this.total = total;
            return this;
        }

        public PlaylistBuilder items(List<Item> items) {
            this.items = items;
            return this;
        }

        public Playlist build() {
            return new Playlist(href, limit, next, offset, previous, total, items);
        }

        public String toString() {
            return "Playlist.PlaylistBuilder(href=" + this.href + ", limit=" + this.limit + ", next=" + this.next + ", offset=" + this.offset + ", previous=" + this.previous + ", total=" + this.total + ", items=" + this.items + ")";
        }
    }
}
