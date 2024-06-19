package com.vietbevis.movies.repositories;

import com.vietbevis.movies.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, String> {
    Country findByName(String name);

    Country findBySlug(String slug);

    Country findByNameAndSlug(String name, String slug);
}
