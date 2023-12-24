-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 22 Gru 2023, 19:38
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
(1, 'Italodisco', 'The Kolors', '00:03:20', 'Italodisco.mp3', 'Italodisco.mp3'),
(2, 'Afraid To Fell', 'LF SYSTEM', '00:02:57', '/project/songs/AfraidToFeel.mp3', '/project/albums/AfraidToFell.png'),
(3, 'Pump It', 'The Black Eyed Peas', '00:02:56', '/project/songs/PumpIt.mp3', '/project/albums/PumpIt.jpg'),
(4, 'Nicea', 'PRO8L3M', '00:03:38', '/project/songs/Nicea.mp3', '/project/albums/Nicea.jpg'),
(5, 'Mayday', 'PRO8L3M', '00:03:58', '/project/songs/Mayday.mp3', '/project/albums/Mayday.jpg'),
(6, 'No More Tears', 'Ozzy Osbourne', '00:05:55', '/project/songs/NoMoreTears.mp3', '/project/albums/NoMoreTears.jpg'),
(7, 'Workin!', 'MUPP x Sadfriendd', '00:01:32', '/project/songs/Workin.mp3', '/project/albums/Workin.jpg'),
(8, 'Jeremy Sochan', 'OKI', '00:02:26', '/project/songs/JeremySochan.mp3', '/project/albums/JeremySochan.jpg');

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
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
