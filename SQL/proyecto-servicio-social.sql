/*Preguntar*/

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

-- Tabla Preguntas
CREATE TABLE Preguntas(
    IdPregunta  int,
    IdUsuario   VARCHAR(7),
    Titulo      VARCHAR(50),
    Categoria   VARCHAR(30),
    Descripcion VARCHAR(500),
    PRIMARY KEY (IdPregunta),
    FOREIGN KEY (IdUsuario) REFERENCES Usuarios(IdUsuario)
);

INSERT INTO Preguntas (IdPregunta, IdUsuario, Titulo, Categoria, Descripcion) VALUES (1, 1, 'Qué buena pregunta, ¿alguien conoce la respuesta?', "Inscripciones", "");
INSERT INTO Preguntas (IdPregunta, IdUsuario, Titulo, Categoria, Descripcion) VALUES (2, 1, 'Tengo entendido que no se pueden meter más de 6 materias', "Inscripciones", "");
INSERT INTO Preguntas (IdPregunta, IdUsuario, Titulo, Categoria, Descripcion) VALUES (3, 1, 'La forma más común de titulación es la tésis', "Inscripciones", "");