-- Este archivo contiene los cambios a todos los esquemas de empresas que deben ejecutarse desde la versión 3.0.0

-- =================================================================
-- Versión 3.0.1

-- No hay cambios

-- =================================================================
-- Versión 3.1.0

-- No hay cambios

-- =================================================================
-- Versión 3.2.0

-- No hay cambios

-- =================================================================
-- Versión 3.2.1

-- No hay cambios

-- =================================================================
-- Versión 3.2.2

-- No hay cambios


-- Versión 2.3.7 (2020-09-25)
-- Se agregra una nueva tabla que es la de auditoría de recursos

CREATE TABLE {esquema}.ae_recursos_aud (
	id int4 NOT NULL,
	id_recurso int4 NOT NULL,
	nombre varchar(100) NOT NULL,
	descripcion varchar(1000) NOT NULL,
	fecha_inicio timestamp NOT NULL,
	fecha_fin timestamp NULL,
	fecha_inicio_disp timestamp NOT NULL,
	fecha_fin_disp timestamp NULL,
	dias_inicio_ventana_intranet int4 NOT NULL,
	dias_ventana_intranet int4 NOT NULL,
	dias_inicio_ventana_internet int4 NOT NULL,
	dias_ventana_internet int4 NOT NULL,
	ventana_cupos_minimos int4 NOT NULL,
	cant_dias_a_generar int4 NOT NULL,
	largo_lista_espera int4 NULL,
	fecha_baja timestamp NULL,
	mostrar_numero_en_llamador bool NOT NULL,
	visible_internet bool NOT NULL,
	usar_llamador bool NOT NULL,
	serie varchar(3) NULL,
	sabado_es_habil bool NOT NULL,
	domingo_es_habil bool NOT NULL DEFAULT false,
	mostrar_numero_en_ticket bool NOT NULL,
	mostrar_id_en_ticket bool NULL,
	fuente_ticket varchar(100) NOT NULL DEFAULT 'Helvetica-Bold'::character varying,
	tamanio_fuente_grande int4 NOT NULL DEFAULT 12,
	tamanio_fuente_normal int4 NOT NULL DEFAULT 10,
	tamanio_fuente_chica int4 NOT NULL DEFAULT 8,
	oficina_id varchar(25) NULL,
	direccion varchar(100) NULL,
	localidad varchar(100) NULL,
	departamento varchar(100) NULL,
	telefonos varchar(100) NULL,
	horarios varchar(100) NULL,
	latitud numeric NULL,
	longitud numeric NULL,
	agenda int4 NOT NULL,
	presencial_admite bool NOT NULL DEFAULT false,
	presencial_cupos int4 NOT NULL DEFAULT 0,
	presencial_lunes bool NOT NULL DEFAULT false,
	presencial_martes bool NOT NULL DEFAULT false,
	presencial_miercoles bool NOT NULL DEFAULT false,
	presencial_jueves bool NOT NULL DEFAULT false,
	presencial_viernes bool NOT NULL DEFAULT false,
	presencial_sabado bool NOT NULL DEFAULT false,
	presencial_domingo bool NOT NULL DEFAULT false,
	multiple_admite bool NOT NULL DEFAULT false,
	cambios_admite bool NOT NULL DEFAULT false,
	cambios_tiempo int4 NULL,
	cambios_unidad int4 NULL,
	periodo_validacion int4 NOT NULL DEFAULT 0,
	validar_por_ip bool NOT NULL DEFAULT false,
	cantidad_por_ip int4 NULL,
	periodo_por_ip int4 NULL,
	ips_sin_validacion varchar(4000) NULL DEFAULT NULL::character varying,
	cancela_tiempo int4 NOT NULL DEFAULT 0,
	cancela_unidad int4 NOT NULL DEFAULT 12,
	cancela_tipo varchar(1) NOT NULL DEFAULT 'I'::character varying,
	mi_perfil_con_hab bool NOT NULL DEFAULT true,
	mi_perfil_con_tit varchar(200) NULL DEFAULT NULL::character varying,
	mi_perfil_con_cor varchar(500) NULL DEFAULT NULL::character varying,
	mi_perfil_con_lar varchar(3200) NULL DEFAULT NULL::character varying,
	mi_perfil_con_ven int4 NULL,
	mi_perfil_can_hab bool NOT NULL DEFAULT true,
	mi_perfil_can_tit varchar(200) NULL DEFAULT NULL::character varying,
	mi_perfil_can_cor varchar(500) NULL DEFAULT NULL::character varying,
	mi_perfil_can_lar varchar(3200) NULL DEFAULT NULL::character varying,
	mi_perfil_can_ven int4 NULL,
	mi_perfil_rec_hab bool NOT NULL DEFAULT true,
	mi_perfil_rec_tit varchar(200) NULL DEFAULT NULL::character varying,
	mi_perfil_rec_cor varchar(500) NULL DEFAULT NULL::character varying,
	mi_perfil_rec_lar varchar(3200) NULL DEFAULT NULL::character varying,
	mi_perfil_rec_ven int4 NULL,
	mi_perfil_rec_hora int4 NULL,
	mi_perfil_rec_dias int4 NULL,
	
	fecha_modificacion timestamp NULL,
	usuario varchar(45) NULL,
	"version" int4 NOT NULL,
	tipo_operacion int2 null, -- 0 creación, 1 modificación y 2 eliminación;,
	CONSTRAINT ae_recursos_aud_pkey PRIMARY KEY (id)
);
ALTER TABLE ae_recursos_aud OWNER TO sae;

