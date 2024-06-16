package com.vietbevis.movies.mapper;

import com.vietbevis.movies.models.Country;
import com.vietbevis.movies.responses.country.CountryResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    CountryResponse toCountryResponse(Country country);

    List<CountryResponse> toCountryResponseList(List<Country> countries);
}
