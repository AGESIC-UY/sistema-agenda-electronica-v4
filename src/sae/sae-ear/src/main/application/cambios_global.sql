-- Este archivo contiene los cambios al esquema global que deben ejecutarse desde la versión 3.0.0

-- =================================================================
-- Versión 3.0.1

update ae_textos set texto='Relativo a  "java:jboss/exported/"; de la forma {war}-{class}!uy.gub.imm.sae.validaciones.business.ejb.ValidadorReservaRemote' where codigo='validacion_servicio_descripcion';

-- =================================================================
-- Versión 3.1.0

-- No hay cambios

-- =================================================================
-- Versión 3.2.0
INSERT INTO ae_textos (codigo, texto) VALUES ('debe_seleccionar_al_menos_un_recurso', 'Debe seleccionar al menos un recurso');
INSERT INTO ae_textos (codigo, texto) VALUES ('no_se_pudo_abrir_el_llamador', 'No se pudo abrir el llamador');
INSERT INTO ae_textos (codigo, texto) VALUES ('recursos_a_mostrar', 'Recursos a mostrar');
INSERT INTO ae_textos (codigo, texto) VALUES ('abrir_llamador_externo', 'Abrir el llamador externo');
INSERT INTO ae_textos (codigo, texto) VALUES ('abrir_llamador_interno', 'Abrir el llamador interno');

-- =================================================================
-- Versión 3.2.1

-- No hay cambios

-- =================================================================
-- Versión 3.2.2

-- No hay cambios


-- Versión 2.3.7 (2020-09-25)
INSERT INTO "global".ae_textos (codigo, texto) VALUES('administradorDeRecursos', 'Administrador de recursos');

-- Versión 4.0.0 (2020-11-06)
INSERT INTO "global".ae_textos (codigo,texto) VALUES ('seleccion_organismo','Selección de organismo');
INSERT INTO "global".ae_textos (codigo,texto) VALUES ('dispone_varios_organismos','Dispone de varios organismos, debe seleccionar uno para operar');
INSERT INTO "global".ae_textos (codigo,texto) VALUES ('debe_selecionar_un_organismo','Debe seleccionar un organismo');
INSERT INTO "global".ae_textos (codigo,texto) VALUES ('configuracion_del_organismo','Configuración del organismo');


-- Se eliminan valores en la tabla de configuración
delete from "global".ae_configuracion where clave in ('WS_TRAZABILIDAD_URLSTS','WS_TRAZABILIDAD_ROL','WS_TRAZABILIDAD_POLICY','WS_TRAZABILIDAD_ORG_KS_PATH',
'WS_TRAZABILIDAD_ORG_KS_PASS','WS_TRAZABILIDAD_ORG_KS_ALIAS','WS_TRAZABILIDAD_SSL_KS_PATH','WS_TRAZABILIDAD_SSL_KS_PASS','WS_TRAZABILIDAD_SSL_KS_ALIAS',
'WS_TRAZABILIDAD_SSL_TS_PATH','WS_TRAZABILIDAD_SSL_TS_PASS','WS_TRAZABILIDAD_WSATO_CABEZAL','WS_TRAZABILIDAD_WSAACTION_CABEZAL',
'WS_TRAZABILIDAD_WSAACTION_LINEA','WS_TRAZABILIDAD_WSATO_LINEA','WS_NOVEDADES_ORG_KS_ALIAS','WS_NOVEDADES_ORG_KS_PASS','WS_NOVEDADES_ORG_KS_PATH',
'WS_NOVEDADES_POLICY','WS_NOVEDADES_ROL','WS_NOVEDADES_SSL_KS_ALIAS','WS_NOVEDADES_SSL_KS_PASS','WS_NOVEDADES_SSL_KS_PATH','WS_NOVEDADES_SSL_TS_PASS',
'WS_NOVEDADES_SSL_TS_PATH','WS_NOVEDADES_URLSTS','WS_NOVEDADES_WSAACTION','WS_NOVEDADES_WSATO');

-- Función que realiza cambios en la tabla de configuración
-- Se elimina la primary key, se crean dos columnas nuevas id como primary key y org_id hace referencia al id del organismo
-- Se relacionan los mismos valores actuales a cada organismo
DO $$
DECLARE

	ColOrganismo record;
	contador integer;
	Configuracion record;
	
