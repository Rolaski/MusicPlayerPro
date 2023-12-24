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
    @Column(name = "musician")
    private String musician;
    @Column(name = "length")
    private java.time.LocalTime songLength;

    @Column(name = "path")
    private String path;
    @Column(name = "albumPath")
    private String albumPath;

    public Song() {
    }

    public Song(int id, String track, String musician, LocalTime songLength, String path, String albumPath) {
        this.id = id;
        this.track = track;
        this.musician = musician;
        this.songLength = songLength;
        this.path = path;
        this.albumPath = albumPath;
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


    public String getMusician() {
        return musician;
    }

    public LocalTime getSongLength() {
        return songLength;
    }

    public String getPath() {
        return path;
    }

}