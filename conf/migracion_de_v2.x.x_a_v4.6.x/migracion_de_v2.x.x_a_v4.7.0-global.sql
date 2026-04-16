-- =======================================================================================================================
-- 2.0.1

INSERT INTO global.ae_textos (codigo, texto) VALUES ('quitar_logo', 'Quitar logo');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('debe_seleccionar_al_menos_un_dia', 'Debe selecconar al menos un día de la semana');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('la_fecha_debe_ser_igual_o_posterior_a_hoy', 'La fecha debe ser igual o posterior a hoy');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('la_fecha_de_inicio_debe_ser_igual_o_posterior_a_hoy', 'La fecha de inicio debe ser igual o posterior a hoy');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('la_fecha_de_fin_debe_ser_igual_o_posterior_a_hoy', 'La fecha de fin debe ser igual o posterior a hoy');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('la_recurso_no_esta_vigente', 'El recurso no está vigente');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('configuracion_del_ticket', 'Configuración del ticket');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('fuente', 'Fuente');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('tamanio_fuente_chica', 'Tamaño de la fuente chica');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('tamanio_fuente_normal', 'Tamaño de la fuente normal');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('tamanio_fuente_grande', 'Tamaño de la fuente grande');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('previsualizar_ticket', 'Previsualizar ticket');

-- =======================================================================================================================
-- 2.0.2

INSERT INTO global.ae_textos (codigo, texto) VALUES ('es_la_ultima_empresa', 'Es la última empresa');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('debe_quedar_al_menos_una_empresa', 'Debe quedar al menos una empresa viva');

-- =======================================================================================================================
-- 2.0.3

UPDATE global.ae_textos SET texto='Hay {count} errores en el formulario' WHERE codigo='mensajes_en_el_formulario_error';
INSERT INTO global.ae_textos (codigo, texto) VALUES ('la_fecha_de_inicio_debe_ser_anterior_a_la_fecha_de_fin', 'La fecha de inicio debe ser anterior a la fecha de fin');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('no_se_pudo_realizar_la_importacion', 'No se pudo realizar la importación');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('la_fecha_de_inicio_es_invalida', 'La fecha de inicio es inválida');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('la_fecha_de_fin_es_invalida', 'La fecha de fin es inválida');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('el_campo_campo_no_tiene_una_fecha_válida', 'El valor del campo {campo} no es una fecha válida');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('la_fecha_de_inicio_de_vigencia_es_invalida', 'La fecha de inicio de vigencia es inválida');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('la_fecha_de_fin_de_vigencia_es_invalida', 'La fecha de fin de vigencia es inválida');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('la_fecha_de_inicio_de_disponibilidad_es_invalida', 'La fecha de inicio de atención al público es inválida');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('la_fecha_de_fin_de_disponibilidad_es_invalida', 'La fecha de fin de atención al público es inválida');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('la_fecha_es_invalida', 'La fecha es inválida');

-- =======================================================================================================================
-- 2.0.4

INSERT INTO global.ae_textos (codigo, texto) VALUES ('pagina', 'Página');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('pagina_anterior', 'Anterior');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('pagina_siguiente', 'Siguiente');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('pagina_primera', 'Primera');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('pagina_ultima', 'Última');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('no_hay_cupos_disponibles_para_el_recurso', 'En la oficina seleccionada no hay cupos disponibles');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('proximamente_se_añadiran_cupos', 'A la brevedad se añadirán cupos');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('ya_tiene_una_reserva_para_el_dia_seleccionado', 'Ya tiene una reserva para el día seleccionado');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('solo_se_permite_una_reserva_diaria', 'Solo se permite una reserva diaria');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('volver_al_paso_anterior_para_seleccionar_otro_dia', 'Puede volver al paso anterior para seleccionar otro día disponible');

-- =======================================================================================================================
-- 2.0.5

-- Nada para hacer

-- =======================================================================================================================
-- 2.0.6

INSERT INTO global.ae_textos (codigo, texto) VALUES ('mensaje_en_el_formulario_error', 'Hay un error en el formulario');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('mensaje_en_el_formulario_info', 'Ejecución exitosa');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('mensaje_en_el_formulario_warn', 'Hay una advertencia a la cual debe prestar atención');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('el_periodo_no_puede_ser_mayor_a_un_ano', 'El período no puede ser mayor a un año');

