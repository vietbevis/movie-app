package com.vietbevis.movies.controllers;

import com.vietbevis.movies.dtos.CountryDTO;
import com.vietbevis.movies.responses.ResponseObject;
import com.vietbevis.movies.responses.country.CountryListResponse;
import com.vietbevis.movies.responses.country.CountryResponse;
import com.vietbevis.movies.services.country.CountryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/countries")
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;

    @GetMapping("")
    public ResponseEntity<?> getCountries() {
        List<CountryResponse> countries = countryService.getAllCountries();
        CountryListResponse countryListResponse = CountryListResponse.builder()
                .countries(countries).build();
        return ResponseObject.getBuilder()
                .setMessage("Get all countries successfully")
                .setData(countryListResponse)
                .build();
    }

    @PostMapping("")
    public ResponseEntity<?> createCountry(@Valid @RequestBody CountryDTO countryDTO) {
        CountryResponse countryResponse = countryService.createCountry(countryDTO);
        return ResponseObject.getBuilder()
                .setMessage("Create country successfully")
                .setData(countryResponse)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCountry(@Valid @PathVariable("id") Long id) {
        CountryResponse countryResponse = countryService.getCountryById(id);
        return ResponseObject.getBuilder()
                .setMessage("Get country successfully")
                .setData(countryResponse)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCountry(@PathVariable("id") Long id, @Valid @RequestBody CountryDTO countryDTO) {
        CountryResponse countryResponse = countryService.updateCountry(id, countryDTO);
        return ResponseObject.getBuilder()
                .setMessage("Update country successfully")
                .setData(countryResponse)
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCountry(@PathVariable("id") Long id) {
        countryService.deleteCountry(id);
        return ResponseObject.getBuilder()
                .setMessage("Delete country successfully")
                .build();
    }
}
