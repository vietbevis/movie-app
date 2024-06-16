package com.vietbevis.movies.repositories;

import com.vietbevis.movies.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);

    Category findBySlug(String slug);
}
