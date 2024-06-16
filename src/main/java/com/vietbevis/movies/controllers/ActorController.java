package com.vietbevis.movies.controllers;

import com.vietbevis.movies.dtos.ActorDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/actors")
public class ActorController {
    @GetMapping("")
    public String getActors() {
        return "actors";
    }

    @PostMapping("")
    public ResponseEntity<String> createActor(@Valid @RequestBody ActorDTO actorDTO) {
        return ResponseEntity.ok("Create actor successfully: " + actorDTO);
    }

    @GetMapping("/{id}")
    public String getActor(@Valid @PathVariable("id") Long id) {
        return "Actor with id: " + id;
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateActor(@PathVariable("id") Long id, @Valid @RequestBody ActorDTO actorDTO) {
        return ResponseEntity.ok("Actor with id: " + id + " updated " + actorDTO);
    }
}
