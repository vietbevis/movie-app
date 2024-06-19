package com.vietbevis.movies.services.director;

import com.vietbevis.movies.dtos.DirectorDTO;
import com.vietbevis.movies.responses.director.DirectorResponse;

import java.util.List;

public interface IDirectorService {
    DirectorResponse getDirectorById(String id);

    DirectorResponse getDirectorByName(String name);

    DirectorResponse createDirector(DirectorDTO directorDTO);

    DirectorResponse updateDirector(String id, DirectorDTO directorDTO);

    void deleteDirector(String id);

    List<DirectorResponse> getAllDirectors();
}