-- =======================================================================================================================
-- 2.0.7

INSERT INTO global.ae_configuracion (clave, valor) VALUES ('GOOGLE_ANALYTICS', '');

INSERT INTO global.ae_textos (codigo, texto) VALUES ('configuracion', 'Configuración');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('configuracion_global', 'Configuración global');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('editar_configuracion', 'Editar configuración');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('solicite_turno_para_ser_atendido', 'Solicite turno para ser atendido');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('no_tengo_cedula', 'No tengo cédula de identidad');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('solicitar_turno', 'Solicitar turno');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('debe_seleccionar_el_tipo_de_documento', 'Debe seleccionar el tipo de documento');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('debe_ingresar_el_numero_de_documento', 'Debe ingresar el número de documento');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('solicitud_confirmada', 'Solicitud confirmada');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('sera_llamado_por_documento', 'Será llamado por su número de documento');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('solicitud_de_atencion', 'Solicitud de atención');

-- =======================================================================================================================
-- 2.0.8

UPDATE global.ae_textos SET codigo='el_campo_campo_no_tiene_una_fecha_valida' WHERE codigo='el_campo_campo_no_tiene_una_fecha_válida';
UPDATE global.ae_textos SET texto='Continuar con el trámite' WHERE codigo='continuar_tramite';

INSERT INTO global.ae_textos (codigo, texto) VALUES ('no_tiene_acceso_al_recurso_o_no_existe', 'No tiene acceso al recurso solicitado o éste no existe');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('si_error_contacte_al_administrador', 'Si cree que es un error contacte al administrador');

-- =======================================================================================================================
-- 2.0.8.R1

-- Nada para hacer

-- =======================================================================================================================
-- 2.1.0

INSERT INTO global.ae_textos (codigo, texto) VALUES ('configuracion_de_reserva_multiple', 'Configuración de reserva múltiple');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('reserva_multiple', 'Reserva múltiple');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('admite_reserva_multiple', 'Admite reserva múltiple');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('reservas_incluidas', 'Reservas incluidas');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('no_es_posible_cambiar_de', 'No es posible cambiar de');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('ya_hay_una_reserva', 'Ya hay una reserva añadida');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('confirmar_reservas', 'Confirmar reservas');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('confirmacion_de_reservas', 'Confirmación de reservas');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('reservas_confirmadas', 'Todas las reservas están confirmadas');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('fecha_y_hora', 'Fecha y hora');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('seleccionar_recurso', 'Seleccionar recurso');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('otra_reserva', 'Otra reserva');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('la_cedula_es_obligatoria', 'La cédula de identidad es requerida');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('identificacion', 'Identificación');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('cancelar_reservas', 'Cancelar reservas');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('el_token_esta_cancelado', 'Las reservas ya están canceladas');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('el_token_esta_confirmado', 'Las reservas ya están confirmadas');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('no_es_posible_cambiar_de_recurso', 'No es posible cambiar de recurso');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('no_se_encuentra_el_token', 'No se encuentra el token especificado');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('el_token_ha_expirado', 'El token especificado ha expirado');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('no_es_posible_cambiar_de_tramite', 'No es posible cambiar de trámite');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('confirma_cancelar_las_reservas', '¿Confirma la cancelación de todas las reservas?');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('el_recurso_no_admite_reservas_multiples', 'El recurso indicado no admite reservas múltiples');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('debe_especificar_el_token_de_reservas', 'Debe especificar el token de reservas múltiples');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('la_reserva_no_corresponde_al_token', 'La reserva no corresponde al token');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('reserva_original', 'Reserva original');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('seleccionar_reserva', 'Seleccionar reserva');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('reserva_nueva', 'Reserva nueva');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('modificar_reserva', 'Modificar reserva');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('debe_especificar_los_datos_personales', 'Debe especificar los datos personales');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('debe_especificar_el_tramite', 'Debe especificar el trámite');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('no_se_encuentra_el_token_de_reservas_especificado', 'No se encuentra el token de reservas especificado');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('debe_especificar_el_token', 'Debe especificar el token');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('la_reserva_esta_utilizada', 'La reserva está utilizada');

