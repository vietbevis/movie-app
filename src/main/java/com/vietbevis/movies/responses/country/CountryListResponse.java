package com.vietbevis.movies.responses.country;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CountryListResponse {
    List<CountryResponse> countries;
}
