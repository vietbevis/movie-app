package com.vietbevis.movies.services.director;

import com.vietbevis.movies.dtos.DirectorDTO;
import com.vietbevis.movies.responses.director.DirectorResponse;

import java.util.List;

public interface IDirectorService {
    DirectorResponse getDirectorById(Long id);

    DirectorResponse getDirectorByName(String name);

    DirectorResponse createDirector(DirectorDTO directorDTO);

    DirectorResponse updateDirector(Long id, DirectorDTO directorDTO);

    void deleteDirector(Long id);

    List<DirectorResponse> getAllDirectors();
}
