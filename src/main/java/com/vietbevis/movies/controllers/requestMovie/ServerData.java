package com.vietbevis.movies.controllers.requestMovie;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ServerData {
    private String name;
    private String slug;
    private String filename;
    private String link_embed;
    private String link_m3u8;
}
