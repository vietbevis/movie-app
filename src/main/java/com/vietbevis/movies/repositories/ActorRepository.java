package com.vietbevis.movies.repositories;

import com.vietbevis.movies.models.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Long> {
    Actor findByName(String name);
}
