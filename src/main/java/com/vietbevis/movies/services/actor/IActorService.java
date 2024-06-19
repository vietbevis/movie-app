package com.vietbevis.movies.services.actor;

import com.vietbevis.movies.dtos.ActorDTO;
import com.vietbevis.movies.responses.actor.ActorListResponse;
import com.vietbevis.movies.responses.actor.ActorResponse;

public interface IActorService {
    ActorResponse getActorById(String id);

    ActorListResponse getAllActors();

    ActorResponse getActorByName(String name);

    ActorResponse createActor(ActorDTO actorDTO);

    ActorResponse updateActor(String id, ActorDTO actorDTO);

    void deleteActor(String id);
}
