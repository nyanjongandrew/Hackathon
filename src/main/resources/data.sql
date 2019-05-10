-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 10, 2019 at 12:59 PM
-- Server version: 5.7.14
-- PHP Version: 7.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `movies`
--

-- --------------------------------------------------------

--
-- Table structure for table `app_user`
--

CREATE TABLE `app_user` (
  `id` varchar(36) NOT NULL,
  `firstname` varchar(15) NOT NULL,
  `lastname` varchar(15) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `app_user`
--

INSERT INTO `app_user` (`id`, `firstname`, `lastname`, `username`, `password`) VALUES
('32991f7d-6012-4f90-aacb-b5d75c378c33', 'Ken', 'Walibora', 'kwalibora', '$2a$10$ZVEqsLo4uApIyTjSWffOh.HLtbm0lvC9efHYeAc/85xBVQdOanqTy'),
('faa41524-aaab-440c-a382-5cd91d8b2f92', 'Andrew', 'O', 'anyanjong', '$2a$10$zQLgZgTC3MMQ7HRxgR83MeWGeEZAo2bXRWGsb/Cu9/kYj.Y0T53YG');

-- --------------------------------------------------------

--
-- Table structure for table `moviez`
--

CREATE TABLE `moviez` (
  `id` varchar(36) NOT NULL,
  `title` varchar(50) NOT NULL,
  `description` text NOT NULL,
  `recommendation` text NOT NULL,
  `rating` int(11) NOT NULL,
  `watched` int(11) NOT NULL DEFAULT '0',
  `reg_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(30) NOT NULL,
  `last_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_by` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `moviez`
--

INSERT INTO `moviez` (`id`, `title`, `description`, `recommendation`, `rating`, `watched`, `reg_date`, `created_by`, `last_updated`, `updated_by`) VALUES
('6bd7acd5-46ee-4d9a-8985-67322fa2eec4', 'Game of Thrones', 'About game of thrones', 'For individuals who love fighting scenes', 1, 1, '2019-05-10 12:19:08', 'ADMIN', '2019-05-10 12:19:08', 'ADMIN'),
('948c96c1-cbbc-496c-b06f-cc9a19c791d5', 'Into the Badlands', 'Action oriented movie ', 'Barons are controlling everything within and outside the walls', 3, 1, '2019-05-10 12:26:30', 'ADMIN', '2019-05-10 12:26:30', 'ADMIN'),
('f0ae72f3-d8cf-4c6a-88cf-b723c65f18cf', 'House of Cards', 'Politicians or personels with political interests', 'Political Games', 4, 0, '2019-05-10 12:25:33', 'ADMIN', '2019-05-10 12:25:33', 'ADMIN');

-- --------------------------------------------------------

--
-- Table structure for table `userlogintoken`
--

CREATE TABLE `userlogintoken` (
  `tokenId` varchar(36) NOT NULL,
  `refreshtime` varchar(30) NOT NULL,
  `token` text NOT NULL,
  `userid` varchar(36) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `app_user`
--
ALTER TABLE `app_user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `moviez`
--
ALTER TABLE `moviez`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `userlogintoken`
--
ALTER TABLE `userlogintoken`
  ADD PRIMARY KEY (`tokenId`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
