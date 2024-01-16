# MusicPlayerPro

MusicPlayerPro is a JavaFX and CSS-based music player project developed for academic purposes. 
The program allows users to listen to music from a pre-loaded database of tracks and project resources. 
It encompasses all essential features of a music player, such as playing selected songs, shuffling, playing the next track, and seeking to a specific second of a song.

<hr>

## Presentation
Check out the project in action on [YouTube](https://youtu.be/skL0QiSQOU4).

<hr>

## Features
- **Playback Control:** Listen to selected songs, shuffle mode, play the next track, and seek to any second of the song.
- **User Authentication:** Users need to log in, and there are three user types: regular user, premium user, and admin.
- **User Privileges:**
  - *Regular User:* Access to shuffle mode only.
  - *Premium User:* Access to shuffle mode, play selected songs, and play songs sequentially.
  - *Admin:* All privileges of regular and premium users, plus an admin panel for user management (e.g., delete users, change usernames, assign user types).

<hr>

## Getting Started
1. Clone the repository.
2. Compile and run the program using Java and JavaFX.
3. Log in using your credentials.

<hr>

## What I Learned
This project provided an opportunity to enhance my skills in Java programming, JavaFX, and CSS. 
I gained practical experience in database management, user authentication, and building a feature-rich application.

<hr>

## Catalog structure
-> src - source folder for this project.  
    -> java - contains module info and all clasess used in this program.  
    -> resources - hibernate config (include all entities class).
        -> musicplayerpro - fxml views (scene builder), CSS files for xml views .
        -> images - images and icons used in project.
        -> albums - albums images used in project.
        -> songs - songs .mp3 file used in project.

<hr>

## Dependencies
- Java 17 or higher
- Java Development Kit 17
- JavaFX
- Javafx-media
- Hibernate 6.2.13.Final
- Mysql-connector 8.2.0
- Jakarta 3.1.0
- Jbcrypt 0.4

<hr>

## Contributing
Feel free to contribute to the project by submitting issues or pull requests.

## License
This project is licensed under the [MIT License](LICENSE).


