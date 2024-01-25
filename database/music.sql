-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 18 Sty 2024, 16:56
-- Wersja serwera: 10.4.21-MariaDB
-- Wersja PHP: 7.3.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `music`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `musicians`
--

CREATE TABLE `musicians` (
  `idMusician` int(10) UNSIGNED NOT NULL,
  `musicianName` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `musicians`
--

INSERT INTO `musicians` (`idMusician`, `musicianName`) VALUES
(1, 'The Kolors'),
(2, 'LF SYSTEM'),
(3, 'The Black Eyed Peas'),
(4, 'PRO8L3M'),
(5, 'Ozzy Osbourne'),
(6, 'MUPP'),
(7, 'OKI'),
(8, 'Russian Village Boys'),
(9, 'Hotel Maffija'),
(10, 'Schafter'),
(11, 'JACKBOYS'),
(12, 'Avi'),
(13, 'Nickelback'),
(14, 'phonk.me '),
(15, 'Coolio'),
(16, 'Mr. Polska'),
(17, 'Icona Pop'),
(18, 'Travis Scott'),
(19, 'Pitbull'),
(20, 'HELLFXRMANCE Remix');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `songs`
--

CREATE TABLE `songs` (
  `id` int(10) UNSIGNED NOT NULL,
  `track` varchar(45) DEFAULT NULL,
  `length` time NOT NULL DEFAULT '00:00:00',
  `path` varchar(45) NOT NULL,
  `albumPath` varchar(45) NOT NULL,
  `fk_musician` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `songs`
--

INSERT INTO `songs` (`id`, `track`, `length`, `path`, `albumPath`, `fk_musician`) VALUES
(1, 'Italodisco', '00:03:20', '/com/example/songs/Italodisco.mp3', '/com/example/albums/Italodisco.jpg', 1),
(2, 'Afraid To Feel', '00:02:57', '/com/example/songs/AfraidToFeel.mp3', '/com/example/albums/AfraidToFeel.png', 2),
(3, 'Pump It', '00:02:56', '/com/example/songs/PumpIt.mp3', '/com/example/albums/PumpIt.jpg', 3),
(4, 'Nicea', '00:03:38', '/com/example/songs/Nicea.mp3', '/com/example/albums/Nicea.jpg', 4),
(5, 'Mayday', '00:03:58', '/com/example/songs/Mayday.mp3', '/com/example/albums/Mayday.jpg', 4),
(6, 'No More Tears', '00:05:55', '/com/example/songs/NoMoreTears.mp3', '/com/example/albums/NoMoreTears.jpg', 5),
(7, 'Workin!', '00:01:32', '/com/example/songs/Workin.mp3', '/com/example/albums/Workin.jpg', 6),
(8, 'Jeremy Sochan', '00:02:26', '/com/example/songs/JeremySochan.mp3', '/com/example/albums/JeremySochan.jpg', 7),
(9, 'Adidas', '00:02:15', '/com/example/songs/Adidas.mp3', '/com/example/albums/Adidas.jpg', 8),
(10, 'Bungee', '00:03:18', '/com/example/songs/Bungee.mp3', '/com/example/albums/Bungee.jpg', 9),
(11, 'Good will hunting', '00:03:01', '/com/example/songs/GoodWillHunting.mp3', '/com/example/albums/GoodWillHunting.jpg', 10),
(12, 'OUT WEST', '00:02:43', '/com/example/songs/OutWest.mp3', '/com/example/albums/OutWest.jpg', 11),
(13, 'Gelenda', '00:02:38', '/com/example/songs/Gelenda.mp3', '/com/example/albums/Gelenda.jpg', 12),
(14, 'Burn It ', '00:03:29', '/com/example/songs/BurnItToTheGround.mp3', '/com/example/albums/BurnItToTheGround.jpg', 13),
(15, 'Ghost!', '00:03:51', '/com/example/songs/Ghost!.mp3', '/com/example/albums/Ghost!.jpg', 14),
(16, 'Gangsta Paradise', '00:04:01', '/com/example/songs/GangstaParadise.mp3', '/com/example/albums/GangstaParadise.jpg', 15),
(17, 'Złote Tarasy', '00:02:33', '/com/example/songs/ZloteTarasy.mp3', '/com/example/albums/ZloteTarasy.jpg', 16),
(18, 'I Love It', '00:02:35', '/com/example/songs/ILoveIt.mp3', '/com/example/albums/ILoveIt.jpg', 17),
(19, 'Highest In Room', '00:02:57', '/com/example/songs/HighestInTheRoom.mp3', '/com/example/albums/HighestInTheRoom.jpg', 18),
(20, 'We Are One', '00:03:46', '/com/example/songs/WeAreOne.mp3', '/com/example/albums/WeAreOne.jpg', 19),
(21, 'The Ketchup Song', '00:03:04', '/com/example/songs/TheKetchupSong.mp3', '/com/example/albums/TheKetchupSong.jpg', 20);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users`
--

CREATE TABLE `users` (
  `idUser` int(10) UNSIGNED NOT NULL,
  `userName` varchar(45) NOT NULL,
  `userPassword` varchar(150) NOT NULL,
  `userType` enum('admin','user','premium') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `users`
--

INSERT INTO `users` (`idUser`, `userName`, `userPassword`, `userType`) VALUES
(8, 'admin', '$2a$10$UzY4sAc.Tx0sPSzfQYfLE.tBZpLeoRBsJsPtVWgs/etACsqCLle0a', 'admin'),
(9, 'jacob', '$2a$10$Gv3zEmJHEyCfbepl7XggJOFMxmuWenaKslKDeLciZZ4s010Feg4lu', 'user'),
(10, 'kamila', '$2a$10$8cgQbnSi6/NCeFC6LWsQq.KK/Sp6nsi2t8.kPHEqo1Ekp0QNz0SMC', 'premium');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `musicians`
--
ALTER TABLE `musicians`
  ADD PRIMARY KEY (`idMusician`);

--
-- Indeksy dla tabeli `songs`
--
ALTER TABLE `songs`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_musician` (`fk_musician`);

--
-- Indeksy dla tabeli `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`idUser`);

--
-- AUTO_INCREMENT dla zrzuconych tabel
--

--
-- AUTO_INCREMENT dla tabeli `musicians`
--
ALTER TABLE `musicians`
  MODIFY `idMusician` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT dla tabeli `songs`
--
ALTER TABLE `songs`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT dla tabeli `users`
--
ALTER TABLE `users`
  MODIFY `idUser` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `songs`
--
ALTER TABLE `songs`
  ADD CONSTRAINT `songs_ibfk_1` FOREIGN KEY (`fk_musician`) REFERENCES `musicians` (`idMusician`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
