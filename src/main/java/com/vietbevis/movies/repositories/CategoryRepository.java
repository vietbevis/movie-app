package com.vietbevis.movies.repositories;

import com.vietbevis.movies.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, String> {
    Category findByName(String name);

    Category findBySlug(String slug);

    Category findByNameAndSlug(String name, String slug);
}
