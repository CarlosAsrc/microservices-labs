-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Tempo de geração: 16-Jun-2019 às 05:59
-- Versão do servidor: 10.3.15-MariaDB
-- versão do PHP: 7.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `song_service`
--
CREATE DATABASE IF NOT EXISTS `song_service` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `song_service`;

-- --------------------------------------------------------

--
-- Estrutura da tabela `song`
--

CREATE TABLE `song` (
  `id` int(11) NOT NULL,
  `title` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `song`
--

INSERT INTO `song` (`id`, `title`) VALUES
(1, 'Are You Ready'),
(2, 'The Game'),
(3, 'Decadence'),
(4, 'The Animal'),
(5, 'Indestructible'),
(6, 'The Vengeful One'),
(7, 'Inside the Fire'),
(8, 'Warrior'),
(9, 'Ten Thounsand Fists'),
(10, 'Immortalized'),
(11, 'Land of Confusion'),
(12, 'Open Your Eyes'),
(13, 'Stupefy'),
(14, 'The Night'),
(15, 'Sons of Plunder'),
(16, 'This Moment'),
(17, 'God of the Mind'),
(18, 'Façade'),
(19, 'Monster'),
(20, 'Run');

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `song`
--
ALTER TABLE `song`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `song`
--
ALTER TABLE `song`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
