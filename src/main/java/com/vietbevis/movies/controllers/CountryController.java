package com.vietbevis.movies.controllers;

import com.vietbevis.movies.dtos.CountryDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/countries")
public class CountryController {
    @GetMapping("")
    public String getCountries() {
        return "countries";
    }

    @PostMapping("")
    public ResponseEntity<String> createCountry(@Valid @RequestBody CountryDTO countryDTO) {
        return ResponseEntity.ok("Create country successfully: " + countryDTO);
    }

    @GetMapping("/{id}")
    public String getCountry(@Valid @PathVariable("id") Long id) {
        return "Country with id: " + id;
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCountry(@PathVariable("id") Long id, @Valid @RequestBody CountryDTO countryDTO) {
        return ResponseEntity.ok("Country with id: " + id + " updated " + countryDTO);
    }
}