UPDATE global.ae_textos SET texto='Código de seguridad' WHERE codigo='codigo_de_seguridad';

INSERT INTO global.ae_configuracion (clave, valor) VALUES ('RESERVA_PENDIENTE_TIEMPO_MAXIMO', '10');
INSERT INTO global.ae_configuracion (clave, valor) VALUES ('RESERVA_MULTIPLE_PENDIENTE_TIEMPO_MAXIMO', '2880');

-- =======================================================================================================================
-- 2.1.1

INSERT INTO global.ae_textos (codigo, texto) VALUES ('configuracion_de_cambios_de_reservas', 'Configuración de cambios de reservas');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('cambios_reserva', 'Cambios de reservas');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('admite_cambios_reserva', 'Admite cambios de reservas');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('tiempo_previo', 'Tiempo previo');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('tiempo_unidad', 'Unidad de tiempo');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('dias', 'Días');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('horas', 'Horas');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('el_tiempo_previo_para_cambios_es_requerido', 'El tiempo previo para cambios es requerido');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('el_tiempo_previo_para_cambios_debe_ser_mayor_a_cero', 'El tiempo previo para cambios debe ser mayor a cero');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('enlace_a_la_pagina_de_modificacion', 'Enlace a la página de modificación');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('el_recurso_no_admite_cambios_de_reservas', 'El recurso no admite cambios de reservas');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('la_reserva_especificada_ya_no_admite_cambios', 'La reserva ya no admite cambios');

-- =======================================================================================================================
-- 2.2.0

INSERT INTO global.ae_textos (codigo, texto) VALUES ('periodo_validacion', 'Período de validación');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('ya_tiene_una_reserva_para_el_periodo', 'Ya tiene una reserva para el período {periodo}');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('el_periodo_de_validacion_es_obligatorio', 'El período de validación es obligatorio');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('el_periodo_de_validacion_debe_ser_mayor_o_igual_a_cero', 'El período de validación debe ser mayor o igual a cero');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('solo_se_permite_una_reserva_en_un_periodo_de_dias', 'Solo se permite una reserva en un período de {dias} días');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('configuracion_de_validacion_por_ip', 'Configuración de validación por dirección IP');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('validar_por_ip', 'Validar por dirección IP');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('cantidad_reservas_por_ip', 'Cantidad de reservas por dirección IP');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('periodo_validacion_por_ip', 'Período de validación');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('direcciones_ip_sin_validacion', 'Direcciones IP sin validación');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('la_cantidad_de_reservas_por_ip_es_obligatoria', 'La cantidad de reservas por IP es obligatoria');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('la_cantidad_de_reservas_por_ip_debe_ser_mayor_a_cero', 'La cantidad de reservas por IP debe ser mayor a cero');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('el_periodo_de_validacion_por_ip_es_obligatorio', 'El período de validación por IP es obligatorio');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('el_periodo_de_validacion_por_ip_debe_ser_mayor_o_igual_a_cero', 'El período de validación por IP debe ser mayor o igual a cero');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('limite_de_reservas_para_la_direccion_ip_alcanzado', 'No se admiten más de {cantidad} reservas desde una misma dirección IP');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('consulta_de_cancelaciones', 'Consulta de cancelaciones');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('creacion', 'Creación');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('cancelacion', 'Cancelación');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('todos', 'Todos');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('todas', 'Todas');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('cantidad_de_elementos', 'Cantidad de elementos');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('configuracion_de_cancelaciones_de_reservas', 'Configuración de cancelaciones de reservas');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('cancelaciones_de_reservas', 'Cancelaciones de reservas');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('tipo_liberacion_cupo', 'Tipo de liberación del cupo');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('inmediata', 'Inmediata');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('diferida', 'Diferida');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('el_tiempo_previo_para_cancelaciones_es_requerido', 'El tiempo previo para cancelaciones es requerido');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('el_tiempo_previo_para_cancelaciones_debe_ser_mayor_o_igual_a_cero', 'El tiempo previo para cancelaciones debe ser mayor o igual a cero');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('el_tipo_de_cancelacion_es_obligatorio', 'El tipo de cancelación es obligatorio');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('ha_expirado_el_plazo_de_cancelacion', 'Ha expirado el plazo de cancelación');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('fecha_de_liberacion', 'Fecha de liberación');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('liberar', 'Liberar');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('no_se_encuentra_la_reserva_o_no_esta_cancelada', 'No se encuentra la reserva o no está cancelada');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('reserva_liberada', 'El cupo de la reserva ha sido liberado');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('esta_seguro_que_desea_liberar_el_cupo_de_la_reserva', '¿Está seguro que desea liberar el cupo de la reserva?');

