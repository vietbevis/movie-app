package com.vietbevis.movies.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "directors")
public class Director {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "directors")
    private List<Movie> movies;

}