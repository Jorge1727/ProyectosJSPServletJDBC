-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 23-08-2014 a las 11:18:07
-- Versión del servidor: 5.5.38-0ubuntu0.14.04.1
-- Versión de PHP: 5.5.9-1ubuntu4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `banco`
--
CREATE DATABASE IF NOT EXISTS `banco` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `banco`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE IF NOT EXISTS `cliente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(40) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `direccion` varchar(40) COLLATE utf8_spanish2_ciDEFAULT NULL,
  `telefono` varchar(40) COLLATE utf8_spanish2_ciDEFAULT NULL,
  `fecha_de_nacimiento` DATE,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`id`, `nombre`, `direccion`, `telefono`, `fecha_de_nacimiento`) VALUES
(1235, 'Bermúdez Espada, Ana María', 'Málaga', '123456789', '1990-05-15'),
(1236, 'Cano Cuenca, Margarita', 'Málaga', '987654321', '1988-08-22'),
(1237, 'Doña Enríquez, Adrián Manuel', 'Málaga', '555555555', '1991-02-10'),
(1238, 'Fernández Padilla, Esther', 'Málaga', '777777777', '1996-11-03'),
(1239, 'Galán Bazán, Ester María', 'Málaga', '999999999', '1970-09-28'),
(1301, 'Naranjo González, David', 'Campanillas', '333333333', '2001-07-14'),
(1302, 'Robledo Salazar, Ángel Antonio', 'Campanillas', '444444444', '1999-12-01');


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;