-- =======================================================================================================================
-- 2.2.1

-- Nada para hacer


-- =======================================================================================================================
-- 2.3.0

INSERT INTO global.ae_configuracion (clave, valor) VALUES ('WS_MIPERFIL_HABILITADO', 'false');
INSERT INTO global.ae_configuracion (clave, valor) VALUES ('WS_MIPERFIL_URL', '');
INSERT INTO global.ae_configuracion (clave, valor) VALUES ('WS_MIPERFIL_KS_PATH', '');
INSERT INTO global.ae_configuracion (clave, valor) VALUES ('WS_MIPERFIL_KS_PASS', '');
INSERT INTO global.ae_configuracion (clave, valor) VALUES ('WS_MIPERFIL_TS_PATH', '');
INSERT INTO global.ae_configuracion (clave, valor) VALUES ('WS_MIPERFIL_TS_PASS', '');

INSERT INTO global.ae_textos (codigo, texto) VALUES ('configuracion_de_mi_perfil', 'Configuración de Mi Perfil');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('enviar_aviso_al_confirmar', 'Enviar un aviso al confirmar');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('enviar_aviso_al_cancelar', 'Enviar un aviso al cancelar');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('enviar_aviso_recordatorio', 'Enviar un recordatorio');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('no_se_pudo_enviar_notificacion_de_confirmacion_tome_nota_de_los_datos_de_la_reserva', 'No se pudo enviar una notificación al usuario; tome nota de los datos de la reserva');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('no_se_pudo_enviar_notificacion_de_cancelacion', 'No se pudo enviar una notificación al usuario');


-- =======================================================================================================================
-- 2.3.1

INSERT INTO global.ae_textos (codigo, texto) VALUES ('acc_titulo', 'Título');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('acc_url', 'URL');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('acc_destacada', 'Destacada');

INSERT INTO global.ae_textos (codigo, texto) VALUES ('debe_haber_una_unica_accion_de_confirmacion_destacada', 'Debe haber una (y solo una) acción de aviso al confirmar marcada como destacada, cuya URL no sea vacía.');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('debe_haber_una_unica_accion_de_cancelacion_destacada', 'Debe haber una (y solo una) acción de aviso al cancelar marcada como destacada, cuya URL no sea vacía.');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('debe_haber_una_unica_accion_de_recordatorio_destacada', 'Debe haber una (y solo una) acción de recordatorio marcada como destacada, cuya URL no sea vacía');

INSERT INTO global.ae_textos (codigo, texto) VALUES ('vencimiento_aviso_al_confirmar', 'Vencimiento de aviso al confirmar');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('vencimiento_aviso_al_cancelar', 'Vencimiento de aviso al cancelar');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('vencimiento_recordatorio', 'Vencimiento de recordatorio');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('hora_recordatorio', 'Hora de recordatorio');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('dias_recordatorio', 'Días de recordatorio');

INSERT INTO global.ae_textos (codigo, texto) VALUES ('textos_aviso_al_confirmar', 'Textos aviso al confirmar');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('textos_aviso_al_cancelar', 'Textos aviso al cancelar');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('textos_recordatorio', 'Textos recordatorio');

INSERT INTO global.ae_textos (codigo, texto) VALUES ('texto_titulo_aviso_al_confirmar', 'Título del aviso al confirmar');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('texto_corto_aviso_al_confirmar', 'Texto corto del aviso al confirmar');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('texto_largo_aviso_al_confirmar', 'Texto largo del aviso al confirmar');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('texto_titulo_aviso_al_cancelar', 'Título del aviso al cancelar');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('texto_corto_aviso_al_cancelar', 'Texto corto del aviso al cancelar');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('texto_largo_aviso_al_cancelar', 'Texto largo del aviso al cancelar');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('texto_titulo_recordatorio', 'Título del recordatorio');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('texto_corto_recordatorio', 'Texto corto del recordatorio');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('texto_largo_recordatorio', 'Texto largo del recordatorio');

