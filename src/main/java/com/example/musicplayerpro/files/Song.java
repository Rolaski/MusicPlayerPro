package com.example.musicplayerpro.files;

import jakarta.persistence.*;
import java.time.LocalTime;

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
    public void setAlbumPath()
    {
        this.albumPath = albumPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public String getMusician() {
        return musician;
    }

    public void setMusician(String musician) {
        this.musician = musician;
    }

    public LocalTime getSongLength() {
        return songLength;
    }

    public void setSongLength(LocalTime songLength) {
        this.songLength = songLength;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}