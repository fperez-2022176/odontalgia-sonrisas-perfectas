
create database clinica_dental_in4av;
use clinica_dental_in4av;
 
call  crear_admin("Doc Sonrisitas","Sonrisitas177H");


 call  crear_admin("Doc Sonrisitas","Sonrisitas177H");
 

call crear_servicio('extraccion dental', 'procedimiento odontologico para remover una pieza...', 500.00);
call crear_servicio('limpieza profunda', 'tratamiento odontologico destinado a eliminar...', 1200.00);
call crear_servicio('ortodoncia', 'tratamiento odontologico especializado...', 18000.00);
 
select * from servicios;
select * from admin;
select * from clientes;