INSERT INTO global.ae_configuracion(clave, valor) VALUES ('DOMINIO', 'https://localhost:443');
INSERT INTO global.ae_configuracion(clave, valor) VALUES ('JSON_ESCAPE', 'false');

-- =======================================================================================================================
-- 2.3.2

-- Nada para hacer

-- =======================================================================================================================
-- 2.3.3

INSERT INTO global.ae_configuracion(clave, valor) VALUES ('MIPERFIL_OID', '');

-- =======================================================================================================================
-- 2.3.4

-- Nada para hacer

-- =======================================================================================================================
-- 2.3.4.1

-- Nada para hacer

-- =======================================================================================================================
-- 2.3.4.2

-- Nada para hacer

-- =======================================================================================================================
-- 2.3.5

-- Nada para hacer

-- =======================================================================================================================
-- 2.3.6

-- Nada para hacer

-- =======================================================================================================================
-- 2.3.7

INSERT INTO "global".ae_textos (codigo, texto) VALUES('administradorDeRecursos', 'Administrador de recursos');
-- =======================================================================================================================
-- 4.0.0

INSERT INTO "global".ae_configuracion (clave, valor) VALUES ('WS_NOVEDADES_XML_LOG', 'false');
INSERT INTO "global".ae_configuracion (clave, valor) VALUES ('CARGA_MASIVA_DIAS_RECURSOS_NUEVOS', '30');
INSERT INTO "global".ae_configuracion (clave, valor) VALUES ('CARGA_MASIVA_DIAS_RECURSOS_EXISTENTES', '15');

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

	alter table "global".ae_configuracion add column org_id integer not NULL;
	
	alter table "global".ae_configuracion add column id integer  NULL;
	
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

CREATE TABLE global.ae_rel_usuarios_organismos (
    usuario_id integer NOT NULL,
    org_id integer NOT NULL
);
ALTER TABLE global.ae_rel_usuarios_organismos OWNER TO sae;

ALTER TABLE ONLY global.ae_rel_usuarios_organismos ADD CONSTRAINT ae_rel_usuarios_organismos_pkey PRIMARY KEY (usuario_id, org_id);

-- Versión 4.1.0 (2020-04-09)
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

-- Versión 4.4.0 (2021-10-09)
INSERT INTO "global".ae_textos(codigo, texto) VALUES ('consultar_usuarios_por_empresa', 'Consultar usuarios por empresa');
INSERT INTO "global".ae_textos(codigo, texto) VALUES ('roles', 'Roles');
INSERT INTO "global".ae_textos(codigo, texto) VALUES ('valor_en_traza', 'Valor en Traza');
INSERT INTO "global".ae_textos(codigo, texto) VALUES ('ingrese_el_numero_de_puesto_en_el_campo_correcto', 'Ingrese el numero de puesto en el campo correcto');

-- Versión 4.4.1 (2021-12-06)
update global.ae_textos set texto='Se han modificado x recursos' where codigo = 'actualizacion_recursos';
INSERT INTO global.ae_textos(codigo, texto) VALUES ('reporte_errores_actualizacion_masiva_reservas', 'Reporte de errores de actualización masiva de recursos');

-- Versión 4.4.2 (2021-12-27)
INSERT INTO global.ae_textos(codigo, texto) VALUES ('descargar', 'Descargar');
INSERT INTO global.ae_textos(codigo, texto) VALUES ('mensaje_reporte_disponible', 'Reporte disponible, use el botón Descargar.');

-- =======================================================================================================================
-- 4.4.4
-- Nada para hacer
-- =======================================================================================================================
-- 4.4.5
-- Nada para hacer
-- =======================================================================================================================
-- 4.4.6
-- Nada para hacer
-- =======================================================================================================================
-- 4.4.7
-- Nada para hacer
-- =======================================================================================================================
-- 4.6.0
-- Nada para hacer
-- =======================================================================================================================
-- 4.6.1

