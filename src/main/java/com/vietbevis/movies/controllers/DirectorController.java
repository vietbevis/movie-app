package com.vietbevis.movies.controllers;

import com.vietbevis.movies.dtos.DirectorDTO;
import com.vietbevis.movies.responses.ResponseObject;
import com.vietbevis.movies.responses.director.DirectorListResponse;
import com.vietbevis.movies.responses.director.DirectorResponse;
import com.vietbevis.movies.services.director.DirectorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/directors")
@RequiredArgsConstructor
public class DirectorController {
    private final DirectorService directorService;

    @GetMapping("")
    public ResponseEntity<?> getDirectors() {
        List<DirectorResponse> directors = directorService.getAllDirectors();
        DirectorListResponse directorListResponse = DirectorListResponse.builder()
                .directors(directors).build();
        return ResponseObject.getBuilder()
                .setMessage("Get all directors successfully")
                .setData(directorListResponse)
                .build();
    }

    @PostMapping("")
    public ResponseEntity<?> createDirector(@Valid @RequestBody DirectorDTO directorDTO) {
        DirectorResponse directorResponse = directorService.createDirector(directorDTO);
        return ResponseObject.getBuilder()
                .setMessage("Director created successfully")
                .setData(directorResponse)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDirector(@Valid @PathVariable("id") Long id) {
        DirectorResponse directorResponse = directorService.getDirectorById(id);
        return ResponseObject.getBuilder()
                .setMessage("Get director successfully")
                .setData(directorResponse)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDirector(@PathVariable("id") Long id, @Valid @RequestBody DirectorDTO directorDTO) {
        DirectorResponse directorResponse = directorService.updateDirector(id, directorDTO);
        return ResponseObject.getBuilder()
                .setMessage("Update director successfully")
                .setData(directorResponse)
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDirector(@PathVariable("id") Long id) {
        directorService.deleteDirector(id);
        return ResponseObject.getBuilder()
                .setMessage("Delete director successfully")
                .build();
    }
}
