package com.vietbevis.movies.services.actor;

import com.vietbevis.movies.dtos.ActorDTO;
import com.vietbevis.movies.responses.actor.ActorListResponse;
import com.vietbevis.movies.responses.actor.ActorResponse;

public interface IActorService {
    ActorResponse getActorById(Long id);

    ActorListResponse getAllActors();

    ActorResponse getActorByName(String name);

    ActorResponse createActor(ActorDTO actorDTO);

    ActorResponse updateActor(Long id, ActorDTO actorDTO);

    void deleteActor(Long id);
}
