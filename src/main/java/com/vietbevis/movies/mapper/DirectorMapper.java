package com.vietbevis.movies.mapper;

import com.vietbevis.movies.models.Director;
import com.vietbevis.movies.responses.director.DirectorResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DirectorMapper {

    DirectorResponse toDirectorResponse(Director director);

    List<DirectorResponse> toDirectorListResponse(List<Director> directors);
}
