drop database if exists clinica_dental_in4av;
create database clinica_dental_in4av;
use clinica_dental_in4av;
 
 
create table clientes(
    id_cliente int auto_increment primary key,
    name varchar(50) not null,
    lastname varchar(50) not null
);
 
 
delimiter //
 
create procedure crear_cliente(
    in p_name varchar(50),
    in p_lastname varchar(50)
)
begin
    insert into clientes
    (name, lastname)
    values
    (p_name, p_lastname);
end //
 
delimiter ;
 
 
delimiter //
 
create procedure mostrar_clientes()
begin
    select *
    from clientes;
end //
 
delimiter ;
 
 
delimiter //
 
create procedure leer_cliente(
    in p_id_cliente int
)
begin
    select *
    from clientes
    where id_cliente = p_id_cliente;
end //
 
delimiter ;
 
 
delimiter //
 
create procedure editar_cliente(
    in p_id_cliente int,
    in p_name varchar(50),
    in p_lastname varchar(50)
)
begin
    update clientes
    set
        name = p_name,
        lastname = p_lastname
    where id_cliente = p_id_cliente;
end //
 
delimiter ;
 
 
delimiter //
 
create procedure eliminar_cliente(
    in p_id_cliente int
)
begin
    delete from clientes
    where id_cliente = p_id_cliente;
end //
 
delimiter ;
 
 
create table servicios(
    id_servicio int primary key,
    name_service varchar(50) not null,
    descripcion varchar(200),
    precio decimal(10,2) not null
);
 
 
delimiter //
 
create procedure crear_servicio(
    in p_id_servicio int,
    in p_name_service varchar(50),
    in p_descripcion varchar(200),
    in p_precio decimal(10,2)
)
begin
    insert into servicios
    (id_servicio, name_service, descripcion, precio)
    values
    (p_id_servicio, p_name_service, p_descripcion, p_precio);
end //
 
delimiter ;
 
 
create table admin(
    name_clave varchar(50) primary key,
    pass_admin varchar(50) not null
);
 
 
delimiter //
 
create procedure crear_admin(
    in p_name_clave varchar(50),
    in p_pass_admin varchar(50)
)
begin
    insert into admin
    (name_clave, pass_admin)
    values
    (p_name_clave, p_pass_admin);
end //
 
delimiter ;
 
 
delimiter //
 
create procedure mostrar_admin()
begin
    select *
    from admin;
end //
 
delimiter ;
 
 
create table citas(
    id_cita int auto_increment primary key,
    fecha date not null,
    id_cliente int not null,
    id_servicio int not null,
    foreign key (id_cliente) references clientes(id_cliente),
    foreign key (id_servicio) references servicios(id_servicio)
);
 
 
delimiter //
 
create procedure crear_cita(
    in p_fecha date,
    in p_id_cliente int,
    in p_id_servicio int
)
begin
    insert into citas
    (fecha, id_cliente, id_servicio)
    values
    (p_fecha, p_id_cliente, p_id_servicio);
end //
 
delimiter ;
