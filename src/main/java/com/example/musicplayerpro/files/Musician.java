package com.example.musicplayerpro.files;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "musicians")
public class Musician
{
    @Id
    @Column(name = "idMusician")
    private int idMusician;

    @Column(name = "musicianName")
    private String musicianName;

    @OneToMany(mappedBy = "musician")
    private List<Song> song;

    public Musician() {
    }

    public int getIdMusician() {
        return idMusician;
    }

    public void setIdMusician(int idMusician) {
        this.idMusician = idMusician;
    }

    public String getMusicianName() {
        return musicianName;
    }

    public void setMusicianName(String musicianName) {
        this.musicianName = musicianName;
    }
}
