package com.vietbevis.movies.controllers;

import com.vietbevis.movies.dtos.DirectorDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/directors")
public class DirectorController {
    @GetMapping("")
    public String getDirectors() {
        return "directors";
    }

    @PostMapping("")
    public ResponseEntity<String> createDirector(@Valid @RequestBody DirectorDTO directorDTO) {
        return ResponseEntity.ok("Create director successfully: " + directorDTO);
    }

    @GetMapping("/{id}")
    public String getDirector(@Valid @PathVariable("id") Long id) {
        return "Director with id: " + id;
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateDirector(@PathVariable("id") Long id, @Valid @RequestBody DirectorDTO directorDTO) {
        return ResponseEntity.ok("Director with id: " + id + " updated " + directorDTO);
    }
}