CREATE SEQUENCE {esquema}.s_ae_recurso_aud START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;
ALTER TABLE s_ae_recurso_aud OWNER TO sae;


--Versión 4.0.0 (2020-10-05)
-- No hay cambios 

--Versión 4.2.0 (2021-05-20)
ALTER TABLE {esquema}.ae_recursos ADD COLUMN reserva_pen_tiempo_max integer DEFAULT NULL;
ALTER TABLE {esquema}.ae_recursos_aud ADD COLUMN reserva_pen_tiempo_max integer DEFAULT NULL;

ALTER TABLE {esquema}.ae_recursos ADD COLUMN reserva_multiple_pend_tiempo_max integer DEFAULT NULL;
ALTER TABLE {esquema}.ae_recursos_aud ADD COLUMN reserva_multiple_pend_tiempo_max integer DEFAULT NULL;

-- Ejecutar este script especificando el esquema de base de datos de cada empresa para actualizar los dos nuevos parámetros en todos los recursos de una empresa
-- (cambiar los valores de 10 por los valores reales)
update {esquema}.ae_recursos set reserva_pen_tiempo_max=10;
update {esquema}.ae_recursos set reserva_multiple_pend_tiempo_max=10;

-- Ejecutar este script especificando el esquema de base de datos de cada empresa para actualizar los dos nuevos parámetros en los recursos de una agenda de una empresa
-- (cambiar los valores de 10 por los valores reales)
update {esquema}.ae_recursos set reserva_pen_tiempo_max=10 where aeag_id=10;
update {esquema}.ae_recursos set reserva_multiple_pend_tiempo_max=10 where aeag_id=10;

--Versión 4.3.0 (2021-07-27)
-- Ejecutar estos scripts especificando el esquema de base de datos de cada empresa, se agregan dos columnas en la tabla ae_recursos y ae_recursos_aud
ALTER TABLE {esquema}.ae_reservas ADD id_origen int4 NULL;
ALTER TABLE {esquema}.ae_textos_agenda ADD texto_correo_tras varchar(1000) NULL;

alter table {esquema}.ae_recursos alter column mi_perfil_con_cor set data type varchar(500);
alter table {esquema}.ae_recursos alter column mi_perfil_can_cor set data type varchar(500);
alter table {esquema}.ae_recursos alter column mi_perfil_rec_cor set data type varchar(500);

alter table {esquema}.ae_recursos alter column mi_perfil_con_hab set default false;
alter table {esquema}.ae_recursos alter column mi_perfil_can_hab set default false;
alter table {esquema}.ae_recursos alter column mi_perfil_rec_hab set default false;

CREATE INDEX ae_reservas_estado_idx ON {esquema}.ae_reservas USING btree (estado);
CREATE INDEX ae_reservas_fcrea_idx ON {esquema}.ae_reservas USING btree (fcrea);
CREATE INDEX ae_disponibilidades_aere_id_idx ON {esquema}.ae_disponibilidades USING btree (aere_id);
CREATE INDEX ae_datos_reserva_aers_id_idx ON {esquema}.ae_datos_reserva USING btree (aers_id);
CREATE INDEX ae_datos_reserva_aeds_id_idx ON {esquema}.ae_datos_reserva USING btree (aeds_id);
CREATE INDEX ae_datos_reserva_valor_idx ON {esquema}.ae_datos_reserva USING btree (valor);
CREATE INDEX ae_disponibilidades_fecha_idx ON {esquema}.ae_disponibilidades USING btree (fecha);
CREATE INDEX ae_datos_a_solicitar_nombre_idx ON {esquema}.ae_datos_a_solicitar USING btree (nombre);

-- Versión 4.4.0 (2021-10-08)

ALTER TABLE {esquema}.ae_valores_del_dato ADD valor_en_traza varchar(50);

-- 4.4.1 (2021-12-06)
-- Nada para hacer

-- =======================================================================================================================
