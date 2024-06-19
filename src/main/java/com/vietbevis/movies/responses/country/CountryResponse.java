package com.vietbevis.movies.responses.country;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CountryResponse {
    private String id;
    private String name;
    private String slug;
}
