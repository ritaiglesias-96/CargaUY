INSERT INTO public."Usuario"(tipo_usuario, id, apellido, nombre, fechaNacimiento, correo, username, password) values('administrador', 100, 'Iglesias', 'Rita', '1996-06-12 00:00:00', 'rita.iglesias.adrover@gmail.com', 'admin', '21232F297A57A5A743894A0E4A801FC3');
INSERT INTO public."Usuario"(tipo_usuario, id, apellido, nombre, fechaNacimiento, correo, username, password) values('autoridad', 101, 'Bravo', 'Marcelo', '1995-04-26 00:00:00', 'marceb95@gmail.com', 'autoridad', '21232F297A57A5A743894A0E4A801FC3');

--- Ciudadano
INSERT INTO public."Ciudadano" (dtype, id, cedula, email, rol, empresa_id) VALUES ('Ciudadano', 1956, '1452159', 'ciudadano@com', null, null);
INSERT INTO public."Ciudadano" (dtype, id, cedula, email, rol, empresa_id) VALUES ('Ciudadano', 2568, '1458745', 'ciudadano2@com', null, null);

--- Funcionario
INSERT INTO public."Ciudadano" (dtype, id, cedula, email, rol, empresa_id) VALUES ('Funcionario', 3568, '1234567', 'funcionario@com', 0, null);
INSERT INTO public."Ciudadano" (dtype, id, cedula, email, rol, empresa_id) VALUES ('Funcionario', 4858, '2345678', 'funcionario2@com', 0, null);

--- Responsable
INSERT INTO public."Ciudadano" (dtype, id, cedula, email, rol, empresa_id) VALUES ('Responsable', 7856, '3456789', 'responsable@com', 1, null);
INSERT INTO public."Ciudadano" (dtype, id, cedula, email, rol, empresa_id) VALUES ('Responsable', 8856, '4567891', 'responsable2@com', 1, null);
INSERT INTO public."Ciudadano" (dtype, id, cedula, email, rol, empresa_id) VALUES ('Responsable', 4567, '56587008', 'rita@gmail.com', 1, null);

---Empresas
INSERT INTO public."Empresa" (id, dirprincipal, nombrepublico, nroempresa, razonsocial, responsable_id) VALUES (1586, 'Rivera 1234', 'TATA', 1254,'tatasa',7856);
INSERT INTO public."Empresa" (id, dirprincipal, nombrepublico, nroempresa, razonsocial, responsable_id) VALUES (1587, 'Rivera 4321', 'ATAT', 4521,'atatsa',4567);


UPDATE public."Ciudadano" SET empresa_id = 1587  WHERE id = 4567;
--- Choferes
INSERT INTO public."Ciudadano" (dtype, id, cedula, email, rol, empresa_id) VALUES ('Chofer', 5856, '51002930', 'chofer@com', 2, 1586);
INSERT INTO public."Ciudadano" (dtype, id, cedula, email, rol, empresa_id) VALUES ('Chofer', 6856, '2547856', 'chofer2@com', 2, 1587);


--- Vehiculos
INSERT INTO public."Vehiculo" (id, capacidadcarga, fechafinitv, fechafinpnc, fechainiciopnc, marca, matricula, modelo, pais, peso, pnc, empresa_id) VALUES (1999, 365, '2023-06-11', '2023-06-29', '2023-06-30', 'Honda', 'SDE5687', 'Camion', 'Uruguay', 2000, 1891, 1586);
INSERT INTO public."Vehiculo" (id, capacidadcarga, fechafinitv, fechafinpnc, fechainiciopnc, marca, matricula, modelo, pais, peso, pnc, empresa_id) VALUES (2999, 200, '2023-06-01', '2023-06-15', '2023-06-15', 'Toyota', 'SDF1254', 'Camion', 'Uruguay', 2000, 1891, 1586);
INSERT INTO public."Vehiculo" (id, capacidadcarga, fechafinitv, fechafinpnc, fechainiciopnc, marca, matricula, modelo, pais, peso, pnc, empresa_id) VALUES (3999, 123, '2023-06-01', '2023-06-15', '2023-06-15', 'Volkswagen', 'SDF2112', 'Camion', 'Uruguay', 2000, 1891, 1586);

-- Empresa-Vehiculo
INSERT INTO public."Empresa_Vehiculo" ("Empresa_id", vehiculos_id) values(1586,1999);
INSERT INTO public."Empresa_Vehiculo" ("Empresa_id", vehiculos_id) values(1586,2999);
INSERT INTO public."Empresa_Vehiculo" ("Empresa_id", vehiculos_id) values(1586,3999);