BEGIN

	contador:=1;
	
	-- recorrer la tabla de configuraciones y guardar esos valores
	CREATE TEMP TABLE tmp_configuracion ON COMMIT DROP AS
  	select * from "global".ae_configuracion;
   
	-- vaciar la tabla de configuraciones
	truncate table "global".ae_configuracion;	
	
	ALTER TABLE "global".ae_configuracion DROP CONSTRAINT ae_configuracion_pk;

	alter table "global".ae_configuracion add column org_id int4 not NULL;
	
	alter table "global".ae_configuracion add column id int4  NULL;
	
	ALTER TABLE ONLY "global".ae_configuracion ADD CONSTRAINT ae_configuracion_pk PRIMARY KEY (id);
	
	CREATE SEQUENCE "global".s_ae_configuracion START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;
	ALTER TABLE "global".s_ae_configuracion OWNER TO sae;
	
	ALTER TABLE "global".ae_configuracion ALTER COLUMN id SET DEFAULT nextval('global.s_ae_configuracion'::regclass);

	-- obtengo los organismos existentes
	FOR ColOrganismo IN(
		
		SELECT * from "global".ae_organismos where id is not null
		
	) LOOP
            
            FOR Configuracion IN (
				SELECT * from tmp_configuracion
            ) LOOP
	
                RAISE NOTICE '---CONFIGURACIÓN # -----%', contador;
                RAISE NOTICE '---INSERTANDO CONFIGURACIONES DEL ORGANISMO: -----%', ColOrganismo.id;
                contador:= contador + 1;

                INSERT INTO global.ae_configuracion (clave, valor,org_id) VALUES (Configuracion.clave,Configuracion.valor,ColOrganismo.id);	
            
            END LOOP;    

	END LOOP;	
	
END$$;

ALTER TABLE "global".ae_usuarios DROP COLUMN superadmin;


CREATE UNIQUE INDEX idx_clave_org
ON "global".ae_configuracion(clave, org_id);

-- Creación de indexes
CREATE INDEX ae_rel_usuarios_roles_usuario_id_idx ON "global".ae_rel_usuarios_roles(usuario_id);

CREATE INDEX ae_rel_usuarios_roles_empresa_id_idx ON "global".ae_rel_usuarios_roles(empresa_id);

CREATE INDEX ae_rel_usuarios_roles_rol_idx ON "global".ae_rel_usuarios_roles(rol_nombre) WHERE rol_nombre='RA_AE_ADMINISTRADOR';

CREATE INDEX ae_empresas_org_id_idx ON "global".ae_empresas(org_id);



-- Versión 4.0.1 (2020-01-25)
INSERT INTO "global".ae_textos (codigo,texto) VALUES ('reporte_recursos_por_agenda','Reporte de Recursos por Agenda');

CREATE TABLE "global".ae_rel_usuarios_organismos (
    usuario_id integer NOT NULL,
    org_id integer NOT NULL
);
ALTER TABLE "global".ae_rel_usuarios_organismos OWNER TO sae;

ALTER TABLE ONLY "global".ae_rel_usuarios_organismos ADD CONSTRAINT ae_rel_usuarios_organismos_pkey PRIMARY KEY (usuario_id, org_id);

