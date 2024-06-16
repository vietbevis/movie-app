package com.vietbevis.movies.services.director;

import com.vietbevis.movies.dtos.DirectorDTO;
import com.vietbevis.movies.exceptions.commons.DataExistsException;
import com.vietbevis.movies.exceptions.commons.DataNotFoundException;
import com.vietbevis.movies.mapper.DirectorMapper;
import com.vietbevis.movies.models.Director;
import com.vietbevis.movies.repositories.DirectorRepository;
import com.vietbevis.movies.responses.director.DirectorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DirectorService implements IDirectorService {
    private final DirectorRepository directorRepository;
    private final DirectorMapper directorMapper;

    @Override
    public DirectorResponse getDirectorById(Long id) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Director not found with id: " + id));
        return directorMapper.toDirectorResponse(director);
    }

    @Override
    public DirectorResponse getDirectorByName(String name) {
        Director director = directorRepository.findByName(name);
        return directorMapper.toDirectorResponse(director);
    }

    @Override
    public DirectorResponse createDirector(DirectorDTO directorDTO) {
        DirectorResponse directorExists = getDirectorByName(directorDTO.getName());
        if (directorExists != null) {
            throw new DataExistsException("Director already exists");
        }
        Director director = Director.builder()
                .name(directorDTO.getName())
                .build();
        Director newDirector = directorRepository.save(director);
        return directorMapper.toDirectorResponse(newDirector);
    }

    @Override
    public DirectorResponse updateDirector(Long id, DirectorDTO directorDTO) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Director not found with id: " + id));
        DirectorResponse directorExists = getDirectorByName(directorDTO.getName());
        if (directorExists != null) {
            throw new DataExistsException("Director already exists");
        }
        director.setName(directorDTO.getName());
        Director newDirector = directorRepository.save(director);
        return directorMapper.toDirectorResponse(newDirector);
    }

    @Override
    public void deleteDirector(Long id) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Director not found with id: " + id));
        directorRepository.deleteById(id);
    }

    @Override
    public List<DirectorResponse> getAllDirectors() {
        List<Director> directors = directorRepository.findAll();
        return directorMapper.toDirectorListResponse(directors);
    }
}
