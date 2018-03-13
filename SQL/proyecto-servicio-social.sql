/*Registro*/

CREATE TABLE Carreras(
	IdCarrera int,
	Nombre VARCHAR(100),
	PRIMARY KEY (IdCarrera)
);

CREATE TABLE Usuarios(
	IdUsuario VARCHAR(7),
	Nombre VARCHAR(20),
	Correo VARCHAR(100),
	Password VARCHAR(20),
	Carrera int,
	Foto VARCHAR(100),
	PRIMARY KEY (IdUsuario),
    FOREIGN KEY (Carrera) REFERENCES Carreras(IdCarrera)
);