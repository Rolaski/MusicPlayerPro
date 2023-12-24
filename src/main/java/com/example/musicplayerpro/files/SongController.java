package com.example.musicplayerpro.files;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;
import java.util.Objects;

public class SongController
{

    @FXML
    private TableView<Song> table;

    @FXML
    private TableColumn<Song, String> track;

    @FXML
    private TableColumn<Song, String> musician;

    @FXML
    private TableColumn<Song, String> songLength;



    @FXML
    private Slider progressBar;
    @FXML
    private Slider volumeBar;
    @FXML
    private ImageView albumView;
    @FXML
    private Label trackView;
    @FXML
    private Label musicianView;
    @FXML
    private Label songNow;
    @FXML
    private Label songEnd;
    @FXML
    private ImageView play;
    @FXML
    private ImageView resume;
    @FXML
    private ImageView reset;
    @FXML
    private ImageView next;

    private boolean isSliderBeingDragged = false;
    static double lastVolumeValue = 25.0;
    private int currentIndex = Integer.MAX_VALUE;
    private final SessionFactory sessionFactory;
    private MediaPlayer mediaPlayer;

    //BUTTONS HOVER SECTION
    @FXML
    void playHover(MouseEvent event)
    {
        Image hoverImage = new Image(Objects.requireNonNull(getClass().getResource("/com/example/images/playHover.png")).toExternalForm());
        play.setImage(hoverImage);
    }
    @FXML
    void playDefault(MouseEvent event)
    {
        Image hoverImage = new Image(Objects.requireNonNull(getClass().getResource("/com/example/images/play.png")).toExternalForm());
        play.setImage(hoverImage);
    }
    @FXML
    void resumeHover(MouseEvent event)
    {
        Image hoverImage = new Image(Objects.requireNonNull(getClass().getResource("/com/example/images/resumeHover.png")).toExternalForm());
        resume.setImage(hoverImage);
    }
    @FXML
    void resumeDefault(MouseEvent event)
    {
        Image hoverImage = new Image(Objects.requireNonNull(getClass().getResource("/com/example/images/resume.png")).toExternalForm());
        resume.setImage(hoverImage);
    }
    @FXML
    void resetHover(MouseEvent event)
    {
        Image hoverImage = new Image(Objects.requireNonNull(getClass().getResource("/com/example/images/restartHover.png")).toExternalForm());
        reset.setImage(hoverImage);
    }
    @FXML
    void resetDefault(MouseEvent event)
    {
        Image hoverImage = new Image(Objects.requireNonNull(getClass().getResource("/com/example/images/reset.png")).toExternalForm());
        reset.setImage(hoverImage);
    }
    @FXML
    void nextHover(MouseEvent event)
    {
        Image hoverImage = new Image(Objects.requireNonNull(getClass().getResource("/com/example/images/nextHover.png")).toExternalForm());
        next.setImage(hoverImage);
    }
    @FXML
    void nextDefault(MouseEvent event)
    {
        Image hoverImage = new Image(Objects.requireNonNull(getClass().getResource("/com/example/images/next.png")).toExternalForm());
        next.setImage(hoverImage);
    }



    //song resume
    @FXML
    private void playButtonClicked()
    {
        if (mediaPlayer != null)
        {
            mediaPlayer.play();
        }
    }
    //song pause
    @FXML
    private void pauseButtonClicked()
    {
        if (mediaPlayer != null)
        {
            mediaPlayer.pause();
        }
    }
    //song reset -> play again
    @FXML
    private void resetButtonClicked()
    {
        if (mediaPlayer != null)
        {
            mediaPlayer.seek(Duration.ZERO);
        }
    }
    //play next song from list
    @FXML
    private void nextButtonClicked()
    {
        if (currentIndex == Integer.MAX_VALUE)
        {
            Song currentSong = table.getSelectionModel().getSelectedItem();
            currentIndex = table.getItems().indexOf(currentSong);
        }

        // Odtwórz następny utwór (jeśli istnieje)
        if (currentIndex < table.getItems().size() - 1) {
            currentIndex++;
            Song nextSong = table.getItems().get(currentIndex);
            playSong(nextSong);
        }
        else
        {
            currentIndex = -1;
            nextButtonClicked();
        }
    }


