package com.vietbevis.movies.services.actor;

import com.vietbevis.movies.dtos.ActorDTO;
import com.vietbevis.movies.exceptions.commons.DataExistsException;
import com.vietbevis.movies.exceptions.commons.DataNotFoundException;
import com.vietbevis.movies.mapper.ActorMapper;
import com.vietbevis.movies.models.Actor;
import com.vietbevis.movies.repositories.ActorRepository;
import com.vietbevis.movies.responses.actor.ActorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActorService {
    private final ActorRepository actorRepository;
    private final ActorMapper actorMapper;

    public ActorResponse getActorById(String id) {
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Actor not found with id: " + id));
        return actorMapper.toActorResponse(actor);
    }

    public List<ActorResponse> getAllActors() {
        List<Actor> actors = actorRepository.findAll();
        return actorMapper.toActorListResponse(actors);
    }

    public ActorResponse getActorByName(String name) {
        Actor actor = actorRepository.findByName(name);
        return actorMapper.toActorResponse(actor);
    }

    public ActorResponse createActor(ActorDTO actorDTO) {
        ActorResponse actorExists = getActorByName(actorDTO.getName());
        if (actorExists != null) {
            throw new DataExistsException("Actor already exists");
        }
        Actor actor = Actor.builder()
                .name(actorDTO.getName())
                .build();
        Actor newActor = actorRepository.save(actor);
        return actorMapper.toActorResponse(newActor);
    }

    public ActorResponse updateActor(String id, ActorDTO actorDTO) {
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Actor not found with id: " + id));
        ActorResponse actorExists = getActorByName(actorDTO.getName());
        if (actorExists != null) {
            throw new DataExistsException("Actor already exists");
        }
        actor.setName(actorDTO.getName());
        Actor newActor = actorRepository.save(actor);
        return actorMapper.toActorResponse(newActor);
    }

    public void deleteActor(String id) {
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Actor not found with id: " + id));
        actorRepository.deleteById(id);
    }
}
