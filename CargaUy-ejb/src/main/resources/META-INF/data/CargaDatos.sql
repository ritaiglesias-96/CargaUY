INSERT INTO public."Usuario"(tipo_usuario, id, apellido, nombre, fechaNacimiento, correo, username, password) values('administrador', 100, 'Iglesias', 'Rita', '1996-06-12 00:00:00', 'rita.iglesias.adrover@gmail.com', 'admin', '21232F297A57A5A743894A0E4A801FC3');
INSERT INTO public."Usuario"(tipo_usuario, id, apellido, nombre, fechaNacimiento, correo, username, password) values('autoridad', 101, 'Bravo', 'Marcelo', '1995-04-26 00:00:00', 'marceb95@gmail.com', 'autoridad', '21232F297A57A5A743894A0E4A801FC3');

--- Vehiculos
INSERT INTO public."Vehiculo" (id, capacidadcarga, fechafinitv, fechafinpnc, fechainiciopnc, marca, matricula, modelo, pais, peso, pnc) VALUES (1, 365, '2023-06-11', '2023-06-29', '2023-06-30', 'Honda', 'SDE5687', 'Camion', 'Uruguay', 2000, 1891);
INSERT INTO public."Vehiculo" (id, capacidadcarga, fechafinitv, fechafinpnc, fechainiciopnc, marca, matricula, modelo, pais, peso, pnc) VALUES (2, 200, '2023-06-01', '2023-06-15', '2023-06-15', 'Toyota', 'SDF1254', 'Camion', 'Uruguay', 2000, 1891);

--- Ciudadano

INSERT INTO public."Ciudadano" (dtype, id, cedula, email, rol, empresa_id) VALUES ('Ciudadano', 1, '1452159', 'ciudadano@com', null, null);
INSERT INTO public."Ciudadano" (dtype, id, cedula, email, rol, empresa_id) VALUES ('Ciudadano', 2, '1458745', 'ciudadano2@com', null, null);

--- Funcionario
INSERT INTO public."Ciudadano" (dtype, id, cedula, email, rol, empresa_id) VALUES ('Funcionario', 3, '1234567', 'funcionario@com', 0, null);
INSERT INTO public."Ciudadano" (dtype, id, cedula, email, rol, empresa_id) VALUES ('Funcionario', 4, '2345678', 'funcionario2@com', 0, null);

--- Choferes
INSERT INTO public."Ciudadano" (dtype, id, cedula, email, rol, empresa_id) VALUES ('Chofer', 5, '2365896', 'chofer@com', 2, null);
INSERT INTO public."Ciudadano" (dtype, id, cedula, email, rol, empresa_id) VALUES ('Chofer', 6, '2547856', 'chofer2@com', 2, null);

--- Responsable
INSERT INTO public."Ciudadano" (dtype, id, cedula, email, rol, empresa_id) VALUES ('Responsable', 7, '3456789', 'responsable@com', 1, null);
INSERT INTO public."Ciudadano" (dtype, id, cedula, email, rol, empresa_id) VALUES ('Responsable', 8, '4567891', 'responsable2@com', 1, null);

---Empresas
INSERT INTO public."Empresa" (id, dirprincipal, nombrepublico, nroempresa, razonsocial, responsable_id) VALUES (1, 'Rivera 1234', 'TATA', 1254,'tatasa',null);

-- Tipo de carga
insert into public."TipoCarga" values (1000,'Producto quimico');
insert into public."TipoCarga" values (1001,'Producto no quimico');

-- Rubros
insert into public."Rubro" values (1000,'Carnes');
insert into public."Rubro" values (1001,'Farmacos');
insert into public."Rubro" values (1002,'Informatica');

-- Guia de viaje
-- Viaje finalizado
INSERT INTO public."GuiaDeViaje" values(1000,'Montevideo','2023-05-02','2023-05-15','2023-05-02',1,'Artigas','Carnes','Producto no quimico',75.0);
-- Viaje iniciado sin finalizar
INSERT INTO public."GuiaDeViaje" values(1001,'Montevideo','2023-06-15',null,'2023-06-15',2,'Salto','Farmacos','Producto quimico',34.0);
-- Viaje sin iniciar
INSERT INTO public."GuiaDeViaje" values(1002,'Montevideo',current_date,null,null,3,'Colonia','Carnes','Producto no quimico',50.0);