-- Versión 4.1.0 (2020-02-26)
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('la_fecha_hasta_debe_ser_posterior_a_la_fecha_desde', 'La fecha hasta debe ser posterior a la fecha desde');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('debe_especificar_la_fecha_reserva_dos', 'Debe especificar la fecha para la reserva de la segunda dosis');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('debe_especificar_el_tipo_doc_reserva_dos', 'Debe especificar el tipo de documento para la reserva de la segunda dosis');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('no_se_encuentra_la_disponibilidad2_especificada', 'No se encuentra la disponibilidad para la reserva de la segunda dosis');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('error_marcar_reservas_pares', 'Error: no se ha podido crear las reservas de primera y segunda dosis');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('debe_especificar_la_reserva_segunda', 'Debe especificar la reserva de la segunda dosis');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('la_reserva_primer_dosis_esta_utilizada', 'La reserva de primer dosis está utilizada');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('no_se_puede_cancelar_la_reserva_dos', 'No se puede cancelar la reserva de la segunda dosis por si sola, la cancelación se hace mediante la reserva de la primer dosis y el sistema cancela ambas reservas');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('no_existen_reservas_para_confirmar', 'No existen reservas para confirmar');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('envio_novedades_como_dato_extra', 'Envío a novedades como dato extra');
INSERT INTO "global".ae_configuracion (clave, valor, org_id) VALUES('WS_NOVEDADES_XML_LOG', 'false', {organismo_id});
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('la_fecha_reserva_dos_debe_ser_posterior_a_la_fecha_reserva_uno', 'La fecha de la reserva dos debe ser posterior a la fecha de la reserva uno');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('error_marcar_reserva', 'Error: no se ha podido crear la reserva');

-- Versión 4.2.0 (2020-05-19)
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('reserva_pendiente_tiempo_max', 'Reserva pendiente tiempo máximo');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('reserva_multiple_pendiente_tiempo_max', 'Reserva múltiple pendiente tiempo máximo');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('reserva_pendiente_tiempo_max_es_obligatoria', 'El tiempo máximo para una reserva pendiente es obligatoria');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('reserva_multiple_pendiente_tiempo_max_es_obligatoria', 'El tiempo máximo para las reservas múltiples pendientes es obligatoria');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('reserva_pendiente_tiempo_max_debe_ser_mayor_a_cero', 'El tiempo máximo para una reserva pendiente debe ser mayor a cero');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('reserva_multiple_pendiente_tiempo_max_debe_ser_mayor_a_cero', 'El tiempo máximo para las reservas múltiples pendientes debe ser mayor a cero');

-- Versión 4.3.0 (2021-07-22)
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('mover_reservas', 'Mover reservas');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('datos_de_reservas_a_mover', 'Datos de las reservas a mover');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('agenda_origen', 'Agenda origen');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('recurso_origen', 'Recurso origen');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('agenda_destino', 'Agenda destino');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('recurso_destino', 'Recurso destino');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('fecha_reservas', 'Fecha de las reservas');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('enviar_correo', 'Enviar notificaciones a los usuarios');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('generar_novedades', 'Generar novedades');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('no_se_pudo_cargar_lista_de_agendas', 'No se pudo cargar la lista de agendas');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('no_se_pudo_cargar_lista_de_recursos', 'No se pudo cargar la lista de recursos');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('confirmar_movimiento_reservas', 'Confirmar movimiento de reservas');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('ejecutar', 'Ejecutar');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('validar', 'Validar');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('correo_de_traslado', 'Correo de traslado');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('texto_para_el_correo_de_traslado', 'Texto para el correo de traslado');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('nombre_de_la_agenda_origen', 'Nombre de la agenda de origen');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('nombre_del_recurso_origen', 'Nombre del recurso de origen');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('nombre_de_la_agenda_destino', 'Nombre de la agenda de destino');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('nombre_del_recurso_destino', 'Nombre del recurso de destino');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('no_se_puedo_mover_las_reservas', 'No se pudo mover todas las reservas');

INSERT INTO "global".ae_textos (codigo, texto) VALUES ('paso_uno_datos_reservas_mover', 'Paso 1 - Datos de las reservas a mover');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('paso_dos_datos_reservas_mover', 'Paso 2 - Datos del recurso destino de las reservas');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('siguiente', 'Siguiente');
INSERT INTO "global".ae_textos(codigo, texto)  VALUES ('no_existen_reservas_recurso_origen', 'No existen reservas a mover en la fecha y horario seleccionado');
INSERT INTO "global".ae_textos(codigo, texto)  VALUES ('recurso_origen_recurso_destino_hora_inicio_distintas', 'Si el recurso origen es igual al recurso destino, las horas inicio deben ser distintas');
INSERT INTO "global".ae_textos(codigo, texto)  VALUES ('no_se_validar_las_reservas', 'No se ha podido validar en el paso 2');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('debe_seleccionar_agenda_recurso_destino', 'Debe seleccionar agenda destino y recurso destino');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('fecha_destino', 'Fecha destino');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('fecha_destino_vacia', 'Seleccione una fecha destino'); 

