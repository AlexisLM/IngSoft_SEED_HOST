/*Iniciar Sesión*/
CREATE TABLE Usuarios(
  IdUsuario VARCHAR(7),
  Nombre VARCHAR(20),
  Correo VARCHAR(100),
  Password VARCHAR(20),
  PRIMARY KEY (IdUsuario)
);