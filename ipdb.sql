-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Feb 27, 2015 at 04:08 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `ipdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `dogadjaj`
--

CREATE TABLE IF NOT EXISTS `dogadjaj` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(32) NOT NULL,
  `mesto` varchar(32) NOT NULL,
  `vremeOdrzavanja` datetime NOT NULL,
  `opis` text NOT NULL,
  `datoteka` text NOT NULL,
  `kategorije` varchar(50) NOT NULL,
  `prodate` varchar(50) NOT NULL,
  `rezervisane` varchar(50) NOT NULL,
  `maxUl` int(11) NOT NULL,
  `istekao` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=13 ;

--
-- Dumping data for table `dogadjaj`
--

INSERT INTO `dogadjaj` (`ID`, `naziv`, `mesto`, `vremeOdrzavanja`, `opis`, `datoteka`, `kategorije`, `prodate`, `rezervisane`, `maxUl`, `istekao`) VALUES
(1, 'prestava A', 'Dom kulture', '2015-02-18 00:00:00', 'opis', 'pozoriste.jpg', '5/6', '6/25', '9/25', 920, 1),
(2, 'prestava Istekla', 'Dom kulture', '2015-01-21 00:00:00', 'opis', 'datoteka', '5/6', '200/0', '0/0', 920, 1),
(3, 'kosarka', 'Pionir', '2015-06-26 00:00:00', 'KK Partizan - Mega Leks, 20 kolo ABA lige', 'kosarka.jpg', '2/3/4', '2/0/0', '2/0/0', 7200, 0),
(4, 'kosarka', 'Pionir', '2015-03-19 00:00:00', 'Crvena Zvezda - Zadar', 'kosarka.jpg', '3/4', '2400/3500', '0/0', 5900, 0),
(5, 'koncert', 'Arena', '2015-04-09 20:00:00', 'koncert Zdravka Colica', 'koncert.png', '12/13/15/16', '15/7/5/7', '5/10/5/9', 27230, 0),
(6, 'tenis', 'Arena', '2015-01-14 13:00:00', 'Djokovic - Nadal', '', '14/15/16', '0/0/0', '54/71/366', 19080, 1),
(8, 'kosarka', 'Pionir\r\n', '2015-03-20 20:45:00', 'KKPartizan - MegaLeks', 'kosarka.jpg', '2/3/4', '0/0/0', '0/0/0', 7200, 0),
(9, 'kosarka', 'Arena\r\n', '2015-04-10 15:00:00', 'KKCrvena Zvezda - Zlatokosa Uzice', 'kosarka.jpg', '14/15/16', '0/0/0', '0/0/0', 19080, 0),
(10, 'koncert', 'Arena\r\n', '2015-07-03 21:00:00', 'rock koncert AC/DC', 'koncert.png', '12/13/15/16', '0/0/0/0', '0/0/0/0', 27230, 0),
(11, 'film', 'SavaCentar\r\n', '2015-04-10 19:00:00', 'Bicemo prvaci Evrope', 'bioskop.jpg', '7/8/9/10/11', '0/0/0/0/0', '2/0/0/0/0', 3470, 0),
(12, 'koncert', 'SavaCentar\r\n', '2015-06-23 20:15:00', 'Miroslav Ilic', 'koncert.png', '7/8/9/10', '0/0/0/0', '0/0/0/0', 3150, 0);

-- --------------------------------------------------------

--
-- Table structure for table `kategorije`
--

CREATE TABLE IF NOT EXISTS `kategorije` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `mesto` varchar(32) NOT NULL,
  `tipSedista` varchar(32) NOT NULL,
  `maxUl` int(11) NOT NULL,
  `cenaUl` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=21 ;

--
-- Dumping data for table `kategorije`
--

INSERT INTO `kategorije` (`ID`, `mesto`, `tipSedista`, `maxUl`, `cenaUl`) VALUES
(1, 'Pionir', 'stajanje', 5000, 700),
(2, 'Pionir', 'parter', 1300, 1500),
(3, 'Pionir', 'plava loza', 2400, 2400),
(4, 'Pionir', 'tribine', 3500, 900),
(5, 'Dom kulture', 'parter', 800, 600),
(6, 'Dom kulture', 'loza', 120, 1500),
(7, 'SavaCentar', 'parter levo/desno', 400, 2500),
(8, 'SavaCentar', 'parter centar', 350, 2800),
(9, 'SavaCentar', 'parter nazad', 1200, 2000),
(10, 'SavaCentar', 'balkon', 1200, 1700),
(11, 'SavaCentar', 'loza', 320, 4000),
(12, 'Arena', 'fan pit', 230, 5000),
(13, 'Arena', 'teren stajanje', 8000, 1000),
(14, 'Arena', 'prvi red', 80, 8000),
(15, 'Arena', 'tribine nivo 200', 10000, 2000),
(16, 'Arena', 'tribine nivo 200', 9000, 1000),
(17, 'Pionir\r\n', 'novo', 200, 1200),
(19, 'Pionir', 'izBaze', 100, 100),
(20, 'Pionir', 'novo2', 100, 100);

-- --------------------------------------------------------

--
-- Table structure for table `korisnik`
--

CREATE TABLE IF NOT EXISTS `korisnik` (
  `korisnickoIme` varchar(32) NOT NULL,
  `lozinka` varchar(32) NOT NULL,
  `tip` enum('korisnik','blagajnik','admin') NOT NULL DEFAULT 'korisnik',
  `sifraMesta` int(11) NOT NULL DEFAULT '0',
  `ime` varchar(32) NOT NULL,
  `prezime` varchar(32) NOT NULL,
  `adresa` text NOT NULL,
  `telefon` varchar(32) NOT NULL,
  `ePosta` varchar(50) NOT NULL,
  `istekleRez` int(11) NOT NULL DEFAULT '0',
  `blokiran` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`korisnickoIme`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `korisnik`
--

INSERT INTO `korisnik` (`korisnickoIme`, `lozinka`, `tip`, `sifraMesta`, `ime`, `prezime`, `adresa`, `telefon`, `ePosta`, `istekleRez`, `blokiran`) VALUES
('admin', 'password', 'admin', 0, 'Nikola', 'Gajovic', 'Mirijevska 44, Uzice', '0991231234', 'admin@gamil.com', 0, 0),
('blagajnik', 'password', 'blagajnik', 1, 'Marko', 'Markovic', 'Sarajevska 13, Beograd', '0111231231', 'blagajnik@hotmail.com', 0, 0),
('blagajnikArena', 'password', 'blagajnik', 5, 'Marko', 'Drndarevic', '', '', '', 0, 0),
('blagajnikSC', 'password', 'blagajnik', 4, 'Nenad', 'Damjanovic', '', '', '', 0, 0),
('ja', 'password', 'korisnik', 0, 'Predrag', 'Popov', 'Jerkovic 32, Beograd', '0112323456', 'ja@hotmail.com', 3, 1),
('korisnik', 'password', 'korisnik', 0, 'Predrag', 'Popov', 'Brace Jerkovic 43, Beograd', '0641234567', 'nesto@gmail.com', 0, 0),
('korisnik2', 'password', 'korisnik', 0, 'Blokiran', 'Blokiran', '', '', '', 0, 1),
('proba', 'password', 'korisnik', 0, 'Mikajlo', 'Zivkovic', 'Pariska 10, Beograd', '0112342345', 'proba@gmail.com', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `radnomesto`
--

CREATE TABLE IF NOT EXISTS `radnomesto` (
  `sifraMesta` int(11) NOT NULL AUTO_INCREMENT,
  `mesto` varchar(32) NOT NULL,
  `adresa` text NOT NULL,
  `grad` varchar(32) NOT NULL,
  PRIMARY KEY (`sifraMesta`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `radnomesto`
--

INSERT INTO `radnomesto` (`sifraMesta`, `mesto`, `adresa`, `grad`) VALUES
(1, 'Pionir', '', 'Beograd'),
(2, 'Fudbalski stadion Partizan', '', 'Beograd'),
(3, 'Dom kulture', '', 'Uzice'),
(4, 'SavaCentar', '', 'Beograd'),
(5, 'Arena', '', 'Beograd'),
(8, '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `registracija`
--

CREATE TABLE IF NOT EXISTS `registracija` (
  `korisnickoIme` varchar(32) NOT NULL,
  `lozinka` varchar(32) NOT NULL,
  `tip` varchar(32) NOT NULL,
  `ime` varchar(32) NOT NULL,
  `prezime` varchar(32) NOT NULL,
  `adresa` text NOT NULL,
  `telefon` varchar(32) NOT NULL,
  `ePosta` varchar(50) NOT NULL,
  PRIMARY KEY (`korisnickoIme`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `registracija`
--

INSERT INTO `registracija` (`korisnickoIme`, `lozinka`, `tip`, `ime`, `prezime`, `adresa`, `telefon`, `ePosta`) VALUES
('', '', 'korisnik', '', '', '', '', ''),
('123', '', 'korisnik', '', '', '', '', ''),
('123123', 'wwwwwww', 'korisnik', '', '', '', '1212121212123', 'nesto.gamil.com'),
('korisnikPera', 'password', 'korisnik', 'Pera', 'Peric', '', '', ''),
('wwww', '123456', 'korisnik', '', '', '', '99999999999999', 'nesto.gamil.coc');

-- --------------------------------------------------------

--
-- Table structure for table `rezervacije`
--

CREATE TABLE IF NOT EXISTS `rezervacije` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `korisnik` varchar(32) NOT NULL,
  `dogadjajID` int(11) NOT NULL,
  `dogadjajNaziv` varchar(32) NOT NULL,
  `mesto` varchar(32) NOT NULL,
  `vremeOdrzavanja` datetime NOT NULL,
  `katID` int(11) NOT NULL,
  `brUl` int(11) NOT NULL,
  `ukupnaCena` int(11) NOT NULL,
  `vremeRez` datetime NOT NULL,
  `kupljena` tinyint(1) NOT NULL DEFAULT '0',
  `istekla` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=22 ;

--
-- Dumping data for table `rezervacije`
--

INSERT INTO `rezervacije` (`ID`, `korisnik`, `dogadjajID`, `dogadjajNaziv`, `mesto`, `vremeOdrzavanja`, `katID`, `brUl`, `ukupnaCena`, `vremeRez`, `kupljena`, `istekla`) VALUES
(2, 'korisnik', 3, 'kosarka', 'Pionir', '2015-02-26 00:00:00', 4, 2, 1800, '2015-01-31 08:26:47', 1, 0),
(4, 'korisnik', 6, 'tenis', 'Arena', '2015-02-03 00:00:00', 14, 2, 16000, '2015-01-31 11:07:30', 0, 1),
(5, 'korisnik', 6, 'tenis', 'Arena', '2015-01-03 00:00:00', 14, 3, 24000, '2015-01-31 11:08:47', 0, 1),
(7, 'korisnik', 3, 'kosarka', 'Pionir', '2015-02-26 00:00:00', 4, 3, 2700, '2015-01-31 11:13:50', 1, 0),
(8, 'korisnik', 5, 'koncert', 'Arena', '2015-04-09 20:00:00', 15, 12, 24000, '2015-01-31 11:15:04', 0, 1),
(9, 'korisnik', 3, 'kosarka', 'Pionir', '2014-12-18 05:00:00', 2, 2, 3000, '2015-01-31 11:17:47', 0, 1),
(10, 'korisnik', 3, 'kosarka', 'Pionir', '2015-02-26 00:00:00', 2, 2, 3000, '2015-01-31 11:19:48', 0, 1),
(12, 'korisnik', 5, 'koncert', 'Arena', '2015-04-09 20:00:00', 15, 2, 4000, '2015-02-02 12:05:13', 1, 0),
(13, 'proba', 3, 'kosarka', 'Pionir', '2015-02-26 00:00:00', 2, 2, 3000, '2015-02-23 04:16:36', 0, 1),
(15, 'ja', 5, 'koncert', 'Arena', '2015-04-09 20:00:00', 12, 3, 15000, '2015-02-27 11:03:45', 1, 0),
(18, 'ja', 5, 'koncert', 'Arena', '2015-04-09 20:00:00', 12, 1, 5000, '2015-01-27 01:17:00', 0, 1),
(19, 'ja', 5, 'koncert', 'Arena', '2015-04-09 20:00:00', 16, 2, 2000, '2015-02-27 01:17:11', 0, 0),
(20, 'ja', 5, 'koncert', 'Arena', '2015-04-09 20:00:00', 13, 3, 3000, '2015-02-21 01:17:21', 0, 1),
(21, 'proba', 11, 'film', 'SavaCentar', '2015-04-10 19:00:00', 7, 2, 5000, '2015-02-27 03:59:50', 0, 0);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