INSERT INTO "global".ae_textos (codigo, texto) VALUES ('envio_de_comunicacion', 'Envío de comunicación');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('aplica_envio_de_correo', 'Aplica envío de correo electrónico');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('actualizacion_masiva', 'Actualización Masiva');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('no_enviar_comunicacion_cancelacion', 'Tenga en cuenta que no se enviará una comunicación de cancelación');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('todos_los_recursos', 'Todos los recursos');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('agendas_recursos', 'Agendas y Recursos');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('aplicar_todos', 'Aplicar a todos');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('seleccionar_recursos', 'Seleccionar Recursos');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('aplicar_todos_recursos', 'Aplicar a todos los recursos');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('mensaje_aplica_para_todos', 'En caso de estar seleccionado afectará a todos los recursos de todas las agendas de la empresa seleccionada.');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('recursos_agenda', 'Recursos de la agenda');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('recursos_confirmados', 'Recursos confirmados');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('confirmar_recursos', 'Confirmar recursos');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('actualizacion_recursos', 'Se han modificado x recursos de la agenda actualmente seleccionada');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('actualizar_datos', 'Actualizar datos');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('actualizacion_masiva_datos', 'Actualización masiva de datos');
INSERT INTO "global".ae_textos (codigo, texto) VALUES ('carga_masiva', 'Carga Masiva');
UPDATE "global".ae_textos SET texto='Correo de cambio de recurso' WHERE codigo='correo_de_traslado';
UPDATE "global".ae_textos SET texto='Tenga en cuenta que no se ha podido enviar la notificación por correo electrónico; tome nota de los datos de la reserva.' WHERE codigo='no_se_pudo_enviar_notificacion_de_confirmacion_tome_nota_de_los_datos_de_la_reserva';
INSERT INTO "global".ae_textos(codigo, texto) VALUES('carga_masiva_recursos_disponibilidades', 'Carga masiva de recursos y disponibilidades');

-- Ejecutar para cada organismo estas dos variables de configuración
INSERT INTO "global".ae_configuracion (clave, valor,org_id) VALUES('CARGA_MASIVA_DIAS_RECURSOS_NUEVOS', '30',{organismo_id});
INSERT INTO "global".ae_configuracion (clave, valor,org_id) VALUES('CARGA_MASIVA_DIAS_RECURSOS_EXISTENTES', '15',{organismo_id});


-- Versión 4.4.0 (2021-10-08)
INSERT INTO global.ae_textos(codigo, texto) VALUES ('consultar_usuarios_por_empresa', 'Consultar usuarios por empresa');
INSERT INTO global.ae_textos(codigo, texto) VALUES ('roles', 'Roles');
INSERT INTO global.ae_textos(codigo, texto) VALUES ('valor_en_traza', 'Valor en Traza');
INSERT INTO global.ae_textos(codigo, texto) VALUES ('ingrese_el_numero_de_puesto_en_el_campo_correcto', 'Ingrese el numero de puesto en el campo correcto');

-- Versión 4.4.1 (2021-12-06)
update global.ae_textos set texto='Se han modificado x recursos' where codigo = 'actualizacion_recursos';
INSERT INTO global.ae_textos(codigo, texto) VALUES ('reporte_errores_actualizacion_masiva_reservas', 'Reporte de errores de actualización masiva de recursos');

-- Versión 4.4.2 (2021-12-27)
INSERT INTO global.ae_textos(codigo, texto) VALUES ('descargar', 'Descargar');
INSERT INTO global.ae_textos(codigo, texto) VALUES ('mensaje_reporte_disponible', 'Reporte disponible, use el botón Descargar.');

-- Versión 4.4.3 (2024-01-04)
INSERT INTO global.ae_textos (codigo, texto) VALUES ('volver_paso_anterior', 'Volver al Paso Anterior');
ALTER TABLE global.ae_usuarios ALTER COLUMN ultimo_login TYPE TIMESTAMP WITH TIME ZONE USING ultimo_login AT TIME ZONE 'UTC';
INSERT INTO global.ae_textos (codigo, texto) VALUES ('ultimo_login', 'Último Login');