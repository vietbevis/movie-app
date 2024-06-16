package com.vietbevis.movies.responses.actor;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActorResponse {
    private Long id;
    private String name;
}
