package com.vietbevis.movies.responses.actor;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActorListResponse {
    List<ActorResponse> actors;
}