-- Guia de viaje
-- Datos historicos
-- 2018
INSERT INTO public."GuiaDeViaje" values(9999,'Montevideo','2018-06-06','2018-06-06','2018-06-06',-15,'Salto','Carnes','Producto no quimico',79.0);
-- 2019
INSERT INTO public."GuiaDeViaje" values(10000,'Montevideo','2019-05-02','2019-05-02','2019-05-02',-13,'Salto','Carnes','Producto no quimico',35.0);
INSERT INTO public."GuiaDeViaje" values(10001,'Montevideo','2019-01-02','2019-01-02','2019-01-02',-14,'Maldonado','Informatica','Producto no quimico',12.0);
INSERT INTO public."GuiaDeViaje" values(10002,'Montevideo','2019-05-31','2019-05-31','2019-05-31',-12,'Salto','Carnes','Producto no quimico',77.0);
INSERT INTO public."GuiaDeViaje" values(10003,'Montevideo','2019-10-15','2019-10-15','2019-10-15',-11,'Maldonado','Informatica','Producto no quimico',24.0);
-- 2020
INSERT INTO public."GuiaDeViaje" values(10004,'Montevideo','2020-01-03','2020-01-03','2020-01-03',-10,'Salto','Carnes','Producto no quimico',35.0);
INSERT INTO public."GuiaDeViaje" values(10005,'Montevideo','2020-09-01','2020-09-01','2020-09-01',-9,'Maldonado','Informatica','Producto no quimico',12.0);
INSERT INTO public."GuiaDeViaje" values(10006,'Montevideo','2020-12-20','2020-12-20','2020-12-20',-8,'Salto','Carnes','Producto no quimico',77.0);
-- 2021
INSERT INTO public."GuiaDeViaje" values(10007,'Montevideo','2021-03-15','2021-03-15','2021-03-15',-7,'Colonia','Farmacos','Producto quimico',10.0);
INSERT INTO public."GuiaDeViaje" values(10008,'Montevideo','2021-07-22','2021-07-22','2021-07-22',-6,'Colonia','Farmacos','Producto quimico',15.0);
-- 2022
INSERT INTO public."GuiaDeViaje" values(10009,'Montevideo','2022-02-14','2022-02-14','2022-02-14',-5,'Canelones','Carnes','Producto no quimico',120.0);
INSERT INTO public."GuiaDeViaje" values(10010,'Montevideo','2022-04-26','2022-04-26','2022-04-26',-4,'Canelones','Informatica','Producto no quimico',8.0);
INSERT INTO public."GuiaDeViaje" values(10011,'Montevideo','2022-06-30','2022-06-30','2022-06-30',-3,'Salto','Farmacos','Producto quimico',7.0);
INSERT INTO public."GuiaDeViaje" values(10012,'Montevideo','2022-10-10','2022-10-10','2022-10-10',-2,'Rivera','Farmacos','Producto quimico',15.0);
INSERT INTO public."GuiaDeViaje" values(10013,'Montevideo','2022-12-03','2022-12-03','2022-12-03',-1,'Rivera','Informatica','Producto no quimico',13.0);
-- Viaje finalizado
INSERT INTO public."GuiaDeViaje" values(1000,'Montevideo','2023-05-02','2023-05-15','2023-05-02',1,'Artigas','Carnes','Producto no quimico',75.0);
-- Viaje iniciado sin finalizar
INSERT INTO public."GuiaDeViaje" values(1001,'Montevideo','2023-06-15',null,'2023-06-15',2,'Salto','Farmacos','Producto quimico',34.0);
-- Viaje sin iniciar
INSERT INTO public."GuiaDeViaje" values(1002,'Montevideo',current_date,null,null,3,'Colonia','Carnes','Producto no quimico',50.0);

-- Asignacion
INSERT INTO public."Asignacion" values(1000,'2023-05-02',1000);
INSERT INTO public."Asignacion" values(1001,'2023-05-15',1001);
INSERT INTO public."Asignacion" values(1002,'2023-07-10',1002);

-- Chofer-Asignacion
INSERT INTO public."Ciudadano_Asignacion" values(5856,1000);
INSERT INTO public."Ciudadano_Asignacion" values(5856,1001);
INSERT INTO public."Ciudadano_Asignacion" values(5856,1002);

-- Vehiculo-Asignacion
INSERT INTO public."Vehiculo_Asignacion" ("Vehiculo_id", asignaciones_id) values(1999,1000);
INSERT INTO public."Vehiculo_Asignacion" ("Vehiculo_id", asignaciones_id) values(1999,1001);
INSERT INTO public."Vehiculo_Asignacion" ("Vehiculo_id", asignaciones_id) values(2999,1002);

-- Tipo de carga
insert into public."TipoCarga" values (1000,'Producto quimico');
insert into public."TipoCarga" values (1001,'Producto no quimico');

-- Rubros
insert into public."Rubro" values (1000,'Carnes');
insert into public."Rubro" values (1001,'Farmacos');
insert into public."Rubro" values (1002,'Informatica');

-- Pesajes
insert into public."Pesaje" values(1000,75,'2023-05-02 12:30:00',-15.2135,-89.4568);
insert into public."Pesaje" values(1001,75,'2023-05-10 12:30:00',-14.2135,-93.4568);
insert into public."Pesaje" values(1002,75,'2023-05-15 12:35:00',-19.2135,-84.4568);

-- GuiaDeViaje/Pesaje
insert into public."GuiaDeViaje_Pesaje" values(1000,1000);
insert into public."GuiaDeViaje_Pesaje" values(1000,1001);
insert into public."GuiaDeViaje_Pesaje" values(1000,1002);