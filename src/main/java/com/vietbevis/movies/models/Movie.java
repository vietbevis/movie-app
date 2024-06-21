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
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "slug", nullable = false)
    private String slug;

    @Column(name = "origin_name", nullable = false)
    private String originName;

    @Column(name = "type")
    private String type;

    @Column(name = "status")
    private String status;

    @Column(name = "content", columnDefinition = "LONGTEXT")
    private String content;

    @Column(name = "thumb_url")
    private String thumbUrl;

    @Size(max = 255)
    @Column(name = "poster_url")
    private String posterUrl;

    @Column(name = "trailer_url")
    private String trailerUrl;

    @Column(name = "episode_current")
    private String episodeCurrent;

    @Column(name = "episode_total")
    private String episodeTotal;

    @Column(name = "quality")
    private String quality;

    @Column(name = "lang")
    private String lang;

    @Column(name = "year")
    private Integer year;

    @Column(name = "view")
    private Integer view;

    @Column(name = "time")
    private String time;

    @Column(name = "show_times")
    private String showTimes;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @Column(name = "episodes", columnDefinition = "LONGTEXT")
    private String episodes;

    @ManyToMany
    @JoinTable(
            name = "movie_actor",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private List<Actor> actors;

    @ManyToMany
    @JoinTable(
            name = "movie_director",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "director_id")
    )
    private List<Director> directors;

    @ManyToMany
    @JoinTable(
            name = "movie_category",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    @ManyToMany
    @JoinTable(
            name = "movie_country",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id")
    )
    private List<Country> countries;


}