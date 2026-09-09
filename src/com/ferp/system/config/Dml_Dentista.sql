drop database if exists clinica_dental_in4av;
create database clinica_dental_in4av;
use clinica_dental_in4av;
 

 
 
call crear_servicio(
    717,
    'extraccion dental',
    'procedimiento odontologico para remover una pieza dental cuando no puede ser conservada mediante otros tratamientos',
    500.00
);
 
call crear_servicio(
    224,
    'limpieza profunda',
    'tratamiento odontologico destinado a eliminar placa bacteriana, calculo dental y acumulaciones presentes en las superficies y zonas de dificil acceso',
    1200.00
);
 
call crear_servicio(
    302,
    'ortodoncia',
    'tratamiento odontologico especializado para corregir la posicion de los dientes y mejorar la mordida mediante aparatologia ortodontica',
    18000.00
);
 
select * from servicios;