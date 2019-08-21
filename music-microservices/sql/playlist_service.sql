-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Tempo de geração: 16-Jun-2019 às 05:58
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
-- Banco de dados: `playlist_service`
--
CREATE DATABASE IF NOT EXISTS `playlist_service` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `playlist_service`;

-- --------------------------------------------------------

--
-- Estrutura da tabela `playlist`
--

CREATE TABLE `playlist` (
  `id_song` int(11) NOT NULL,
  `id_playlist` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `playlist`
--

INSERT INTO `playlist` (`id_song`, `id_playlist`) VALUES
(1, 3),
(1, 5),
(1, 17),
(1, 20),
(2, 4),
(2, 7),
(2, 9),
(3, 8),
(3, 14);

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `playlist`
--
ALTER TABLE `playlist`
  ADD UNIQUE KEY `id_song` (`id_song`,`id_playlist`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
