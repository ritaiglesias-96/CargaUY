INSERT INTO public."Usuario"(tipo_usuario, id, apellido, nombre, fechaNacimiento, correo, username, password) values('administrador', 100, 'Iglesias', 'Rita', '1996-06-12 00:00:00', 'rita.iglesias.adrover@gmail.com', 'admin', '21232F297A57A5A743894A0E4A801FC3');


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

---Guia de Viaje
INSERT INTO public."GuiaDeViaje" (id, destino, fecha, fin, inicio, numero, origen, rubrocliente, tipocarga, volumencarga) VALUES (1, 'Peñarol', '2023-06-06', null, null, 75, 'Cordon', 'Rubro', 'Tipo', 754);
INSERT INTO public."GuiaDeViaje" (id, destino, fecha, fin, inicio, numero, origen, rubrocliente, tipocarga, volumencarga) VALUES (2, 'Buceo', '2023-06-06', null, null, 21, 'Palacio Legislativo', 'Bondi', 'Gente', 1000);

---Asignacion
INSERT INTO public."Asignacion" (id, fechacambio, guia_id) VALUES (1, '2023-06-12 19:25:08.000000', 1);
INSERT INTO public."Asignacion" (id, fechacambio, guia_id) VALUES (2, '2023-06-14 15:40:00.000000', 2);

---Ciudadano_Asignacion
INSERT INTO public."Ciudadano_Asignacion" ("Chofer_id", asignaciones_id) VALUES (5, 1);
INSERT INTO public."Ciudadano_Asignacion" ("Chofer_id", asignaciones_id) VALUES (5, 2);