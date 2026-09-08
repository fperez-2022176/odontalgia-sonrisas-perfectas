create database clinica_dental_in4av;
use clinica_dental_in4av;

create table Clientes(
	name varchar(50),
    lastname varchar(50),
    id_cliente int(25),
		primary key(id_cliente)
);

create table Servicios(
	id_servicio int(50) primary key,
    name_service varchar(50),
    descripcion varchar(200),
    precio decimal(10,2)
);

create table Admin(
	name_clave varchar(50) primary key,
    pass_admin varchar(50)
);

create table Citas(
	id_cita int (50) primary key ,
    fecha date not null,
    id_cliente int not null,
    id_servicio int not null,
	foreign key (id_cliente) references Clientes(id_cliente),
    foreign key (id_servicio) references Servicios(id_servicio)
);	
