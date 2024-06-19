package com.vietbevis.movies.controllers;

import com.vietbevis.movies.dtos.ActorDTO;
import com.vietbevis.movies.models.Actor;
import com.vietbevis.movies.responses.ResponseObject;
import com.vietbevis.movies.responses.actor.ActorListResponse;
import com.vietbevis.movies.responses.actor.ActorResponse;
import com.vietbevis.movies.services.actor.ActorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/actors")
@RequiredArgsConstructor
public class ActorController {
    private final ActorService actorService;

    @GetMapping("")
    public ResponseEntity<?> getActors() {
        List<ActorResponse> actors = actorService.getAllActors();
        ActorListResponse actorListResponse = ActorListResponse.builder()
                .actors(actors).build();
        return ResponseObject.getBuilder()
                .setMessage("Get all actors successfully")
                .setData(actorListResponse)
                .build();
    }

    @PostMapping("")
    public ResponseEntity<?> createActor(@Valid @RequestBody ActorDTO actorDTO) {
        ActorResponse actorResponse = actorService.createActor(actorDTO);
        return ResponseObject.getBuilder()
                .setMessage("Actor created successfully")
                .setData(actorResponse)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getActor(@Valid @PathVariable("id") String id) {
        ActorResponse actorResponse = actorService.getActorById(id);
        return ResponseObject.getBuilder()
                .setMessage("Get actor successfully")
                .setData(actorResponse)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateActor(@PathVariable("id") String id, @Valid @RequestBody ActorDTO actorDTO) {
        ActorResponse actorResponse = actorService.updateActor(id, actorDTO);
        return ResponseObject.getBuilder()
                .setMessage("Update actor successfully")
                .setData(actorResponse)
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteActor(@PathVariable("id") String id) {
        actorService.deleteActor(id);
        return ResponseObject.getBuilder()
                .setMessage("Delete actor successfully")
                .build();
    }
}
