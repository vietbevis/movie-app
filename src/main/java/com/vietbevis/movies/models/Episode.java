package com.vietbevis.movies.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "episodes")
public class Episode {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name = "name")
    private String name;

    @Size(max = 255)
    @Column(name = "slug")
    private String slug;

    @Size(max = 255)
    @Column(name = "file_name")
    private String fileName;

    @Size(max = 255)
    @Column(name = "link_embed")
    private String linkEmbed;

    @Size(max = 255)
    @Column(name = "link_m3u8")
    private String linkM3u8;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "server_id")
    private Server server;

}