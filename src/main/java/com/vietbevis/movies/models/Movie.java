package com.vietbevis.movies.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 255)
    @Column(name = "slug", nullable = false)
    private String slug;

    @Size(max = 255)
    @Column(name = "origin_name", nullable = false)
    private String originName;

    @Size(max = 255)
    @Column(name = "type")
    private String type;

    @Size(max = 255)
    @Column(name = "status")
    private String status;

    @Size(max = 255)
    @Column(name = "content")
    private String content;

    @Size(max = 255)
    @Column(name = "thumb_url")
    private String thumbUrl;

    @Size(max = 255)
    @Column(name = "poster_url")
    private String posterUrl;

    @Size(max = 255)
    @Column(name = "trailer_url")
    private String trailerUrl;

    @Size(max = 255)
    @Column(name = "episode_current")
    private String episodeCurrent;

    @Size(max = 255)
    @Column(name = "episode_total")
    private String episodeTotal;

    @Size(max = 255)
    @Column(name = "quality")
    private String quality;

    @Size(max = 255)
    @Column(name = "lang")
    private String lang;

    @Column(name = "year")
    private Integer year;

    @Column(name = "view")
    private Integer view;

    @Size(max = 255)
    @Column(name = "time")
    private String time;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "movie_actor",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private List<Actor> actors;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "movie_director",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "director_id")
    )
    private List<Director> directors;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "movie_category",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "movie_country",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id")
    )
    private List<Country> countries;

}