package com.vietbevis.movies.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "actors")
public class Actor {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "actors")
    private List<Movie> movies;

}