INSERT INTO global.ae_textos (codigo, texto) VALUES ('recursos_a_mostrar', 'Recursos a mostrar');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('abrir_llamador_externo', 'Abrir el llamador externo');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('abrir_llamador_interno', 'Abrir el llamador interno');

-- =======================================================================================================================
-- 4.6.2
-- Nada para hacer
-- =======================================================================================================================

-- Versión 4.6.2 (2023-07-06)
INSERT INTO global.ae_textos (codigo, texto) VALUES ('la_hora_de_inicio_debe_ser_igual_o_posterior_a_la_actual', 'La hora de inicio debe ser igual o posterior a la actual');

--  Versión 4.6.3 (2023-09-05)
INSERT INTO global.ae_textos(codigo, texto) VALUES ('confirmacion_hora_seleccionada','CONFIRMACIÓN DE HORA SELECCIONADA');
INSERT INTO global.ae_textos(codigo, texto) VALUES ('volver_paso_anterior','Volver al Paso Anterior');

--  Versión 4.6.4 (2024-06-20) 
INSERT INTO global.ae_textos (codigo, texto) VALUES ('la_fecha_de_inicio_de_disponibilidad_debe_ser_igual_o_anterior_a_la_fecha_de_fin_de_vigencia', 'La fecha de inicio de atención al público debe ser anterior a la fecha de fin de vigencia');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('el_periodo_de_atencion_debe_estar_comprendido_en_el_periodo_de_vigencia', 'El período de antención al público debe estar comprendido dentro del período de vigencia');

-- Versión 4.6.5 (2025-06-13)
ALTER TABLE global.ae_usuarios ADD COLUMN ultimo_login TIMESTAMPTZ;
INSERT INTO global.ae_textos (codigo, texto) VALUES ('ultimo_login', 'Último Login');
UPDATE global.ae_textos SET texto='Texto del logo' WHERE codigo='logo_texto_alternativo';
UPDATE global.ae_textos SET texto='Atenciones por funcionario' WHERE codigo='reporte_atencion_funcionario';
UPDATE global.ae_textos SET texto='Tiempos de atención por funcionario' WHERE codigo='reporte_tiempo_atencion_funcionario';
-- =======================================================================================================================
-- 4.6.6
-- Nada para hacer 
-- =======================================================================================================================
-- 4.6.7
INSERT INTO global.ae_textos (codigo, texto) VALUES ('paso1_reserva', 'Agendar reserva');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('turnos_disponibles', 'Turnos disponibles');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('ver_calendario_completo', 'Ver calendario completo');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('horarios_disponibles', 'Horarios disponibles');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('paso1_reserva_agenda', 'Agenda');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('paso_final_reserva_agenda', 'Ubicación');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('etiqueta_calendario', 'Calendario');
UPDATE global.ae_textos SET texto='Su reserva está confirmada' WHERE codigo='la_reserva_esta_confirmada';
INSERT INTO global.ae_textos (codigo, texto) VALUES ('url_google_maps', 'https://www.google.com/maps?q=');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('ver_ubicacion_en_maps', 'Ver ubicación en el mapa'); 
INSERT INTO global.ae_textos (codigo, texto) VALUES ('imprimir', 'Imprimir');  
INSERT INTO global.ae_textos (codigo, texto) VALUES ('codigo_de_cancelacion', 'Código de cancelación');
-- =======================================================================================================================
-- 4.7.0
-- Textos para opciones vacías en formularios de búsqueda/cancelación (hotfix 16056)
-- Campos LIST y BOOLEAN en formulario tipo CONSULTA requieren opción "sin selección" por defecto
-- para evitar que se generen condiciones implícitas en la query de búsqueda de reservas.
INSERT INTO global.ae_textos (codigo, texto) VALUES ('seleccionar_opcion', '-- Seleccione --');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('opcion_si', 'Sí');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('opcion_no', 'No');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('la_longitud_del_recurso_es_obligatoria', 'La longitud del recurso es obligatoria');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('la_localidad_del_recurso_es_obligatoria', 'La localidad del recurso es obligatoria');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('el_departamento_del_recurso_es_obligatorio', 'El departamento del recurso es obligatorio');
INSERT INTO global.ae_textos (codigo, texto) VALUES ('la_latitud_del_recurso_es_obligatoria', 'La latitud del recurso es obligatoria');