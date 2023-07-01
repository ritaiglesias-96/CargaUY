INSERT INTO public."Usuario"(tipo_usuario, id, apellido, nombre, fechaNacimiento, correo, username, password) values('administrador', 100, 'Iglesias', 'Rita', '1996-06-12 00:00:00', 'rita.iglesias.adrover@gmail.com', 'admin', '21232F297A57A5A743894A0E4A801FC3');

--- Ciudadano
INSERT INTO public."Ciudadano" (dtype, id, cedula, email, rol, empresa_id) VALUES ('Ciudadano', 1956, '1452159', 'ciudadano@com', null, null);
INSERT INTO public."Ciudadano" (dtype, id, cedula, email, rol, empresa_id) VALUES ('Ciudadano', 2568, '1458745', 'ciudadano2@com', null, null);

--- Funcionario
INSERT INTO public."Ciudadano" (dtype, id, cedula, email, rol, empresa_id) VALUES ('Funcionario', 3568, '1234567', 'funcionario@com', 0, null);
INSERT INTO public."Ciudadano" (dtype, id, cedula, email, rol, empresa_id) VALUES ('Funcionario', 4858, '2345678', 'funcionario2@com', 0, null);

--- Responsable
INSERT INTO public."Ciudadano" (dtype, id, cedula, email, rol, empresa_id) VALUES ('Responsable', 7856, '3456789', 'responsable@com', 1, null);
INSERT INTO public."Ciudadano" (dtype, id, cedula, email, rol, empresa_id) VALUES ('Responsable', 8856, '4567891', 'responsable2@com', 1, null);

---Empresas
INSERT INTO public."Empresa" (id, dirprincipal, nombrepublico, nroempresa, razonsocial, responsable_id) VALUES (1586, 'Rivera 1234', 'TATA', 1254,'tatasa',7856);
INSERT INTO public."Empresa" (id, dirprincipal, nombrepublico, nroempresa, razonsocial, responsable_id) VALUES (1587, 'Rivera 4321', 'ATAT', 4521,'atatsa',8856);

--- Choferes
INSERT INTO public."Ciudadano" (dtype, id, cedula, email, rol, empresa_id) VALUES ('Chofer', 5856, '2365896', 'chofer@com', 2, 1586);
INSERT INTO public."Ciudadano" (dtype, id, cedula, email, rol, empresa_id) VALUES ('Chofer', 6856, '2547856', 'chofer2@com', 2, 1587);


--- Vehiculos
INSERT INTO public."Vehiculo" (id, capacidadcarga, fechafinitv, fechafinpnc, fechainiciopnc, marca, matricula, modelo, pais, peso, pnc, empresa_id) VALUES (1999, 365, '2023-06-11', '2023-06-29', '2023-06-30', 'Honda', 'SDE5687', 'Camion', 'Uruguay', 2000, 1891, 1586);
INSERT INTO public."Vehiculo" (id, capacidadcarga, fechafinitv, fechafinpnc, fechainiciopnc, marca, matricula, modelo, pais, peso, pnc, empresa_id) VALUES (2999, 200, '2023-06-01', '2023-06-15', '2023-06-15', 'Toyota', 'SDF1254', 'Camion', 'Uruguay', 2000, 1891, 1586);
INSERT INTO public."Vehiculo" (id, capacidadcarga, fechafinitv, fechafinpnc, fechainiciopnc, marca, matricula, modelo, pais, peso, pnc, empresa_id) VALUES (3999, 123, '2023-06-01', '2023-06-15', '2023-06-15', 'Volkswagen', 'SDF2112', 'Camion', 'Uruguay', 2000, 1891, 1586);

-- Empresa-Vehiculo
INSERT INTO public."Empresa_Vehiculo" ("Empresa_id", vehiculos_id) values(1586,1999);
INSERT INTO public."Empresa_Vehiculo" ("Empresa_id", vehiculos_id) values(1586,2999);
INSERT INTO public."Empresa_Vehiculo" ("Empresa_id", vehiculos_id) values(1586,3999);

-- Empresa-Ciudadano

INSERT INTO public."Empresa_Ciudadano" ("Empresa_id", "choferes_id") values (1586, 5856);
INSERT INTO public."Empresa_Ciudadano" ("Empresa_id", "choferes_id") values (1587, 6856);

-- Guia de viaje
-- Viaje finalizado
INSERT INTO public."GuiaDeViaje" values(1000,'Montevideo','2023-05-02','2023-05-15','2023-05-02',1,'Artigas','Carnes','Producto no quimico',75.0);
-- Viaje iniciado sin finalizar
INSERT INTO public."GuiaDeViaje" values(1001,'Montevideo','2023-06-15',null,'2023-06-15',2,'Salto','Farmacos','Producto quimico',34.0);
-- Viaje sin iniciar
INSERT INTO public."GuiaDeViaje" values(1002,'Montevideo',current_date,null,null,3,'Colonia','Carnes','Producto no quimico',50.0);

-- Asignacion
INSERT INTO public."Asignacion" values(1000,'2023-05-02 11:23:00',1000);
INSERT INTO public."Asignacion" values(1001,'2023-05-15 12:23:53',1001);
INSERT INTO public."Asignacion" values(1002,current_timestamp,1002);

-- Chofer-Asignacion
INSERT INTO public."Ciudadano_Asignacion" values(5856,1000);
INSERT INTO public."Ciudadano_Asignacion" values(6856,1001);
INSERT INTO public."Ciudadano_Asignacion" values(6856,1002);

-- Vehiculo-Asignacion
INSERT INTO public."Vehiculo_Asignacion" ("Vehiculo_id", asignaciones_id) values(1999,1000);
INSERT INTO public."Vehiculo_Asignacion" ("Vehiculo_id", asignaciones_id) values(1999,1001);
INSERT INTO public."Vehiculo_Asignacion" ("Vehiculo_id", asignaciones_id) values(2999,1002);