    public SongController()
    {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @FXML
    private void initialize()
    {
        track.setCellValueFactory(new PropertyValueFactory<>("track"));
        musician.setCellValueFactory(new PropertyValueFactory<>("musician"));
        songLength.setCellValueFactory(new PropertyValueFactory<>("songLength"));

        track.setSortable(false);
        musician.setSortable(false);
        songLength.setSortable(false);
        loadSongs();

        volumeBar.setValue(lastVolumeValue);
        volumeBar.valueProperty().addListener(observable ->
        {
            lastVolumeValue = volumeBar.getValue();
            if (mediaPlayer != null)
            {
                mediaPlayer.setVolume(lastVolumeValue / 100);
            }
        });

        //Play song which you clicked
        table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
        {
            stopSong();
            if (newValue != null)
            {
                playSong(newValue);
            }
        });
    }

    //load all songs from database, using entity of course
    private void loadSongs()
    {
        try (Session session = sessionFactory.openSession())
        {
            List<Song> songs = session.createQuery("FROM Song", Song.class).list();
            table.getItems().addAll(songs);
        }
    }
    //stop playing song
    public void stopSong()
    {
        if (mediaPlayer != null)
        {
            mediaPlayer.stop();
            mediaPlayer.dispose();
        }
    }


    //update songNow -> current track time
    private void updateSongNowLabel()
    {
        Duration currentTime = mediaPlayer.getCurrentTime();
        songNow.setText(formatDuration(currentTime));
    }

    //songNow set to 00:00, and songEnd set to song length
    private void updateLabels()
    {
        Duration currentTime = mediaPlayer.getCurrentTime();
        Duration totalDuration = mediaPlayer.getTotalDuration();

        songNow.setText(formatDuration(currentTime));
        songEnd.setText(formatDuration(totalDuration));
    }

    //format change to minutes:seconds
    private String formatDuration(Duration duration)
    {
        int minutes = (int) duration.toMinutes();
        int seconds = (int) (duration.toSeconds() % 60);
        return String.format("%02d:%02d", minutes, seconds);
    }

    //actually this method playing songs and using other methods
    private void playSong(Song song)
    {
        stopSong();
        try
        {
            //MP3 file opening
            String trackPath = String.valueOf(getClass().getResource(song.getPath()));
            if (trackPath != null)
            {
                Media media = new Media(trackPath);
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setVolume(lastVolumeValue / 100);
            } else
            {
                System.out.println("I CANNOT FIND IMAGE FOR: " + song.getTrack());
            }

            //IMAGE file opening
            String albumImagePath = String.valueOf(getClass().getResource(song.getAlbumPath()));
            if (albumImagePath != null) {
                Image albumImage = new Image(albumImagePath);
                albumView.setImage(albumImage);
                trackView.setText(song.getTrack());
                musicianView.setText(song.getMusician());
            }
            else
            {
                System.out.println("I CANNOT FIND IMAGE FOR: " + song.getTrack());
            }

            //next song after current song end
            mediaPlayer.setOnEndOfMedia(() ->
            {
                nextButtonClicked();
            });

            mediaPlayer.play();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }



        //SLIDER, DURATION SECTION
        //setting initial values
        mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>()
        {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue)
            {
                if(!isSliderBeingDragged)
                {
                    double progress = newValue.toSeconds() / mediaPlayer.getTotalDuration().toSeconds();
                    progressBar.setValue(progress * progressBar.getMax());
                    updateLabels();
                }
            }
        });
        //press on slider
        progressBar.setOnMousePressed(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                isSliderBeingDragged=true;
                mediaPlayer.seek(Duration.seconds(progressBar.getValue()* mediaPlayer.getTotalDuration().toSeconds() / progressBar.getMax()));
            }
        });
        //slider dragging, without letting go!
        progressBar.setOnMouseDragged(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                mediaPlayer.pause();
                mediaPlayer.seek(Duration.seconds(progressBar.getValue() * mediaPlayer.getTotalDuration().toSeconds() / progressBar.getMax()));
                updateSongNowLabel();
            }
        });
        //released slider, simply this circle
        progressBar.setOnMouseReleased(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                isSliderBeingDragged = false;
                playButtonClicked();
            }
        });

    }
}

