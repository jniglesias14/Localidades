CREATE USER geografia@localhost IDENTIFIED BY 'A12345a';
grant all on geografia.* to geografia@localhost;
select * from localidades;

select c.noCREATE USER geografia@localhost IDENTIFIED BY 'A12345a';
grant all on geografia.* to geografia@localhost;
select * from localidades;mbre,p.nombre,l.nombre,l.poblacion
from localidades l
         join provincias p using(n_provincia)
         join comunidades c using(id_comunidad)
where id_localidad=2943;

select sum(poblacion)
from localidades l
         join provincias p using(n_provincia)
where p.nombre='Valladolid';

/*Asi se haria con mysql*/
select nombre
from localidades
order by RAND() /*RAND(): es el random de mysql*/
LIMIT 1;

select * from localidades;
