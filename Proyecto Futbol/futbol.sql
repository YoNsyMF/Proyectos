-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 25-10-2021 a las 12:32:30
-- Versión del servidor: 10.4.21-MariaDB
-- Versión de PHP: 8.0.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `futbol`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `DAMEJUGADOR` (`id_equipo` INT(15))  SELECT * FROM jugador WHERE jugador.equipo = id_equipo$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `DAMEJUGADORESINTERNACIONALES` (`internacional` INT(15))  SELECT * FROM jugador WHERE jugador.ha_sido_internacional = internacional$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `DAMEJUGADORMISMOREPRESENTANTE` (`id_representante` INT(15))  SELECT *
    FROM jugador
    WHERE jugador.representante = id_representante$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `NUMEROEQUIPOSPATROCINADOR` (`patrocinador_id` INT(15))  SELECT COUNT(id_equipo)
FROM equipo
WHERE equipo.patrocinador = patrocinador_id$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `equipo`
--

CREATE TABLE `equipo` (
  `id_equipo` int(15) NOT NULL,
  `nombre` varchar(40) DEFAULT NULL,
  `fondos` double DEFAULT NULL,
  `nacionalidad` int(15) DEFAULT NULL,
  `patrocinador` int(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `equipo`
--

INSERT INTO `equipo` (`id_equipo`, `nombre`, `fondos`, `nacionalidad`, `patrocinador`) VALUES
(1, 'Real Madrid', 2222222333, 1, 1),
(2, 'Oporto', 22000000, 3, 1),
(3, 'Barca', 222, 1, 1),
(4, 'Atletico de Madrid', 200000000, 1, 1),
(5, 'Chelsea', 400000000, 4, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `jugador`
--

CREATE TABLE `jugador` (
  `id_jugador` int(15) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `equipo` int(15) DEFAULT NULL,
  `nacionalidad` int(15) DEFAULT NULL,
  `salario` double DEFAULT NULL,
  `representante` int(15) DEFAULT NULL,
  `ha_sido_internacional` bit(1) NOT NULL DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `jugador`
--

INSERT INTO `jugador` (`id_jugador`, `nombre`, `equipo`, `nacionalidad`, `salario`, `representante`, `ha_sido_internacional`) VALUES
(1, 'Asensio', 1, 1, 2000000, 1, b'1'),
(2, 'Kroos', 1, 3, 60000000, 2, b'1'),
(3, 'Gerard Pique', 3, 1, 7000000, 1, b'0'),
(4, 'Ansu Fati', 3, 1, 4000000, 3, b'1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `nacionalidad`
--

CREATE TABLE `nacionalidad` (
  `id_pais` int(15) NOT NULL,
  `nombre_pais` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `nacionalidad`
--

INSERT INTO `nacionalidad` (`id_pais`, `nombre_pais`) VALUES
(1, 'Espana'),
(2, 'Brasil'),
(3, 'Portugal'),
(4, 'Inglaterra');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `patrocinador`
--

CREATE TABLE `patrocinador` (
  `id_patrocinador` int(15) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `dinero_aporta` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `patrocinador`
--

INSERT INTO `patrocinador` (`id_patrocinador`, `nombre`, `dinero_aporta`) VALUES
(1, 'Fly Emirates', 2222222333);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `representante`
--

CREATE TABLE `representante` (
  `id_representante` int(15) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `comision` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `representante`
--

INSERT INTO `representante` (`id_representante`, `nombre`, `comision`) VALUES
(1, 'Rene Ramos', 250000),
(2, 'Juan Kroos', 700000),
(3, 'Mino Raiola', 300000);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `equipo`
--
ALTER TABLE `equipo`
  ADD PRIMARY KEY (`id_equipo`),
  ADD KEY `nacionalidad` (`nacionalidad`),
  ADD KEY `patrocinador` (`patrocinador`);

--
-- Indices de la tabla `jugador`
--
ALTER TABLE `jugador`
  ADD PRIMARY KEY (`id_jugador`),
  ADD KEY `equipo` (`equipo`),
  ADD KEY `nacionalidad` (`nacionalidad`),
  ADD KEY `representante` (`representante`);

--
-- Indices de la tabla `nacionalidad`
--
ALTER TABLE `nacionalidad`
  ADD PRIMARY KEY (`id_pais`);

--
-- Indices de la tabla `patrocinador`
--
ALTER TABLE `patrocinador`
  ADD PRIMARY KEY (`id_patrocinador`);

--
-- Indices de la tabla `representante`
--
ALTER TABLE `representante`
  ADD PRIMARY KEY (`id_representante`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `equipo`
--
ALTER TABLE `equipo`
  MODIFY `id_equipo` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `jugador`
--
ALTER TABLE `jugador`
  MODIFY `id_jugador` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `nacionalidad`
--
ALTER TABLE `nacionalidad`
  MODIFY `id_pais` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `patrocinador`
--
ALTER TABLE `patrocinador`
  MODIFY `id_patrocinador` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `representante`
--
ALTER TABLE `representante`
  MODIFY `id_representante` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `equipo`
--
ALTER TABLE `equipo`
  ADD CONSTRAINT `equipo_ibfk_1` FOREIGN KEY (`nacionalidad`) REFERENCES `nacionalidad` (`id_pais`),
  ADD CONSTRAINT `equipo_ibfk_2` FOREIGN KEY (`patrocinador`) REFERENCES `patrocinador` (`id_patrocinador`);

--
-- Filtros para la tabla `jugador`
--
ALTER TABLE `jugador`
  ADD CONSTRAINT `jugador_ibfk_1` FOREIGN KEY (`equipo`) REFERENCES `equipo` (`id_equipo`),
  ADD CONSTRAINT `jugador_ibfk_2` FOREIGN KEY (`nacionalidad`) REFERENCES `nacionalidad` (`id_pais`),
  ADD CONSTRAINT `jugador_ibfk_3` FOREIGN KEY (`representante`) REFERENCES `representante` (`id_representante`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
