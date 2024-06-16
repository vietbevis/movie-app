package com.vietbevis.movies.responses.director;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DirectorResponse {
    private Long id;
    private String name;
}
