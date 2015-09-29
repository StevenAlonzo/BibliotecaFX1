Create Database BibliotecaInFX
Go
Use BibliotecaInFX
Go

CREATE TABLE Usuario(
	Codigo_Usuario INT IDENTITY PRIMARY KEY,
	nombre VARCHAR (30) NOT NULL,
	direccion VARCHAR (30) NOT NULL,
	telefono VARCHAR (30) NOT NULL,
)
GO
CREATE TABLE Autor(
	Codigo_Autor INT IDENTITY PRIMARY KEY ,		
	Nombre_A VARCHAR (50) NOT NULL,
)
Go
Create Table Libros(
	Codigo_Libro INT IDENTITY PRIMARY KEY,
	Titulo VARCHAR (30) NOT NULL , 
	Editorial VARCHAR (30) NOT NULL,
	ISBN INT NOT NULL,
	Paginas INT  NOT NULL,
	Codigo_Autor INT FOREIGN KEY REFERENCES Autor(Codigo_Autor),
)
Go
CREATE TABLE Ejemplar(
	Codigo_Ejemplar int IDENTITY PRIMARY KEY,
	Localizacion VARCHAR(30) NOT NULL,
	Codigo_Libro INT FOREIGN KEY REFERENCES Libros(Codigo_Libro)
)
Go
CREATE TABLE Prestamo(
	Codigo_Prestamo INT IDENTITY PRIMARY KEY,
	Codigo_Usuario INT FOREIGN KEY REFERENCES Usuario(Codigo_Usuario),
	Codigo_Ejemplar INT FOREIGN KEY REFERENCES Ejemplar(Codigo_Ejemplar),
	Fecha_Prestamo Varchar NOT NULL UNIQUE,
	Fecha_Limite Varchar NOT NULL UNIQUE,
	Fecha_Devolucion Varchar NULL,
)
GO


INSERT INTO Usuario(nombre,direccion,telefono) Values ('pedro Fernandez','zona 10 casa 1',54128745)
INSERT INTO Usuario(nombre,direccion,telefono) Values ('Juan Gutierrez','zona 19 casa 2',56987452)
INSERT INTO Usuario(nombre,direccion,telefono) Values ('Julian Priego','zona 16 casa 32',4587412)
INSERT INTO Usuario(nombre,direccion,telefono) Values ('Julio Estacuy','Zona 2 villacanales casa 1',58974563)
INSERT INTO Usuario(nombre,direccion,telefono) Values ('Mario Rodriguez','zona 15 casa 7',98745612)
INSERT INTO Usuario(nombre,direccion,telefono) Values ('Humberto Ruiz', 'zona 21 casa 7',98746532)
INSERT INTO Usuario(nombre,direccion,telefono) Values ('Carlos Camiany', 'zona 21 casa 87',98745632)
INSERT INTO Usuario(nombre,direccion,telefono) Values ('Axel Messi','Argentina',78945621)
INSERT INTO Usuario(nombre,direccion,telefono) Values ('Oscar Peralta','villa lobos 2',45612101)
INSERT INTO Usuario(nombre,direccion,telefono) Values ('wisquil perez','mixco',78952001)
GO

INSERT INTO Autor(Nombre_A) VALUES('Otto Perez')
INSERT INTO Autor(Nombre_A) VALUES('Roxana Baldeti')
INSERT INTO Autor(Nombre_A) VALUES('Omar Bonilla')
INSERT INTO Autor(Nombre_A) VALUES('Sintia Aguila')
INSERT INTO Autor(Nombre_A) VALUES('Turminos Larios')
INSERT INTO Autor(Nombre_A) VALUES('Brayan Julian')
INSERT INTO Autor(Nombre_A) VALUES('Pio Pieterchina')
INSERT INTO Autor(Nombre_A) VALUES('Agusto Robles')
INSERT INTO Autor(Nombre_A) VALUES('Ernetino Western')
INSERT INTO Autor(Nombre_A) VALUES('Calimba perez')
GO

