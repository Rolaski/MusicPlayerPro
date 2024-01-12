package com.example.musicplayerpro.files;

import jakarta.persistence.*;
import java.time.LocalTime;

//entity class for hibernate
@Entity
@Table(name = "songs")
public class Song
{
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "track")
    private String track;
    @Column(name = "length")
    private java.time.LocalTime songLength;
    @Column(name = "path")
    private String path;
    @Column(name = "albumPath")
    private String albumPath;

    @ManyToOne
    @JoinColumn(name = "fk_musician")
    Musician musician;

    public Song() {
    }

    public Song(int id, String track, LocalTime songLength, String path, String albumPath, Musician musician)
    {
        this.id = id;
        this.track = track;
        this.songLength = songLength;
        this.path = path;
        this.albumPath = albumPath;
        this.musician = musician;
    }

    public String getMusician() {
        return musician.getMusicianName();
    }

    public String getAlbumPath()
    {
        return albumPath;
    }

    public int getId() {
        return id;
    }

    public String getTrack() {
        return track;
    }


    public LocalTime getSongLength() {
        return songLength;
    }

    public String getPath() {
        return path;
    }




}