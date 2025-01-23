\c sae
-- Función anónima para crear un nuevo organismo con sus respectivas configuraciones
-- Sustituir los valores de la línea 13 con los datos del nuevo organismo

DO $$
DECLARE
	orgid integer;	

BEGIN

	
	-- insertar organismo
	INSERT INTO "global".ae_organismos (id,codigo,nombre)
	values(1,'1','sae_development')
	returning id into orgid;
		
	
	
	-- obtengo los organismos existentes
	INSERT INTO global.ae_configuracion (clave, valor,org_id) VALUES ('DOMINIO','',orgid);
	INSERT INTO global.ae_configuracion (clave, valor,org_id) VALUES ('GOOGLE_ANALYTICS','',orgid);
	INSERT INTO global.ae_configuracion (clave, valor,org_id) VALUES ('IDIOMAS_SOPORTADOS','es',orgid);
	INSERT INTO global.ae_configuracion (clave, valor,org_id) VALUES ('JSON_ESCAPE','FALSE',orgid);
	INSERT INTO global.ae_configuracion (clave, valor,org_id) VALUES ('MIPERFIL_OID','',orgid);
	INSERT INTO global.ae_configuracion (clave, valor,org_id) VALUES ('MOSTRAR_FECHA_ACTUAL','TRUE',orgid);
	INSERT INTO global.ae_configuracion (clave, valor,org_id) VALUES ('RESERVA_MULTIPLE_PENDIENTE_TIEMPO_MAXIMO','2880',orgid);
	INSERT INTO global.ae_configuracion (clave, valor,org_id) VALUES ('RESERVA_PENDIENTE_TIEMPO_MAXIMO','10',orgid);
	INSERT INTO global.ae_configuracion (clave, valor,org_id) VALUES ('WS_MIPERFIL_HABILITADO','FALSE',orgid);
	INSERT INTO global.ae_configuracion (clave, valor,org_id) VALUES ('WS_MIPERFIL_KS_PASS','',orgid);
	INSERT INTO global.ae_configuracion (clave, valor,org_id) VALUES ('WS_MIPERFIL_KS_PATH','',orgid);
	INSERT INTO global.ae_configuracion (clave, valor,org_id) VALUES ('WS_MIPERFIL_TS_PASS','',orgid);
	INSERT INTO global.ae_configuracion (clave, valor,org_id) VALUES ('WS_MIPERFIL_TS_PATH','',orgid);
	INSERT INTO global.ae_configuracion (clave, valor,org_id) VALUES ('WS_MIPERFIL_URL','',orgid);
	INSERT INTO global.ae_configuracion (clave, valor,org_id) VALUES ('WS_NOVEDADES_HABILITADO','FALSE',orgid);
	INSERT INTO global.ae_configuracion (clave, valor,org_id) VALUES ('WS_NOVEDADES_LOCATION','',orgid);
	INSERT INTO global.ae_configuracion (clave, valor,org_id) VALUES ('WS_NOVEDADES_MAXINTENTOS','10',orgid);
	INSERT INTO global.ae_configuracion (clave, valor,org_id) VALUES ('WS_NOVEDADES_PRODUCTOR','',orgid);
	INSERT INTO global.ae_configuracion (clave, valor,org_id) VALUES ('WS_NOVEDADES_TIMEOUT','3500',orgid);
	INSERT INTO global.ae_configuracion (clave, valor,org_id) VALUES ('WS_NOVEDADES_TOPICO','',orgid);
        INSERT INTO global.ae_configuracion (clave, valor,org_id) VALUES ('WS_NOVEDADES_VERSION','',orgid);
	INSERT INTO global.ae_configuracion (clave, valor,org_id) VALUES ('WS_TRAMITE_LOCATION_GUIA','',orgid);
	INSERT INTO global.ae_configuracion (clave, valor,org_id) VALUES ('WS_TRAMITE_LOCATION_INFO','',orgid);
	INSERT INTO global.ae_configuracion (clave, valor,org_id) VALUES ('WS_TRAMITE_PASS','',orgid);
	INSERT INTO global.ae_configuracion (clave, valor,org_id) VALUES ('WS_TRAMITE_TIMEOUT','9000',orgid);
	INSERT INTO global.ae_configuracion (clave, valor,org_id) VALUES ('WS_TRAMITE_USER','',orgid);
	INSERT INTO global.ae_configuracion (clave, valor,org_id) VALUES ('WS_TRAZABILIDAD_HABILITADO','FALSE',orgid);
	INSERT INTO global.ae_configuracion (clave, valor,org_id) VALUES ('WS_TRAZABILIDAD_LOCATION_CABEZAL','',orgid);
	INSERT INTO global.ae_configuracion (clave, valor,org_id) VALUES ('WS_TRAZABILIDAD_LOCATION_LINEA','',orgid);
	INSERT INTO global.ae_configuracion (clave, valor,org_id) VALUES ('WS_TRAZABILIDAD_MAXINTENTOS','10',orgid);
	INSERT INTO global.ae_configuracion (clave, valor,org_id) VALUES ('WS_TRAZABILIDAD_TIMEOUT','3500',orgid);
        INSERT INTO global.ae_configuracion (clave, valor,org_id) VALUES ('WS_TRAZABILIDAD_VERSION','',orgid);




END$$;

