package com.vietbevis.movies.responses.director;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DirectorListResponse {
    List<DirectorResponse> directors;
}
