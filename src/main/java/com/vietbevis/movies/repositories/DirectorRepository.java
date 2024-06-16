package com.vietbevis.movies.repositories;

import com.vietbevis.movies.models.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository extends JpaRepository<Director, Long> {
}