INSERT INTO Libros(Titulo,Editorial,ISBN,Paginas,Codigo_Autor) VALUES('La linea','Santillana',789-456-456-245,58,1)
INSERT INTO Libros(Titulo,Editorial,ISBN,Paginas,Codigo_Autor) VALUES('La mojarra','El progreso',787-456-789-456,150,2)
INSERT INTO Libros(Titulo,Editorial,ISBN,Paginas,Codigo_Autor) VALUES('Los ministros','Santillana',789-632-658-236,250,3)
INSERT INTO Libros(Titulo,Editorial,ISBN,Paginas,Codigo_Autor) VALUES('La Educacion','Nuevo Mundo',789-654-123-357,550,4)
INSERT INTO Libros(Titulo,Editorial,ISBN,Paginas,Codigo_Autor) VALUES('Las cosas de la vida','Juelsa',456-987-785-159,225,5)
INSERT INTO Libros(Titulo,Editorial,ISBN,Paginas,Codigo_Autor) VALUES('Las moscas','kinal',789-357-951-258,55,6)
INSERT INTO Libros(Titulo,Editorial,ISBN,Paginas,Codigo_Autor) VALUES('Los Indijentes','Petronila',789-654-987-321,552,8)
INSERT INTO Libros(Titulo,Editorial,ISBN,Paginas,Codigo_Autor) VALUES('Exorcismos En El Baticano','El espiritu Santo',987-357-148-784,500,7)
INSERT INTO Libros(Titulo,Editorial,ISBN,Paginas,Codigo_Autor) VALUES('laldfiada','kiknal',756-659-984-874,541,9)
INSERT INTO Libros(Titulo,Editorial,ISBN,Paginas,Codigo_Autor) VALUES('destruccion al mundo','Iluminati',789-541-259-357,250,10)
GO

INSERT INTO Ejemplar(Localizacion,Codigo_Libro) VALUES('Actualidad',1)
INSERT INTO Ejemplar(Localizacion,Codigo_Libro) VALUES('Actualidad',2)
INSERT INTO Ejemplar(Localizacion,Codigo_Libro) VALUES('Historia',3)
INSERT INTO Ejemplar(Localizacion,Codigo_Libro) VALUES('Historia',4)
INSERT INTO Ejemplar(Localizacion,Codigo_Libro) VALUES('Informacion',5)
INSERT INTO Ejemplar(Localizacion,Codigo_Libro) VALUES('Desinformacion',6)
INSERT INTO Ejemplar(Localizacion,Codigo_Libro) VALUES('Religion',7)
INSERT INTO Ejemplar(Localizacion,Codigo_Libro) VALUES('BetBox',8)
INSERT INTO Ejemplar(Localizacion,Codigo_Libro) VALUES('regueboys',9)
INSERT INTO Ejemplar(Localizacion,Codigo_Libro) VALUES('Libros',10)
GO

INSERT INTO Prestamo(Codigo_Usuario,Codigo_Ejemplar,Fecha_Prestamo,Fecha_Limite,Fecha_Devolucion ) VALUES (1,1,'22/05/15','25/05/15')
INSERT INTO Prestamo(Codigo_Usuario,Codigo_Ejemplar,Fecha_Prestamo,Fecha_Limite,Fecha_Devolucion ) VALUES (2,2,'23/05/15','26/05/15','24/05/15')
INSERT INTO Prestamo(Codigo_Usuario,Codigo_Ejemplar,Fecha_Prestamo,Fecha_Limite,Fecha_Devolucion ) VALUES (3,3,'24/05/15','27/05/15')
INSERT INTO Prestamo(Codigo_Usuario,Codigo_Ejemplar,Fecha_Prestamo,Fecha_Limite,Fecha_Devolucion ) VALUES (4,4,'25/05/15','28/05/15','27/05/15')
INSERT INTO Prestamo(Codigo_Usuario,Codigo_Ejemplar,Fecha_Prestamo,Fecha_Limite,Fecha_Devolucion ) VALUES (5,5,'26/05/15','29/05/15')
INSERT INTO Prestamo(Codigo_Usuario,Codigo_Ejemplar,Fecha_Prestamo,Fecha_Limite,Fecha_Devolucion ) VALUES (6,6,'27/05/15','30/05/15')
INSERT INTO Prestamo(Codigo_Usuario,Codigo_Ejemplar,Fecha_Prestamo,Fecha_Limite,Fecha_Devolucion ) VALUES (7,7,'1/07/15','4/07/15','3/05/15')
INSERT INTO Prestamo(Codigo_Usuario,Codigo_Ejemplar,Fecha_Prestamo,Fecha_Limite,Fecha_Devolucion ) VALUES (8,8,'2/07/15','5/07/15')
INSERT INTO Prestamo(Codigo_Usuario,Codigo_Ejemplar,Fecha_Prestamo,Fecha_Limite,Fecha_Devolucion ) VALUES (9,9,'3/07/15','6/07/15')
INSERT INTO Prestamo(Codigo_Usuario,Codigo_Ejemplar,Fecha_Prestamo,Fecha_Limite,Fecha_Devolucion ) VALUES (10,10,'4/07/15','7/07/15','12/07/15')
GO

select * from Libros 