create database arquitectura;

use arquitectura;

create table sujeto (
	idSujeto int not null auto_increment primary key,
    nombre varchar(20),
    apellidoPaterno varchar(20),
    apellidoMaterno varchar(20),
    username varchar(20),
    fechaNacimiento date,
    correo varchar(30)
);

