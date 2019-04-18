-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 14, 2019 at 04:40 PM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kampus_app`
--

-- --------------------------------------------------------

--
-- Table structure for table `bio_mhs`
--

CREATE TABLE `bio_mhs` (
  `id_mhs` int(4) UNSIGNED NOT NULL,
  `nama_mhs` varchar(50) NOT NULL,
  `npm_mhs` int(7) NOT NULL,
  `tempat_lahir` varchar(25) NOT NULL,
  `tgl_lahir` date NOT NULL,
  `jenis_kelamin` varchar(6) NOT NULL,
  `alamat_mhs` text NOT NULL,
  `prodi_mhs` varchar(20) NOT NULL,
  `jenjang_mhs` varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bio_mhs`
--

INSERT INTO `bio_mhs` (`id_mhs`, `nama_mhs`, `npm_mhs`, `tempat_lahir`, `tgl_lahir`, `jenis_kelamin`, `alamat_mhs`, `prodi_mhs`, `jenjang_mhs`) VALUES
(2, 'Ravi Mukti', 17111247, 'Bandung', '1996-04-22', 'Pria', 'Baraja', 'Teknik Informatika', 'S1'),
(3, 'Yassy', 17111266, 'Sulawesy', '1983-12-14', 'Wanita', 'Jalan Jalan Di Sulawessee', 'Teknik Industri', 'D2'),
(4, 'Juunaedii', 17111555, 'Master Sep', '1984-06-29', 'Pria', 'Master Sep Indonesa', 'Teknik Informatika', 'S2'),
(5, 'Nguyen Hac Cuy', 17222111, 'Vientien', '1990-03-16', 'Pria', 'Some Streeet in Vietnam', 'Teknik Informatika', 'D4'),
(6, 'Yanti', 17666221, 'Jimbaran', '1973-04-11', 'Pria', 'Hotel Aston Jimbaran Balee', 'Teknik Industri', 'S2'),
(7, 'Esther', 17111645, 'Sumatra', '2018-09-18', 'Wanita', 'West Sumatra', 'DKV', 'D2'),
(8, 'Yadi', 17111888, 'Java', '2019-04-09', 'Wanita', 'Sui', 'DKV', 'S2'),
(9, 'Yanto Basna', 17111244, 'Jayapura', '1996-04-10', 'Pria', 'Bangkok Glass FC', 'DKV', 'S2'),
(10, 'Eka Ramdani', 17111666, 'Borneo FC', '1997-04-08', 'Pria', 'Nabil Husein FC', 'DKV', 'D1'),
(11, 'Hemalia', 17111289, 'Bandung', '2002-10-15', 'Wanita', 'KP. Baraja	', 'Teknik Industri', 'S2');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bio_mhs`
--
ALTER TABLE `bio_mhs`
  ADD PRIMARY KEY (`id_mhs`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bio_mhs`
--
ALTER TABLE `bio_mhs`
  MODIFY `id_mhs` int(4) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
