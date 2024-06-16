package com.vietbevis.movies.services.country;

import com.vietbevis.movies.dtos.CountryDTO;
import com.vietbevis.movies.responses.country.CountryResponse;

import java.util.List;

public interface ICountryService {
    CountryResponse getCountryById(Long id);

    CountryResponse getCountryByName(String name);

    CountryResponse getCountryBySlug(String slug);

    List<CountryResponse> getAllCountries();

    CountryResponse createCountry(CountryDTO countryDTO);

    CountryResponse updateCountry(Long id, CountryDTO countryDTO);

    void deleteCountry(Long id);

}
