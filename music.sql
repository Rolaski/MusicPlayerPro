-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 26 Gru 2023, 09:20
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
-- Struktura tabeli dla tabeli `songs`
--

CREATE TABLE `songs` (
  `id` int(10) UNSIGNED NOT NULL,
  `track` varchar(45) DEFAULT NULL,
  `musician` varchar(45) DEFAULT NULL,
  `length` time NOT NULL DEFAULT '00:00:00',
  `path` varchar(45) NOT NULL,
  `albumPath` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `songs`
--

INSERT INTO `songs` (`id`, `track`, `musician`, `length`, `path`, `albumPath`) VALUES
(1, 'Italodisco', 'The Kolors', '00:03:20', '/com/example/songs/Italodisco.mp3', '/com/example/albums/Italodisco.jpg'),
(2, 'Afraid To Feel', 'LF SYSTEM', '00:02:57', '/com/example/songs/AfraidToFeel.mp3', '/com/example/albums/AfraidToFeel.png'),
(3, 'Pump It', 'The Black Eyed Peas', '00:02:56', '/com/example/songs/PumpIt.mp3', '/com/example/albums/PumpIt.jpg'),
(4, 'Nicea', 'PRO8L3M', '00:03:38', '/com/example/songs/Nicea.mp3', '/com/example/albums/Nicea.jpg'),
(5, 'Mayday', 'PRO8L3M', '00:03:58', '/com/example/songs/Mayday.mp3', '/com/example/albums/Mayday.jpg'),
(6, 'No More Tears', 'Ozzy Osbourne', '00:05:55', '/com/example/songs/NoMoreTears.mp3', '/com/example/albums/NoMoreTears.jpg'),
(7, 'Workin!', 'MUPP x Sadfriendd', '00:01:32', '/com/example/songs/Workin.mp3', '/com/example/albums/Workin.jpg'),
(8, 'Jeremy Sochan', 'OKI', '00:02:26', '/com/example/songs/JeremySochan.mp3', '/com/example/albums/JeremySochan.jpg'),
(9, 'Adidas', 'Russian Village Boys & Mr. Polska', '00:02:15', '/com/example/songs/Adidas.mp3', '/com/example/albums/Adidas.jpg'),
(10, 'Bungee', 'Hotel Maffija', '00:03:18', '/com/example/songs/Bungee.mp3', '/com/example/albums/Bungee.jpg'),
(11, 'Good will hunting', 'Schafter', '00:03:01', '/com/example/songs/GoodWillHunting.mp3', '/com/example/albums/GoodWillHunting.jpg'),
(12, 'OUT WEST', 'Travis Scott & JACKBOYS', '00:02:43', '/com/example/songs/OutWest.mp3', '/com/example/albums/OutWest.jpg');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `songs`
--
ALTER TABLE `songs`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT dla zrzuconych tabel
--

--
-- AUTO_INCREMENT dla tabeli `songs`
--
ALTER TABLE `songs`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
