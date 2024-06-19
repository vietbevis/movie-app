package com.vietbevis.movies.controllers.requestMovie;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class EpisodeDTO {
    private String server_name;
    private List<ServerData> server_data;
}
