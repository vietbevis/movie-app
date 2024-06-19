package com.vietbevis.movies.services.country;

import com.vietbevis.movies.dtos.CountryDTO;
import com.vietbevis.movies.exceptions.commons.DataExistsException;
import com.vietbevis.movies.exceptions.commons.DataNotFoundException;
import com.vietbevis.movies.mapper.CountryMapper;
import com.vietbevis.movies.models.Country;
import com.vietbevis.movies.repositories.CountryRepository;
import com.vietbevis.movies.responses.country.CountryResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryService implements ICountryService {
    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    @Override
    public CountryResponse getCountryById(String id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Country not found"));
        return countryMapper.toCountryResponse(country);
    }

    @Override
    public CountryResponse getCountryByName(String name) {
        Country country = countryRepository.findByName(name);
        return countryMapper.toCountryResponse(country);
    }

    @Override
    public CountryResponse getCountryBySlug(String slug) {
        Country country = countryRepository.findBySlug(slug);
        return countryMapper.toCountryResponse(country);
    }

    @Override
    public List<CountryResponse> getAllCountries() {
        List<Country> countries = countryRepository.findAll();
        return countryMapper.toCountryResponseList(countries);
    }

    @Override
    @Transactional
    public CountryResponse createCountry(CountryDTO countryDTO) {
        CountryResponse countryExists = getCountryByName(countryDTO.getName());
        CountryResponse countryExistsSlug = getCountryBySlug(countryDTO.getSlug());
        if (countryExists != null || countryExistsSlug != null) {
            throw new DataExistsException("Country already exists");
        }
        Country newCountry = Country.builder()
                .name(countryDTO.getName())
                .slug(countryDTO.getSlug())
                .build();
        Country country = countryRepository.save(newCountry);
        return countryMapper.toCountryResponse(country);
    }

    @Override
    public CountryResponse updateCountry(String id, CountryDTO countryDTO) {
        Country countryExisting = countryRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Country not found"));
        CountryResponse countryExistsName = getCountryByName(countryDTO.getName());
        CountryResponse countryExistsSlug = getCountryBySlug(countryDTO.getSlug());
        if (countryExistsName != null || countryExistsSlug != null) {
            throw new DataExistsException("Country already exists");
        }
        countryExisting.setName(countryDTO.getName());
        countryExisting.setSlug(countryDTO.getSlug());
        Country country = countryRepository.save(countryExisting);
        return countryMapper.toCountryResponse(country);
    }

    @Override
    public void deleteCountry(String id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Country not found"));
        countryRepository.delete(country);
    }
}
