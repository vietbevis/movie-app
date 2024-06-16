package com.vietbevis.movies.repositories;

import com.vietbevis.movies.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
