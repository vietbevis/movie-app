package com.vietbevis.movies.mapper;

import com.vietbevis.movies.models.Actor;
import com.vietbevis.movies.responses.actor.ActorResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ActorMapper {

    ActorResponse toActorResponse(Actor actor);

    List<ActorResponse> toActorListResponse(List<Actor> actors);
}
