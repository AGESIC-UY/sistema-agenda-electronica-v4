--
-- PostgreSQL database dump
--

-- Dumped from database version 10.17
-- Dumped by pg_dump version 10.17

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: empresa1; Type: SCHEMA; Schema: -; Owner: sae
--

CREATE SCHEMA empresa1;


ALTER SCHEMA empresa1 OWNER TO sae;

--
-- Name: empresa2; Type: SCHEMA; Schema: -; Owner: sae
--

CREATE SCHEMA empresa2;


ALTER SCHEMA empresa2 OWNER TO sae;

--
-- Name: empresa3; Type: SCHEMA; Schema: -; Owner: sae
--

CREATE SCHEMA empresa3;


ALTER SCHEMA empresa3 OWNER TO sae;

--
-- Name: empresa4; Type: SCHEMA; Schema: -; Owner: sae
--

CREATE SCHEMA empresa4;


ALTER SCHEMA empresa4 OWNER TO sae;

--
-- Name: empresa5; Type: SCHEMA; Schema: -; Owner: sae
--

CREATE SCHEMA empresa5;


ALTER SCHEMA empresa5 OWNER TO sae;

--
-- Name: global; Type: SCHEMA; Schema: -; Owner: sae
--

CREATE SCHEMA global;


ALTER SCHEMA global OWNER TO sae;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: ae_acciones; Type: TABLE; Schema: empresa1; Owner: sae
--

CREATE TABLE empresa1.ae_acciones (
    id integer NOT NULL,
    descripcion character varying(100) NOT NULL,
    fecha_baja timestamp without time zone,
    host character varying(100),
    nombre character varying(50) NOT NULL,
    servicio character varying(250) NOT NULL
);


ALTER TABLE empresa1.ae_acciones OWNER TO sae;

--
-- Name: ae_acciones_miperfil_recurso; Type: TABLE; Schema: empresa1; Owner: sae
--

CREATE TABLE empresa1.ae_acciones_miperfil_recurso (
    id integer NOT NULL,
    recurso_id integer NOT NULL,
    titulo_con_1 character varying(100),
    url_con_1 character varying(1024),
    destacada_con_1 boolean,
    titulo_con_2 character varying(100),
    url_con_2 character varying(1024),
    destacada_con_2 boolean,
    titulo_con_3 character varying(100),
    url_con_3 character varying(1024),
    destacada_con_3 boolean,
    titulo_con_4 character varying(100),
    url_con_4 character varying(1024),
    destacada_con_4 boolean,
    titulo_con_5 character varying(100),
    url_con_5 character varying(1024),
    destacada_con_5 boolean,
    titulo_can_1 character varying(100),
    url_can_1 character varying(1024),
    destacada_can_1 boolean,
    titulo_can_2 character varying(100),
    url_can_2 character varying(1024),
    destacada_can_2 boolean,
    titulo_can_3 character varying(100),
    url_can_3 character varying(1024),
    destacada_can_3 boolean,
    titulo_can_4 character varying(100),
    url_can_4 character varying(1024),
    destacada_can_4 boolean,
    titulo_can_5 character varying(100),
    url_can_5 character varying(1024),
    destacada_can_5 boolean,
    titulo_rec_1 character varying(100),
    url_rec_1 character varying(1024),
    destacada_rec_1 boolean,
    titulo_rec_2 character varying(100),
    url_rec_2 character varying(1024),
    destacada_rec_2 boolean,
    titulo_rec_3 character varying(100),
    url_rec_3 character varying(1024),
    destacada_rec_3 boolean,
    titulo_rec_4 character varying(100),
    url_rec_4 character varying(1024),
    destacada_rec_4 boolean,
    titulo_rec_5 character varying(100),
    url_rec_5 character varying(1024),
    destacada_rec_5 boolean
);


ALTER TABLE empresa1.ae_acciones_miperfil_recurso OWNER TO sae;

--
-- Name: ae_acciones_por_dato; Type: TABLE; Schema: empresa1; Owner: sae
--

CREATE TABLE empresa1.ae_acciones_por_dato (
    id integer NOT NULL,
    fecha_desasociacion timestamp without time zone,
    nombre_parametro character varying(50) NOT NULL,
    aear_id integer NOT NULL,
    aeds_id integer NOT NULL
);


ALTER TABLE empresa1.ae_acciones_por_dato OWNER TO sae;

--
-- Name: ae_acciones_por_recurso; Type: TABLE; Schema: empresa1; Owner: sae
--

CREATE TABLE empresa1.ae_acciones_por_recurso (
    id integer NOT NULL,
    evento character varying(1) NOT NULL,
    fecha_baja timestamp without time zone,
    orden_ejecucion integer,
    aeac_id integer NOT NULL,
    aere_id integer NOT NULL
);


ALTER TABLE empresa1.ae_acciones_por_recurso OWNER TO sae;

--
-- Name: ae_agendas; Type: TABLE; Schema: empresa1; Owner: sae
--

CREATE TABLE empresa1.ae_agendas (
    id integer NOT NULL,
    descripcion character varying(1000) NOT NULL,
    fecha_baja timestamp without time zone,
    nombre character varying(100) NOT NULL,
    tramite_id character varying(25),
    timezone character varying(100),
    idiomas character varying(100),
    con_cda boolean DEFAULT false,
    tramite_codigo character varying(10),
    publicar_novedades boolean DEFAULT false NOT NULL,
    con_trazabilidad boolean DEFAULT false NOT NULL
);


ALTER TABLE empresa1.ae_agendas OWNER TO sae;

--
-- Name: ae_agrupaciones_datos; Type: TABLE; Schema: empresa1; Owner: sae
--

CREATE TABLE empresa1.ae_agrupaciones_datos (
    id integer NOT NULL,
    fecha_baja timestamp without time zone,
    nombre character varying(50) NOT NULL,
    orden integer NOT NULL,
    aere_id integer NOT NULL,
    borrar_flag boolean NOT NULL,
    etiqueta character varying(50)
);


ALTER TABLE empresa1.ae_agrupaciones_datos OWNER TO sae;

--
-- Name: ae_anios; Type: TABLE; Schema: empresa1; Owner: sae
--

CREATE TABLE empresa1.ae_anios (
    id integer NOT NULL,
    anio integer NOT NULL,
    aepl_id integer NOT NULL
);


ALTER TABLE empresa1.ae_anios OWNER TO sae;

--
-- Name: ae_atencion; Type: TABLE; Schema: empresa1; Owner: sae
--

CREATE TABLE empresa1.ae_atencion (
    id integer NOT NULL,
    asistio boolean NOT NULL,
    duracion integer NOT NULL,
    fact timestamp without time zone NOT NULL,
    fcrea timestamp without time zone NOT NULL,
    funcionario character varying(255) NOT NULL,
    aers_id integer NOT NULL
);


ALTER TABLE empresa1.ae_atencion OWNER TO sae;

--
-- Name: ae_comunicaciones; Type: TABLE; Schema: empresa1; Owner: sae
--

CREATE TABLE empresa1.ae_comunicaciones (
    id integer NOT NULL,
    tipo_1 character varying(25) NOT NULL,
    tipo_2 character varying(25) NOT NULL,
    destino character varying(100) NOT NULL,
    recurso_id integer NOT NULL,
    reserva_id integer,
    token_id integer,
    procesado boolean DEFAULT false NOT NULL,
    mensaje character varying(4000)
);


ALTER TABLE empresa1.ae_comunicaciones OWNER TO sae;

--
-- Name: ae_constante_validacion; Type: TABLE; Schema: empresa1; Owner: sae
--

CREATE TABLE empresa1.ae_constante_validacion (
    id integer NOT NULL,
    fecha_baja timestamp without time zone,
    largo integer NOT NULL,
    nombre character varying(50) NOT NULL,
    tipo character varying(30) NOT NULL,
    aeva_id integer NOT NULL
);


ALTER TABLE empresa1.ae_constante_validacion OWNER TO sae;

--
-- Name: ae_datos_a_solicitar; Type: TABLE; Schema: empresa1; Owner: sae
--

CREATE TABLE empresa1.ae_datos_a_solicitar (
    id integer NOT NULL,
    ancho_despliegue integer NOT NULL,
    columna integer NOT NULL,
    es_clave boolean NOT NULL,
    etiqueta character varying(50) NOT NULL,
    fecha_baja timestamp without time zone,
    fila integer NOT NULL,
    incluir_en_llamador boolean NOT NULL,
    incluir_en_reporte boolean NOT NULL,
    largo integer,
    largo_en_llamador integer NOT NULL,
    nombre character varying(50) NOT NULL,
    orden_en_llamador integer NOT NULL,
    requerido boolean NOT NULL,
    texto_ayuda character varying(100),
    tipo character varying(30) NOT NULL,
    aead_id integer NOT NULL,
    aere_id integer NOT NULL,
    borrar_flag boolean DEFAULT true NOT NULL,
    solo_lectura boolean DEFAULT false NOT NULL,
    incluir_en_novedades boolean DEFAULT false NOT NULL
);


ALTER TABLE empresa1.ae_datos_a_solicitar OWNER TO sae;

--
-- Name: ae_datos_del_recurso; Type: TABLE; Schema: empresa1; Owner: sae
--

CREATE TABLE empresa1.ae_datos_del_recurso (
    id integer NOT NULL,
    etiqueta character varying(50) NOT NULL,
    orden integer NOT NULL,
    valor character varying(100) NOT NULL,
    aere_id integer NOT NULL
);


ALTER TABLE empresa1.ae_datos_del_recurso OWNER TO sae;

--
-- Name: ae_datos_reserva; Type: TABLE; Schema: empresa1; Owner: sae
--

CREATE TABLE empresa1.ae_datos_reserva (
    id integer NOT NULL,
    valor character varying(100) NOT NULL,
    aeds_id integer NOT NULL,
    aers_id integer NOT NULL
);


ALTER TABLE empresa1.ae_datos_reserva OWNER TO sae;

--
-- Name: ae_dias_del_mes; Type: TABLE; Schema: empresa1; Owner: sae
--

CREATE TABLE empresa1.ae_dias_del_mes (
    id integer NOT NULL,
    dia_del_mes integer NOT NULL,
    aepl_id integer NOT NULL
);


ALTER TABLE empresa1.ae_dias_del_mes OWNER TO sae;

--
-- Name: ae_dias_semana; Type: TABLE; Schema: empresa1; Owner: sae
--

CREATE TABLE empresa1.ae_dias_semana (
    id integer NOT NULL,
    dia_semana integer NOT NULL,
    aepl_id integer NOT NULL
);


ALTER TABLE empresa1.ae_dias_semana OWNER TO sae;

--
-- Name: ae_disponibilidades; Type: TABLE; Schema: empresa1; Owner: sae
--

CREATE TABLE empresa1.ae_disponibilidades (
    id integer NOT NULL,
    cupo integer NOT NULL,
    fecha date NOT NULL,
    fecha_baja timestamp without time zone,
    hora_fin timestamp without time zone NOT NULL,
    hora_inicio timestamp without time zone NOT NULL,
    numerador integer NOT NULL,
    version integer NOT NULL,
    aepl_id integer,
    aere_id integer NOT NULL,
    presencial boolean DEFAULT false NOT NULL
);


ALTER TABLE empresa1.ae_disponibilidades OWNER TO sae;

--
-- Name: ae_frases_captcha; Type: TABLE; Schema: empresa1; Owner: sae
--

CREATE TABLE empresa1.ae_frases_captcha (
    clave character varying(100) NOT NULL,
    frase character varying(1024),
    idioma character varying(5) NOT NULL
);


ALTER TABLE empresa1.ae_frases_captcha OWNER TO sae;

--
-- Name: ae_llamadas; Type: TABLE; Schema: empresa1; Owner: sae
--

CREATE TABLE empresa1.ae_llamadas (
    id integer NOT NULL,
    etiqueta character varying(100) NOT NULL,
    fecha date NOT NULL,
    hora timestamp without time zone NOT NULL,
    numero integer NOT NULL,
    puesto integer NOT NULL,
    aere_id integer NOT NULL,
    aers_id integer NOT NULL
);


ALTER TABLE empresa1.ae_llamadas OWNER TO sae;

--
-- Name: ae_meses; Type: TABLE; Schema: empresa1; Owner: sae
--

CREATE TABLE empresa1.ae_meses (
    id integer NOT NULL,
    mes integer NOT NULL,
    aepl_id integer NOT NULL
);


ALTER TABLE empresa1.ae_meses OWNER TO sae;

--
-- Name: ae_parametros_accion; Type: TABLE; Schema: empresa1; Owner: sae
--

CREATE TABLE empresa1.ae_parametros_accion (
    id integer NOT NULL,
    fecha_baja timestamp without time zone,
    largo integer,
    nombre character varying(50) NOT NULL,
    tipo character varying(30),
    aeac_id integer NOT NULL
);


ALTER TABLE empresa1.ae_parametros_accion OWNER TO sae;

--
-- Name: ae_parametros_autocompletar; Type: TABLE; Schema: empresa1; Owner: sae
--

CREATE TABLE empresa1.ae_parametros_autocompletar (
    id integer NOT NULL,
    fecha_baja timestamp without time zone,
    modo integer NOT NULL,
    nombre character varying(50) NOT NULL,
    tipo character varying(30) NOT NULL,
    aeserv_id integer NOT NULL
);


ALTER TABLE empresa1.ae_parametros_autocompletar OWNER TO sae;

--
-- Name: ae_parametros_validacion; Type: TABLE; Schema: empresa1; Owner: sae
--

CREATE TABLE empresa1.ae_parametros_validacion (
    id integer NOT NULL,
    fecha_baja timestamp without time zone,
    largo integer,
    nombre character varying(50) NOT NULL,
    tipo character varying(30),
    aeva_id integer NOT NULL
);


ALTER TABLE empresa1.ae_parametros_validacion OWNER TO sae;

--
-- Name: ae_plantillas; Type: TABLE; Schema: empresa1; Owner: sae
--

CREATE TABLE empresa1.ae_plantillas (
    id integer NOT NULL,
    cupo integer NOT NULL,
    fecha_baja timestamp without time zone,
    frecuencia integer NOT NULL,
    generacion_automatica boolean NOT NULL,
    hora_fin timestamp without time zone NOT NULL,
    hora_inicio timestamp without time zone NOT NULL,
    aere_id integer NOT NULL
);


ALTER TABLE empresa1.ae_plantillas OWNER TO sae;

--
-- Name: ae_preguntas_captcha; Type: TABLE; Schema: empresa1; Owner: sae
--

CREATE TABLE empresa1.ae_preguntas_captcha (
    clave character varying(100) NOT NULL,
    pregunta character varying(1024),
    respuesta character varying(25),
    idioma character varying(5) NOT NULL
);


ALTER TABLE empresa1.ae_preguntas_captcha OWNER TO sae;

--
-- Name: ae_recursos; Type: TABLE; Schema: empresa1; Owner: sae
--

CREATE TABLE empresa1.ae_recursos (
    id integer NOT NULL,
    cant_dias_a_generar integer NOT NULL,
    descripcion character varying(1000) NOT NULL,
    dias_inicio_ventana_internet integer NOT NULL,
    dias_inicio_ventana_intranet integer NOT NULL,
    dias_ventana_internet integer NOT NULL,
    dias_ventana_intranet integer NOT NULL,
    fecha_baja timestamp without time zone,
    fecha_fin timestamp without time zone,
    fecha_fin_disp timestamp without time zone,
    fecha_inicio timestamp without time zone NOT NULL,
    fecha_inicio_disp timestamp without time zone NOT NULL,
    largo_lista_espera integer,
    mostrar_numero_en_llamador boolean NOT NULL,
    mostrar_numero_en_ticket boolean NOT NULL,
    nombre character varying(100) NOT NULL,
    sabado_es_habil boolean NOT NULL,
    serie character varying(3),
    usar_llamador boolean NOT NULL,
    ventana_cupos_minimos integer NOT NULL,
    version integer NOT NULL,
    visible_internet boolean NOT NULL,
    aeag_id integer NOT NULL,
    oficina_id character varying(25),
    direccion character varying(100),
    localidad character varying(100),
    departamento character varying(100),
    telefonos character varying(100),
    horarios character varying(100),
    latitud numeric,
    longitud numeric,
    mostrar_id_en_ticket boolean,
    domingo_es_habil boolean DEFAULT false NOT NULL,
    presencial_admite boolean DEFAULT false NOT NULL,
    presencial_lunes boolean DEFAULT false NOT NULL,
    presencial_martes boolean DEFAULT false NOT NULL,
    presencial_miercoles boolean DEFAULT false NOT NULL,
    presencial_jueves boolean DEFAULT false NOT NULL,
    presencial_viernes boolean DEFAULT false NOT NULL,
    presencial_sabado boolean DEFAULT false NOT NULL,
    presencial_domingo boolean DEFAULT false NOT NULL,
    presencial_cupos integer DEFAULT 0 NOT NULL,
    tamanio_fuente_grande integer DEFAULT 12 NOT NULL,
    tamanio_fuente_normal integer DEFAULT 10 NOT NULL,
    tamanio_fuente_chica integer DEFAULT 8 NOT NULL,
    fuente_ticket character varying(100) DEFAULT 'Helvetica-Bold'::character varying NOT NULL,
    multiple_admite boolean DEFAULT false NOT NULL,
    cambios_admite boolean DEFAULT false NOT NULL,
    cambios_tiempo integer,
    cambios_unidad integer,
    periodo_validacion integer DEFAULT 0 NOT NULL,
    validar_por_ip boolean DEFAULT false NOT NULL,
    cantidad_por_ip integer,
    periodo_por_ip integer,
    ips_sin_validacion character varying(4000) DEFAULT NULL::character varying,
    cancela_tiempo integer DEFAULT 0 NOT NULL,
    cancela_unidad integer DEFAULT 12 NOT NULL,
    cancela_tipo character varying(1) DEFAULT 'I'::character varying NOT NULL,
    mi_perfil_con_hab boolean DEFAULT true NOT NULL,
    mi_perfil_can_hab boolean DEFAULT true NOT NULL,
    mi_perfil_rec_hab boolean DEFAULT true NOT NULL,
    mi_perfil_con_tit character varying(255) DEFAULT NULL::character varying,
    mi_perfil_con_cor character varying(500) DEFAULT NULL::character varying,
    mi_perfil_con_lar character varying(1024) DEFAULT NULL::character varying,
    mi_perfil_con_ven integer,
    mi_perfil_can_tit character varying(255) DEFAULT NULL::character varying,
    mi_perfil_can_cor character varying(500) DEFAULT NULL::character varying,
    mi_perfil_can_lar character varying(1024) DEFAULT NULL::character varying,
    mi_perfil_can_ven integer,
    mi_perfil_rec_tit character varying(255) DEFAULT NULL::character varying,
    mi_perfil_rec_cor character varying(500) DEFAULT NULL::character varying,
    mi_perfil_rec_lar character varying(1024) DEFAULT NULL::character varying,
    mi_perfil_rec_ven integer,
    mi_perfil_rec_hora integer,
    mi_perfil_rec_dias integer
);


ALTER TABLE empresa1.ae_recursos OWNER TO sae;

--
-- Name: ae_recursos_aud; Type: TABLE; Schema: empresa1; Owner: sae
--

CREATE TABLE empresa1.ae_recursos_aud (
    id integer NOT NULL,
    id_recurso integer NOT NULL,
    nombre character varying(100) NOT NULL,
    descripcion character varying(1000) NOT NULL,
    fecha_inicio timestamp without time zone NOT NULL,
    fecha_fin timestamp without time zone,
    fecha_inicio_disp timestamp without time zone NOT NULL,
    fecha_fin_disp timestamp without time zone,
    dias_inicio_ventana_intranet integer NOT NULL,
    dias_ventana_intranet integer NOT NULL,
    dias_inicio_ventana_internet integer NOT NULL,
    dias_ventana_internet integer NOT NULL,
    ventana_cupos_minimos integer NOT NULL,
    cant_dias_a_generar integer NOT NULL,
    largo_lista_espera integer,
    fecha_baja timestamp without time zone,
    mostrar_numero_en_llamador boolean NOT NULL,
    visible_internet boolean NOT NULL,
    usar_llamador boolean NOT NULL,
    serie character varying(3),
    sabado_es_habil boolean NOT NULL,
    domingo_es_habil boolean DEFAULT false NOT NULL,
    mostrar_numero_en_ticket boolean NOT NULL,
    mostrar_id_en_ticket boolean,
    fuente_ticket character varying(100) DEFAULT 'Helvetica-Bold'::character varying NOT NULL,
    tamanio_fuente_grande integer DEFAULT 12 NOT NULL,
    tamanio_fuente_normal integer DEFAULT 10 NOT NULL,
    tamanio_fuente_chica integer DEFAULT 8 NOT NULL,
    oficina_id character varying(25),
    direccion character varying(100),
    localidad character varying(100),
    departamento character varying(100),
    telefonos character varying(100),
    horarios character varying(100),
    latitud numeric,
    longitud numeric,
    agenda integer NOT NULL,
    presencial_admite boolean DEFAULT false NOT NULL,
    presencial_cupos integer DEFAULT 0 NOT NULL,
    presencial_lunes boolean DEFAULT false NOT NULL,
    presencial_martes boolean DEFAULT false NOT NULL,
    presencial_miercoles boolean DEFAULT false NOT NULL,
    presencial_jueves boolean DEFAULT false NOT NULL,
    presencial_viernes boolean DEFAULT false NOT NULL,
    presencial_sabado boolean DEFAULT false NOT NULL,
    presencial_domingo boolean DEFAULT false NOT NULL,
    multiple_admite boolean DEFAULT false NOT NULL,
    cambios_admite boolean DEFAULT false NOT NULL,
    cambios_tiempo integer,
    cambios_unidad integer,
    periodo_validacion integer DEFAULT 0 NOT NULL,
    validar_por_ip boolean DEFAULT false NOT NULL,
    cantidad_por_ip integer,
    periodo_por_ip integer,
    ips_sin_validacion character varying(4000) DEFAULT NULL::character varying,
    cancela_tiempo integer DEFAULT 0 NOT NULL,
    cancela_unidad integer DEFAULT 12 NOT NULL,
    cancela_tipo character varying(1) DEFAULT 'I'::character varying NOT NULL,
    mi_perfil_con_hab boolean DEFAULT true NOT NULL,
    mi_perfil_con_tit character varying(200) DEFAULT NULL::character varying,
    mi_perfil_con_cor character varying(500) DEFAULT NULL::character varying,
    mi_perfil_con_lar character varying(3200) DEFAULT NULL::character varying,
    mi_perfil_con_ven integer,
    mi_perfil_can_hab boolean DEFAULT true NOT NULL,
    mi_perfil_can_tit character varying(200) DEFAULT NULL::character varying,
    mi_perfil_can_cor character varying(500) DEFAULT NULL::character varying,
    mi_perfil_can_lar character varying(3200) DEFAULT NULL::character varying,
    mi_perfil_can_ven integer,
    mi_perfil_rec_hab boolean DEFAULT true NOT NULL,
    mi_perfil_rec_tit character varying(200) DEFAULT NULL::character varying,
    mi_perfil_rec_cor character varying(500) DEFAULT NULL::character varying,
    mi_perfil_rec_lar character varying(3200) DEFAULT NULL::character varying,
    mi_perfil_rec_ven integer,
    mi_perfil_rec_hora integer,
    mi_perfil_rec_dias integer,
    reserva_pen_tiempo_max integer,
    reserva_pend_tiempo_max integer,
    fecha_modificacion timestamp without time zone,
    usuario character varying(45),
    version integer NOT NULL,
    tipo_operacion smallint
);


ALTER TABLE empresa1.ae_recursos_aud OWNER TO sae;

--
-- Name: ae_reservas; Type: TABLE; Schema: empresa1; Owner: sae
--

CREATE TABLE empresa1.ae_reservas (
    id integer NOT NULL,
    estado character varying(1) NOT NULL,
    fact timestamp without time zone NOT NULL,
    fcrea timestamp without time zone NOT NULL,
    numero integer,
    observaciones character varying(100),
    origen character varying(1),
    ucancela character varying(255),
    ucrea character varying(255),
    version integer NOT NULL,
    codigo_seguridad character varying(10) DEFAULT '00000'::character varying,
    trazabilidad_guid character varying(25),
    tramite_codigo character varying(10),
    tramite_nombre character varying(100),
    serie character varying(3),
    tcancela character varying(1),
    fcancela timestamp without time zone,
    aetr_id integer,
    ip_origen character varying(16) DEFAULT NULL::character varying,
    flibera timestamp without time zone,
    mi_perfil_notif boolean DEFAULT true NOT NULL,
    notificar boolean DEFAULT true,
    reserva_hija_id integer
);


ALTER TABLE empresa1.ae_reservas OWNER TO sae;

--
-- Name: ae_reservas_disponibilidades; Type: TABLE; Schema: empresa1; Owner: sae
--

CREATE TABLE empresa1.ae_reservas_disponibilidades (
    aers_id integer NOT NULL,
    aedi_id integer NOT NULL
);


ALTER TABLE empresa1.ae_reservas_disponibilidades OWNER TO sae;

--
-- Name: ae_roles_usuario_recurso; Type: TABLE; Schema: empresa1; Owner: sae
--

CREATE TABLE empresa1.ae_roles_usuario_recurso (
    usuario_id integer NOT NULL,
    recurso_id integer NOT NULL,
    roles character varying(4000)
);


ALTER TABLE empresa1.ae_roles_usuario_recurso OWNER TO sae;

--
-- Name: ae_serv_autocomp_por_dato; Type: TABLE; Schema: empresa1; Owner: sae
--

CREATE TABLE empresa1.ae_serv_autocomp_por_dato (
    id integer NOT NULL,
    fecha_desasociacion timestamp without time zone,
    nombre_parametro character varying(50) NOT NULL,
    aeds_id integer NOT NULL,
    aesr_id integer NOT NULL
);


ALTER TABLE empresa1.ae_serv_autocomp_por_dato OWNER TO sae;

--
-- Name: ae_serv_autocompletar; Type: TABLE; Schema: empresa1; Owner: sae
--

CREATE TABLE empresa1.ae_serv_autocompletar (
    id integer NOT NULL,
    descripcion character varying(100) NOT NULL,
    fecha_baja timestamp without time zone,
    host character varying(100),
    nombre character varying(50) NOT NULL,
    servicio character varying(250) NOT NULL
);


ALTER TABLE empresa1.ae_serv_autocompletar OWNER TO sae;

--
-- Name: ae_servicio_por_recurso; Type: TABLE; Schema: empresa1; Owner: sae
--

CREATE TABLE empresa1.ae_servicio_por_recurso (
    id integer NOT NULL,
    fecha_baja timestamp without time zone,
    aeserv_id integer NOT NULL,
    aere_id integer NOT NULL
);


ALTER TABLE empresa1.ae_servicio_por_recurso OWNER TO sae;

--
-- Name: ae_textos; Type: TABLE; Schema: empresa1; Owner: sae
--

CREATE TABLE empresa1.ae_textos (
    codigo character varying(100) NOT NULL,
    idioma character varying(5) NOT NULL,
    texto character varying(4096) NOT NULL
);


ALTER TABLE empresa1.ae_textos OWNER TO sae;

--
-- Name: ae_textos_agenda; Type: TABLE; Schema: empresa1; Owner: sae
--

CREATE TABLE empresa1.ae_textos_agenda (
    id integer NOT NULL,
    texto_paso1 character varying(1000),
    texto_paso2 character varying(1000),
    texto_paso3 character varying(1000),
    texto_sel_recurso character varying(50),
    texto_ticket character varying(1000),
    aeag_id integer NOT NULL,
    texto_correo_conf character varying(1000),
    texto_correo_canc character varying(1000),
    por_defecto boolean DEFAULT false NOT NULL,
    idioma character varying(5) NOT NULL
);


ALTER TABLE empresa1.ae_textos_agenda OWNER TO sae;

--
-- Name: ae_textos_recurso; Type: TABLE; Schema: empresa1; Owner: sae
--

CREATE TABLE empresa1.ae_textos_recurso (
    id integer NOT NULL,
    texto_paso2 character varying(1000),
    texto_paso3 character varying(1000),
    ticket_etiqueta_dos character varying(15),
    ticket_etiqueta_uno character varying(15),
    titulo_ciudadano_en_llamador character varying(255),
    titulo_puesto_en_llamador character varying(255),
    valor_etiqueta_dos character varying(30),
    valor_etiqueta_uno character varying(30),
    aere_id integer NOT NULL,
    idioma character varying(5) NOT NULL
);


ALTER TABLE empresa1.ae_textos_recurso OWNER TO sae;

--
-- Name: ae_tokens_reservas; Type: TABLE; Schema: empresa1; Owner: sae
--

CREATE TABLE empresa1.ae_tokens_reservas (
    id integer NOT NULL,
    token character varying(50) NOT NULL,
    aere_id integer,
    fecha_inicio timestamp without time zone NOT NULL,
    ultima_reserva timestamp without time zone,
    estado character varying(1) NOT NULL,
    cedula character varying(50) NOT NULL,
    nombre character varying(100) NOT NULL,
    correoe character varying(100) NOT NULL,
    tramite character varying(10),
    notas character varying(4000),
    version integer NOT NULL,
    ip_origen character varying(16) DEFAULT NULL::character varying
);


ALTER TABLE empresa1.ae_tokens_reservas OWNER TO sae;

--
-- Name: ae_tramites_agendas; Type: TABLE; Schema: empresa1; Owner: sae
--

CREATE TABLE empresa1.ae_tramites_agendas (
    id integer NOT NULL,
    agenda_id integer NOT NULL,
    tramite_id character varying(25),
    tramite_codigo character varying(10),
    tramite_nombre character varying(100)
);


ALTER TABLE empresa1.ae_tramites_agendas OWNER TO sae;

--
-- Name: ae_validaciones; Type: TABLE; Schema: empresa1; Owner: sae
--

CREATE TABLE empresa1.ae_validaciones (
    id integer NOT NULL,
    descripcion character varying(100) NOT NULL,
    fecha_baja timestamp without time zone,
    host character varying(100),
    nombre character varying(50) NOT NULL,
    servicio character varying(250) NOT NULL
);


ALTER TABLE empresa1.ae_validaciones OWNER TO sae;

--
-- Name: ae_validaciones_por_dato; Type: TABLE; Schema: empresa1; Owner: sae
--

CREATE TABLE empresa1.ae_validaciones_por_dato (
    id integer NOT NULL,
    fecha_desasociacion timestamp without time zone,
    nombre_parametro character varying(50) NOT NULL,
    aeds_id integer NOT NULL,
    aevr_id integer NOT NULL
);


ALTER TABLE empresa1.ae_validaciones_por_dato OWNER TO sae;

--
-- Name: ae_validaciones_por_recurso; Type: TABLE; Schema: empresa1; Owner: sae
--

CREATE TABLE empresa1.ae_validaciones_por_recurso (
    id integer NOT NULL,
    fecha_baja timestamp without time zone,
    orden_ejecucion integer,
    aere_id integer NOT NULL,
    aeva_id integer NOT NULL
);


ALTER TABLE empresa1.ae_validaciones_por_recurso OWNER TO sae;

--
-- Name: ae_valor_constante_val_rec; Type: TABLE; Schema: empresa1; Owner: sae
--

CREATE TABLE empresa1.ae_valor_constante_val_rec (
    id integer NOT NULL,
    fecha_desasociacion timestamp without time zone,
    nombre_constante character varying(50) NOT NULL,
    valor character varying(100) NOT NULL,
    aevr_id integer NOT NULL
);


ALTER TABLE empresa1.ae_valor_constante_val_rec OWNER TO sae;

--
-- Name: ae_valores_del_dato; Type: TABLE; Schema: empresa1; Owner: sae
--

CREATE TABLE empresa1.ae_valores_del_dato (
    id integer NOT NULL,
    etiqueta character varying(50) NOT NULL,
    fecha_desde timestamp without time zone NOT NULL,
    fecha_hasta timestamp without time zone,
    orden integer NOT NULL,
    valor character varying(50) NOT NULL,
    aeds_id integer NOT NULL,
    borrar_flag boolean DEFAULT true NOT NULL
);


ALTER TABLE empresa1.ae_valores_del_dato OWNER TO sae;

--
-- Name: s_ae_accion; Type: SEQUENCE; Schema: empresa1; Owner: sae
--

CREATE SEQUENCE empresa1.s_ae_accion
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa1.s_ae_accion OWNER TO sae;

--
-- Name: s_ae_acciondato; Type: SEQUENCE; Schema: empresa1; Owner: sae
--

CREATE SEQUENCE empresa1.s_ae_acciondato
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa1.s_ae_acciondato OWNER TO sae;

--
-- Name: s_ae_acciones_miperfil; Type: SEQUENCE; Schema: empresa1; Owner: sae
--

CREATE SEQUENCE empresa1.s_ae_acciones_miperfil
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa1.s_ae_acciones_miperfil OWNER TO sae;

--
-- Name: s_ae_accionrecurso; Type: SEQUENCE; Schema: empresa1; Owner: sae
--

CREATE SEQUENCE empresa1.s_ae_accionrecurso
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa1.s_ae_accionrecurso OWNER TO sae;

--
-- Name: s_ae_agenda; Type: SEQUENCE; Schema: empresa1; Owner: sae
--

CREATE SEQUENCE empresa1.s_ae_agenda
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa1.s_ae_agenda OWNER TO sae;

--
-- Name: s_ae_agrupacion_dato; Type: SEQUENCE; Schema: empresa1; Owner: sae
--

CREATE SEQUENCE empresa1.s_ae_agrupacion_dato
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa1.s_ae_agrupacion_dato OWNER TO sae;

--
-- Name: s_ae_anio; Type: SEQUENCE; Schema: empresa1; Owner: sae
--

CREATE SEQUENCE empresa1.s_ae_anio
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa1.s_ae_anio OWNER TO sae;

--
-- Name: s_ae_atencion; Type: SEQUENCE; Schema: empresa1; Owner: sae
--

CREATE SEQUENCE empresa1.s_ae_atencion
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa1.s_ae_atencion OWNER TO sae;

--
-- Name: s_ae_comunicaciones; Type: SEQUENCE; Schema: empresa1; Owner: sae
--

CREATE SEQUENCE empresa1.s_ae_comunicaciones
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa1.s_ae_comunicaciones OWNER TO sae;

--
-- Name: s_ae_constval; Type: SEQUENCE; Schema: empresa1; Owner: sae
--

CREATE SEQUENCE empresa1.s_ae_constval
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa1.s_ae_constval OWNER TO sae;

--
-- Name: s_ae_datoasolicitar; Type: SEQUENCE; Schema: empresa1; Owner: sae
--

CREATE SEQUENCE empresa1.s_ae_datoasolicitar
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa1.s_ae_datoasolicitar OWNER TO sae;

--
-- Name: s_ae_datodelrecurso; Type: SEQUENCE; Schema: empresa1; Owner: sae
--

CREATE SEQUENCE empresa1.s_ae_datodelrecurso
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa1.s_ae_datodelrecurso OWNER TO sae;

--
-- Name: s_ae_datoreserva; Type: SEQUENCE; Schema: empresa1; Owner: sae
--

CREATE SEQUENCE empresa1.s_ae_datoreserva
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa1.s_ae_datoreserva OWNER TO sae;

--
-- Name: s_ae_dia_mes; Type: SEQUENCE; Schema: empresa1; Owner: sae
--

CREATE SEQUENCE empresa1.s_ae_dia_mes
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa1.s_ae_dia_mes OWNER TO sae;

--
-- Name: s_ae_dia_semana; Type: SEQUENCE; Schema: empresa1; Owner: sae
--

CREATE SEQUENCE empresa1.s_ae_dia_semana
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa1.s_ae_dia_semana OWNER TO sae;

--
-- Name: s_ae_disponibilidad; Type: SEQUENCE; Schema: empresa1; Owner: sae
--

CREATE SEQUENCE empresa1.s_ae_disponibilidad
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa1.s_ae_disponibilidad OWNER TO sae;

--
-- Name: s_ae_llamada; Type: SEQUENCE; Schema: empresa1; Owner: sae
--

CREATE SEQUENCE empresa1.s_ae_llamada
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa1.s_ae_llamada OWNER TO sae;

--
-- Name: s_ae_mes; Type: SEQUENCE; Schema: empresa1; Owner: sae
--

CREATE SEQUENCE empresa1.s_ae_mes
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa1.s_ae_mes OWNER TO sae;

--
-- Name: s_ae_paramaccion; Type: SEQUENCE; Schema: empresa1; Owner: sae
--

CREATE SEQUENCE empresa1.s_ae_paramaccion
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa1.s_ae_paramaccion OWNER TO sae;

--
-- Name: s_ae_parametros_autocompletar; Type: SEQUENCE; Schema: empresa1; Owner: sae
--

CREATE SEQUENCE empresa1.s_ae_parametros_autocompletar
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa1.s_ae_parametros_autocompletar OWNER TO sae;

--
-- Name: s_ae_paramval; Type: SEQUENCE; Schema: empresa1; Owner: sae
--

CREATE SEQUENCE empresa1.s_ae_paramval
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa1.s_ae_paramval OWNER TO sae;

--
-- Name: s_ae_plantilla; Type: SEQUENCE; Schema: empresa1; Owner: sae
--

CREATE SEQUENCE empresa1.s_ae_plantilla
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa1.s_ae_plantilla OWNER TO sae;

--
-- Name: s_ae_recurso; Type: SEQUENCE; Schema: empresa1; Owner: sae
--

CREATE SEQUENCE empresa1.s_ae_recurso
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa1.s_ae_recurso OWNER TO sae;

--
-- Name: s_ae_recurso_aud; Type: SEQUENCE; Schema: empresa1; Owner: sae
--

CREATE SEQUENCE empresa1.s_ae_recurso_aud
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa1.s_ae_recurso_aud OWNER TO sae;

--
-- Name: s_ae_reserva; Type: SEQUENCE; Schema: empresa1; Owner: sae
--

CREATE SEQUENCE empresa1.s_ae_reserva
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa1.s_ae_reserva OWNER TO sae;

--
-- Name: s_ae_serv_autocompletar; Type: SEQUENCE; Schema: empresa1; Owner: sae
--

CREATE SEQUENCE empresa1.s_ae_serv_autocompletar
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa1.s_ae_serv_autocompletar OWNER TO sae;

--
-- Name: s_ae_servdato; Type: SEQUENCE; Schema: empresa1; Owner: sae
--

CREATE SEQUENCE empresa1.s_ae_servdato
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa1.s_ae_servdato OWNER TO sae;

--
-- Name: s_ae_servrecurso; Type: SEQUENCE; Schema: empresa1; Owner: sae
--

CREATE SEQUENCE empresa1.s_ae_servrecurso
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa1.s_ae_servrecurso OWNER TO sae;

--
-- Name: s_ae_texto_agenda; Type: SEQUENCE; Schema: empresa1; Owner: sae
--

CREATE SEQUENCE empresa1.s_ae_texto_agenda
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa1.s_ae_texto_agenda OWNER TO sae;

--
-- Name: s_ae_textorecurso; Type: SEQUENCE; Schema: empresa1; Owner: sae
--

CREATE SEQUENCE empresa1.s_ae_textorecurso
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa1.s_ae_textorecurso OWNER TO sae;

--
-- Name: s_ae_token_reserva; Type: SEQUENCE; Schema: empresa1; Owner: sae
--

CREATE SEQUENCE empresa1.s_ae_token_reserva
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa1.s_ae_token_reserva OWNER TO sae;

--
-- Name: s_ae_tramites_agendas; Type: SEQUENCE; Schema: empresa1; Owner: sae
--

CREATE SEQUENCE empresa1.s_ae_tramites_agendas
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa1.s_ae_tramites_agendas OWNER TO sae;

--
-- Name: s_ae_valconstante; Type: SEQUENCE; Schema: empresa1; Owner: sae
--

CREATE SEQUENCE empresa1.s_ae_valconstante
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa1.s_ae_valconstante OWNER TO sae;

--
-- Name: s_ae_valdato; Type: SEQUENCE; Schema: empresa1; Owner: sae
--

CREATE SEQUENCE empresa1.s_ae_valdato
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa1.s_ae_valdato OWNER TO sae;

--
-- Name: s_ae_validacion; Type: SEQUENCE; Schema: empresa1; Owner: sae
--

CREATE SEQUENCE empresa1.s_ae_validacion
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa1.s_ae_validacion OWNER TO sae;

--
-- Name: s_ae_valorposible; Type: SEQUENCE; Schema: empresa1; Owner: sae
--

CREATE SEQUENCE empresa1.s_ae_valorposible
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa1.s_ae_valorposible OWNER TO sae;

--
-- Name: s_ae_valrecurso; Type: SEQUENCE; Schema: empresa1; Owner: sae
--

CREATE SEQUENCE empresa1.s_ae_valrecurso
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa1.s_ae_valrecurso OWNER TO sae;

--
-- Name: ae_acciones; Type: TABLE; Schema: empresa2; Owner: sae
--

CREATE TABLE empresa2.ae_acciones (
    id integer NOT NULL,
    descripcion character varying(100) NOT NULL,
    fecha_baja timestamp without time zone,
    host character varying(100),
    nombre character varying(50) NOT NULL,
    servicio character varying(250) NOT NULL
);


ALTER TABLE empresa2.ae_acciones OWNER TO sae;

--
-- Name: ae_acciones_miperfil_recurso; Type: TABLE; Schema: empresa2; Owner: sae
--

CREATE TABLE empresa2.ae_acciones_miperfil_recurso (
    id integer NOT NULL,
    recurso_id integer NOT NULL,
    titulo_con_1 character varying(100),
    url_con_1 character varying(1024),
    destacada_con_1 boolean,
    titulo_con_2 character varying(100),
    url_con_2 character varying(1024),
    destacada_con_2 boolean,
    titulo_con_3 character varying(100),
    url_con_3 character varying(1024),
    destacada_con_3 boolean,
    titulo_con_4 character varying(100),
    url_con_4 character varying(1024),
    destacada_con_4 boolean,
    titulo_con_5 character varying(100),
    url_con_5 character varying(1024),
    destacada_con_5 boolean,
    titulo_can_1 character varying(100),
    url_can_1 character varying(1024),
    destacada_can_1 boolean,
    titulo_can_2 character varying(100),
    url_can_2 character varying(1024),
    destacada_can_2 boolean,
    titulo_can_3 character varying(100),
    url_can_3 character varying(1024),
    destacada_can_3 boolean,
    titulo_can_4 character varying(100),
    url_can_4 character varying(1024),
    destacada_can_4 boolean,
    titulo_can_5 character varying(100),
    url_can_5 character varying(1024),
    destacada_can_5 boolean,
    titulo_rec_1 character varying(100),
    url_rec_1 character varying(1024),
    destacada_rec_1 boolean,
    titulo_rec_2 character varying(100),
    url_rec_2 character varying(1024),
    destacada_rec_2 boolean,
    titulo_rec_3 character varying(100),
    url_rec_3 character varying(1024),
    destacada_rec_3 boolean,
    titulo_rec_4 character varying(100),
    url_rec_4 character varying(1024),
    destacada_rec_4 boolean,
    titulo_rec_5 character varying(100),
    url_rec_5 character varying(1024),
    destacada_rec_5 boolean
);


ALTER TABLE empresa2.ae_acciones_miperfil_recurso OWNER TO sae;

--
-- Name: ae_acciones_por_dato; Type: TABLE; Schema: empresa2; Owner: sae
--

CREATE TABLE empresa2.ae_acciones_por_dato (
    id integer NOT NULL,
    fecha_desasociacion timestamp without time zone,
    nombre_parametro character varying(50) NOT NULL,
    aear_id integer NOT NULL,
    aeds_id integer NOT NULL
);


ALTER TABLE empresa2.ae_acciones_por_dato OWNER TO sae;

--
-- Name: ae_acciones_por_recurso; Type: TABLE; Schema: empresa2; Owner: sae
--

CREATE TABLE empresa2.ae_acciones_por_recurso (
    id integer NOT NULL,
    evento character varying(1) NOT NULL,
    fecha_baja timestamp without time zone,
    orden_ejecucion integer,
    aeac_id integer NOT NULL,
    aere_id integer NOT NULL
);


ALTER TABLE empresa2.ae_acciones_por_recurso OWNER TO sae;

--
-- Name: ae_agendas; Type: TABLE; Schema: empresa2; Owner: sae
--

CREATE TABLE empresa2.ae_agendas (
    id integer NOT NULL,
    descripcion character varying(1000) NOT NULL,
    fecha_baja timestamp without time zone,
    nombre character varying(100) NOT NULL,
    tramite_id character varying(25),
    timezone character varying(100),
    idiomas character varying(100),
    con_cda boolean DEFAULT false,
    tramite_codigo character varying(10),
    publicar_novedades boolean DEFAULT false NOT NULL,
    con_trazabilidad boolean DEFAULT false NOT NULL
);


ALTER TABLE empresa2.ae_agendas OWNER TO sae;

--
-- Name: ae_agrupaciones_datos; Type: TABLE; Schema: empresa2; Owner: sae
--

CREATE TABLE empresa2.ae_agrupaciones_datos (
    id integer NOT NULL,
    fecha_baja timestamp without time zone,
    nombre character varying(50) NOT NULL,
    orden integer NOT NULL,
    aere_id integer NOT NULL,
    borrar_flag boolean NOT NULL,
    etiqueta character varying(50)
);


ALTER TABLE empresa2.ae_agrupaciones_datos OWNER TO sae;

--
-- Name: ae_anios; Type: TABLE; Schema: empresa2; Owner: sae
--

CREATE TABLE empresa2.ae_anios (
    id integer NOT NULL,
    anio integer NOT NULL,
    aepl_id integer NOT NULL
);


ALTER TABLE empresa2.ae_anios OWNER TO sae;

--
-- Name: ae_atencion; Type: TABLE; Schema: empresa2; Owner: sae
--

CREATE TABLE empresa2.ae_atencion (
    id integer NOT NULL,
    asistio boolean NOT NULL,
    duracion integer NOT NULL,
    fact timestamp without time zone NOT NULL,
    fcrea timestamp without time zone NOT NULL,
    funcionario character varying(255) NOT NULL,
    aers_id integer NOT NULL
);


ALTER TABLE empresa2.ae_atencion OWNER TO sae;

--
-- Name: ae_comunicaciones; Type: TABLE; Schema: empresa2; Owner: sae
--

CREATE TABLE empresa2.ae_comunicaciones (
    id integer NOT NULL,
    tipo_1 character varying(25) NOT NULL,
    tipo_2 character varying(25) NOT NULL,
    destino character varying(100) NOT NULL,
    recurso_id integer NOT NULL,
    reserva_id integer,
    token_id integer,
    procesado boolean DEFAULT false NOT NULL,
    mensaje character varying(4000)
);


ALTER TABLE empresa2.ae_comunicaciones OWNER TO sae;

--
-- Name: ae_constante_validacion; Type: TABLE; Schema: empresa2; Owner: sae
--

CREATE TABLE empresa2.ae_constante_validacion (
    id integer NOT NULL,
    fecha_baja timestamp without time zone,
    largo integer NOT NULL,
    nombre character varying(50) NOT NULL,
    tipo character varying(30) NOT NULL,
    aeva_id integer NOT NULL
);


ALTER TABLE empresa2.ae_constante_validacion OWNER TO sae;

--
-- Name: ae_datos_a_solicitar; Type: TABLE; Schema: empresa2; Owner: sae
--

CREATE TABLE empresa2.ae_datos_a_solicitar (
    id integer NOT NULL,
    ancho_despliegue integer NOT NULL,
    columna integer NOT NULL,
    es_clave boolean NOT NULL,
    etiqueta character varying(50) NOT NULL,
    fecha_baja timestamp without time zone,
    fila integer NOT NULL,
    incluir_en_llamador boolean NOT NULL,
    incluir_en_reporte boolean NOT NULL,
    largo integer,
    largo_en_llamador integer NOT NULL,
    nombre character varying(50) NOT NULL,
    orden_en_llamador integer NOT NULL,
    requerido boolean NOT NULL,
    texto_ayuda character varying(100),
    tipo character varying(30) NOT NULL,
    aead_id integer NOT NULL,
    aere_id integer NOT NULL,
    borrar_flag boolean DEFAULT true NOT NULL,
    solo_lectura boolean DEFAULT false NOT NULL,
    incluir_en_novedades boolean DEFAULT false NOT NULL
);


ALTER TABLE empresa2.ae_datos_a_solicitar OWNER TO sae;

--
-- Name: ae_datos_del_recurso; Type: TABLE; Schema: empresa2; Owner: sae
--

CREATE TABLE empresa2.ae_datos_del_recurso (
    id integer NOT NULL,
    etiqueta character varying(50) NOT NULL,
    orden integer NOT NULL,
    valor character varying(100) NOT NULL,
    aere_id integer NOT NULL
);


ALTER TABLE empresa2.ae_datos_del_recurso OWNER TO sae;

--
-- Name: ae_datos_reserva; Type: TABLE; Schema: empresa2; Owner: sae
--

CREATE TABLE empresa2.ae_datos_reserva (
    id integer NOT NULL,
    valor character varying(100) NOT NULL,
    aeds_id integer NOT NULL,
    aers_id integer NOT NULL
);


ALTER TABLE empresa2.ae_datos_reserva OWNER TO sae;

--
-- Name: ae_dias_del_mes; Type: TABLE; Schema: empresa2; Owner: sae
--

CREATE TABLE empresa2.ae_dias_del_mes (
    id integer NOT NULL,
    dia_del_mes integer NOT NULL,
    aepl_id integer NOT NULL
);


ALTER TABLE empresa2.ae_dias_del_mes OWNER TO sae;

--
-- Name: ae_dias_semana; Type: TABLE; Schema: empresa2; Owner: sae
--

CREATE TABLE empresa2.ae_dias_semana (
    id integer NOT NULL,
    dia_semana integer NOT NULL,
    aepl_id integer NOT NULL
);


ALTER TABLE empresa2.ae_dias_semana OWNER TO sae;

--
-- Name: ae_disponibilidades; Type: TABLE; Schema: empresa2; Owner: sae
--

CREATE TABLE empresa2.ae_disponibilidades (
    id integer NOT NULL,
    cupo integer NOT NULL,
    fecha date NOT NULL,
    fecha_baja timestamp without time zone,
    hora_fin timestamp without time zone NOT NULL,
    hora_inicio timestamp without time zone NOT NULL,
    numerador integer NOT NULL,
    version integer NOT NULL,
    aepl_id integer,
    aere_id integer NOT NULL,
    presencial boolean DEFAULT false NOT NULL
);


ALTER TABLE empresa2.ae_disponibilidades OWNER TO sae;

--
-- Name: ae_frases_captcha; Type: TABLE; Schema: empresa2; Owner: sae
--

CREATE TABLE empresa2.ae_frases_captcha (
    clave character varying(100) NOT NULL,
    frase character varying(1024),
    idioma character varying(5) NOT NULL
);


ALTER TABLE empresa2.ae_frases_captcha OWNER TO sae;

--
-- Name: ae_llamadas; Type: TABLE; Schema: empresa2; Owner: sae
--

CREATE TABLE empresa2.ae_llamadas (
    id integer NOT NULL,
    etiqueta character varying(100) NOT NULL,
    fecha date NOT NULL,
    hora timestamp without time zone NOT NULL,
    numero integer NOT NULL,
    puesto integer NOT NULL,
    aere_id integer NOT NULL,
    aers_id integer NOT NULL
);


ALTER TABLE empresa2.ae_llamadas OWNER TO sae;

--
-- Name: ae_meses; Type: TABLE; Schema: empresa2; Owner: sae
--

CREATE TABLE empresa2.ae_meses (
    id integer NOT NULL,
    mes integer NOT NULL,
    aepl_id integer NOT NULL
);


ALTER TABLE empresa2.ae_meses OWNER TO sae;

--
-- Name: ae_parametros_accion; Type: TABLE; Schema: empresa2; Owner: sae
--

CREATE TABLE empresa2.ae_parametros_accion (
    id integer NOT NULL,
    fecha_baja timestamp without time zone,
    largo integer,
    nombre character varying(50) NOT NULL,
    tipo character varying(30),
    aeac_id integer NOT NULL
);


ALTER TABLE empresa2.ae_parametros_accion OWNER TO sae;

--
-- Name: ae_parametros_autocompletar; Type: TABLE; Schema: empresa2; Owner: sae
--

CREATE TABLE empresa2.ae_parametros_autocompletar (
    id integer NOT NULL,
    fecha_baja timestamp without time zone,
    modo integer NOT NULL,
    nombre character varying(50) NOT NULL,
    tipo character varying(30) NOT NULL,
    aeserv_id integer NOT NULL
);


ALTER TABLE empresa2.ae_parametros_autocompletar OWNER TO sae;

--
-- Name: ae_parametros_validacion; Type: TABLE; Schema: empresa2; Owner: sae
--

CREATE TABLE empresa2.ae_parametros_validacion (
    id integer NOT NULL,
    fecha_baja timestamp without time zone,
    largo integer,
    nombre character varying(50) NOT NULL,
    tipo character varying(30),
    aeva_id integer NOT NULL
);


ALTER TABLE empresa2.ae_parametros_validacion OWNER TO sae;

--
-- Name: ae_plantillas; Type: TABLE; Schema: empresa2; Owner: sae
--

CREATE TABLE empresa2.ae_plantillas (
    id integer NOT NULL,
    cupo integer NOT NULL,
    fecha_baja timestamp without time zone,
    frecuencia integer NOT NULL,
    generacion_automatica boolean NOT NULL,
    hora_fin timestamp without time zone NOT NULL,
    hora_inicio timestamp without time zone NOT NULL,
    aere_id integer NOT NULL
);


ALTER TABLE empresa2.ae_plantillas OWNER TO sae;

--
-- Name: ae_preguntas_captcha; Type: TABLE; Schema: empresa2; Owner: sae
--

CREATE TABLE empresa2.ae_preguntas_captcha (
    clave character varying(100) NOT NULL,
    pregunta character varying(1024),
    respuesta character varying(25),
    idioma character varying(5) NOT NULL
);


ALTER TABLE empresa2.ae_preguntas_captcha OWNER TO sae;

--
-- Name: ae_recursos; Type: TABLE; Schema: empresa2; Owner: sae
--

CREATE TABLE empresa2.ae_recursos (
    id integer NOT NULL,
    cant_dias_a_generar integer NOT NULL,
    descripcion character varying(1000) NOT NULL,
    dias_inicio_ventana_internet integer NOT NULL,
    dias_inicio_ventana_intranet integer NOT NULL,
    dias_ventana_internet integer NOT NULL,
    dias_ventana_intranet integer NOT NULL,
    fecha_baja timestamp without time zone,
    fecha_fin timestamp without time zone,
    fecha_fin_disp timestamp without time zone,
    fecha_inicio timestamp without time zone NOT NULL,
    fecha_inicio_disp timestamp without time zone NOT NULL,
    largo_lista_espera integer,
    mostrar_numero_en_llamador boolean NOT NULL,
    mostrar_numero_en_ticket boolean NOT NULL,
    nombre character varying(100) NOT NULL,
    sabado_es_habil boolean NOT NULL,
    serie character varying(3),
    usar_llamador boolean NOT NULL,
    ventana_cupos_minimos integer NOT NULL,
    version integer NOT NULL,
    visible_internet boolean NOT NULL,
    aeag_id integer NOT NULL,
    oficina_id character varying(25),
    direccion character varying(100),
    localidad character varying(100),
    departamento character varying(100),
    telefonos character varying(100),
    horarios character varying(100),
    latitud numeric,
    longitud numeric,
    mostrar_id_en_ticket boolean,
    domingo_es_habil boolean DEFAULT false NOT NULL,
    presencial_admite boolean DEFAULT false NOT NULL,
    presencial_lunes boolean DEFAULT false NOT NULL,
    presencial_martes boolean DEFAULT false NOT NULL,
    presencial_miercoles boolean DEFAULT false NOT NULL,
    presencial_jueves boolean DEFAULT false NOT NULL,
    presencial_viernes boolean DEFAULT false NOT NULL,
    presencial_sabado boolean DEFAULT false NOT NULL,
    presencial_domingo boolean DEFAULT false NOT NULL,
    presencial_cupos integer DEFAULT 0 NOT NULL,
    tamanio_fuente_grande integer DEFAULT 12 NOT NULL,
    tamanio_fuente_normal integer DEFAULT 10 NOT NULL,
    tamanio_fuente_chica integer DEFAULT 8 NOT NULL,
    fuente_ticket character varying(100) DEFAULT 'Helvetica-Bold'::character varying NOT NULL,
    multiple_admite boolean DEFAULT false NOT NULL,
    cambios_admite boolean DEFAULT false NOT NULL,
    cambios_tiempo integer,
    cambios_unidad integer,
    periodo_validacion integer DEFAULT 0 NOT NULL,
    validar_por_ip boolean DEFAULT false NOT NULL,
    cantidad_por_ip integer,
    periodo_por_ip integer,
    ips_sin_validacion character varying(4000) DEFAULT NULL::character varying,
    cancela_tiempo integer DEFAULT 0 NOT NULL,
    cancela_unidad integer DEFAULT 12 NOT NULL,
    cancela_tipo character varying(1) DEFAULT 'I'::character varying NOT NULL,
    mi_perfil_con_hab boolean DEFAULT true NOT NULL,
    mi_perfil_can_hab boolean DEFAULT true NOT NULL,
    mi_perfil_rec_hab boolean DEFAULT true NOT NULL,
    mi_perfil_con_tit character varying(255) DEFAULT NULL::character varying,
    mi_perfil_con_cor character varying(500) DEFAULT NULL::character varying,
    mi_perfil_con_lar character varying(1024) DEFAULT NULL::character varying,
    mi_perfil_con_ven integer,
    mi_perfil_can_tit character varying(255) DEFAULT NULL::character varying,
    mi_perfil_can_cor character varying(500) DEFAULT NULL::character varying,
    mi_perfil_can_lar character varying(1024) DEFAULT NULL::character varying,
    mi_perfil_can_ven integer,
    mi_perfil_rec_tit character varying(255) DEFAULT NULL::character varying,
    mi_perfil_rec_cor character varying(500) DEFAULT NULL::character varying,
    mi_perfil_rec_lar character varying(1024) DEFAULT NULL::character varying,
    mi_perfil_rec_ven integer,
    mi_perfil_rec_hora integer,
    mi_perfil_rec_dias integer
);


ALTER TABLE empresa2.ae_recursos OWNER TO sae;

--
-- Name: ae_recursos_aud; Type: TABLE; Schema: empresa2; Owner: sae
--

CREATE TABLE empresa2.ae_recursos_aud (
    id integer NOT NULL,
    id_recurso integer NOT NULL,
    nombre character varying(100) NOT NULL,
    descripcion character varying(1000) NOT NULL,
    fecha_inicio timestamp without time zone NOT NULL,
    fecha_fin timestamp without time zone,
    fecha_inicio_disp timestamp without time zone NOT NULL,
    fecha_fin_disp timestamp without time zone,
    dias_inicio_ventana_intranet integer NOT NULL,
    dias_ventana_intranet integer NOT NULL,
    dias_inicio_ventana_internet integer NOT NULL,
    dias_ventana_internet integer NOT NULL,
    ventana_cupos_minimos integer NOT NULL,
    cant_dias_a_generar integer NOT NULL,
    largo_lista_espera integer,
    fecha_baja timestamp without time zone,
    mostrar_numero_en_llamador boolean NOT NULL,
    visible_internet boolean NOT NULL,
    usar_llamador boolean NOT NULL,
    serie character varying(3),
    sabado_es_habil boolean NOT NULL,
    domingo_es_habil boolean DEFAULT false NOT NULL,
    mostrar_numero_en_ticket boolean NOT NULL,
    mostrar_id_en_ticket boolean,
    fuente_ticket character varying(100) DEFAULT 'Helvetica-Bold'::character varying NOT NULL,
    tamanio_fuente_grande integer DEFAULT 12 NOT NULL,
    tamanio_fuente_normal integer DEFAULT 10 NOT NULL,
    tamanio_fuente_chica integer DEFAULT 8 NOT NULL,
    oficina_id character varying(25),
    direccion character varying(100),
    localidad character varying(100),
    departamento character varying(100),
    telefonos character varying(100),
    horarios character varying(100),
    latitud numeric,
    longitud numeric,
    agenda integer NOT NULL,
    presencial_admite boolean DEFAULT false NOT NULL,
    presencial_cupos integer DEFAULT 0 NOT NULL,
    presencial_lunes boolean DEFAULT false NOT NULL,
    presencial_martes boolean DEFAULT false NOT NULL,
    presencial_miercoles boolean DEFAULT false NOT NULL,
    presencial_jueves boolean DEFAULT false NOT NULL,
    presencial_viernes boolean DEFAULT false NOT NULL,
    presencial_sabado boolean DEFAULT false NOT NULL,
    presencial_domingo boolean DEFAULT false NOT NULL,
    multiple_admite boolean DEFAULT false NOT NULL,
    cambios_admite boolean DEFAULT false NOT NULL,
    cambios_tiempo integer,
    cambios_unidad integer,
    periodo_validacion integer DEFAULT 0 NOT NULL,
    validar_por_ip boolean DEFAULT false NOT NULL,
    cantidad_por_ip integer,
    periodo_por_ip integer,
    ips_sin_validacion character varying(4000) DEFAULT NULL::character varying,
    cancela_tiempo integer DEFAULT 0 NOT NULL,
    cancela_unidad integer DEFAULT 12 NOT NULL,
    cancela_tipo character varying(1) DEFAULT 'I'::character varying NOT NULL,
    mi_perfil_con_hab boolean DEFAULT true NOT NULL,
    mi_perfil_con_tit character varying(200) DEFAULT NULL::character varying,
    mi_perfil_con_cor character varying(500) DEFAULT NULL::character varying,
    mi_perfil_con_lar character varying(3200) DEFAULT NULL::character varying,
    mi_perfil_con_ven integer,
    mi_perfil_can_hab boolean DEFAULT true NOT NULL,
    mi_perfil_can_tit character varying(200) DEFAULT NULL::character varying,
    mi_perfil_can_cor character varying(500) DEFAULT NULL::character varying,
    mi_perfil_can_lar character varying(3200) DEFAULT NULL::character varying,
    mi_perfil_can_ven integer,
    mi_perfil_rec_hab boolean DEFAULT true NOT NULL,
    mi_perfil_rec_tit character varying(200) DEFAULT NULL::character varying,
    mi_perfil_rec_cor character varying(500) DEFAULT NULL::character varying,
    mi_perfil_rec_lar character varying(3200) DEFAULT NULL::character varying,
    mi_perfil_rec_ven integer,
    mi_perfil_rec_hora integer,
    mi_perfil_rec_dias integer,
    reserva_pen_tiempo_max integer,
    reserva_pend_tiempo_max integer,
    fecha_modificacion timestamp without time zone,
    usuario character varying(45),
    version integer NOT NULL,
    tipo_operacion smallint
);


ALTER TABLE empresa2.ae_recursos_aud OWNER TO sae;

--
-- Name: ae_reservas; Type: TABLE; Schema: empresa2; Owner: sae
--

CREATE TABLE empresa2.ae_reservas (
    id integer NOT NULL,
    estado character varying(1) NOT NULL,
    fact timestamp without time zone NOT NULL,
    fcrea timestamp without time zone NOT NULL,
    numero integer,
    observaciones character varying(100),
    origen character varying(1),
    ucancela character varying(255),
    ucrea character varying(255),
    version integer NOT NULL,
    codigo_seguridad character varying(10) DEFAULT '00000'::character varying,
    trazabilidad_guid character varying(25),
    tramite_codigo character varying(10),
    tramite_nombre character varying(100),
    serie character varying(3),
    tcancela character varying(1),
    fcancela timestamp without time zone,
    aetr_id integer,
    ip_origen character varying(16) DEFAULT NULL::character varying,
    flibera timestamp without time zone,
    mi_perfil_notif boolean DEFAULT true NOT NULL,
    notificar boolean DEFAULT true,
    reserva_hija_id integer
);


ALTER TABLE empresa2.ae_reservas OWNER TO sae;

--
-- Name: ae_reservas_disponibilidades; Type: TABLE; Schema: empresa2; Owner: sae
--

CREATE TABLE empresa2.ae_reservas_disponibilidades (
    aers_id integer NOT NULL,
    aedi_id integer NOT NULL
);


ALTER TABLE empresa2.ae_reservas_disponibilidades OWNER TO sae;

--
-- Name: ae_roles_usuario_recurso; Type: TABLE; Schema: empresa2; Owner: sae
--

CREATE TABLE empresa2.ae_roles_usuario_recurso (
    usuario_id integer NOT NULL,
    recurso_id integer NOT NULL,
    roles character varying(4000)
);


ALTER TABLE empresa2.ae_roles_usuario_recurso OWNER TO sae;

--
-- Name: ae_serv_autocomp_por_dato; Type: TABLE; Schema: empresa2; Owner: sae
--

CREATE TABLE empresa2.ae_serv_autocomp_por_dato (
    id integer NOT NULL,
    fecha_desasociacion timestamp without time zone,
    nombre_parametro character varying(50) NOT NULL,
    aeds_id integer NOT NULL,
    aesr_id integer NOT NULL
);


ALTER TABLE empresa2.ae_serv_autocomp_por_dato OWNER TO sae;

--
-- Name: ae_serv_autocompletar; Type: TABLE; Schema: empresa2; Owner: sae
--

CREATE TABLE empresa2.ae_serv_autocompletar (
    id integer NOT NULL,
    descripcion character varying(100) NOT NULL,
    fecha_baja timestamp without time zone,
    host character varying(100),
    nombre character varying(50) NOT NULL,
    servicio character varying(250) NOT NULL
);


ALTER TABLE empresa2.ae_serv_autocompletar OWNER TO sae;

--
-- Name: ae_servicio_por_recurso; Type: TABLE; Schema: empresa2; Owner: sae
--

CREATE TABLE empresa2.ae_servicio_por_recurso (
    id integer NOT NULL,
    fecha_baja timestamp without time zone,
    aeserv_id integer NOT NULL,
    aere_id integer NOT NULL
);


ALTER TABLE empresa2.ae_servicio_por_recurso OWNER TO sae;

--
-- Name: ae_textos; Type: TABLE; Schema: empresa2; Owner: sae
--

CREATE TABLE empresa2.ae_textos (
    codigo character varying(100) NOT NULL,
    idioma character varying(5) NOT NULL,
    texto character varying(4096) NOT NULL
);


ALTER TABLE empresa2.ae_textos OWNER TO sae;

--
-- Name: ae_textos_agenda; Type: TABLE; Schema: empresa2; Owner: sae
--

CREATE TABLE empresa2.ae_textos_agenda (
    id integer NOT NULL,
    texto_paso1 character varying(1000),
    texto_paso2 character varying(1000),
    texto_paso3 character varying(1000),
    texto_sel_recurso character varying(50),
    texto_ticket character varying(1000),
    aeag_id integer NOT NULL,
    texto_correo_conf character varying(1000),
    texto_correo_canc character varying(1000),
    por_defecto boolean DEFAULT false NOT NULL,
    idioma character varying(5) NOT NULL
);


ALTER TABLE empresa2.ae_textos_agenda OWNER TO sae;

--
-- Name: ae_textos_recurso; Type: TABLE; Schema: empresa2; Owner: sae
--

CREATE TABLE empresa2.ae_textos_recurso (
    id integer NOT NULL,
    texto_paso2 character varying(1000),
    texto_paso3 character varying(1000),
    ticket_etiqueta_dos character varying(15),
    ticket_etiqueta_uno character varying(15),
    titulo_ciudadano_en_llamador character varying(255),
    titulo_puesto_en_llamador character varying(255),
    valor_etiqueta_dos character varying(30),
    valor_etiqueta_uno character varying(30),
    aere_id integer NOT NULL,
    idioma character varying(5) NOT NULL
);


ALTER TABLE empresa2.ae_textos_recurso OWNER TO sae;

--
-- Name: ae_tokens_reservas; Type: TABLE; Schema: empresa2; Owner: sae
--

CREATE TABLE empresa2.ae_tokens_reservas (
    id integer NOT NULL,
    token character varying(50) NOT NULL,
    aere_id integer,
    fecha_inicio timestamp without time zone NOT NULL,
    ultima_reserva timestamp without time zone,
    estado character varying(1) NOT NULL,
    cedula character varying(50) NOT NULL,
    nombre character varying(100) NOT NULL,
    correoe character varying(100) NOT NULL,
    tramite character varying(10),
    notas character varying(4000),
    version integer NOT NULL,
    ip_origen character varying(16) DEFAULT NULL::character varying
);


ALTER TABLE empresa2.ae_tokens_reservas OWNER TO sae;

--
-- Name: ae_tramites_agendas; Type: TABLE; Schema: empresa2; Owner: sae
--

CREATE TABLE empresa2.ae_tramites_agendas (
    id integer NOT NULL,
    agenda_id integer NOT NULL,
    tramite_id character varying(25),
    tramite_codigo character varying(10),
    tramite_nombre character varying(100)
);


ALTER TABLE empresa2.ae_tramites_agendas OWNER TO sae;

--
-- Name: ae_validaciones; Type: TABLE; Schema: empresa2; Owner: sae
--

CREATE TABLE empresa2.ae_validaciones (
    id integer NOT NULL,
    descripcion character varying(100) NOT NULL,
    fecha_baja timestamp without time zone,
    host character varying(100),
    nombre character varying(50) NOT NULL,
    servicio character varying(250) NOT NULL
);


ALTER TABLE empresa2.ae_validaciones OWNER TO sae;

--
-- Name: ae_validaciones_por_dato; Type: TABLE; Schema: empresa2; Owner: sae
--

CREATE TABLE empresa2.ae_validaciones_por_dato (
    id integer NOT NULL,
    fecha_desasociacion timestamp without time zone,
    nombre_parametro character varying(50) NOT NULL,
    aeds_id integer NOT NULL,
    aevr_id integer NOT NULL
);


ALTER TABLE empresa2.ae_validaciones_por_dato OWNER TO sae;

--
-- Name: ae_validaciones_por_recurso; Type: TABLE; Schema: empresa2; Owner: sae
--

CREATE TABLE empresa2.ae_validaciones_por_recurso (
    id integer NOT NULL,
    fecha_baja timestamp without time zone,
    orden_ejecucion integer,
    aere_id integer NOT NULL,
    aeva_id integer NOT NULL
);


ALTER TABLE empresa2.ae_validaciones_por_recurso OWNER TO sae;

--
-- Name: ae_valor_constante_val_rec; Type: TABLE; Schema: empresa2; Owner: sae
--

CREATE TABLE empresa2.ae_valor_constante_val_rec (
    id integer NOT NULL,
    fecha_desasociacion timestamp without time zone,
    nombre_constante character varying(50) NOT NULL,
    valor character varying(100) NOT NULL,
    aevr_id integer NOT NULL
);


ALTER TABLE empresa2.ae_valor_constante_val_rec OWNER TO sae;

--
-- Name: ae_valores_del_dato; Type: TABLE; Schema: empresa2; Owner: sae
--

CREATE TABLE empresa2.ae_valores_del_dato (
    id integer NOT NULL,
    etiqueta character varying(50) NOT NULL,
    fecha_desde timestamp without time zone NOT NULL,
    fecha_hasta timestamp without time zone,
    orden integer NOT NULL,
    valor character varying(50) NOT NULL,
    aeds_id integer NOT NULL,
    borrar_flag boolean DEFAULT true NOT NULL
);


ALTER TABLE empresa2.ae_valores_del_dato OWNER TO sae;

--
-- Name: s_ae_accion; Type: SEQUENCE; Schema: empresa2; Owner: sae
--

CREATE SEQUENCE empresa2.s_ae_accion
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa2.s_ae_accion OWNER TO sae;

--
-- Name: s_ae_acciondato; Type: SEQUENCE; Schema: empresa2; Owner: sae
--

CREATE SEQUENCE empresa2.s_ae_acciondato
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa2.s_ae_acciondato OWNER TO sae;

--
-- Name: s_ae_acciones_miperfil; Type: SEQUENCE; Schema: empresa2; Owner: sae
--

CREATE SEQUENCE empresa2.s_ae_acciones_miperfil
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa2.s_ae_acciones_miperfil OWNER TO sae;

--
-- Name: s_ae_accionrecurso; Type: SEQUENCE; Schema: empresa2; Owner: sae
--

CREATE SEQUENCE empresa2.s_ae_accionrecurso
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa2.s_ae_accionrecurso OWNER TO sae;

--
-- Name: s_ae_agenda; Type: SEQUENCE; Schema: empresa2; Owner: sae
--

CREATE SEQUENCE empresa2.s_ae_agenda
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa2.s_ae_agenda OWNER TO sae;

--
-- Name: s_ae_agrupacion_dato; Type: SEQUENCE; Schema: empresa2; Owner: sae
--

CREATE SEQUENCE empresa2.s_ae_agrupacion_dato
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa2.s_ae_agrupacion_dato OWNER TO sae;

--
-- Name: s_ae_anio; Type: SEQUENCE; Schema: empresa2; Owner: sae
--

CREATE SEQUENCE empresa2.s_ae_anio
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa2.s_ae_anio OWNER TO sae;

--
-- Name: s_ae_atencion; Type: SEQUENCE; Schema: empresa2; Owner: sae
--

CREATE SEQUENCE empresa2.s_ae_atencion
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa2.s_ae_atencion OWNER TO sae;

--
-- Name: s_ae_comunicaciones; Type: SEQUENCE; Schema: empresa2; Owner: sae
--

CREATE SEQUENCE empresa2.s_ae_comunicaciones
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa2.s_ae_comunicaciones OWNER TO sae;

--
-- Name: s_ae_constval; Type: SEQUENCE; Schema: empresa2; Owner: sae
--

CREATE SEQUENCE empresa2.s_ae_constval
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa2.s_ae_constval OWNER TO sae;

--
-- Name: s_ae_datoasolicitar; Type: SEQUENCE; Schema: empresa2; Owner: sae
--

CREATE SEQUENCE empresa2.s_ae_datoasolicitar
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa2.s_ae_datoasolicitar OWNER TO sae;

--
-- Name: s_ae_datodelrecurso; Type: SEQUENCE; Schema: empresa2; Owner: sae
--

CREATE SEQUENCE empresa2.s_ae_datodelrecurso
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa2.s_ae_datodelrecurso OWNER TO sae;

--
-- Name: s_ae_datoreserva; Type: SEQUENCE; Schema: empresa2; Owner: sae
--

CREATE SEQUENCE empresa2.s_ae_datoreserva
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa2.s_ae_datoreserva OWNER TO sae;

--
-- Name: s_ae_dia_mes; Type: SEQUENCE; Schema: empresa2; Owner: sae
--

CREATE SEQUENCE empresa2.s_ae_dia_mes
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa2.s_ae_dia_mes OWNER TO sae;

--
-- Name: s_ae_dia_semana; Type: SEQUENCE; Schema: empresa2; Owner: sae
--

CREATE SEQUENCE empresa2.s_ae_dia_semana
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa2.s_ae_dia_semana OWNER TO sae;

--
-- Name: s_ae_disponibilidad; Type: SEQUENCE; Schema: empresa2; Owner: sae
--

CREATE SEQUENCE empresa2.s_ae_disponibilidad
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa2.s_ae_disponibilidad OWNER TO sae;

--
-- Name: s_ae_llamada; Type: SEQUENCE; Schema: empresa2; Owner: sae
--

CREATE SEQUENCE empresa2.s_ae_llamada
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa2.s_ae_llamada OWNER TO sae;

--
-- Name: s_ae_mes; Type: SEQUENCE; Schema: empresa2; Owner: sae
--

CREATE SEQUENCE empresa2.s_ae_mes
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa2.s_ae_mes OWNER TO sae;

--
-- Name: s_ae_paramaccion; Type: SEQUENCE; Schema: empresa2; Owner: sae
--

CREATE SEQUENCE empresa2.s_ae_paramaccion
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa2.s_ae_paramaccion OWNER TO sae;

--
-- Name: s_ae_parametros_autocompletar; Type: SEQUENCE; Schema: empresa2; Owner: sae
--

CREATE SEQUENCE empresa2.s_ae_parametros_autocompletar
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa2.s_ae_parametros_autocompletar OWNER TO sae;

--
-- Name: s_ae_paramval; Type: SEQUENCE; Schema: empresa2; Owner: sae
--

CREATE SEQUENCE empresa2.s_ae_paramval
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa2.s_ae_paramval OWNER TO sae;

--
-- Name: s_ae_plantilla; Type: SEQUENCE; Schema: empresa2; Owner: sae
--

CREATE SEQUENCE empresa2.s_ae_plantilla
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa2.s_ae_plantilla OWNER TO sae;

--
-- Name: s_ae_recurso; Type: SEQUENCE; Schema: empresa2; Owner: sae
--

CREATE SEQUENCE empresa2.s_ae_recurso
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa2.s_ae_recurso OWNER TO sae;

--
-- Name: s_ae_recurso_aud; Type: SEQUENCE; Schema: empresa2; Owner: sae
--

CREATE SEQUENCE empresa2.s_ae_recurso_aud
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa2.s_ae_recurso_aud OWNER TO sae;

--
-- Name: s_ae_reserva; Type: SEQUENCE; Schema: empresa2; Owner: sae
--

CREATE SEQUENCE empresa2.s_ae_reserva
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa2.s_ae_reserva OWNER TO sae;

--
-- Name: s_ae_serv_autocompletar; Type: SEQUENCE; Schema: empresa2; Owner: sae
--

CREATE SEQUENCE empresa2.s_ae_serv_autocompletar
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa2.s_ae_serv_autocompletar OWNER TO sae;

--
-- Name: s_ae_servdato; Type: SEQUENCE; Schema: empresa2; Owner: sae
--

CREATE SEQUENCE empresa2.s_ae_servdato
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa2.s_ae_servdato OWNER TO sae;

--
-- Name: s_ae_servrecurso; Type: SEQUENCE; Schema: empresa2; Owner: sae
--

CREATE SEQUENCE empresa2.s_ae_servrecurso
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa2.s_ae_servrecurso OWNER TO sae;

--
-- Name: s_ae_texto_agenda; Type: SEQUENCE; Schema: empresa2; Owner: sae
--

CREATE SEQUENCE empresa2.s_ae_texto_agenda
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa2.s_ae_texto_agenda OWNER TO sae;

--
-- Name: s_ae_textorecurso; Type: SEQUENCE; Schema: empresa2; Owner: sae
--

CREATE SEQUENCE empresa2.s_ae_textorecurso
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa2.s_ae_textorecurso OWNER TO sae;

--
-- Name: s_ae_token_reserva; Type: SEQUENCE; Schema: empresa2; Owner: sae
--

CREATE SEQUENCE empresa2.s_ae_token_reserva
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa2.s_ae_token_reserva OWNER TO sae;

--
-- Name: s_ae_tramites_agendas; Type: SEQUENCE; Schema: empresa2; Owner: sae
--

CREATE SEQUENCE empresa2.s_ae_tramites_agendas
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa2.s_ae_tramites_agendas OWNER TO sae;

--
-- Name: s_ae_valconstante; Type: SEQUENCE; Schema: empresa2; Owner: sae
--

CREATE SEQUENCE empresa2.s_ae_valconstante
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa2.s_ae_valconstante OWNER TO sae;

--
-- Name: s_ae_valdato; Type: SEQUENCE; Schema: empresa2; Owner: sae
--

CREATE SEQUENCE empresa2.s_ae_valdato
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa2.s_ae_valdato OWNER TO sae;

--
-- Name: s_ae_validacion; Type: SEQUENCE; Schema: empresa2; Owner: sae
--

CREATE SEQUENCE empresa2.s_ae_validacion
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa2.s_ae_validacion OWNER TO sae;

--
-- Name: s_ae_valorposible; Type: SEQUENCE; Schema: empresa2; Owner: sae
--

CREATE SEQUENCE empresa2.s_ae_valorposible
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa2.s_ae_valorposible OWNER TO sae;

--
-- Name: s_ae_valrecurso; Type: SEQUENCE; Schema: empresa2; Owner: sae
--

CREATE SEQUENCE empresa2.s_ae_valrecurso
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa2.s_ae_valrecurso OWNER TO sae;

--
-- Name: ae_acciones; Type: TABLE; Schema: empresa3; Owner: sae
--

CREATE TABLE empresa3.ae_acciones (
    id integer NOT NULL,
    descripcion character varying(100) NOT NULL,
    fecha_baja timestamp without time zone,
    host character varying(100),
    nombre character varying(50) NOT NULL,
    servicio character varying(250) NOT NULL
);


ALTER TABLE empresa3.ae_acciones OWNER TO sae;

--
-- Name: ae_acciones_miperfil_recurso; Type: TABLE; Schema: empresa3; Owner: sae
--

CREATE TABLE empresa3.ae_acciones_miperfil_recurso (
    id integer NOT NULL,
    recurso_id integer NOT NULL,
    titulo_con_1 character varying(100),
    url_con_1 character varying(1024),
    destacada_con_1 boolean,
    titulo_con_2 character varying(100),
    url_con_2 character varying(1024),
    destacada_con_2 boolean,
    titulo_con_3 character varying(100),
    url_con_3 character varying(1024),
    destacada_con_3 boolean,
    titulo_con_4 character varying(100),
    url_con_4 character varying(1024),
    destacada_con_4 boolean,
    titulo_con_5 character varying(100),
    url_con_5 character varying(1024),
    destacada_con_5 boolean,
    titulo_can_1 character varying(100),
    url_can_1 character varying(1024),
    destacada_can_1 boolean,
    titulo_can_2 character varying(100),
    url_can_2 character varying(1024),
    destacada_can_2 boolean,
    titulo_can_3 character varying(100),
    url_can_3 character varying(1024),
    destacada_can_3 boolean,
    titulo_can_4 character varying(100),
    url_can_4 character varying(1024),
    destacada_can_4 boolean,
    titulo_can_5 character varying(100),
    url_can_5 character varying(1024),
    destacada_can_5 boolean,
    titulo_rec_1 character varying(100),
    url_rec_1 character varying(1024),
    destacada_rec_1 boolean,
    titulo_rec_2 character varying(100),
    url_rec_2 character varying(1024),
    destacada_rec_2 boolean,
    titulo_rec_3 character varying(100),
    url_rec_3 character varying(1024),
    destacada_rec_3 boolean,
    titulo_rec_4 character varying(100),
    url_rec_4 character varying(1024),
    destacada_rec_4 boolean,
    titulo_rec_5 character varying(100),
    url_rec_5 character varying(1024),
    destacada_rec_5 boolean
);


ALTER TABLE empresa3.ae_acciones_miperfil_recurso OWNER TO sae;

--
-- Name: ae_acciones_por_dato; Type: TABLE; Schema: empresa3; Owner: sae
--

CREATE TABLE empresa3.ae_acciones_por_dato (
    id integer NOT NULL,
    fecha_desasociacion timestamp without time zone,
    nombre_parametro character varying(50) NOT NULL,
    aear_id integer NOT NULL,
    aeds_id integer NOT NULL
);


ALTER TABLE empresa3.ae_acciones_por_dato OWNER TO sae;

--
-- Name: ae_acciones_por_recurso; Type: TABLE; Schema: empresa3; Owner: sae
--

CREATE TABLE empresa3.ae_acciones_por_recurso (
    id integer NOT NULL,
    evento character varying(1) NOT NULL,
    fecha_baja timestamp without time zone,
    orden_ejecucion integer,
    aeac_id integer NOT NULL,
    aere_id integer NOT NULL
);


ALTER TABLE empresa3.ae_acciones_por_recurso OWNER TO sae;

--
-- Name: ae_agendas; Type: TABLE; Schema: empresa3; Owner: sae
--

CREATE TABLE empresa3.ae_agendas (
    id integer NOT NULL,
    descripcion character varying(1000) NOT NULL,
    fecha_baja timestamp without time zone,
    nombre character varying(100) NOT NULL,
    tramite_id character varying(25),
    timezone character varying(100),
    idiomas character varying(100),
    con_cda boolean DEFAULT false,
    tramite_codigo character varying(10),
    publicar_novedades boolean DEFAULT false NOT NULL,
    con_trazabilidad boolean DEFAULT false NOT NULL
);


ALTER TABLE empresa3.ae_agendas OWNER TO sae;

--
-- Name: ae_agrupaciones_datos; Type: TABLE; Schema: empresa3; Owner: sae
--

CREATE TABLE empresa3.ae_agrupaciones_datos (
    id integer NOT NULL,
    fecha_baja timestamp without time zone,
    nombre character varying(50) NOT NULL,
    orden integer NOT NULL,
    aere_id integer NOT NULL,
    borrar_flag boolean NOT NULL,
    etiqueta character varying(50)
);


ALTER TABLE empresa3.ae_agrupaciones_datos OWNER TO sae;

--
-- Name: ae_anios; Type: TABLE; Schema: empresa3; Owner: sae
--

CREATE TABLE empresa3.ae_anios (
    id integer NOT NULL,
    anio integer NOT NULL,
    aepl_id integer NOT NULL
);


ALTER TABLE empresa3.ae_anios OWNER TO sae;

--
-- Name: ae_atencion; Type: TABLE; Schema: empresa3; Owner: sae
--

CREATE TABLE empresa3.ae_atencion (
    id integer NOT NULL,
    asistio boolean NOT NULL,
    duracion integer NOT NULL,
    fact timestamp without time zone NOT NULL,
    fcrea timestamp without time zone NOT NULL,
    funcionario character varying(255) NOT NULL,
    aers_id integer NOT NULL
);


ALTER TABLE empresa3.ae_atencion OWNER TO sae;

--
-- Name: ae_comunicaciones; Type: TABLE; Schema: empresa3; Owner: sae
--

CREATE TABLE empresa3.ae_comunicaciones (
    id integer NOT NULL,
    tipo_1 character varying(25) NOT NULL,
    tipo_2 character varying(25) NOT NULL,
    destino character varying(100) NOT NULL,
    recurso_id integer NOT NULL,
    reserva_id integer,
    token_id integer,
    procesado boolean DEFAULT false NOT NULL,
    mensaje character varying(4000)
);


ALTER TABLE empresa3.ae_comunicaciones OWNER TO sae;

--
-- Name: ae_constante_validacion; Type: TABLE; Schema: empresa3; Owner: sae
--

CREATE TABLE empresa3.ae_constante_validacion (
    id integer NOT NULL,
    fecha_baja timestamp without time zone,
    largo integer NOT NULL,
    nombre character varying(50) NOT NULL,
    tipo character varying(30) NOT NULL,
    aeva_id integer NOT NULL
);


ALTER TABLE empresa3.ae_constante_validacion OWNER TO sae;

--
-- Name: ae_datos_a_solicitar; Type: TABLE; Schema: empresa3; Owner: sae
--

CREATE TABLE empresa3.ae_datos_a_solicitar (
    id integer NOT NULL,
    ancho_despliegue integer NOT NULL,
    columna integer NOT NULL,
    es_clave boolean NOT NULL,
    etiqueta character varying(50) NOT NULL,
    fecha_baja timestamp without time zone,
    fila integer NOT NULL,
    incluir_en_llamador boolean NOT NULL,
    incluir_en_reporte boolean NOT NULL,
    largo integer,
    largo_en_llamador integer NOT NULL,
    nombre character varying(50) NOT NULL,
    orden_en_llamador integer NOT NULL,
    requerido boolean NOT NULL,
    texto_ayuda character varying(100),
    tipo character varying(30) NOT NULL,
    aead_id integer NOT NULL,
    aere_id integer NOT NULL,
    borrar_flag boolean DEFAULT true NOT NULL,
    solo_lectura boolean DEFAULT false NOT NULL,
    incluir_en_novedades boolean DEFAULT false NOT NULL
);


ALTER TABLE empresa3.ae_datos_a_solicitar OWNER TO sae;

--
-- Name: ae_datos_del_recurso; Type: TABLE; Schema: empresa3; Owner: sae
--

CREATE TABLE empresa3.ae_datos_del_recurso (
    id integer NOT NULL,
    etiqueta character varying(50) NOT NULL,
    orden integer NOT NULL,
    valor character varying(100) NOT NULL,
    aere_id integer NOT NULL
);


ALTER TABLE empresa3.ae_datos_del_recurso OWNER TO sae;

--
-- Name: ae_datos_reserva; Type: TABLE; Schema: empresa3; Owner: sae
--

CREATE TABLE empresa3.ae_datos_reserva (
    id integer NOT NULL,
    valor character varying(100) NOT NULL,
    aeds_id integer NOT NULL,
    aers_id integer NOT NULL
);


ALTER TABLE empresa3.ae_datos_reserva OWNER TO sae;

--
-- Name: ae_dias_del_mes; Type: TABLE; Schema: empresa3; Owner: sae
--

CREATE TABLE empresa3.ae_dias_del_mes (
    id integer NOT NULL,
    dia_del_mes integer NOT NULL,
    aepl_id integer NOT NULL
);


ALTER TABLE empresa3.ae_dias_del_mes OWNER TO sae;

--
-- Name: ae_dias_semana; Type: TABLE; Schema: empresa3; Owner: sae
--

CREATE TABLE empresa3.ae_dias_semana (
    id integer NOT NULL,
    dia_semana integer NOT NULL,
    aepl_id integer NOT NULL
);


ALTER TABLE empresa3.ae_dias_semana OWNER TO sae;

--
-- Name: ae_disponibilidades; Type: TABLE; Schema: empresa3; Owner: sae
--

CREATE TABLE empresa3.ae_disponibilidades (
    id integer NOT NULL,
    cupo integer NOT NULL,
    fecha date NOT NULL,
    fecha_baja timestamp without time zone,
    hora_fin timestamp without time zone NOT NULL,
    hora_inicio timestamp without time zone NOT NULL,
    numerador integer NOT NULL,
    version integer NOT NULL,
    aepl_id integer,
    aere_id integer NOT NULL,
    presencial boolean DEFAULT false NOT NULL
);


ALTER TABLE empresa3.ae_disponibilidades OWNER TO sae;

--
-- Name: ae_frases_captcha; Type: TABLE; Schema: empresa3; Owner: sae
--

CREATE TABLE empresa3.ae_frases_captcha (
    clave character varying(100) NOT NULL,
    frase character varying(1024),
    idioma character varying(5) NOT NULL
);


ALTER TABLE empresa3.ae_frases_captcha OWNER TO sae;

--
-- Name: ae_llamadas; Type: TABLE; Schema: empresa3; Owner: sae
--

CREATE TABLE empresa3.ae_llamadas (
    id integer NOT NULL,
    etiqueta character varying(100) NOT NULL,
    fecha date NOT NULL,
    hora timestamp without time zone NOT NULL,
    numero integer NOT NULL,
    puesto integer NOT NULL,
    aere_id integer NOT NULL,
    aers_id integer NOT NULL
);


ALTER TABLE empresa3.ae_llamadas OWNER TO sae;

--
-- Name: ae_meses; Type: TABLE; Schema: empresa3; Owner: sae
--

CREATE TABLE empresa3.ae_meses (
    id integer NOT NULL,
    mes integer NOT NULL,
    aepl_id integer NOT NULL
);


ALTER TABLE empresa3.ae_meses OWNER TO sae;

--
-- Name: ae_parametros_accion; Type: TABLE; Schema: empresa3; Owner: sae
--

CREATE TABLE empresa3.ae_parametros_accion (
    id integer NOT NULL,
    fecha_baja timestamp without time zone,
    largo integer,
    nombre character varying(50) NOT NULL,
    tipo character varying(30),
    aeac_id integer NOT NULL
);


ALTER TABLE empresa3.ae_parametros_accion OWNER TO sae;

--
-- Name: ae_parametros_autocompletar; Type: TABLE; Schema: empresa3; Owner: sae
--

CREATE TABLE empresa3.ae_parametros_autocompletar (
    id integer NOT NULL,
    fecha_baja timestamp without time zone,
    modo integer NOT NULL,
    nombre character varying(50) NOT NULL,
    tipo character varying(30) NOT NULL,
    aeserv_id integer NOT NULL
);


ALTER TABLE empresa3.ae_parametros_autocompletar OWNER TO sae;

--
-- Name: ae_parametros_validacion; Type: TABLE; Schema: empresa3; Owner: sae
--

CREATE TABLE empresa3.ae_parametros_validacion (
    id integer NOT NULL,
    fecha_baja timestamp without time zone,
    largo integer,
    nombre character varying(50) NOT NULL,
    tipo character varying(30),
    aeva_id integer NOT NULL
);


ALTER TABLE empresa3.ae_parametros_validacion OWNER TO sae;

--
-- Name: ae_plantillas; Type: TABLE; Schema: empresa3; Owner: sae
--

CREATE TABLE empresa3.ae_plantillas (
    id integer NOT NULL,
    cupo integer NOT NULL,
    fecha_baja timestamp without time zone,
    frecuencia integer NOT NULL,
    generacion_automatica boolean NOT NULL,
    hora_fin timestamp without time zone NOT NULL,
    hora_inicio timestamp without time zone NOT NULL,
    aere_id integer NOT NULL
);


ALTER TABLE empresa3.ae_plantillas OWNER TO sae;

--
-- Name: ae_preguntas_captcha; Type: TABLE; Schema: empresa3; Owner: sae
--

CREATE TABLE empresa3.ae_preguntas_captcha (
    clave character varying(100) NOT NULL,
    pregunta character varying(1024),
    respuesta character varying(25),
    idioma character varying(5) NOT NULL
);


ALTER TABLE empresa3.ae_preguntas_captcha OWNER TO sae;

--
-- Name: ae_recursos; Type: TABLE; Schema: empresa3; Owner: sae
--

CREATE TABLE empresa3.ae_recursos (
    id integer NOT NULL,
    cant_dias_a_generar integer NOT NULL,
    descripcion character varying(1000) NOT NULL,
    dias_inicio_ventana_internet integer NOT NULL,
    dias_inicio_ventana_intranet integer NOT NULL,
    dias_ventana_internet integer NOT NULL,
    dias_ventana_intranet integer NOT NULL,
    fecha_baja timestamp without time zone,
    fecha_fin timestamp without time zone,
    fecha_fin_disp timestamp without time zone,
    fecha_inicio timestamp without time zone NOT NULL,
    fecha_inicio_disp timestamp without time zone NOT NULL,
    largo_lista_espera integer,
    mostrar_numero_en_llamador boolean NOT NULL,
    mostrar_numero_en_ticket boolean NOT NULL,
    nombre character varying(100) NOT NULL,
    sabado_es_habil boolean NOT NULL,
    serie character varying(3),
    usar_llamador boolean NOT NULL,
    ventana_cupos_minimos integer NOT NULL,
    version integer NOT NULL,
    visible_internet boolean NOT NULL,
    aeag_id integer NOT NULL,
    oficina_id character varying(25),
    direccion character varying(100),
    localidad character varying(100),
    departamento character varying(100),
    telefonos character varying(100),
    horarios character varying(100),
    latitud numeric,
    longitud numeric,
    mostrar_id_en_ticket boolean,
    domingo_es_habil boolean DEFAULT false NOT NULL,
    presencial_admite boolean DEFAULT false NOT NULL,
    presencial_lunes boolean DEFAULT false NOT NULL,
    presencial_martes boolean DEFAULT false NOT NULL,
    presencial_miercoles boolean DEFAULT false NOT NULL,
    presencial_jueves boolean DEFAULT false NOT NULL,
    presencial_viernes boolean DEFAULT false NOT NULL,
    presencial_sabado boolean DEFAULT false NOT NULL,
    presencial_domingo boolean DEFAULT false NOT NULL,
    presencial_cupos integer DEFAULT 0 NOT NULL,
    tamanio_fuente_grande integer DEFAULT 12 NOT NULL,
    tamanio_fuente_normal integer DEFAULT 10 NOT NULL,
    tamanio_fuente_chica integer DEFAULT 8 NOT NULL,
    fuente_ticket character varying(100) DEFAULT 'Helvetica-Bold'::character varying NOT NULL,
    multiple_admite boolean DEFAULT false NOT NULL,
    cambios_admite boolean DEFAULT false NOT NULL,
    cambios_tiempo integer,
    cambios_unidad integer,
    periodo_validacion integer DEFAULT 0 NOT NULL,
    validar_por_ip boolean DEFAULT false NOT NULL,
    cantidad_por_ip integer,
    periodo_por_ip integer,
    ips_sin_validacion character varying(4000) DEFAULT NULL::character varying,
    cancela_tiempo integer DEFAULT 0 NOT NULL,
    cancela_unidad integer DEFAULT 12 NOT NULL,
    cancela_tipo character varying(1) DEFAULT 'I'::character varying NOT NULL,
    mi_perfil_con_hab boolean DEFAULT true NOT NULL,
    mi_perfil_can_hab boolean DEFAULT true NOT NULL,
    mi_perfil_rec_hab boolean DEFAULT true NOT NULL,
    mi_perfil_con_tit character varying(255) DEFAULT NULL::character varying,
    mi_perfil_con_cor character varying(500) DEFAULT NULL::character varying,
    mi_perfil_con_lar character varying(1024) DEFAULT NULL::character varying,
    mi_perfil_con_ven integer,
    mi_perfil_can_tit character varying(255) DEFAULT NULL::character varying,
    mi_perfil_can_cor character varying(500) DEFAULT NULL::character varying,
    mi_perfil_can_lar character varying(1024) DEFAULT NULL::character varying,
    mi_perfil_can_ven integer,
    mi_perfil_rec_tit character varying(255) DEFAULT NULL::character varying,
    mi_perfil_rec_cor character varying(500) DEFAULT NULL::character varying,
    mi_perfil_rec_lar character varying(1024) DEFAULT NULL::character varying,
    mi_perfil_rec_ven integer,
    mi_perfil_rec_hora integer,
    mi_perfil_rec_dias integer
);


ALTER TABLE empresa3.ae_recursos OWNER TO sae;

--
-- Name: ae_recursos_aud; Type: TABLE; Schema: empresa3; Owner: sae
--

CREATE TABLE empresa3.ae_recursos_aud (
    id integer NOT NULL,
    id_recurso integer NOT NULL,
    nombre character varying(100) NOT NULL,
    descripcion character varying(1000) NOT NULL,
    fecha_inicio timestamp without time zone NOT NULL,
    fecha_fin timestamp without time zone,
    fecha_inicio_disp timestamp without time zone NOT NULL,
    fecha_fin_disp timestamp without time zone,
    dias_inicio_ventana_intranet integer NOT NULL,
    dias_ventana_intranet integer NOT NULL,
    dias_inicio_ventana_internet integer NOT NULL,
    dias_ventana_internet integer NOT NULL,
    ventana_cupos_minimos integer NOT NULL,
    cant_dias_a_generar integer NOT NULL,
    largo_lista_espera integer,
    fecha_baja timestamp without time zone,
    mostrar_numero_en_llamador boolean NOT NULL,
    visible_internet boolean NOT NULL,
    usar_llamador boolean NOT NULL,
    serie character varying(3),
    sabado_es_habil boolean NOT NULL,
    domingo_es_habil boolean DEFAULT false NOT NULL,
    mostrar_numero_en_ticket boolean NOT NULL,
    mostrar_id_en_ticket boolean,
    fuente_ticket character varying(100) DEFAULT 'Helvetica-Bold'::character varying NOT NULL,
    tamanio_fuente_grande integer DEFAULT 12 NOT NULL,
    tamanio_fuente_normal integer DEFAULT 10 NOT NULL,
    tamanio_fuente_chica integer DEFAULT 8 NOT NULL,
    oficina_id character varying(25),
    direccion character varying(100),
    localidad character varying(100),
    departamento character varying(100),
    telefonos character varying(100),
    horarios character varying(100),
    latitud numeric,
    longitud numeric,
    agenda integer NOT NULL,
    presencial_admite boolean DEFAULT false NOT NULL,
    presencial_cupos integer DEFAULT 0 NOT NULL,
    presencial_lunes boolean DEFAULT false NOT NULL,
    presencial_martes boolean DEFAULT false NOT NULL,
    presencial_miercoles boolean DEFAULT false NOT NULL,
    presencial_jueves boolean DEFAULT false NOT NULL,
    presencial_viernes boolean DEFAULT false NOT NULL,
    presencial_sabado boolean DEFAULT false NOT NULL,
    presencial_domingo boolean DEFAULT false NOT NULL,
    multiple_admite boolean DEFAULT false NOT NULL,
    cambios_admite boolean DEFAULT false NOT NULL,
    cambios_tiempo integer,
    cambios_unidad integer,
    periodo_validacion integer DEFAULT 0 NOT NULL,
    validar_por_ip boolean DEFAULT false NOT NULL,
    cantidad_por_ip integer,
    periodo_por_ip integer,
    ips_sin_validacion character varying(4000) DEFAULT NULL::character varying,
    cancela_tiempo integer DEFAULT 0 NOT NULL,
    cancela_unidad integer DEFAULT 12 NOT NULL,
    cancela_tipo character varying(1) DEFAULT 'I'::character varying NOT NULL,
    mi_perfil_con_hab boolean DEFAULT true NOT NULL,
    mi_perfil_con_tit character varying(200) DEFAULT NULL::character varying,
    mi_perfil_con_cor character varying(500) DEFAULT NULL::character varying,
    mi_perfil_con_lar character varying(3200) DEFAULT NULL::character varying,
    mi_perfil_con_ven integer,
    mi_perfil_can_hab boolean DEFAULT true NOT NULL,
    mi_perfil_can_tit character varying(200) DEFAULT NULL::character varying,
    mi_perfil_can_cor character varying(500) DEFAULT NULL::character varying,
    mi_perfil_can_lar character varying(3200) DEFAULT NULL::character varying,
    mi_perfil_can_ven integer,
    mi_perfil_rec_hab boolean DEFAULT true NOT NULL,
    mi_perfil_rec_tit character varying(200) DEFAULT NULL::character varying,
    mi_perfil_rec_cor character varying(500) DEFAULT NULL::character varying,
    mi_perfil_rec_lar character varying(3200) DEFAULT NULL::character varying,
    mi_perfil_rec_ven integer,
    mi_perfil_rec_hora integer,
    mi_perfil_rec_dias integer,
    reserva_pen_tiempo_max integer,
    reserva_pend_tiempo_max integer,
    fecha_modificacion timestamp without time zone,
    usuario character varying(45),
    version integer NOT NULL,
    tipo_operacion smallint
);


ALTER TABLE empresa3.ae_recursos_aud OWNER TO sae;

--
-- Name: ae_reservas; Type: TABLE; Schema: empresa3; Owner: sae
--

CREATE TABLE empresa3.ae_reservas (
    id integer NOT NULL,
    estado character varying(1) NOT NULL,
    fact timestamp without time zone NOT NULL,
    fcrea timestamp without time zone NOT NULL,
    numero integer,
    observaciones character varying(100),
    origen character varying(1),
    ucancela character varying(255),
    ucrea character varying(255),
    version integer NOT NULL,
    codigo_seguridad character varying(10) DEFAULT '00000'::character varying,
    trazabilidad_guid character varying(25),
    tramite_codigo character varying(10),
    tramite_nombre character varying(100),
    serie character varying(3),
    tcancela character varying(1),
    fcancela timestamp without time zone,
    aetr_id integer,
    ip_origen character varying(16) DEFAULT NULL::character varying,
    flibera timestamp without time zone,
    mi_perfil_notif boolean DEFAULT true NOT NULL,
    notificar boolean DEFAULT true,
    reserva_hija_id integer
);


ALTER TABLE empresa3.ae_reservas OWNER TO sae;

--
-- Name: ae_reservas_disponibilidades; Type: TABLE; Schema: empresa3; Owner: sae
--

CREATE TABLE empresa3.ae_reservas_disponibilidades (
    aers_id integer NOT NULL,
    aedi_id integer NOT NULL
);


ALTER TABLE empresa3.ae_reservas_disponibilidades OWNER TO sae;

--
-- Name: ae_roles_usuario_recurso; Type: TABLE; Schema: empresa3; Owner: sae
--

CREATE TABLE empresa3.ae_roles_usuario_recurso (
    usuario_id integer NOT NULL,
    recurso_id integer NOT NULL,
    roles character varying(4000)
);


ALTER TABLE empresa3.ae_roles_usuario_recurso OWNER TO sae;

--
-- Name: ae_serv_autocomp_por_dato; Type: TABLE; Schema: empresa3; Owner: sae
--

CREATE TABLE empresa3.ae_serv_autocomp_por_dato (
    id integer NOT NULL,
    fecha_desasociacion timestamp without time zone,
    nombre_parametro character varying(50) NOT NULL,
    aeds_id integer NOT NULL,
    aesr_id integer NOT NULL
);


ALTER TABLE empresa3.ae_serv_autocomp_por_dato OWNER TO sae;

--
-- Name: ae_serv_autocompletar; Type: TABLE; Schema: empresa3; Owner: sae
--

CREATE TABLE empresa3.ae_serv_autocompletar (
    id integer NOT NULL,
    descripcion character varying(100) NOT NULL,
    fecha_baja timestamp without time zone,
    host character varying(100),
    nombre character varying(50) NOT NULL,
    servicio character varying(250) NOT NULL
);


ALTER TABLE empresa3.ae_serv_autocompletar OWNER TO sae;

--
-- Name: ae_servicio_por_recurso; Type: TABLE; Schema: empresa3; Owner: sae
--

CREATE TABLE empresa3.ae_servicio_por_recurso (
    id integer NOT NULL,
    fecha_baja timestamp without time zone,
    aeserv_id integer NOT NULL,
    aere_id integer NOT NULL
);


ALTER TABLE empresa3.ae_servicio_por_recurso OWNER TO sae;

--
-- Name: ae_textos; Type: TABLE; Schema: empresa3; Owner: sae
--

CREATE TABLE empresa3.ae_textos (
    codigo character varying(100) NOT NULL,
    idioma character varying(5) NOT NULL,
    texto character varying(4096) NOT NULL
);


ALTER TABLE empresa3.ae_textos OWNER TO sae;

--
-- Name: ae_textos_agenda; Type: TABLE; Schema: empresa3; Owner: sae
--

CREATE TABLE empresa3.ae_textos_agenda (
    id integer NOT NULL,
    texto_paso1 character varying(1000),
    texto_paso2 character varying(1000),
    texto_paso3 character varying(1000),
    texto_sel_recurso character varying(50),
    texto_ticket character varying(1000),
    aeag_id integer NOT NULL,
    texto_correo_conf character varying(1000),
    texto_correo_canc character varying(1000),
    por_defecto boolean DEFAULT false NOT NULL,
    idioma character varying(5) NOT NULL
);


ALTER TABLE empresa3.ae_textos_agenda OWNER TO sae;

--
-- Name: ae_textos_recurso; Type: TABLE; Schema: empresa3; Owner: sae
--

CREATE TABLE empresa3.ae_textos_recurso (
    id integer NOT NULL,
    texto_paso2 character varying(1000),
    texto_paso3 character varying(1000),
    ticket_etiqueta_dos character varying(15),
    ticket_etiqueta_uno character varying(15),
    titulo_ciudadano_en_llamador character varying(255),
    titulo_puesto_en_llamador character varying(255),
    valor_etiqueta_dos character varying(30),
    valor_etiqueta_uno character varying(30),
    aere_id integer NOT NULL,
    idioma character varying(5) NOT NULL
);


ALTER TABLE empresa3.ae_textos_recurso OWNER TO sae;

--
-- Name: ae_tokens_reservas; Type: TABLE; Schema: empresa3; Owner: sae
--

CREATE TABLE empresa3.ae_tokens_reservas (
    id integer NOT NULL,
    token character varying(50) NOT NULL,
    aere_id integer,
    fecha_inicio timestamp without time zone NOT NULL,
    ultima_reserva timestamp without time zone,
    estado character varying(1) NOT NULL,
    cedula character varying(50) NOT NULL,
    nombre character varying(100) NOT NULL,
    correoe character varying(100) NOT NULL,
    tramite character varying(10),
    notas character varying(4000),
    version integer NOT NULL,
    ip_origen character varying(16) DEFAULT NULL::character varying
);


ALTER TABLE empresa3.ae_tokens_reservas OWNER TO sae;

--
-- Name: ae_tramites_agendas; Type: TABLE; Schema: empresa3; Owner: sae
--

CREATE TABLE empresa3.ae_tramites_agendas (
    id integer NOT NULL,
    agenda_id integer NOT NULL,
    tramite_id character varying(25),
    tramite_codigo character varying(10),
    tramite_nombre character varying(100)
);


ALTER TABLE empresa3.ae_tramites_agendas OWNER TO sae;

--
-- Name: ae_validaciones; Type: TABLE; Schema: empresa3; Owner: sae
--

CREATE TABLE empresa3.ae_validaciones (
    id integer NOT NULL,
    descripcion character varying(100) NOT NULL,
    fecha_baja timestamp without time zone,
    host character varying(100),
    nombre character varying(50) NOT NULL,
    servicio character varying(250) NOT NULL
);


ALTER TABLE empresa3.ae_validaciones OWNER TO sae;

--
-- Name: ae_validaciones_por_dato; Type: TABLE; Schema: empresa3; Owner: sae
--

CREATE TABLE empresa3.ae_validaciones_por_dato (
    id integer NOT NULL,
    fecha_desasociacion timestamp without time zone,
    nombre_parametro character varying(50) NOT NULL,
    aeds_id integer NOT NULL,
    aevr_id integer NOT NULL
);


ALTER TABLE empresa3.ae_validaciones_por_dato OWNER TO sae;

--
-- Name: ae_validaciones_por_recurso; Type: TABLE; Schema: empresa3; Owner: sae
--

CREATE TABLE empresa3.ae_validaciones_por_recurso (
    id integer NOT NULL,
    fecha_baja timestamp without time zone,
    orden_ejecucion integer,
    aere_id integer NOT NULL,
    aeva_id integer NOT NULL
);


ALTER TABLE empresa3.ae_validaciones_por_recurso OWNER TO sae;

--
-- Name: ae_valor_constante_val_rec; Type: TABLE; Schema: empresa3; Owner: sae
--

CREATE TABLE empresa3.ae_valor_constante_val_rec (
    id integer NOT NULL,
    fecha_desasociacion timestamp without time zone,
    nombre_constante character varying(50) NOT NULL,
    valor character varying(100) NOT NULL,
    aevr_id integer NOT NULL
);


ALTER TABLE empresa3.ae_valor_constante_val_rec OWNER TO sae;

--
-- Name: ae_valores_del_dato; Type: TABLE; Schema: empresa3; Owner: sae
--

CREATE TABLE empresa3.ae_valores_del_dato (
    id integer NOT NULL,
    etiqueta character varying(50) NOT NULL,
    fecha_desde timestamp without time zone NOT NULL,
    fecha_hasta timestamp without time zone,
    orden integer NOT NULL,
    valor character varying(50) NOT NULL,
    aeds_id integer NOT NULL,
    borrar_flag boolean DEFAULT true NOT NULL
);


ALTER TABLE empresa3.ae_valores_del_dato OWNER TO sae;

--
-- Name: s_ae_accion; Type: SEQUENCE; Schema: empresa3; Owner: sae
--

CREATE SEQUENCE empresa3.s_ae_accion
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa3.s_ae_accion OWNER TO sae;

--
-- Name: s_ae_acciondato; Type: SEQUENCE; Schema: empresa3; Owner: sae
--

CREATE SEQUENCE empresa3.s_ae_acciondato
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa3.s_ae_acciondato OWNER TO sae;

--
-- Name: s_ae_acciones_miperfil; Type: SEQUENCE; Schema: empresa3; Owner: sae
--

CREATE SEQUENCE empresa3.s_ae_acciones_miperfil
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa3.s_ae_acciones_miperfil OWNER TO sae;

--
-- Name: s_ae_accionrecurso; Type: SEQUENCE; Schema: empresa3; Owner: sae
--

CREATE SEQUENCE empresa3.s_ae_accionrecurso
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa3.s_ae_accionrecurso OWNER TO sae;

--
-- Name: s_ae_agenda; Type: SEQUENCE; Schema: empresa3; Owner: sae
--

CREATE SEQUENCE empresa3.s_ae_agenda
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa3.s_ae_agenda OWNER TO sae;

--
-- Name: s_ae_agrupacion_dato; Type: SEQUENCE; Schema: empresa3; Owner: sae
--

CREATE SEQUENCE empresa3.s_ae_agrupacion_dato
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa3.s_ae_agrupacion_dato OWNER TO sae;

--
-- Name: s_ae_anio; Type: SEQUENCE; Schema: empresa3; Owner: sae
--

CREATE SEQUENCE empresa3.s_ae_anio
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa3.s_ae_anio OWNER TO sae;

--
-- Name: s_ae_atencion; Type: SEQUENCE; Schema: empresa3; Owner: sae
--

CREATE SEQUENCE empresa3.s_ae_atencion
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa3.s_ae_atencion OWNER TO sae;

--
-- Name: s_ae_comunicaciones; Type: SEQUENCE; Schema: empresa3; Owner: sae
--

CREATE SEQUENCE empresa3.s_ae_comunicaciones
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa3.s_ae_comunicaciones OWNER TO sae;

--
-- Name: s_ae_constval; Type: SEQUENCE; Schema: empresa3; Owner: sae
--

CREATE SEQUENCE empresa3.s_ae_constval
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa3.s_ae_constval OWNER TO sae;

--
-- Name: s_ae_datoasolicitar; Type: SEQUENCE; Schema: empresa3; Owner: sae
--

CREATE SEQUENCE empresa3.s_ae_datoasolicitar
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa3.s_ae_datoasolicitar OWNER TO sae;

--
-- Name: s_ae_datodelrecurso; Type: SEQUENCE; Schema: empresa3; Owner: sae
--

CREATE SEQUENCE empresa3.s_ae_datodelrecurso
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa3.s_ae_datodelrecurso OWNER TO sae;

--
-- Name: s_ae_datoreserva; Type: SEQUENCE; Schema: empresa3; Owner: sae
--

CREATE SEQUENCE empresa3.s_ae_datoreserva
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa3.s_ae_datoreserva OWNER TO sae;

--
-- Name: s_ae_dia_mes; Type: SEQUENCE; Schema: empresa3; Owner: sae
--

CREATE SEQUENCE empresa3.s_ae_dia_mes
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa3.s_ae_dia_mes OWNER TO sae;

--
-- Name: s_ae_dia_semana; Type: SEQUENCE; Schema: empresa3; Owner: sae
--

CREATE SEQUENCE empresa3.s_ae_dia_semana
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa3.s_ae_dia_semana OWNER TO sae;

--
-- Name: s_ae_disponibilidad; Type: SEQUENCE; Schema: empresa3; Owner: sae
--

CREATE SEQUENCE empresa3.s_ae_disponibilidad
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa3.s_ae_disponibilidad OWNER TO sae;

--
-- Name: s_ae_llamada; Type: SEQUENCE; Schema: empresa3; Owner: sae
--

CREATE SEQUENCE empresa3.s_ae_llamada
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa3.s_ae_llamada OWNER TO sae;

--
-- Name: s_ae_mes; Type: SEQUENCE; Schema: empresa3; Owner: sae
--

CREATE SEQUENCE empresa3.s_ae_mes
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa3.s_ae_mes OWNER TO sae;

--
-- Name: s_ae_paramaccion; Type: SEQUENCE; Schema: empresa3; Owner: sae
--

CREATE SEQUENCE empresa3.s_ae_paramaccion
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa3.s_ae_paramaccion OWNER TO sae;

--
-- Name: s_ae_parametros_autocompletar; Type: SEQUENCE; Schema: empresa3; Owner: sae
--

CREATE SEQUENCE empresa3.s_ae_parametros_autocompletar
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa3.s_ae_parametros_autocompletar OWNER TO sae;

--
-- Name: s_ae_paramval; Type: SEQUENCE; Schema: empresa3; Owner: sae
--

CREATE SEQUENCE empresa3.s_ae_paramval
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa3.s_ae_paramval OWNER TO sae;

--
-- Name: s_ae_plantilla; Type: SEQUENCE; Schema: empresa3; Owner: sae
--

CREATE SEQUENCE empresa3.s_ae_plantilla
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa3.s_ae_plantilla OWNER TO sae;

--
-- Name: s_ae_recurso; Type: SEQUENCE; Schema: empresa3; Owner: sae
--

CREATE SEQUENCE empresa3.s_ae_recurso
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa3.s_ae_recurso OWNER TO sae;

--
-- Name: s_ae_recurso_aud; Type: SEQUENCE; Schema: empresa3; Owner: sae
--

CREATE SEQUENCE empresa3.s_ae_recurso_aud
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa3.s_ae_recurso_aud OWNER TO sae;

--
-- Name: s_ae_reserva; Type: SEQUENCE; Schema: empresa3; Owner: sae
--

CREATE SEQUENCE empresa3.s_ae_reserva
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa3.s_ae_reserva OWNER TO sae;

--
-- Name: s_ae_serv_autocompletar; Type: SEQUENCE; Schema: empresa3; Owner: sae
--

CREATE SEQUENCE empresa3.s_ae_serv_autocompletar
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa3.s_ae_serv_autocompletar OWNER TO sae;

--
-- Name: s_ae_servdato; Type: SEQUENCE; Schema: empresa3; Owner: sae
--

CREATE SEQUENCE empresa3.s_ae_servdato
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa3.s_ae_servdato OWNER TO sae;

--
-- Name: s_ae_servrecurso; Type: SEQUENCE; Schema: empresa3; Owner: sae
--

CREATE SEQUENCE empresa3.s_ae_servrecurso
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa3.s_ae_servrecurso OWNER TO sae;

--
-- Name: s_ae_texto_agenda; Type: SEQUENCE; Schema: empresa3; Owner: sae
--

CREATE SEQUENCE empresa3.s_ae_texto_agenda
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa3.s_ae_texto_agenda OWNER TO sae;

--
-- Name: s_ae_textorecurso; Type: SEQUENCE; Schema: empresa3; Owner: sae
--

CREATE SEQUENCE empresa3.s_ae_textorecurso
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa3.s_ae_textorecurso OWNER TO sae;

--
-- Name: s_ae_token_reserva; Type: SEQUENCE; Schema: empresa3; Owner: sae
--

CREATE SEQUENCE empresa3.s_ae_token_reserva
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa3.s_ae_token_reserva OWNER TO sae;

--
-- Name: s_ae_tramites_agendas; Type: SEQUENCE; Schema: empresa3; Owner: sae
--

CREATE SEQUENCE empresa3.s_ae_tramites_agendas
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa3.s_ae_tramites_agendas OWNER TO sae;

--
-- Name: s_ae_valconstante; Type: SEQUENCE; Schema: empresa3; Owner: sae
--

CREATE SEQUENCE empresa3.s_ae_valconstante
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa3.s_ae_valconstante OWNER TO sae;

--
-- Name: s_ae_valdato; Type: SEQUENCE; Schema: empresa3; Owner: sae
--

CREATE SEQUENCE empresa3.s_ae_valdato
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa3.s_ae_valdato OWNER TO sae;

--
-- Name: s_ae_validacion; Type: SEQUENCE; Schema: empresa3; Owner: sae
--

CREATE SEQUENCE empresa3.s_ae_validacion
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa3.s_ae_validacion OWNER TO sae;

--
-- Name: s_ae_valorposible; Type: SEQUENCE; Schema: empresa3; Owner: sae
--

CREATE SEQUENCE empresa3.s_ae_valorposible
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa3.s_ae_valorposible OWNER TO sae;

--
-- Name: s_ae_valrecurso; Type: SEQUENCE; Schema: empresa3; Owner: sae
--

CREATE SEQUENCE empresa3.s_ae_valrecurso
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa3.s_ae_valrecurso OWNER TO sae;

--
-- Name: ae_acciones; Type: TABLE; Schema: empresa4; Owner: sae
--

CREATE TABLE empresa4.ae_acciones (
    id integer NOT NULL,
    descripcion character varying(100) NOT NULL,
    fecha_baja timestamp without time zone,
    host character varying(100),
    nombre character varying(50) NOT NULL,
    servicio character varying(250) NOT NULL
);


ALTER TABLE empresa4.ae_acciones OWNER TO sae;

--
-- Name: ae_acciones_miperfil_recurso; Type: TABLE; Schema: empresa4; Owner: sae
--

CREATE TABLE empresa4.ae_acciones_miperfil_recurso (
    id integer NOT NULL,
    recurso_id integer NOT NULL,
    titulo_con_1 character varying(100),
    url_con_1 character varying(1024),
    destacada_con_1 boolean,
    titulo_con_2 character varying(100),
    url_con_2 character varying(1024),
    destacada_con_2 boolean,
    titulo_con_3 character varying(100),
    url_con_3 character varying(1024),
    destacada_con_3 boolean,
    titulo_con_4 character varying(100),
    url_con_4 character varying(1024),
    destacada_con_4 boolean,
    titulo_con_5 character varying(100),
    url_con_5 character varying(1024),
    destacada_con_5 boolean,
    titulo_can_1 character varying(100),
    url_can_1 character varying(1024),
    destacada_can_1 boolean,
    titulo_can_2 character varying(100),
    url_can_2 character varying(1024),
    destacada_can_2 boolean,
    titulo_can_3 character varying(100),
    url_can_3 character varying(1024),
    destacada_can_3 boolean,
    titulo_can_4 character varying(100),
    url_can_4 character varying(1024),
    destacada_can_4 boolean,
    titulo_can_5 character varying(100),
    url_can_5 character varying(1024),
    destacada_can_5 boolean,
    titulo_rec_1 character varying(100),
    url_rec_1 character varying(1024),
    destacada_rec_1 boolean,
    titulo_rec_2 character varying(100),
    url_rec_2 character varying(1024),
    destacada_rec_2 boolean,
    titulo_rec_3 character varying(100),
    url_rec_3 character varying(1024),
    destacada_rec_3 boolean,
    titulo_rec_4 character varying(100),
    url_rec_4 character varying(1024),
    destacada_rec_4 boolean,
    titulo_rec_5 character varying(100),
    url_rec_5 character varying(1024),
    destacada_rec_5 boolean
);


ALTER TABLE empresa4.ae_acciones_miperfil_recurso OWNER TO sae;

--
-- Name: ae_acciones_por_dato; Type: TABLE; Schema: empresa4; Owner: sae
--

CREATE TABLE empresa4.ae_acciones_por_dato (
    id integer NOT NULL,
    fecha_desasociacion timestamp without time zone,
    nombre_parametro character varying(50) NOT NULL,
    aear_id integer NOT NULL,
    aeds_id integer NOT NULL
);


ALTER TABLE empresa4.ae_acciones_por_dato OWNER TO sae;

--
-- Name: ae_acciones_por_recurso; Type: TABLE; Schema: empresa4; Owner: sae
--

CREATE TABLE empresa4.ae_acciones_por_recurso (
    id integer NOT NULL,
    evento character varying(1) NOT NULL,
    fecha_baja timestamp without time zone,
    orden_ejecucion integer,
    aeac_id integer NOT NULL,
    aere_id integer NOT NULL
);


ALTER TABLE empresa4.ae_acciones_por_recurso OWNER TO sae;

--
-- Name: ae_agendas; Type: TABLE; Schema: empresa4; Owner: sae
--

CREATE TABLE empresa4.ae_agendas (
    id integer NOT NULL,
    descripcion character varying(1000) NOT NULL,
    fecha_baja timestamp without time zone,
    nombre character varying(100) NOT NULL,
    tramite_id character varying(25),
    timezone character varying(100),
    idiomas character varying(100),
    con_cda boolean DEFAULT false,
    tramite_codigo character varying(10),
    publicar_novedades boolean DEFAULT false NOT NULL,
    con_trazabilidad boolean DEFAULT false NOT NULL
);


ALTER TABLE empresa4.ae_agendas OWNER TO sae;

--
-- Name: ae_agrupaciones_datos; Type: TABLE; Schema: empresa4; Owner: sae
--

CREATE TABLE empresa4.ae_agrupaciones_datos (
    id integer NOT NULL,
    fecha_baja timestamp without time zone,
    nombre character varying(50) NOT NULL,
    orden integer NOT NULL,
    aere_id integer NOT NULL,
    borrar_flag boolean NOT NULL,
    etiqueta character varying(50)
);


ALTER TABLE empresa4.ae_agrupaciones_datos OWNER TO sae;

--
-- Name: ae_anios; Type: TABLE; Schema: empresa4; Owner: sae
--

CREATE TABLE empresa4.ae_anios (
    id integer NOT NULL,
    anio integer NOT NULL,
    aepl_id integer NOT NULL
);


ALTER TABLE empresa4.ae_anios OWNER TO sae;

--
-- Name: ae_atencion; Type: TABLE; Schema: empresa4; Owner: sae
--

CREATE TABLE empresa4.ae_atencion (
    id integer NOT NULL,
    asistio boolean NOT NULL,
    duracion integer NOT NULL,
    fact timestamp without time zone NOT NULL,
    fcrea timestamp without time zone NOT NULL,
    funcionario character varying(255) NOT NULL,
    aers_id integer NOT NULL
);


ALTER TABLE empresa4.ae_atencion OWNER TO sae;

--
-- Name: ae_comunicaciones; Type: TABLE; Schema: empresa4; Owner: sae
--

CREATE TABLE empresa4.ae_comunicaciones (
    id integer NOT NULL,
    tipo_1 character varying(25) NOT NULL,
    tipo_2 character varying(25) NOT NULL,
    destino character varying(100) NOT NULL,
    recurso_id integer NOT NULL,
    reserva_id integer,
    token_id integer,
    procesado boolean DEFAULT false NOT NULL,
    mensaje character varying(4000)
);


ALTER TABLE empresa4.ae_comunicaciones OWNER TO sae;

--
-- Name: ae_constante_validacion; Type: TABLE; Schema: empresa4; Owner: sae
--

CREATE TABLE empresa4.ae_constante_validacion (
    id integer NOT NULL,
    fecha_baja timestamp without time zone,
    largo integer NOT NULL,
    nombre character varying(50) NOT NULL,
    tipo character varying(30) NOT NULL,
    aeva_id integer NOT NULL
);


ALTER TABLE empresa4.ae_constante_validacion OWNER TO sae;

--
-- Name: ae_datos_a_solicitar; Type: TABLE; Schema: empresa4; Owner: sae
--

CREATE TABLE empresa4.ae_datos_a_solicitar (
    id integer NOT NULL,
    ancho_despliegue integer NOT NULL,
    columna integer NOT NULL,
    es_clave boolean NOT NULL,
    etiqueta character varying(50) NOT NULL,
    fecha_baja timestamp without time zone,
    fila integer NOT NULL,
    incluir_en_llamador boolean NOT NULL,
    incluir_en_reporte boolean NOT NULL,
    largo integer,
    largo_en_llamador integer NOT NULL,
    nombre character varying(50) NOT NULL,
    orden_en_llamador integer NOT NULL,
    requerido boolean NOT NULL,
    texto_ayuda character varying(100),
    tipo character varying(30) NOT NULL,
    aead_id integer NOT NULL,
    aere_id integer NOT NULL,
    borrar_flag boolean DEFAULT true NOT NULL,
    solo_lectura boolean DEFAULT false NOT NULL,
    incluir_en_novedades boolean DEFAULT false NOT NULL
);


ALTER TABLE empresa4.ae_datos_a_solicitar OWNER TO sae;

--
-- Name: ae_datos_del_recurso; Type: TABLE; Schema: empresa4; Owner: sae
--

CREATE TABLE empresa4.ae_datos_del_recurso (
    id integer NOT NULL,
    etiqueta character varying(50) NOT NULL,
    orden integer NOT NULL,
    valor character varying(100) NOT NULL,
    aere_id integer NOT NULL
);


ALTER TABLE empresa4.ae_datos_del_recurso OWNER TO sae;

--
-- Name: ae_datos_reserva; Type: TABLE; Schema: empresa4; Owner: sae
--

CREATE TABLE empresa4.ae_datos_reserva (
    id integer NOT NULL,
    valor character varying(100) NOT NULL,
    aeds_id integer NOT NULL,
    aers_id integer NOT NULL
);


ALTER TABLE empresa4.ae_datos_reserva OWNER TO sae;

--
-- Name: ae_dias_del_mes; Type: TABLE; Schema: empresa4; Owner: sae
--

CREATE TABLE empresa4.ae_dias_del_mes (
    id integer NOT NULL,
    dia_del_mes integer NOT NULL,
    aepl_id integer NOT NULL
);


ALTER TABLE empresa4.ae_dias_del_mes OWNER TO sae;

--
-- Name: ae_dias_semana; Type: TABLE; Schema: empresa4; Owner: sae
--

CREATE TABLE empresa4.ae_dias_semana (
    id integer NOT NULL,
    dia_semana integer NOT NULL,
    aepl_id integer NOT NULL
);


ALTER TABLE empresa4.ae_dias_semana OWNER TO sae;

--
-- Name: ae_disponibilidades; Type: TABLE; Schema: empresa4; Owner: sae
--

CREATE TABLE empresa4.ae_disponibilidades (
    id integer NOT NULL,
    cupo integer NOT NULL,
    fecha date NOT NULL,
    fecha_baja timestamp without time zone,
    hora_fin timestamp without time zone NOT NULL,
    hora_inicio timestamp without time zone NOT NULL,
    numerador integer NOT NULL,
    version integer NOT NULL,
    aepl_id integer,
    aere_id integer NOT NULL,
    presencial boolean DEFAULT false NOT NULL
);


ALTER TABLE empresa4.ae_disponibilidades OWNER TO sae;

--
-- Name: ae_frases_captcha; Type: TABLE; Schema: empresa4; Owner: sae
--

CREATE TABLE empresa4.ae_frases_captcha (
    clave character varying(100) NOT NULL,
    frase character varying(1024),
    idioma character varying(5) NOT NULL
);


ALTER TABLE empresa4.ae_frases_captcha OWNER TO sae;

--
-- Name: ae_llamadas; Type: TABLE; Schema: empresa4; Owner: sae
--

CREATE TABLE empresa4.ae_llamadas (
    id integer NOT NULL,
    etiqueta character varying(100) NOT NULL,
    fecha date NOT NULL,
    hora timestamp without time zone NOT NULL,
    numero integer NOT NULL,
    puesto integer NOT NULL,
    aere_id integer NOT NULL,
    aers_id integer NOT NULL
);


ALTER TABLE empresa4.ae_llamadas OWNER TO sae;

--
-- Name: ae_meses; Type: TABLE; Schema: empresa4; Owner: sae
--

CREATE TABLE empresa4.ae_meses (
    id integer NOT NULL,
    mes integer NOT NULL,
    aepl_id integer NOT NULL
);


ALTER TABLE empresa4.ae_meses OWNER TO sae;

--
-- Name: ae_parametros_accion; Type: TABLE; Schema: empresa4; Owner: sae
--

CREATE TABLE empresa4.ae_parametros_accion (
    id integer NOT NULL,
    fecha_baja timestamp without time zone,
    largo integer,
    nombre character varying(50) NOT NULL,
    tipo character varying(30),
    aeac_id integer NOT NULL
);


ALTER TABLE empresa4.ae_parametros_accion OWNER TO sae;

--
-- Name: ae_parametros_autocompletar; Type: TABLE; Schema: empresa4; Owner: sae
--

CREATE TABLE empresa4.ae_parametros_autocompletar (
    id integer NOT NULL,
    fecha_baja timestamp without time zone,
    modo integer NOT NULL,
    nombre character varying(50) NOT NULL,
    tipo character varying(30) NOT NULL,
    aeserv_id integer NOT NULL
);


ALTER TABLE empresa4.ae_parametros_autocompletar OWNER TO sae;

--
-- Name: ae_parametros_validacion; Type: TABLE; Schema: empresa4; Owner: sae
--

CREATE TABLE empresa4.ae_parametros_validacion (
    id integer NOT NULL,
    fecha_baja timestamp without time zone,
    largo integer,
    nombre character varying(50) NOT NULL,
    tipo character varying(30),
    aeva_id integer NOT NULL
);


ALTER TABLE empresa4.ae_parametros_validacion OWNER TO sae;

--
-- Name: ae_plantillas; Type: TABLE; Schema: empresa4; Owner: sae
--

CREATE TABLE empresa4.ae_plantillas (
    id integer NOT NULL,
    cupo integer NOT NULL,
    fecha_baja timestamp without time zone,
    frecuencia integer NOT NULL,
    generacion_automatica boolean NOT NULL,
    hora_fin timestamp without time zone NOT NULL,
    hora_inicio timestamp without time zone NOT NULL,
    aere_id integer NOT NULL
);


ALTER TABLE empresa4.ae_plantillas OWNER TO sae;

--
-- Name: ae_preguntas_captcha; Type: TABLE; Schema: empresa4; Owner: sae
--

CREATE TABLE empresa4.ae_preguntas_captcha (
    clave character varying(100) NOT NULL,
    pregunta character varying(1024),
    respuesta character varying(25),
    idioma character varying(5) NOT NULL
);


ALTER TABLE empresa4.ae_preguntas_captcha OWNER TO sae;

--
-- Name: ae_recursos; Type: TABLE; Schema: empresa4; Owner: sae
--

CREATE TABLE empresa4.ae_recursos (
    id integer NOT NULL,
    cant_dias_a_generar integer NOT NULL,
    descripcion character varying(1000) NOT NULL,
    dias_inicio_ventana_internet integer NOT NULL,
    dias_inicio_ventana_intranet integer NOT NULL,
    dias_ventana_internet integer NOT NULL,
    dias_ventana_intranet integer NOT NULL,
    fecha_baja timestamp without time zone,
    fecha_fin timestamp without time zone,
    fecha_fin_disp timestamp without time zone,
    fecha_inicio timestamp without time zone NOT NULL,
    fecha_inicio_disp timestamp without time zone NOT NULL,
    largo_lista_espera integer,
    mostrar_numero_en_llamador boolean NOT NULL,
    mostrar_numero_en_ticket boolean NOT NULL,
    nombre character varying(100) NOT NULL,
    sabado_es_habil boolean NOT NULL,
    serie character varying(3),
    usar_llamador boolean NOT NULL,
    ventana_cupos_minimos integer NOT NULL,
    version integer NOT NULL,
    visible_internet boolean NOT NULL,
    aeag_id integer NOT NULL,
    oficina_id character varying(25),
    direccion character varying(100),
    localidad character varying(100),
    departamento character varying(100),
    telefonos character varying(100),
    horarios character varying(100),
    latitud numeric,
    longitud numeric,
    mostrar_id_en_ticket boolean,
    domingo_es_habil boolean DEFAULT false NOT NULL,
    presencial_admite boolean DEFAULT false NOT NULL,
    presencial_lunes boolean DEFAULT false NOT NULL,
    presencial_martes boolean DEFAULT false NOT NULL,
    presencial_miercoles boolean DEFAULT false NOT NULL,
    presencial_jueves boolean DEFAULT false NOT NULL,
    presencial_viernes boolean DEFAULT false NOT NULL,
    presencial_sabado boolean DEFAULT false NOT NULL,
    presencial_domingo boolean DEFAULT false NOT NULL,
    presencial_cupos integer DEFAULT 0 NOT NULL,
    tamanio_fuente_grande integer DEFAULT 12 NOT NULL,
    tamanio_fuente_normal integer DEFAULT 10 NOT NULL,
    tamanio_fuente_chica integer DEFAULT 8 NOT NULL,
    fuente_ticket character varying(100) DEFAULT 'Helvetica-Bold'::character varying NOT NULL,
    multiple_admite boolean DEFAULT false NOT NULL,
    cambios_admite boolean DEFAULT false NOT NULL,
    cambios_tiempo integer,
    cambios_unidad integer,
    periodo_validacion integer DEFAULT 0 NOT NULL,
    validar_por_ip boolean DEFAULT false NOT NULL,
    cantidad_por_ip integer,
    periodo_por_ip integer,
    ips_sin_validacion character varying(4000) DEFAULT NULL::character varying,
    cancela_tiempo integer DEFAULT 0 NOT NULL,
    cancela_unidad integer DEFAULT 12 NOT NULL,
    cancela_tipo character varying(1) DEFAULT 'I'::character varying NOT NULL,
    mi_perfil_con_hab boolean DEFAULT true NOT NULL,
    mi_perfil_can_hab boolean DEFAULT true NOT NULL,
    mi_perfil_rec_hab boolean DEFAULT true NOT NULL,
    mi_perfil_con_tit character varying(255) DEFAULT NULL::character varying,
    mi_perfil_con_cor character varying(500) DEFAULT NULL::character varying,
    mi_perfil_con_lar character varying(1024) DEFAULT NULL::character varying,
    mi_perfil_con_ven integer,
    mi_perfil_can_tit character varying(255) DEFAULT NULL::character varying,
    mi_perfil_can_cor character varying(500) DEFAULT NULL::character varying,
    mi_perfil_can_lar character varying(1024) DEFAULT NULL::character varying,
    mi_perfil_can_ven integer,
    mi_perfil_rec_tit character varying(255) DEFAULT NULL::character varying,
    mi_perfil_rec_cor character varying(500) DEFAULT NULL::character varying,
    mi_perfil_rec_lar character varying(1024) DEFAULT NULL::character varying,
    mi_perfil_rec_ven integer,
    mi_perfil_rec_hora integer,
    mi_perfil_rec_dias integer
);


ALTER TABLE empresa4.ae_recursos OWNER TO sae;

--
-- Name: ae_recursos_aud; Type: TABLE; Schema: empresa4; Owner: sae
--

CREATE TABLE empresa4.ae_recursos_aud (
    id integer NOT NULL,
    id_recurso integer NOT NULL,
    nombre character varying(100) NOT NULL,
    descripcion character varying(1000) NOT NULL,
    fecha_inicio timestamp without time zone NOT NULL,
    fecha_fin timestamp without time zone,
    fecha_inicio_disp timestamp without time zone NOT NULL,
    fecha_fin_disp timestamp without time zone,
    dias_inicio_ventana_intranet integer NOT NULL,
    dias_ventana_intranet integer NOT NULL,
    dias_inicio_ventana_internet integer NOT NULL,
    dias_ventana_internet integer NOT NULL,
    ventana_cupos_minimos integer NOT NULL,
    cant_dias_a_generar integer NOT NULL,
    largo_lista_espera integer,
    fecha_baja timestamp without time zone,
    mostrar_numero_en_llamador boolean NOT NULL,
    visible_internet boolean NOT NULL,
    usar_llamador boolean NOT NULL,
    serie character varying(3),
    sabado_es_habil boolean NOT NULL,
    domingo_es_habil boolean DEFAULT false NOT NULL,
    mostrar_numero_en_ticket boolean NOT NULL,
    mostrar_id_en_ticket boolean,
    fuente_ticket character varying(100) DEFAULT 'Helvetica-Bold'::character varying NOT NULL,
    tamanio_fuente_grande integer DEFAULT 12 NOT NULL,
    tamanio_fuente_normal integer DEFAULT 10 NOT NULL,
    tamanio_fuente_chica integer DEFAULT 8 NOT NULL,
    oficina_id character varying(25),
    direccion character varying(100),
    localidad character varying(100),
    departamento character varying(100),
    telefonos character varying(100),
    horarios character varying(100),
    latitud numeric,
    longitud numeric,
    agenda integer NOT NULL,
    presencial_admite boolean DEFAULT false NOT NULL,
    presencial_cupos integer DEFAULT 0 NOT NULL,
    presencial_lunes boolean DEFAULT false NOT NULL,
    presencial_martes boolean DEFAULT false NOT NULL,
    presencial_miercoles boolean DEFAULT false NOT NULL,
    presencial_jueves boolean DEFAULT false NOT NULL,
    presencial_viernes boolean DEFAULT false NOT NULL,
    presencial_sabado boolean DEFAULT false NOT NULL,
    presencial_domingo boolean DEFAULT false NOT NULL,
    multiple_admite boolean DEFAULT false NOT NULL,
    cambios_admite boolean DEFAULT false NOT NULL,
    cambios_tiempo integer,
    cambios_unidad integer,
    periodo_validacion integer DEFAULT 0 NOT NULL,
    validar_por_ip boolean DEFAULT false NOT NULL,
    cantidad_por_ip integer,
    periodo_por_ip integer,
    ips_sin_validacion character varying(4000) DEFAULT NULL::character varying,
    cancela_tiempo integer DEFAULT 0 NOT NULL,
    cancela_unidad integer DEFAULT 12 NOT NULL,
    cancela_tipo character varying(1) DEFAULT 'I'::character varying NOT NULL,
    mi_perfil_con_hab boolean DEFAULT true NOT NULL,
    mi_perfil_con_tit character varying(200) DEFAULT NULL::character varying,
    mi_perfil_con_cor character varying(500) DEFAULT NULL::character varying,
    mi_perfil_con_lar character varying(3200) DEFAULT NULL::character varying,
    mi_perfil_con_ven integer,
    mi_perfil_can_hab boolean DEFAULT true NOT NULL,
    mi_perfil_can_tit character varying(200) DEFAULT NULL::character varying,
    mi_perfil_can_cor character varying(500) DEFAULT NULL::character varying,
    mi_perfil_can_lar character varying(3200) DEFAULT NULL::character varying,
    mi_perfil_can_ven integer,
    mi_perfil_rec_hab boolean DEFAULT true NOT NULL,
    mi_perfil_rec_tit character varying(200) DEFAULT NULL::character varying,
    mi_perfil_rec_cor character varying(500) DEFAULT NULL::character varying,
    mi_perfil_rec_lar character varying(3200) DEFAULT NULL::character varying,
    mi_perfil_rec_ven integer,
    mi_perfil_rec_hora integer,
    mi_perfil_rec_dias integer,
    reserva_pen_tiempo_max integer,
    reserva_pend_tiempo_max integer,
    fecha_modificacion timestamp without time zone,
    usuario character varying(45),
    version integer NOT NULL,
    tipo_operacion smallint
);


ALTER TABLE empresa4.ae_recursos_aud OWNER TO sae;

--
-- Name: ae_reservas; Type: TABLE; Schema: empresa4; Owner: sae
--

CREATE TABLE empresa4.ae_reservas (
    id integer NOT NULL,
    estado character varying(1) NOT NULL,
    fact timestamp without time zone NOT NULL,
    fcrea timestamp without time zone NOT NULL,
    numero integer,
    observaciones character varying(100),
    origen character varying(1),
    ucancela character varying(255),
    ucrea character varying(255),
    version integer NOT NULL,
    codigo_seguridad character varying(10) DEFAULT '00000'::character varying,
    trazabilidad_guid character varying(25),
    tramite_codigo character varying(10),
    tramite_nombre character varying(100),
    serie character varying(3),
    tcancela character varying(1),
    fcancela timestamp without time zone,
    aetr_id integer,
    ip_origen character varying(16) DEFAULT NULL::character varying,
    flibera timestamp without time zone,
    mi_perfil_notif boolean DEFAULT true NOT NULL,
    notificar boolean DEFAULT true,
    reserva_hija_id integer
);


ALTER TABLE empresa4.ae_reservas OWNER TO sae;

--
-- Name: ae_reservas_disponibilidades; Type: TABLE; Schema: empresa4; Owner: sae
--

CREATE TABLE empresa4.ae_reservas_disponibilidades (
    aers_id integer NOT NULL,
    aedi_id integer NOT NULL
);


ALTER TABLE empresa4.ae_reservas_disponibilidades OWNER TO sae;

--
-- Name: ae_roles_usuario_recurso; Type: TABLE; Schema: empresa4; Owner: sae
--

CREATE TABLE empresa4.ae_roles_usuario_recurso (
    usuario_id integer NOT NULL,
    recurso_id integer NOT NULL,
    roles character varying(4000)
);


ALTER TABLE empresa4.ae_roles_usuario_recurso OWNER TO sae;

--
-- Name: ae_serv_autocomp_por_dato; Type: TABLE; Schema: empresa4; Owner: sae
--

CREATE TABLE empresa4.ae_serv_autocomp_por_dato (
    id integer NOT NULL,
    fecha_desasociacion timestamp without time zone,
    nombre_parametro character varying(50) NOT NULL,
    aeds_id integer NOT NULL,
    aesr_id integer NOT NULL
);


ALTER TABLE empresa4.ae_serv_autocomp_por_dato OWNER TO sae;

--
-- Name: ae_serv_autocompletar; Type: TABLE; Schema: empresa4; Owner: sae
--

CREATE TABLE empresa4.ae_serv_autocompletar (
    id integer NOT NULL,
    descripcion character varying(100) NOT NULL,
    fecha_baja timestamp without time zone,
    host character varying(100),
    nombre character varying(50) NOT NULL,
    servicio character varying(250) NOT NULL
);


ALTER TABLE empresa4.ae_serv_autocompletar OWNER TO sae;

--
-- Name: ae_servicio_por_recurso; Type: TABLE; Schema: empresa4; Owner: sae
--

CREATE TABLE empresa4.ae_servicio_por_recurso (
    id integer NOT NULL,
    fecha_baja timestamp without time zone,
    aeserv_id integer NOT NULL,
    aere_id integer NOT NULL
);


ALTER TABLE empresa4.ae_servicio_por_recurso OWNER TO sae;

--
-- Name: ae_textos; Type: TABLE; Schema: empresa4; Owner: sae
--

CREATE TABLE empresa4.ae_textos (
    codigo character varying(100) NOT NULL,
    idioma character varying(5) NOT NULL,
    texto character varying(4096) NOT NULL
);


ALTER TABLE empresa4.ae_textos OWNER TO sae;

--
-- Name: ae_textos_agenda; Type: TABLE; Schema: empresa4; Owner: sae
--

CREATE TABLE empresa4.ae_textos_agenda (
    id integer NOT NULL,
    texto_paso1 character varying(1000),
    texto_paso2 character varying(1000),
    texto_paso3 character varying(1000),
    texto_sel_recurso character varying(50),
    texto_ticket character varying(1000),
    aeag_id integer NOT NULL,
    texto_correo_conf character varying(1000),
    texto_correo_canc character varying(1000),
    por_defecto boolean DEFAULT false NOT NULL,
    idioma character varying(5) NOT NULL
);


ALTER TABLE empresa4.ae_textos_agenda OWNER TO sae;

--
-- Name: ae_textos_recurso; Type: TABLE; Schema: empresa4; Owner: sae
--

CREATE TABLE empresa4.ae_textos_recurso (
    id integer NOT NULL,
    texto_paso2 character varying(1000),
    texto_paso3 character varying(1000),
    ticket_etiqueta_dos character varying(15),
    ticket_etiqueta_uno character varying(15),
    titulo_ciudadano_en_llamador character varying(255),
    titulo_puesto_en_llamador character varying(255),
    valor_etiqueta_dos character varying(30),
    valor_etiqueta_uno character varying(30),
    aere_id integer NOT NULL,
    idioma character varying(5) NOT NULL
);


ALTER TABLE empresa4.ae_textos_recurso OWNER TO sae;

--
-- Name: ae_tokens_reservas; Type: TABLE; Schema: empresa4; Owner: sae
--

CREATE TABLE empresa4.ae_tokens_reservas (
    id integer NOT NULL,
    token character varying(50) NOT NULL,
    aere_id integer,
    fecha_inicio timestamp without time zone NOT NULL,
    ultima_reserva timestamp without time zone,
    estado character varying(1) NOT NULL,
    cedula character varying(50) NOT NULL,
    nombre character varying(100) NOT NULL,
    correoe character varying(100) NOT NULL,
    tramite character varying(10),
    notas character varying(4000),
    version integer NOT NULL,
    ip_origen character varying(16) DEFAULT NULL::character varying
);


ALTER TABLE empresa4.ae_tokens_reservas OWNER TO sae;

--
-- Name: ae_tramites_agendas; Type: TABLE; Schema: empresa4; Owner: sae
--

CREATE TABLE empresa4.ae_tramites_agendas (
    id integer NOT NULL,
    agenda_id integer NOT NULL,
    tramite_id character varying(25),
    tramite_codigo character varying(10),
    tramite_nombre character varying(100)
);


ALTER TABLE empresa4.ae_tramites_agendas OWNER TO sae;

--
-- Name: ae_validaciones; Type: TABLE; Schema: empresa4; Owner: sae
--

CREATE TABLE empresa4.ae_validaciones (
    id integer NOT NULL,
    descripcion character varying(100) NOT NULL,
    fecha_baja timestamp without time zone,
    host character varying(100),
    nombre character varying(50) NOT NULL,
    servicio character varying(250) NOT NULL
);


ALTER TABLE empresa4.ae_validaciones OWNER TO sae;

--
-- Name: ae_validaciones_por_dato; Type: TABLE; Schema: empresa4; Owner: sae
--

CREATE TABLE empresa4.ae_validaciones_por_dato (
    id integer NOT NULL,
    fecha_desasociacion timestamp without time zone,
    nombre_parametro character varying(50) NOT NULL,
    aeds_id integer NOT NULL,
    aevr_id integer NOT NULL
);


ALTER TABLE empresa4.ae_validaciones_por_dato OWNER TO sae;

--
-- Name: ae_validaciones_por_recurso; Type: TABLE; Schema: empresa4; Owner: sae
--

CREATE TABLE empresa4.ae_validaciones_por_recurso (
    id integer NOT NULL,
    fecha_baja timestamp without time zone,
    orden_ejecucion integer,
    aere_id integer NOT NULL,
    aeva_id integer NOT NULL
);


ALTER TABLE empresa4.ae_validaciones_por_recurso OWNER TO sae;

--
-- Name: ae_valor_constante_val_rec; Type: TABLE; Schema: empresa4; Owner: sae
--

CREATE TABLE empresa4.ae_valor_constante_val_rec (
    id integer NOT NULL,
    fecha_desasociacion timestamp without time zone,
    nombre_constante character varying(50) NOT NULL,
    valor character varying(100) NOT NULL,
    aevr_id integer NOT NULL
);


ALTER TABLE empresa4.ae_valor_constante_val_rec OWNER TO sae;

--
-- Name: ae_valores_del_dato; Type: TABLE; Schema: empresa4; Owner: sae
--

CREATE TABLE empresa4.ae_valores_del_dato (
    id integer NOT NULL,
    etiqueta character varying(50) NOT NULL,
    fecha_desde timestamp without time zone NOT NULL,
    fecha_hasta timestamp without time zone,
    orden integer NOT NULL,
    valor character varying(50) NOT NULL,
    aeds_id integer NOT NULL,
    borrar_flag boolean DEFAULT true NOT NULL
);


ALTER TABLE empresa4.ae_valores_del_dato OWNER TO sae;

--
-- Name: s_ae_accion; Type: SEQUENCE; Schema: empresa4; Owner: sae
--

CREATE SEQUENCE empresa4.s_ae_accion
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa4.s_ae_accion OWNER TO sae;

--
-- Name: s_ae_acciondato; Type: SEQUENCE; Schema: empresa4; Owner: sae
--

CREATE SEQUENCE empresa4.s_ae_acciondato
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa4.s_ae_acciondato OWNER TO sae;

--
-- Name: s_ae_acciones_miperfil; Type: SEQUENCE; Schema: empresa4; Owner: sae
--

CREATE SEQUENCE empresa4.s_ae_acciones_miperfil
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa4.s_ae_acciones_miperfil OWNER TO sae;

--
-- Name: s_ae_accionrecurso; Type: SEQUENCE; Schema: empresa4; Owner: sae
--

CREATE SEQUENCE empresa4.s_ae_accionrecurso
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa4.s_ae_accionrecurso OWNER TO sae;

--
-- Name: s_ae_agenda; Type: SEQUENCE; Schema: empresa4; Owner: sae
--

CREATE SEQUENCE empresa4.s_ae_agenda
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa4.s_ae_agenda OWNER TO sae;

--
-- Name: s_ae_agrupacion_dato; Type: SEQUENCE; Schema: empresa4; Owner: sae
--

CREATE SEQUENCE empresa4.s_ae_agrupacion_dato
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa4.s_ae_agrupacion_dato OWNER TO sae;

--
-- Name: s_ae_anio; Type: SEQUENCE; Schema: empresa4; Owner: sae
--

CREATE SEQUENCE empresa4.s_ae_anio
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa4.s_ae_anio OWNER TO sae;

--
-- Name: s_ae_atencion; Type: SEQUENCE; Schema: empresa4; Owner: sae
--

CREATE SEQUENCE empresa4.s_ae_atencion
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa4.s_ae_atencion OWNER TO sae;

--
-- Name: s_ae_comunicaciones; Type: SEQUENCE; Schema: empresa4; Owner: sae
--

CREATE SEQUENCE empresa4.s_ae_comunicaciones
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa4.s_ae_comunicaciones OWNER TO sae;

--
-- Name: s_ae_constval; Type: SEQUENCE; Schema: empresa4; Owner: sae
--

CREATE SEQUENCE empresa4.s_ae_constval
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa4.s_ae_constval OWNER TO sae;

--
-- Name: s_ae_datoasolicitar; Type: SEQUENCE; Schema: empresa4; Owner: sae
--

CREATE SEQUENCE empresa4.s_ae_datoasolicitar
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa4.s_ae_datoasolicitar OWNER TO sae;

--
-- Name: s_ae_datodelrecurso; Type: SEQUENCE; Schema: empresa4; Owner: sae
--

CREATE SEQUENCE empresa4.s_ae_datodelrecurso
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa4.s_ae_datodelrecurso OWNER TO sae;

--
-- Name: s_ae_datoreserva; Type: SEQUENCE; Schema: empresa4; Owner: sae
--

CREATE SEQUENCE empresa4.s_ae_datoreserva
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa4.s_ae_datoreserva OWNER TO sae;

--
-- Name: s_ae_dia_mes; Type: SEQUENCE; Schema: empresa4; Owner: sae
--

CREATE SEQUENCE empresa4.s_ae_dia_mes
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa4.s_ae_dia_mes OWNER TO sae;

--
-- Name: s_ae_dia_semana; Type: SEQUENCE; Schema: empresa4; Owner: sae
--

CREATE SEQUENCE empresa4.s_ae_dia_semana
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa4.s_ae_dia_semana OWNER TO sae;

--
-- Name: s_ae_disponibilidad; Type: SEQUENCE; Schema: empresa4; Owner: sae
--

CREATE SEQUENCE empresa4.s_ae_disponibilidad
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa4.s_ae_disponibilidad OWNER TO sae;

--
-- Name: s_ae_llamada; Type: SEQUENCE; Schema: empresa4; Owner: sae
--

CREATE SEQUENCE empresa4.s_ae_llamada
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa4.s_ae_llamada OWNER TO sae;

--
-- Name: s_ae_mes; Type: SEQUENCE; Schema: empresa4; Owner: sae
--

CREATE SEQUENCE empresa4.s_ae_mes
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa4.s_ae_mes OWNER TO sae;

--
-- Name: s_ae_paramaccion; Type: SEQUENCE; Schema: empresa4; Owner: sae
--

CREATE SEQUENCE empresa4.s_ae_paramaccion
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa4.s_ae_paramaccion OWNER TO sae;

--
-- Name: s_ae_parametros_autocompletar; Type: SEQUENCE; Schema: empresa4; Owner: sae
--

CREATE SEQUENCE empresa4.s_ae_parametros_autocompletar
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa4.s_ae_parametros_autocompletar OWNER TO sae;

--
-- Name: s_ae_paramval; Type: SEQUENCE; Schema: empresa4; Owner: sae
--

CREATE SEQUENCE empresa4.s_ae_paramval
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa4.s_ae_paramval OWNER TO sae;

--
-- Name: s_ae_plantilla; Type: SEQUENCE; Schema: empresa4; Owner: sae
--

CREATE SEQUENCE empresa4.s_ae_plantilla
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa4.s_ae_plantilla OWNER TO sae;

--
-- Name: s_ae_recurso; Type: SEQUENCE; Schema: empresa4; Owner: sae
--

CREATE SEQUENCE empresa4.s_ae_recurso
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa4.s_ae_recurso OWNER TO sae;

--
-- Name: s_ae_recurso_aud; Type: SEQUENCE; Schema: empresa4; Owner: sae
--

CREATE SEQUENCE empresa4.s_ae_recurso_aud
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa4.s_ae_recurso_aud OWNER TO sae;

--
-- Name: s_ae_reserva; Type: SEQUENCE; Schema: empresa4; Owner: sae
--

CREATE SEQUENCE empresa4.s_ae_reserva
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa4.s_ae_reserva OWNER TO sae;

--
-- Name: s_ae_serv_autocompletar; Type: SEQUENCE; Schema: empresa4; Owner: sae
--

CREATE SEQUENCE empresa4.s_ae_serv_autocompletar
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa4.s_ae_serv_autocompletar OWNER TO sae;

--
-- Name: s_ae_servdato; Type: SEQUENCE; Schema: empresa4; Owner: sae
--

CREATE SEQUENCE empresa4.s_ae_servdato
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa4.s_ae_servdato OWNER TO sae;

--
-- Name: s_ae_servrecurso; Type: SEQUENCE; Schema: empresa4; Owner: sae
--

CREATE SEQUENCE empresa4.s_ae_servrecurso
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa4.s_ae_servrecurso OWNER TO sae;

--
-- Name: s_ae_texto_agenda; Type: SEQUENCE; Schema: empresa4; Owner: sae
--

CREATE SEQUENCE empresa4.s_ae_texto_agenda
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa4.s_ae_texto_agenda OWNER TO sae;

--
-- Name: s_ae_textorecurso; Type: SEQUENCE; Schema: empresa4; Owner: sae
--

CREATE SEQUENCE empresa4.s_ae_textorecurso
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa4.s_ae_textorecurso OWNER TO sae;

--
-- Name: s_ae_token_reserva; Type: SEQUENCE; Schema: empresa4; Owner: sae
--

CREATE SEQUENCE empresa4.s_ae_token_reserva
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa4.s_ae_token_reserva OWNER TO sae;

--
-- Name: s_ae_tramites_agendas; Type: SEQUENCE; Schema: empresa4; Owner: sae
--

CREATE SEQUENCE empresa4.s_ae_tramites_agendas
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa4.s_ae_tramites_agendas OWNER TO sae;

--
-- Name: s_ae_valconstante; Type: SEQUENCE; Schema: empresa4; Owner: sae
--

CREATE SEQUENCE empresa4.s_ae_valconstante
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa4.s_ae_valconstante OWNER TO sae;

--
-- Name: s_ae_valdato; Type: SEQUENCE; Schema: empresa4; Owner: sae
--

CREATE SEQUENCE empresa4.s_ae_valdato
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa4.s_ae_valdato OWNER TO sae;

--
-- Name: s_ae_validacion; Type: SEQUENCE; Schema: empresa4; Owner: sae
--

CREATE SEQUENCE empresa4.s_ae_validacion
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa4.s_ae_validacion OWNER TO sae;

--
-- Name: s_ae_valorposible; Type: SEQUENCE; Schema: empresa4; Owner: sae
--

CREATE SEQUENCE empresa4.s_ae_valorposible
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa4.s_ae_valorposible OWNER TO sae;

--
-- Name: s_ae_valrecurso; Type: SEQUENCE; Schema: empresa4; Owner: sae
--

CREATE SEQUENCE empresa4.s_ae_valrecurso
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa4.s_ae_valrecurso OWNER TO sae;

--
-- Name: ae_acciones; Type: TABLE; Schema: empresa5; Owner: sae
--

CREATE TABLE empresa5.ae_acciones (
    id integer NOT NULL,
    descripcion character varying(100) NOT NULL,
    fecha_baja timestamp without time zone,
    host character varying(100),
    nombre character varying(50) NOT NULL,
    servicio character varying(250) NOT NULL
);


ALTER TABLE empresa5.ae_acciones OWNER TO sae;

--
-- Name: ae_acciones_miperfil_recurso; Type: TABLE; Schema: empresa5; Owner: sae
--

CREATE TABLE empresa5.ae_acciones_miperfil_recurso (
    id integer NOT NULL,
    recurso_id integer NOT NULL,
    titulo_con_1 character varying(100),
    url_con_1 character varying(1024),
    destacada_con_1 boolean,
    titulo_con_2 character varying(100),
    url_con_2 character varying(1024),
    destacada_con_2 boolean,
    titulo_con_3 character varying(100),
    url_con_3 character varying(1024),
    destacada_con_3 boolean,
    titulo_con_4 character varying(100),
    url_con_4 character varying(1024),
    destacada_con_4 boolean,
    titulo_con_5 character varying(100),
    url_con_5 character varying(1024),
    destacada_con_5 boolean,
    titulo_can_1 character varying(100),
    url_can_1 character varying(1024),
    destacada_can_1 boolean,
    titulo_can_2 character varying(100),
    url_can_2 character varying(1024),
    destacada_can_2 boolean,
    titulo_can_3 character varying(100),
    url_can_3 character varying(1024),
    destacada_can_3 boolean,
    titulo_can_4 character varying(100),
    url_can_4 character varying(1024),
    destacada_can_4 boolean,
    titulo_can_5 character varying(100),
    url_can_5 character varying(1024),
    destacada_can_5 boolean,
    titulo_rec_1 character varying(100),
    url_rec_1 character varying(1024),
    destacada_rec_1 boolean,
    titulo_rec_2 character varying(100),
    url_rec_2 character varying(1024),
    destacada_rec_2 boolean,
    titulo_rec_3 character varying(100),
    url_rec_3 character varying(1024),
    destacada_rec_3 boolean,
    titulo_rec_4 character varying(100),
    url_rec_4 character varying(1024),
    destacada_rec_4 boolean,
    titulo_rec_5 character varying(100),
    url_rec_5 character varying(1024),
    destacada_rec_5 boolean
);


ALTER TABLE empresa5.ae_acciones_miperfil_recurso OWNER TO sae;

--
-- Name: ae_acciones_por_dato; Type: TABLE; Schema: empresa5; Owner: sae
--

CREATE TABLE empresa5.ae_acciones_por_dato (
    id integer NOT NULL,
    fecha_desasociacion timestamp without time zone,
    nombre_parametro character varying(50) NOT NULL,
    aear_id integer NOT NULL,
    aeds_id integer NOT NULL
);


ALTER TABLE empresa5.ae_acciones_por_dato OWNER TO sae;

--
-- Name: ae_acciones_por_recurso; Type: TABLE; Schema: empresa5; Owner: sae
--

CREATE TABLE empresa5.ae_acciones_por_recurso (
    id integer NOT NULL,
    evento character varying(1) NOT NULL,
    fecha_baja timestamp without time zone,
    orden_ejecucion integer,
    aeac_id integer NOT NULL,
    aere_id integer NOT NULL
);


ALTER TABLE empresa5.ae_acciones_por_recurso OWNER TO sae;

--
-- Name: ae_agendas; Type: TABLE; Schema: empresa5; Owner: sae
--

CREATE TABLE empresa5.ae_agendas (
    id integer NOT NULL,
    descripcion character varying(1000) NOT NULL,
    fecha_baja timestamp without time zone,
    nombre character varying(100) NOT NULL,
    tramite_id character varying(25),
    timezone character varying(100),
    idiomas character varying(100),
    con_cda boolean DEFAULT false,
    tramite_codigo character varying(10),
    publicar_novedades boolean DEFAULT false NOT NULL,
    con_trazabilidad boolean DEFAULT false NOT NULL
);


ALTER TABLE empresa5.ae_agendas OWNER TO sae;

--
-- Name: ae_agrupaciones_datos; Type: TABLE; Schema: empresa5; Owner: sae
--

CREATE TABLE empresa5.ae_agrupaciones_datos (
    id integer NOT NULL,
    fecha_baja timestamp without time zone,
    nombre character varying(50) NOT NULL,
    orden integer NOT NULL,
    aere_id integer NOT NULL,
    borrar_flag boolean NOT NULL,
    etiqueta character varying(50)
);


ALTER TABLE empresa5.ae_agrupaciones_datos OWNER TO sae;

--
-- Name: ae_anios; Type: TABLE; Schema: empresa5; Owner: sae
--

CREATE TABLE empresa5.ae_anios (
    id integer NOT NULL,
    anio integer NOT NULL,
    aepl_id integer NOT NULL
);


ALTER TABLE empresa5.ae_anios OWNER TO sae;

--
-- Name: ae_atencion; Type: TABLE; Schema: empresa5; Owner: sae
--

CREATE TABLE empresa5.ae_atencion (
    id integer NOT NULL,
    asistio boolean NOT NULL,
    duracion integer NOT NULL,
    fact timestamp without time zone NOT NULL,
    fcrea timestamp without time zone NOT NULL,
    funcionario character varying(255) NOT NULL,
    aers_id integer NOT NULL
);


ALTER TABLE empresa5.ae_atencion OWNER TO sae;

--
-- Name: ae_comunicaciones; Type: TABLE; Schema: empresa5; Owner: sae
--

CREATE TABLE empresa5.ae_comunicaciones (
    id integer NOT NULL,
    tipo_1 character varying(25) NOT NULL,
    tipo_2 character varying(25) NOT NULL,
    destino character varying(100) NOT NULL,
    recurso_id integer NOT NULL,
    reserva_id integer,
    token_id integer,
    procesado boolean DEFAULT false NOT NULL,
    mensaje character varying(4000)
);


ALTER TABLE empresa5.ae_comunicaciones OWNER TO sae;

--
-- Name: ae_constante_validacion; Type: TABLE; Schema: empresa5; Owner: sae
--

CREATE TABLE empresa5.ae_constante_validacion (
    id integer NOT NULL,
    fecha_baja timestamp without time zone,
    largo integer NOT NULL,
    nombre character varying(50) NOT NULL,
    tipo character varying(30) NOT NULL,
    aeva_id integer NOT NULL
);


ALTER TABLE empresa5.ae_constante_validacion OWNER TO sae;

--
-- Name: ae_datos_a_solicitar; Type: TABLE; Schema: empresa5; Owner: sae
--

CREATE TABLE empresa5.ae_datos_a_solicitar (
    id integer NOT NULL,
    ancho_despliegue integer NOT NULL,
    columna integer NOT NULL,
    es_clave boolean NOT NULL,
    etiqueta character varying(50) NOT NULL,
    fecha_baja timestamp without time zone,
    fila integer NOT NULL,
    incluir_en_llamador boolean NOT NULL,
    incluir_en_reporte boolean NOT NULL,
    largo integer,
    largo_en_llamador integer NOT NULL,
    nombre character varying(50) NOT NULL,
    orden_en_llamador integer NOT NULL,
    requerido boolean NOT NULL,
    texto_ayuda character varying(100),
    tipo character varying(30) NOT NULL,
    aead_id integer NOT NULL,
    aere_id integer NOT NULL,
    borrar_flag boolean DEFAULT true NOT NULL,
    solo_lectura boolean DEFAULT false NOT NULL,
    incluir_en_novedades boolean DEFAULT false NOT NULL
);


ALTER TABLE empresa5.ae_datos_a_solicitar OWNER TO sae;

--
-- Name: ae_datos_del_recurso; Type: TABLE; Schema: empresa5; Owner: sae
--

CREATE TABLE empresa5.ae_datos_del_recurso (
    id integer NOT NULL,
    etiqueta character varying(50) NOT NULL,
    orden integer NOT NULL,
    valor character varying(100) NOT NULL,
    aere_id integer NOT NULL
);


ALTER TABLE empresa5.ae_datos_del_recurso OWNER TO sae;

--
-- Name: ae_datos_reserva; Type: TABLE; Schema: empresa5; Owner: sae
--

CREATE TABLE empresa5.ae_datos_reserva (
    id integer NOT NULL,
    valor character varying(100) NOT NULL,
    aeds_id integer NOT NULL,
    aers_id integer NOT NULL
);


ALTER TABLE empresa5.ae_datos_reserva OWNER TO sae;

--
-- Name: ae_dias_del_mes; Type: TABLE; Schema: empresa5; Owner: sae
--

CREATE TABLE empresa5.ae_dias_del_mes (
    id integer NOT NULL,
    dia_del_mes integer NOT NULL,
    aepl_id integer NOT NULL
);


ALTER TABLE empresa5.ae_dias_del_mes OWNER TO sae;

--
-- Name: ae_dias_semana; Type: TABLE; Schema: empresa5; Owner: sae
--

CREATE TABLE empresa5.ae_dias_semana (
    id integer NOT NULL,
    dia_semana integer NOT NULL,
    aepl_id integer NOT NULL
);


ALTER TABLE empresa5.ae_dias_semana OWNER TO sae;

--
-- Name: ae_disponibilidades; Type: TABLE; Schema: empresa5; Owner: sae
--

CREATE TABLE empresa5.ae_disponibilidades (
    id integer NOT NULL,
    cupo integer NOT NULL,
    fecha date NOT NULL,
    fecha_baja timestamp without time zone,
    hora_fin timestamp without time zone NOT NULL,
    hora_inicio timestamp without time zone NOT NULL,
    numerador integer NOT NULL,
    version integer NOT NULL,
    aepl_id integer,
    aere_id integer NOT NULL,
    presencial boolean DEFAULT false NOT NULL
);


ALTER TABLE empresa5.ae_disponibilidades OWNER TO sae;

--
-- Name: ae_frases_captcha; Type: TABLE; Schema: empresa5; Owner: sae
--

CREATE TABLE empresa5.ae_frases_captcha (
    clave character varying(100) NOT NULL,
    frase character varying(1024),
    idioma character varying(5) NOT NULL
);


ALTER TABLE empresa5.ae_frases_captcha OWNER TO sae;

--
-- Name: ae_llamadas; Type: TABLE; Schema: empresa5; Owner: sae
--

CREATE TABLE empresa5.ae_llamadas (
    id integer NOT NULL,
    etiqueta character varying(100) NOT NULL,
    fecha date NOT NULL,
    hora timestamp without time zone NOT NULL,
    numero integer NOT NULL,
    puesto integer NOT NULL,
    aere_id integer NOT NULL,
    aers_id integer NOT NULL
);


ALTER TABLE empresa5.ae_llamadas OWNER TO sae;

--
-- Name: ae_meses; Type: TABLE; Schema: empresa5; Owner: sae
--

CREATE TABLE empresa5.ae_meses (
    id integer NOT NULL,
    mes integer NOT NULL,
    aepl_id integer NOT NULL
);


ALTER TABLE empresa5.ae_meses OWNER TO sae;

--
-- Name: ae_parametros_accion; Type: TABLE; Schema: empresa5; Owner: sae
--

CREATE TABLE empresa5.ae_parametros_accion (
    id integer NOT NULL,
    fecha_baja timestamp without time zone,
    largo integer,
    nombre character varying(50) NOT NULL,
    tipo character varying(30),
    aeac_id integer NOT NULL
);


ALTER TABLE empresa5.ae_parametros_accion OWNER TO sae;

--
-- Name: ae_parametros_autocompletar; Type: TABLE; Schema: empresa5; Owner: sae
--

CREATE TABLE empresa5.ae_parametros_autocompletar (
    id integer NOT NULL,
    fecha_baja timestamp without time zone,
    modo integer NOT NULL,
    nombre character varying(50) NOT NULL,
    tipo character varying(30) NOT NULL,
    aeserv_id integer NOT NULL
);


ALTER TABLE empresa5.ae_parametros_autocompletar OWNER TO sae;

--
-- Name: ae_parametros_validacion; Type: TABLE; Schema: empresa5; Owner: sae
--

CREATE TABLE empresa5.ae_parametros_validacion (
    id integer NOT NULL,
    fecha_baja timestamp without time zone,
    largo integer,
    nombre character varying(50) NOT NULL,
    tipo character varying(30),
    aeva_id integer NOT NULL
);


ALTER TABLE empresa5.ae_parametros_validacion OWNER TO sae;

--
-- Name: ae_plantillas; Type: TABLE; Schema: empresa5; Owner: sae
--

CREATE TABLE empresa5.ae_plantillas (
    id integer NOT NULL,
    cupo integer NOT NULL,
    fecha_baja timestamp without time zone,
    frecuencia integer NOT NULL,
    generacion_automatica boolean NOT NULL,
    hora_fin timestamp without time zone NOT NULL,
    hora_inicio timestamp without time zone NOT NULL,
    aere_id integer NOT NULL
);


ALTER TABLE empresa5.ae_plantillas OWNER TO sae;

--
-- Name: ae_preguntas_captcha; Type: TABLE; Schema: empresa5; Owner: sae
--

CREATE TABLE empresa5.ae_preguntas_captcha (
    clave character varying(100) NOT NULL,
    pregunta character varying(1024),
    respuesta character varying(25),
    idioma character varying(5) NOT NULL
);


ALTER TABLE empresa5.ae_preguntas_captcha OWNER TO sae;

--
-- Name: ae_recursos; Type: TABLE; Schema: empresa5; Owner: sae
--

CREATE TABLE empresa5.ae_recursos (
    id integer NOT NULL,
    cant_dias_a_generar integer NOT NULL,
    descripcion character varying(1000) NOT NULL,
    dias_inicio_ventana_internet integer NOT NULL,
    dias_inicio_ventana_intranet integer NOT NULL,
    dias_ventana_internet integer NOT NULL,
    dias_ventana_intranet integer NOT NULL,
    fecha_baja timestamp without time zone,
    fecha_fin timestamp without time zone,
    fecha_fin_disp timestamp without time zone,
    fecha_inicio timestamp without time zone NOT NULL,
    fecha_inicio_disp timestamp without time zone NOT NULL,
    largo_lista_espera integer,
    mostrar_numero_en_llamador boolean NOT NULL,
    mostrar_numero_en_ticket boolean NOT NULL,
    nombre character varying(100) NOT NULL,
    sabado_es_habil boolean NOT NULL,
    serie character varying(3),
    usar_llamador boolean NOT NULL,
    ventana_cupos_minimos integer NOT NULL,
    version integer NOT NULL,
    visible_internet boolean NOT NULL,
    aeag_id integer NOT NULL,
    oficina_id character varying(25),
    direccion character varying(100),
    localidad character varying(100),
    departamento character varying(100),
    telefonos character varying(100),
    horarios character varying(100),
    latitud numeric,
    longitud numeric,
    mostrar_id_en_ticket boolean,
    domingo_es_habil boolean DEFAULT false NOT NULL,
    presencial_admite boolean DEFAULT false NOT NULL,
    presencial_lunes boolean DEFAULT false NOT NULL,
    presencial_martes boolean DEFAULT false NOT NULL,
    presencial_miercoles boolean DEFAULT false NOT NULL,
    presencial_jueves boolean DEFAULT false NOT NULL,
    presencial_viernes boolean DEFAULT false NOT NULL,
    presencial_sabado boolean DEFAULT false NOT NULL,
    presencial_domingo boolean DEFAULT false NOT NULL,
    presencial_cupos integer DEFAULT 0 NOT NULL,
    tamanio_fuente_grande integer DEFAULT 12 NOT NULL,
    tamanio_fuente_normal integer DEFAULT 10 NOT NULL,
    tamanio_fuente_chica integer DEFAULT 8 NOT NULL,
    fuente_ticket character varying(100) DEFAULT 'Helvetica-Bold'::character varying NOT NULL,
    multiple_admite boolean DEFAULT false NOT NULL,
    cambios_admite boolean DEFAULT false NOT NULL,
    cambios_tiempo integer,
    cambios_unidad integer,
    periodo_validacion integer DEFAULT 0 NOT NULL,
    validar_por_ip boolean DEFAULT false NOT NULL,
    cantidad_por_ip integer,
    periodo_por_ip integer,
    ips_sin_validacion character varying(4000) DEFAULT NULL::character varying,
    cancela_tiempo integer DEFAULT 0 NOT NULL,
    cancela_unidad integer DEFAULT 12 NOT NULL,
    cancela_tipo character varying(1) DEFAULT 'I'::character varying NOT NULL,
    mi_perfil_con_hab boolean DEFAULT true NOT NULL,
    mi_perfil_can_hab boolean DEFAULT true NOT NULL,
    mi_perfil_rec_hab boolean DEFAULT true NOT NULL,
    mi_perfil_con_tit character varying(255) DEFAULT NULL::character varying,
    mi_perfil_con_cor character varying(500) DEFAULT NULL::character varying,
    mi_perfil_con_lar character varying(1024) DEFAULT NULL::character varying,
    mi_perfil_con_ven integer,
    mi_perfil_can_tit character varying(255) DEFAULT NULL::character varying,
    mi_perfil_can_cor character varying(500) DEFAULT NULL::character varying,
    mi_perfil_can_lar character varying(1024) DEFAULT NULL::character varying,
    mi_perfil_can_ven integer,
    mi_perfil_rec_tit character varying(255) DEFAULT NULL::character varying,
    mi_perfil_rec_cor character varying(500) DEFAULT NULL::character varying,
    mi_perfil_rec_lar character varying(1024) DEFAULT NULL::character varying,
    mi_perfil_rec_ven integer,
    mi_perfil_rec_hora integer,
    mi_perfil_rec_dias integer
);


ALTER TABLE empresa5.ae_recursos OWNER TO sae;

--
-- Name: ae_recursos_aud; Type: TABLE; Schema: empresa5; Owner: sae
--

CREATE TABLE empresa5.ae_recursos_aud (
    id integer NOT NULL,
    id_recurso integer NOT NULL,
    nombre character varying(100) NOT NULL,
    descripcion character varying(1000) NOT NULL,
    fecha_inicio timestamp without time zone NOT NULL,
    fecha_fin timestamp without time zone,
    fecha_inicio_disp timestamp without time zone NOT NULL,
    fecha_fin_disp timestamp without time zone,
    dias_inicio_ventana_intranet integer NOT NULL,
    dias_ventana_intranet integer NOT NULL,
    dias_inicio_ventana_internet integer NOT NULL,
    dias_ventana_internet integer NOT NULL,
    ventana_cupos_minimos integer NOT NULL,
    cant_dias_a_generar integer NOT NULL,
    largo_lista_espera integer,
    fecha_baja timestamp without time zone,
    mostrar_numero_en_llamador boolean NOT NULL,
    visible_internet boolean NOT NULL,
    usar_llamador boolean NOT NULL,
    serie character varying(3),
    sabado_es_habil boolean NOT NULL,
    domingo_es_habil boolean DEFAULT false NOT NULL,
    mostrar_numero_en_ticket boolean NOT NULL,
    mostrar_id_en_ticket boolean,
    fuente_ticket character varying(100) DEFAULT 'Helvetica-Bold'::character varying NOT NULL,
    tamanio_fuente_grande integer DEFAULT 12 NOT NULL,
    tamanio_fuente_normal integer DEFAULT 10 NOT NULL,
    tamanio_fuente_chica integer DEFAULT 8 NOT NULL,
    oficina_id character varying(25),
    direccion character varying(100),
    localidad character varying(100),
    departamento character varying(100),
    telefonos character varying(100),
    horarios character varying(100),
    latitud numeric,
    longitud numeric,
    agenda integer NOT NULL,
    presencial_admite boolean DEFAULT false NOT NULL,
    presencial_cupos integer DEFAULT 0 NOT NULL,
    presencial_lunes boolean DEFAULT false NOT NULL,
    presencial_martes boolean DEFAULT false NOT NULL,
    presencial_miercoles boolean DEFAULT false NOT NULL,
    presencial_jueves boolean DEFAULT false NOT NULL,
    presencial_viernes boolean DEFAULT false NOT NULL,
    presencial_sabado boolean DEFAULT false NOT NULL,
    presencial_domingo boolean DEFAULT false NOT NULL,
    multiple_admite boolean DEFAULT false NOT NULL,
    cambios_admite boolean DEFAULT false NOT NULL,
    cambios_tiempo integer,
    cambios_unidad integer,
    periodo_validacion integer DEFAULT 0 NOT NULL,
    validar_por_ip boolean DEFAULT false NOT NULL,
    cantidad_por_ip integer,
    periodo_por_ip integer,
    ips_sin_validacion character varying(4000) DEFAULT NULL::character varying,
    cancela_tiempo integer DEFAULT 0 NOT NULL,
    cancela_unidad integer DEFAULT 12 NOT NULL,
    cancela_tipo character varying(1) DEFAULT 'I'::character varying NOT NULL,
    mi_perfil_con_hab boolean DEFAULT true NOT NULL,
    mi_perfil_con_tit character varying(200) DEFAULT NULL::character varying,
    mi_perfil_con_cor character varying(500) DEFAULT NULL::character varying,
    mi_perfil_con_lar character varying(3200) DEFAULT NULL::character varying,
    mi_perfil_con_ven integer,
    mi_perfil_can_hab boolean DEFAULT true NOT NULL,
    mi_perfil_can_tit character varying(200) DEFAULT NULL::character varying,
    mi_perfil_can_cor character varying(500) DEFAULT NULL::character varying,
    mi_perfil_can_lar character varying(3200) DEFAULT NULL::character varying,
    mi_perfil_can_ven integer,
    mi_perfil_rec_hab boolean DEFAULT true NOT NULL,
    mi_perfil_rec_tit character varying(200) DEFAULT NULL::character varying,
    mi_perfil_rec_cor character varying(500) DEFAULT NULL::character varying,
    mi_perfil_rec_lar character varying(3200) DEFAULT NULL::character varying,
    mi_perfil_rec_ven integer,
    mi_perfil_rec_hora integer,
    mi_perfil_rec_dias integer,
    reserva_pen_tiempo_max integer,
    reserva_pend_tiempo_max integer,
    fecha_modificacion timestamp without time zone,
    usuario character varying(45),
    version integer NOT NULL,
    tipo_operacion smallint
);


ALTER TABLE empresa5.ae_recursos_aud OWNER TO sae;

--
-- Name: ae_reservas; Type: TABLE; Schema: empresa5; Owner: sae
--

CREATE TABLE empresa5.ae_reservas (
    id integer NOT NULL,
    estado character varying(1) NOT NULL,
    fact timestamp without time zone NOT NULL,
    fcrea timestamp without time zone NOT NULL,
    numero integer,
    observaciones character varying(100),
    origen character varying(1),
    ucancela character varying(255),
    ucrea character varying(255),
    version integer NOT NULL,
    codigo_seguridad character varying(10) DEFAULT '00000'::character varying,
    trazabilidad_guid character varying(25),
    tramite_codigo character varying(10),
    tramite_nombre character varying(100),
    serie character varying(3),
    tcancela character varying(1),
    fcancela timestamp without time zone,
    aetr_id integer,
    ip_origen character varying(16) DEFAULT NULL::character varying,
    flibera timestamp without time zone,
    mi_perfil_notif boolean DEFAULT true NOT NULL,
    notificar boolean DEFAULT true,
    reserva_hija_id integer
);


ALTER TABLE empresa5.ae_reservas OWNER TO sae;

--
-- Name: ae_reservas_disponibilidades; Type: TABLE; Schema: empresa5; Owner: sae
--

CREATE TABLE empresa5.ae_reservas_disponibilidades (
    aers_id integer NOT NULL,
    aedi_id integer NOT NULL
);


ALTER TABLE empresa5.ae_reservas_disponibilidades OWNER TO sae;

--
-- Name: ae_roles_usuario_recurso; Type: TABLE; Schema: empresa5; Owner: sae
--

CREATE TABLE empresa5.ae_roles_usuario_recurso (
    usuario_id integer NOT NULL,
    recurso_id integer NOT NULL,
    roles character varying(4000)
);


ALTER TABLE empresa5.ae_roles_usuario_recurso OWNER TO sae;

--
-- Name: ae_serv_autocomp_por_dato; Type: TABLE; Schema: empresa5; Owner: sae
--

CREATE TABLE empresa5.ae_serv_autocomp_por_dato (
    id integer NOT NULL,
    fecha_desasociacion timestamp without time zone,
    nombre_parametro character varying(50) NOT NULL,
    aeds_id integer NOT NULL,
    aesr_id integer NOT NULL
);


ALTER TABLE empresa5.ae_serv_autocomp_por_dato OWNER TO sae;

--
-- Name: ae_serv_autocompletar; Type: TABLE; Schema: empresa5; Owner: sae
--

CREATE TABLE empresa5.ae_serv_autocompletar (
    id integer NOT NULL,
    descripcion character varying(100) NOT NULL,
    fecha_baja timestamp without time zone,
    host character varying(100),
    nombre character varying(50) NOT NULL,
    servicio character varying(250) NOT NULL
);


ALTER TABLE empresa5.ae_serv_autocompletar OWNER TO sae;

--
-- Name: ae_servicio_por_recurso; Type: TABLE; Schema: empresa5; Owner: sae
--

CREATE TABLE empresa5.ae_servicio_por_recurso (
    id integer NOT NULL,
    fecha_baja timestamp without time zone,
    aeserv_id integer NOT NULL,
    aere_id integer NOT NULL
);


ALTER TABLE empresa5.ae_servicio_por_recurso OWNER TO sae;

--
-- Name: ae_textos; Type: TABLE; Schema: empresa5; Owner: sae
--

CREATE TABLE empresa5.ae_textos (
    codigo character varying(100) NOT NULL,
    idioma character varying(5) NOT NULL,
    texto character varying(4096) NOT NULL
);


ALTER TABLE empresa5.ae_textos OWNER TO sae;

--
-- Name: ae_textos_agenda; Type: TABLE; Schema: empresa5; Owner: sae
--

CREATE TABLE empresa5.ae_textos_agenda (
    id integer NOT NULL,
    texto_paso1 character varying(1000),
    texto_paso2 character varying(1000),
    texto_paso3 character varying(1000),
    texto_sel_recurso character varying(50),
    texto_ticket character varying(1000),
    aeag_id integer NOT NULL,
    texto_correo_conf character varying(1000),
    texto_correo_canc character varying(1000),
    por_defecto boolean DEFAULT false NOT NULL,
    idioma character varying(5) NOT NULL
);


ALTER TABLE empresa5.ae_textos_agenda OWNER TO sae;

--
-- Name: ae_textos_recurso; Type: TABLE; Schema: empresa5; Owner: sae
--

CREATE TABLE empresa5.ae_textos_recurso (
    id integer NOT NULL,
    texto_paso2 character varying(1000),
    texto_paso3 character varying(1000),
    ticket_etiqueta_dos character varying(15),
    ticket_etiqueta_uno character varying(15),
    titulo_ciudadano_en_llamador character varying(255),
    titulo_puesto_en_llamador character varying(255),
    valor_etiqueta_dos character varying(30),
    valor_etiqueta_uno character varying(30),
    aere_id integer NOT NULL,
    idioma character varying(5) NOT NULL
);


ALTER TABLE empresa5.ae_textos_recurso OWNER TO sae;

--
-- Name: ae_tokens_reservas; Type: TABLE; Schema: empresa5; Owner: sae
--

CREATE TABLE empresa5.ae_tokens_reservas (
    id integer NOT NULL,
    token character varying(50) NOT NULL,
    aere_id integer,
    fecha_inicio timestamp without time zone NOT NULL,
    ultima_reserva timestamp without time zone,
    estado character varying(1) NOT NULL,
    cedula character varying(50) NOT NULL,
    nombre character varying(100) NOT NULL,
    correoe character varying(100) NOT NULL,
    tramite character varying(10),
    notas character varying(4000),
    version integer NOT NULL,
    ip_origen character varying(16) DEFAULT NULL::character varying
);


ALTER TABLE empresa5.ae_tokens_reservas OWNER TO sae;

--
-- Name: ae_tramites_agendas; Type: TABLE; Schema: empresa5; Owner: sae
--

CREATE TABLE empresa5.ae_tramites_agendas (
    id integer NOT NULL,
    agenda_id integer NOT NULL,
    tramite_id character varying(25),
    tramite_codigo character varying(10),
    tramite_nombre character varying(100)
);


ALTER TABLE empresa5.ae_tramites_agendas OWNER TO sae;

--
-- Name: ae_validaciones; Type: TABLE; Schema: empresa5; Owner: sae
--

CREATE TABLE empresa5.ae_validaciones (
    id integer NOT NULL,
    descripcion character varying(100) NOT NULL,
    fecha_baja timestamp without time zone,
    host character varying(100),
    nombre character varying(50) NOT NULL,
    servicio character varying(250) NOT NULL
);


ALTER TABLE empresa5.ae_validaciones OWNER TO sae;

--
-- Name: ae_validaciones_por_dato; Type: TABLE; Schema: empresa5; Owner: sae
--

CREATE TABLE empresa5.ae_validaciones_por_dato (
    id integer NOT NULL,
    fecha_desasociacion timestamp without time zone,
    nombre_parametro character varying(50) NOT NULL,
    aeds_id integer NOT NULL,
    aevr_id integer NOT NULL
);


ALTER TABLE empresa5.ae_validaciones_por_dato OWNER TO sae;

--
-- Name: ae_validaciones_por_recurso; Type: TABLE; Schema: empresa5; Owner: sae
--

CREATE TABLE empresa5.ae_validaciones_por_recurso (
    id integer NOT NULL,
    fecha_baja timestamp without time zone,
    orden_ejecucion integer,
    aere_id integer NOT NULL,
    aeva_id integer NOT NULL
);


ALTER TABLE empresa5.ae_validaciones_por_recurso OWNER TO sae;

--
-- Name: ae_valor_constante_val_rec; Type: TABLE; Schema: empresa5; Owner: sae
--

CREATE TABLE empresa5.ae_valor_constante_val_rec (
    id integer NOT NULL,
    fecha_desasociacion timestamp without time zone,
    nombre_constante character varying(50) NOT NULL,
    valor character varying(100) NOT NULL,
    aevr_id integer NOT NULL
);


ALTER TABLE empresa5.ae_valor_constante_val_rec OWNER TO sae;

--
-- Name: ae_valores_del_dato; Type: TABLE; Schema: empresa5; Owner: sae
--

CREATE TABLE empresa5.ae_valores_del_dato (
    id integer NOT NULL,
    etiqueta character varying(50) NOT NULL,
    fecha_desde timestamp without time zone NOT NULL,
    fecha_hasta timestamp without time zone,
    orden integer NOT NULL,
    valor character varying(50) NOT NULL,
    aeds_id integer NOT NULL,
    borrar_flag boolean DEFAULT true NOT NULL
);


ALTER TABLE empresa5.ae_valores_del_dato OWNER TO sae;

--
-- Name: s_ae_accion; Type: SEQUENCE; Schema: empresa5; Owner: sae
--

CREATE SEQUENCE empresa5.s_ae_accion
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa5.s_ae_accion OWNER TO sae;

--
-- Name: s_ae_acciondato; Type: SEQUENCE; Schema: empresa5; Owner: sae
--

CREATE SEQUENCE empresa5.s_ae_acciondato
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa5.s_ae_acciondato OWNER TO sae;

--
-- Name: s_ae_acciones_miperfil; Type: SEQUENCE; Schema: empresa5; Owner: sae
--

CREATE SEQUENCE empresa5.s_ae_acciones_miperfil
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa5.s_ae_acciones_miperfil OWNER TO sae;

--
-- Name: s_ae_accionrecurso; Type: SEQUENCE; Schema: empresa5; Owner: sae
--

CREATE SEQUENCE empresa5.s_ae_accionrecurso
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa5.s_ae_accionrecurso OWNER TO sae;

--
-- Name: s_ae_agenda; Type: SEQUENCE; Schema: empresa5; Owner: sae
--

CREATE SEQUENCE empresa5.s_ae_agenda
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa5.s_ae_agenda OWNER TO sae;

--
-- Name: s_ae_agrupacion_dato; Type: SEQUENCE; Schema: empresa5; Owner: sae
--

CREATE SEQUENCE empresa5.s_ae_agrupacion_dato
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa5.s_ae_agrupacion_dato OWNER TO sae;

--
-- Name: s_ae_anio; Type: SEQUENCE; Schema: empresa5; Owner: sae
--

CREATE SEQUENCE empresa5.s_ae_anio
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa5.s_ae_anio OWNER TO sae;

--
-- Name: s_ae_atencion; Type: SEQUENCE; Schema: empresa5; Owner: sae
--

CREATE SEQUENCE empresa5.s_ae_atencion
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa5.s_ae_atencion OWNER TO sae;

--
-- Name: s_ae_comunicaciones; Type: SEQUENCE; Schema: empresa5; Owner: sae
--

CREATE SEQUENCE empresa5.s_ae_comunicaciones
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa5.s_ae_comunicaciones OWNER TO sae;

--
-- Name: s_ae_constval; Type: SEQUENCE; Schema: empresa5; Owner: sae
--

CREATE SEQUENCE empresa5.s_ae_constval
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa5.s_ae_constval OWNER TO sae;

--
-- Name: s_ae_datoasolicitar; Type: SEQUENCE; Schema: empresa5; Owner: sae
--

CREATE SEQUENCE empresa5.s_ae_datoasolicitar
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa5.s_ae_datoasolicitar OWNER TO sae;

--
-- Name: s_ae_datodelrecurso; Type: SEQUENCE; Schema: empresa5; Owner: sae
--

CREATE SEQUENCE empresa5.s_ae_datodelrecurso
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa5.s_ae_datodelrecurso OWNER TO sae;

--
-- Name: s_ae_datoreserva; Type: SEQUENCE; Schema: empresa5; Owner: sae
--

CREATE SEQUENCE empresa5.s_ae_datoreserva
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa5.s_ae_datoreserva OWNER TO sae;

--
-- Name: s_ae_dia_mes; Type: SEQUENCE; Schema: empresa5; Owner: sae
--

CREATE SEQUENCE empresa5.s_ae_dia_mes
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa5.s_ae_dia_mes OWNER TO sae;

--
-- Name: s_ae_dia_semana; Type: SEQUENCE; Schema: empresa5; Owner: sae
--

CREATE SEQUENCE empresa5.s_ae_dia_semana
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa5.s_ae_dia_semana OWNER TO sae;

--
-- Name: s_ae_disponibilidad; Type: SEQUENCE; Schema: empresa5; Owner: sae
--

CREATE SEQUENCE empresa5.s_ae_disponibilidad
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa5.s_ae_disponibilidad OWNER TO sae;

--
-- Name: s_ae_llamada; Type: SEQUENCE; Schema: empresa5; Owner: sae
--

CREATE SEQUENCE empresa5.s_ae_llamada
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa5.s_ae_llamada OWNER TO sae;

--
-- Name: s_ae_mes; Type: SEQUENCE; Schema: empresa5; Owner: sae
--

CREATE SEQUENCE empresa5.s_ae_mes
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa5.s_ae_mes OWNER TO sae;

--
-- Name: s_ae_paramaccion; Type: SEQUENCE; Schema: empresa5; Owner: sae
--

CREATE SEQUENCE empresa5.s_ae_paramaccion
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa5.s_ae_paramaccion OWNER TO sae;

--
-- Name: s_ae_parametros_autocompletar; Type: SEQUENCE; Schema: empresa5; Owner: sae
--

CREATE SEQUENCE empresa5.s_ae_parametros_autocompletar
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa5.s_ae_parametros_autocompletar OWNER TO sae;

--
-- Name: s_ae_paramval; Type: SEQUENCE; Schema: empresa5; Owner: sae
--

CREATE SEQUENCE empresa5.s_ae_paramval
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa5.s_ae_paramval OWNER TO sae;

--
-- Name: s_ae_plantilla; Type: SEQUENCE; Schema: empresa5; Owner: sae
--

CREATE SEQUENCE empresa5.s_ae_plantilla
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa5.s_ae_plantilla OWNER TO sae;

--
-- Name: s_ae_recurso; Type: SEQUENCE; Schema: empresa5; Owner: sae
--

CREATE SEQUENCE empresa5.s_ae_recurso
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa5.s_ae_recurso OWNER TO sae;

--
-- Name: s_ae_recurso_aud; Type: SEQUENCE; Schema: empresa5; Owner: sae
--

CREATE SEQUENCE empresa5.s_ae_recurso_aud
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa5.s_ae_recurso_aud OWNER TO sae;

--
-- Name: s_ae_reserva; Type: SEQUENCE; Schema: empresa5; Owner: sae
--

CREATE SEQUENCE empresa5.s_ae_reserva
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa5.s_ae_reserva OWNER TO sae;

--
-- Name: s_ae_serv_autocompletar; Type: SEQUENCE; Schema: empresa5; Owner: sae
--

CREATE SEQUENCE empresa5.s_ae_serv_autocompletar
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa5.s_ae_serv_autocompletar OWNER TO sae;

--
-- Name: s_ae_servdato; Type: SEQUENCE; Schema: empresa5; Owner: sae
--

CREATE SEQUENCE empresa5.s_ae_servdato
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa5.s_ae_servdato OWNER TO sae;

--
-- Name: s_ae_servrecurso; Type: SEQUENCE; Schema: empresa5; Owner: sae
--

CREATE SEQUENCE empresa5.s_ae_servrecurso
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa5.s_ae_servrecurso OWNER TO sae;

--
-- Name: s_ae_texto_agenda; Type: SEQUENCE; Schema: empresa5; Owner: sae
--

CREATE SEQUENCE empresa5.s_ae_texto_agenda
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa5.s_ae_texto_agenda OWNER TO sae;

--
-- Name: s_ae_textorecurso; Type: SEQUENCE; Schema: empresa5; Owner: sae
--

CREATE SEQUENCE empresa5.s_ae_textorecurso
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa5.s_ae_textorecurso OWNER TO sae;

--
-- Name: s_ae_token_reserva; Type: SEQUENCE; Schema: empresa5; Owner: sae
--

CREATE SEQUENCE empresa5.s_ae_token_reserva
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa5.s_ae_token_reserva OWNER TO sae;

--
-- Name: s_ae_tramites_agendas; Type: SEQUENCE; Schema: empresa5; Owner: sae
--

CREATE SEQUENCE empresa5.s_ae_tramites_agendas
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa5.s_ae_tramites_agendas OWNER TO sae;

--
-- Name: s_ae_valconstante; Type: SEQUENCE; Schema: empresa5; Owner: sae
--

CREATE SEQUENCE empresa5.s_ae_valconstante
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa5.s_ae_valconstante OWNER TO sae;

--
-- Name: s_ae_valdato; Type: SEQUENCE; Schema: empresa5; Owner: sae
--

CREATE SEQUENCE empresa5.s_ae_valdato
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa5.s_ae_valdato OWNER TO sae;

--
-- Name: s_ae_validacion; Type: SEQUENCE; Schema: empresa5; Owner: sae
--

CREATE SEQUENCE empresa5.s_ae_validacion
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa5.s_ae_validacion OWNER TO sae;

--
-- Name: s_ae_valorposible; Type: SEQUENCE; Schema: empresa5; Owner: sae
--

CREATE SEQUENCE empresa5.s_ae_valorposible
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa5.s_ae_valorposible OWNER TO sae;

--
-- Name: s_ae_valrecurso; Type: SEQUENCE; Schema: empresa5; Owner: sae
--

CREATE SEQUENCE empresa5.s_ae_valrecurso
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empresa5.s_ae_valrecurso OWNER TO sae;

--
-- Name: ae_captchas; Type: TABLE; Schema: global; Owner: sae
--

CREATE TABLE global.ae_captchas (
    clave character varying(100) NOT NULL,
    fase character varying(1024)
);


ALTER TABLE global.ae_captchas OWNER TO sae;

--
-- Name: s_ae_configuracion; Type: SEQUENCE; Schema: global; Owner: sae
--

CREATE SEQUENCE global.s_ae_configuracion
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE global.s_ae_configuracion OWNER TO sae;

--
-- Name: ae_configuracion; Type: TABLE; Schema: global; Owner: sae
--

CREATE TABLE global.ae_configuracion (
    id integer DEFAULT nextval('global.s_ae_configuracion'::regclass) NOT NULL,
    clave character varying(100) NOT NULL,
    valor character varying(1024),
    org_id integer NOT NULL
);


ALTER TABLE global.ae_configuracion OWNER TO sae;

--
-- Name: ae_empresas; Type: TABLE; Schema: global; Owner: sae
--

CREATE TABLE global.ae_empresas (
    id integer NOT NULL,
    nombre character varying(100) NOT NULL,
    datasource character varying(25) NOT NULL,
    fecha_baja timestamp without time zone,
    org_id integer,
    org_codigo character varying(10),
    org_nombre character varying(100),
    unej_id integer,
    unej_codigo character varying(10),
    unej_nombre character varying(100),
    logo bytea,
    cc_finalidad character varying(100) DEFAULT ''::character varying NOT NULL,
    cc_responsable character varying(100) DEFAULT ''::character varying NOT NULL,
    cc_direccion character varying(100) DEFAULT ''::character varying NOT NULL,
    logo_texto character varying(100),
    timezone character varying(100),
    formato_fecha character varying(25),
    formato_hora character varying(25),
    oid character varying(50),
    pie_publico text
);


ALTER TABLE global.ae_empresas OWNER TO sae;

--
-- Name: ae_novedades; Type: TABLE; Schema: global; Owner: sae
--

CREATE TABLE global.ae_novedades (
    fecha_creacion timestamp with time zone NOT NULL,
    fecha_ult_intento timestamp without time zone NOT NULL,
    intentos integer DEFAULT 0 NOT NULL,
    datos character varying(4096) NOT NULL,
    enviado boolean DEFAULT false NOT NULL,
    id integer NOT NULL,
    reserva_id integer,
    empresa_id integer
);


ALTER TABLE global.ae_novedades OWNER TO sae;

--
-- Name: ae_oficinas; Type: TABLE; Schema: global; Owner: sae
--

CREATE TABLE global.ae_oficinas (
    id character varying(25) NOT NULL,
    tramite_id character varying(25) NOT NULL,
    nombre character varying(100) NOT NULL,
    direccion character varying(100),
    localidad character varying(100),
    departamento character varying(100),
    telefonos character varying(100),
    horarios character varying(100),
    comentarios character varying(1000)
);


ALTER TABLE global.ae_oficinas OWNER TO sae;

--
-- Name: ae_organismos; Type: TABLE; Schema: global; Owner: sae
--

CREATE TABLE global.ae_organismos (
    id integer NOT NULL,
    codigo character varying(25) NOT NULL,
    nombre character varying(100) NOT NULL
);


ALTER TABLE global.ae_organismos OWNER TO sae;

--
-- Name: ae_rel_usuarios_empresas; Type: TABLE; Schema: global; Owner: sae
--

CREATE TABLE global.ae_rel_usuarios_empresas (
    usuario_id integer NOT NULL,
    empresa_id integer NOT NULL
);


ALTER TABLE global.ae_rel_usuarios_empresas OWNER TO sae;

--
-- Name: ae_rel_usuarios_organismos; Type: TABLE; Schema: global; Owner: sae
--

CREATE TABLE global.ae_rel_usuarios_organismos (
    usuario_id integer NOT NULL,
    org_id integer NOT NULL
);


ALTER TABLE global.ae_rel_usuarios_organismos OWNER TO sae;

--
-- Name: ae_rel_usuarios_roles; Type: TABLE; Schema: global; Owner: sae
--

CREATE TABLE global.ae_rel_usuarios_roles (
    usuario_id integer NOT NULL,
    empresa_id integer NOT NULL,
    rol_nombre character varying NOT NULL
);


ALTER TABLE global.ae_rel_usuarios_roles OWNER TO sae;

--
-- Name: ae_textos; Type: TABLE; Schema: global; Owner: sae
--

CREATE TABLE global.ae_textos (
    codigo character varying(100) NOT NULL,
    texto character varying(4096) NOT NULL
);


ALTER TABLE global.ae_textos OWNER TO sae;

--
-- Name: ae_tokens; Type: TABLE; Schema: global; Owner: sae
--

CREATE TABLE global.ae_tokens (
    token character varying(25) NOT NULL,
    empresa_id integer NOT NULL,
    nombre character varying(100) NOT NULL,
    email character varying(100) NOT NULL,
    fecha timestamp without time zone NOT NULL
);


ALTER TABLE global.ae_tokens OWNER TO sae;

--
-- Name: ae_tramites; Type: TABLE; Schema: global; Owner: sae
--

CREATE TABLE global.ae_tramites (
    id character varying(25) NOT NULL,
    empresa_id integer,
    nombre character varying(100) NOT NULL,
    quees character varying(1000) NOT NULL,
    temas character varying(1000) NOT NULL,
    online boolean NOT NULL
);


ALTER TABLE global.ae_tramites OWNER TO sae;

--
-- Name: ae_trazabilidad; Type: TABLE; Schema: global; Owner: sae
--

CREATE TABLE global.ae_trazabilidad (
    transaccion_id character varying(100) NOT NULL,
    fecha_creacion timestamp with time zone NOT NULL,
    fecha_ult_intento timestamp without time zone NOT NULL,
    intentos integer DEFAULT 0 NOT NULL,
    datos character varying(4096) NOT NULL,
    enviado boolean DEFAULT false NOT NULL,
    id integer NOT NULL,
    es_cabezal boolean DEFAULT false NOT NULL,
    reserva_id integer,
    empresa_id integer,
    es_final boolean DEFAULT false
);


ALTER TABLE global.ae_trazabilidad OWNER TO sae;

--
-- Name: ae_unidadesejecutoras; Type: TABLE; Schema: global; Owner: sae
--

CREATE TABLE global.ae_unidadesejecutoras (
    id integer NOT NULL,
    codigo character varying(25) NOT NULL,
    nombre character varying(100) NOT NULL
);


ALTER TABLE global.ae_unidadesejecutoras OWNER TO sae;

--
-- Name: ae_usuarios; Type: TABLE; Schema: global; Owner: sae
--

CREATE TABLE global.ae_usuarios (
    id integer NOT NULL,
    codigo character varying(25) NOT NULL,
    nombre character varying(100) NOT NULL,
    fecha_baja timestamp without time zone,
    password character varying(50),
    correoe character varying(100)
);


ALTER TABLE global.ae_usuarios OWNER TO sae;

--
-- Name: s_ae_empresa; Type: SEQUENCE; Schema: global; Owner: sae
--

CREATE SEQUENCE global.s_ae_empresa
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE global.s_ae_empresa OWNER TO sae;

--
-- Name: s_ae_novedades; Type: SEQUENCE; Schema: global; Owner: sae
--

CREATE SEQUENCE global.s_ae_novedades
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE global.s_ae_novedades OWNER TO sae;

--
-- Name: s_ae_trazabilidad; Type: SEQUENCE; Schema: global; Owner: sae
--

CREATE SEQUENCE global.s_ae_trazabilidad
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE global.s_ae_trazabilidad OWNER TO sae;

--
-- Name: s_ae_usuario; Type: SEQUENCE; Schema: global; Owner: sae
--

CREATE SEQUENCE global.s_ae_usuario
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE global.s_ae_usuario OWNER TO sae;

--
-- Data for Name: ae_acciones; Type: TABLE DATA; Schema: empresa1; Owner: sae
--

COPY empresa1.ae_acciones (id, descripcion, fecha_baja, host, nombre, servicio) FROM stdin;
\.


--
-- Data for Name: ae_acciones_miperfil_recurso; Type: TABLE DATA; Schema: empresa1; Owner: sae
--

COPY empresa1.ae_acciones_miperfil_recurso (id, recurso_id, titulo_con_1, url_con_1, destacada_con_1, titulo_con_2, url_con_2, destacada_con_2, titulo_con_3, url_con_3, destacada_con_3, titulo_con_4, url_con_4, destacada_con_4, titulo_con_5, url_con_5, destacada_con_5, titulo_can_1, url_can_1, destacada_can_1, titulo_can_2, url_can_2, destacada_can_2, titulo_can_3, url_can_3, destacada_can_3, titulo_can_4, url_can_4, destacada_can_4, titulo_can_5, url_can_5, destacada_can_5, titulo_rec_1, url_rec_1, destacada_rec_1, titulo_rec_2, url_rec_2, destacada_rec_2, titulo_rec_3, url_rec_3, destacada_rec_3, titulo_rec_4, url_rec_4, destacada_rec_4, titulo_rec_5, url_rec_5, destacada_rec_5) FROM stdin;
\.


--
-- Data for Name: ae_acciones_por_dato; Type: TABLE DATA; Schema: empresa1; Owner: sae
--

COPY empresa1.ae_acciones_por_dato (id, fecha_desasociacion, nombre_parametro, aear_id, aeds_id) FROM stdin;
\.


--
-- Data for Name: ae_acciones_por_recurso; Type: TABLE DATA; Schema: empresa1; Owner: sae
--

COPY empresa1.ae_acciones_por_recurso (id, evento, fecha_baja, orden_ejecucion, aeac_id, aere_id) FROM stdin;
\.


--
-- Data for Name: ae_agendas; Type: TABLE DATA; Schema: empresa1; Owner: sae
--

COPY empresa1.ae_agendas (id, descripcion, fecha_baja, nombre, tramite_id, timezone, idiomas, con_cda, tramite_codigo, publicar_novedades, con_trazabilidad) FROM stdin;
\.


--
-- Data for Name: ae_agrupaciones_datos; Type: TABLE DATA; Schema: empresa1; Owner: sae
--

COPY empresa1.ae_agrupaciones_datos (id, fecha_baja, nombre, orden, aere_id, borrar_flag, etiqueta) FROM stdin;
\.


--
-- Data for Name: ae_anios; Type: TABLE DATA; Schema: empresa1; Owner: sae
--

COPY empresa1.ae_anios (id, anio, aepl_id) FROM stdin;
\.


--
-- Data for Name: ae_atencion; Type: TABLE DATA; Schema: empresa1; Owner: sae
--

COPY empresa1.ae_atencion (id, asistio, duracion, fact, fcrea, funcionario, aers_id) FROM stdin;
\.


--
-- Data for Name: ae_comunicaciones; Type: TABLE DATA; Schema: empresa1; Owner: sae
--

COPY empresa1.ae_comunicaciones (id, tipo_1, tipo_2, destino, recurso_id, reserva_id, token_id, procesado, mensaje) FROM stdin;
\.


--
-- Data for Name: ae_constante_validacion; Type: TABLE DATA; Schema: empresa1; Owner: sae
--

COPY empresa1.ae_constante_validacion (id, fecha_baja, largo, nombre, tipo, aeva_id) FROM stdin;
\.


--
-- Data for Name: ae_datos_a_solicitar; Type: TABLE DATA; Schema: empresa1; Owner: sae
--

COPY empresa1.ae_datos_a_solicitar (id, ancho_despliegue, columna, es_clave, etiqueta, fecha_baja, fila, incluir_en_llamador, incluir_en_reporte, largo, largo_en_llamador, nombre, orden_en_llamador, requerido, texto_ayuda, tipo, aead_id, aere_id, borrar_flag, solo_lectura, incluir_en_novedades) FROM stdin;
\.


--
-- Data for Name: ae_datos_del_recurso; Type: TABLE DATA; Schema: empresa1; Owner: sae
--

COPY empresa1.ae_datos_del_recurso (id, etiqueta, orden, valor, aere_id) FROM stdin;
\.


--
-- Data for Name: ae_datos_reserva; Type: TABLE DATA; Schema: empresa1; Owner: sae
--

COPY empresa1.ae_datos_reserva (id, valor, aeds_id, aers_id) FROM stdin;
\.


--
-- Data for Name: ae_dias_del_mes; Type: TABLE DATA; Schema: empresa1; Owner: sae
--

COPY empresa1.ae_dias_del_mes (id, dia_del_mes, aepl_id) FROM stdin;
\.


--
-- Data for Name: ae_dias_semana; Type: TABLE DATA; Schema: empresa1; Owner: sae
--

COPY empresa1.ae_dias_semana (id, dia_semana, aepl_id) FROM stdin;
\.


--
-- Data for Name: ae_disponibilidades; Type: TABLE DATA; Schema: empresa1; Owner: sae
--

COPY empresa1.ae_disponibilidades (id, cupo, fecha, fecha_baja, hora_fin, hora_inicio, numerador, version, aepl_id, aere_id, presencial) FROM stdin;
\.


--
-- Data for Name: ae_frases_captcha; Type: TABLE DATA; Schema: empresa1; Owner: sae
--

COPY empresa1.ae_frases_captcha (clave, frase, idioma) FROM stdin;
\.


--
-- Data for Name: ae_llamadas; Type: TABLE DATA; Schema: empresa1; Owner: sae
--

COPY empresa1.ae_llamadas (id, etiqueta, fecha, hora, numero, puesto, aere_id, aers_id) FROM stdin;
\.


--
-- Data for Name: ae_meses; Type: TABLE DATA; Schema: empresa1; Owner: sae
--

COPY empresa1.ae_meses (id, mes, aepl_id) FROM stdin;
\.


--
-- Data for Name: ae_parametros_accion; Type: TABLE DATA; Schema: empresa1; Owner: sae
--

COPY empresa1.ae_parametros_accion (id, fecha_baja, largo, nombre, tipo, aeac_id) FROM stdin;
\.


--
-- Data for Name: ae_parametros_autocompletar; Type: TABLE DATA; Schema: empresa1; Owner: sae
--

COPY empresa1.ae_parametros_autocompletar (id, fecha_baja, modo, nombre, tipo, aeserv_id) FROM stdin;
\.


--
-- Data for Name: ae_parametros_validacion; Type: TABLE DATA; Schema: empresa1; Owner: sae
--

COPY empresa1.ae_parametros_validacion (id, fecha_baja, largo, nombre, tipo, aeva_id) FROM stdin;
\.


--
-- Data for Name: ae_plantillas; Type: TABLE DATA; Schema: empresa1; Owner: sae
--

COPY empresa1.ae_plantillas (id, cupo, fecha_baja, frecuencia, generacion_automatica, hora_fin, hora_inicio, aere_id) FROM stdin;
\.


--
-- Data for Name: ae_preguntas_captcha; Type: TABLE DATA; Schema: empresa1; Owner: sae
--

COPY empresa1.ae_preguntas_captcha (clave, pregunta, respuesta, idioma) FROM stdin;
P000001	¿De qué color era el caballo blanco de Artigas?	blanco	es
P000002	¿Cuánto es dos más dos? Responde con palabras	cuatro	es
P000003	Escribe la tercera palabra de: Hoy está lloviendo	lloviendo	es
P000004	¿Cuánto es tres más uno? Responde con palabras	cuatro	es
P000005	Escribe la segunda palabra de: Hoy es Lunes	es	es
P000006	¿Cuánto es uno más uno? Responde con palabras	dos	es
P000007	¿Cuánto es cinco más dos? Responde con palabras	siete	es
P000008	Escribe la primera palabra de: Hoy está soleado	hoy	es
P000009	De los números uno, cuatro y dos, ¿cuál es el menor?	uno	es
P000010	¿Cuántos colores hay en la lista: torta, verde, hotel?	uno	es
P000011	¿Cuál es el segundo dígito de 7101712? Responde con palabras	uno	es
P000012	Si el león es amarillo, ¿de qué color es el león?	amarillo	es
P000013	¿Cuál de los siguientes es un color: libro, nariz, verde, queso?	verde	es
P000014	Si mañana es sábado, ¿qué día es hoy?	viernes	es
P000016	Escribe la segunda palabra de: Ayer tuve frío	tuve	es
P000017	Escribe la última palabra de: me caigo y no me levanto	levanto	es
P000018	Escribe la quinta palabra de: más vale pájaro en mano que cien volando	mano	es
P000020	Escribe la quinta palabra de: cuanto menos se piensa salta la liebre	salta	es
P000021	Un día en la vida, ¿cuántos días son? escribe en letras	uno	es
P000022	Tres tristes tigres comen trigo, ¿qué comen los tigres?	trigo	es
P000023	Si hoy me acuesto, despierto mañana; ¿cuándo me acuesto?	hoy	es
P000024	¿Qué animal es mayor, el mono  o el elefante?	elefante	es
P000025	¿Cuál es el primer mes del año?	enero	es
P000026	¿Cuál es el segundo mes del año?	febrero	es
P000027	¿Cuál es el tercer mes del año?	marzo	es
P000028	¿Cuál es el cuarto mes del año?	abril	es
P000029	¿Cuál es el quinto mes del año?	mayo	es
P000030	¿Cuál es el sexto mes del año?	junio	es
P000031	¿Cuál es el séptimo mes del año?	julio	es
P000032	¿Cuál es el octavo mes del año?	agosto	es
P000033	¿Cuál es el noveno mes del año?	setiembre	es
P000034	¿Cuál es el décimo mes del año?	octubre	es
P000035	¿Cuál es el penúltimo mes del año?	noviembre	es
P000036	¿Cuál es el último mes del año?	diciembre	es
P000037	¿Cuántos días tiene enero? Responde con números	31	es
P000038	¿Cuántos días tiene febrero? Responde con números	28	es
P000039	¿Cuántos días tiene marzo? Responde con números	31	es
P000040	¿Cuántos días tiene abril? Responde con números	30	es
P000041	¿Cuántos días tiene mayo? Responde con números	31	es
P000042	¿Cuántos días tiene junio? Responde con números	30	es
P000043	¿Cuántos días tiene julio? Responde con números	31	es
P000044	¿Cuántos días tiene agosto? Responde con números	31	es
P000045	¿Cuántos días tiene setiembre? Responde con números	30	es
P000046	¿Cuántos días tiene octubre? Responde con números	31	es
P000047	¿Cuántos días tiene noviembre? Responde con números	30	es
P000048	¿Cuántos días tiene diciembre? Responde con números	31	es
P000049	Hola dijo Bartola; ¿quién dijo hola?	Bartola	es
P000050	¿Cuál palabra es un color: amarillo, abejorro, mono?	amarillo	es
P000051	¿Cuál palabra es un color: violeta, araña, morsa?	violeta	es
P000052	¿Cuál palabra es un color: blanco, alce, mosca?	blanco	es
P000053	¿Cuál palabra es un color: azul, almeja, mosquito?	azul	es
P000054	¿Cuál palabra es un color: rojo, ardilla, casa?	rojo	es
P000055	¿Cuál palabra es un color: negro, vaca, auto?	negro	es
\.


--
-- Data for Name: ae_recursos; Type: TABLE DATA; Schema: empresa1; Owner: sae
--

COPY empresa1.ae_recursos (id, cant_dias_a_generar, descripcion, dias_inicio_ventana_internet, dias_inicio_ventana_intranet, dias_ventana_internet, dias_ventana_intranet, fecha_baja, fecha_fin, fecha_fin_disp, fecha_inicio, fecha_inicio_disp, largo_lista_espera, mostrar_numero_en_llamador, mostrar_numero_en_ticket, nombre, sabado_es_habil, serie, usar_llamador, ventana_cupos_minimos, version, visible_internet, aeag_id, oficina_id, direccion, localidad, departamento, telefonos, horarios, latitud, longitud, mostrar_id_en_ticket, domingo_es_habil, presencial_admite, presencial_lunes, presencial_martes, presencial_miercoles, presencial_jueves, presencial_viernes, presencial_sabado, presencial_domingo, presencial_cupos, tamanio_fuente_grande, tamanio_fuente_normal, tamanio_fuente_chica, fuente_ticket, multiple_admite, cambios_admite, cambios_tiempo, cambios_unidad, periodo_validacion, validar_por_ip, cantidad_por_ip, periodo_por_ip, ips_sin_validacion, cancela_tiempo, cancela_unidad, cancela_tipo, mi_perfil_con_hab, mi_perfil_can_hab, mi_perfil_rec_hab, mi_perfil_con_tit, mi_perfil_con_cor, mi_perfil_con_lar, mi_perfil_con_ven, mi_perfil_can_tit, mi_perfil_can_cor, mi_perfil_can_lar, mi_perfil_can_ven, mi_perfil_rec_tit, mi_perfil_rec_cor, mi_perfil_rec_lar, mi_perfil_rec_ven, mi_perfil_rec_hora, mi_perfil_rec_dias) FROM stdin;
\.


--
-- Data for Name: ae_recursos_aud; Type: TABLE DATA; Schema: empresa1; Owner: sae
--

COPY empresa1.ae_recursos_aud (id, id_recurso, nombre, descripcion, fecha_inicio, fecha_fin, fecha_inicio_disp, fecha_fin_disp, dias_inicio_ventana_intranet, dias_ventana_intranet, dias_inicio_ventana_internet, dias_ventana_internet, ventana_cupos_minimos, cant_dias_a_generar, largo_lista_espera, fecha_baja, mostrar_numero_en_llamador, visible_internet, usar_llamador, serie, sabado_es_habil, domingo_es_habil, mostrar_numero_en_ticket, mostrar_id_en_ticket, fuente_ticket, tamanio_fuente_grande, tamanio_fuente_normal, tamanio_fuente_chica, oficina_id, direccion, localidad, departamento, telefonos, horarios, latitud, longitud, agenda, presencial_admite, presencial_cupos, presencial_lunes, presencial_martes, presencial_miercoles, presencial_jueves, presencial_viernes, presencial_sabado, presencial_domingo, multiple_admite, cambios_admite, cambios_tiempo, cambios_unidad, periodo_validacion, validar_por_ip, cantidad_por_ip, periodo_por_ip, ips_sin_validacion, cancela_tiempo, cancela_unidad, cancela_tipo, mi_perfil_con_hab, mi_perfil_con_tit, mi_perfil_con_cor, mi_perfil_con_lar, mi_perfil_con_ven, mi_perfil_can_hab, mi_perfil_can_tit, mi_perfil_can_cor, mi_perfil_can_lar, mi_perfil_can_ven, mi_perfil_rec_hab, mi_perfil_rec_tit, mi_perfil_rec_cor, mi_perfil_rec_lar, mi_perfil_rec_ven, mi_perfil_rec_hora, mi_perfil_rec_dias, reserva_pen_tiempo_max, reserva_pend_tiempo_max, fecha_modificacion, usuario, version, tipo_operacion) FROM stdin;
\.


--
-- Data for Name: ae_reservas; Type: TABLE DATA; Schema: empresa1; Owner: sae
--

COPY empresa1.ae_reservas (id, estado, fact, fcrea, numero, observaciones, origen, ucancela, ucrea, version, codigo_seguridad, trazabilidad_guid, tramite_codigo, tramite_nombre, serie, tcancela, fcancela, aetr_id, ip_origen, flibera, mi_perfil_notif, notificar, reserva_hija_id) FROM stdin;
\.


--
-- Data for Name: ae_reservas_disponibilidades; Type: TABLE DATA; Schema: empresa1; Owner: sae
--

COPY empresa1.ae_reservas_disponibilidades (aers_id, aedi_id) FROM stdin;
\.


--
-- Data for Name: ae_roles_usuario_recurso; Type: TABLE DATA; Schema: empresa1; Owner: sae
--

COPY empresa1.ae_roles_usuario_recurso (usuario_id, recurso_id, roles) FROM stdin;
\.


--
-- Data for Name: ae_serv_autocomp_por_dato; Type: TABLE DATA; Schema: empresa1; Owner: sae
--

COPY empresa1.ae_serv_autocomp_por_dato (id, fecha_desasociacion, nombre_parametro, aeds_id, aesr_id) FROM stdin;
\.


--
-- Data for Name: ae_serv_autocompletar; Type: TABLE DATA; Schema: empresa1; Owner: sae
--

COPY empresa1.ae_serv_autocompletar (id, descripcion, fecha_baja, host, nombre, servicio) FROM stdin;
\.


--
-- Data for Name: ae_servicio_por_recurso; Type: TABLE DATA; Schema: empresa1; Owner: sae
--

COPY empresa1.ae_servicio_por_recurso (id, fecha_baja, aeserv_id, aere_id) FROM stdin;
\.


--
-- Data for Name: ae_textos; Type: TABLE DATA; Schema: empresa1; Owner: sae
--

COPY empresa1.ae_textos (codigo, idioma, texto) FROM stdin;
\.


--
-- Data for Name: ae_textos_agenda; Type: TABLE DATA; Schema: empresa1; Owner: sae
--

COPY empresa1.ae_textos_agenda (id, texto_paso1, texto_paso2, texto_paso3, texto_sel_recurso, texto_ticket, aeag_id, texto_correo_conf, texto_correo_canc, por_defecto, idioma) FROM stdin;
\.


--
-- Data for Name: ae_textos_recurso; Type: TABLE DATA; Schema: empresa1; Owner: sae
--

COPY empresa1.ae_textos_recurso (id, texto_paso2, texto_paso3, ticket_etiqueta_dos, ticket_etiqueta_uno, titulo_ciudadano_en_llamador, titulo_puesto_en_llamador, valor_etiqueta_dos, valor_etiqueta_uno, aere_id, idioma) FROM stdin;
\.


--
-- Data for Name: ae_tokens_reservas; Type: TABLE DATA; Schema: empresa1; Owner: sae
--

COPY empresa1.ae_tokens_reservas (id, token, aere_id, fecha_inicio, ultima_reserva, estado, cedula, nombre, correoe, tramite, notas, version, ip_origen) FROM stdin;
\.


--
-- Data for Name: ae_tramites_agendas; Type: TABLE DATA; Schema: empresa1; Owner: sae
--

COPY empresa1.ae_tramites_agendas (id, agenda_id, tramite_id, tramite_codigo, tramite_nombre) FROM stdin;
\.


--
-- Data for Name: ae_validaciones; Type: TABLE DATA; Schema: empresa1; Owner: sae
--

COPY empresa1.ae_validaciones (id, descripcion, fecha_baja, host, nombre, servicio) FROM stdin;
\.


--
-- Data for Name: ae_validaciones_por_dato; Type: TABLE DATA; Schema: empresa1; Owner: sae
--

COPY empresa1.ae_validaciones_por_dato (id, fecha_desasociacion, nombre_parametro, aeds_id, aevr_id) FROM stdin;
\.


--
-- Data for Name: ae_validaciones_por_recurso; Type: TABLE DATA; Schema: empresa1; Owner: sae
--

COPY empresa1.ae_validaciones_por_recurso (id, fecha_baja, orden_ejecucion, aere_id, aeva_id) FROM stdin;
\.


--
-- Data for Name: ae_valor_constante_val_rec; Type: TABLE DATA; Schema: empresa1; Owner: sae
--

COPY empresa1.ae_valor_constante_val_rec (id, fecha_desasociacion, nombre_constante, valor, aevr_id) FROM stdin;
\.


--
-- Data for Name: ae_valores_del_dato; Type: TABLE DATA; Schema: empresa1; Owner: sae
--

COPY empresa1.ae_valores_del_dato (id, etiqueta, fecha_desde, fecha_hasta, orden, valor, aeds_id, borrar_flag) FROM stdin;
\.


--
-- Data for Name: ae_acciones; Type: TABLE DATA; Schema: empresa2; Owner: sae
--

COPY empresa2.ae_acciones (id, descripcion, fecha_baja, host, nombre, servicio) FROM stdin;
\.


--
-- Data for Name: ae_acciones_miperfil_recurso; Type: TABLE DATA; Schema: empresa2; Owner: sae
--

COPY empresa2.ae_acciones_miperfil_recurso (id, recurso_id, titulo_con_1, url_con_1, destacada_con_1, titulo_con_2, url_con_2, destacada_con_2, titulo_con_3, url_con_3, destacada_con_3, titulo_con_4, url_con_4, destacada_con_4, titulo_con_5, url_con_5, destacada_con_5, titulo_can_1, url_can_1, destacada_can_1, titulo_can_2, url_can_2, destacada_can_2, titulo_can_3, url_can_3, destacada_can_3, titulo_can_4, url_can_4, destacada_can_4, titulo_can_5, url_can_5, destacada_can_5, titulo_rec_1, url_rec_1, destacada_rec_1, titulo_rec_2, url_rec_2, destacada_rec_2, titulo_rec_3, url_rec_3, destacada_rec_3, titulo_rec_4, url_rec_4, destacada_rec_4, titulo_rec_5, url_rec_5, destacada_rec_5) FROM stdin;
1	1	Ir a ubicacion	https://www.google.com.uy/maps/@{latitud},{longitud},15z	t	Cancelar reserva	{linkBase}/cancelarReserva/Paso1.xhtml?e={empresa}&a={agenda}&ri={reserva}	f			f			f			f	Ir a ubicacion	https://www.google.com.uy/maps/@{latitud},{longitud},15z	t			f			f			f			f	Ir a ubicacion	https://www.google.com.uy/maps/@{latitud},{longitud},15z	t	Cancelar reserva	{linkBase}/cancelarReserva/Paso1.xhtml?e={empresa}&a={agenda}&ri={reserva}	f			f			f			f
\.


--
-- Data for Name: ae_acciones_por_dato; Type: TABLE DATA; Schema: empresa2; Owner: sae
--

COPY empresa2.ae_acciones_por_dato (id, fecha_desasociacion, nombre_parametro, aear_id, aeds_id) FROM stdin;
\.


--
-- Data for Name: ae_acciones_por_recurso; Type: TABLE DATA; Schema: empresa2; Owner: sae
--

COPY empresa2.ae_acciones_por_recurso (id, evento, fecha_baja, orden_ejecucion, aeac_id, aere_id) FROM stdin;
\.


--
-- Data for Name: ae_agendas; Type: TABLE DATA; Schema: empresa2; Owner: sae
--

COPY empresa2.ae_agendas (id, descripcion, fecha_baja, nombre, tramite_id, timezone, idiomas, con_cda, tramite_codigo, publicar_novedades, con_trazabilidad) FROM stdin;
1		\N	Agenda de Prueba Agesic	\N	\N	es	f	\N	f	f
\.


--
-- Data for Name: ae_agrupaciones_datos; Type: TABLE DATA; Schema: empresa2; Owner: sae
--

COPY empresa2.ae_agrupaciones_datos (id, fecha_baja, nombre, orden, aere_id, borrar_flag, etiqueta) FROM stdin;
1	\N	datos_personales	1	1	f	Datos personales
\.


--
-- Data for Name: ae_anios; Type: TABLE DATA; Schema: empresa2; Owner: sae
--

COPY empresa2.ae_anios (id, anio, aepl_id) FROM stdin;
\.


--
-- Data for Name: ae_atencion; Type: TABLE DATA; Schema: empresa2; Owner: sae
--

COPY empresa2.ae_atencion (id, asistio, duracion, fact, fcrea, funcionario, aers_id) FROM stdin;
\.


--
-- Data for Name: ae_comunicaciones; Type: TABLE DATA; Schema: empresa2; Owner: sae
--

COPY empresa2.ae_comunicaciones (id, tipo_1, tipo_2, destino, recurso_id, reserva_id, token_id, procesado, mensaje) FROM stdin;
\.


--
-- Data for Name: ae_constante_validacion; Type: TABLE DATA; Schema: empresa2; Owner: sae
--

COPY empresa2.ae_constante_validacion (id, fecha_baja, largo, nombre, tipo, aeva_id) FROM stdin;
\.


--
-- Data for Name: ae_datos_a_solicitar; Type: TABLE DATA; Schema: empresa2; Owner: sae
--

COPY empresa2.ae_datos_a_solicitar (id, ancho_despliegue, columna, es_clave, etiqueta, fecha_baja, fila, incluir_en_llamador, incluir_en_reporte, largo, largo_en_llamador, nombre, orden_en_llamador, requerido, texto_ayuda, tipo, aead_id, aere_id, borrar_flag, solo_lectura, incluir_en_novedades) FROM stdin;
1	100	1	t	Tipo de documento	\N	1	t	t	20	20	TipoDocumento	1	t	\N	LIST	1	1	f	f	f
2	120	1	t	Número de documento	\N	2	t	t	10	10	NroDocumento	2	t	\N	STRING	1	1	f	f	f
3	100	1	f	Correo electrónico	\N	3	f	t	100	150	Mail	3	t	\N	STRING	1	1	f	f	f
\.


--
-- Data for Name: ae_datos_del_recurso; Type: TABLE DATA; Schema: empresa2; Owner: sae
--

COPY empresa2.ae_datos_del_recurso (id, etiqueta, orden, valor, aere_id) FROM stdin;
\.


--
-- Data for Name: ae_datos_reserva; Type: TABLE DATA; Schema: empresa2; Owner: sae
--

COPY empresa2.ae_datos_reserva (id, valor, aeds_id, aers_id) FROM stdin;
\.


--
-- Data for Name: ae_dias_del_mes; Type: TABLE DATA; Schema: empresa2; Owner: sae
--

COPY empresa2.ae_dias_del_mes (id, dia_del_mes, aepl_id) FROM stdin;
\.


--
-- Data for Name: ae_dias_semana; Type: TABLE DATA; Schema: empresa2; Owner: sae
--

COPY empresa2.ae_dias_semana (id, dia_semana, aepl_id) FROM stdin;
\.


--
-- Data for Name: ae_disponibilidades; Type: TABLE DATA; Schema: empresa2; Owner: sae
--

COPY empresa2.ae_disponibilidades (id, cupo, fecha, fecha_baja, hora_fin, hora_inicio, numerador, version, aepl_id, aere_id, presencial) FROM stdin;
1	5	2021-06-30	\N	2021-06-30 09:10:00	2021-06-30 09:00:00	0	0	\N	1	f
2	5	2021-06-30	\N	2021-06-30 09:20:00	2021-06-30 09:10:00	0	0	\N	1	f
3	5	2021-06-30	\N	2021-06-30 09:30:00	2021-06-30 09:20:00	0	0	\N	1	f
4	5	2021-06-30	\N	2021-06-30 09:40:00	2021-06-30 09:30:00	0	0	\N	1	f
5	5	2021-06-30	\N	2021-06-30 09:50:00	2021-06-30 09:40:00	0	0	\N	1	f
6	5	2021-06-30	\N	2021-06-30 10:00:00	2021-06-30 09:50:00	0	0	\N	1	f
7	5	2021-06-30	\N	2021-06-30 10:10:00	2021-06-30 10:00:00	0	0	\N	1	f
8	5	2021-06-30	\N	2021-06-30 10:20:00	2021-06-30 10:10:00	0	0	\N	1	f
9	5	2021-06-30	\N	2021-06-30 10:30:00	2021-06-30 10:20:00	0	0	\N	1	f
10	5	2021-06-30	\N	2021-06-30 10:40:00	2021-06-30 10:30:00	0	0	\N	1	f
11	5	2021-06-30	\N	2021-06-30 10:50:00	2021-06-30 10:40:00	0	0	\N	1	f
12	5	2021-06-30	\N	2021-06-30 11:00:00	2021-06-30 10:50:00	0	0	\N	1	f
13	5	2021-06-30	\N	2021-06-30 11:10:00	2021-06-30 11:00:00	0	0	\N	1	f
14	5	2021-06-30	\N	2021-06-30 11:20:00	2021-06-30 11:10:00	0	0	\N	1	f
15	5	2021-06-30	\N	2021-06-30 11:30:00	2021-06-30 11:20:00	0	0	\N	1	f
16	5	2021-06-30	\N	2021-06-30 11:40:00	2021-06-30 11:30:00	0	0	\N	1	f
17	5	2021-06-30	\N	2021-06-30 11:50:00	2021-06-30 11:40:00	0	0	\N	1	f
18	5	2021-06-30	\N	2021-06-30 12:00:00	2021-06-30 11:50:00	0	0	\N	1	f
19	5	2021-06-30	\N	2021-06-30 12:10:00	2021-06-30 12:00:00	0	0	\N	1	f
20	5	2021-06-30	\N	2021-06-30 12:20:00	2021-06-30 12:10:00	0	0	\N	1	f
21	5	2021-06-30	\N	2021-06-30 12:30:00	2021-06-30 12:20:00	0	0	\N	1	f
22	5	2021-06-30	\N	2021-06-30 12:40:00	2021-06-30 12:30:00	0	0	\N	1	f
23	5	2021-06-30	\N	2021-06-30 12:50:00	2021-06-30 12:40:00	0	0	\N	1	f
24	5	2021-06-30	\N	2021-06-30 13:00:00	2021-06-30 12:50:00	0	0	\N	1	f
25	5	2021-06-30	\N	2021-06-30 13:10:00	2021-06-30 13:00:00	0	0	\N	1	f
26	5	2021-06-30	\N	2021-06-30 13:20:00	2021-06-30 13:10:00	0	0	\N	1	f
27	5	2021-06-30	\N	2021-06-30 13:30:00	2021-06-30 13:20:00	0	0	\N	1	f
28	5	2021-06-30	\N	2021-06-30 13:40:00	2021-06-30 13:30:00	0	0	\N	1	f
29	5	2021-06-30	\N	2021-06-30 13:50:00	2021-06-30 13:40:00	0	0	\N	1	f
30	5	2021-06-30	\N	2021-06-30 14:00:00	2021-06-30 13:50:00	0	0	\N	1	f
31	5	2021-06-30	\N	2021-06-30 14:10:00	2021-06-30 14:00:00	0	0	\N	1	f
32	5	2021-06-30	\N	2021-06-30 14:20:00	2021-06-30 14:10:00	0	0	\N	1	f
33	5	2021-06-30	\N	2021-06-30 14:30:00	2021-06-30 14:20:00	0	0	\N	1	f
34	5	2021-06-30	\N	2021-06-30 14:40:00	2021-06-30 14:30:00	0	0	\N	1	f
35	5	2021-06-30	\N	2021-06-30 14:50:00	2021-06-30 14:40:00	0	0	\N	1	f
36	5	2021-06-30	\N	2021-06-30 15:00:00	2021-06-30 14:50:00	0	0	\N	1	f
37	5	2021-06-30	\N	2021-06-30 15:10:00	2021-06-30 15:00:00	0	0	\N	1	f
38	5	2021-06-30	\N	2021-06-30 15:20:00	2021-06-30 15:10:00	0	0	\N	1	f
39	5	2021-06-30	\N	2021-06-30 15:30:00	2021-06-30 15:20:00	0	0	\N	1	f
40	5	2021-06-30	\N	2021-06-30 15:40:00	2021-06-30 15:30:00	0	0	\N	1	f
41	5	2021-06-30	\N	2021-06-30 15:50:00	2021-06-30 15:40:00	0	0	\N	1	f
42	5	2021-06-30	\N	2021-06-30 16:00:00	2021-06-30 15:50:00	0	0	\N	1	f
43	5	2021-06-30	\N	2021-06-30 16:10:00	2021-06-30 16:00:00	0	0	\N	1	f
44	5	2021-06-30	\N	2021-06-30 16:20:00	2021-06-30 16:10:00	0	0	\N	1	f
45	5	2021-06-30	\N	2021-06-30 16:30:00	2021-06-30 16:20:00	0	0	\N	1	f
46	5	2021-06-30	\N	2021-06-30 16:40:00	2021-06-30 16:30:00	0	0	\N	1	f
47	5	2021-06-30	\N	2021-06-30 16:50:00	2021-06-30 16:40:00	0	0	\N	1	f
48	5	2021-06-30	\N	2021-06-30 17:00:00	2021-06-30 16:50:00	0	0	\N	1	f
49	5	2021-06-30	\N	2021-06-30 17:10:00	2021-06-30 17:00:00	0	0	\N	1	f
50	5	2021-07-01	\N	2021-07-01 09:10:00	2021-07-01 09:00:00	0	0	\N	1	f
51	5	2021-07-01	\N	2021-07-01 09:20:00	2021-07-01 09:10:00	0	0	\N	1	f
52	5	2021-07-01	\N	2021-07-01 09:30:00	2021-07-01 09:20:00	0	0	\N	1	f
53	5	2021-07-01	\N	2021-07-01 09:40:00	2021-07-01 09:30:00	0	0	\N	1	f
54	5	2021-07-01	\N	2021-07-01 09:50:00	2021-07-01 09:40:00	0	0	\N	1	f
55	5	2021-07-01	\N	2021-07-01 10:00:00	2021-07-01 09:50:00	0	0	\N	1	f
56	5	2021-07-01	\N	2021-07-01 10:10:00	2021-07-01 10:00:00	0	0	\N	1	f
57	5	2021-07-01	\N	2021-07-01 10:20:00	2021-07-01 10:10:00	0	0	\N	1	f
58	5	2021-07-01	\N	2021-07-01 10:30:00	2021-07-01 10:20:00	0	0	\N	1	f
59	5	2021-07-01	\N	2021-07-01 10:40:00	2021-07-01 10:30:00	0	0	\N	1	f
60	5	2021-07-01	\N	2021-07-01 10:50:00	2021-07-01 10:40:00	0	0	\N	1	f
61	5	2021-07-01	\N	2021-07-01 11:00:00	2021-07-01 10:50:00	0	0	\N	1	f
62	5	2021-07-01	\N	2021-07-01 11:10:00	2021-07-01 11:00:00	0	0	\N	1	f
63	5	2021-07-01	\N	2021-07-01 11:20:00	2021-07-01 11:10:00	0	0	\N	1	f
64	5	2021-07-01	\N	2021-07-01 11:30:00	2021-07-01 11:20:00	0	0	\N	1	f
65	5	2021-07-01	\N	2021-07-01 11:40:00	2021-07-01 11:30:00	0	0	\N	1	f
66	5	2021-07-01	\N	2021-07-01 11:50:00	2021-07-01 11:40:00	0	0	\N	1	f
67	5	2021-07-01	\N	2021-07-01 12:00:00	2021-07-01 11:50:00	0	0	\N	1	f
68	5	2021-07-01	\N	2021-07-01 12:10:00	2021-07-01 12:00:00	0	0	\N	1	f
69	5	2021-07-01	\N	2021-07-01 12:20:00	2021-07-01 12:10:00	0	0	\N	1	f
70	5	2021-07-01	\N	2021-07-01 12:30:00	2021-07-01 12:20:00	0	0	\N	1	f
71	5	2021-07-01	\N	2021-07-01 12:40:00	2021-07-01 12:30:00	0	0	\N	1	f
72	5	2021-07-01	\N	2021-07-01 12:50:00	2021-07-01 12:40:00	0	0	\N	1	f
73	5	2021-07-01	\N	2021-07-01 13:00:00	2021-07-01 12:50:00	0	0	\N	1	f
74	5	2021-07-01	\N	2021-07-01 13:10:00	2021-07-01 13:00:00	0	0	\N	1	f
75	5	2021-07-01	\N	2021-07-01 13:20:00	2021-07-01 13:10:00	0	0	\N	1	f
76	5	2021-07-01	\N	2021-07-01 13:30:00	2021-07-01 13:20:00	0	0	\N	1	f
77	5	2021-07-01	\N	2021-07-01 13:40:00	2021-07-01 13:30:00	0	0	\N	1	f
78	5	2021-07-01	\N	2021-07-01 13:50:00	2021-07-01 13:40:00	0	0	\N	1	f
79	5	2021-07-01	\N	2021-07-01 14:00:00	2021-07-01 13:50:00	0	0	\N	1	f
80	5	2021-07-01	\N	2021-07-01 14:10:00	2021-07-01 14:00:00	0	0	\N	1	f
81	5	2021-07-01	\N	2021-07-01 14:20:00	2021-07-01 14:10:00	0	0	\N	1	f
82	5	2021-07-01	\N	2021-07-01 14:30:00	2021-07-01 14:20:00	0	0	\N	1	f
83	5	2021-07-01	\N	2021-07-01 14:40:00	2021-07-01 14:30:00	0	0	\N	1	f
84	5	2021-07-01	\N	2021-07-01 14:50:00	2021-07-01 14:40:00	0	0	\N	1	f
85	5	2021-07-01	\N	2021-07-01 15:00:00	2021-07-01 14:50:00	0	0	\N	1	f
86	5	2021-07-01	\N	2021-07-01 15:10:00	2021-07-01 15:00:00	0	0	\N	1	f
87	5	2021-07-01	\N	2021-07-01 15:20:00	2021-07-01 15:10:00	0	0	\N	1	f
88	5	2021-07-01	\N	2021-07-01 15:30:00	2021-07-01 15:20:00	0	0	\N	1	f
89	5	2021-07-01	\N	2021-07-01 15:40:00	2021-07-01 15:30:00	0	0	\N	1	f
90	5	2021-07-01	\N	2021-07-01 15:50:00	2021-07-01 15:40:00	0	0	\N	1	f
91	5	2021-07-01	\N	2021-07-01 16:00:00	2021-07-01 15:50:00	0	0	\N	1	f
92	5	2021-07-01	\N	2021-07-01 16:10:00	2021-07-01 16:00:00	0	0	\N	1	f
93	5	2021-07-01	\N	2021-07-01 16:20:00	2021-07-01 16:10:00	0	0	\N	1	f
94	5	2021-07-01	\N	2021-07-01 16:30:00	2021-07-01 16:20:00	0	0	\N	1	f
95	5	2021-07-01	\N	2021-07-01 16:40:00	2021-07-01 16:30:00	0	0	\N	1	f
96	5	2021-07-01	\N	2021-07-01 16:50:00	2021-07-01 16:40:00	0	0	\N	1	f
97	5	2021-07-01	\N	2021-07-01 17:00:00	2021-07-01 16:50:00	0	0	\N	1	f
98	5	2021-07-01	\N	2021-07-01 17:10:00	2021-07-01 17:00:00	0	0	\N	1	f
99	5	2021-07-02	\N	2021-07-02 09:10:00	2021-07-02 09:00:00	0	0	\N	1	f
100	5	2021-07-02	\N	2021-07-02 09:20:00	2021-07-02 09:10:00	0	0	\N	1	f
101	5	2021-07-02	\N	2021-07-02 09:30:00	2021-07-02 09:20:00	0	0	\N	1	f
102	5	2021-07-02	\N	2021-07-02 09:40:00	2021-07-02 09:30:00	0	0	\N	1	f
103	5	2021-07-02	\N	2021-07-02 09:50:00	2021-07-02 09:40:00	0	0	\N	1	f
104	5	2021-07-02	\N	2021-07-02 10:00:00	2021-07-02 09:50:00	0	0	\N	1	f
105	5	2021-07-02	\N	2021-07-02 10:10:00	2021-07-02 10:00:00	0	0	\N	1	f
106	5	2021-07-02	\N	2021-07-02 10:20:00	2021-07-02 10:10:00	0	0	\N	1	f
107	5	2021-07-02	\N	2021-07-02 10:30:00	2021-07-02 10:20:00	0	0	\N	1	f
108	5	2021-07-02	\N	2021-07-02 10:40:00	2021-07-02 10:30:00	0	0	\N	1	f
109	5	2021-07-02	\N	2021-07-02 10:50:00	2021-07-02 10:40:00	0	0	\N	1	f
110	5	2021-07-02	\N	2021-07-02 11:00:00	2021-07-02 10:50:00	0	0	\N	1	f
111	5	2021-07-02	\N	2021-07-02 11:10:00	2021-07-02 11:00:00	0	0	\N	1	f
112	5	2021-07-02	\N	2021-07-02 11:20:00	2021-07-02 11:10:00	0	0	\N	1	f
113	5	2021-07-02	\N	2021-07-02 11:30:00	2021-07-02 11:20:00	0	0	\N	1	f
114	5	2021-07-02	\N	2021-07-02 11:40:00	2021-07-02 11:30:00	0	0	\N	1	f
115	5	2021-07-02	\N	2021-07-02 11:50:00	2021-07-02 11:40:00	0	0	\N	1	f
116	5	2021-07-02	\N	2021-07-02 12:00:00	2021-07-02 11:50:00	0	0	\N	1	f
117	5	2021-07-02	\N	2021-07-02 12:10:00	2021-07-02 12:00:00	0	0	\N	1	f
118	5	2021-07-02	\N	2021-07-02 12:20:00	2021-07-02 12:10:00	0	0	\N	1	f
119	5	2021-07-02	\N	2021-07-02 12:30:00	2021-07-02 12:20:00	0	0	\N	1	f
120	5	2021-07-02	\N	2021-07-02 12:40:00	2021-07-02 12:30:00	0	0	\N	1	f
121	5	2021-07-02	\N	2021-07-02 12:50:00	2021-07-02 12:40:00	0	0	\N	1	f
122	5	2021-07-02	\N	2021-07-02 13:00:00	2021-07-02 12:50:00	0	0	\N	1	f
123	5	2021-07-02	\N	2021-07-02 13:10:00	2021-07-02 13:00:00	0	0	\N	1	f
124	5	2021-07-02	\N	2021-07-02 13:20:00	2021-07-02 13:10:00	0	0	\N	1	f
125	5	2021-07-02	\N	2021-07-02 13:30:00	2021-07-02 13:20:00	0	0	\N	1	f
126	5	2021-07-02	\N	2021-07-02 13:40:00	2021-07-02 13:30:00	0	0	\N	1	f
127	5	2021-07-02	\N	2021-07-02 13:50:00	2021-07-02 13:40:00	0	0	\N	1	f
128	5	2021-07-02	\N	2021-07-02 14:00:00	2021-07-02 13:50:00	0	0	\N	1	f
129	5	2021-07-02	\N	2021-07-02 14:10:00	2021-07-02 14:00:00	0	0	\N	1	f
130	5	2021-07-02	\N	2021-07-02 14:20:00	2021-07-02 14:10:00	0	0	\N	1	f
131	5	2021-07-02	\N	2021-07-02 14:30:00	2021-07-02 14:20:00	0	0	\N	1	f
132	5	2021-07-02	\N	2021-07-02 14:40:00	2021-07-02 14:30:00	0	0	\N	1	f
133	5	2021-07-02	\N	2021-07-02 14:50:00	2021-07-02 14:40:00	0	0	\N	1	f
134	5	2021-07-02	\N	2021-07-02 15:00:00	2021-07-02 14:50:00	0	0	\N	1	f
135	5	2021-07-02	\N	2021-07-02 15:10:00	2021-07-02 15:00:00	0	0	\N	1	f
136	5	2021-07-02	\N	2021-07-02 15:20:00	2021-07-02 15:10:00	0	0	\N	1	f
137	5	2021-07-02	\N	2021-07-02 15:30:00	2021-07-02 15:20:00	0	0	\N	1	f
138	5	2021-07-02	\N	2021-07-02 15:40:00	2021-07-02 15:30:00	0	0	\N	1	f
139	5	2021-07-02	\N	2021-07-02 15:50:00	2021-07-02 15:40:00	0	0	\N	1	f
140	5	2021-07-02	\N	2021-07-02 16:00:00	2021-07-02 15:50:00	0	0	\N	1	f
141	5	2021-07-02	\N	2021-07-02 16:10:00	2021-07-02 16:00:00	0	0	\N	1	f
142	5	2021-07-02	\N	2021-07-02 16:20:00	2021-07-02 16:10:00	0	0	\N	1	f
143	5	2021-07-02	\N	2021-07-02 16:30:00	2021-07-02 16:20:00	0	0	\N	1	f
144	5	2021-07-02	\N	2021-07-02 16:40:00	2021-07-02 16:30:00	0	0	\N	1	f
145	5	2021-07-02	\N	2021-07-02 16:50:00	2021-07-02 16:40:00	0	0	\N	1	f
146	5	2021-07-02	\N	2021-07-02 17:00:00	2021-07-02 16:50:00	0	0	\N	1	f
147	5	2021-07-02	\N	2021-07-02 17:10:00	2021-07-02 17:00:00	0	0	\N	1	f
148	5	2021-07-05	\N	2021-07-05 09:10:00	2021-07-05 09:00:00	0	0	\N	1	f
149	5	2021-07-05	\N	2021-07-05 09:20:00	2021-07-05 09:10:00	0	0	\N	1	f
150	5	2021-07-05	\N	2021-07-05 09:30:00	2021-07-05 09:20:00	0	0	\N	1	f
151	5	2021-07-05	\N	2021-07-05 09:40:00	2021-07-05 09:30:00	0	0	\N	1	f
152	5	2021-07-05	\N	2021-07-05 09:50:00	2021-07-05 09:40:00	0	0	\N	1	f
153	5	2021-07-05	\N	2021-07-05 10:00:00	2021-07-05 09:50:00	0	0	\N	1	f
154	5	2021-07-05	\N	2021-07-05 10:10:00	2021-07-05 10:00:00	0	0	\N	1	f
155	5	2021-07-05	\N	2021-07-05 10:20:00	2021-07-05 10:10:00	0	0	\N	1	f
156	5	2021-07-05	\N	2021-07-05 10:30:00	2021-07-05 10:20:00	0	0	\N	1	f
157	5	2021-07-05	\N	2021-07-05 10:40:00	2021-07-05 10:30:00	0	0	\N	1	f
158	5	2021-07-05	\N	2021-07-05 10:50:00	2021-07-05 10:40:00	0	0	\N	1	f
159	5	2021-07-05	\N	2021-07-05 11:00:00	2021-07-05 10:50:00	0	0	\N	1	f
160	5	2021-07-05	\N	2021-07-05 11:10:00	2021-07-05 11:00:00	0	0	\N	1	f
161	5	2021-07-05	\N	2021-07-05 11:20:00	2021-07-05 11:10:00	0	0	\N	1	f
162	5	2021-07-05	\N	2021-07-05 11:30:00	2021-07-05 11:20:00	0	0	\N	1	f
163	5	2021-07-05	\N	2021-07-05 11:40:00	2021-07-05 11:30:00	0	0	\N	1	f
164	5	2021-07-05	\N	2021-07-05 11:50:00	2021-07-05 11:40:00	0	0	\N	1	f
165	5	2021-07-05	\N	2021-07-05 12:00:00	2021-07-05 11:50:00	0	0	\N	1	f
166	5	2021-07-05	\N	2021-07-05 12:10:00	2021-07-05 12:00:00	0	0	\N	1	f
167	5	2021-07-05	\N	2021-07-05 12:20:00	2021-07-05 12:10:00	0	0	\N	1	f
168	5	2021-07-05	\N	2021-07-05 12:30:00	2021-07-05 12:20:00	0	0	\N	1	f
169	5	2021-07-05	\N	2021-07-05 12:40:00	2021-07-05 12:30:00	0	0	\N	1	f
170	5	2021-07-05	\N	2021-07-05 12:50:00	2021-07-05 12:40:00	0	0	\N	1	f
171	5	2021-07-05	\N	2021-07-05 13:00:00	2021-07-05 12:50:00	0	0	\N	1	f
172	5	2021-07-05	\N	2021-07-05 13:10:00	2021-07-05 13:00:00	0	0	\N	1	f
173	5	2021-07-05	\N	2021-07-05 13:20:00	2021-07-05 13:10:00	0	0	\N	1	f
174	5	2021-07-05	\N	2021-07-05 13:30:00	2021-07-05 13:20:00	0	0	\N	1	f
175	5	2021-07-05	\N	2021-07-05 13:40:00	2021-07-05 13:30:00	0	0	\N	1	f
176	5	2021-07-05	\N	2021-07-05 13:50:00	2021-07-05 13:40:00	0	0	\N	1	f
177	5	2021-07-05	\N	2021-07-05 14:00:00	2021-07-05 13:50:00	0	0	\N	1	f
178	5	2021-07-05	\N	2021-07-05 14:10:00	2021-07-05 14:00:00	0	0	\N	1	f
179	5	2021-07-05	\N	2021-07-05 14:20:00	2021-07-05 14:10:00	0	0	\N	1	f
180	5	2021-07-05	\N	2021-07-05 14:30:00	2021-07-05 14:20:00	0	0	\N	1	f
181	5	2021-07-05	\N	2021-07-05 14:40:00	2021-07-05 14:30:00	0	0	\N	1	f
182	5	2021-07-05	\N	2021-07-05 14:50:00	2021-07-05 14:40:00	0	0	\N	1	f
183	5	2021-07-05	\N	2021-07-05 15:00:00	2021-07-05 14:50:00	0	0	\N	1	f
184	5	2021-07-05	\N	2021-07-05 15:10:00	2021-07-05 15:00:00	0	0	\N	1	f
185	5	2021-07-05	\N	2021-07-05 15:20:00	2021-07-05 15:10:00	0	0	\N	1	f
186	5	2021-07-05	\N	2021-07-05 15:30:00	2021-07-05 15:20:00	0	0	\N	1	f
187	5	2021-07-05	\N	2021-07-05 15:40:00	2021-07-05 15:30:00	0	0	\N	1	f
188	5	2021-07-05	\N	2021-07-05 15:50:00	2021-07-05 15:40:00	0	0	\N	1	f
189	5	2021-07-05	\N	2021-07-05 16:00:00	2021-07-05 15:50:00	0	0	\N	1	f
190	5	2021-07-05	\N	2021-07-05 16:10:00	2021-07-05 16:00:00	0	0	\N	1	f
191	5	2021-07-05	\N	2021-07-05 16:20:00	2021-07-05 16:10:00	0	0	\N	1	f
192	5	2021-07-05	\N	2021-07-05 16:30:00	2021-07-05 16:20:00	0	0	\N	1	f
193	5	2021-07-05	\N	2021-07-05 16:40:00	2021-07-05 16:30:00	0	0	\N	1	f
194	5	2021-07-05	\N	2021-07-05 16:50:00	2021-07-05 16:40:00	0	0	\N	1	f
195	5	2021-07-05	\N	2021-07-05 17:00:00	2021-07-05 16:50:00	0	0	\N	1	f
196	5	2021-07-05	\N	2021-07-05 17:10:00	2021-07-05 17:00:00	0	0	\N	1	f
\.


--
-- Data for Name: ae_frases_captcha; Type: TABLE DATA; Schema: empresa2; Owner: sae
--

COPY empresa2.ae_frases_captcha (clave, frase, idioma) FROM stdin;
\.


--
-- Data for Name: ae_llamadas; Type: TABLE DATA; Schema: empresa2; Owner: sae
--

COPY empresa2.ae_llamadas (id, etiqueta, fecha, hora, numero, puesto, aere_id, aers_id) FROM stdin;
\.


--
-- Data for Name: ae_meses; Type: TABLE DATA; Schema: empresa2; Owner: sae
--

COPY empresa2.ae_meses (id, mes, aepl_id) FROM stdin;
\.


--
-- Data for Name: ae_parametros_accion; Type: TABLE DATA; Schema: empresa2; Owner: sae
--

COPY empresa2.ae_parametros_accion (id, fecha_baja, largo, nombre, tipo, aeac_id) FROM stdin;
\.


--
-- Data for Name: ae_parametros_autocompletar; Type: TABLE DATA; Schema: empresa2; Owner: sae
--

COPY empresa2.ae_parametros_autocompletar (id, fecha_baja, modo, nombre, tipo, aeserv_id) FROM stdin;
\.


--
-- Data for Name: ae_parametros_validacion; Type: TABLE DATA; Schema: empresa2; Owner: sae
--

COPY empresa2.ae_parametros_validacion (id, fecha_baja, largo, nombre, tipo, aeva_id) FROM stdin;
\.


--
-- Data for Name: ae_plantillas; Type: TABLE DATA; Schema: empresa2; Owner: sae
--

COPY empresa2.ae_plantillas (id, cupo, fecha_baja, frecuencia, generacion_automatica, hora_fin, hora_inicio, aere_id) FROM stdin;
\.


--
-- Data for Name: ae_preguntas_captcha; Type: TABLE DATA; Schema: empresa2; Owner: sae
--

COPY empresa2.ae_preguntas_captcha (clave, pregunta, respuesta, idioma) FROM stdin;
P000001	¿De qué color era el caballo blanco de Artigas?	blanco	es
P000002	¿Cuánto es dos más dos? Responde con palabras	cuatro	es
P000003	Escribe la tercera palabra de: Hoy está lloviendo	lloviendo	es
P000004	¿Cuánto es tres más uno? Responde con palabras	cuatro	es
P000005	Escribe la segunda palabra de: Hoy es Lunes	es	es
P000006	¿Cuánto es uno más uno? Responde con palabras	dos	es
P000007	¿Cuánto es cinco más dos? Responde con palabras	siete	es
P000008	Escribe la primera palabra de: Hoy está soleado	hoy	es
P000009	De los números uno, cuatro y dos, ¿cuál es el menor?	uno	es
P000010	¿Cuántos colores hay en la lista: torta, verde, hotel?	uno	es
P000011	¿Cuál es el segundo dígito de 7101712? Responde con palabras	uno	es
P000012	Si el león es amarillo, ¿de qué color es el león?	amarillo	es
P000013	¿Cuál de los siguientes es un color: libro, nariz, verde, queso?	verde	es
P000014	Si mañana es sábado, ¿qué día es hoy?	viernes	es
P000016	Escribe la segunda palabra de: Ayer tuve frío	tuve	es
P000017	Escribe la última palabra de: me caigo y no me levanto	levanto	es
P000018	Escribe la quinta palabra de: más vale pájaro en mano que cien volando	mano	es
P000020	Escribe la quinta palabra de: cuanto menos se piensa salta la liebre	salta	es
P000021	Un día en la vida, ¿cuántos días son? escribe en letras	uno	es
P000022	Tres tristes tigres comen trigo, ¿qué comen los tigres?	trigo	es
P000023	Si hoy me acuesto, despierto mañana; ¿cuándo me acuesto?	hoy	es
P000024	¿Qué animal es mayor, el mono  o el elefante?	elefante	es
P000025	¿Cuál es el primer mes del año?	enero	es
P000026	¿Cuál es el segundo mes del año?	febrero	es
P000027	¿Cuál es el tercer mes del año?	marzo	es
P000028	¿Cuál es el cuarto mes del año?	abril	es
P000029	¿Cuál es el quinto mes del año?	mayo	es
P000030	¿Cuál es el sexto mes del año?	junio	es
P000031	¿Cuál es el séptimo mes del año?	julio	es
P000032	¿Cuál es el octavo mes del año?	agosto	es
P000033	¿Cuál es el noveno mes del año?	setiembre	es
P000034	¿Cuál es el décimo mes del año?	octubre	es
P000035	¿Cuál es el penúltimo mes del año?	noviembre	es
P000036	¿Cuál es el último mes del año?	diciembre	es
P000037	¿Cuántos días tiene enero? Responde con números	31	es
P000038	¿Cuántos días tiene febrero? Responde con números	28	es
P000039	¿Cuántos días tiene marzo? Responde con números	31	es
P000040	¿Cuántos días tiene abril? Responde con números	30	es
P000041	¿Cuántos días tiene mayo? Responde con números	31	es
P000042	¿Cuántos días tiene junio? Responde con números	30	es
P000043	¿Cuántos días tiene julio? Responde con números	31	es
P000044	¿Cuántos días tiene agosto? Responde con números	31	es
P000045	¿Cuántos días tiene setiembre? Responde con números	30	es
P000046	¿Cuántos días tiene octubre? Responde con números	31	es
P000047	¿Cuántos días tiene noviembre? Responde con números	30	es
P000048	¿Cuántos días tiene diciembre? Responde con números	31	es
P000049	Hola dijo Bartola; ¿quién dijo hola?	Bartola	es
P000050	¿Cuál palabra es un color: amarillo, abejorro, mono?	amarillo	es
P000051	¿Cuál palabra es un color: violeta, araña, morsa?	violeta	es
P000052	¿Cuál palabra es un color: blanco, alce, mosca?	blanco	es
P000053	¿Cuál palabra es un color: azul, almeja, mosquito?	azul	es
P000054	¿Cuál palabra es un color: rojo, ardilla, casa?	rojo	es
P000055	¿Cuál palabra es un color: negro, vaca, auto?	negro	es
\.


--
-- Data for Name: ae_recursos; Type: TABLE DATA; Schema: empresa2; Owner: sae
--

COPY empresa2.ae_recursos (id, cant_dias_a_generar, descripcion, dias_inicio_ventana_internet, dias_inicio_ventana_intranet, dias_ventana_internet, dias_ventana_intranet, fecha_baja, fecha_fin, fecha_fin_disp, fecha_inicio, fecha_inicio_disp, largo_lista_espera, mostrar_numero_en_llamador, mostrar_numero_en_ticket, nombre, sabado_es_habil, serie, usar_llamador, ventana_cupos_minimos, version, visible_internet, aeag_id, oficina_id, direccion, localidad, departamento, telefonos, horarios, latitud, longitud, mostrar_id_en_ticket, domingo_es_habil, presencial_admite, presencial_lunes, presencial_martes, presencial_miercoles, presencial_jueves, presencial_viernes, presencial_sabado, presencial_domingo, presencial_cupos, tamanio_fuente_grande, tamanio_fuente_normal, tamanio_fuente_chica, fuente_ticket, multiple_admite, cambios_admite, cambios_tiempo, cambios_unidad, periodo_validacion, validar_por_ip, cantidad_por_ip, periodo_por_ip, ips_sin_validacion, cancela_tiempo, cancela_unidad, cancela_tipo, mi_perfil_con_hab, mi_perfil_can_hab, mi_perfil_rec_hab, mi_perfil_con_tit, mi_perfil_con_cor, mi_perfil_con_lar, mi_perfil_con_ven, mi_perfil_can_tit, mi_perfil_can_cor, mi_perfil_can_lar, mi_perfil_can_ven, mi_perfil_rec_tit, mi_perfil_rec_cor, mi_perfil_rec_lar, mi_perfil_rec_ven, mi_perfil_rec_hora, mi_perfil_rec_dias) FROM stdin;
1	5	Recurso de Prueba Agesic	0	0	3	5	\N	2022-02-01 23:59:59	2022-02-01 23:59:59	2021-06-28 00:00:00	2021-06-28 00:00:00	1	f	f	Recurso de Prueba Agesic	f	A	t	0	2	t	1	0						\N	\N	f	f	t	f	t	f	t	f	f	f	2	12	10	6	helvetica	t	f	\N	\N	3	f	\N	\N	\N	1	5	I	f	f	f				0				0				0	0	0
\.


--
-- Data for Name: ae_recursos_aud; Type: TABLE DATA; Schema: empresa2; Owner: sae
--

COPY empresa2.ae_recursos_aud (id, id_recurso, nombre, descripcion, fecha_inicio, fecha_fin, fecha_inicio_disp, fecha_fin_disp, dias_inicio_ventana_intranet, dias_ventana_intranet, dias_inicio_ventana_internet, dias_ventana_internet, ventana_cupos_minimos, cant_dias_a_generar, largo_lista_espera, fecha_baja, mostrar_numero_en_llamador, visible_internet, usar_llamador, serie, sabado_es_habil, domingo_es_habil, mostrar_numero_en_ticket, mostrar_id_en_ticket, fuente_ticket, tamanio_fuente_grande, tamanio_fuente_normal, tamanio_fuente_chica, oficina_id, direccion, localidad, departamento, telefonos, horarios, latitud, longitud, agenda, presencial_admite, presencial_cupos, presencial_lunes, presencial_martes, presencial_miercoles, presencial_jueves, presencial_viernes, presencial_sabado, presencial_domingo, multiple_admite, cambios_admite, cambios_tiempo, cambios_unidad, periodo_validacion, validar_por_ip, cantidad_por_ip, periodo_por_ip, ips_sin_validacion, cancela_tiempo, cancela_unidad, cancela_tipo, mi_perfil_con_hab, mi_perfil_con_tit, mi_perfil_con_cor, mi_perfil_con_lar, mi_perfil_con_ven, mi_perfil_can_hab, mi_perfil_can_tit, mi_perfil_can_cor, mi_perfil_can_lar, mi_perfil_can_ven, mi_perfil_rec_hab, mi_perfil_rec_tit, mi_perfil_rec_cor, mi_perfil_rec_lar, mi_perfil_rec_ven, mi_perfil_rec_hora, mi_perfil_rec_dias, reserva_pen_tiempo_max, reserva_pend_tiempo_max, fecha_modificacion, usuario, version, tipo_operacion) FROM stdin;
1	1	Recurso de Prueba Agesic	Recurso de Prueba Agesic	2021-06-28 00:00:00	2022-02-01 23:59:59	2021-06-28 00:00:00	2022-02-01 23:59:59	0	5	0	3	0	5	1	\N	f	t	t	A	f	f	f	f	helvetica	12	10	6	0						\N	\N	1	t	2	f	t	f	t	f	f	f	t	f	\N	\N	3	f	\N	\N	\N	1	5	I	f				0	f				0	f				0	0	0	\N	\N	2021-06-28 20:27:20.748	40446448	0	0
\.


--
-- Data for Name: ae_reservas; Type: TABLE DATA; Schema: empresa2; Owner: sae
--

COPY empresa2.ae_reservas (id, estado, fact, fcrea, numero, observaciones, origen, ucancela, ucrea, version, codigo_seguridad, trazabilidad_guid, tramite_codigo, tramite_nombre, serie, tcancela, fcancela, aetr_id, ip_origen, flibera, mi_perfil_notif, notificar, reserva_hija_id) FROM stdin;
\.


--
-- Data for Name: ae_reservas_disponibilidades; Type: TABLE DATA; Schema: empresa2; Owner: sae
--

COPY empresa2.ae_reservas_disponibilidades (aers_id, aedi_id) FROM stdin;
\.


--
-- Data for Name: ae_roles_usuario_recurso; Type: TABLE DATA; Schema: empresa2; Owner: sae
--

COPY empresa2.ae_roles_usuario_recurso (usuario_id, recurso_id, roles) FROM stdin;
\.


--
-- Data for Name: ae_serv_autocomp_por_dato; Type: TABLE DATA; Schema: empresa2; Owner: sae
--

COPY empresa2.ae_serv_autocomp_por_dato (id, fecha_desasociacion, nombre_parametro, aeds_id, aesr_id) FROM stdin;
\.


--
-- Data for Name: ae_serv_autocompletar; Type: TABLE DATA; Schema: empresa2; Owner: sae
--

COPY empresa2.ae_serv_autocompletar (id, descripcion, fecha_baja, host, nombre, servicio) FROM stdin;
\.


--
-- Data for Name: ae_servicio_por_recurso; Type: TABLE DATA; Schema: empresa2; Owner: sae
--

COPY empresa2.ae_servicio_por_recurso (id, fecha_baja, aeserv_id, aere_id) FROM stdin;
\.


--
-- Data for Name: ae_textos; Type: TABLE DATA; Schema: empresa2; Owner: sae
--

COPY empresa2.ae_textos (codigo, idioma, texto) FROM stdin;
\.


--
-- Data for Name: ae_textos_agenda; Type: TABLE DATA; Schema: empresa2; Owner: sae
--

COPY empresa2.ae_textos_agenda (id, texto_paso1, texto_paso2, texto_paso3, texto_sel_recurso, texto_ticket, aeag_id, texto_correo_conf, texto_correo_canc, por_defecto, idioma) FROM stdin;
\.


--
-- Data for Name: ae_textos_recurso; Type: TABLE DATA; Schema: empresa2; Owner: sae
--

COPY empresa2.ae_textos_recurso (id, texto_paso2, texto_paso3, ticket_etiqueta_dos, ticket_etiqueta_uno, titulo_ciudadano_en_llamador, titulo_puesto_en_llamador, valor_etiqueta_dos, valor_etiqueta_uno, aere_id, idioma) FROM stdin;
\.


--
-- Data for Name: ae_tokens_reservas; Type: TABLE DATA; Schema: empresa2; Owner: sae
--

COPY empresa2.ae_tokens_reservas (id, token, aere_id, fecha_inicio, ultima_reserva, estado, cedula, nombre, correoe, tramite, notas, version, ip_origen) FROM stdin;
\.


--
-- Data for Name: ae_tramites_agendas; Type: TABLE DATA; Schema: empresa2; Owner: sae
--

COPY empresa2.ae_tramites_agendas (id, agenda_id, tramite_id, tramite_codigo, tramite_nombre) FROM stdin;
1	1	0	1	1
\.


--
-- Data for Name: ae_validaciones; Type: TABLE DATA; Schema: empresa2; Owner: sae
--

COPY empresa2.ae_validaciones (id, descripcion, fecha_baja, host, nombre, servicio) FROM stdin;
\.


--
-- Data for Name: ae_validaciones_por_dato; Type: TABLE DATA; Schema: empresa2; Owner: sae
--

COPY empresa2.ae_validaciones_por_dato (id, fecha_desasociacion, nombre_parametro, aeds_id, aevr_id) FROM stdin;
\.


--
-- Data for Name: ae_validaciones_por_recurso; Type: TABLE DATA; Schema: empresa2; Owner: sae
--

COPY empresa2.ae_validaciones_por_recurso (id, fecha_baja, orden_ejecucion, aere_id, aeva_id) FROM stdin;
\.


--
-- Data for Name: ae_valor_constante_val_rec; Type: TABLE DATA; Schema: empresa2; Owner: sae
--

COPY empresa2.ae_valor_constante_val_rec (id, fecha_desasociacion, nombre_constante, valor, aevr_id) FROM stdin;
\.


--
-- Data for Name: ae_valores_del_dato; Type: TABLE DATA; Schema: empresa2; Owner: sae
--

COPY empresa2.ae_valores_del_dato (id, etiqueta, fecha_desde, fecha_hasta, orden, valor, aeds_id, borrar_flag) FROM stdin;
1	Cédula de Identidad	2021-06-28 00:00:00	2022-02-01 23:59:59	1	CI	1	f
2	Pasaporte	2021-06-28 00:00:00	2022-02-01 23:59:59	2	P	1	f
3	Otro	2021-06-28 00:00:00	2022-02-01 23:59:59	3	O	1	f
\.


--
-- Data for Name: ae_acciones; Type: TABLE DATA; Schema: empresa3; Owner: sae
--

COPY empresa3.ae_acciones (id, descripcion, fecha_baja, host, nombre, servicio) FROM stdin;
\.


--
-- Data for Name: ae_acciones_miperfil_recurso; Type: TABLE DATA; Schema: empresa3; Owner: sae
--

COPY empresa3.ae_acciones_miperfil_recurso (id, recurso_id, titulo_con_1, url_con_1, destacada_con_1, titulo_con_2, url_con_2, destacada_con_2, titulo_con_3, url_con_3, destacada_con_3, titulo_con_4, url_con_4, destacada_con_4, titulo_con_5, url_con_5, destacada_con_5, titulo_can_1, url_can_1, destacada_can_1, titulo_can_2, url_can_2, destacada_can_2, titulo_can_3, url_can_3, destacada_can_3, titulo_can_4, url_can_4, destacada_can_4, titulo_can_5, url_can_5, destacada_can_5, titulo_rec_1, url_rec_1, destacada_rec_1, titulo_rec_2, url_rec_2, destacada_rec_2, titulo_rec_3, url_rec_3, destacada_rec_3, titulo_rec_4, url_rec_4, destacada_rec_4, titulo_rec_5, url_rec_5, destacada_rec_5) FROM stdin;
\.


--
-- Data for Name: ae_acciones_por_dato; Type: TABLE DATA; Schema: empresa3; Owner: sae
--

COPY empresa3.ae_acciones_por_dato (id, fecha_desasociacion, nombre_parametro, aear_id, aeds_id) FROM stdin;
\.


--
-- Data for Name: ae_acciones_por_recurso; Type: TABLE DATA; Schema: empresa3; Owner: sae
--

COPY empresa3.ae_acciones_por_recurso (id, evento, fecha_baja, orden_ejecucion, aeac_id, aere_id) FROM stdin;
\.


--
-- Data for Name: ae_agendas; Type: TABLE DATA; Schema: empresa3; Owner: sae
--

COPY empresa3.ae_agendas (id, descripcion, fecha_baja, nombre, tramite_id, timezone, idiomas, con_cda, tramite_codigo, publicar_novedades, con_trazabilidad) FROM stdin;
\.


--
-- Data for Name: ae_agrupaciones_datos; Type: TABLE DATA; Schema: empresa3; Owner: sae
--

COPY empresa3.ae_agrupaciones_datos (id, fecha_baja, nombre, orden, aere_id, borrar_flag, etiqueta) FROM stdin;
\.


--
-- Data for Name: ae_anios; Type: TABLE DATA; Schema: empresa3; Owner: sae
--

COPY empresa3.ae_anios (id, anio, aepl_id) FROM stdin;
\.


--
-- Data for Name: ae_atencion; Type: TABLE DATA; Schema: empresa3; Owner: sae
--

COPY empresa3.ae_atencion (id, asistio, duracion, fact, fcrea, funcionario, aers_id) FROM stdin;
\.


--
-- Data for Name: ae_comunicaciones; Type: TABLE DATA; Schema: empresa3; Owner: sae
--

COPY empresa3.ae_comunicaciones (id, tipo_1, tipo_2, destino, recurso_id, reserva_id, token_id, procesado, mensaje) FROM stdin;
\.


--
-- Data for Name: ae_constante_validacion; Type: TABLE DATA; Schema: empresa3; Owner: sae
--

COPY empresa3.ae_constante_validacion (id, fecha_baja, largo, nombre, tipo, aeva_id) FROM stdin;
\.


--
-- Data for Name: ae_datos_a_solicitar; Type: TABLE DATA; Schema: empresa3; Owner: sae
--

COPY empresa3.ae_datos_a_solicitar (id, ancho_despliegue, columna, es_clave, etiqueta, fecha_baja, fila, incluir_en_llamador, incluir_en_reporte, largo, largo_en_llamador, nombre, orden_en_llamador, requerido, texto_ayuda, tipo, aead_id, aere_id, borrar_flag, solo_lectura, incluir_en_novedades) FROM stdin;
\.


--
-- Data for Name: ae_datos_del_recurso; Type: TABLE DATA; Schema: empresa3; Owner: sae
--

COPY empresa3.ae_datos_del_recurso (id, etiqueta, orden, valor, aere_id) FROM stdin;
\.


--
-- Data for Name: ae_datos_reserva; Type: TABLE DATA; Schema: empresa3; Owner: sae
--

COPY empresa3.ae_datos_reserva (id, valor, aeds_id, aers_id) FROM stdin;
\.


--
-- Data for Name: ae_dias_del_mes; Type: TABLE DATA; Schema: empresa3; Owner: sae
--

COPY empresa3.ae_dias_del_mes (id, dia_del_mes, aepl_id) FROM stdin;
\.


--
-- Data for Name: ae_dias_semana; Type: TABLE DATA; Schema: empresa3; Owner: sae
--

COPY empresa3.ae_dias_semana (id, dia_semana, aepl_id) FROM stdin;
\.


--
-- Data for Name: ae_disponibilidades; Type: TABLE DATA; Schema: empresa3; Owner: sae
--

COPY empresa3.ae_disponibilidades (id, cupo, fecha, fecha_baja, hora_fin, hora_inicio, numerador, version, aepl_id, aere_id, presencial) FROM stdin;
\.


--
-- Data for Name: ae_frases_captcha; Type: TABLE DATA; Schema: empresa3; Owner: sae
--

COPY empresa3.ae_frases_captcha (clave, frase, idioma) FROM stdin;
\.


--
-- Data for Name: ae_llamadas; Type: TABLE DATA; Schema: empresa3; Owner: sae
--

COPY empresa3.ae_llamadas (id, etiqueta, fecha, hora, numero, puesto, aere_id, aers_id) FROM stdin;
\.


--
-- Data for Name: ae_meses; Type: TABLE DATA; Schema: empresa3; Owner: sae
--

COPY empresa3.ae_meses (id, mes, aepl_id) FROM stdin;
\.


--
-- Data for Name: ae_parametros_accion; Type: TABLE DATA; Schema: empresa3; Owner: sae
--

COPY empresa3.ae_parametros_accion (id, fecha_baja, largo, nombre, tipo, aeac_id) FROM stdin;
\.


--
-- Data for Name: ae_parametros_autocompletar; Type: TABLE DATA; Schema: empresa3; Owner: sae
--

COPY empresa3.ae_parametros_autocompletar (id, fecha_baja, modo, nombre, tipo, aeserv_id) FROM stdin;
\.


--
-- Data for Name: ae_parametros_validacion; Type: TABLE DATA; Schema: empresa3; Owner: sae
--

COPY empresa3.ae_parametros_validacion (id, fecha_baja, largo, nombre, tipo, aeva_id) FROM stdin;
\.


--
-- Data for Name: ae_plantillas; Type: TABLE DATA; Schema: empresa3; Owner: sae
--

COPY empresa3.ae_plantillas (id, cupo, fecha_baja, frecuencia, generacion_automatica, hora_fin, hora_inicio, aere_id) FROM stdin;
\.


--
-- Data for Name: ae_preguntas_captcha; Type: TABLE DATA; Schema: empresa3; Owner: sae
--

COPY empresa3.ae_preguntas_captcha (clave, pregunta, respuesta, idioma) FROM stdin;
P000001	¿De qué color era el caballo blanco de Artigas?	blanco	es
P000002	¿Cuánto es dos más dos? Responde con palabras	cuatro	es
P000003	Escribe la tercera palabra de: Hoy está lloviendo	lloviendo	es
P000004	¿Cuánto es tres más uno? Responde con palabras	cuatro	es
P000005	Escribe la segunda palabra de: Hoy es Lunes	es	es
P000006	¿Cuánto es uno más uno? Responde con palabras	dos	es
P000007	¿Cuánto es cinco más dos? Responde con palabras	siete	es
P000008	Escribe la primera palabra de: Hoy está soleado	hoy	es
P000009	De los números uno, cuatro y dos, ¿cuál es el menor?	uno	es
P000010	¿Cuántos colores hay en la lista: torta, verde, hotel?	uno	es
P000011	¿Cuál es el segundo dígito de 7101712? Responde con palabras	uno	es
P000012	Si el león es amarillo, ¿de qué color es el león?	amarillo	es
P000013	¿Cuál de los siguientes es un color: libro, nariz, verde, queso?	verde	es
P000014	Si mañana es sábado, ¿qué día es hoy?	viernes	es
P000016	Escribe la segunda palabra de: Ayer tuve frío	tuve	es
P000017	Escribe la última palabra de: me caigo y no me levanto	levanto	es
P000018	Escribe la quinta palabra de: más vale pájaro en mano que cien volando	mano	es
P000020	Escribe la quinta palabra de: cuanto menos se piensa salta la liebre	salta	es
P000021	Un día en la vida, ¿cuántos días son? escribe en letras	uno	es
P000022	Tres tristes tigres comen trigo, ¿qué comen los tigres?	trigo	es
P000023	Si hoy me acuesto, despierto mañana; ¿cuándo me acuesto?	hoy	es
P000024	¿Qué animal es mayor, el mono  o el elefante?	elefante	es
P000025	¿Cuál es el primer mes del año?	enero	es
P000026	¿Cuál es el segundo mes del año?	febrero	es
P000027	¿Cuál es el tercer mes del año?	marzo	es
P000028	¿Cuál es el cuarto mes del año?	abril	es
P000029	¿Cuál es el quinto mes del año?	mayo	es
P000030	¿Cuál es el sexto mes del año?	junio	es
P000031	¿Cuál es el séptimo mes del año?	julio	es
P000032	¿Cuál es el octavo mes del año?	agosto	es
P000033	¿Cuál es el noveno mes del año?	setiembre	es
P000034	¿Cuál es el décimo mes del año?	octubre	es
P000035	¿Cuál es el penúltimo mes del año?	noviembre	es
P000036	¿Cuál es el último mes del año?	diciembre	es
P000037	¿Cuántos días tiene enero? Responde con números	31	es
P000038	¿Cuántos días tiene febrero? Responde con números	28	es
P000039	¿Cuántos días tiene marzo? Responde con números	31	es
P000040	¿Cuántos días tiene abril? Responde con números	30	es
P000041	¿Cuántos días tiene mayo? Responde con números	31	es
P000042	¿Cuántos días tiene junio? Responde con números	30	es
P000043	¿Cuántos días tiene julio? Responde con números	31	es
P000044	¿Cuántos días tiene agosto? Responde con números	31	es
P000045	¿Cuántos días tiene setiembre? Responde con números	30	es
P000046	¿Cuántos días tiene octubre? Responde con números	31	es
P000047	¿Cuántos días tiene noviembre? Responde con números	30	es
P000048	¿Cuántos días tiene diciembre? Responde con números	31	es
P000049	Hola dijo Bartola; ¿quién dijo hola?	Bartola	es
P000050	¿Cuál palabra es un color: amarillo, abejorro, mono?	amarillo	es
P000051	¿Cuál palabra es un color: violeta, araña, morsa?	violeta	es
P000052	¿Cuál palabra es un color: blanco, alce, mosca?	blanco	es
P000053	¿Cuál palabra es un color: azul, almeja, mosquito?	azul	es
P000054	¿Cuál palabra es un color: rojo, ardilla, casa?	rojo	es
P000055	¿Cuál palabra es un color: negro, vaca, auto?	negro	es
\.


--
-- Data for Name: ae_recursos; Type: TABLE DATA; Schema: empresa3; Owner: sae
--

COPY empresa3.ae_recursos (id, cant_dias_a_generar, descripcion, dias_inicio_ventana_internet, dias_inicio_ventana_intranet, dias_ventana_internet, dias_ventana_intranet, fecha_baja, fecha_fin, fecha_fin_disp, fecha_inicio, fecha_inicio_disp, largo_lista_espera, mostrar_numero_en_llamador, mostrar_numero_en_ticket, nombre, sabado_es_habil, serie, usar_llamador, ventana_cupos_minimos, version, visible_internet, aeag_id, oficina_id, direccion, localidad, departamento, telefonos, horarios, latitud, longitud, mostrar_id_en_ticket, domingo_es_habil, presencial_admite, presencial_lunes, presencial_martes, presencial_miercoles, presencial_jueves, presencial_viernes, presencial_sabado, presencial_domingo, presencial_cupos, tamanio_fuente_grande, tamanio_fuente_normal, tamanio_fuente_chica, fuente_ticket, multiple_admite, cambios_admite, cambios_tiempo, cambios_unidad, periodo_validacion, validar_por_ip, cantidad_por_ip, periodo_por_ip, ips_sin_validacion, cancela_tiempo, cancela_unidad, cancela_tipo, mi_perfil_con_hab, mi_perfil_can_hab, mi_perfil_rec_hab, mi_perfil_con_tit, mi_perfil_con_cor, mi_perfil_con_lar, mi_perfil_con_ven, mi_perfil_can_tit, mi_perfil_can_cor, mi_perfil_can_lar, mi_perfil_can_ven, mi_perfil_rec_tit, mi_perfil_rec_cor, mi_perfil_rec_lar, mi_perfil_rec_ven, mi_perfil_rec_hora, mi_perfil_rec_dias) FROM stdin;
\.


--
-- Data for Name: ae_recursos_aud; Type: TABLE DATA; Schema: empresa3; Owner: sae
--

COPY empresa3.ae_recursos_aud (id, id_recurso, nombre, descripcion, fecha_inicio, fecha_fin, fecha_inicio_disp, fecha_fin_disp, dias_inicio_ventana_intranet, dias_ventana_intranet, dias_inicio_ventana_internet, dias_ventana_internet, ventana_cupos_minimos, cant_dias_a_generar, largo_lista_espera, fecha_baja, mostrar_numero_en_llamador, visible_internet, usar_llamador, serie, sabado_es_habil, domingo_es_habil, mostrar_numero_en_ticket, mostrar_id_en_ticket, fuente_ticket, tamanio_fuente_grande, tamanio_fuente_normal, tamanio_fuente_chica, oficina_id, direccion, localidad, departamento, telefonos, horarios, latitud, longitud, agenda, presencial_admite, presencial_cupos, presencial_lunes, presencial_martes, presencial_miercoles, presencial_jueves, presencial_viernes, presencial_sabado, presencial_domingo, multiple_admite, cambios_admite, cambios_tiempo, cambios_unidad, periodo_validacion, validar_por_ip, cantidad_por_ip, periodo_por_ip, ips_sin_validacion, cancela_tiempo, cancela_unidad, cancela_tipo, mi_perfil_con_hab, mi_perfil_con_tit, mi_perfil_con_cor, mi_perfil_con_lar, mi_perfil_con_ven, mi_perfil_can_hab, mi_perfil_can_tit, mi_perfil_can_cor, mi_perfil_can_lar, mi_perfil_can_ven, mi_perfil_rec_hab, mi_perfil_rec_tit, mi_perfil_rec_cor, mi_perfil_rec_lar, mi_perfil_rec_ven, mi_perfil_rec_hora, mi_perfil_rec_dias, reserva_pen_tiempo_max, reserva_pend_tiempo_max, fecha_modificacion, usuario, version, tipo_operacion) FROM stdin;
\.


--
-- Data for Name: ae_reservas; Type: TABLE DATA; Schema: empresa3; Owner: sae
--

COPY empresa3.ae_reservas (id, estado, fact, fcrea, numero, observaciones, origen, ucancela, ucrea, version, codigo_seguridad, trazabilidad_guid, tramite_codigo, tramite_nombre, serie, tcancela, fcancela, aetr_id, ip_origen, flibera, mi_perfil_notif, notificar, reserva_hija_id) FROM stdin;
\.


--
-- Data for Name: ae_reservas_disponibilidades; Type: TABLE DATA; Schema: empresa3; Owner: sae
--

COPY empresa3.ae_reservas_disponibilidades (aers_id, aedi_id) FROM stdin;
\.


--
-- Data for Name: ae_roles_usuario_recurso; Type: TABLE DATA; Schema: empresa3; Owner: sae
--

COPY empresa3.ae_roles_usuario_recurso (usuario_id, recurso_id, roles) FROM stdin;
\.


--
-- Data for Name: ae_serv_autocomp_por_dato; Type: TABLE DATA; Schema: empresa3; Owner: sae
--

COPY empresa3.ae_serv_autocomp_por_dato (id, fecha_desasociacion, nombre_parametro, aeds_id, aesr_id) FROM stdin;
\.


--
-- Data for Name: ae_serv_autocompletar; Type: TABLE DATA; Schema: empresa3; Owner: sae
--

COPY empresa3.ae_serv_autocompletar (id, descripcion, fecha_baja, host, nombre, servicio) FROM stdin;
\.


--
-- Data for Name: ae_servicio_por_recurso; Type: TABLE DATA; Schema: empresa3; Owner: sae
--

COPY empresa3.ae_servicio_por_recurso (id, fecha_baja, aeserv_id, aere_id) FROM stdin;
\.


--
-- Data for Name: ae_textos; Type: TABLE DATA; Schema: empresa3; Owner: sae
--

COPY empresa3.ae_textos (codigo, idioma, texto) FROM stdin;
\.


--
-- Data for Name: ae_textos_agenda; Type: TABLE DATA; Schema: empresa3; Owner: sae
--

COPY empresa3.ae_textos_agenda (id, texto_paso1, texto_paso2, texto_paso3, texto_sel_recurso, texto_ticket, aeag_id, texto_correo_conf, texto_correo_canc, por_defecto, idioma) FROM stdin;
\.


--
-- Data for Name: ae_textos_recurso; Type: TABLE DATA; Schema: empresa3; Owner: sae
--

COPY empresa3.ae_textos_recurso (id, texto_paso2, texto_paso3, ticket_etiqueta_dos, ticket_etiqueta_uno, titulo_ciudadano_en_llamador, titulo_puesto_en_llamador, valor_etiqueta_dos, valor_etiqueta_uno, aere_id, idioma) FROM stdin;
\.


--
-- Data for Name: ae_tokens_reservas; Type: TABLE DATA; Schema: empresa3; Owner: sae
--

COPY empresa3.ae_tokens_reservas (id, token, aere_id, fecha_inicio, ultima_reserva, estado, cedula, nombre, correoe, tramite, notas, version, ip_origen) FROM stdin;
\.


--
-- Data for Name: ae_tramites_agendas; Type: TABLE DATA; Schema: empresa3; Owner: sae
--

COPY empresa3.ae_tramites_agendas (id, agenda_id, tramite_id, tramite_codigo, tramite_nombre) FROM stdin;
\.


--
-- Data for Name: ae_validaciones; Type: TABLE DATA; Schema: empresa3; Owner: sae
--

COPY empresa3.ae_validaciones (id, descripcion, fecha_baja, host, nombre, servicio) FROM stdin;
\.


--
-- Data for Name: ae_validaciones_por_dato; Type: TABLE DATA; Schema: empresa3; Owner: sae
--

COPY empresa3.ae_validaciones_por_dato (id, fecha_desasociacion, nombre_parametro, aeds_id, aevr_id) FROM stdin;
\.


--
-- Data for Name: ae_validaciones_por_recurso; Type: TABLE DATA; Schema: empresa3; Owner: sae
--

COPY empresa3.ae_validaciones_por_recurso (id, fecha_baja, orden_ejecucion, aere_id, aeva_id) FROM stdin;
\.


--
-- Data for Name: ae_valor_constante_val_rec; Type: TABLE DATA; Schema: empresa3; Owner: sae
--

COPY empresa3.ae_valor_constante_val_rec (id, fecha_desasociacion, nombre_constante, valor, aevr_id) FROM stdin;
\.


--
-- Data for Name: ae_valores_del_dato; Type: TABLE DATA; Schema: empresa3; Owner: sae
--

COPY empresa3.ae_valores_del_dato (id, etiqueta, fecha_desde, fecha_hasta, orden, valor, aeds_id, borrar_flag) FROM stdin;
\.


--
-- Data for Name: ae_acciones; Type: TABLE DATA; Schema: empresa4; Owner: sae
--

COPY empresa4.ae_acciones (id, descripcion, fecha_baja, host, nombre, servicio) FROM stdin;
\.


--
-- Data for Name: ae_acciones_miperfil_recurso; Type: TABLE DATA; Schema: empresa4; Owner: sae
--

COPY empresa4.ae_acciones_miperfil_recurso (id, recurso_id, titulo_con_1, url_con_1, destacada_con_1, titulo_con_2, url_con_2, destacada_con_2, titulo_con_3, url_con_3, destacada_con_3, titulo_con_4, url_con_4, destacada_con_4, titulo_con_5, url_con_5, destacada_con_5, titulo_can_1, url_can_1, destacada_can_1, titulo_can_2, url_can_2, destacada_can_2, titulo_can_3, url_can_3, destacada_can_3, titulo_can_4, url_can_4, destacada_can_4, titulo_can_5, url_can_5, destacada_can_5, titulo_rec_1, url_rec_1, destacada_rec_1, titulo_rec_2, url_rec_2, destacada_rec_2, titulo_rec_3, url_rec_3, destacada_rec_3, titulo_rec_4, url_rec_4, destacada_rec_4, titulo_rec_5, url_rec_5, destacada_rec_5) FROM stdin;
\.


--
-- Data for Name: ae_acciones_por_dato; Type: TABLE DATA; Schema: empresa4; Owner: sae
--

COPY empresa4.ae_acciones_por_dato (id, fecha_desasociacion, nombre_parametro, aear_id, aeds_id) FROM stdin;
\.


--
-- Data for Name: ae_acciones_por_recurso; Type: TABLE DATA; Schema: empresa4; Owner: sae
--

COPY empresa4.ae_acciones_por_recurso (id, evento, fecha_baja, orden_ejecucion, aeac_id, aere_id) FROM stdin;
\.


--
-- Data for Name: ae_agendas; Type: TABLE DATA; Schema: empresa4; Owner: sae
--

COPY empresa4.ae_agendas (id, descripcion, fecha_baja, nombre, tramite_id, timezone, idiomas, con_cda, tramite_codigo, publicar_novedades, con_trazabilidad) FROM stdin;
\.


--
-- Data for Name: ae_agrupaciones_datos; Type: TABLE DATA; Schema: empresa4; Owner: sae
--

COPY empresa4.ae_agrupaciones_datos (id, fecha_baja, nombre, orden, aere_id, borrar_flag, etiqueta) FROM stdin;
\.


--
-- Data for Name: ae_anios; Type: TABLE DATA; Schema: empresa4; Owner: sae
--

COPY empresa4.ae_anios (id, anio, aepl_id) FROM stdin;
\.


--
-- Data for Name: ae_atencion; Type: TABLE DATA; Schema: empresa4; Owner: sae
--

COPY empresa4.ae_atencion (id, asistio, duracion, fact, fcrea, funcionario, aers_id) FROM stdin;
\.


--
-- Data for Name: ae_comunicaciones; Type: TABLE DATA; Schema: empresa4; Owner: sae
--

COPY empresa4.ae_comunicaciones (id, tipo_1, tipo_2, destino, recurso_id, reserva_id, token_id, procesado, mensaje) FROM stdin;
\.


--
-- Data for Name: ae_constante_validacion; Type: TABLE DATA; Schema: empresa4; Owner: sae
--

COPY empresa4.ae_constante_validacion (id, fecha_baja, largo, nombre, tipo, aeva_id) FROM stdin;
\.


--
-- Data for Name: ae_datos_a_solicitar; Type: TABLE DATA; Schema: empresa4; Owner: sae
--

COPY empresa4.ae_datos_a_solicitar (id, ancho_despliegue, columna, es_clave, etiqueta, fecha_baja, fila, incluir_en_llamador, incluir_en_reporte, largo, largo_en_llamador, nombre, orden_en_llamador, requerido, texto_ayuda, tipo, aead_id, aere_id, borrar_flag, solo_lectura, incluir_en_novedades) FROM stdin;
\.


--
-- Data for Name: ae_datos_del_recurso; Type: TABLE DATA; Schema: empresa4; Owner: sae
--

COPY empresa4.ae_datos_del_recurso (id, etiqueta, orden, valor, aere_id) FROM stdin;
\.


--
-- Data for Name: ae_datos_reserva; Type: TABLE DATA; Schema: empresa4; Owner: sae
--

COPY empresa4.ae_datos_reserva (id, valor, aeds_id, aers_id) FROM stdin;
\.


--
-- Data for Name: ae_dias_del_mes; Type: TABLE DATA; Schema: empresa4; Owner: sae
--

COPY empresa4.ae_dias_del_mes (id, dia_del_mes, aepl_id) FROM stdin;
\.


--
-- Data for Name: ae_dias_semana; Type: TABLE DATA; Schema: empresa4; Owner: sae
--

COPY empresa4.ae_dias_semana (id, dia_semana, aepl_id) FROM stdin;
\.


--
-- Data for Name: ae_disponibilidades; Type: TABLE DATA; Schema: empresa4; Owner: sae
--

COPY empresa4.ae_disponibilidades (id, cupo, fecha, fecha_baja, hora_fin, hora_inicio, numerador, version, aepl_id, aere_id, presencial) FROM stdin;
\.


--
-- Data for Name: ae_frases_captcha; Type: TABLE DATA; Schema: empresa4; Owner: sae
--

COPY empresa4.ae_frases_captcha (clave, frase, idioma) FROM stdin;
\.


--
-- Data for Name: ae_llamadas; Type: TABLE DATA; Schema: empresa4; Owner: sae
--

COPY empresa4.ae_llamadas (id, etiqueta, fecha, hora, numero, puesto, aere_id, aers_id) FROM stdin;
\.


--
-- Data for Name: ae_meses; Type: TABLE DATA; Schema: empresa4; Owner: sae
--

COPY empresa4.ae_meses (id, mes, aepl_id) FROM stdin;
\.


--
-- Data for Name: ae_parametros_accion; Type: TABLE DATA; Schema: empresa4; Owner: sae
--

COPY empresa4.ae_parametros_accion (id, fecha_baja, largo, nombre, tipo, aeac_id) FROM stdin;
\.


--
-- Data for Name: ae_parametros_autocompletar; Type: TABLE DATA; Schema: empresa4; Owner: sae
--

COPY empresa4.ae_parametros_autocompletar (id, fecha_baja, modo, nombre, tipo, aeserv_id) FROM stdin;
\.


--
-- Data for Name: ae_parametros_validacion; Type: TABLE DATA; Schema: empresa4; Owner: sae
--

COPY empresa4.ae_parametros_validacion (id, fecha_baja, largo, nombre, tipo, aeva_id) FROM stdin;
\.


--
-- Data for Name: ae_plantillas; Type: TABLE DATA; Schema: empresa4; Owner: sae
--

COPY empresa4.ae_plantillas (id, cupo, fecha_baja, frecuencia, generacion_automatica, hora_fin, hora_inicio, aere_id) FROM stdin;
\.


--
-- Data for Name: ae_preguntas_captcha; Type: TABLE DATA; Schema: empresa4; Owner: sae
--

COPY empresa4.ae_preguntas_captcha (clave, pregunta, respuesta, idioma) FROM stdin;
P000001	¿De qué color era el caballo blanco de Artigas?	blanco	es
P000002	¿Cuánto es dos más dos? Responde con palabras	cuatro	es
P000003	Escribe la tercera palabra de: Hoy está lloviendo	lloviendo	es
P000004	¿Cuánto es tres más uno? Responde con palabras	cuatro	es
P000005	Escribe la segunda palabra de: Hoy es Lunes	es	es
P000006	¿Cuánto es uno más uno? Responde con palabras	dos	es
P000007	¿Cuánto es cinco más dos? Responde con palabras	siete	es
P000008	Escribe la primera palabra de: Hoy está soleado	hoy	es
P000009	De los números uno, cuatro y dos, ¿cuál es el menor?	uno	es
P000010	¿Cuántos colores hay en la lista: torta, verde, hotel?	uno	es
P000011	¿Cuál es el segundo dígito de 7101712? Responde con palabras	uno	es
P000012	Si el león es amarillo, ¿de qué color es el león?	amarillo	es
P000013	¿Cuál de los siguientes es un color: libro, nariz, verde, queso?	verde	es
P000014	Si mañana es sábado, ¿qué día es hoy?	viernes	es
P000016	Escribe la segunda palabra de: Ayer tuve frío	tuve	es
P000017	Escribe la última palabra de: me caigo y no me levanto	levanto	es
P000018	Escribe la quinta palabra de: más vale pájaro en mano que cien volando	mano	es
P000020	Escribe la quinta palabra de: cuanto menos se piensa salta la liebre	salta	es
P000021	Un día en la vida, ¿cuántos días son? escribe en letras	uno	es
P000022	Tres tristes tigres comen trigo, ¿qué comen los tigres?	trigo	es
P000023	Si hoy me acuesto, despierto mañana; ¿cuándo me acuesto?	hoy	es
P000024	¿Qué animal es mayor, el mono  o el elefante?	elefante	es
P000025	¿Cuál es el primer mes del año?	enero	es
P000026	¿Cuál es el segundo mes del año?	febrero	es
P000027	¿Cuál es el tercer mes del año?	marzo	es
P000028	¿Cuál es el cuarto mes del año?	abril	es
P000029	¿Cuál es el quinto mes del año?	mayo	es
P000030	¿Cuál es el sexto mes del año?	junio	es
P000031	¿Cuál es el séptimo mes del año?	julio	es
P000032	¿Cuál es el octavo mes del año?	agosto	es
P000033	¿Cuál es el noveno mes del año?	setiembre	es
P000034	¿Cuál es el décimo mes del año?	octubre	es
P000035	¿Cuál es el penúltimo mes del año?	noviembre	es
P000036	¿Cuál es el último mes del año?	diciembre	es
P000037	¿Cuántos días tiene enero? Responde con números	31	es
P000038	¿Cuántos días tiene febrero? Responde con números	28	es
P000039	¿Cuántos días tiene marzo? Responde con números	31	es
P000040	¿Cuántos días tiene abril? Responde con números	30	es
P000041	¿Cuántos días tiene mayo? Responde con números	31	es
P000042	¿Cuántos días tiene junio? Responde con números	30	es
P000043	¿Cuántos días tiene julio? Responde con números	31	es
P000044	¿Cuántos días tiene agosto? Responde con números	31	es
P000045	¿Cuántos días tiene setiembre? Responde con números	30	es
P000046	¿Cuántos días tiene octubre? Responde con números	31	es
P000047	¿Cuántos días tiene noviembre? Responde con números	30	es
P000048	¿Cuántos días tiene diciembre? Responde con números	31	es
P000049	Hola dijo Bartola; ¿quién dijo hola?	Bartola	es
P000050	¿Cuál palabra es un color: amarillo, abejorro, mono?	amarillo	es
P000051	¿Cuál palabra es un color: violeta, araña, morsa?	violeta	es
P000052	¿Cuál palabra es un color: blanco, alce, mosca?	blanco	es
P000053	¿Cuál palabra es un color: azul, almeja, mosquito?	azul	es
P000054	¿Cuál palabra es un color: rojo, ardilla, casa?	rojo	es
P000055	¿Cuál palabra es un color: negro, vaca, auto?	negro	es
\.


--
-- Data for Name: ae_recursos; Type: TABLE DATA; Schema: empresa4; Owner: sae
--

COPY empresa4.ae_recursos (id, cant_dias_a_generar, descripcion, dias_inicio_ventana_internet, dias_inicio_ventana_intranet, dias_ventana_internet, dias_ventana_intranet, fecha_baja, fecha_fin, fecha_fin_disp, fecha_inicio, fecha_inicio_disp, largo_lista_espera, mostrar_numero_en_llamador, mostrar_numero_en_ticket, nombre, sabado_es_habil, serie, usar_llamador, ventana_cupos_minimos, version, visible_internet, aeag_id, oficina_id, direccion, localidad, departamento, telefonos, horarios, latitud, longitud, mostrar_id_en_ticket, domingo_es_habil, presencial_admite, presencial_lunes, presencial_martes, presencial_miercoles, presencial_jueves, presencial_viernes, presencial_sabado, presencial_domingo, presencial_cupos, tamanio_fuente_grande, tamanio_fuente_normal, tamanio_fuente_chica, fuente_ticket, multiple_admite, cambios_admite, cambios_tiempo, cambios_unidad, periodo_validacion, validar_por_ip, cantidad_por_ip, periodo_por_ip, ips_sin_validacion, cancela_tiempo, cancela_unidad, cancela_tipo, mi_perfil_con_hab, mi_perfil_can_hab, mi_perfil_rec_hab, mi_perfil_con_tit, mi_perfil_con_cor, mi_perfil_con_lar, mi_perfil_con_ven, mi_perfil_can_tit, mi_perfil_can_cor, mi_perfil_can_lar, mi_perfil_can_ven, mi_perfil_rec_tit, mi_perfil_rec_cor, mi_perfil_rec_lar, mi_perfil_rec_ven, mi_perfil_rec_hora, mi_perfil_rec_dias) FROM stdin;
\.


--
-- Data for Name: ae_recursos_aud; Type: TABLE DATA; Schema: empresa4; Owner: sae
--

COPY empresa4.ae_recursos_aud (id, id_recurso, nombre, descripcion, fecha_inicio, fecha_fin, fecha_inicio_disp, fecha_fin_disp, dias_inicio_ventana_intranet, dias_ventana_intranet, dias_inicio_ventana_internet, dias_ventana_internet, ventana_cupos_minimos, cant_dias_a_generar, largo_lista_espera, fecha_baja, mostrar_numero_en_llamador, visible_internet, usar_llamador, serie, sabado_es_habil, domingo_es_habil, mostrar_numero_en_ticket, mostrar_id_en_ticket, fuente_ticket, tamanio_fuente_grande, tamanio_fuente_normal, tamanio_fuente_chica, oficina_id, direccion, localidad, departamento, telefonos, horarios, latitud, longitud, agenda, presencial_admite, presencial_cupos, presencial_lunes, presencial_martes, presencial_miercoles, presencial_jueves, presencial_viernes, presencial_sabado, presencial_domingo, multiple_admite, cambios_admite, cambios_tiempo, cambios_unidad, periodo_validacion, validar_por_ip, cantidad_por_ip, periodo_por_ip, ips_sin_validacion, cancela_tiempo, cancela_unidad, cancela_tipo, mi_perfil_con_hab, mi_perfil_con_tit, mi_perfil_con_cor, mi_perfil_con_lar, mi_perfil_con_ven, mi_perfil_can_hab, mi_perfil_can_tit, mi_perfil_can_cor, mi_perfil_can_lar, mi_perfil_can_ven, mi_perfil_rec_hab, mi_perfil_rec_tit, mi_perfil_rec_cor, mi_perfil_rec_lar, mi_perfil_rec_ven, mi_perfil_rec_hora, mi_perfil_rec_dias, reserva_pen_tiempo_max, reserva_pend_tiempo_max, fecha_modificacion, usuario, version, tipo_operacion) FROM stdin;
\.


--
-- Data for Name: ae_reservas; Type: TABLE DATA; Schema: empresa4; Owner: sae
--

COPY empresa4.ae_reservas (id, estado, fact, fcrea, numero, observaciones, origen, ucancela, ucrea, version, codigo_seguridad, trazabilidad_guid, tramite_codigo, tramite_nombre, serie, tcancela, fcancela, aetr_id, ip_origen, flibera, mi_perfil_notif, notificar, reserva_hija_id) FROM stdin;
\.


--
-- Data for Name: ae_reservas_disponibilidades; Type: TABLE DATA; Schema: empresa4; Owner: sae
--

COPY empresa4.ae_reservas_disponibilidades (aers_id, aedi_id) FROM stdin;
\.


--
-- Data for Name: ae_roles_usuario_recurso; Type: TABLE DATA; Schema: empresa4; Owner: sae
--

COPY empresa4.ae_roles_usuario_recurso (usuario_id, recurso_id, roles) FROM stdin;
\.


--
-- Data for Name: ae_serv_autocomp_por_dato; Type: TABLE DATA; Schema: empresa4; Owner: sae
--

COPY empresa4.ae_serv_autocomp_por_dato (id, fecha_desasociacion, nombre_parametro, aeds_id, aesr_id) FROM stdin;
\.


--
-- Data for Name: ae_serv_autocompletar; Type: TABLE DATA; Schema: empresa4; Owner: sae
--

COPY empresa4.ae_serv_autocompletar (id, descripcion, fecha_baja, host, nombre, servicio) FROM stdin;
\.


--
-- Data for Name: ae_servicio_por_recurso; Type: TABLE DATA; Schema: empresa4; Owner: sae
--

COPY empresa4.ae_servicio_por_recurso (id, fecha_baja, aeserv_id, aere_id) FROM stdin;
\.


--
-- Data for Name: ae_textos; Type: TABLE DATA; Schema: empresa4; Owner: sae
--

COPY empresa4.ae_textos (codigo, idioma, texto) FROM stdin;
\.


--
-- Data for Name: ae_textos_agenda; Type: TABLE DATA; Schema: empresa4; Owner: sae
--

COPY empresa4.ae_textos_agenda (id, texto_paso1, texto_paso2, texto_paso3, texto_sel_recurso, texto_ticket, aeag_id, texto_correo_conf, texto_correo_canc, por_defecto, idioma) FROM stdin;
\.


--
-- Data for Name: ae_textos_recurso; Type: TABLE DATA; Schema: empresa4; Owner: sae
--

COPY empresa4.ae_textos_recurso (id, texto_paso2, texto_paso3, ticket_etiqueta_dos, ticket_etiqueta_uno, titulo_ciudadano_en_llamador, titulo_puesto_en_llamador, valor_etiqueta_dos, valor_etiqueta_uno, aere_id, idioma) FROM stdin;
\.


--
-- Data for Name: ae_tokens_reservas; Type: TABLE DATA; Schema: empresa4; Owner: sae
--

COPY empresa4.ae_tokens_reservas (id, token, aere_id, fecha_inicio, ultima_reserva, estado, cedula, nombre, correoe, tramite, notas, version, ip_origen) FROM stdin;
\.


--
-- Data for Name: ae_tramites_agendas; Type: TABLE DATA; Schema: empresa4; Owner: sae
--

COPY empresa4.ae_tramites_agendas (id, agenda_id, tramite_id, tramite_codigo, tramite_nombre) FROM stdin;
\.


--
-- Data for Name: ae_validaciones; Type: TABLE DATA; Schema: empresa4; Owner: sae
--

COPY empresa4.ae_validaciones (id, descripcion, fecha_baja, host, nombre, servicio) FROM stdin;
\.


--
-- Data for Name: ae_validaciones_por_dato; Type: TABLE DATA; Schema: empresa4; Owner: sae
--

COPY empresa4.ae_validaciones_por_dato (id, fecha_desasociacion, nombre_parametro, aeds_id, aevr_id) FROM stdin;
\.


--
-- Data for Name: ae_validaciones_por_recurso; Type: TABLE DATA; Schema: empresa4; Owner: sae
--

COPY empresa4.ae_validaciones_por_recurso (id, fecha_baja, orden_ejecucion, aere_id, aeva_id) FROM stdin;
\.


--
-- Data for Name: ae_valor_constante_val_rec; Type: TABLE DATA; Schema: empresa4; Owner: sae
--

COPY empresa4.ae_valor_constante_val_rec (id, fecha_desasociacion, nombre_constante, valor, aevr_id) FROM stdin;
\.


--
-- Data for Name: ae_valores_del_dato; Type: TABLE DATA; Schema: empresa4; Owner: sae
--

COPY empresa4.ae_valores_del_dato (id, etiqueta, fecha_desde, fecha_hasta, orden, valor, aeds_id, borrar_flag) FROM stdin;
\.


--
-- Data for Name: ae_acciones; Type: TABLE DATA; Schema: empresa5; Owner: sae
--

COPY empresa5.ae_acciones (id, descripcion, fecha_baja, host, nombre, servicio) FROM stdin;
\.


--
-- Data for Name: ae_acciones_miperfil_recurso; Type: TABLE DATA; Schema: empresa5; Owner: sae
--

COPY empresa5.ae_acciones_miperfil_recurso (id, recurso_id, titulo_con_1, url_con_1, destacada_con_1, titulo_con_2, url_con_2, destacada_con_2, titulo_con_3, url_con_3, destacada_con_3, titulo_con_4, url_con_4, destacada_con_4, titulo_con_5, url_con_5, destacada_con_5, titulo_can_1, url_can_1, destacada_can_1, titulo_can_2, url_can_2, destacada_can_2, titulo_can_3, url_can_3, destacada_can_3, titulo_can_4, url_can_4, destacada_can_4, titulo_can_5, url_can_5, destacada_can_5, titulo_rec_1, url_rec_1, destacada_rec_1, titulo_rec_2, url_rec_2, destacada_rec_2, titulo_rec_3, url_rec_3, destacada_rec_3, titulo_rec_4, url_rec_4, destacada_rec_4, titulo_rec_5, url_rec_5, destacada_rec_5) FROM stdin;
\.


--
-- Data for Name: ae_acciones_por_dato; Type: TABLE DATA; Schema: empresa5; Owner: sae
--

COPY empresa5.ae_acciones_por_dato (id, fecha_desasociacion, nombre_parametro, aear_id, aeds_id) FROM stdin;
\.


--
-- Data for Name: ae_acciones_por_recurso; Type: TABLE DATA; Schema: empresa5; Owner: sae
--

COPY empresa5.ae_acciones_por_recurso (id, evento, fecha_baja, orden_ejecucion, aeac_id, aere_id) FROM stdin;
\.


--
-- Data for Name: ae_agendas; Type: TABLE DATA; Schema: empresa5; Owner: sae
--

COPY empresa5.ae_agendas (id, descripcion, fecha_baja, nombre, tramite_id, timezone, idiomas, con_cda, tramite_codigo, publicar_novedades, con_trazabilidad) FROM stdin;
\.


--
-- Data for Name: ae_agrupaciones_datos; Type: TABLE DATA; Schema: empresa5; Owner: sae
--

COPY empresa5.ae_agrupaciones_datos (id, fecha_baja, nombre, orden, aere_id, borrar_flag, etiqueta) FROM stdin;
\.


--
-- Data for Name: ae_anios; Type: TABLE DATA; Schema: empresa5; Owner: sae
--

COPY empresa5.ae_anios (id, anio, aepl_id) FROM stdin;
\.


--
-- Data for Name: ae_atencion; Type: TABLE DATA; Schema: empresa5; Owner: sae
--

COPY empresa5.ae_atencion (id, asistio, duracion, fact, fcrea, funcionario, aers_id) FROM stdin;
\.


--
-- Data for Name: ae_comunicaciones; Type: TABLE DATA; Schema: empresa5; Owner: sae
--

COPY empresa5.ae_comunicaciones (id, tipo_1, tipo_2, destino, recurso_id, reserva_id, token_id, procesado, mensaje) FROM stdin;
\.


--
-- Data for Name: ae_constante_validacion; Type: TABLE DATA; Schema: empresa5; Owner: sae
--

COPY empresa5.ae_constante_validacion (id, fecha_baja, largo, nombre, tipo, aeva_id) FROM stdin;
\.


--
-- Data for Name: ae_datos_a_solicitar; Type: TABLE DATA; Schema: empresa5; Owner: sae
--

COPY empresa5.ae_datos_a_solicitar (id, ancho_despliegue, columna, es_clave, etiqueta, fecha_baja, fila, incluir_en_llamador, incluir_en_reporte, largo, largo_en_llamador, nombre, orden_en_llamador, requerido, texto_ayuda, tipo, aead_id, aere_id, borrar_flag, solo_lectura, incluir_en_novedades) FROM stdin;
\.


--
-- Data for Name: ae_datos_del_recurso; Type: TABLE DATA; Schema: empresa5; Owner: sae
--

COPY empresa5.ae_datos_del_recurso (id, etiqueta, orden, valor, aere_id) FROM stdin;
\.


--
-- Data for Name: ae_datos_reserva; Type: TABLE DATA; Schema: empresa5; Owner: sae
--

COPY empresa5.ae_datos_reserva (id, valor, aeds_id, aers_id) FROM stdin;
\.


--
-- Data for Name: ae_dias_del_mes; Type: TABLE DATA; Schema: empresa5; Owner: sae
--

COPY empresa5.ae_dias_del_mes (id, dia_del_mes, aepl_id) FROM stdin;
\.


--
-- Data for Name: ae_dias_semana; Type: TABLE DATA; Schema: empresa5; Owner: sae
--

COPY empresa5.ae_dias_semana (id, dia_semana, aepl_id) FROM stdin;
\.


--
-- Data for Name: ae_disponibilidades; Type: TABLE DATA; Schema: empresa5; Owner: sae
--

COPY empresa5.ae_disponibilidades (id, cupo, fecha, fecha_baja, hora_fin, hora_inicio, numerador, version, aepl_id, aere_id, presencial) FROM stdin;
\.


--
-- Data for Name: ae_frases_captcha; Type: TABLE DATA; Schema: empresa5; Owner: sae
--

COPY empresa5.ae_frases_captcha (clave, frase, idioma) FROM stdin;
\.


--
-- Data for Name: ae_llamadas; Type: TABLE DATA; Schema: empresa5; Owner: sae
--

COPY empresa5.ae_llamadas (id, etiqueta, fecha, hora, numero, puesto, aere_id, aers_id) FROM stdin;
\.


--
-- Data for Name: ae_meses; Type: TABLE DATA; Schema: empresa5; Owner: sae
--

COPY empresa5.ae_meses (id, mes, aepl_id) FROM stdin;
\.


--
-- Data for Name: ae_parametros_accion; Type: TABLE DATA; Schema: empresa5; Owner: sae
--

COPY empresa5.ae_parametros_accion (id, fecha_baja, largo, nombre, tipo, aeac_id) FROM stdin;
\.


--
-- Data for Name: ae_parametros_autocompletar; Type: TABLE DATA; Schema: empresa5; Owner: sae
--

COPY empresa5.ae_parametros_autocompletar (id, fecha_baja, modo, nombre, tipo, aeserv_id) FROM stdin;
\.


--
-- Data for Name: ae_parametros_validacion; Type: TABLE DATA; Schema: empresa5; Owner: sae
--

COPY empresa5.ae_parametros_validacion (id, fecha_baja, largo, nombre, tipo, aeva_id) FROM stdin;
\.


--
-- Data for Name: ae_plantillas; Type: TABLE DATA; Schema: empresa5; Owner: sae
--

COPY empresa5.ae_plantillas (id, cupo, fecha_baja, frecuencia, generacion_automatica, hora_fin, hora_inicio, aere_id) FROM stdin;
\.


--
-- Data for Name: ae_preguntas_captcha; Type: TABLE DATA; Schema: empresa5; Owner: sae
--

COPY empresa5.ae_preguntas_captcha (clave, pregunta, respuesta, idioma) FROM stdin;
P000001	¿De qué color era el caballo blanco de Artigas?	blanco	es
P000002	¿Cuánto es dos más dos? Responde con palabras	cuatro	es
P000003	Escribe la tercera palabra de: Hoy está lloviendo	lloviendo	es
P000004	¿Cuánto es tres más uno? Responde con palabras	cuatro	es
P000005	Escribe la segunda palabra de: Hoy es Lunes	es	es
P000006	¿Cuánto es uno más uno? Responde con palabras	dos	es
P000007	¿Cuánto es cinco más dos? Responde con palabras	siete	es
P000008	Escribe la primera palabra de: Hoy está soleado	hoy	es
P000009	De los números uno, cuatro y dos, ¿cuál es el menor?	uno	es
P000010	¿Cuántos colores hay en la lista: torta, verde, hotel?	uno	es
P000011	¿Cuál es el segundo dígito de 7101712? Responde con palabras	uno	es
P000012	Si el león es amarillo, ¿de qué color es el león?	amarillo	es
P000013	¿Cuál de los siguientes es un color: libro, nariz, verde, queso?	verde	es
P000014	Si mañana es sábado, ¿qué día es hoy?	viernes	es
P000016	Escribe la segunda palabra de: Ayer tuve frío	tuve	es
P000017	Escribe la última palabra de: me caigo y no me levanto	levanto	es
P000018	Escribe la quinta palabra de: más vale pájaro en mano que cien volando	mano	es
P000020	Escribe la quinta palabra de: cuanto menos se piensa salta la liebre	salta	es
P000021	Un día en la vida, ¿cuántos días son? escribe en letras	uno	es
P000022	Tres tristes tigres comen trigo, ¿qué comen los tigres?	trigo	es
P000023	Si hoy me acuesto, despierto mañana; ¿cuándo me acuesto?	hoy	es
P000024	¿Qué animal es mayor, el mono  o el elefante?	elefante	es
P000025	¿Cuál es el primer mes del año?	enero	es
P000026	¿Cuál es el segundo mes del año?	febrero	es
P000027	¿Cuál es el tercer mes del año?	marzo	es
P000028	¿Cuál es el cuarto mes del año?	abril	es
P000029	¿Cuál es el quinto mes del año?	mayo	es
P000030	¿Cuál es el sexto mes del año?	junio	es
P000031	¿Cuál es el séptimo mes del año?	julio	es
P000032	¿Cuál es el octavo mes del año?	agosto	es
P000033	¿Cuál es el noveno mes del año?	setiembre	es
P000034	¿Cuál es el décimo mes del año?	octubre	es
P000035	¿Cuál es el penúltimo mes del año?	noviembre	es
P000036	¿Cuál es el último mes del año?	diciembre	es
P000037	¿Cuántos días tiene enero? Responde con números	31	es
P000038	¿Cuántos días tiene febrero? Responde con números	28	es
P000039	¿Cuántos días tiene marzo? Responde con números	31	es
P000040	¿Cuántos días tiene abril? Responde con números	30	es
P000041	¿Cuántos días tiene mayo? Responde con números	31	es
P000042	¿Cuántos días tiene junio? Responde con números	30	es
P000043	¿Cuántos días tiene julio? Responde con números	31	es
P000044	¿Cuántos días tiene agosto? Responde con números	31	es
P000045	¿Cuántos días tiene setiembre? Responde con números	30	es
P000046	¿Cuántos días tiene octubre? Responde con números	31	es
P000047	¿Cuántos días tiene noviembre? Responde con números	30	es
P000048	¿Cuántos días tiene diciembre? Responde con números	31	es
P000049	Hola dijo Bartola; ¿quién dijo hola?	Bartola	es
P000050	¿Cuál palabra es un color: amarillo, abejorro, mono?	amarillo	es
P000051	¿Cuál palabra es un color: violeta, araña, morsa?	violeta	es
P000052	¿Cuál palabra es un color: blanco, alce, mosca?	blanco	es
P000053	¿Cuál palabra es un color: azul, almeja, mosquito?	azul	es
P000054	¿Cuál palabra es un color: rojo, ardilla, casa?	rojo	es
P000055	¿Cuál palabra es un color: negro, vaca, auto?	negro	es
\.


--
-- Data for Name: ae_recursos; Type: TABLE DATA; Schema: empresa5; Owner: sae
--

COPY empresa5.ae_recursos (id, cant_dias_a_generar, descripcion, dias_inicio_ventana_internet, dias_inicio_ventana_intranet, dias_ventana_internet, dias_ventana_intranet, fecha_baja, fecha_fin, fecha_fin_disp, fecha_inicio, fecha_inicio_disp, largo_lista_espera, mostrar_numero_en_llamador, mostrar_numero_en_ticket, nombre, sabado_es_habil, serie, usar_llamador, ventana_cupos_minimos, version, visible_internet, aeag_id, oficina_id, direccion, localidad, departamento, telefonos, horarios, latitud, longitud, mostrar_id_en_ticket, domingo_es_habil, presencial_admite, presencial_lunes, presencial_martes, presencial_miercoles, presencial_jueves, presencial_viernes, presencial_sabado, presencial_domingo, presencial_cupos, tamanio_fuente_grande, tamanio_fuente_normal, tamanio_fuente_chica, fuente_ticket, multiple_admite, cambios_admite, cambios_tiempo, cambios_unidad, periodo_validacion, validar_por_ip, cantidad_por_ip, periodo_por_ip, ips_sin_validacion, cancela_tiempo, cancela_unidad, cancela_tipo, mi_perfil_con_hab, mi_perfil_can_hab, mi_perfil_rec_hab, mi_perfil_con_tit, mi_perfil_con_cor, mi_perfil_con_lar, mi_perfil_con_ven, mi_perfil_can_tit, mi_perfil_can_cor, mi_perfil_can_lar, mi_perfil_can_ven, mi_perfil_rec_tit, mi_perfil_rec_cor, mi_perfil_rec_lar, mi_perfil_rec_ven, mi_perfil_rec_hora, mi_perfil_rec_dias) FROM stdin;
\.


--
-- Data for Name: ae_recursos_aud; Type: TABLE DATA; Schema: empresa5; Owner: sae
--

COPY empresa5.ae_recursos_aud (id, id_recurso, nombre, descripcion, fecha_inicio, fecha_fin, fecha_inicio_disp, fecha_fin_disp, dias_inicio_ventana_intranet, dias_ventana_intranet, dias_inicio_ventana_internet, dias_ventana_internet, ventana_cupos_minimos, cant_dias_a_generar, largo_lista_espera, fecha_baja, mostrar_numero_en_llamador, visible_internet, usar_llamador, serie, sabado_es_habil, domingo_es_habil, mostrar_numero_en_ticket, mostrar_id_en_ticket, fuente_ticket, tamanio_fuente_grande, tamanio_fuente_normal, tamanio_fuente_chica, oficina_id, direccion, localidad, departamento, telefonos, horarios, latitud, longitud, agenda, presencial_admite, presencial_cupos, presencial_lunes, presencial_martes, presencial_miercoles, presencial_jueves, presencial_viernes, presencial_sabado, presencial_domingo, multiple_admite, cambios_admite, cambios_tiempo, cambios_unidad, periodo_validacion, validar_por_ip, cantidad_por_ip, periodo_por_ip, ips_sin_validacion, cancela_tiempo, cancela_unidad, cancela_tipo, mi_perfil_con_hab, mi_perfil_con_tit, mi_perfil_con_cor, mi_perfil_con_lar, mi_perfil_con_ven, mi_perfil_can_hab, mi_perfil_can_tit, mi_perfil_can_cor, mi_perfil_can_lar, mi_perfil_can_ven, mi_perfil_rec_hab, mi_perfil_rec_tit, mi_perfil_rec_cor, mi_perfil_rec_lar, mi_perfil_rec_ven, mi_perfil_rec_hora, mi_perfil_rec_dias, reserva_pen_tiempo_max, reserva_pend_tiempo_max, fecha_modificacion, usuario, version, tipo_operacion) FROM stdin;
\.


--
-- Data for Name: ae_reservas; Type: TABLE DATA; Schema: empresa5; Owner: sae
--

COPY empresa5.ae_reservas (id, estado, fact, fcrea, numero, observaciones, origen, ucancela, ucrea, version, codigo_seguridad, trazabilidad_guid, tramite_codigo, tramite_nombre, serie, tcancela, fcancela, aetr_id, ip_origen, flibera, mi_perfil_notif, notificar, reserva_hija_id) FROM stdin;
\.


--
-- Data for Name: ae_reservas_disponibilidades; Type: TABLE DATA; Schema: empresa5; Owner: sae
--

COPY empresa5.ae_reservas_disponibilidades (aers_id, aedi_id) FROM stdin;
\.


--
-- Data for Name: ae_roles_usuario_recurso; Type: TABLE DATA; Schema: empresa5; Owner: sae
--

COPY empresa5.ae_roles_usuario_recurso (usuario_id, recurso_id, roles) FROM stdin;
\.


--
-- Data for Name: ae_serv_autocomp_por_dato; Type: TABLE DATA; Schema: empresa5; Owner: sae
--

COPY empresa5.ae_serv_autocomp_por_dato (id, fecha_desasociacion, nombre_parametro, aeds_id, aesr_id) FROM stdin;
\.


--
-- Data for Name: ae_serv_autocompletar; Type: TABLE DATA; Schema: empresa5; Owner: sae
--

COPY empresa5.ae_serv_autocompletar (id, descripcion, fecha_baja, host, nombre, servicio) FROM stdin;
\.


--
-- Data for Name: ae_servicio_por_recurso; Type: TABLE DATA; Schema: empresa5; Owner: sae
--

COPY empresa5.ae_servicio_por_recurso (id, fecha_baja, aeserv_id, aere_id) FROM stdin;
\.


--
-- Data for Name: ae_textos; Type: TABLE DATA; Schema: empresa5; Owner: sae
--

COPY empresa5.ae_textos (codigo, idioma, texto) FROM stdin;
\.


--
-- Data for Name: ae_textos_agenda; Type: TABLE DATA; Schema: empresa5; Owner: sae
--

COPY empresa5.ae_textos_agenda (id, texto_paso1, texto_paso2, texto_paso3, texto_sel_recurso, texto_ticket, aeag_id, texto_correo_conf, texto_correo_canc, por_defecto, idioma) FROM stdin;
\.


--
-- Data for Name: ae_textos_recurso; Type: TABLE DATA; Schema: empresa5; Owner: sae
--

COPY empresa5.ae_textos_recurso (id, texto_paso2, texto_paso3, ticket_etiqueta_dos, ticket_etiqueta_uno, titulo_ciudadano_en_llamador, titulo_puesto_en_llamador, valor_etiqueta_dos, valor_etiqueta_uno, aere_id, idioma) FROM stdin;
\.


--
-- Data for Name: ae_tokens_reservas; Type: TABLE DATA; Schema: empresa5; Owner: sae
--

COPY empresa5.ae_tokens_reservas (id, token, aere_id, fecha_inicio, ultima_reserva, estado, cedula, nombre, correoe, tramite, notas, version, ip_origen) FROM stdin;
\.


--
-- Data for Name: ae_tramites_agendas; Type: TABLE DATA; Schema: empresa5; Owner: sae
--

COPY empresa5.ae_tramites_agendas (id, agenda_id, tramite_id, tramite_codigo, tramite_nombre) FROM stdin;
\.


--
-- Data for Name: ae_validaciones; Type: TABLE DATA; Schema: empresa5; Owner: sae
--

COPY empresa5.ae_validaciones (id, descripcion, fecha_baja, host, nombre, servicio) FROM stdin;
\.


--
-- Data for Name: ae_validaciones_por_dato; Type: TABLE DATA; Schema: empresa5; Owner: sae
--

COPY empresa5.ae_validaciones_por_dato (id, fecha_desasociacion, nombre_parametro, aeds_id, aevr_id) FROM stdin;
\.


--
-- Data for Name: ae_validaciones_por_recurso; Type: TABLE DATA; Schema: empresa5; Owner: sae
--

COPY empresa5.ae_validaciones_por_recurso (id, fecha_baja, orden_ejecucion, aere_id, aeva_id) FROM stdin;
\.


--
-- Data for Name: ae_valor_constante_val_rec; Type: TABLE DATA; Schema: empresa5; Owner: sae
--

COPY empresa5.ae_valor_constante_val_rec (id, fecha_desasociacion, nombre_constante, valor, aevr_id) FROM stdin;
\.


--
-- Data for Name: ae_valores_del_dato; Type: TABLE DATA; Schema: empresa5; Owner: sae
--

COPY empresa5.ae_valores_del_dato (id, etiqueta, fecha_desde, fecha_hasta, orden, valor, aeds_id, borrar_flag) FROM stdin;
\.


--
-- Data for Name: ae_captchas; Type: TABLE DATA; Schema: global; Owner: sae
--

COPY global.ae_captchas (clave, fase) FROM stdin;
\.


--
-- Data for Name: ae_configuracion; Type: TABLE DATA; Schema: global; Owner: sae
--

COPY global.ae_configuracion (id, clave, valor, org_id) FROM stdin;
1	DOMINIO		5
2	GOOGLE_ANALYTICS		5
3	IDIOMAS_SOPORTADOS	es	5
4	JSON_ESCAPE	FALSE	5
5	MIPERFIL_OID		5
6	MOSTRAR_FECHA_ACTUAL	TRUE	5
7	RESERVA_MULTIPLE_PENDIENTE_TIEMPO_MAXIMO	2880	5
8	RESERVA_PENDIENTE_TIEMPO_MAXIMO	10	5
9	WS_MIPERFIL_HABILITADO	FALSE	5
10	WS_MIPERFIL_KS_PASS		5
11	WS_MIPERFIL_KS_PATH		5
12	WS_MIPERFIL_TS_PASS		5
13	WS_MIPERFIL_TS_PATH		5
14	WS_MIPERFIL_URL		5
15	WS_NOVEDADES_HABILITADO	FALSE	5
16	WS_NOVEDADES_LOCATION		5
17	WS_NOVEDADES_MAXINTENTOS	10	5
18	WS_NOVEDADES_PRODUCTOR		5
19	WS_NOVEDADES_TIMEOUT	3500	5
20	WS_NOVEDADES_TOPICO		5
21	WS_NOVEDADES_VERSION		5
22	WS_TRAMITE_LOCATION_GUIA		5
23	WS_TRAMITE_LOCATION_INFO		5
24	WS_TRAMITE_PASS		5
25	WS_TRAMITE_TIMEOUT	9000	5
26	WS_TRAMITE_USER		5
27	WS_TRAZABILIDAD_HABILITADO	FALSE	5
28	WS_TRAZABILIDAD_LOCATION_CABEZAL		5
29	WS_TRAZABILIDAD_LOCATION_LINEA		5
30	WS_TRAZABILIDAD_MAXINTENTOS	10	5
31	WS_TRAZABILIDAD_TIMEOUT	3500	5
32	WS_TRAZABILIDAD_VERSION		5
\.


--
-- Data for Name: ae_empresas; Type: TABLE DATA; Schema: global; Owner: sae
--

COPY global.ae_empresas (id, nombre, datasource, fecha_baja, org_id, org_codigo, org_nombre, unej_id, unej_codigo, unej_nombre, logo, cc_finalidad, cc_responsable, cc_direccion, logo_texto, timezone, formato_fecha, formato_hora, oid, pie_publico) FROM stdin;
1	Empresa 1	empresa1	\N	0	0	No\nconfigurado	0	0	No configurado	\N	No configurado	No configurado	No\nconfigurado	No configurado	GMT	dd/MM/yyyy	HH:mm	No configurado	
2	Agesic	empresa2	\N	5	138	Presidencia	0	5		\\xffd8ffe10bd64578696600004d4d002a000000080007011200030000000100010000011a00050000000100000062011b0005000000010000006a012800030000000100020000013100020000001f000000720132000200000014000000918769000400000001000000a8000000d4000afc8000002710000afc800000271041646f62652050686f746f73686f702032312e32202857696e646f77732900323032303a30383a30352031363a31343a3036000000000003a00100030000000100010000a00200040000000100000198a0030004000000010000006e0000000000000006010300030000000100060000011a00050000000100000122011b0005000000010000012a012800030000000100020000020100040000000100000132020200040000000100000a9c0000000000000048000000010000004800000001ffd8ffed000c41646f62655f434d0001ffee000e41646f626500648000000001ffdb0084000c08080809080c09090c110b0a0b11150f0c0c0f1518131315131318110c0c0c0c0c0c110c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c010d0b0b0d0e0d100e0e10140e0e0e14140e0e0e0e14110c0c0c0c0c11110c0c0c0c0c0c110c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0cffc0001108002b009f03012200021101031101ffdd0004000affc4013f0000010501010101010100000000000000030001020405060708090a0b0100010501010101010100000000000000010002030405060708090a0b1000010401030204020507060805030c33010002110304211231054151611322718132061491a1b14223241552c16233347282d14307259253f0e1f163733516a2b283264493546445c2a3743617d255e265f2b384c3d375e3f3462794a485b495c4d4e4f4a5b5c5d5e5f55666768696a6b6c6d6e6f637475767778797a7b7c7d7e7f711000202010204040304050607070605350100021103213112044151617122130532819114a1b14223c152d1f0332462e1728292435315637334f1250616a2b283072635c2d2449354a317644555367465e2f2b384c3d375e3f34694a485b495c4d4e4f4a5b5c5d5e5f55666768696a6b6c6d6e6f62737475767778797a7b7c7ffda000c03010002110311003f00f5549249252925c4fd67fad59997983a07d5c2e7e53dc5976454750e1f4e9a2cfa2cf4ff00ed5657f80fe6ff009efe6f6baa607d60b3eacb7131333fcad5d75fa97b6186d7336faccaecff0002ebe3d967fe7b529c440899111e33d7a47f78ade306e85f0bb892e5bea87d6e3d4bfc97d53f43d5a996fb86cf5767f39fa3d3d2cbaa3f58c7ff00afd3fa2f52ba3a1ea19d8dd3b0aecdca76ca286973cf27c9ad1f9cf7bbd8c6fefa6cf1ca32e1235e9fd6f2489022c6cd8497095fd60faf3d74bb23a2e2578d84d710c73c34974685beadeedb6bb70f7fa157a55ff37eb2a99bf5e3eb5e1b9b83978f562e754f1eab9cc243eb77b59b6b16167d2dff00a7aafb2ab3fb0a41cacc9a063c5d63c5ea8f9acf763bd1aef5a3e8c92e73eb4fd6d3d1ecab03069fb5f54c882caa096b438ecacbdb5fe92db2d7b7f45457fcbfd257f9f8f6f54ff191d3eb39f998b55b8cc1bada43584b5bf9da63daeb9bb7f7dbf69f4d363825200dc63c5f2f11e132feea4e400d5135bd0d9eed25c9fd50fad39dd672fa89cd35578b435b6d2008d8c7badd2db8bb6d9b2badbfa4d95aa567d6cfac9d772ada7eab62b462d3ce4da049fdd7937399553ea7d2651b2ec8f4ff0049fa247eef3e23134386b8a44fa63c5e2af72340ea6f61d5ee525c462fd6febbd233eac2fad58cdaeabb4665b00000983638d6eb28baa66e67ade9fa56e3fd3b2a56feb5fd63eab8fd531ba1745d8cccc90d73af7806379736b6337eeadbfcd5965af7b2df67f375ef4bd89f101a6a2c4afd1c2373c4af72357ae9a57e93d62cfc8ebbd331bab63f47bad2dcdcb617d4cdae223dfb77591b1bbfd1b767fc5ae75957f8cac1c8a775d8fd4a9b1ed65921bb581c435d659b2ac2bb6b3e97e8bd7ff8a46eaf9f9357d7de9388cf4fd1b29925d531d60ddf6adfe9e439beb54d7fa356ef4de90c22cfaa32f4ca5e83fbaa33d362350351ddeb125c975dfadd9e3a99e89f5771c65e7374bad23735a63dcc637756cfd16e67ad75d6b29a5ffa2fd258a1d3b33fc6151d471e8ea389564635ee8b2c1b1a2b68d5eef56873b66dfcd63e87fadfcdef43d8970f11318d8e21191a910af705d004f4b0347b04924944bdffd0f5558df5b6beb16f43bd9d209191a7a819fce3aaff000ccc73fe99cdfa3ff817e9762d94918cb864255746f5411608eef0ff00e2dade8629baaa1bb3aa99f577c4baa0619f65fdda2bff000b57f395ddfcf7f815dc2e3feb4fd50c9b329bd6feafcd5d498f0fb2a610cdeee3ed14b9fb6a6e47fa6659fa1caabf9dff0084d5ea8efaccff00ab4d38753075ab2bac5ecadcd1b4bb6fda7ecceb5de97a8cf77a7ea59ff6e29b288e4909c65f39a2247e43ff0078b217106247cbdbf49e4febf3fa764758a6ae94d7bfae35cd6db650625c35c7afdbeefb755f4abb59fd1eafe91fe0fd3d2fae95f581f527186739afcd63e8fb73ebd1a5dab43b4f6ff49751fc8f51687d51faa55f46afed99916f53b41def9dc2a0ef73aaa9eefa4f7ff87bff00c33d6fe5e2e3e6635b8994c16d1734b2cacf05a79e13a59a3196311f54711f98ef2feea040912274331b357eafbf12ce8780ec38fb3fa158ac0ed0d0d731dfcb6386cb3fe11723fe335f8c727a656d8fb5377b9f1c8a8bab0ddffd7b5bfa3fea5a8c3ea5fd65e976bdbf57fab0af16c25de9dc4b48274f706d5934d966dff0edaa8b153ccff17bd75edaf24e5b3373edb03b29d6bdcd686b6366cb1ecbadb9fedff81af67d0a93b10c71cbc7ee0235a1fa5eafde44b8cc78785b78aea6aff19d97f6d80fb2a8c32ee371ab1f66cffacb32d9ff006eaee1ce6b5a5ce21ad68924e8000b0beb3fd53c6ebcdaee6d87173e811564344c80778aed682c739ad7fbea7b1ecb29b3f9bff09bf0aefaa7f5cb2e9fb2753eb35fd800fd27b9ef25a3fd237d3c7f57febf92f4c2219040998818c44240ff0057f7570e28d8e1e2b24823c7bb93d2765b89f5b4f4d1143e873b1c374fd01b329c1ad1ff0085be8237d5b67d73b3a68fd817d0cc36d8f05a7d2de1f3bdfeaefa6c7ee76e6b99b9ff00ccfa6b43fc5c5551cfeb1663175986d35d54d8fe5ed0ebdd5b9d01addcea7d3b1dff001a8f93f51ba9e0e6bf2feac67fd89b6fd2c7792d6b46a431af6b6e6db53377e8aaba8fd0ff00a553cf2444e703c3fa320720e28fc9fa4c7189e18c85f51e935d5a3d4fa0ff008c0ead8e31ba8bb1f22a6bb7b5bbab690e873370757435df45ee6ad2eadf52727a9e174fc86e40c7ead8b8b4d37171258f756377f3b5edb6ab2bb8d9e9e433fedbfdcb9f577a2fd66c3ceb333abf55fb4b6c6ed38cd97b091f42c0f7b696d3b3f768a3f49fe1143af740fac97754fdabd17a9fa366c6d7f66b3db586b64fb76b2eaaddef76efd3e3bdff00f0ca3f74f10889c23c3a89462782cfe8aee014491237d09f5388eebbf5cbeab595b7ad3066e113b43c90e2e03dce14e5b031feb6df7eccdabf4aad755babbffc60f43bea3babb7198f61f16b866b9a54323eac7d72ebcfaa9ebd974d78553b7385505dc1639f5b195b19eaec7398cb2db3f43bff00995ab9df57336cfad9d33a9e336a6e060d2da9cd2e21e368c9680caf63b737f4f5ff008444cb183771e330989707c9b7a7fc240123df878a35c5f378b97fe2f0b19d53ad55931fb47d525fbbe916b6cb85fb7ff421dfa4ff008ca5774b97fac3f531d9d9dfb5ba4e49c0ea5cb9c2435ee0366fdf59165367a7fa37bdbea32d67f394a074efabbf5cbf68e3e5f53eb3fa3c67077a7492f0f1c3ea7d6eab1a8fd233fc2bebbecff47fa451e4e0c87dce311b1ac4ddf101d17c78a3e9e1275dc3d7a4924abb23ffd1f554924925292492494a4924925292492494c2ef5bd17fa1b7d6da7d3df3b7747b37edf76cddf4971591f557ebb7529c7ea7d62afb25849b195ee220ebb3d16d78dbebff0082baf7b1770929b099ebc0224ff5b878bfc1e3593e1fd2baf0bffb968f47e9187d1b05b85860ec692e7bddabdef3f4edb5c36ee7bbff0051b3f46af24928a5c5c478af8af5b5c2a856ca4924904a92492494a4924925292492494fffd9ffed144450686f746f73686f7020332e30003842494d0425000000000010000000000000000000000000000000003842494d043a000000000143000000100000000100000000000b7072696e744f7574707574000000050000000050737453626f6f6c0100000000496e7465656e756d00000000496e746500000000496d67200000000f7072696e745369787465656e426974626f6f6c000000000b7072696e7465724e616d65544558540000002b005c005c006e00650077007300720076002d0069006d0070005c003500310020002d0020005200690063006f00680020005000690073006f002000340020002d00200043006900750064006100640065006c006100000000000f7072696e7450726f6f6653657475704f626a63000000110041006a0075007300740065002000640065002000700072007500650062006100000000000a70726f6f6653657475700000000100000000426c746e656e756d0000000c6275696c74696e50726f6f660000000970726f6f66434d594b003842494d043b00000000022d00000010000000010000000000127072696e744f75747075744f7074696f6e7300000017000000004370746e626f6f6c0000000000436c6272626f6f6c00000000005267734d626f6f6c000000000043726e43626f6f6c0000000000436e7443626f6f6c00000000004c626c73626f6f6c00000000004e677476626f6f6c0000000000456d6c44626f6f6c0000000000496e7472626f6f6c000000000042636b674f626a630000000100000000000052474243000000030000000052642020646f7562406fe000000000000000000047726e20646f7562406fe0000000000000000000426c2020646f7562406fe000000000000000000042726454556e744623526c74000000000000000000000000426c6420556e744623526c7400000000000000000000000052736c74556e74462350786c40520000000000000000000a766563746f7244617461626f6f6c010000000050675073656e756d00000000506750730000000050675043000000004c656674556e744623526c74000000000000000000000000546f7020556e744623526c7400000000000000000000000053636c20556e74462350726340590000000000000000001063726f705768656e5072696e74696e67626f6f6c000000000e63726f7052656374426f74746f6d6c6f6e67000000000000000c63726f70526563744c6566746c6f6e67000000000000000d63726f705265637452696768746c6f6e67000000000000000b63726f7052656374546f706c6f6e6700000000003842494d03ed000000000010004800000001000200480000000100023842494d042600000000000e000000000000000000003f8000003842494d040d0000000000040000005a3842494d04190000000000040000001e3842494d03f3000000000009000000000000000001003842494d271000000000000a000100000000000000023842494d03f5000000000048002f66660001006c66660006000000000001002f6666000100a1999a0006000000000001003200000001005a00000006000000000001003500000001002d000000060000000000013842494d03f80000000000700000ffffffffffffffffffffffffffffffffffffffffffff03e800000000ffffffffffffffffffffffffffffffffffffffffffff03e800000000ffffffffffffffffffffffffffffffffffffffffffff03e800000000ffffffffffffffffffffffffffffffffffffffffffff03e800003842494d040000000000000200043842494d040200000000000a000000000000000000003842494d04300000000000050101010101003842494d042d0000000000060001000000083842494d040800000000001a00000001000002400000024000000002000003c00000002e60003842494d041e000000000004000000003842494d041a00000000034d0000000600000000000000000000006e000001980000000c00530069006e0020007400ed00740075006c006f002d00310000000100000000000000000000000000000000000000010000000000000000000001980000006e00000000000000000000000000000000010000000000000000000000000000000000000010000000010000000000006e756c6c0000000200000006626f756e64734f626a6300000001000000000000526374310000000400000000546f70206c6f6e6700000000000000004c6566746c6f6e67000000000000000042746f6d6c6f6e670000006e00000000526768746c6f6e670000019800000006736c69636573566c4c73000000014f626a6300000001000000000005736c6963650000001200000007736c69636549446c6f6e67000000000000000767726f757049446c6f6e6700000000000000066f726967696e656e756d0000000c45536c6963654f726967696e0000000d6175746f47656e6572617465640000000054797065656e756d0000000a45536c6963655479706500000000496d672000000006626f756e64734f626a6300000001000000000000526374310000000400000000546f70206c6f6e6700000000000000004c6566746c6f6e67000000000000000042746f6d6c6f6e670000006e00000000526768746c6f6e67000001980000000375726c54455854000000010000000000006e756c6c54455854000000010000000000004d7367655445585400000001000000000006616c74546167544558540000000100000000000e63656c6c54657874497348544d4c626f6f6c010000000863656c6c546578745445585400000001000000000009686f727a416c69676e656e756d0000000f45536c696365486f727a416c69676e0000000764656661756c740000000976657274416c69676e656e756d0000000f45536c69636556657274416c69676e0000000764656661756c740000000b6267436f6c6f7254797065656e756d0000001145536c6963654247436f6c6f7254797065000000004e6f6e6500000009746f704f75747365746c6f6e67000000000000000a6c6566744f75747365746c6f6e67000000000000000c626f74746f6d4f75747365746c6f6e67000000000000000b72696768744f75747365746c6f6e6700000000003842494d042800000000000c000000023ff00000000000003842494d0414000000000004000000093842494d040c000000000ab8000000010000009f0000002b000001e0000050a000000a9c00180001ffd8ffed000c41646f62655f434d0001ffee000e41646f626500648000000001ffdb0084000c08080809080c09090c110b0a0b11150f0c0c0f1518131315131318110c0c0c0c0c0c110c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c010d0b0b0d0e0d100e0e10140e0e0e14140e0e0e0e14110c0c0c0c0c11110c0c0c0c0c0c110c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0cffc0001108002b009f03012200021101031101ffdd0004000affc4013f0000010501010101010100000000000000030001020405060708090a0b0100010501010101010100000000000000010002030405060708090a0b1000010401030204020507060805030c33010002110304211231054151611322718132061491a1b14223241552c16233347282d14307259253f0e1f163733516a2b283264493546445c2a3743617d255e265f2b384c3d375e3f3462794a485b495c4d4e4f4a5b5c5d5e5f55666768696a6b6c6d6e6f637475767778797a7b7c7d7e7f711000202010204040304050607070605350100021103213112044151617122130532819114a1b14223c152d1f0332462e1728292435315637334f1250616a2b283072635c2d2449354a317644555367465e2f2b384c3d375e3f34694a485b495c4d4e4f4a5b5c5d5e5f55666768696a6b6c6d6e6f62737475767778797a7b7c7ffda000c03010002110311003f00f5549249252925c4fd67fad59997983a07d5c2e7e53dc5976454750e1f4e9a2cfa2cf4ff00ed5657f80fe6ff009efe6f6baa607d60b3eacb7131333fcad5d75fa97b6186d7336faccaecff0002ebe3d967fe7b529c440899111e33d7a47f78ade306e85f0bb892e5bea87d6e3d4bfc97d53f43d5a996fb86cf5767f39fa3d3d2cbaa3f58c7ff00afd3fa2f52ba3a1ea19d8dd3b0aecdca76ca286973cf27c9ad1f9cf7bbd8c6fefa6cf1ca32e1235e9fd6f2489022c6cd8497095fd60faf3d74bb23a2e2578d84d710c73c34974685beadeedb6bb70f7fa157a55ff37eb2a99bf5e3eb5e1b9b83978f562e754f1eab9cc243eb77b59b6b16167d2dff00a7aafb2ab3fb0a41cacc9a063c5d63c5ea8f9acf763bd1aef5a3e8c92e73eb4fd6d3d1ecab03069fb5f54c882caa096b438ecacbdb5fe92db2d7b7f45457fcbfd257f9f8f6f54ff191d3eb39f998b55b8cc1bada43584b5bf9da63daeb9bb7f7dbf69f4d363825200dc63c5f2f11e132feea4e400d5135bd0d9eed25c9fd50fad39dd672fa89cd35578b435b6d2008d8c7badd2db8bb6d9b2badbfa4d95aa567d6cfac9d772ada7eab62b462d3ce4da049fdd7937399553ea7d2651b2ec8f4ff0049fa247eef3e23134386b8a44fa63c5e2af72340ea6f61d5ee525c462fd6febbd233eac2fad58cdaeabb4665b00000983638d6eb28baa66e67ade9fa56e3fd3b2a56feb5fd63eab8fd531ba1745d8cccc90d73af7806379736b6337eeadbfcd5965af7b2df67f375ef4bd89f101a6a2c4afd1c2373c4af72357ae9a57e93d62cfc8ebbd331bab63f47bad2dcdcb617d4cdae223dfb77591b1bbfd1b767fc5ae75957f8cac1c8a775d8fd4a9b1ed65921bb581c435d659b2ac2bb6b3e97e8bd7ff8a46eaf9f9357d7de9388cf4fd1b29925d531d60ddf6adfe9e439beb54d7fa356ef4de90c22cfaa32f4ca5e83fbaa33d362350351ddeb125c975dfadd9e3a99e89f5771c65e7374bad23735a63dcc637756cfd16e67ad75d6b29a5ffa2fd258a1d3b33fc6151d471e8ea389564635ee8b2c1b1a2b68d5eef56873b66dfcd63e87fadfcdef43d8970f11318d8e21191a910af705d004f4b0347b04924944bdffd0f5558df5b6beb16f43bd9d209191a7a819fce3aaff000ccc73fe99cdfa3ff817e9762d94918cb864255746f5411608eef0ff00e2dade8629baaa1bb3aa99f577c4baa0619f65fdda2bff000b57f395ddfcf7f815dc2e3feb4fd50c9b329bd6feafcd5d498f0fb2a610cdeee3ed14b9fb6a6e47fa6659fa1caabf9dff0084d5ea8efaccff00ab4d38753075ab2bac5ecadcd1b4bb6fda7ecceb5de97a8cf77a7ea59ff6e29b288e4909c65f39a2247e43ff0078b217106247cbdbf49e4febf3fa764758a6ae94d7bfae35cd6db650625c35c7afdbeefb755f4abb59fd1eafe91fe0fd3d2fae95f581f527186739afcd63e8fb73ebd1a5dab43b4f6ff49751fc8f51687d51faa55f46afed99916f53b41def9dc2a0ef73aaa9eefa4f7ff87bff00c33d6fe5e2e3e6635b8994c16d1734b2cacf05a79e13a59a3196311f54711f98ef2feea040912274331b357eafbf12ce8780ec38fb3fa158ac0ed0d0d731dfcb6386cb3fe11723fe335f8c727a656d8fb5377b9f1c8a8bab0ddffd7b5bfa3fea5a8c3ea5fd65e976bdbf57fab0af16c25de9dc4b48274f706d5934d966dff0edaa8b153ccff17bd75edaf24e5b3373edb03b29d6bdcd686b6366cb1ecbadb9fedff81af67d0a93b10c71cbc7ee0235a1fa5eafde44b8cc78785b78aea6aff19d97f6d80fb2a8c32ee371ab1f66cffacb32d9ff006eaee1ce6b5a5ce21ad68924e8000b0beb3fd53c6ebcdaee6d87173e811564344c80778aed682c739ad7fbea7b1ecb29b3f9bff09bf0aefaa7f5cb2e9fb2753eb35fd800fd27b9ef25a3fd237d3c7f57febf92f4c2219040998818c44240ff0057f7570e28d8e1e2b24823c7bb93d2765b89f5b4f4d1143e873b1c374fd01b329c1ad1ff0085be8237d5b67d73b3a68fd817d0cc36d8f05a7d2de1f3bdfeaefa6c7ee76e6b99b9ff00ccfa6b43fc5c5551cfeb1663175986d35d54d8fe5ed0ebdd5b9d01addcea7d3b1dff001a8f93f51ba9e0e6bf2feac67fd89b6fd2c7792d6b46a431af6b6e6db53377e8aaba8fd0ff00a553cf2444e703c3fa320720e28fc9fa4c7189e18c85f51e935d5a3d4fa0ff008c0ead8e31ba8bb1f22a6bb7b5bbab690e873370757435df45ee6ad2eadf52727a9e174fc86e40c7ead8b8b4d37171258f756377f3b5edb6ab2bb8d9e9e433fedbfdcb9f577a2fd66c3ceb333abf55fb4b6c6ed38cd97b091f42c0f7b696d3b3f768a3f49fe1143af740fac97754fdabd17a9fa366c6d7f66b3db586b64fb76b2eaaddef76efd3e3bdff00f0ca3f74f10889c23c3a89462782cfe8aee014491237d09f5388eebbf5cbeab595b7ad3066e113b43c90e2e03dce14e5b031feb6df7eccdabf4aad755babbffc60f43bea3babb7198f61f16b866b9a54323eac7d72ebcfaa9ebd974d78553b7385505dc1639f5b195b19eaec7398cb2db3f43bff00995ab9df57336cfad9d33a9e336a6e060d2da9cd2e21e368c9680caf63b737f4f5ff008444cb183771e330989707c9b7a7fc240123df878a35c5f378b97fe2f0b19d53ad55931fb47d525fbbe916b6cb85fb7ff421dfa4ff008ca5774b97fac3f531d9d9dfb5ba4e49c0ea5cb9c2435ee0366fdf59165367a7fa37bdbea32d67f394a074efabbf5cbf68e3e5f53eb3fa3c67077a7492f0f1c3ea7d6eab1a8fd233fc2bebbecff47fa451e4e0c87dce311b1ac4ddf101d17c78a3e9e1275dc3d7a4924abb23ffd1f554924925292492494a4924925292492494c2ef5bd17fa1b7d6da7d3df3b7747b37edf76cddf4971591f557ebb7529c7ea7d62afb25849b195ee220ebb3d16d78dbebff0082baf7b1770929b099ebc0224ff5b878bfc1e3593e1fd2baf0bffb968f47e9187d1b05b85860ec692e7bddabdef3f4edb5c36ee7bbff0051b3f46af24928a5c5c478af8af5b5c2a856ca4924904a92492494a4924925292492494fffd93842494d042100000000005700000001010000000f00410064006f00620065002000500068006f0074006f00730068006f00700000001400410064006f00620065002000500068006f0074006f00730068006f00700020003200300032003000000001003842494d04060000000000070006000000010100ffe10dfb687474703a2f2f6e732e61646f62652e636f6d2f7861702f312e302f003c3f787061636b657420626567696e3d22efbbbf222069643d2257354d304d7043656869487a7265537a4e54637a6b633964223f3e203c783a786d706d65746120786d6c6e733a783d2261646f62653a6e733a6d6574612f2220783a786d70746b3d2241646f626520584d5020436f726520362e302d633030322037392e3136343436302c20323032302f30352f31322d31363a30343a31372020202020202020223e203c7264663a52444620786d6c6e733a7264663d22687474703a2f2f7777772e77332e6f72672f313939392f30322f32322d7264662d73796e7461782d6e7323223e203c7264663a4465736372697074696f6e207264663a61626f75743d222220786d6c6e733a786d703d22687474703a2f2f6e732e61646f62652e636f6d2f7861702f312e302f2220786d6c6e733a786d704d4d3d22687474703a2f2f6e732e61646f62652e636f6d2f7861702f312e302f6d6d2f2220786d6c6e733a73744576743d22687474703a2f2f6e732e61646f62652e636f6d2f7861702f312e302f73547970652f5265736f757263654576656e74232220786d6c6e733a64633d22687474703a2f2f7075726c2e6f72672f64632f656c656d656e74732f312e312f2220786d6c6e733a70686f746f73686f703d22687474703a2f2f6e732e61646f62652e636f6d2f70686f746f73686f702f312e302f2220786d703a43726561746f72546f6f6c3d2241646f62652050686f746f73686f702032312e32202857696e646f7773292220786d703a437265617465446174653d22323032302d30382d30355431363a31343a30362d30333a30302220786d703a4d65746164617461446174653d22323032302d30382d30355431363a31343a30362d30333a30302220786d703a4d6f64696679446174653d22323032302d30382d30355431363a31343a30362d30333a30302220786d704d4d3a496e7374616e636549443d22786d702e6969643a35326630633535642d363437362d653334392d393661632d6136623935373037343531382220786d704d4d3a446f63756d656e7449443d2261646f62653a646f6369643a70686f746f73686f703a31303361306530642d333962392d653134362d393034612d6462626562646533653837662220786d704d4d3a4f726967696e616c446f63756d656e7449443d22786d702e6469643a62326264353164362d356536312d663534372d393865642d356435303338303435646438222064633a666f726d61743d22696d6167652f6a706567222070686f746f73686f703a436f6c6f724d6f64653d2233222070686f746f73686f703a49434350726f66696c653d22735247422049454336313936362d322e31223e203c786d704d4d3a486973746f72793e203c7264663a5365713e203c7264663a6c692073744576743a616374696f6e3d2263726561746564222073744576743a696e7374616e636549443d22786d702e6969643a62326264353164362d356536312d663534372d393865642d356435303338303435646438222073744576743a7768656e3d22323032302d30382d30355431363a31343a30362d30333a3030222073744576743a736f6674776172654167656e743d2241646f62652050686f746f73686f702032312e32202857696e646f777329222f3e203c7264663a6c692073744576743a616374696f6e3d227361766564222073744576743a696e7374616e636549443d22786d702e6969643a35326630633535642d363437362d653334392d393661632d613662393537303734353138222073744576743a7768656e3d22323032302d30382d30355431363a31343a30362d30333a3030222073744576743a736f6674776172654167656e743d2241646f62652050686f746f73686f702032312e32202857696e646f777329222073744576743a6368616e6765643d222f222f3e203c2f7264663a5365713e203c2f786d704d4d3a486973746f72793e203c2f7264663a4465736372697074696f6e3e203c2f7264663a5244463e203c2f783a786d706d6574613e2020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020203c3f787061636b657420656e643d2277223f3effe20c584943435f50524f46494c4500010100000c484c696e6f021000006d6e74725247422058595a2007ce00020009000600310000616373704d5346540000000049454320735247420000000000000000000000000000f6d6000100000000d32d4850202000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001163707274000001500000003364657363000001840000006c77747074000001f000000014626b707400000204000000147258595a00000218000000146758595a0000022c000000146258595a0000024000000014646d6e640000025400000070646d6464000002c400000088767565640000034c0000008676696577000003d4000000246c756d69000003f8000000146d6561730000040c0000002474656368000004300000000c725452430000043c0000080c675452430000043c0000080c625452430000043c0000080c7465787400000000436f70797269676874202863292031393938204865776c6574742d5061636b61726420436f6d70616e790000646573630000000000000012735247422049454336313936362d322e31000000000000000000000012735247422049454336313936362d322e31000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000058595a20000000000000f35100010000000116cc58595a200000000000000000000000000000000058595a200000000000006fa2000038f50000039058595a2000000000000062990000b785000018da58595a2000000000000024a000000f840000b6cf64657363000000000000001649454320687474703a2f2f7777772e6965632e636800000000000000000000001649454320687474703a2f2f7777772e6965632e63680000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000064657363000000000000002e4945432036313936362d322e312044656661756c742052474220636f6c6f7572207370616365202d207352474200000000000000000000002e4945432036313936362d322e312044656661756c742052474220636f6c6f7572207370616365202d20735247420000000000000000000000000000000000000000000064657363000000000000002c5265666572656e63652056696577696e6720436f6e646974696f6e20696e2049454336313936362d322e3100000000000000000000002c5265666572656e63652056696577696e6720436f6e646974696f6e20696e2049454336313936362d322e31000000000000000000000000000000000000000000000000000076696577000000000013a4fe00145f2e0010cf140003edcc0004130b00035c9e0000000158595a2000000000004c09560050000000571fe76d6561730000000000000001000000000000000000000000000000000000028f0000000273696720000000004352542063757276000000000000040000000005000a000f00140019001e00230028002d00320037003b00400045004a004f00540059005e00630068006d00720077007c00810086008b00900095009a009f00a400a900ae00b200b700bc00c100c600cb00d000d500db00e000e500eb00f000f600fb01010107010d01130119011f0125012b01320138013e0145014c0152015901600167016e0175017c0183018b0192019a01a101a901b101b901c101c901d101d901e101e901f201fa0203020c0214021d0226022f02380241024b0254025d02670271027a0284028e029802a202ac02b602c102cb02d502e002eb02f50300030b03160321032d03380343034f035a03660372037e038a039603a203ae03ba03c703d303e003ec03f9040604130420042d043b0448045504630471047e048c049a04a804b604c404d304e104f004fe050d051c052b053a05490558056705770586059605a605b505c505d505e505f6060606160627063706480659066a067b068c069d06af06c006d106e306f507070719072b073d074f076107740786079907ac07bf07d207e507f8080b081f08320846085a086e0882089608aa08be08d208e708fb09100925093a094f09640979098f09a409ba09cf09e509fb0a110a270a3d0a540a6a0a810a980aae0ac50adc0af30b0b0b220b390b510b690b800b980bb00bc80be10bf90c120c2a0c430c5c0c750c8e0ca70cc00cd90cf30d0d0d260d400d5a0d740d8e0da90dc30dde0df80e130e2e0e490e640e7f0e9b0eb60ed20eee0f090f250f410f5e0f7a0f960fb30fcf0fec1009102610431061107e109b10b910d710f511131131114f116d118c11aa11c911e81207122612451264128412a312c312e31303132313431363138313a413c513e5140614271449146a148b14ad14ce14f01512153415561578159b15bd15e0160316261649166c168f16b216d616fa171d17411765178917ae17d217f7181b18401865188a18af18d518fa19201945196b199119b719dd1a041a2a1a511a771a9e1ac51aec1b141b3b1b631b8a1bb21bda1c021c2a1c521c7b1ca31ccc1cf51d1e1d471d701d991dc31dec1e161e401e6a1e941ebe1ee91f131f3e1f691f941fbf1fea20152041206c209820c420f0211c2148217521a121ce21fb22272255228222af22dd230a23382366239423c223f0241f244d247c24ab24da250925382568259725c725f726272657268726b726e827182749277a27ab27dc280d283f287128a228d429062938296b299d29d02a022a352a682a9b2acf2b022b362b692b9d2bd12c052c392c6e2ca22cd72d0c2d412d762dab2de12e162e4c2e822eb72eee2f242f5a2f912fc72ffe3035306c30a430db3112314a318231ba31f2322a3263329b32d4330d3346337f33b833f1342b3465349e34d83513354d358735c235fd3637367236ae36e937243760379c37d738143850388c38c839053942397f39bc39f93a363a743ab23aef3b2d3b6b3baa3be83c273c653ca43ce33d223d613da13de03e203e603ea03ee03f213f613fa23fe24023406440a640e74129416a41ac41ee4230427242b542f7433a437d43c044034447448a44ce45124555459a45de4622466746ab46f04735477b47c04805484b489148d7491d496349a949f04a374a7d4ac44b0c4b534b9a4be24c2a4c724cba4d024d4a4d934ddc4e254e6e4eb74f004f494f934fdd5027507150bb51065150519b51e65231527c52c75313535f53aa53f65442548f54db5528557555c2560f565c56a956f75744579257e0582f587d58cb591a596959b85a075a565aa65af55b455b955be55c355c865cd65d275d785dc95e1a5e6c5ebd5f0f5f615fb36005605760aa60fc614f61a261f56249629c62f06343639763eb6440649464e9653d659265e7663d669266e8673d679367e9683f689668ec6943699a69f16a486a9f6af76b4f6ba76bff6c576caf6d086d606db96e126e6b6ec46f1e6f786fd1702b708670e0713a719571f0724b72a67301735d73b87414747074cc7528758575e1763e769b76f8775677b37811786e78cc792a798979e77a467aa57b047b637bc27c217c817ce17d417da17e017e627ec27f237f847fe5804780a8810a816b81cd8230829282f4835783ba841d848084e3854785ab860e867286d7873b879f8804886988ce8933899989fe8a648aca8b308b968bfc8c638cca8d318d988dff8e668ece8f368f9e9006906e90d6913f91a89211927a92e3934d93b69420948a94f4955f95c99634969f970a977597e0984c98b89924999099fc9a689ad59b429baf9c1c9c899cf79d649dd29e409eae9f1d9f8b9ffaa069a0d8a147a1b6a226a296a306a376a3e6a456a4c7a538a5a9a61aa68ba6fda76ea7e0a852a8c4a937a9a9aa1caa8fab02ab75abe9ac5cacd0ad44adb8ae2daea1af16af8bb000b075b0eab160b1d6b24bb2c2b338b3aeb425b49cb513b58ab601b679b6f0b768b7e0b859b8d1b94ab9c2ba3bbab5bb2ebba7bc21bc9bbd15bd8fbe0abe84beffbf7abff5c070c0ecc167c1e3c25fc2dbc358c3d4c451c4cec54bc5c8c646c6c3c741c7bfc83dc8bcc93ac9b9ca38cab7cb36cbb6cc35ccb5cd35cdb5ce36ceb6cf37cfb8d039d0bad13cd1bed23fd2c1d344d3c6d449d4cbd54ed5d1d655d6d8d75cd7e0d864d8e8d96cd9f1da76dafbdb80dc05dc8add10dd96de1cdea2df29dfafe036e0bde144e1cce253e2dbe363e3ebe473e4fce584e60de696e71fe7a9e832e8bce946e9d0ea5beae5eb70ebfbec86ed11ed9cee28eeb4ef40efccf058f0e5f172f1fff28cf319f3a7f434f4c2f550f5def66df6fbf78af819f8a8f938f9c7fa57fae7fb77fc07fc98fd29fdbafe4bfedcff6dffffffee000e41646f626500644000000001ffdb0084000202020202020202020203020202030403020203040504040404040506050505050505060607070807070609090a0a09090c0c0c0c0c0c0c0c0c0c0c0c0c0c0c01030303050405090606090d0a090a0d0f0e0e0e0e0f0f0c0c0c0c0c0f0f0c0c0c0c0c0c0f0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0cffc0001108006e019803011100021101031101ffdd00040033ffc401a20000000701010101010000000000000000040503020601000708090a0b0100020203010101010100000000000000010002030405060708090a0b1000020103030204020607030402060273010203110400052112314151061361227181143291a10715b14223c152d1e1331662f0247282f12543345392a2b26373c235442793a3b33617546474c3d2e2082683090a181984944546a4b456d355281af2e3f3c4d4e4f465758595a5b5c5d5e5f566768696a6b6c6d6e6f637475767778797a7b7c7d7e7f738485868788898a8b8c8d8e8f82939495969798999a9b9c9d9e9f92a3a4a5a6a7a8a9aaabacadaeafa110002020102030505040506040803036d0100021103042112314105511361220671819132a1b1f014c1d1e1234215526272f1332434438216925325a263b2c20773d235e2448317549308090a18192636451a2764745537f2a3b3c32829d3e3f38494a4b4c4d4e4f465758595a5b5c5d5e5f5465666768696a6b6c6d6e6f6475767778797a7b7c7d7e7f738485868788898a8b8c8d8e8f839495969798999a9b9c9d9e9f92a3a4a5a6a7a8a9aaabacadaeafaffda000c03010002110311003f00fbf98abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762aff00ffd0fbf98abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762ac13f303f337c87f959a3a6bde7ff32daf96f4c9a5f46da59c492493494a948608564964206e4229a0dce65697479b552e1c51323f8ebc9ab36786117334157c85f98fe47fccfd13fc45e42f31daf99348129825b8b7e68f14a002639a1955248da841a3a83435e98355a3cba59f0658989fc7c170e78651c503619b66336bb15762aec55d8abb15762aec55d8abb157ffd1fbf98abb15762aec55d8abb15762a866bdb34ba8ec9eee14bd954bc56864512b28eaca95a9029d6992e13575b22c5d227229539a686de29279e54821894b4b348c1555475258d0018402760a4d3504f0dcc51cf6d325c4128e514d1b074607bab0a823120834541b55c0aec55d8abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762aec55d8abb157e6cff00ce7f7e4cfe63fe609f23f9b3c93a35ef9aec3cbb6f7765aa683a746d3dd40f70f1c8b7115ba55e40e178bf0048e2bb53a75fecb768e0d3f1e3c8444ca882797badd276be9b264e19445d744c7fe700bf273f313f2e74ef3d798fcf1a4de795edbcd5f518349f2f5fa34374ff00543333dc4d6ef468bfbd0aa18063f11a538931f6a7b4306a250863225c376472deb6bebc93d91a6c9884a531575b3f457392774ec55d8abb15762aec55d8abb15762aec55fffd2fbf98abb15762aec55d8abb157c91ff3947ff3947a27e44e88da3e8ed06b1f997ac405b47d1d8f28eca36a81797801a8507ec27573e0a091beec5ec59eba7c52db18e67bfc87e3675dafd78d38a1bc8fe2cbf0bb53f3779b35ff0034cbe70d475ed42fbcdd79782f0eb9eb38bbfacf2aa346e84142a68102502ec16800cf4b869f1e3c7e18881102aba53ca4b24a52e2277ef7f4abf97371e63bbfcbef235d79be3687cd773a069b2f996275e0eb7ef6b19b90cbb716f50b547639e3dab1019a631fd3c46bdd7b3dbe13238e3c5ce85fbdf9c5ff003f28d4fce3045f973a5c12dcc1e43be5bc92f9612c209f528da331a5c5362523f8a30de2e47434ebbd8f8623e248d718af9797e9f83a5edb94fd23f87f4be4cff9c63ff9c9df307e43ebeb63a83dc6b5f971abca3f4ef9783726b666343796418d1645fda5d848366a10acbbded9ec686be1636c8391eff23e5f73aed0ebe5a7951de2798fd21fbcbe58f33e83e72d074bf33f963548359d0759816e34ed46ddaa9221dbbd0ab290432900a9041008233cc73619e19984c548730f5b8f2472444a26c14fb2a66ec55d8ab03fcc5fccdf24fe54f9766f33f9eb5d8344d3233c2dd5eaf3dccb4a886de15abc8e7c146c3734504e656934797553e0c51b3f77bda73678618f14cd07e627e61ffcfc87ccb75753dafe57f92acb48d394958b57f3017babb917b38b78248e288fb17906767a4f64318179a649ee8ec3e6773f63a2cddb7227f771a1e6f105ff009cf3ff009c8f5b8f58f9974c78eb5faa1d26d3d3a7854207ff0086cd97fa18d0d5709f99717f95b51de3e41f40fe5b7fcfc87515bab7b1fcd8f25db4d65210927983cb85e39621d393d9dc48e24f13c655f653d3355acf642357827bf74bf58fd4e660edb375923f11fa992fe79ffce7b6afe52f356916df93e3ca9e72f286aba1db6a2354be86f24b88ee659a749209162bab7319458d494740c2bbec4653d99ecbc72e32751c519891142aab6df91bf7b3d5f6b98487874411e7fadef9ff003887ff00390be71ff9c80d2bcef7fe6fd2b46d2e5f2d5dd94162ba3c57112badcc72b3993eb171392418c528466afb7bb2b16825018c93c40f3ae9ee01cceced64f52246400aee7d899cfbb27c91f9ebff00398df969f92f7173e5f8449e76f3bdb8226f2e69d2aa456afd96f6e887588ff90aaee3ba8041cdf7667b3f9f5a38fe88779ebee1d7ec0ebb57da58f06dce5ddfadf9e9e65ff9f85fe7a6af73236856fa0794ecea7d086dac8ddca17b7392e9e4563ee1147b675787d94d2407ab8a47df5f73a79f6ce6972a081d07fe7e07ff003901a55c24ba9dc683e66b7047a96d7da72c351df8b59bdb907c09afcb2597d95d1cc7a78a3ee3faed8c3b6338e747e1fa9f6b7e59ff00ce7cfe5f79df4cbfb2f32d9afe5f79ca0b29e5d362bf9bd7d2af2e238d99234ba023285d87d9902f5e2aecd9ce6b3d97cd8240c0f1c2f7afa80f77eaf93b4c1daf8f20a97a65f63e51ff00a290fe757fd4a3e49ffa44d47fef239bdff421a5fe74fe63fe25d7ff002de6ee8fdbfadfb23e5fd426d5b41d1355b8444b8d4ec2daee748c1081e6895d828249a0276a9cf3ecb0109988e8487a581e2883deadab6afa5e83a6deeb3adea36fa4e93a6c4d3ea1a95dc8b0c10c49bb3c92390140f73831e3964908c4593c80594844593403f36bf35ffe7e31a26937773a4fe5179617cccf0314ff0014eb46482c9987786d1384d229f1778cff00927ae75fa1f64a731c59e5c3e439fc4f2fbdd26a3b6a31358c5f99fd4f952f7fe73dbfe7232eae0cd06bfa4e9b19351696da55b3463dab3895ff00e1b3791f65f42051893f13fa29c03dafa83d40f83d1bc91ff3f1bfccdd2ae618bcf5e56d1bcd9a65409e6b11269b7a01d8b060d2c2d4eb4f4857f98661ea7d91c131fba9189f3dc7e83f6b762edac80fac023e4fd33fc9cfcfcfcb8fcf1d2a4bff00256ac7f48d9a2b6afe59bd021d42cf96c0c910660c84ec1d1992bb56bb671dda1d979f432ac8363c88e47f1dc5de69b578f502e277eeeaf68cd7394f15fcf2fcf4f27fe43f94c798fccc5ef6fef9da0f2ef972dd80b9bfb85152aa4d42468083248451411b162aadb1ecdecccbafc9c10d80e67a01f8e41c5d5eae1a78f14be03bdf8eff00983ff39bdf9f5e77bcb8fd19e645f21e8eec7eafa4e828b13aafecf3bb70d3b353a9575527a28ceff49ecde8f00de3c67be5fab93cde6ed5cf90ec784797eb790c1ff3903f9eb6f38b88ff0038fce8d2035e32eb97d2a7fc8b92665fc33607b2b48457850ff4a3f538c359987f1cbe65ee7e45ff009cf2fcfaf29cd0a6b9aa58f9f74c4204967abdb247370efc2e6d442fcbde4e7f2cd66a7d98d1e51e90607c8fe837f653978bb5b3c399e21e6fd5cff9c7aff9c82d13fe720bcb5a8eb7a5797b51f2edde873c569acda5d8596dc4f2a170b6d74bc44a028a9aaab0a8aad082787ed5eca9f67e411948481dc573f88e8f41a3d60d4c49008a7cbdff0039c1f9f9f9b3f93fe6af2369bf975e6bff000ed96b1a4dc5cea30fd42c2efd49639c22b72bbb799968bb514819baf66fb2f4dacc7339a3c44115b91d3c88703b57579704a2206ac770fd2987fce0d7e7afe6a7e716abf98b6df98de69ff1143a0da69b2e949f51b1b4f49ae1ee0486b676f096e4117ed5694db21ed2f6669f471c670c786c9bdc9e55de4b2ecad5e5ce65c66eaba0fd0fd12ce4ddcbe62fcf3ff9caff00cb3fc8d32695a84f2799fce6503c5e51d3194c91721556bb98d52dd48a1a105c82084237cdcf66761e7d77a87a61fce3fa075fbbcdc0d5f6863d3ec779770fd2fce4f357fcfc43f3af58b997fc35a6e81e50b0a9fabc71db35f5c85f0926b8631b1f7112fcb3aec1ec9e9603d66523efa1f67eb74b93b67348fa401f6a41a3ff00ce7f7fce4369b70935f6a3a1f986106ad697da6471a11e15b36b761f7e5b93d96d1486c251f71fd76c63daf9c7320fc3f53ed8fc99ff009cfcf20f9eaf2d340fcc4d387e5deb974cb15beaad37afa44d21d8069982bdb927a7a80a0ef20ce73b43d96cd8019e23c71eefe2fdbf0dfc9d9e9bb5e190d4c709fb3f63eff0410083507a1ce59dc3b15762aec55d8abfffd3fbf98abb15762aec55d8abe4bff9ca1ff9ca1d0ff223433a569460d67f32b5980b689a231e51da46d502f2f0035080fd84d8c8450514330def62f62cf5d3e296d8c733dfe43f1b3aed7ebe3a78d0de47f165f841e60f306bde70d7b52f31798b51b8d6f5fd6ee0cfa86a139e72cd2beddba00281540a014000000cf4dc58a18602101511c83c9ce729c8ca46c97eb3ff00ce1f7fce1f2f95974cfcd5fcd5d303799d82dcf94bca572b51a683f125d5d237fc7c77443fdd753fbcfeef85edfedff16f0603e9fe2977f90f2fbfddcfa1ecdecde0ac9906fd0777edfb9fa599c7bbc625e79f23795ff31fcafaa793fce1a5c7ab685ab47c2e2ddf66461ba4b138dd2443bab0dc1cbf4da9c9a6c832633520d7971472c4c642c17e03ff00ce457fce3b799ff20bcd3f52bbf5356f276aeeede55f3404a2cc837304f4d9278c7da1d187c4bb540f52ec9ed6c7afc763698e63f48f2790d6e8a5a6957389e47f1d5917fce317fce4eebdf90baf0b0bf33eb3f973acce0ebfa00357b77345379661880b2280392d40900a1a10acb4f6cf63435f0b1b641c8f7f91f2fb99e875d2d3ca8ef13cc7e90fde7f2d799742f38e83a5f99fcb3a9c1ac685acc0b73a6ea36edc92446fb8ab290432900a9041008233cc73619e19984c548730f5b09c6711289b053cca99b19f39f9bb44f2179535ff0039798ee7eaba2f972ce4bdbf946ec5631b2202472776a2a8eec40cbb4f8279f2471c39c8d35e5c831c4ca5c83f9d3fceafce5f357e7779d6fbcd9e64b868ed833c5e5ed095cb5be9d67caa90c636058800bbd2aedbf4a01eb5d9dd9f8f458863873ea7bcfe39773c66ab532d44f8a5f01dcf7ffc89ff009c20f3f7e6d69967e6af326a0be40f27dfaacba6dc5c4067d42f623bac905b728c246c3a3c8c2bb32ab29ae6abb4fda4c3a49184071cc73ee1ef3dfe41ccd27654f30e297a47dafaf64ff9f6d7e549b231c5e7af3626a3c682e9dac5a1e5e3e88b556a7b7a9f4e6847b5fa8bfa235f1fd7fa1d8ff2262afa8dfc3f53e26fcfbff9c36fcc2fc94b29fccb67751f9dfc8f011f5ad76ce1686e2cc13406f2d4b48514934e6aecbfcdc6a01e8fb2fda0c3ad3c04704fb8f23ee3fa1d5eafb332601c43d51eff00d6f8ff0037eeb9fae5ff003ecfff009473f363feda5a57fc99b8ce0fdb1faf17b8fe87a2ec3fa67ef0f59ff9cd5ff9c8ebbfca1f2c5a792fc9f79f56f3ff009ca07717f19fde699a6d4c6d729e12cac0a447b51db62ab5c1f677b206af21c9907a23fec8f77b875f8391da9ad3823c31fa8fd81f8bbe55f2a79abf313ccd65e5bf2be9975e62f32eb731f42d63f8a4918d59e49247202a8dd9ddc8006e4e7a1e7cf8f4f8cce644621e631e3965970c4592fd34f20ffcfb66d5ec20bafccdf3fdcc7a84ca1a6d1bcb9146120277e3f5bba59399f1a42078139c76abdaf375861b77cbf50fd6ef70f620afde4be5fad37f387fcfb5bcb5258cafe40fcc4d4ecf528d4982d7cc10c37304add95a6b58e068c1fe611bff00ab95e9fdb0c80fef71823fa3b7df7f786593b1235e891bf37e657e64fe58f9d3f297ccd73e53f3ce8efa56a90afa96f203cedeea024859ede51f0c88d43b8dc1aab00c081d968f5b8b578fc4c46c7da3c8ba2cf827865c3314580665353fa89f25ff00ca1de53ffb63587fd43a678b6a3fbd97bcfdef778be81ee0fc51ff009cc7ff009c93d43f363cdd7be49f2cea2d17e5af956e9a08520721355bc858abddcb4d9a35604423a53e3eadb7a37b3fd8f1d26319263f7921fe94777ebf93cc7696b8e69f044fa47dbe7fa9e4ff00919ff38d9f989f9f37d31f2e5bc5a5796ac25116ade6ed4392da44f40c6288282d34bc4d78aec36e6ca08273bb4fb6306807af791e511cff006071f49a1c9a83e9d877bf43749ff9f6c7e5ac36489aefe60799b50d4b8fc7756096767016f110cb0dd301ff003d3393c9ed86727d30881e767f48fb9dc47b131d6f237f0fdaf05fcdff00f9f7b79c7ca5a75debdf965af1f3e5959a34b71e5db8845bea811454fa1c19a3b8200af11c18f45563b66d341ed5e2cb211cd1e027af38fc7bbed71353d8d380bc678bcbafed7c2be4ff003879a7f2e3cd3a779a7cafa8cfa1f98b429f9c332d5482a68f14a8766461557461422a0e74da8d3e3d4e33098b897558f24b1484a26887f441f90ff9bfa57e777e5be8de77d3e34b4bd93959f98f4956e5f53d4600beb4553bf12195d09dca32d77ae793f69e825a2ce719dc7307bc7e3ed7b2d26a46a31898f8fbdf8f1ff39d3e73bff347fce41798f499a766d2fc936d69a46936f5f856b025cdc371e9c9a699813d4855f019dffb33a718b4519759d93f3a1f6079bed6ca679c8e91d9987fce0eff00ce3f792bf38757f37798fcfb6e757d1fc9c6ce1b3f2e091e38ee2e6efd56f52e0c655ca4622d941a313f16c2871fda5ed5cba38c618b632bdfb80ae5f36cecad1c339329ee0747ea26a1ff0038c5ff0038fba9591b0b8fca3f2e47015e3ced6d16d26a7fc67b731cb5f7e55ce2e1db3ad89b1965f137f61d9df1d06022b803e46fcd3ff9f73f95b5286e751fca4f31cfe5bd4402d179775976bab073d912e4033c43ddc4bf466fb45ed6e489acf1e21de363f2e47ec75da8ec589df19a3dc797e3e6fb5ff247f2b34dfc9bfcb5f2df9134fe12dc69f0fadae6a0829f5bd467a35ccdbef42df0a57708157b6739da5ad96b33cb29ebc8770e83f1d5da6974e3063101f1f7bf357fe7e57ff29c7e5a7fdb0aeffea246761ec7ff007593fac3ee747db9f5c7dc9aff00cfb3bfe3b9f9b9ff00303a3ffc9dbbc87b63f462f7cbf432ec3faa7f0fd2fad7fe72f7fe7211bf23bc8b0da797e58cf9ff00ce1eadb7973900e2ce18c0f5ef990d41f4f9058c1d8b90685558668bb07b2bf3d9ae7f4479f9f70fd7e4ec7b4759f9786df51e5fadf855a7e9fe67f3ef99a0b0d3e0bef3479afccd78447102f71777773312cccccc4b3126acccc76dc934a9cf4b9cf1e0c766a3188f7001e500964950dc97e957e5c7fcfb7ae6ef4fb7bffcd4f3bc9a55e4ea1a4f2ee811c72bc15df8c97b38642c3a10911507a3b0ce3f59ed7812ac10b1df2fd43f5bbbc1d8962f24abc87eb669e68ff9f6b792a6b193fc17f989ade9ba92a9308d6e2b6bd81d8745636d1dab203e23953c0e63e1f6c3283fbcc608f2b1f7db6e4ec4857a6441f3dff531dff9c79ff9c0bd4b43f38de798bf3b21b0bfd37cb777c7cbbe5db5945cdb6a7225196eee0d01f4149f862750cec3e350838bdbdaded3c6788434d60c86e7918f90f3f3f9794347d90633e2cbc8721dff00b1faa3d361b019c4bbf762aec55d8abb157fffd4fbf98abb15762aec55f297fce4effce4de85f915a0be99a6cd6da97e646b16e5b42d16425a3b546e405e5d84ab04041e2bd5db6d97930de7637634f5d3e236318e67bfc87e3675faed7474f1a1bc8f2fd65f83be66d73cc3e6ad7f51f30799b519f5ad7f5c9cdcdf6a533891e791cd2a0afc3414e2a17650388000a67a761c70c501080a88e8f2792729c8991b25fac3ff003877ff00387e3cb0ba6fe6b7e6ae961bccae12e7ca3e53ba5afe8e07e24bbba8dbfe3e0f5443fdd753fbca08f86f683b7fc5bc180fa7f8a5dfe43cbbfbfddcfa0ecdecde0ac9906fd0777edfb9fa5d9c73bc762aec558879efc89e57fccaf2bea9e4ef38e971eada1ead1f19a16d9e371f62685faa48877561b83ed991a5d564d36419319a23f1f26bcb8a3962632160bf01ff00e7223fe71dfcd1f905e683657a24d5bca1ab48ede56f34aa512741bfa13d3649d07da5e847c4bb74f51ec9ed6c7afc763698e63f48f2790d6e8a5a6951dc1e47f1d5917fce347fce53ea9ff38f9ae47a76b97326a3f96faf4bcf58d02acf2c0fb29bcb2515e2e3a30345900e248650cb8ddb7d918f5d1b1b641c8fe83f8b08d276a8d1caa47d27a7e9fc737ef3f963ccfa079d3cbfa579a7cadaadbeb7e5fd6e05b9d2f54b66e51cb19a8a8ee0820ab022aa4104020e799e5c53c533098a23987b0c5963962270360f22f803fe7e3fe75b9d23f2f7c95e47b598c4be71d567bdd4829fef2db4a48c88d87819ae237f9a0ceabd91d309e69e53fc2287be5fb01f9ba9edbca638e30ef3f73e11ff009c3ffca6b0fcdcfce7d274dd72d96efcb5e59b6935fd7ecdc552e23b678d21b76aec5649a44e43ba0619d376feb8e934a4c7694bd23cafafcbed753d9ba719b300790dcbfa09555455445088802a228a0006c00033cadec1762a87bbb4b5bfb5b9b1beb78af2caf62782f2d2640f1cb148a55d1d1810cac09041ea30c64626c6c420804517f39fff003929f95d07e507e71f9b7c9fa7ab2e84254d43cb9c892458dea89638ea773e912d154ee78d73d6fb1f5a757a58e43f5723ef1faf9bc5ebb078194c472e9ee7df7ff3ecff00f9473f363feda5a57fc99b8ce5bdb1faf17b8fe8771d87f4cfde1f047fce4f79d6e7cfbf9eff00993accd3196dacb579f47d296bf0ada698c6d22e03b07f4cb9f7627be751d8da6183498e3de2cfbe5bba8d7e53933c8f9d7cb67e9fff00ce02fe51e9de51fcac8ff322f2d11fcd1f984d2bc576ebfbcb7d2e095a3861427a095e332b53ed0295fb2338cf6a35f2cda8f041f4c3ed9753f0e5f377bd91a710c5c679cbee7deb9cbbb7762af95bfe730bf2934efcd1fc9af31dd0b446f33f91ed27d77cb57c17f7a3eac9ea5cdb83d4acf1215e35a7308dfb39bbec0d7cb4baa88bf4ccf09f8f23f03f65bafed2d38cd84f7c770fe7e73d51e41fd08fe7479d6e7f2fff00e715b5cf3258cc6df515f29d869fa6ce868f1cfa9241649221fe68fd6e63e59e55d9da61a8ed08c0f2e224fc2cfe87b0d56538f4c6439d0fb767f3fda4d9db6a1aae996179a845a459dedd4305deab38668ada291c2bccea80b158c12c42826836cf52c923189205903977f93c8445900ecfddcf227fce477fce26fe5d794741f25f967f3234fb3d1bcbf6a96d6c82cef834846f24d2116a39492392ee7bb1273ccb53d91da3a8c92c93c64991ef1fafa3d662d6e971404632143dff00a996ff00d0e1ff00ce367fe5d3b1ff00a44bff00fb26ca3f9035dfea67e63f5b67f2969ff9ff007bbfe870ff00e71b3ff2e9d8ff00d225ff00fd9363fc81aeff00533f31fad7f94b4ffcff00bdf945ff00398b7df939e66fcc2b6f3dfe51799ecf598fcd50bbf9bb4db482783d1d4222a3eb349a2887fa42b02dc6bf1ab336ef9dcfb3f1d562c3e16a22470fd24d72eef87dcf3fda4714f271e3377cfdff00b5ecdff3edff003adce9ff00981e74f214b31fd1de63d1c6ab6d113502ef4e9523f84762f15c316a75e03c335ded7e984b0c32f589af81fda3ed727b132d64943a117f27987fce77fe5fea9e53fcf5d5fccb2db38d0bf3020b7d4b49bca1e0668208edaea1e5d39aba7323b2bae66fb31aa8e5d2087f142c1f9d8fc793476b6130cc65d25bbc37f263f3c3cf3f917e6497cc3e4cba85e2bf44835cd0ef14c9677d0a12cab2aa9560c84928ea432d4ef42c0ecbb47b3716bb1f0641cb911cc389a5d54f4f2e28fc477bf54bf2e3fe7e15f94be675b7b3f3d69da87e5e6a8e02cb72eada869c58edb4d027acb53fcd0803bb6713abf653538b7c444c7c8fc8edf6bd060ed8c53da7e93f30fb6fcb3e6df2b79cf4d4d63ca5e61d3bccba5c9b0bed36e63b98c352bc58c6cdc587753b8ef9cde6c1930cb87244c4f98a7698f24720b890479321ca99bf1f7fe7e57ff29c7e5a7fdb0aeffea24677dec7ff007593fac3ee79bedcfae3ee4d7fe7d9dff1dcfcdcff00981d1ffe4edde43db1fa317be5fa19761fd53f87e97cd9ff0039abe75b9f38ff00ce4279ca2798be9fe51f43cbfa5c44ed1ada2069c786f7324a7eecdc7b3ba6187450ef97a8fc797d94e176a65e3cf2f2dbf1f17dadff003eeefca3d3b4ef286abf9c1a95a24dae798ae67d2fcb93ba83f57d3ed5824ef193d1a69832b1f08c01f69b39cf6b35f29651a707d31167cc9e5f21f7bb4ec6d3810390f33b0f73f4ab38f776ec55d8abb15762aec55d8abb157fffd5fbf98abb15762aec55f885ff0039f3f967e6fd17f38755fcc7bbb379fc9fe734b08b49d510f28e0b8b4b18ada4b5937f818981a41b5083b6e1a9e91ecbeb31cf4c3083eb8dd8f226efeda796ed7c138e533e86bee7ce3f92ff00983a1fe557e63795fcd7e61f2cdb79cf4ed16f3d6bad3e521cdb92388b8b43cbd369a23f1af2aa120743c5d76fda3a59eab04a10918923f00f91fc77385a5cd1c3904a42c07f447e4cf39796ff00303cb5a579bbca5aa45ac683acc425b2bc88fd0c8ea7747460559585548a1cf26d469f269f21c7905483d962cb1c911289b0594652d8ec55d8abb157c13ff39c1f9e7f97fe56f23ea7f95979a558f9cfce3e67b752ba35c55a2d250ef1df4cc8cae928eb0aab063f68fc1b3751ecdf6666cb946704c211ebfcef21e5deea3b57570840e3ab91fb3cff0053f08352d1352bbd450d924baa3ea532c76d1a00d3fa8e78ac463403a6c14a8e245294fb23bffa46fd1e07369b2716d66dfd12ff00ce167e57f9bbf297f21740f2df9de26b2d7afaf6ef579746760cf6115e3298edde9501a8bcd947d96620ee0e79876eeaf1ea754658f700017df5d5effb174b3d3698467cec9aeebe8f93ff00e7e6765702f3f27f51a136ad0eb76dcbb2c8ad64fbff00ac0edf2ce87d8e90aca3afa7f4b89db83781f7fe860bff003eddd5acad7f34fceda44eea979ab796bd5b1e5b16faadd426445f13493953c14f8664fb5f8c9d3c2439097de0b5762480cb21de1fb359e7af4cec55d8abf0bffe7e09aad9ea1ff39052dadac81e6d0fcbba6d8ea0a3f6666335d053ff003cee10fd39e97ecac0c74567ac891f60fd0f29db12073fb807d27ff3ecff00f9473f363feda5a57fc99b8cd3fb63f5e2f71fd0e7761fd33f787e627e66595c69bf98ff00981a75d822eac3cc9aadbdc86ebea45792ab57e919d9e8e425820472311f73a2ce2b2481ef3f7bf7dbfe715756b2d67fe71e7f2a2e6c5d5a2b6d0e3b0982f69ec9deda607df9c673cbbb731986b7283fcebf9eef5dd9f212c10aee7d059aa731d8ab07fccdd5acb41fcb9f3eeb3a8baa58e99e5ed4ae6e4b1a5563b590f11e25ba01dce64e8f19c99e111ccc87ded59e4238e44f405fcc467b33c2bf737fe72d2c6e2f3fe70ee79200585858f96ee6e147fbec4f6b1934f62e09cf34ec2901da7bf532fb8bd57688bd27cbf43f0d511e4748e3469249182c71a8a962760001d49cf4a269e55e9dff002a43f3a7ff002d0f9dbff09fd47fea8661ff0029697fd561fe987eb6ff00cae6fe64be45dff2a43f3a7ff2d0f9dbff0009fd47fea863fca5a5ff005587fa61fad7f2b9bf992f9177fca90fce9ffcb43e76ff00c27f51ff00aa18ff0029697fd561fe987eb5fcae6fe64be45dff002a43f3a7ff002d0f9dbff09fd47fea863fca5a5ff5587fa61fad7f2b9bf992f917d79ff3841f95df995e59fcf8d3b58f32f907cc9e5ad26df46d4965d4754d2af2cedcb491aa227ab3c48b524ec2b534cd0fb49adc197466309c646c6c083f73b1ecac19219c19448147982fd63fccafcb0f25fe6d7962e7ca7e78d21355d2e66f56de40785c5ace010b3db4a3e28dd6a771b1155605490785d1eb72e93278988d1fb0f917a1cf8219a3c33161f94ff99dff003eecfcc0d127b9bdfcafd72d3ceba554b41a45fba586a683b2737a5b4b4eec5e3aff002e771a2f6b30cc5668981ef1bc7f58fb5e7f3f63648ef8cd8f91fd4f887ce7f963f985f97771f56f3bf93357f2cb16e114f7d6b224121ff8aa7a18a4f9a31ce934fadc3a8178e625ee3fa39babcb83262fae24203c9de79f37fe5f6b30f983c95e62bef2deaf0114bbb294a73506bc254dd2443dd5c153dc64b51a6c5a88f0648890f3462cb3c46e268bf703fe712bfe72757f3e744bfd13cc9041a7fe61f9621497548adc7182fed18841790a124a10e42c8bd012a46cdc57cdfb77b1bf2131286f8e5cbc8f77ea7a9eced77e62352fa87dbe6f907fe7e57ff29c7e5a7fdb0aeffea2466fbd8ffeeb27f587dceb7b73eb8fb935ff009f677fc773f373fe60747ff93b7790f6c7e8c5ef97e865d87f54fe1fa5f14ffce48d95c69ff9f9f9bf05c822493cd5a95ca83fefbb99da78ff00e11c533a2ec890968f111fcd1f60a759ad159e7ef2fd8cff009c1bd5acb52ff9c6bf235bdaba99b44b8d56c7518d7f626fd21717001f731cc8df4e701ed2c0c75d327ad11f203f43d2765481d3c7cafef7d6f9a1762ec55d8abb15762aec55d8abb157ffd6fbf98abb15762aec558df9bfca1e5cf3e797355f29f9b34a8759d075984c37d6330d88eaacac2851d080caca41520106b9769f513c1319319a9061931c7244c642c17e08ff00ce4aff00ce35798ff20fcc7cd3d6d67c81acccc3cb5e652bba9ddbea977c40093201b1d8480725fda55f50ec7ed886be1dd31cc7e91e5f73c8ebb432d34bbe2791fd054ffe71b3fe724fcc9f905e64ff00776b3e42d6665ff13796797c97eb769c88093a01ec1c0e2dfb2caf6c763c35f0ee98e47f41f2fb9743ae969a5df13cc3f7c3c9de71f2df9fbcb7a579b7ca5aac3ace83acc226b2bd84fd0c8ea6851d082acac01520822b9e5fa8d3cf04ce3c82a41eb71e48e488944d82c9b296c762af8dbfe72b3fe72a74bfc90d224f2d79665b7d53f33f5782b6766d4922d2e1906d7774bd0b1eb1c67ed7da6f87ed743d87d892d6cb8e7b631fecbc87e92eb3b43b4069c70c7791fb3cdf85fa9ea7ad79a35abbd5755bbbad6f5ed6ee9a6bcbc999a6b8b9b899b724ee59989d87d033d2a108e2888c401103e003caca4666cee4bf627fe7103fe710e1fcbe834ffccdfcccd3d66f3ddc209bcbde5f9d432e8c8c3e196553506e883ff3cfa7dbaf1e03b7fb78ea09c384fa3a9fe77fc77eff0073d2766f67787fbcc83d5d0777edfb9fa219c9bb97c93ff39a5f95377f9a3f92ba9b68f6cd75e62f24ce35fd26de3159268e0474bb8140dc9685d9c002acc8a075cdefb3bae1a5d50e23e99fa4fe83f375dda7a739b09ae6377e20fe59fe60eb9f959e79f2e79f3cbac3f49797ae84c2ddc911dc42e0c73dbc94df8cb1b321a6e2b51b8cf48d66961aac32c53e521fd87e0f2d8334b0cc4e3cc3fa1bfca0fce9f22fe75f96adfcc3e4fd52392e1634fd33e5f95d45f69f311bc73c55ad2b50ae3e16eaa73ca35fd9d9b45938320f71e87dcf65a6d5433c78a27e1d43d67305c8785fe7a7e7ff0091ff0022bcb571a9ebf7d15e798ae6163e5cf28c320faddecb4214951531c21bedc8c2806c393514ecfb33b2b2ebb270c0547acba0fdbe4e26af590d3c6cf3e81fcf3f9bfcd5acf9e3cd1af79bfcc373f5bd6bcc57b2df6a330145f5256af145a9e2aa28aa3b28033d5b4f823831c71c3611141e3b2643924652e65fa9ff00f3ecff00f9473f363feda5a57fc99b8ce27db1faf17b8fe877fd87f4cfde1f377fce797e54dd791ff382e7ce7696cc3cb7f9929fa420b851f047a8c4aa97b093fccc78cdbf5e669f64e6e3d98d70cfa6f0c9f563dbe1d3f57c1c1ed6d39c7978ba4befeac83fe70a7fe727b4cfcaabcbbfcb8f3f5e1b4f246bf75f5ad235b7a98f4cbe900471375220982ad48d91872228cec29f68fb165aa0336217388dc7f387eb1f6fc99f65ebc613c13fa4fd85fb4d697969a85adbdf585d437b65771acb6b796eeb2452c6e2aae8ea4ab0237041cf3b944c4d11443d38208b0aceeb1ab3bb0444059dd8d000372493d29812fc95ff009cddff009ca8d13ccba5dc7e4e7e5bea91eaba7cb3a379dfcc96ae1ade5f41c3a58db48bb48bcd4348ebf0ec1413f1e777ecdf624f14bf319851fe11d7de7f47f63cef6a6be331e140df79fd0fcbcced1d13fa5abcf27d87e607e4baf927526e167e68f2943a7c93d2a6269acd552551dcc6f471ee33c7a3a83a7d578839c657f6bdb9c63261e13d47e87f38be6af2c6b3e4bf326b7e53f30da358eb7e5ebc96c751b63da4898a92a7f695bed2b0d88208d8e7ae60cd1cd8c6481b12161e2f26338e4632e61fb83ff38a5ff3949e5cfcddf2ce93e54f326a70e99f99fa3db25aded8dcb843ab2c2a145dda96203bb8159107c4ad52070a1cf36edcec59e93219c05e33fec7c8fe82f53d9faf8e6888c8d487dafb3b39e76697ea9ab697a258cda9eb5a95ae91a6db53eb1a85ecc96f047c8851ca490aa8a9200a9eb9386394cf0c4127b8225211164d04c0104020d41e87209762ac1359fccefcbff2ef9b746f226bbe6cd3b48f367982d9aef47d1eee5113cf107f4c7166a20676a8452dc9e8dc41e269958f459b263396312631d891f8fec6a967c7198812012cef315b50b7b6365a95a5c586a36705fd8dd218eeacae635962910f5574705581f0232519189b068a0804517e1f7fce757e4e792bf2abcfde5bbef23d9c5a2e9fe73b1b8bbbdf2ec1b416d716f2aab49027fbae39438a20f84156e341b0f48f667b432eab0c865366240bef07bfdcf2ddada68619830dafa251ff00381335ec5ff391be5f4b466105c693aac7a985e86016cce037b7aaa9f4d32cf6a003a195f78af9feab61d904fe607b8bd83fe7e57ff29c7e5a7fdb0aeffea2466bfd8ffeeb27f587dce4f6e7d71f726bff003eceff008ee7e6e7fcc0e8ff00f276ef21ed8fd18bdf2fd0cbb0fea9fc3f4b12ff009f877e555de83f983a67e6ad8db33689e77b78acb58b851f0c5aa59442350e7a0f56dd14af8947cbfd93d70c984e03ce1b8fea9fd47ef0d7db3a731c8320e52fbffb1e7fff00386fff0039276bf927e64bff002d79be5907e5ef9ba68def2e9159ce9b7ca022dd84504b23ad12500568158578f16caf683b1ceb60278ffbc8fda3bbf534f66eb860970cbe93f61ef7ee5695ab697aee9d67abe8ba8db6ada56a1189ac752b395268268dba3472212ac3dc1cf369e394246320411d0bd5c64242c1b098641290e87e6af2d799cea63cb9afe9faefe85bb6b0d5bea1711dc0b6b9450cd0ca6366e2c030a8396e5c1931571c48b162c558610c919df090693eca99bb15762aec55d8abffd7fbf98abb15762aec55d8ab1bf37f943cb9e7cf2e6abe53f366950eb3a0eb30986fac661b11d5595850a3a101959482a4020d72ed3ea2782632633520c32638e4898c8582fc0dff009c93ff009c6cf327e41f992a3d5d63c85acccdfe18f3371f9b7d52eb88a24e83e41c0e4bfb4abea1d8fdb10d7c3ba6398fd23cbee791d76865a6977c4f22bbfe71abfe724fcc5f907e65a37adac790759997fc4fe590db8e8bf5bb5e44049907c8381c5bf6595ed8ec786bf1f74c723fa0f97dcba1d74b4d2ef89e61fd00689ace9de62d1b49f3068f722f349d72ca0d434bbb504096dee635962700d08e4ac0ef9e59931cb1c8c25b1068fbc3d7c2424011c8be5dff009cb1ff009c938ff217caf6765a25ba5f79fbcd91cebe5e8e51ca0b38a2e2b25e4ebfb5c4b8089fb4dd76520eebb0bb1ff3f90996d08f3f3f21fa5c0ed0d77e5e343ea3cbf5bf07b55d575bf356b779ab6ad7975ae6bfae5d19af2f262d35c5cdc4cdd4f52c589a003e433d3a10862808c401103e4f2729199b3b92fd84ff009c41ff009c418bc81169ff0099df99da7a4de7999167f2ef976750cba3ab0aacd329d8dd11d07fbabfd7fb3c076ff6ff00e62f0e13e8ea7f9dff001dfbfdcf49d9bd9be1fef327d5d0777edfb9fa279c9bb9762aec55f91fff00395fff003855ab5b6a9aa7e64fe4e692da96977eef77e62f235a256e2d6663ca49ac221bc91b1a9312fc487ec029b2777d87ed144c461d41a236123d7ca5e7e7d7af9f3bda1d964133c42c751fa9f9b3a56b1e60f2a6aaba8689aa6a1e5bd6ec1d912f6ca696ceea17068ca1e328ea411422b9d84f1c32c6a40481efdc3a48ca50360905ec527fce517fce424965f506fcdbf308838f1f516e024f4ff8ceaa25afbf2ae6bc762e8aefc28fcbf472727f3f9eab8cbc7269f5ff0035eb3ea5c4da87993cc1abccabea48d2de5e5d4cfb28a9e7248c7a0ea73600431476a8c47c00718994cf524a7fe7bfcbaf38fe59ea3a6e8fe77d1a4d0757d534d8756874c9994cd1db4ef2471faaaa4f0626224a1f887ed0076cab4babc5a9899633601abf367970cf1102628916fd3bff009f67ff00ca39f9b1ff006d2d2bfe4cdc6719ed8fd78bdc7f43bdec3fa67ef0fbcff367f2afcadf9c9e49d4fc91e6cb72d6779496c2fe203d7b2bb8c1f4ae6063d192a453a3292a7627398d0ebb268f28c98f98fb477176da8d3c73c0c24fc0ff00cebff9c79fcc4fc8dd665b4f33698f7be5e96529a379c6d119ac2e909f8016dfd2929d63735eb4e4b463ea1d9ddad835d1b81a97589e63f58f3791d568f269cfa86ddfd1897933f37ff347f2f2236de4af3eeb7e5db224b1d3ad6ee416a589a9636ec5a2afbf1ae5fa8d069f51be48091ef237f9f36bc5a9c98be99108ef37fe79fe7079f6cdf4ef36fe636bbace9928a4fa5bddbc76b27fc64b78b846dfec94e474fd99a6c06f1e3883df5bfcd964d5e5c82a5224207f2e3f297cff00f9ada949a7f927cbf3ea51da0e7aa6acff00bab0b28c0e4cf7372f444014134af23fb2a4ed92d5ebf0e963792557c8753ee0c7069e798d445fdcf383d4ef5f7ccc697f513e4b14f27794c1d88d1ac2a3fe8dd33c5b51fdecbde7ef7bcc5f40f707c89ff3969ff389b07e74dbff008d3c97e869df997a6c0229229488e0d62de31f04333f449906d1c87623e07f878b26fbb0bb74e88f87937c67fd8fecef1f11e7aded1ecff1fd51fa87dafc51f30f96fccde48d72e344f32e917de5bd7f4c907ad65771bc13c6ca7e175ad2a0d2aaca687a839e8d8b363cf0e2811289ee7989c258e5521443d4f46ff9c99fcfdd06cd2c34efcd7f300b58978c497573f5b2aa3a057b912b003b0076cc1c9d8da3c86ce28dfbabee6f8ebb3c45099607e71fccbfcc1fcc196397cefe73d63cd1e8372b68750bb966862245098a166f4d2bfe4a8ccad3e8f0e9ff00bb808fb83564cf9327d7225fad1ff3815f985f9bfe64f2bdc7973ce1e5fbfd47c87a2db81e52f3f5e1f4c8e2428b006521ae51454a3a57d3a70634281785f6a349a6c7938f1c8099faa23fdd797e9e7def43d919b2ca3c321e91c8fe8f37e86b9708e6350f20525118f104d3604d0d3e74ce4c3b97f3a5ff00392ba77e6e47f9a9e63d73f37f42b9d1f5bd76e9e4b026b2581b58fe0822b1b81f04914518551435fe701c9cf5aec79e9bf2f18e9e40803e37d6c779fec78bd7472f8a4e4144fcbe099fe5cffce5afe7afe59c1069fa3f9c64d6745b60161d0f5e41a8408abd111e42278d476549147b64357d85a4d49b9428f7c76fd9f30cb0768e6c5b0958ee3bbdf9ff00e7e47f9b6d66634f2579492fcad3eb7e9df18c1f1111bbaffc3e6ac7b21a6bfae75f0fd4e67f2de5afa47dbfadf16fe64fe6779d3f36bccf3f9b7cf5abb6adab4b1ac1000ab1436f6e8494820890054452c4d06e492cc4b124f45a3d162d263f0f10a1f7f997579f3cf34b8a66cbf4dffe7df3f91bac797adf57fce3f33d849a7c9e60b11a67932d2752b23d948eb2cf7851b70b298d1633d4a863f65949e33daaed28e4234f037c26e5efe83e1d5def63e90c6f2cbaec3f5bcdbfe7e57ff29c7e5a7fdb0aeffea246667b1ffdd64feb0fb9a3b73eb8fb935ff9f677fc773f373fe60747ff0093b7790f6c7e8c5ef97e865d87f54fe1fa5fa75f981e42f2d7e66f94759f2579b6c7ebda26b50fa732a9e32c5229e51cf0bd0f192360194d3a8dc11519c669755934d9064c6688fc53bdcd863960632e45f829f9f7ff0038c7f981f917aadccb7f672ebde499652349f3a5a444dbb2b1f823ba515fabcbdb8b1a13f619b3d3fb2fb670eba3b1a9f589fd1de3f05e4b57a1c9a73bef1ef795f937f34bf31ff2f1a4ff000479db59f2cc52b739ed2c6ee48ede46fe6920afa6e7dd94e676a3458351fde404bde37f9b8f8b519317d122192f9a3fe720bf3b3ce5632699e63fccdd7eff004d9d4a5ce9eb76d6f04aa762b2c707a6ae3d981ca70f6569709e2863883df57f7b664d66698a948d33aff9c52d77f3a342fcceb197f27746b9f314f72d1c5e68d0cf24d367b22dbfd7a63f0401772921dd5becf2a946c6edcc5a5c9a73f98223dc7adf977f986decf9e68e4fdd0bef1d3e2fe8485682a2869b81be794bd8b78abb15762aec55ffd0fbf98abb15762aec55d8abb15633e71f27796fcfde5bd57ca5e6dd2a1d6741d66130ded94c3e957461428e840656520a900835cbb4fa89e098c98cd4835e4c71c913190b05f945abff00cfb73ceffe3092df42f3c68dfe0396e3943a9defaffa520b626bc1ada387d292451b0225556ebf074cee31fb5f8bc2b940f1f70ae1bf7ddfd9f379f97624f8f690e1fb5fac9e54f2e69fe4ff002bf977ca7a4f3fd19e59d32d34ad3cca793986ce15850b914ab154049f1ce1b3e6966c92c92e72249f8bd0e3808444472029f28ffce5b7fce2eea1f9fd6be5dd67cafacda691e6ef2cc735b4716a3ea2da5ddacccafc1e4892468dd1949521083c883d88de76176d4740651982632eee60bafed1d01d48062688623ff38c7ff385367f94baaa79e3f31aef4ff33f9d6d1abe5fb2b2e725869a7fdfe1a648da59ff009494013aad5a8c323b67da33ab8f858418c0f3be67cbc87ded7a1ecb184f1cf7974ee0fbef39676eec55d8abb15762af22f3f7e42fe4ff00e67caf73e77f20697ac6a120a49aba235a5f301b00d776ad14cc07605e999fa5ed4d4e976c732077731f2361c6cda4c597eb883f7bc517fe7037fe71c16e3d63e58d4de3ad7ea8756bcf4fe551207ff86cd8ff00a27d755710f9071bf9274fdc7e65ef3e42fc97fcabfcb015f22791b4bf2fdc95e0da947119af590f556bb9cc9391ec5e99acd5768ea355fdecccbcba7c86ce5e1d2e2c5f4440fc77bc9bf3affe711bf2eff3d7cdb6be72f356b9e61d2f53b4d321d2960d267b48a068a0966955d84f6b3b73acc413ca94036f1ceeceedecfa1c671c231209bdefcbb88ee71b55d9d8f512e29120d56d5fa997fe45ff00ce3d793ffe71fecbcc561e51d5759d522f32cf6f3df36b12dbcac8d6caea823fabdbc00022435a8394769f6b65d7989c800e1be57d7de4b6e93470d3022249bef7bce6adcb42df5858ea76971a7ea56706a16176863bbb2b98d6586543d55e3705581f0232519189b068841008a2f99bccbff3863ff38e5e66b992f26fcbd8b47ba9492efa3dd5cd847bf65b7865102fd118cdc61f6875d88578963cc03f6f3fb5c19f6669e7bf0d7bb640e83ff3847ff38e1a15c25dff0081a4d66788f28ff4a5fdddc460fbc3eaac4c3d994e4f2fb47aec82b8ebdc00fda88765e9e3fc37ef2539ff009c89f37795bf237f20fcda346b1b0f2e8bed3e6d0bca3a2e9f0c76b19bdd4236894c3144157f74a5a56a0e8872aec9c1935dac8f1132a3c52277d87ebe5f165adc91d3e035b6d43e2fc08f2a7976ff00cdfe67f2ef9574b42fa8f99352b5d36c940afef2ea558949f605aa7db3d4b3e61871ca72e5104fc9e471c0ce42239934fea1ed2d61b2b4b6b2b75e305a4490c0be091a8551f70cf16948c8927abdd8142911812c33ce7f975e45fcc4b15d3bcf1e53d33ccf6b1d7d017f6e92c9096ea61948e7193e28c0e6469f579b4e6f148c4f916acb8619454c02f9baff00fe7047fe71bef2769a1f2aea1a6ab1afd5ed755bc318f97ad24a47df9b88fb4dae02b881f80708f64e9cf423e2598f94bfe7113fe71e7c9b7315f69df97165a95f424325ceb32cfa98046e0886ea49210476212b98f9fb7b5b9851c840f2a8fddbb663ecec18cd88fcf77d1f1451411470c31ac30c2a122890055555140aa06c001d066a09b7395302a4baff00973cbfe6bd327d17ccda258f98348baff7a34cd46de3b981a9d098e556151d8d2a32cc59a78a5c502627bc6cc6708cc548587c81e6eff9c04fc82f32cd2dce9569acf92a7909631e8f7bca0e47afeeaf52e683d90a8f0a66ff004fed4eb318a9113f78fd54eb72764609f2b1eefdb6f325ff009f6afe5f89b937e63f984c15feec5bda07a7fafc48ff0085ccdff4619abfbb8fdad1fc898ff9c5edbf977ff3849f90df97d796faa368777e73d56d583dbddf99264ba8e371bf216b1470db9a1e9ce3623c735babf68f59a81c36220ff376fb773f6b9587b2f06237567cdf5c2a850154055514551b0007619a1762f9b3f3cffe7173c89f9fdab685ac79bb5ad7b4bb9f2fda4967691e9135ac48e92c9ea1320b8b69c935e9423371d99db59b4119471889e237bdfe821c1d5e821a920c8915ddfd888fc8aff9c66f23ff00ce3fddf98ef3ca1aceb9aa49e678ada1be5d626b695516d5a46431fd5eda0209329ad6bdb0769f6ce5ed0111904470df2bebef253a4d0c34c498926fbdf4666a1cd51b8b782ee09adaea08ee6dae11a3b8b79543a3a30a32b2b020823620e1048361045be6cf357fce1eff00ce3b79bae65bdbdfcb9b5d2af25259a7d1a7b8d356a7afee2da44837ff008c79b8c1dbfadc228642479d1fb4eff6b8593b374f336635eed920d1ff00e7073fe71bb49b84b97f25dc6b0f19e51a6a1a9dec9183ef1a4b1ab7c981196e4f6975d315c75ee03f5308f6569e3fc37f12fa6bcb9e56f2d79434c8b46f2ae81a7f973498778f4ed36da3b6841eec52255049ee4ee7be69b367c99a5c539191ef26dce8638c0544003c93eca99bb15762aec55d8abfffd1fbf98abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762af12fcc4ff009c8cfc9afca9d76dbcb7e7cf3ac3a2eb3736ff005afa925b5d5e3c5192021985a43318cbf550d4a815e99b2d27646ab55033c50b1ef03ef21c5cdadc3865c33951fc773c43ce5ff39f9f909e5eb1965f2ddfea5e7cd48a136d6361653d9c45fb0966be4838af895573fe49cd969fd96d6643eb0203cc83f60bfd0e2e5ed7c111e9b91fc77bf26bf3cbf3ebcebf9f3e664d73cd12259699a7078fcbbe59b52df55b189c82d4e5bbc8f41ce43bb50502a8551dcf66f65e2d063e186e4f33d4fecee0f3dabd5cf512b972e83b9f6aff00ce027fce3e5f5c6aa9f9e3e6bb16b7d36c12583c816b32d0dc4f20314d7dc4fec46a59233dd89614e009e77da9ed5023f9681dcfd5e5e5faff006bb3ec8d1927c5972e9fadfad99c23d13b15762aec55d8abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762aec55fffd2fbf98abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15611f98cde7e5f266ba3f2c61d2e6f3bbdb94d10eaf2bc56b1bb6c643c63939328a9456a296a72216b993a4f07c58f8d7c1d6b9b566e3e03e1d7174b7e13f9cbfe7193fe728ae75dd5357f347e5eebbe60d6b53b87b8d435786587526b8918eee64b7965ad7b0ec36a0cf4cd3f6cf678808c324620721b8fbde4f2e8752644ca2493f14b347ff9c49ff9c8bd6e7482d7f2b355b52e4032ea0d058c6a3c4b5ccb1f4f6df2793b7745016720f859fb911eced44bf80fdcfb9bf243fe7deb63a3de59f98bf3a754b6d7a6b66596dfc95a6339b3e4371f5cb9608d281de3450b5eaeeb5079aed2f6accc1869c57f48f3f80e9eff00b1dae97b1844f16537e43f4bf4dadadadecededececede3b5b4b58d61b5b58542471c6802a22228015540a00360338d24c8d9dc9778056c15b025d8abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762afffd3fbf98abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762aff00ffd4fbf98abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762aec55d8abb15762aff00ffd9	Agesic	Agesic	Liniers1324	Agesic	America/Montevideo	dd/MM/yyyy	HH:mm	2.16.858.0.0.0.1.0	
\.


--
-- Data for Name: ae_novedades; Type: TABLE DATA; Schema: global; Owner: sae
--

COPY global.ae_novedades (fecha_creacion, fecha_ult_intento, intentos, datos, enviado, id, reserva_id, empresa_id) FROM stdin;
\.


--
-- Data for Name: ae_oficinas; Type: TABLE DATA; Schema: global; Owner: sae
--

COPY global.ae_oficinas (id, tramite_id, nombre, direccion, localidad, departamento, telefonos, horarios, comentarios) FROM stdin;
\.


--
-- Data for Name: ae_organismos; Type: TABLE DATA; Schema: global; Owner: sae
--

COPY global.ae_organismos (id, codigo, nombre) FROM stdin;
5	138	Presidencia
\.


--
-- Data for Name: ae_rel_usuarios_empresas; Type: TABLE DATA; Schema: global; Owner: sae
--

COPY global.ae_rel_usuarios_empresas (usuario_id, empresa_id) FROM stdin;
3	2
\.


--
-- Data for Name: ae_rel_usuarios_organismos; Type: TABLE DATA; Schema: global; Owner: sae
--

COPY global.ae_rel_usuarios_organismos (usuario_id, org_id) FROM stdin;
3	5
\.


--
-- Data for Name: ae_rel_usuarios_roles; Type: TABLE DATA; Schema: global; Owner: sae
--

COPY global.ae_rel_usuarios_roles (usuario_id, empresa_id, rol_nombre) FROM stdin;
3	2	RA_AE_ADMINISTRADOR
\.


--
-- Data for Name: ae_textos; Type: TABLE DATA; Schema: global; Owner: sae
--

COPY global.ae_textos (codigo, texto) FROM stdin;
ingrese_al_sistema	Ingrese al Sistema de Agenda electrónica
string	Texto
contrasena	Contraseña
ingresar	Ingresar
ingrese_usuario_y_contrasena	Ingrese su código de usuario y contraseña
saltar_al_contenido	Saltar al contenido
empresa	Empresa
agenda	Agenda
seleccionar	Seleccionar
recurso	Recurso
usuario	Usuario
cerrar_sesion	Cerrar sesión
menu	Menu
nombre_aplicacion	SAE - Sistema de Agenda Electrónica
informacion_adicional	Información adicional
elegir_dia_y_hora	Elegir día y hora
volver_a_la_pagina_principal	Volver a la página principal
nueva_reserva	Nueva reserva
detalle_y_ubicacion	Detalle y ubicación
dia_y_hora	Día y hora
datos_necesarios	Datos necesarios
confirmacion	Confirmación
seleccione_el_dia	Seleccione el día de su preferencia haciendo click con el mouse
debajo_del_calendario_horarios_disponibles	Luego de seleccionar el día, debajo del calendario se mostrarán los horarios disponibles para ese día
seleccione_un_horario	Seleccione un horario para continuar con la reserva
preferencia_de_horario	Preferencia de horario
cualquier_horario	Cualquier horario
solo_matutino	Solo por la mañana
solo_vespertino	Solo por la tarde
seleccione_un_dia	Seleccione un día
por_la_manana	Por la mañana
por_la_tarde	Por la tarde
completar_datos	Completar datos
datos_marcados_obligatorios	Los datos que estén marcados con un asterisco (*) son obligatorios
clausula_de_consentimiento_informado	Cláusula de consentimiento informado
terminos_de_la_clausula	Términos de la cláusula
acepto_los_terminos	Acepto los términos
no_acepto_los_terminos	No acepto los términos
confirmar_reserva	Confirmar reserva
reserva_confirmada	Su reserva está confirmada
guardar_ticket	Guardar ticket
imprimir_ticket	Imprimir ticket
cancelar_reserva	Cancelar reserva
a_las	a las
confirma_cancelar_la_reserva	¿Esta seguro que desea cancelar la reserva?
cancelar_reserva_para_agenda	Cancelar reserva para
continuar	Continuar
su_reserva	Su reserva
debe_especificar_la_empresa	Debe especificar la empresa
dias_con_turnos_disponibles	Los días marcados en color verde tienen turnos disponibles
codigo_de_seguridad	Código de seguridad
codigo_de_seguridad_de_la_reserva	Código de seguridad de la reserva
la_combinacion_de_parametros_especificada_no_es_valida	La combinación de parámetros especificada no es válida
no_se_pudo_registrar_un_usuario_anonimo	No se pudo registrar un usuario anónimo para permitir esta invocación
la_empresa_especificada_no_es_valida	La empresa especificada no es válida
la_agenda_especificada_no_es_valida	La agenda especificada no es válida
no_se_encuentra_la_reserva_o_ya_fue_cancelada	No se encuentra la reserva o la misma ya fue cancelada
ingrese_el_codigo_de_seguridad	Ingrese el código de seguridad
el_recurso_especificado_no_es_valido	El recurso especificado no es válido
debe_ingresar_codigo_de_seguridad	Debe ingresar el código de seguridad
debe_ingresar_al_menos_dos_de_los_datos_solicitados	Debe ingresar al menos dos de los datos solicitados en la reserva
debe_especificar_la_agenda	Debe especificar la agenda.
debe_especificar_el_recurso	Debe especificar el recurso
no_se_encontraron_reservas	No se encontraron reservas
debe_especificar_la_reserva	Debe especificar la reserva
no_es_posible_cancelar_la_reserva	No es posible cancelar la reserva
reserva_cancelada_correctamente	Reserva cancelada correctamente
la_agenda_especificada_no_tiene_recursos	La agenda especificada no tiene recursos disponibles
recurso_no_habilitado_para_ser_accedido_desde_internet	El recurso especificado no está habilitado para ser accedido desde internet
acceso_denegado	Acceso denegado
sistema_en_mantenimiento	Sistema en mantenimiento. Por favor intente nuevamente más tarde
sin_disponibilidades	No hay disponibilidades para la opción seleccionada
clausula_de_consentimiento_informado_texto	De conformidad con la Ley N° 18.331, de 11 de agosto de 2008, de Protección de Datos Personales y Acción de Habeas Data (LPDP), los datos suministrados por usted quedarán incorporados en una base de datos, la cual será procesada exclusivamente para la siguiente finalidad: {finalidad}. "Los datos personales serán tratados con el grado de protección adecuado, tomándose las medidas de seguridad necesarias para evitar su alteración, pérdida, tratamiento o acceso no autorizado por parte de terceros que lo puedan utilizar para finalidades distintas para las que han sido solicitadas al usuario. El responsable de la base de datos es {responsable} y la dirección donde podrá ejercer los derechos de acceso, rectificación, actualización, inclusión o supresión, es {direccion}, según lo establecido en la LPDP.
debe_aceptar_los_terminos_de_la_clausula_de_consentimiento_informado	Debe aceptar los términos de la cláusula de consentimiento informado para poder continuar
ticket_de_reserva	Ticket de reserva
fecha	Fecha
hora	Hora
serie	Serie
numero	Número
debe_seleccionar_un_horario	Debe seleccionar un día y una hora
ingrese_el_texto_que_aparece_en_la_imagen	Ingrese el texto que aparece en la imagen
debe_ingresar_el_texto_que_aparece_en_la_imagen	Debe ingresar el texto que aparece en la imagen
verificacion_de_seguridad	Verificación de seguridad
esta_seguro_que_desea_eliminar_la_empresa	¿Está seguro que desea eliminar la empresa?
eliminar	Eliminar
cancelar	Cancelar
horarios_diponibles	Horarios disponibles
listado_de_empresas	Listado de empresas
identificador	Identificador
nombre	Nombre
acciones	Acciones
booleano	Sí/No
lista	Lista desplegable
datos_de_la_empresa	Datos de la empresa
organismo	Organismo
codigo	Código
recargar_listado	Recargar listado
unidad_ejecutora	Unidad ejecutora
logo	Logo
dias_de_inicio_de_la_ventana_de_intranet	Días requeridos antes de comenzar a agendar
subir	Subir
logo_texto_alternativo	Texto alternativo del logo
zona_horaria	Zona horaria
formato_de_fecha	Formato de fecha
formato_de_hora	Formato de hora
origen_de_datos	Origen de datos
datos_para_la_clausula_de_consentimiento	Datos para la cláusula de consentimiento
finalidad	Finalidad
responsable	Responsable
direccion	Dirección
guardar	Guardar
esta_seguro_que_desea_eliminar_el_usuario	¿Está seguro que desea eliminar el usuario?
listado_de_usuarios	Listado de usuarios
cedula_de_identidad	Cédula de identidad
nombre_y_apellido	Nombre y apellido
correo_electronico	Correo electrónico
modificar	Modificar
datos_del_usuario	Datos del usuario
superadministrador	Superadministrador
enviar_por_correo	Enviar por correo
roles_del_usuario_en_la_empresa	Roles del usuario en la empresa
administrador	Administrador
planificador	Planificador
funcionario_de_call_center	Funcionario de call center
funcionario_de_atencion	Funcionario de atención
llamador	Llamador
datos_de_la_agenda	Datos de la agenda
dias_de_la_ventana_de_intranet	Duración de la ventana para agendar
tramite	Trámite
descripcion	Descripción
misma_que_la_de_la_empresa	Misma que la de la empresa
dias_de_inicio_de_la_ventana_de_internet	Días requeridos antes de comenzar a agendar
listado_de_agendas	Listado de agendas
copiar	Copiar
etiquetas	Etiquetas
nomenclatura_para_recurso	Nomenclatura para 'Recurso' (p.e. 'Oficina', 'Centro' o 'Sucursal')
texto_paso_1	Texto paso 1
texto_para_el_paso_1_de_la_reserva	Texto para el paso 1 de la reserva
texto_paso_2	Texto paso 2
texto_para_el_paso_2_de_la_reserva	Texto para el paso 2 de la reserva
texto_paso_3	Texto paso 3
texto_para_el_paso_3_de_la_reserva	Texto para el paso 3 de la reserva
texto_para_el_ticket_de_reserva	Texto para el ticket de reserva
correo_de_confirmacion	Correo de confirmación
texto_para_el_correo_de_confirmacion	Texto para el correo de confirmación
correo_de_cancelacion	Correo de cancelación
texto_para_el_correo_de_cancelacion	Texto para el correo de cancelación
empresas	Empresas
crear_empresa	Crear empresa
consultar_empresas	Consultar empresas
usuarios	Usuarios
crear_usuario	Crear usuario
consultar_usuarios	Consultar usuarios
agendas	Agendas
crear_agenda	Crear agenda
consultar_agendas	Consultar agendas
modificar_textos_de_agendas	Modificar textos de agendas
recursos	Recursos
crear_recurso	Crear recurso
consultar_recursos	Consultar recursos
modificar_textos_de_recursos	Modificar textos de recursos
datos_a_solicitar	Datos a solicitar
agrupaciones	Agrupaciones
crear_dato	Crear dato
consultar_datos	Consultar datos
disponibilidades	Disponibilidades
consultar_disponibilidades	Consultar disponibilidades
generar_un_dia	Generar un día
copiar_dia	Copiar día
modificar_cupos	Modificar cupos
eliminar_disponibilidades	Eliminar disponibilidades
reservas	Reservas
considerar_el_sabado_como_dia_habil	Considerar como día hábil
lista_de_espera	Lista de espera
abrir_llamador	Abrir llamador
oficina	Oficina
localidad	Localidad
departamento	Departamento
latitud	Latitud
longitud	Longitud
telefonos	Teléfonos
horarios	Horarios
inicio_de_vigencia	Inicio de vigencia
fin_de_vigencia	Fin de vigencia
cupos_minimos_de_la_ventana	Cupos mínimos de la ventana
cantidad_de_dias_a_generar	Cantidad de días a generar
listado_de_recursos	Listado de recursos
esta_seguro_que_desea_eliminar_el_dato	¿Está seguro que desea eliminar el dato?
informacion_adicional_del_recurso	Información adicional del recurso
agregar_informacion	Agregar información
orden	Orden
etiqueta	Etiqueta
valor	Valor
modificar_dato	Modificar dato
agregar_dato	Agregar dato
volver	Volver
esta_seguro_que_desea_eliminar_el_recurso	¿Está seguro que desea eliminar el recurso?
textos_del_llamador	Textos del llamador
titulo_de_la_columna_de_datos	Título de la columna de datos
titulo_de_la_columna_del_puesto	Título de la columna del puesto
esta_seguro_que_desea_eliminar_la_agenda	¿Esta seguro que desea eliminar la agenda?
esta_seguro_que_desea_eliminar_la_agrupacion	¿Esta seguro que desea eliminar la agrupación?
agregar_agrupacion	Agregar agrupación
listado_de_agrupaciones	Listado de agrupaciones
agenda_creada	Agenda creada
modificar_agrupacion	Modificar agrupación
crear_agrupacion	Crear agrupación
vista_preliminar_del_formulario_de_ingreso_de_datos_de_la_reserva	Vista preliminar del formulario de ingreso de datos de la reserva
ver_diseno	Ver diseño
datos_generales	Datos generales
texto_de_ayuda	Texto de ayuda
tipo_de_dato	Tipo de dato
largo_maximo	Largo máximo
requerido	Requerido
clave	Clave
agrupacion	Agrupación
fila	Fila
diseno_del_formulario	Diseño del formulario
diseno_del_reporte	Diseño del reporte
incluir_en_el_reporte	Incluir en el reporte
ancho	Ancho
diseno_del_llamador	Diseño del llamador
incluir_en_el_llamador	Incluir en el llamador
largo	Largo
esta_seguro_que_desea_eliminar_el_campo	¿Esta seguro que desea eliminar el campo?
esta_seguro_que_desea_eliminar_el_valor	¿Esta seguro que desea eliminar el valor?
valores_posibles_para_el_dato	Valores posibles para el dato
agregar_valor	Agregar valor
vigencia_desde	Vigencia desde
vigencia_hasta	Vigencia hasta
modificar_valor_posible	Modificar valor posible
crear_valor_posible	Crear valor posible
aplicar_a_todos_los_dias	Todos los días subsiguientes
agenda_eliminada	Agenda eliminada
desde	Desde
hasta	Hasta
consultar	Consultar
disponibilidades_en_periodo_consultado	Disponibilidades en período consultado
cupos_disponibles	Cupos disponibles
ver_detalles	Ver detalles
disponibilidades_para_el_dia	Disponibilidades para el día
disponibilidades_por_la_manana	Disponibilidades por la mañana
hora_de_inicio	Hora de inicio
cupos_totales	Cupos totales
cantidad_de_reservas	Cantidad de reservas
disponibilidades_por_la_tarde	Disponibilidades por la tarde
periodo_a_consultar	Período a consultar
fecha_para_la_cual_generar	Fecha para la cual generar
nuevas_disponibilidades_para_la_fecha_consultada	Nuevas disponibilidades para la fecha consultada
frecuencia	Frecuencia
crear_disponibilidades	Crear disponibilidades
seleccionar_la_fecha_modelo	Seleccionar la fecha modelo
periodo_para_el_cual_generar	Período para el cual generar
fecha_de_inicio	Fecha de inicio
fecha_de_fin	Fecha de fin
esta_seguro_que_desea_modificar_las_disponibilidades_marcadas	¿Esta seguro que desea modificar las disponibilidades marcadas?
fecha_para_la_cual_modificar	Fecha para la cual modificar
todas_las_horas_del_turno_matutino	Todas las horas del turno matutino
todas_las_horas_del_turno_vespertino	Todas las horas del turno vespertino
modificacion_de_disponibilidades_para_las_horas_seleccionadas	Modificación de disponibilidades para las horas seleccionadas
horarios_seleccionados	Horarios seleccionados
operacion_a_realizar	Operación a realizar
aumentar_disponibilidades_en_la_cantidad_especificada	Aumentar disponibilidades en la cantidad especificada
disminuir_disponibilidades_en_la_cantidad_especificada	Disminuir disponibilidades en la cantidad especificada
establecer_disponibilidades_en_la_cantidad_especificada	Establecer disponibilidades en la cantidad especificada
cantidad_de_cupos	Cantidad de cupos
seleccionar_semana	Seleccionar semana
semana	Semana
disponibilidades_para_la_semana_consultada	Disponibilidades para la semana consultada
eliminar_disponibilidades_de_toda_la_semana	Eliminar disponibilidades de toda la semana
hora_de_fin	Hora de fin
seleccione_ubicacion	Seleccione ubicación
seleccione_dia_y_hora	Seleccione día y hora
disponibilidades_en_la_manana	Disponibilidades en la mañana
disponibilidades_en_la_tarde	Disponibilidades en la tarde
la_reserva_esta_confirmada	La reserva está confirmada
si	Sí
no	No
documento	Documento
llamar_al_siguiente	Llamar al siguiente
mostrar	Mostrar
no_hay_personas_en_espera	No hay personas en espera
volver_a_llamar	Volver a llamar
falto	Faltó
asistio	Asistió
tamano_de_la_pantalla	Tamaño de la pantalla
numero_de_puesto	Número de puesto
debe_haber_una_agenda_seleccionada	Debe haber una agenda seleccionada
agrupacion_modificada	Agrupación modificada
debe_haber_una_agrupacion_seleccionada	Debe haber una agrupación seleccionada
agrupacion_eliminada	Agrupación eliminada
debe_haber_un_recurso_seleccionado	Debe haber un recurso seleccionado
el_nombre_de_la_agrupacion_es_obligatorio	El nombre de la agrupación es obligatorio
agrupacion_creada	Agrupación creada
disponibilidades_creadas	Disponibilidades creadas
error_de_acceso_concurrente	Error de acceso concurrente. Intente más tarde.
campo_modificado	Campo modificado
campo_eliminado	Campo eliminado
dato_creado	Dato creado
valor_modificado	Valor modificado
valor_eliminado	Valor eliminado
valor_creado	Valor creado
datos_correctos	Datos correctos, las validaciones se han ejecutado con éxito
disponibilidades_modificadas	Disponibilidades modificadas
agenda_modificada	Agenda modificada
disponibilidades_eliminadas	Disponibilidades eliminadas
empresa_creada	Empresa creada
empresa_modificada	Empresa modificada
recurso_creado	Recurso creado
recurso_eliminado	Recurso eliminado
recurso_copiado	Recurso copiado
recurso_modificado	Recurso modificado
dato_modificado	Dato modificado
debe_haber_un_dato_seleccionado	Debe haber un dato seleccionado
dato_eliminado	Dato eliminado
reserva_cancelada	Reserva cancelada
usuario_creado	Usuario creado
usuario_modificado	Usuario modificado
debe_haber_un_usuario_seleccionado	Debe haber un usuario seleccionado
la_contrasena_fue_enviada	La contraseña fue enviada al usuario
el_nombre_de_la_agenda_es_obligatorio	El nombre de la agenda es obligatorio
la_fecha_es_obligatoria	La fecha es obligatoria
la_hora_de_inicio_es_obligatoria	La hora de inicio es obligatoria
la_hora_de_fin_es_obligatoria	La hora de fin es obligatoria
debe_crear_al_menos_una_agrupacion	Antes de crear datos a solicitar debe crear al menos una agrupación
no_se_permite_eliminar_esta_agrupacion	No se permite eliminar esta agrupación
no_se_permite_eliminar_este_dato	No se permite eliminar este dato
el_nombre_del_dato_es_obligatorio	El nombre del dato es obligatorio
debe_seleccionar_un_valor	Debe seleccionar un valor
no_se_permite_eliminar_este_valor	No se permite eliminar este valor
el_nombre_del_valor_es_obligatorio	El nombre del valor es obligatorio
solo_se_permiten_letras_y_numeros	Solo se permiten letras y números
codigo_de_usuario	Cédula de identidad
no_se_pudo_obtener_la_ultima_fecha_generada	No se pudo obtener la última fecha generada
solo_se_permiten_hasta_5_caracteres	Solo se permiten hasta 5 caracteres
la_fecha_de_inicio_es_obligatoria	La fecha de inicio es obligatoria
la_fecha_de_fin_es_obligatoria	La fecha de fin es obligatoria
la_fecha_de_fin_debe_ser_posterior_a_la_fecha_de_inicio	La fecha de fin debe ser posterior a la fecha de inicio
debe_seleccionar_al_menos_una_disponibilidad	Debe seleccionar al menos una disponibilidad
no_hay_disponibilidades_para_la_opcion_seleccionada	No hay disponibilidades para la opción seleccionada
debe_seleccionar_un_horario_con_disponibilidades	Debe seleccionar un horario con disponibilidades
el_nombre_del_recurso_es_obligatorio	El nombre del recurso es obligatorio
la_descripcion_del_recurso_es_obligatoria	La descripción del recurso es obligatoria
los_dias_de_inicio_de_la_ventana_de_intranet_es_obligatorio	Los días de inicio de la ventana de intranet es obligatorio
los_dias_de_la_ventana_de_intranet_es_obligatorio	Los días de la ventana de intranet es obligatorio
los_dias_de_la_ventana_de_intranet_debe_ser_mayor_a_cero	Los días de la ventana de intranet debe ser mayor a cero
los_dias_de_la_ventana_de_internet_debe_ser_mayor_a_cero	Los días de la ventana de internet debe ser mayor a cero
version	Versión
los_dias_de_la_ventana_de_internet_es_obligatorio	Los días de la ventana de internet es obligatorio
los_dias_de_inicio_de_la_ventana_de_internet_es_obligatorio	Los días de inicio de la ventana de internet es obligatorio
la_cantidad_de_cupos_minimos_es_obligatoria	La cantidad de cupos mínimos es obligatoria
la_cantidad_de_cupos_minimos_debe_ser_mayor_o_igual_a_cero	La cantidad de cupos mínimos debe ser mayor o igual a cero
la_cantidad_de_dias_a_generar_es_obligatoria	La cantidad de días a generar es obligatoria
la_cantidad_de_dias_a_generar_debe_ser_mayor_a_cero	La cantidad de días a generar debe ser mayor a cero
la_cantidad_de_dias_a_generar_debe_ser_mayor_o_igual_que_la_suma_internet	La cantidad de días a generar debe ser mayor o igual que la suma de los días de inicio de la ventana de internet y los días de la ventana de internet
la_cantidad_de_dias_a_generar_debe_ser_mayor_o_igual_que_la_suma_intranet	La cantidad de días a generar debe ser mayor o igual que la suma de los días de inicio de la ventana de intranet y los días de la ventana de intranet
el_largo_de_la_lista_de_espera_debe_ser_mayor_que_cero	El largo de la lista de espera debe ser mayor que cero
ya_existe_un_recurso_con_el_nombre_especificado	Ya existe un recurso con el nombre especificado
la_fecha_de_inicio_de_disponibilidad_debe_ser_posterior_a_la_fecha_de_inicio	La fecha de inicio de disponibilidad debe ser posterior a la fecha de inicio
la_etiqueta_del_dato_es_obligatoria	La etiqueta del dato es obligatoria
debe_seleccionar_una_reserva	Debe seleccionar una reserva
el_codigo_del_organismo_es_obligatorio	El código del organismo es obligatorio
el_nombre_de_la_empresa_es_obligatorio	El nombre de la empresa es obligatorio
el_origen_de_datos_de_la_empresa_es_obligatorio	El origen de datos de la empresa es obligatorio
la_finalidad_para_la_clausula_de_consentimiento_informado_es_obligatoria	La finalidad para la cláusula de consentimiento informado es obligatoria
la_direccion_para_la_clausula_de_consentimiento_informado_es_obligatoria	La dirección para la cláusula de consentimiento informado es obligatoria
el_responsable_para_la_clausula_de_consentimiento_informado_es_obligatorio	El responsable para la cláusula de consentimiento informado es obligatorio
ya_existe_una_empresa_con_el_nombre_especificado	Ya existe una empresa con el nombre especificado
no_se_puede_obtener_los_tramites_porque_la_empresa_no_esta_asociada_a_ningun_organismo	No se puede obtener los tramites porque la empresa no está asociada a ningún organismo
empresa_eliminada	Empresa eliminada
la_fecha_de_inicio_debe_ser_igual_o_posterior_a_la_fecha_de_inicio_de_la_disponibilidad_del_recurso	La fecha de inicio debe ser igual o posterior a la fecha de inicio de la disponibilidad del recurso
no_es_posible_confirmar_su_reserva	No es posible confirmar su reserva, solicite ayuda en forma telefónica
no_es_posible_cancelar_sus_reservas_anteriores	No es posible cancelar sus reservas anteriores, solicite ayuda en forma telefónica
ya_tiene_una_reserva_para_el_dia	Ya tiene una reserva para el día
los_caracteres_escritos_no_son_correctos	Los caracteres escritos no coinciden con el texto que aparece en la imagen
debe_completar_el_campo_campo	Debe completar el campo {campo}
imagen_de_seguridad	Imagen de seguridad
el_nombre_del_usuario_es_obligatorio	El nombre del usuario es obligatorio
el_correo_electronico_del_usuario_es_obligatorio	El correo electrónico del usuario es obligatorio
ya_existe_una_agenda_con_el_nombre_especificado	Ya existe una agenda con el nombre especificado
la_descripcion_de_la_agenda_es_obligatoria	La descripción de la agenda es obligatoria
no_se_encuentra_la_agenda_especificada	No se encuentra la agenda especificada
no_se_puede_eliminar_la_agenda_porque_hay_recursos_vivos	No se puede eliminar la agenda porque hay recursos vivos
no_se_puede_eliminar_la_agenda_porque_hay_reservas_vivas	No se puede eliminar la agenda porque hay reservas vivas
no_se_puede_eliminar_el_recurso_porque_hay_disponibilidades_vivas	No se puede eliminar el recurso porque hay disponibilidades vivas
no_se_puede_eliminar_el_recurso_porque_hay_reservas_vivas	No se puede eliminar el recurso porque hay reservas vivas
no_se_puede_eliminar_el_recurso_porque_hay_agrupaciones_de_datos_vivas	No se puede eliminar el recurso porque hay agrupaciones de datos vivas
no_se_puede_eliminar_el_recurso_porque_hay_validaciones_vivas	No se puede eliminar el recurso porque hay validaciones vivas
no_se_encuentra_el_recurso_especificado	No se encuentra el recurso especificado
ya_existe_una_agrupacion_con_el_nombre_especificado	Ya existe una agrupación con el nombre especificado
no_se_encuentra_la_empresa_especificada	No se encuentra la empresa especificada
no_se_encuentra_la_agrupacion_especificada	No se encuentra la agrupación especificada
el_tipo_del_dato_es_obligatorio	El tipo del dato es obligatorio
la_fila_del_dato_es_obligatoria	La fila del dato es obligatoria
la_columna_del_dato_es_obligatoria	La columna del dato es obligatoria
el_largo_del_dato_es_obligatorio	El largo del dato es obligatorio
el_ancho_de_despliegue_es_obligatorio	El ancho de despliegue es obligatorio
el_ancho_de_despliegue_debe_ser_mayor_a_cero	El ancho de despliegue debe ser mayor a cero
el_orden_en_el_llamador_es_obligatorio	El orden en el llamador es obligatorio
la_fila_del_dato_debe_ser_mayor_a_cero	La fila del dato debe ser mayor a cero
ya_existe_un_dato_con_el_nombre_especificado	Ya existe un dato con el nombre especificado
el_largo_del_dato_debe_ser_mayor_a_cero	El largo del dato debe ser mayor a cero
el_orden_en_el_llamador_debe_ser_mayor_a_cero	El orden en el llamador debe ser mayor a cero
el_valor_del_dato_es_obligatorio	El valor del dato es obligatorio
el_orden_del_dato_es_obligatorio	El orden del dato es obligatorio
el_orden_del_dato_debe_ser_mayor_a_cero	El orden del dato debe ser mayor a cero
no_se_encuentra_el_dato_especificado	No se encuentra el dato especificado
ya_existe_otro_valor_con_la_misma_etiqueta_y_valor	Ya existe otro valor con la misma etiqueta o valor que se solapa en el período
no_hay_datos_para_mostrar	No hay datos para mostrar
la_hora_de_fin_debe_ser_posterior_a_la_hora_de_inicio	La hora de fin debe ser posterior a la hora de inicio
la_frecuencia_debe_ser_mayor_que_cero	La frecuencia debe ser mayor que cero
la_frecuencia_es_obligatoria	La frecuencia es obligatoria
el_cupo_total_es_obligatorio	El cupo total es obligatorio
el_cupo_total_debe_ser_mayor_a_cero	El cupo total debe ser mayor a cero
la_fecha_debe_ser_igual_o_posterior_a_la_fecha_de_inicio_de_la_disponibilidad_del_recurso	La fecha debe ser igual o posterior a la fecha de inicio de la disponibilidad del recurso
la_fecha_debe_ser_igual_o_anterior_a_la_fecha_de_fin_de_la_disponibilidad_del_recurso	La fecha debe ser igual o anterior a la fecha de fin de la disponibilidad del recurso
la_fecha_no_corresponde_a_un_dia_habil	La fecha no corresponde a un día hábil
ya_existe_alguna_disponibilidad_para_la_fecha_y_la_hora_de_inicio_especificadas	Ya existe alguna disponibilidad para la fecha y la hora de inicio especificadas
la_fecha_debe_estar_comprendida_en_el_periodo_fdesde_a_fhasta	La fecha debe estar comprendida en el período {fdesde} a {fhasta}
ya_existen_disponibilidades_para_algun_dia_en_el_periodo_especificado	Ya existen disponibilidades para algún día en el período especificado
no_existen_disponibilidades_generadas_para_la_fecha_especificada	No existen disponibilidades generadas para la fecha especificada
la_cantidad_de_dias_comprendidos_en_el_periodo_debe_ser_menor_que_la_cantidad_de_dias_a_generar_para	La cantidad de dias comprendidos en el período debe ser menor que la cantidad de dias a generar para el recurso
la_fecha_de_fin_debe_ser_igual_o_anterior_a_la_fecha_de_fin_de_la_disponibilidad_del_recurso	La fecha de fin debe ser igual o anterior a la fecha de fin de la disponibilidad del recurso
las_fechas_deben_estar_comprendidas_en_el_periodo_fdesde_a_fhasta_y_no_abarcar_mas_de_dias_dias	Las fechas deben estar comprendidas en el período {fdesde} a {fhasta} y no abarcar más de {dias} días
no_se_puede_eliminar_las_disponibilidades_porque_hay_reservas_vivas	No se puede eliminar las disponibilidades porque hay reservas vivas
no_hay_disponibilidades_para_el_periodo_especificado	No hay disponibilidades para el período especificado
esta_seguro_que_desea_eliminar_las_disponibilidades	¿Esta seguro que desea eliminar las disponibilidades?
el_oid_de_la_empresa_es_obligatorio	El OID de la empresa es obligatorio
empresa_oid_ayuda	Este dato puede consultarlo en http://unaoid.gub.uy
no_se_pudo_generar_las_comunicaciones	No se pudo generar las comunicaciones
oid	OID
no_se_encuentra_la_reserva_especificada	No se encuentra la reserva especificada
ya_existe_una_reserva_para_el_dia_especificado_con_los_datos_proporcionados	Ya existe una reserva para el día especificado con los datos proporcionados
hay_campos_obligatorios_sin_completar	Hay campos obligatorios sin completar
el_campo_campo_debe_contener_solo_digitos	El campo {campo} debe contener solo dígitos
no_se_pudo_consultar_el_servicio_web	No se pudo consultar el servicio web
ha_pasado_demasiado_tiempo_desde_su_ultima_accion	Ha pasado demasiado tiempo desde su última acción
no_se_puede_enviar_el_correo_porque_el_usuario_no_tiene_direccion_de_correo_electronico	No se puede enviar el correo porque el usuario no tiene dirección de correo electrónico
no_se_pudo_enviar_el_correo_electronico_de_confirmacion_tome_nota_de_los_datos_de_la_reserva	No se pudo enviar el correo electrónico de confirmación; tome nota de los datos de la reserva
codigo_de_trazabilidad	Código de trazabilidad
idioma	Idioma
idiomas_soportados	Idiomas soportados
no_se_puede_guardar_la_empresa_porque_no_existe_el_esquema	No se puede guardar la empresa porque no existe el origen de datos especificado
no_se_puede_modificar_las_fechas_porque_quedarian_disponibilidades_fuera_del_periodo_especificado	No se puede modificar las fechas porque quedarían disponibilidades fuera del período especificado
no_se_puede_modificar_las_fechas_porque_quedarian_reservas_fuera_del_periodo_especificado	No se puede modificar las fechas porque quedarían reservas fuera del período especificado
no_se_ha_definido_ningun_idioma_valido_para_la_agenda	No se ha definido ningún idioma válido para la agenda
establecer_como_idioma_por_defecto	Establecer como idioma por defecto
el_dia_y_la_hora_son_obligatorios	El día y la hora son obligatorios
el_numero_es_obligatorio	El número es obligatorio
el_numero_ingresado_no_es_valido	El número ingresado no es válido
personalizacion_de_apariencia	Personalización de la apariencia
pie_de_pagina_publico	Pie de página público
el_largo_en_el_llamador_es_obligatorio	El largo en el llamador es obligatorio
el_largo_en_el_llamador_debe_ser_mayor_a_cero	El largo en el llamador debe ser mayor a cero
sae	Sistema de Agenda Electrónica
consultas	Consultas
por_id.	Por identificador
por_numero	Por número
por_datos	Por datos
reporte	Reporte
reporte_asistencia	Reporte de asistencia
reporte_atencion_funcionario	Reporte de atención por funcionario
reporte_tiempo_atencion_funcionario	Reporte de tiempo de atención por funcionario
numero_de_la_reserva	Número de la reserva
id._de_la_reserva	Id. de la reserva
datos_de_la_reserva	Datos de la reserva
estado	Estado
observaciones	Observaciones
fecha_de_creacion	Fecha de creación
minutos	Minutos
id	Id
fecha_creacion	Fecha de creación
usuario_creacion	Usuario de creación
origen	Origen
accion	Acción
ver_detalle	Ver detalle
fecha_desde	Fecha desde
fecha_hasta	Fecha hasta
reporte_asistencia_por_periodo	Reporte de asistencia por período
reporte_atencion_por_periodo	Reporte de atención por período
reporte_tiempos_de_atencion	Reporte de tiempos de atención
fecha_y_numero_de_la_reserva	Fecha y número de la reserva
una_pagina_por_hora	Una página por hora
el_tamano_maximo_admitido_para_el_logo_es_de_1mb	El tamaño máximo admitido para el logo es de 1MB
el_texto_alternativo_del_logo_no_puede_estar_vacio	El texto alternativo del logo es obligatorio
la_cantidad_de_cupos_a_aumentar_no_puede_ser_cero	La cantidad de cupos a aumentar no puede ser cero
la_cantidad_de_cupos_a_disminuir_no_puede_ser_cero	La cantidad de cupos a disminuir no puede ser cero
no_se_permiten_valores_negativos	No se permiten valores negativos
el_campo_nombre_solo_puede_contener_letras_sin_tildes,_numeros_y__	El campo Nombre solo puede contener letras sin tildes, números y _
el_campo_nombre_no_puede_comenzar_con_un_numero	El campo Nombre no puede comenzar con un número
correo_electronico_no_valido	La dirección de correo electrónico no es válida
el_valor_para_el_campo_cantidad_de_cupos_debe_ser_numerico	El valor para el campo Cantidad de cupos debe ser numérico
el_largo_del_llamador_debe_ser_mayor_a_cero	El largo del llamador debe ser mayor a cero
ya_existe_una_empresa_con_el_mismo_valor_para_origen_de_datos	Ya existe una empresa con el mismo origen de datos
existe_una_empresa_eliminada_con_el_mismo_valor_para_origen_de_datos	Ya existe una empresa eliminada con el mismo origen de datos
la_fecha_debe_ser_posterior_a_la_fecha_fdesde	La fecha de fin debe ser posterior a la fecha de inicio
no_se_puede_eliminar_la_empresa_porque_hay_reservas_vivas	No se puede eliminar la empresa porque hay reservas vivas
mail_no_valido	La dirección de correo electrónica no es válida
continuar_tramite	Continuar con el trámite
mensajes_en_el_formulario_warn	Hay {count} advertencias a las cuales debe prestar atención
mensajes_en_el_formulario_info	Ejecución exitosa
el_orden_de_la_agrupacion_es_obligatorio	El orden de la agrupación es obligatorio
el_orden_de_la_agrupacion_debe_ser_mayor_a_cero	El orden de la agrupación debe ser mayor a cero
no_se_puede_modifcar_una_agrupacion_eliminada	No se puede modifcar una agrupacion dada de baja
cual_es_la_palabra_1_de_la_frase	¿Cuál es la primera palabra de la frase "{frase}"?
cual_es_la_palabra_2_de_la_frase	¿Cuál es la segunda palabra de la frase "{frase}"?
cual_es_la_palabra_3_de_la_frase	¿Cuál es la tercera palabra de la frase "{frase}"?
la_agrupacion_ya_esta_eliminada	La agrupación ya está eliminada
no_se_puede_eliminar_la_agrupación_porque_tiene_datos_asociados	No se puede eliminar la agrupación porque tiene datos a solicitar asociados
requiere_cda	Control de acceso
considerar_todas	Considerar todas
cual_es_la_palabra_4_de_la_frase	¿Cuál es la cuarta palabra de la frase "{frase}"?
cual_es_la_palabra_5_de_la_frase	¿Cuál es la quinta palabra de la frase "{frase}"?
solicitar_otra_frase	Solicitar otra frase
consulte_al_administrador_de_bases_de_datos	Consulte al administrador de bases de datos
no_existe_el_origen_de_datos	No existe el origen de datos
la_sesion_ha_expirado	La sesión ha expirado
debe_volver_al_sitio_desde_donde_accedio_para_volver_a_comenzar	Debe volver al sitio desde el cual accedió para volver a comenzar
el_codigo_de_la_unidad_ejecutora_es_obligatorio	El código de la unidad ejecutora es obligatorio
solo_si_no_tiene_datos_asociados	Solo si no tiene datos asociados
no_se_puede_modifcar_un_dato_eliminado	No se puede modifcar un dato eliminado
mensajes_login_error	Error de inicio de sesión
el_campo_campo_solo_puede_contener_digitos	El campo {campo} solo puede contener dígitos
no_es_una_direccion_de_correo_electronico_valida	El valor ingresado no es una dirección de correo electrónico válida
el_valor_ingresado_no_es_aceptable	El valor ingresado no es aceptable
la_respuesta_a_la_pregunta_de_seguridad_no_es_correcta	La respuesta a la pregunta de seguridad no es correcta
olvido_su_contraseña	Si olvidó su contraseña póngase en contacto con soporte@agesic.com.uy
cupo_por_periodo	Cupo por período
el_cupo_por_periodo_es_obligatorio	El cupo por período es obligatorio
el_cupo_por_periodo_debe_ser_mayor_a_cero	El cupo por período debe ser mayor a cero
el_valor_es_obligatorio	El valor es obligatorio
no_se_encuentra_la_disponibilidad_especificada	No se encuentra la disponibilidad especificada
no_se_puede_modifcar_una_disponibilidad_eliminada	No se puede modifcar una disponibilidad dada de baja
el_cupo_debe_ser_mayor_o_igual_a_la_cantidad_de_reservas_existentes	El valor del cupo debe ser mayor o igual a la cantidad de reservas existentes
el_cupo_se_modifico_parcialmente_porque_hay_mas_reservas	El cupo se modificó parcialmente porque hay más reservas que el valor solicitado
para_diahora_el_cupo_se_modifico_parcialmente_porque_hay_mas_reservas	El cupo para {diahora} se modificó parcialmente porque hay más reservas que el valor solicitado
el_identificador_de_la_reserva_es_obligatorio	El identificador de la reserva es obligatorio
el_identificador_de_la_reserva_debe_ser_numerico	El identificador de la reserva debe ser numérico
cedula_de_identidad_invalida	La cédula de identidad ingresada no es válida
solo_se_puede_asignar_un_rol	Solo se puede asignar un rol al usuario en una empresa
configuracion_para_intranet	Configuración para intranet
dias_de_inicio_de_la_ventana_de_intranet_descripcion	Cantidad de días que existen entre la fecha de hoy y la fecha en la cual se puede comenzar a hacer reservas. Por ejemplo, se puede reservar a partir de dos días desde que se ingresó al sistema.
cantidad_de_dias_siguientes_disponibles	Cantidad de días siguientes disponibles para hacer una reserva
dias_de_inicio_de_la_ventana_de_internet_descripcion	Cantidad de días que existen entre la fecha de hoy y la fecha en la cual se puede comenzar a hacer reservas. Por ejemplo, se puede reservar a partir de dos días desde que se ingresó al sistema.
configuracion_del_llamador	Configuración del llamador
serie_asociada_a_los_numeros_de_reserva	Serie asociada a los números de reserva
confirmacion_de_datos	Confirmación de datos
el_tipo_de_documento_es_obligatorio	El tipo de documento es obligatorio
el_numero_de_documento_es_obligatorio	El número de documento es obligatorio
los_datos_ingresados_no_son_correctos	Los datos ingresados no son correctos
debe_haber_una_reserva_seleccionada	Debe haber una reserva seleccionada
tipo_de_documento	Tipo de documento
numero_de_documento	Número de documento
ir_a_la_busqueda_de_reservas	Ir a la búsqueda de reservas
el_estado_es_obligatorio	El estado es obligatorio
reporte_reservas	Reporte de reservas
reporte_asistencias	Reporte de asistencias
el_codigo_del_tramite_es_obligatorio	El código del trámite es obligatorio
lugar	lugar
lugares	lugares
datos_del_recurso	Datos generales
inicio_de_disponibilidad	Inicio de atención al público
fin_de_disponibilidad	Fin de atención al público
disponible_para_internet	Visible en internet
dias_de_la_ventana_de_internet	Duración de la ventana para agendar
largo_de_la_lista_de_espera	Número de filas en la lista de espera
con_trazabilidad	Integrar con trazabilidad
configuracion_para_internet	Configuración para internet
reporte_reserva_por_periodo_y_estado	Reporte de reserva por período y estado
el_codigo_de_seguridad_es_obligatorio	El código de seguridad es obligatorio
no_olvide_comunicarle_al_ciudadano_el_codigo_de_seguridad_de_la_reserva	No olvide comunicarle al ciudadano el código de seguridad de la reserva, ya que lo necesitará en caso de que decida cancelarla o modificarla.
reporte_reservas_periodo	Reporte de reservas por período
ninguna	Ninguna
ninguno	Ninguno
reserva	Reserva
ha_ocurrido_un_error_grave	Ha ocurrido un error grave que no permite continuar con su solicitud
recurso_no_encontrado	Recurso no encontrado
el_recurso_solicitado_no_existe	El recurso solicitado no existe
verifique_la_direccion_especificada	Verifique que la dirección especificada sea correcta
error_no_solucionable	Error no solucionable
ha_ocurrido_un_error_no_solucionable	Ha ocurrido un error no solucionable
cerrar	Cerrar
seguimiento	Seguimiento del trámite
mostrar_numero_de_reserva_en_el_llamador	Visible en el llamador
mostrar_numero_de_reserva_en_el_ticket	Mostrar serie y número
debe_responder_la_pregunta_de_seguridad	Debe responder la pregunta de seguridad
publicar_novedades	Publicar en PDI
novedades	Novedades
dato_es	Este dato es
incluir	Incluir
aplicar_a	Aplicar a
sabado	Sábado
tipo_usuario	Tipo de usuario
rol	Rol
mapa_de_locacion	Mapa de locación
fechas_disponibles	Fechas disponibles
fecha_no_valida	La fecha especificada no es válida
ingreso_electronico	Ingreso electrónico
login_deshabilitado	Inicio de sesión deshabilitado
el_login_ha_sido_deshabilitado	El inicio de sesión local ha sido deshabilitado
debe_configurar_cda	Debe configurar la autenticación mediante CDA
ingreso_de_usuario	Ingreso de usuario
no_se_pudo_cargar_tramites	No se pudo cargar la lista de trámites
no_se_pudo_cargar_oficinas	No se pudo cargar la lista de oficinas
se_cargaron_n_oficinas	Se cargaron {cant} oficinas
se_cargaron_n_tramites	Se cargaron {cant} tramites
no_se_pudo_actualizar_lista_de_organismos	No se pudo actualizar la lista de organismos
no_se_pudo_actualizar_lista_de_unidades_ejecutoras	No se pudo actualizar la lista de unidades ejecutoras
lista_de_organismos_actualizada	La lista de organismos fue actualizada
lista_de_unidades_ejecutas_actualizada	La lista de unidades ejecutoras fue actualizada
no_hay_otro_superadmin	No hay otro superadministrador
no_se_pudo_determinar_si_hay_otro_superadministrador	No se pudo determinar si hay otro superadministrador
el_numero_de_puesto_no_es_valido	El número de puesto no es válido
exportar	Exportar
importar	Importar
importar_recurso	Importar recurso
archivo	Archivo
subir_archivo	Subir archivo
el_tamano_maximo_admitido_es_de_1mb	El archivo debe ser menor a 1 MB
no_se_pudo_cargar_el_archivo	No se pudo cargar el archivo
no_se_pudo_realizar_la_exportacion	No se pudo realizar la exportación
archivo_cargado	Archivo cargado
gestionar_tokens	Gestionar tokens
token	Token
crear_token	Crear token
el_nombre_es_obligatorio	El nombre es obligatorio
el_correo_electronico_es_obligatorio	El correo electrónico es obligatorio
parametros_incorrectos	Parámetros incorrectos
esta_seguro_que_desea_eliminar_el_token	¿Esta seguro que desea eliminar el token?
modificar_empresa	Modificar empresa
modificar_usuario	Modificar usuario
modificar_agenda	Modificar agenda
modificar_recurso	Modificar recurso
gestionar_agrupaciones	Gestionar agrupaciones
realizar_reserva	Realizar reserva
seleccionar_agenda_recurso	Seleccionar agenda y recurso
inicio	Inicio
lista_de_llamadas	Lista de llamadas
reserva_por_id	Reserva por id
reserva_por_numero	Reserva por número
reserva_por_datos	Reserva por datos
mostrar_id_de_reserva_en_el_ticket	Mostrar identificador de la reserva
id_de_la_reserva	Identificador de la reserva
recargar_listado_de_organismos	Recargar organismos
recargar_listado_de_unidades_ejecutoras	Recargar unidades ejecutoras
recargar_listado_de_tramites	Recargar trámites
recargar_listado_de_oficinas	Recargar oficinas
el_codigo_del_usuario_es_obligatorio	La cédula de identidad del usuario es obligatoria
los_campos_indicados_con_asterisco_son_obligatorios	Los campos indicados con * son obligatorios
debe_cargar_un_archivo	Debe cargar un archivo
archivo_no_cargado	Archivo no cargado
puede_usar_las_siguientes_metavariables	Puede utilizar las siguientes metavariables
nombre_de_la_agenda_o_tramite	Nombre de la agenda o trámite
nombre_del_recurso_u_oficina	Nombre del recurso u oficina
direccion_fisica_donde_debe_concurrir	Dirección física donde debe concurrir el ciudadano
fecha_cuando_debe_concurrir	Fecha en la cual debe concurrir el ciudadano
hora_cuando_debe_concurrir	Hora en la cual debe concurrir el ciudadano
serie_asociada_al_recurso	Serie asociada al recurso
codigo_de_cancelacion_de_la_reserva	Código de cancelación de la reserva
codigo_de_trazabilidad_de_la_reserva	Código de trazabilidad de la reserva
enlace_a_la_pagina_de_cancelacion	Enlace a la página directa de cancelación de la reserva
identificador_de_la_reserva	Identificador de la reserva
puede_ingresar_codigo_html	Puede ingresar código HTML
reserva_realizada_el	Reserva realizada
considerar_el_domingo_como_dia_habil	Considerar como día hábil
domingo	Domingo
agregar_tramite	Agregar trámite
quitar_tramite	Quitar trámite
debe_haber_al_menos_un_tramite	Debe asociar al menos un trámite
el_nombre_del_tramite_es_obligatorio	El nombre del trámite es obligatorio
tramites_asociados	Trámites asociados
debe_seleccionar_el_tramite	Debe seleccionar el trámite
nombre_de_la_agenda	Nombre de la agenda
nombre_del_recurso	Nombre del recurso
nombre_del_tramite	Nombre del trámite
mantenimiento_de_acciones	Mantenimiento de acciones
listado_de_acciones	Listado de acciones
servicio	Servicio
host	Host
esta_seguro_que_desea_eliminar_la_accion	¿Está seguro que desea eliminar la acción?
agregar_accion	Agregar acción
parametros	Parámetros
agregar_parametro	Agregar parámetro
definir	Definir
asociar	Asociar
accion_creada	Acción creada
accion_modificada	Acción modificada
accion_eliminada	Acción eliminada
el_nombre_de_la_accion_es_obligatorio	El nombre de la acción es obligatorio
el_servicio_de_la_accion_es_obligatorio	El servicio de la acción es obligatorio
el_host_de_la_accion_es_obligatorio	El host de la acción es obligatorio
el_nombre_del_parametro_es_obligatorio	El nombre del parámetro es obligatorio
el_largo_del_parametro_es_obligatorio	El largo del parámetro es obligatorio
asociar_acciones_a_recurso	Asociar acciones al recurso
recurso_importado_exitosamente	Recurso importado correctamente
datos_de_la_accion	Datos de la acción
orden_de_ejecucion	Orden de ejecución
datos_de_la_asignacion	Datos de la asignación
evento	Evento
dato_a_solicitar	Dato a solicitar
la_accion_es_obligatoria	La acción es obligatoria
el_orden_de_ejecucion_es_obligatorio	El orden de ejecución es obligatorio
enviar_aviso_al_confirmar	Enviar un aviso al confirmar
el_orden_de_ejecucion_debe_ser_mayor_a_cero	El orden de ejecución debe ser mayor a cero
parametro	Parámetro
el_parametro_es_obligatorio	El parámetro es obligatorio
el_dato_a_solicitar_es_obligatorio	El dato a solicitar es obligatorio
validaciones	Validaciones
mantenimiento_de_validaciones	Mantenimiento de validaciones
agregar_validacion	Agregar validación
listado_de_validaciones	Listado de validaciones
esta_seguro_que_desea_eliminar_la_validacion	¿Está seguro que desea eliminar la validación?
datos_de_la_validacion	Datos de la validación
validacion_creada	Validación creada
validacion_modificada	Validación modificada
validacion_eliminada	Validación eliminada
el_nombre_de_la_validacion_es_obligatorio	El nombre de la validación es obligatorio
el_servicio_de_la_validacion_es_obligatorio	El servicio de la validación es obligatorio
el_host_de_la_validacion_es_obligatorio	El host de la validación es obligatorio
asociar_validaciones_a_recurso	Asociar validaciones al recurso
la_validacion_es_obligatoria	La validación es obligatoria
validacion	Validación
la_descripcion_de_la_accion_es_obligatoria	La descripción de la acción es obligatoria
el_nombre_de_la_accion_es_demasiado_largo	El nombre es demasiado largo
la_descripcion_de_la_accion_es_demasiado_larga	La descripción es demasiado larga
el_servicio_de_la_accion_es_demasiado_largo	El servicio es demasiado largo
el_host_de_la_accion_es_demasiado_largo	El host es demasiado largo
el_nombre_del_parametro_es_demasiado_largo	El nombre del parámetro es demasiado largo
la_accion_se_encuentra_asociada_a_un_recurso	La acción se encuentra asociada a un recurso
no_se_puede_modificar_los_parametros_si_estan_en_uso	No se puede modificar los parámetros si están en uso
la_descripcion_de_la_validacion_es_obligatoria	La descripción de la validación es obligatoria
ya_existe_una_accion_con_el_nombre_especificado	Ya existe una acción con el nombre especificado
no_se_encuentra_la_accion_especificada	No se encuentra la acción especificada
el_nombre_de_la_validacion_es_demasiado_largo	El nombre es demasiado largo
el_servicio_de_la_validacion_es_demasiado_largo	El servicio es demasiado largo
la_descripcion_de_la_alidacion_es_demasiado_larga	La descripción es demasiado larga
el_host_de_la_validacion_es_demasiado_largo	El host es demasiado largo
ya_existe_una_validacion_con_el_nombre_especificado	Ya existe una validación con el nombre especificado
no_se_encuentra_la_validacion_especificada	No se encuentra la validación especificada
la_validacion_se_encuentra_asociada_a_un_recurso	La acción se encuentra asociada a un recurso
reservar	Reservar
tipo	Tipo
el_codigo_y_el_nombre_del_tramite_son_obligatorios	El código y el nombre del trámite son obligatorios
debe_seleccionar_al_menos_un_idioma	Debe seleccionar al menos un idioma
agregar_asociacion	Agregar asociación
hay_tramites_repetidos	Ha especificado el mismo trámite más de una vez
agenda_copiada	Agenda copiada
debe_especificar_la_disponibilidad	Debe especificar la disponibilidad
el_horario_acaba_de_quedar_sin_cupos	El horario seleccionado acaba de quedar sin cupos, debe elegir otro horario
no_se_generaron_disponibilidades_para_todos_los_horarios	No se generaron disponibilidades para todas las horas porque ya estaban generadas anteriormente
la_fecha_de_inicio_de_vigencia_es_obligatoria	La fecha de inicio de vigencia es obligatoria
la_fecha_de_fin_de_vigencia_debe_ser_posterior_a_la_fecha_de_inicio_de_vigencia	La fecha de fin de vigencia debe ser posterior a la fecha de inicio de vigencia
la_fecha_de_inicio_de_disponibilidad_es_obligatoria	La fecha de inicio de atención al público es obligatoria
la_fecha_de_fin_de_disponibilidad_es_obligatoria	La fecha de fin de atención al público es obligatoria
la_fecha_de_fin_de_disponibilidad_debe_ser_posterior_a_la_fecha_de_disponibilidad_de_vigencia	La fecha de fin de atención al público debe ser posterior a la fecha de inicio de atención al público
la_fecha_de_inicio_de_disponibilidad_debe_ser_igual_o_posterior_a_la_fecha_de_inicio_de_vigencia	La fecha de inicio de atención al público debe ser posterior a la fecha de inicio de vigencia
la_fecha_de_fin_de_vigencia_es_obligatoria	La fecha de fin de vigencia es obligatoria
la_fecha_de_fin_de_disponibilidad_debe_ser_igual_o_anterior_a_la_fecha_de_fin_de_vigencia	La fecha de fin de atención al público debe ser anterior a la fecha de fin de vigencia
este_campo_sera_su_codigo_de_usuario	Este campo será su código de usuario
la_reserva_no_corresponde_al_recurso_seleccionado	La reserva no corresponde al recurso seleccionado
reportes	Reportes
roles_del_usuario_por_recurso	Roles del usuario por recurso
generador_de_reportes	Generador de reportes
dias_a_aplicar	Días a los cuales aplicar
lunes	Lunes
martes	Martes
miercoles	Miércoles
jueves	Jueves
viernes	Viernes
configuracion_de_atencion_presencial	Configuración de atención presencial
atencion_presencial	Atención presencial
admite_atencion_presencial	Admite atención presencial
cupos_por_dia	Cupos por día
la_cantidad_de_cupos_por_dia_debe_ser_mayor_a_cero	La cantidad de cupos por día debe ser mayor a cero
los_dias_de_inicio_de_la_ventana_de_internet_debe_ser_mayor_o_igual_a_cero	Los días de inicio de la ventana de internet debe ser mayor o igual a cero
los_dias_de_inicio_de_la_ventana_de_intranet_debe_ser_mayor_o_igual_a_cero	Los días de inicio de la ventana de intranet debe ser mayor o igual a cero
el_recurso_no_admite_atencion_presencial	El recurso seleccionado no admite atención presencial
no_hay_cupos_disponibles_para_hoy	No hay cupos disponibles para hoy
debe_especificar_la_ventana	Debe especificar la ventana
no_se_encuentra_el_token_especificado	No se encuentra el token especificado
la_ventana_especificada_no_es_valida	La ventana especificada no es válida
reporte_atencion_presencial	Reporte de atención presencial
puesto	Puesto
funcionario	Funcionario
tiempo_en_minutos	Tiempo (mins)
atencion	Atención
no_marcado	No marcado
asistencias	Asistencias
inasistencias	Inasistencias
atenciones	Atenciones
reporte_para_todas_las_agendas_y_recursos	No tiene un recurso ni agenda seleccionada, el reporte se genera contemplando a todos los recursos y agendas
reporte_para_todos_los_recursos	No tiene un recurso seleccionado, el reporte se genera contemplando a todos los recursos de la agenda seleccionada
el_recurso_no_admite_atencion_presencial_para_hoy	El recurso no admite atención presencial en el día de hoy
no_hay_recursos_disponibles_para_la_agenda_seleccionada	No hay recursos disponibles para la agenda seleccionada
presencial	Presencial
eliminar_disponibilidades_semana	Eliminar disponibilidades por semana
eliminar_disponibilidades_periodo	Eliminar disponibilidades por período
eliminar_disponibilidades_de_todo_el_periodo	Eliminar disponibilidades de todo el período
eliminar_semana	Eliminar semana
eliminar_periodo	Eliminar período
la_etiqueta_de_la_agrupacion_es_obligatoria	La etiqueta de la agrupación es obligatoria
solo_lectura_si_hay_valor_en_la_url	Solo lectura si se especifica el valor en la URL
cancelar_reserva_por_periodo	Cancelar reservas por período
esta_seguro_que_desea_cancelar_las_reservas	¿Está seguro que desea cancelar todas las reservas del período especificado?
cancelar_las_reservas_de_todo_el_periodo	Cancelar todas las reservas del período
comunicacion_a_enviar	Comunicación a enviar
asunto_del_mensaje	Asunto del mensaje
cuerpo_del_mensaje	Cuerpo del mensaje
el_asunto_del_mensaje_es_obligatorio	El asunto del mensaje es obligatorio
el_cuerpo_del_mensaje_es_obligatorio	El cuerpo del mensaje es obligatorio
reservas_canceladas	Reservas canceladas
no_se_pudo_enviar_comunicacion_para_las_reservas	No se pudo enviar mensaje de cancelación a algunas reservas
no_hay_una_direccion_de_correo_a_la_cual_enviar_el_mensaje	No hay una dirección de correo electrónico a la cual enviar el mensaje
pendiente	Pendiente
reservada	Reservada
cancelada	Cancelada
usada	Usada
tipo_de_cancelacion	Tipo de cancelación
usuario_de_cancelacion	Usuario de cancelación
fecha_de_cancelacion	Fecha de cancelación
el_nombre_de_la_agenda_no_es_valido	El nombre de la agenda no es válido
el_nombre_del_recurso_no_es_valido	El nombre del recurso no es válido
quitar_logo	Quitar logo
debe_seleccionar_al_menos_un_dia	Debe selecconar al menos un día de la semana
la_fecha_debe_ser_igual_o_posterior_a_hoy	La fecha debe ser igual o posterior a hoy
la_fecha_de_inicio_debe_ser_igual_o_posterior_a_hoy	La fecha de inicio debe ser igual o posterior a hoy
la_fecha_de_fin_debe_ser_igual_o_posterior_a_hoy	La fecha de fin debe ser igual o posterior a hoy
la_recurso_no_esta_vigente	El recurso no está vigente
configuracion_del_ticket	Configuración del ticket
fuente	Fuente
tamanio_fuente_chica	Tamaño de la fuente chica
tamanio_fuente_normal	Tamaño de la fuente normal
tamanio_fuente_grande	Tamaño de la fuente grande
previsualizar_ticket	Previsualizar ticket
es_la_ultima_empresa	Es la última empresa
debe_quedar_al_menos_una_empresa	Debe quedar al menos una empresa viva
la_fecha_de_inicio_debe_ser_anterior_a_la_fecha_de_fin	La fecha de inicio debe ser anterior a la fecha de fin
no_se_pudo_realizar_la_importacion	No se pudo realizar la importación
la_fecha_de_inicio_es_invalida	La fecha de inicio es inválida
la_fecha_de_fin_es_invalida	La fecha de fin es inválida
el_campo_campo_no_tiene_una_fecha_valida	El valor del campo {campo} no es una fecha válida
la_fecha_de_inicio_de_vigencia_es_invalida	La fecha de inicio de vigencia es inválida
la_fecha_de_fin_de_vigencia_es_invalida	La fecha de fin de vigencia es inválida
la_fecha_de_inicio_de_disponibilidad_es_invalida	La fecha de inicio de atención al público es inválida
la_fecha_de_fin_de_disponibilidad_es_invalida	La fecha de fin de atenciónal público  es inválida
la_fecha_es_invalida	La fecha es inválida
mensajes_en_el_formulario_error	Hay {count} errores en el formulario
pagina	Página
pagina_anterior	Anterior
pagina_siguiente	Siguiente
pagina_primera	Primera
pagina_ultima	Última
no_hay_cupos_disponibles_para_el_recurso	En la oficina seleccionada no hay cupos disponibles
proximamente_se_añadiran_cupos	A la brevedad se añadirán cupos
ya_tiene_una_reserva_para_el_dia_seleccionado	Ya tiene una reserva para el día seleccionado
solo_se_permite_una_reserva_diaria	Solo se permite una reserva diaria
volver_al_paso_anterior_para_seleccionar_otro_dia	Puede volver al paso anterior para seleccionar otro día disponible
mensaje_en_el_formulario_error	Hay un error en el formulario
mensaje_en_el_formulario_info	Ejecución exitosa
mensaje_en_el_formulario_warn	Hay una advertencia a la cual debe prestar atención
el_periodo_no_puede_ser_mayor_a_un_ano	El período no puede ser mayor a un año
configuracion	Configuración
configuracion_global	Configuración global
editar_configuracion	Editar configuración
solicite_turno_para_ser_atendido	Solicite turno para ser atendido
no_tengo_cedula	No tengo cédula de identidad
solicitar_turno	Solicitar turno
debe_seleccionar_el_tipo_de_documento	Debe seleccionar el tipo de documento
debe_ingresar_el_numero_de_documento	Debe ingresar el número de documento
solicitud_confirmada	Solicitud confirmada
sera_llamado_por_documento	Será llamado por su número de documento
solicitud_de_atencion	Solicitud de atención
no_tiene_acceso_al_recurso_o_no_existe	No tiene acceso al recurso solicitado o éste no existe
si_error_contacte_al_administrador	Si cree que es un error contacte al administrador
configuracion_de_reserva_multiple	Configuración de reserva múltiple
reserva_multiple	Reserva múltiple
admite_reserva_multiple	Admite reserva múltiple
reservas_incluidas	Reservas incluidas
no_es_posible_cambiar_de	No es posible cambiar de
ya_hay_una_reserva	Ya hay una reserva añadida
confirmar_reservas	Confirmar reservas
confirmacion_de_reservas	Confirmación de reservas
reservas_confirmadas	Todas las reservas están confirmadas
fecha_y_hora	Fecha y hora
seleccionar_recurso	Seleccionar recurso
otra_reserva	Otra reserva
la_cedula_es_obligatoria	La cédula de identidad es requerida
identificacion	Identificación
cancelar_reservas	Cancelar reservas
el_token_esta_cancelado	Las reservas ya están canceladas
el_token_esta_confirmado	Las reservas ya están confirmadas
no_es_posible_cambiar_de_recurso	No es posible cambiar de recurso
no_se_encuentra_el_token	No se encuentra el token especificado
el_token_ha_expirado	El token especificado ha expirado
no_es_posible_cambiar_de_tramite	No es posible cambiar de trámite
confirma_cancelar_las_reservas	¿Confirma la cancelación de todas las reservas?
el_recurso_no_admite_reservas_multiples	El recurso indicado no admite reservas múltiples
debe_especificar_el_token_de_reservas	Debe especificar el token de reservas múltiples
la_reserva_no_corresponde_al_token	La reserva no corresponde al token
reserva_original	Reserva original
seleccionar_reserva	Seleccionar reserva
reserva_nueva	Reserva nueva
modificar_reserva	Modificar reserva
debe_especificar_los_datos_personales	Debe especificar los datos personales
debe_especificar_el_tramite	Debe especificar el trámite
no_se_encuentra_el_token_de_reservas_especificado	No se encuentra el token de reservas especificado
debe_especificar_el_token	Debe especificar el token
la_reserva_esta_utilizada	La reserva está utilizada
configuracion_de_cambios_de_reservas	Configuración de cambios de reservas
cambios_reserva	Cambios de reservas
admite_cambios_reserva	Admite cambios de reservas
tiempo_previo	Tiempo previo
tiempo_unidad	Unidad de tiempo
dias	Días
horas	Horas
el_tiempo_previo_para_cambios_es_requerido	El tiempo previo para cambios es requerido
el_tiempo_previo_para_cambios_debe_ser_mayor_a_cero	El tiempo previo para cambios debe ser mayor a cero
enlace_a_la_pagina_de_modificacion	Enlace a la página de modificación
el_recurso_no_admite_cambios_de_reservas	El recurso no admite cambios de reservas
la_reserva_especificada_ya_no_admite_cambios	La reserva ya no admite cambios
periodo_validacion	Período de validación
ya_tiene_una_reserva_para_el_periodo	Ya tiene una reserva para el período {periodo}
el_periodo_de_validacion_es_obligatorio	El período de validación es obligatorio
el_periodo_de_validacion_debe_ser_mayor_o_igual_a_cero	El período de validación debe ser mayor o igual a cero
solo_se_permite_una_reserva_en_un_periodo_de_dias	Solo se permite una reserva en un período de {dias} días
configuracion_de_validacion_por_ip	Configuración de validación por dirección IP
validar_por_ip	Validar por dirección IP
cantidad_reservas_por_ip	Cantidad de reservas por dirección IP
periodo_validacion_por_ip	Período de validación
direcciones_ip_sin_validacion	Direcciones IP sin validación
la_cantidad_de_reservas_por_ip_es_obligatoria	La cantidad de reservas por IP es obligatoria
la_cantidad_de_reservas_por_ip_debe_ser_mayor_a_cero	La cantidad de reservas por IP debe ser mayor a cero
el_periodo_de_validacion_por_ip_es_obligatorio	El período de validación por IP es obligatorio
el_periodo_de_validacion_por_ip_debe_ser_mayor_o_igual_a_cero	El período de validación por IP debe ser mayor o igual a cero
limite_de_reservas_para_la_direccion_ip_alcanzado	No se admiten más de {cantidad} reservas desde una misma dirección IP
consulta_de_cancelaciones	Consulta de cancelaciones
creacion	Creación
cancelacion	Cancelación
todos	Todos
todas	Todas
cantidad_de_elementos	Cantidad de elementos
configuracion_de_cancelaciones_de_reservas	Configuración de cancelaciones de reservas
cancelaciones_de_reservas	Cancelaciones de reservas
tipo_liberacion_cupo	Tipo de liberación del cupo
inmediata	Inmediata
diferida	Diferida
el_tiempo_previo_para_cancelaciones_es_requerido	El tiempo previo para cancelaciones es requerido
el_tiempo_previo_para_cancelaciones_debe_ser_mayor_o_igual_a_cero	El tiempo previo para cancelaciones debe ser mayor o igual a cero
el_tipo_de_cancelacion_es_obligatorio	El tipo de cancelación es obligatorio
ha_expirado_el_plazo_de_cancelacion	Ha expirado el plazo de cancelación
fecha_de_liberacion	Fecha de liberación
liberar	Liberar
no_se_encuentra_la_reserva_o_no_esta_cancelada	No se encuentra la reserva o no está cancelada
reserva_liberada	El cupo de la reserva ha sido liberado
esta_seguro_que_desea_liberar_el_cupo_de_la_reserva	¿Está seguro que desea liberar el cupo de la reserva?
configuracion_de_mi_perfil	Configuración de Mi Perfil
enviar_aviso_al_cancelar	Enviar un aviso al cancelar
enviar_aviso_recordatorio	Enviar un recordatorio
no_se_pudo_enviar_notificacion_de_confirmacion_tome_nota_de_los_datos_de_la_reserva	No se pudo enviar una notificación al usuario; tome nota de los datos de la reserva
no_se_pudo_enviar_notificacion_de_cancelacion	No se pudo enviar una notificación al usuario
acc_titulo	Título
acc_url	URL
acc_destacada	Destacada
debe_haber_una_unica_accion_de_confirmacion_destacada	Debe haber una (y solo una) acción de aviso al confirmar marcada como destacada, cuya URL no sea vacía.
debe_haber_una_unica_accion_de_cancelacion_destacada	Debe haber una (y solo una) acción de aviso al cancelar marcada como destacada, cuya URL no sea vacía.
debe_haber_una_unica_accion_de_recordatorio_destacada	Debe haber una (y solo una) acción de recordatorio marcada como destacada, cuya URL no sea vacía
vencimiento_aviso_al_confirmar	Vencimiento de aviso al confirmar
vencimiento_aviso_al_cancelar	Vencimiento de aviso al cancelar
vencimiento_recordatorio	Vencimiento de recordatorio
hora_recordatorio	Hora de recordatorio
dias_recordatorio	Días de recordatorio
textos_aviso_al_confirmar	Textos aviso al confirmar
textos_aviso_al_cancelar	Textos aviso al cancelar
textos_recordatorio	Textos recordatorio
texto_titulo_aviso_al_confirmar	Título del aviso al confirmar
texto_corto_aviso_al_confirmar	Texto corto del aviso al confirmar
texto_largo_aviso_al_confirmar	Texto largo del aviso al confirmar
texto_titulo_aviso_al_cancelar	Título del aviso al cancelar
texto_corto_aviso_al_cancelar	Texto corto del aviso al cancelar
texto_largo_aviso_al_cancelar	Texto largo del aviso al cancelar
texto_titulo_recordatorio	Título del recordatorio
texto_corto_recordatorio	Texto corto del recordatorio
texto_largo_recordatorio	Texto largo del recordatorio
accion_servicio_descripcion	Relativo a  "java:jboss/exported/"; de la forma {war}-{class}!uy.gub.sae.acciones.business.ejb.EjecutorAccionRemote
accion_host_descripcion	Por defecto es "http-remoting://localhost:8080"
validacion_servicio_descripcion	Relativo a  "java:jboss/exported/"; de la forma {war}-{class}!uy.gub.imm.sae.validaciones.business.ejb.ValidadorReservaRemote
validacion_host_descripcion	Por defecto es "http-remoting://localhost:8080"
debe_seleccionar_al_menos_un_recurso	Debe seleccionar al menos un recurso
no_se_pudo_abrir_el_llamador	No se pudo abrir el llamador
recursos_a_mostrar	Recursos a mostrar
abrir_llamador_externo	Abrir el llamador externo
abrir_llamador_interno	Abrir el llamador interno
administradorDeRecursos	Administrador de recursos
seleccion_organismo	Selección de organismo
dispone_varios_organismos	Dispone de varios organismos, debe seleccionar uno para operar
debe_selecionar_un_organismo	Debe seleccionar un organismo
configuracion_del_organismo	Configuración del organismo
reporte_recursos_por_agenda	Reporte de Recursos por Agenda
\.


--
-- Data for Name: ae_tokens; Type: TABLE DATA; Schema: global; Owner: sae
--

COPY global.ae_tokens (token, empresa_id, nombre, email, fecha) FROM stdin;
\.


--
-- Data for Name: ae_tramites; Type: TABLE DATA; Schema: global; Owner: sae
--

COPY global.ae_tramites (id, empresa_id, nombre, quees, temas, online) FROM stdin;
\.


--
-- Data for Name: ae_trazabilidad; Type: TABLE DATA; Schema: global; Owner: sae
--

COPY global.ae_trazabilidad (transaccion_id, fecha_creacion, fecha_ult_intento, intentos, datos, enviado, id, es_cabezal, reserva_id, empresa_id, es_final) FROM stdin;
\.


--
-- Data for Name: ae_unidadesejecutoras; Type: TABLE DATA; Schema: global; Owner: sae
--

COPY global.ae_unidadesejecutoras (id, codigo, nombre) FROM stdin;
\.


--
-- Data for Name: ae_usuarios; Type: TABLE DATA; Schema: global; Owner: sae
--

COPY global.ae_usuarios (id, codigo, nombre, fecha_baja, password, correoe) FROM stdin;
3	40446448	Eduardo Latorre Lauria	\N		eduardo.latorre@agesic.gub.uy
\.


--
-- Name: s_ae_accion; Type: SEQUENCE SET; Schema: empresa1; Owner: sae
--

SELECT pg_catalog.setval('empresa1.s_ae_accion', 1, false);


--
-- Name: s_ae_acciondato; Type: SEQUENCE SET; Schema: empresa1; Owner: sae
--

SELECT pg_catalog.setval('empresa1.s_ae_acciondato', 1, false);


--
-- Name: s_ae_acciones_miperfil; Type: SEQUENCE SET; Schema: empresa1; Owner: sae
--

SELECT pg_catalog.setval('empresa1.s_ae_acciones_miperfil', 1, false);


--
-- Name: s_ae_accionrecurso; Type: SEQUENCE SET; Schema: empresa1; Owner: sae
--

SELECT pg_catalog.setval('empresa1.s_ae_accionrecurso', 1, false);


--
-- Name: s_ae_agenda; Type: SEQUENCE SET; Schema: empresa1; Owner: sae
--

SELECT pg_catalog.setval('empresa1.s_ae_agenda', 1, false);


--
-- Name: s_ae_agrupacion_dato; Type: SEQUENCE SET; Schema: empresa1; Owner: sae
--

SELECT pg_catalog.setval('empresa1.s_ae_agrupacion_dato', 1, false);


--
-- Name: s_ae_anio; Type: SEQUENCE SET; Schema: empresa1; Owner: sae
--

SELECT pg_catalog.setval('empresa1.s_ae_anio', 1, false);


--
-- Name: s_ae_atencion; Type: SEQUENCE SET; Schema: empresa1; Owner: sae
--

SELECT pg_catalog.setval('empresa1.s_ae_atencion', 1, false);


--
-- Name: s_ae_comunicaciones; Type: SEQUENCE SET; Schema: empresa1; Owner: sae
--

SELECT pg_catalog.setval('empresa1.s_ae_comunicaciones', 1, false);


--
-- Name: s_ae_constval; Type: SEQUENCE SET; Schema: empresa1; Owner: sae
--

SELECT pg_catalog.setval('empresa1.s_ae_constval', 1, false);


--
-- Name: s_ae_datoasolicitar; Type: SEQUENCE SET; Schema: empresa1; Owner: sae
--

SELECT pg_catalog.setval('empresa1.s_ae_datoasolicitar', 1, false);


--
-- Name: s_ae_datodelrecurso; Type: SEQUENCE SET; Schema: empresa1; Owner: sae
--

SELECT pg_catalog.setval('empresa1.s_ae_datodelrecurso', 1, false);


--
-- Name: s_ae_datoreserva; Type: SEQUENCE SET; Schema: empresa1; Owner: sae
--

SELECT pg_catalog.setval('empresa1.s_ae_datoreserva', 1, false);


--
-- Name: s_ae_dia_mes; Type: SEQUENCE SET; Schema: empresa1; Owner: sae
--

SELECT pg_catalog.setval('empresa1.s_ae_dia_mes', 1, false);


--
-- Name: s_ae_dia_semana; Type: SEQUENCE SET; Schema: empresa1; Owner: sae
--

SELECT pg_catalog.setval('empresa1.s_ae_dia_semana', 1, false);


--
-- Name: s_ae_disponibilidad; Type: SEQUENCE SET; Schema: empresa1; Owner: sae
--

SELECT pg_catalog.setval('empresa1.s_ae_disponibilidad', 1, false);


--
-- Name: s_ae_llamada; Type: SEQUENCE SET; Schema: empresa1; Owner: sae
--

SELECT pg_catalog.setval('empresa1.s_ae_llamada', 1, false);


--
-- Name: s_ae_mes; Type: SEQUENCE SET; Schema: empresa1; Owner: sae
--

SELECT pg_catalog.setval('empresa1.s_ae_mes', 1, false);


--
-- Name: s_ae_paramaccion; Type: SEQUENCE SET; Schema: empresa1; Owner: sae
--

SELECT pg_catalog.setval('empresa1.s_ae_paramaccion', 1, false);


--
-- Name: s_ae_parametros_autocompletar; Type: SEQUENCE SET; Schema: empresa1; Owner: sae
--

SELECT pg_catalog.setval('empresa1.s_ae_parametros_autocompletar', 1, false);


--
-- Name: s_ae_paramval; Type: SEQUENCE SET; Schema: empresa1; Owner: sae
--

SELECT pg_catalog.setval('empresa1.s_ae_paramval', 1, false);


--
-- Name: s_ae_plantilla; Type: SEQUENCE SET; Schema: empresa1; Owner: sae
--

SELECT pg_catalog.setval('empresa1.s_ae_plantilla', 1, false);


--
-- Name: s_ae_recurso; Type: SEQUENCE SET; Schema: empresa1; Owner: sae
--

SELECT pg_catalog.setval('empresa1.s_ae_recurso', 1, false);


--
-- Name: s_ae_recurso_aud; Type: SEQUENCE SET; Schema: empresa1; Owner: sae
--

SELECT pg_catalog.setval('empresa1.s_ae_recurso_aud', 1, false);


--
-- Name: s_ae_reserva; Type: SEQUENCE SET; Schema: empresa1; Owner: sae
--

SELECT pg_catalog.setval('empresa1.s_ae_reserva', 1, false);


--
-- Name: s_ae_serv_autocompletar; Type: SEQUENCE SET; Schema: empresa1; Owner: sae
--

SELECT pg_catalog.setval('empresa1.s_ae_serv_autocompletar', 1, false);


--
-- Name: s_ae_servdato; Type: SEQUENCE SET; Schema: empresa1; Owner: sae
--

SELECT pg_catalog.setval('empresa1.s_ae_servdato', 1, false);


--
-- Name: s_ae_servrecurso; Type: SEQUENCE SET; Schema: empresa1; Owner: sae
--

SELECT pg_catalog.setval('empresa1.s_ae_servrecurso', 1, false);


--
-- Name: s_ae_texto_agenda; Type: SEQUENCE SET; Schema: empresa1; Owner: sae
--

SELECT pg_catalog.setval('empresa1.s_ae_texto_agenda', 1, false);


--
-- Name: s_ae_textorecurso; Type: SEQUENCE SET; Schema: empresa1; Owner: sae
--

SELECT pg_catalog.setval('empresa1.s_ae_textorecurso', 1, false);


--
-- Name: s_ae_token_reserva; Type: SEQUENCE SET; Schema: empresa1; Owner: sae
--

SELECT pg_catalog.setval('empresa1.s_ae_token_reserva', 1, false);


--
-- Name: s_ae_tramites_agendas; Type: SEQUENCE SET; Schema: empresa1; Owner: sae
--

SELECT pg_catalog.setval('empresa1.s_ae_tramites_agendas', 1, false);


--
-- Name: s_ae_valconstante; Type: SEQUENCE SET; Schema: empresa1; Owner: sae
--

SELECT pg_catalog.setval('empresa1.s_ae_valconstante', 1, false);


--
-- Name: s_ae_valdato; Type: SEQUENCE SET; Schema: empresa1; Owner: sae
--

SELECT pg_catalog.setval('empresa1.s_ae_valdato', 1, false);


--
-- Name: s_ae_validacion; Type: SEQUENCE SET; Schema: empresa1; Owner: sae
--

SELECT pg_catalog.setval('empresa1.s_ae_validacion', 1, false);


--
-- Name: s_ae_valorposible; Type: SEQUENCE SET; Schema: empresa1; Owner: sae
--

SELECT pg_catalog.setval('empresa1.s_ae_valorposible', 1, false);


--
-- Name: s_ae_valrecurso; Type: SEQUENCE SET; Schema: empresa1; Owner: sae
--

SELECT pg_catalog.setval('empresa1.s_ae_valrecurso', 1, false);


--
-- Name: s_ae_accion; Type: SEQUENCE SET; Schema: empresa2; Owner: sae
--

SELECT pg_catalog.setval('empresa2.s_ae_accion', 1, false);


--
-- Name: s_ae_acciondato; Type: SEQUENCE SET; Schema: empresa2; Owner: sae
--

SELECT pg_catalog.setval('empresa2.s_ae_acciondato', 1, false);


--
-- Name: s_ae_acciones_miperfil; Type: SEQUENCE SET; Schema: empresa2; Owner: sae
--

SELECT pg_catalog.setval('empresa2.s_ae_acciones_miperfil', 1, true);


--
-- Name: s_ae_accionrecurso; Type: SEQUENCE SET; Schema: empresa2; Owner: sae
--

SELECT pg_catalog.setval('empresa2.s_ae_accionrecurso', 1, false);


--
-- Name: s_ae_agenda; Type: SEQUENCE SET; Schema: empresa2; Owner: sae
--

SELECT pg_catalog.setval('empresa2.s_ae_agenda', 1, true);


--
-- Name: s_ae_agrupacion_dato; Type: SEQUENCE SET; Schema: empresa2; Owner: sae
--

SELECT pg_catalog.setval('empresa2.s_ae_agrupacion_dato', 1, true);


--
-- Name: s_ae_anio; Type: SEQUENCE SET; Schema: empresa2; Owner: sae
--

SELECT pg_catalog.setval('empresa2.s_ae_anio', 1, false);


--
-- Name: s_ae_atencion; Type: SEQUENCE SET; Schema: empresa2; Owner: sae
--

SELECT pg_catalog.setval('empresa2.s_ae_atencion', 1, false);


--
-- Name: s_ae_comunicaciones; Type: SEQUENCE SET; Schema: empresa2; Owner: sae
--

SELECT pg_catalog.setval('empresa2.s_ae_comunicaciones', 1, false);


--
-- Name: s_ae_constval; Type: SEQUENCE SET; Schema: empresa2; Owner: sae
--

SELECT pg_catalog.setval('empresa2.s_ae_constval', 1, false);


--
-- Name: s_ae_datoasolicitar; Type: SEQUENCE SET; Schema: empresa2; Owner: sae
--

SELECT pg_catalog.setval('empresa2.s_ae_datoasolicitar', 3, true);


--
-- Name: s_ae_datodelrecurso; Type: SEQUENCE SET; Schema: empresa2; Owner: sae
--

SELECT pg_catalog.setval('empresa2.s_ae_datodelrecurso', 1, false);


--
-- Name: s_ae_datoreserva; Type: SEQUENCE SET; Schema: empresa2; Owner: sae
--

SELECT pg_catalog.setval('empresa2.s_ae_datoreserva', 1, false);


--
-- Name: s_ae_dia_mes; Type: SEQUENCE SET; Schema: empresa2; Owner: sae
--

SELECT pg_catalog.setval('empresa2.s_ae_dia_mes', 1, false);


--
-- Name: s_ae_dia_semana; Type: SEQUENCE SET; Schema: empresa2; Owner: sae
--

SELECT pg_catalog.setval('empresa2.s_ae_dia_semana', 1, false);


--
-- Name: s_ae_disponibilidad; Type: SEQUENCE SET; Schema: empresa2; Owner: sae
--

SELECT pg_catalog.setval('empresa2.s_ae_disponibilidad', 196, true);


--
-- Name: s_ae_llamada; Type: SEQUENCE SET; Schema: empresa2; Owner: sae
--

SELECT pg_catalog.setval('empresa2.s_ae_llamada', 1, false);


--
-- Name: s_ae_mes; Type: SEQUENCE SET; Schema: empresa2; Owner: sae
--

SELECT pg_catalog.setval('empresa2.s_ae_mes', 1, false);


--
-- Name: s_ae_paramaccion; Type: SEQUENCE SET; Schema: empresa2; Owner: sae
--

SELECT pg_catalog.setval('empresa2.s_ae_paramaccion', 1, false);


--
-- Name: s_ae_parametros_autocompletar; Type: SEQUENCE SET; Schema: empresa2; Owner: sae
--

SELECT pg_catalog.setval('empresa2.s_ae_parametros_autocompletar', 1, false);


--
-- Name: s_ae_paramval; Type: SEQUENCE SET; Schema: empresa2; Owner: sae
--

SELECT pg_catalog.setval('empresa2.s_ae_paramval', 1, false);


--
-- Name: s_ae_plantilla; Type: SEQUENCE SET; Schema: empresa2; Owner: sae
--

SELECT pg_catalog.setval('empresa2.s_ae_plantilla', 1, false);


--
-- Name: s_ae_recurso; Type: SEQUENCE SET; Schema: empresa2; Owner: sae
--

SELECT pg_catalog.setval('empresa2.s_ae_recurso', 1, true);


--
-- Name: s_ae_recurso_aud; Type: SEQUENCE SET; Schema: empresa2; Owner: sae
--

SELECT pg_catalog.setval('empresa2.s_ae_recurso_aud', 1, true);


--
-- Name: s_ae_reserva; Type: SEQUENCE SET; Schema: empresa2; Owner: sae
--

SELECT pg_catalog.setval('empresa2.s_ae_reserva', 1, false);


--
-- Name: s_ae_serv_autocompletar; Type: SEQUENCE SET; Schema: empresa2; Owner: sae
--

SELECT pg_catalog.setval('empresa2.s_ae_serv_autocompletar', 1, false);


--
-- Name: s_ae_servdato; Type: SEQUENCE SET; Schema: empresa2; Owner: sae
--

SELECT pg_catalog.setval('empresa2.s_ae_servdato', 1, false);


--
-- Name: s_ae_servrecurso; Type: SEQUENCE SET; Schema: empresa2; Owner: sae
--

SELECT pg_catalog.setval('empresa2.s_ae_servrecurso', 1, false);


--
-- Name: s_ae_texto_agenda; Type: SEQUENCE SET; Schema: empresa2; Owner: sae
--

SELECT pg_catalog.setval('empresa2.s_ae_texto_agenda', 1, false);


--
-- Name: s_ae_textorecurso; Type: SEQUENCE SET; Schema: empresa2; Owner: sae
--

SELECT pg_catalog.setval('empresa2.s_ae_textorecurso', 1, false);


--
-- Name: s_ae_token_reserva; Type: SEQUENCE SET; Schema: empresa2; Owner: sae
--

SELECT pg_catalog.setval('empresa2.s_ae_token_reserva', 1, false);


--
-- Name: s_ae_tramites_agendas; Type: SEQUENCE SET; Schema: empresa2; Owner: sae
--

SELECT pg_catalog.setval('empresa2.s_ae_tramites_agendas', 1, true);


--
-- Name: s_ae_valconstante; Type: SEQUENCE SET; Schema: empresa2; Owner: sae
--

SELECT pg_catalog.setval('empresa2.s_ae_valconstante', 1, false);


--
-- Name: s_ae_valdato; Type: SEQUENCE SET; Schema: empresa2; Owner: sae
--

SELECT pg_catalog.setval('empresa2.s_ae_valdato', 1, false);


--
-- Name: s_ae_validacion; Type: SEQUENCE SET; Schema: empresa2; Owner: sae
--

SELECT pg_catalog.setval('empresa2.s_ae_validacion', 1, false);


--
-- Name: s_ae_valorposible; Type: SEQUENCE SET; Schema: empresa2; Owner: sae
--

SELECT pg_catalog.setval('empresa2.s_ae_valorposible', 3, true);


--
-- Name: s_ae_valrecurso; Type: SEQUENCE SET; Schema: empresa2; Owner: sae
--

SELECT pg_catalog.setval('empresa2.s_ae_valrecurso', 1, false);


--
-- Name: s_ae_accion; Type: SEQUENCE SET; Schema: empresa3; Owner: sae
--

SELECT pg_catalog.setval('empresa3.s_ae_accion', 1, false);


--
-- Name: s_ae_acciondato; Type: SEQUENCE SET; Schema: empresa3; Owner: sae
--

SELECT pg_catalog.setval('empresa3.s_ae_acciondato', 1, false);


--
-- Name: s_ae_acciones_miperfil; Type: SEQUENCE SET; Schema: empresa3; Owner: sae
--

SELECT pg_catalog.setval('empresa3.s_ae_acciones_miperfil', 1, false);


--
-- Name: s_ae_accionrecurso; Type: SEQUENCE SET; Schema: empresa3; Owner: sae
--

SELECT pg_catalog.setval('empresa3.s_ae_accionrecurso', 1, false);


--
-- Name: s_ae_agenda; Type: SEQUENCE SET; Schema: empresa3; Owner: sae
--

SELECT pg_catalog.setval('empresa3.s_ae_agenda', 1, false);


--
-- Name: s_ae_agrupacion_dato; Type: SEQUENCE SET; Schema: empresa3; Owner: sae
--

SELECT pg_catalog.setval('empresa3.s_ae_agrupacion_dato', 1, false);


--
-- Name: s_ae_anio; Type: SEQUENCE SET; Schema: empresa3; Owner: sae
--

SELECT pg_catalog.setval('empresa3.s_ae_anio', 1, false);


--
-- Name: s_ae_atencion; Type: SEQUENCE SET; Schema: empresa3; Owner: sae
--

SELECT pg_catalog.setval('empresa3.s_ae_atencion', 1, false);


--
-- Name: s_ae_comunicaciones; Type: SEQUENCE SET; Schema: empresa3; Owner: sae
--

SELECT pg_catalog.setval('empresa3.s_ae_comunicaciones', 1, false);


--
-- Name: s_ae_constval; Type: SEQUENCE SET; Schema: empresa3; Owner: sae
--

SELECT pg_catalog.setval('empresa3.s_ae_constval', 1, false);


--
-- Name: s_ae_datoasolicitar; Type: SEQUENCE SET; Schema: empresa3; Owner: sae
--

SELECT pg_catalog.setval('empresa3.s_ae_datoasolicitar', 1, false);


--
-- Name: s_ae_datodelrecurso; Type: SEQUENCE SET; Schema: empresa3; Owner: sae
--

SELECT pg_catalog.setval('empresa3.s_ae_datodelrecurso', 1, false);


--
-- Name: s_ae_datoreserva; Type: SEQUENCE SET; Schema: empresa3; Owner: sae
--

SELECT pg_catalog.setval('empresa3.s_ae_datoreserva', 1, false);


--
-- Name: s_ae_dia_mes; Type: SEQUENCE SET; Schema: empresa3; Owner: sae
--

SELECT pg_catalog.setval('empresa3.s_ae_dia_mes', 1, false);


--
-- Name: s_ae_dia_semana; Type: SEQUENCE SET; Schema: empresa3; Owner: sae
--

SELECT pg_catalog.setval('empresa3.s_ae_dia_semana', 1, false);


--
-- Name: s_ae_disponibilidad; Type: SEQUENCE SET; Schema: empresa3; Owner: sae
--

SELECT pg_catalog.setval('empresa3.s_ae_disponibilidad', 1, false);


--
-- Name: s_ae_llamada; Type: SEQUENCE SET; Schema: empresa3; Owner: sae
--

SELECT pg_catalog.setval('empresa3.s_ae_llamada', 1, false);


--
-- Name: s_ae_mes; Type: SEQUENCE SET; Schema: empresa3; Owner: sae
--

SELECT pg_catalog.setval('empresa3.s_ae_mes', 1, false);


--
-- Name: s_ae_paramaccion; Type: SEQUENCE SET; Schema: empresa3; Owner: sae
--

SELECT pg_catalog.setval('empresa3.s_ae_paramaccion', 1, false);


--
-- Name: s_ae_parametros_autocompletar; Type: SEQUENCE SET; Schema: empresa3; Owner: sae
--

SELECT pg_catalog.setval('empresa3.s_ae_parametros_autocompletar', 1, false);


--
-- Name: s_ae_paramval; Type: SEQUENCE SET; Schema: empresa3; Owner: sae
--

SELECT pg_catalog.setval('empresa3.s_ae_paramval', 1, false);


--
-- Name: s_ae_plantilla; Type: SEQUENCE SET; Schema: empresa3; Owner: sae
--

SELECT pg_catalog.setval('empresa3.s_ae_plantilla', 1, false);


--
-- Name: s_ae_recurso; Type: SEQUENCE SET; Schema: empresa3; Owner: sae
--

SELECT pg_catalog.setval('empresa3.s_ae_recurso', 1, false);


--
-- Name: s_ae_recurso_aud; Type: SEQUENCE SET; Schema: empresa3; Owner: sae
--

SELECT pg_catalog.setval('empresa3.s_ae_recurso_aud', 1, false);


--
-- Name: s_ae_reserva; Type: SEQUENCE SET; Schema: empresa3; Owner: sae
--

SELECT pg_catalog.setval('empresa3.s_ae_reserva', 1, false);


--
-- Name: s_ae_serv_autocompletar; Type: SEQUENCE SET; Schema: empresa3; Owner: sae
--

SELECT pg_catalog.setval('empresa3.s_ae_serv_autocompletar', 1, false);


--
-- Name: s_ae_servdato; Type: SEQUENCE SET; Schema: empresa3; Owner: sae
--

SELECT pg_catalog.setval('empresa3.s_ae_servdato', 1, false);


--
-- Name: s_ae_servrecurso; Type: SEQUENCE SET; Schema: empresa3; Owner: sae
--

SELECT pg_catalog.setval('empresa3.s_ae_servrecurso', 1, false);


--
-- Name: s_ae_texto_agenda; Type: SEQUENCE SET; Schema: empresa3; Owner: sae
--

SELECT pg_catalog.setval('empresa3.s_ae_texto_agenda', 1, false);


--
-- Name: s_ae_textorecurso; Type: SEQUENCE SET; Schema: empresa3; Owner: sae
--

SELECT pg_catalog.setval('empresa3.s_ae_textorecurso', 1, false);


--
-- Name: s_ae_token_reserva; Type: SEQUENCE SET; Schema: empresa3; Owner: sae
--

SELECT pg_catalog.setval('empresa3.s_ae_token_reserva', 1, false);


--
-- Name: s_ae_tramites_agendas; Type: SEQUENCE SET; Schema: empresa3; Owner: sae
--

SELECT pg_catalog.setval('empresa3.s_ae_tramites_agendas', 1, false);


--
-- Name: s_ae_valconstante; Type: SEQUENCE SET; Schema: empresa3; Owner: sae
--

SELECT pg_catalog.setval('empresa3.s_ae_valconstante', 1, false);


--
-- Name: s_ae_valdato; Type: SEQUENCE SET; Schema: empresa3; Owner: sae
--

SELECT pg_catalog.setval('empresa3.s_ae_valdato', 1, false);


--
-- Name: s_ae_validacion; Type: SEQUENCE SET; Schema: empresa3; Owner: sae
--

SELECT pg_catalog.setval('empresa3.s_ae_validacion', 1, false);


--
-- Name: s_ae_valorposible; Type: SEQUENCE SET; Schema: empresa3; Owner: sae
--

SELECT pg_catalog.setval('empresa3.s_ae_valorposible', 1, false);


--
-- Name: s_ae_valrecurso; Type: SEQUENCE SET; Schema: empresa3; Owner: sae
--

SELECT pg_catalog.setval('empresa3.s_ae_valrecurso', 1, false);


--
-- Name: s_ae_accion; Type: SEQUENCE SET; Schema: empresa4; Owner: sae
--

SELECT pg_catalog.setval('empresa4.s_ae_accion', 1, false);


--
-- Name: s_ae_acciondato; Type: SEQUENCE SET; Schema: empresa4; Owner: sae
--

SELECT pg_catalog.setval('empresa4.s_ae_acciondato', 1, false);


--
-- Name: s_ae_acciones_miperfil; Type: SEQUENCE SET; Schema: empresa4; Owner: sae
--

SELECT pg_catalog.setval('empresa4.s_ae_acciones_miperfil', 1, false);


--
-- Name: s_ae_accionrecurso; Type: SEQUENCE SET; Schema: empresa4; Owner: sae
--

SELECT pg_catalog.setval('empresa4.s_ae_accionrecurso', 1, false);


--
-- Name: s_ae_agenda; Type: SEQUENCE SET; Schema: empresa4; Owner: sae
--

SELECT pg_catalog.setval('empresa4.s_ae_agenda', 1, false);


--
-- Name: s_ae_agrupacion_dato; Type: SEQUENCE SET; Schema: empresa4; Owner: sae
--

SELECT pg_catalog.setval('empresa4.s_ae_agrupacion_dato', 1, false);


--
-- Name: s_ae_anio; Type: SEQUENCE SET; Schema: empresa4; Owner: sae
--

SELECT pg_catalog.setval('empresa4.s_ae_anio', 1, false);


--
-- Name: s_ae_atencion; Type: SEQUENCE SET; Schema: empresa4; Owner: sae
--

SELECT pg_catalog.setval('empresa4.s_ae_atencion', 1, false);


--
-- Name: s_ae_comunicaciones; Type: SEQUENCE SET; Schema: empresa4; Owner: sae
--

SELECT pg_catalog.setval('empresa4.s_ae_comunicaciones', 1, false);


--
-- Name: s_ae_constval; Type: SEQUENCE SET; Schema: empresa4; Owner: sae
--

SELECT pg_catalog.setval('empresa4.s_ae_constval', 1, false);


--
-- Name: s_ae_datoasolicitar; Type: SEQUENCE SET; Schema: empresa4; Owner: sae
--

SELECT pg_catalog.setval('empresa4.s_ae_datoasolicitar', 1, false);


--
-- Name: s_ae_datodelrecurso; Type: SEQUENCE SET; Schema: empresa4; Owner: sae
--

SELECT pg_catalog.setval('empresa4.s_ae_datodelrecurso', 1, false);


--
-- Name: s_ae_datoreserva; Type: SEQUENCE SET; Schema: empresa4; Owner: sae
--

SELECT pg_catalog.setval('empresa4.s_ae_datoreserva', 1, false);


--
-- Name: s_ae_dia_mes; Type: SEQUENCE SET; Schema: empresa4; Owner: sae
--

SELECT pg_catalog.setval('empresa4.s_ae_dia_mes', 1, false);


--
-- Name: s_ae_dia_semana; Type: SEQUENCE SET; Schema: empresa4; Owner: sae
--

SELECT pg_catalog.setval('empresa4.s_ae_dia_semana', 1, false);


--
-- Name: s_ae_disponibilidad; Type: SEQUENCE SET; Schema: empresa4; Owner: sae
--

SELECT pg_catalog.setval('empresa4.s_ae_disponibilidad', 1, false);


--
-- Name: s_ae_llamada; Type: SEQUENCE SET; Schema: empresa4; Owner: sae
--

SELECT pg_catalog.setval('empresa4.s_ae_llamada', 1, false);


--
-- Name: s_ae_mes; Type: SEQUENCE SET; Schema: empresa4; Owner: sae
--

SELECT pg_catalog.setval('empresa4.s_ae_mes', 1, false);


--
-- Name: s_ae_paramaccion; Type: SEQUENCE SET; Schema: empresa4; Owner: sae
--

SELECT pg_catalog.setval('empresa4.s_ae_paramaccion', 1, false);


--
-- Name: s_ae_parametros_autocompletar; Type: SEQUENCE SET; Schema: empresa4; Owner: sae
--

SELECT pg_catalog.setval('empresa4.s_ae_parametros_autocompletar', 1, false);


--
-- Name: s_ae_paramval; Type: SEQUENCE SET; Schema: empresa4; Owner: sae
--

SELECT pg_catalog.setval('empresa4.s_ae_paramval', 1, false);


--
-- Name: s_ae_plantilla; Type: SEQUENCE SET; Schema: empresa4; Owner: sae
--

SELECT pg_catalog.setval('empresa4.s_ae_plantilla', 1, false);


--
-- Name: s_ae_recurso; Type: SEQUENCE SET; Schema: empresa4; Owner: sae
--

SELECT pg_catalog.setval('empresa4.s_ae_recurso', 1, false);


--
-- Name: s_ae_recurso_aud; Type: SEQUENCE SET; Schema: empresa4; Owner: sae
--

SELECT pg_catalog.setval('empresa4.s_ae_recurso_aud', 1, false);


--
-- Name: s_ae_reserva; Type: SEQUENCE SET; Schema: empresa4; Owner: sae
--

SELECT pg_catalog.setval('empresa4.s_ae_reserva', 1, false);


--
-- Name: s_ae_serv_autocompletar; Type: SEQUENCE SET; Schema: empresa4; Owner: sae
--

SELECT pg_catalog.setval('empresa4.s_ae_serv_autocompletar', 1, false);


--
-- Name: s_ae_servdato; Type: SEQUENCE SET; Schema: empresa4; Owner: sae
--

SELECT pg_catalog.setval('empresa4.s_ae_servdato', 1, false);


--
-- Name: s_ae_servrecurso; Type: SEQUENCE SET; Schema: empresa4; Owner: sae
--

SELECT pg_catalog.setval('empresa4.s_ae_servrecurso', 1, false);


--
-- Name: s_ae_texto_agenda; Type: SEQUENCE SET; Schema: empresa4; Owner: sae
--

SELECT pg_catalog.setval('empresa4.s_ae_texto_agenda', 1, false);


--
-- Name: s_ae_textorecurso; Type: SEQUENCE SET; Schema: empresa4; Owner: sae
--

SELECT pg_catalog.setval('empresa4.s_ae_textorecurso', 1, false);


--
-- Name: s_ae_token_reserva; Type: SEQUENCE SET; Schema: empresa4; Owner: sae
--

SELECT pg_catalog.setval('empresa4.s_ae_token_reserva', 1, false);


--
-- Name: s_ae_tramites_agendas; Type: SEQUENCE SET; Schema: empresa4; Owner: sae
--

SELECT pg_catalog.setval('empresa4.s_ae_tramites_agendas', 1, false);


--
-- Name: s_ae_valconstante; Type: SEQUENCE SET; Schema: empresa4; Owner: sae
--

SELECT pg_catalog.setval('empresa4.s_ae_valconstante', 1, false);


--
-- Name: s_ae_valdato; Type: SEQUENCE SET; Schema: empresa4; Owner: sae
--

SELECT pg_catalog.setval('empresa4.s_ae_valdato', 1, false);


--
-- Name: s_ae_validacion; Type: SEQUENCE SET; Schema: empresa4; Owner: sae
--

SELECT pg_catalog.setval('empresa4.s_ae_validacion', 1, false);


--
-- Name: s_ae_valorposible; Type: SEQUENCE SET; Schema: empresa4; Owner: sae
--

SELECT pg_catalog.setval('empresa4.s_ae_valorposible', 1, false);


--
-- Name: s_ae_valrecurso; Type: SEQUENCE SET; Schema: empresa4; Owner: sae
--

SELECT pg_catalog.setval('empresa4.s_ae_valrecurso', 1, false);


--
-- Name: s_ae_accion; Type: SEQUENCE SET; Schema: empresa5; Owner: sae
--

SELECT pg_catalog.setval('empresa5.s_ae_accion', 1, false);


--
-- Name: s_ae_acciondato; Type: SEQUENCE SET; Schema: empresa5; Owner: sae
--

SELECT pg_catalog.setval('empresa5.s_ae_acciondato', 1, false);


--
-- Name: s_ae_acciones_miperfil; Type: SEQUENCE SET; Schema: empresa5; Owner: sae
--

SELECT pg_catalog.setval('empresa5.s_ae_acciones_miperfil', 1, false);


--
-- Name: s_ae_accionrecurso; Type: SEQUENCE SET; Schema: empresa5; Owner: sae
--

SELECT pg_catalog.setval('empresa5.s_ae_accionrecurso', 1, false);


--
-- Name: s_ae_agenda; Type: SEQUENCE SET; Schema: empresa5; Owner: sae
--

SELECT pg_catalog.setval('empresa5.s_ae_agenda', 1, false);


--
-- Name: s_ae_agrupacion_dato; Type: SEQUENCE SET; Schema: empresa5; Owner: sae
--

SELECT pg_catalog.setval('empresa5.s_ae_agrupacion_dato', 1, false);


--
-- Name: s_ae_anio; Type: SEQUENCE SET; Schema: empresa5; Owner: sae
--

SELECT pg_catalog.setval('empresa5.s_ae_anio', 1, false);


--
-- Name: s_ae_atencion; Type: SEQUENCE SET; Schema: empresa5; Owner: sae
--

SELECT pg_catalog.setval('empresa5.s_ae_atencion', 1, false);


--
-- Name: s_ae_comunicaciones; Type: SEQUENCE SET; Schema: empresa5; Owner: sae
--

SELECT pg_catalog.setval('empresa5.s_ae_comunicaciones', 1, false);


--
-- Name: s_ae_constval; Type: SEQUENCE SET; Schema: empresa5; Owner: sae
--

SELECT pg_catalog.setval('empresa5.s_ae_constval', 1, false);


--
-- Name: s_ae_datoasolicitar; Type: SEQUENCE SET; Schema: empresa5; Owner: sae
--

SELECT pg_catalog.setval('empresa5.s_ae_datoasolicitar', 1, false);


--
-- Name: s_ae_datodelrecurso; Type: SEQUENCE SET; Schema: empresa5; Owner: sae
--

SELECT pg_catalog.setval('empresa5.s_ae_datodelrecurso', 1, false);


--
-- Name: s_ae_datoreserva; Type: SEQUENCE SET; Schema: empresa5; Owner: sae
--

SELECT pg_catalog.setval('empresa5.s_ae_datoreserva', 1, false);


--
-- Name: s_ae_dia_mes; Type: SEQUENCE SET; Schema: empresa5; Owner: sae
--

SELECT pg_catalog.setval('empresa5.s_ae_dia_mes', 1, false);


--
-- Name: s_ae_dia_semana; Type: SEQUENCE SET; Schema: empresa5; Owner: sae
--

SELECT pg_catalog.setval('empresa5.s_ae_dia_semana', 1, false);


--
-- Name: s_ae_disponibilidad; Type: SEQUENCE SET; Schema: empresa5; Owner: sae
--

SELECT pg_catalog.setval('empresa5.s_ae_disponibilidad', 1, false);


--
-- Name: s_ae_llamada; Type: SEQUENCE SET; Schema: empresa5; Owner: sae
--

SELECT pg_catalog.setval('empresa5.s_ae_llamada', 1, false);


--
-- Name: s_ae_mes; Type: SEQUENCE SET; Schema: empresa5; Owner: sae
--

SELECT pg_catalog.setval('empresa5.s_ae_mes', 1, false);


--
-- Name: s_ae_paramaccion; Type: SEQUENCE SET; Schema: empresa5; Owner: sae
--

SELECT pg_catalog.setval('empresa5.s_ae_paramaccion', 1, false);


--
-- Name: s_ae_parametros_autocompletar; Type: SEQUENCE SET; Schema: empresa5; Owner: sae
--

SELECT pg_catalog.setval('empresa5.s_ae_parametros_autocompletar', 1, false);


--
-- Name: s_ae_paramval; Type: SEQUENCE SET; Schema: empresa5; Owner: sae
--

SELECT pg_catalog.setval('empresa5.s_ae_paramval', 1, false);


--
-- Name: s_ae_plantilla; Type: SEQUENCE SET; Schema: empresa5; Owner: sae
--

SELECT pg_catalog.setval('empresa5.s_ae_plantilla', 1, false);


--
-- Name: s_ae_recurso; Type: SEQUENCE SET; Schema: empresa5; Owner: sae
--

SELECT pg_catalog.setval('empresa5.s_ae_recurso', 1, false);


--
-- Name: s_ae_recurso_aud; Type: SEQUENCE SET; Schema: empresa5; Owner: sae
--

SELECT pg_catalog.setval('empresa5.s_ae_recurso_aud', 1, false);


--
-- Name: s_ae_reserva; Type: SEQUENCE SET; Schema: empresa5; Owner: sae
--

SELECT pg_catalog.setval('empresa5.s_ae_reserva', 1, false);


--
-- Name: s_ae_serv_autocompletar; Type: SEQUENCE SET; Schema: empresa5; Owner: sae
--

SELECT pg_catalog.setval('empresa5.s_ae_serv_autocompletar', 1, false);


--
-- Name: s_ae_servdato; Type: SEQUENCE SET; Schema: empresa5; Owner: sae
--

SELECT pg_catalog.setval('empresa5.s_ae_servdato', 1, false);


--
-- Name: s_ae_servrecurso; Type: SEQUENCE SET; Schema: empresa5; Owner: sae
--

SELECT pg_catalog.setval('empresa5.s_ae_servrecurso', 1, false);


--
-- Name: s_ae_texto_agenda; Type: SEQUENCE SET; Schema: empresa5; Owner: sae
--

SELECT pg_catalog.setval('empresa5.s_ae_texto_agenda', 1, false);


--
-- Name: s_ae_textorecurso; Type: SEQUENCE SET; Schema: empresa5; Owner: sae
--

SELECT pg_catalog.setval('empresa5.s_ae_textorecurso', 1, false);


--
-- Name: s_ae_token_reserva; Type: SEQUENCE SET; Schema: empresa5; Owner: sae
--

SELECT pg_catalog.setval('empresa5.s_ae_token_reserva', 1, false);


--
-- Name: s_ae_tramites_agendas; Type: SEQUENCE SET; Schema: empresa5; Owner: sae
--

SELECT pg_catalog.setval('empresa5.s_ae_tramites_agendas', 1, false);


--
-- Name: s_ae_valconstante; Type: SEQUENCE SET; Schema: empresa5; Owner: sae
--

SELECT pg_catalog.setval('empresa5.s_ae_valconstante', 1, false);


--
-- Name: s_ae_valdato; Type: SEQUENCE SET; Schema: empresa5; Owner: sae
--

SELECT pg_catalog.setval('empresa5.s_ae_valdato', 1, false);


--
-- Name: s_ae_validacion; Type: SEQUENCE SET; Schema: empresa5; Owner: sae
--

SELECT pg_catalog.setval('empresa5.s_ae_validacion', 1, false);


--
-- Name: s_ae_valorposible; Type: SEQUENCE SET; Schema: empresa5; Owner: sae
--

SELECT pg_catalog.setval('empresa5.s_ae_valorposible', 1, false);


--
-- Name: s_ae_valrecurso; Type: SEQUENCE SET; Schema: empresa5; Owner: sae
--

SELECT pg_catalog.setval('empresa5.s_ae_valrecurso', 1, false);


--
-- Name: s_ae_configuracion; Type: SEQUENCE SET; Schema: global; Owner: sae
--

SELECT pg_catalog.setval('global.s_ae_configuracion', 32, true);


--
-- Name: s_ae_empresa; Type: SEQUENCE SET; Schema: global; Owner: sae
--

SELECT pg_catalog.setval('global.s_ae_empresa', 2, true);


--
-- Name: s_ae_novedades; Type: SEQUENCE SET; Schema: global; Owner: sae
--

SELECT pg_catalog.setval('global.s_ae_novedades', 1, false);


--
-- Name: s_ae_trazabilidad; Type: SEQUENCE SET; Schema: global; Owner: sae
--

SELECT pg_catalog.setval('global.s_ae_trazabilidad', 1, false);


--
-- Name: s_ae_usuario; Type: SEQUENCE SET; Schema: global; Owner: sae
--

SELECT pg_catalog.setval('global.s_ae_usuario', 3, true);


--
-- Name: ae_acciones_miperfil_recurso ae_acciones_miperfil_recurso_pkey; Type: CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_acciones_miperfil_recurso
    ADD CONSTRAINT ae_acciones_miperfil_recurso_pkey PRIMARY KEY (id);


--
-- Name: ae_acciones ae_acciones_pkey; Type: CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_acciones
    ADD CONSTRAINT ae_acciones_pkey PRIMARY KEY (id);


--
-- Name: ae_acciones_por_dato ae_acciones_por_dato_pkey; Type: CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_acciones_por_dato
    ADD CONSTRAINT ae_acciones_por_dato_pkey PRIMARY KEY (id);


--
-- Name: ae_acciones_por_recurso ae_acciones_por_recurso_pkey; Type: CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_acciones_por_recurso
    ADD CONSTRAINT ae_acciones_por_recurso_pkey PRIMARY KEY (id);


--
-- Name: ae_agendas ae_agendas_pkey; Type: CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_agendas
    ADD CONSTRAINT ae_agendas_pkey PRIMARY KEY (id);


--
-- Name: ae_agrupaciones_datos ae_agrupaciones_datos_pkey; Type: CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_agrupaciones_datos
    ADD CONSTRAINT ae_agrupaciones_datos_pkey PRIMARY KEY (id);


--
-- Name: ae_anios ae_anios_pkey; Type: CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_anios
    ADD CONSTRAINT ae_anios_pkey PRIMARY KEY (id);


--
-- Name: ae_atencion ae_atencion_pkey; Type: CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_atencion
    ADD CONSTRAINT ae_atencion_pkey PRIMARY KEY (id);


--
-- Name: ae_frases_captcha ae_captchas_pk; Type: CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_frases_captcha
    ADD CONSTRAINT ae_captchas_pk PRIMARY KEY (clave);


--
-- Name: ae_comunicaciones ae_comunicaciones_pkey; Type: CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_comunicaciones
    ADD CONSTRAINT ae_comunicaciones_pkey PRIMARY KEY (id);


--
-- Name: ae_constante_validacion ae_constante_validacion_pkey; Type: CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_constante_validacion
    ADD CONSTRAINT ae_constante_validacion_pkey PRIMARY KEY (id);


--
-- Name: ae_datos_a_solicitar ae_datos_a_solicitar_pkey; Type: CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_datos_a_solicitar
    ADD CONSTRAINT ae_datos_a_solicitar_pkey PRIMARY KEY (id);


--
-- Name: ae_datos_del_recurso ae_datos_del_recurso_pkey; Type: CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_datos_del_recurso
    ADD CONSTRAINT ae_datos_del_recurso_pkey PRIMARY KEY (id);


--
-- Name: ae_datos_reserva ae_datos_reserva_pkey; Type: CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_datos_reserva
    ADD CONSTRAINT ae_datos_reserva_pkey PRIMARY KEY (id);


--
-- Name: ae_dias_del_mes ae_dias_del_mes_pkey; Type: CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_dias_del_mes
    ADD CONSTRAINT ae_dias_del_mes_pkey PRIMARY KEY (id);


--
-- Name: ae_dias_semana ae_dias_semana_pkey; Type: CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_dias_semana
    ADD CONSTRAINT ae_dias_semana_pkey PRIMARY KEY (id);


--
-- Name: ae_disponibilidades ae_disponibilidades_pkey; Type: CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_disponibilidades
    ADD CONSTRAINT ae_disponibilidades_pkey PRIMARY KEY (id);


--
-- Name: ae_llamadas ae_llamadas_aers_id_key; Type: CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_llamadas
    ADD CONSTRAINT ae_llamadas_aers_id_key UNIQUE (aers_id);


--
-- Name: ae_llamadas ae_llamadas_pkey; Type: CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_llamadas
    ADD CONSTRAINT ae_llamadas_pkey PRIMARY KEY (id);


--
-- Name: ae_meses ae_meses_pkey; Type: CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_meses
    ADD CONSTRAINT ae_meses_pkey PRIMARY KEY (id);


--
-- Name: ae_parametros_accion ae_parametros_accion_pkey; Type: CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_parametros_accion
    ADD CONSTRAINT ae_parametros_accion_pkey PRIMARY KEY (id);


--
-- Name: ae_parametros_autocompletar ae_parametros_autocompletar_pkey; Type: CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_parametros_autocompletar
    ADD CONSTRAINT ae_parametros_autocompletar_pkey PRIMARY KEY (id);


--
-- Name: ae_parametros_validacion ae_parametros_validacion_pkey; Type: CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_parametros_validacion
    ADD CONSTRAINT ae_parametros_validacion_pkey PRIMARY KEY (id);


--
-- Name: ae_plantillas ae_plantillas_pkey; Type: CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_plantillas
    ADD CONSTRAINT ae_plantillas_pkey PRIMARY KEY (id);


--
-- Name: ae_preguntas_captcha ae_preguntas_pk; Type: CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_preguntas_captcha
    ADD CONSTRAINT ae_preguntas_pk PRIMARY KEY (clave);


--
-- Name: ae_recursos_aud ae_recursos_aud_pkey; Type: CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_recursos_aud
    ADD CONSTRAINT ae_recursos_aud_pkey PRIMARY KEY (id);


--
-- Name: ae_recursos ae_recursos_pkey; Type: CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_recursos
    ADD CONSTRAINT ae_recursos_pkey PRIMARY KEY (id);


--
-- Name: ae_reservas ae_reservas_pkey; Type: CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_reservas
    ADD CONSTRAINT ae_reservas_pkey PRIMARY KEY (id);


--
-- Name: ae_roles_usuario_recurso ae_roles_usuario_recurso_pkey; Type: CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_roles_usuario_recurso
    ADD CONSTRAINT ae_roles_usuario_recurso_pkey PRIMARY KEY (usuario_id, recurso_id);


--
-- Name: ae_serv_autocomp_por_dato ae_serv_autocomp_por_dato_pkey; Type: CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_serv_autocomp_por_dato
    ADD CONSTRAINT ae_serv_autocomp_por_dato_pkey PRIMARY KEY (id);


--
-- Name: ae_serv_autocompletar ae_serv_autocompletar_pkey; Type: CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_serv_autocompletar
    ADD CONSTRAINT ae_serv_autocompletar_pkey PRIMARY KEY (id);


--
-- Name: ae_servicio_por_recurso ae_servicio_por_recurso_pkey; Type: CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_servicio_por_recurso
    ADD CONSTRAINT ae_servicio_por_recurso_pkey PRIMARY KEY (id);


--
-- Name: ae_textos_agenda ae_textos_agenda_pkey; Type: CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_textos_agenda
    ADD CONSTRAINT ae_textos_agenda_pkey PRIMARY KEY (id);


--
-- Name: ae_textos ae_textos_pk; Type: CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_textos
    ADD CONSTRAINT ae_textos_pk PRIMARY KEY (codigo, idioma);


--
-- Name: ae_textos_recurso ae_textos_recurso_pkey; Type: CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_textos_recurso
    ADD CONSTRAINT ae_textos_recurso_pkey PRIMARY KEY (id);


--
-- Name: ae_tokens_reservas ae_tokens_reservas_pkey; Type: CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_tokens_reservas
    ADD CONSTRAINT ae_tokens_reservas_pkey PRIMARY KEY (id);


--
-- Name: ae_tramites_agendas ae_tramites_agenda_pkey; Type: CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_tramites_agendas
    ADD CONSTRAINT ae_tramites_agenda_pkey PRIMARY KEY (id);


--
-- Name: ae_validaciones ae_validaciones_pkey; Type: CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_validaciones
    ADD CONSTRAINT ae_validaciones_pkey PRIMARY KEY (id);


--
-- Name: ae_validaciones_por_dato ae_validaciones_por_dato_pkey; Type: CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_validaciones_por_dato
    ADD CONSTRAINT ae_validaciones_por_dato_pkey PRIMARY KEY (id);


--
-- Name: ae_validaciones_por_recurso ae_validaciones_por_recurso_pkey; Type: CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_validaciones_por_recurso
    ADD CONSTRAINT ae_validaciones_por_recurso_pkey PRIMARY KEY (id);


--
-- Name: ae_valor_constante_val_rec ae_valor_constante_val_rec_pkey; Type: CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_valor_constante_val_rec
    ADD CONSTRAINT ae_valor_constante_val_rec_pkey PRIMARY KEY (id);


--
-- Name: ae_valores_del_dato ae_valores_del_dato_pkey; Type: CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_valores_del_dato
    ADD CONSTRAINT ae_valores_del_dato_pkey PRIMARY KEY (id);


--
-- Name: ae_acciones_miperfil_recurso ae_acciones_miperfil_recurso_pkey; Type: CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_acciones_miperfil_recurso
    ADD CONSTRAINT ae_acciones_miperfil_recurso_pkey PRIMARY KEY (id);


--
-- Name: ae_acciones ae_acciones_pkey; Type: CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_acciones
    ADD CONSTRAINT ae_acciones_pkey PRIMARY KEY (id);


--
-- Name: ae_acciones_por_dato ae_acciones_por_dato_pkey; Type: CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_acciones_por_dato
    ADD CONSTRAINT ae_acciones_por_dato_pkey PRIMARY KEY (id);


--
-- Name: ae_acciones_por_recurso ae_acciones_por_recurso_pkey; Type: CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_acciones_por_recurso
    ADD CONSTRAINT ae_acciones_por_recurso_pkey PRIMARY KEY (id);


--
-- Name: ae_agendas ae_agendas_pkey; Type: CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_agendas
    ADD CONSTRAINT ae_agendas_pkey PRIMARY KEY (id);


--
-- Name: ae_agrupaciones_datos ae_agrupaciones_datos_pkey; Type: CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_agrupaciones_datos
    ADD CONSTRAINT ae_agrupaciones_datos_pkey PRIMARY KEY (id);


--
-- Name: ae_anios ae_anios_pkey; Type: CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_anios
    ADD CONSTRAINT ae_anios_pkey PRIMARY KEY (id);


--
-- Name: ae_atencion ae_atencion_pkey; Type: CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_atencion
    ADD CONSTRAINT ae_atencion_pkey PRIMARY KEY (id);


--
-- Name: ae_frases_captcha ae_captchas_pk; Type: CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_frases_captcha
    ADD CONSTRAINT ae_captchas_pk PRIMARY KEY (clave);


--
-- Name: ae_comunicaciones ae_comunicaciones_pkey; Type: CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_comunicaciones
    ADD CONSTRAINT ae_comunicaciones_pkey PRIMARY KEY (id);


--
-- Name: ae_constante_validacion ae_constante_validacion_pkey; Type: CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_constante_validacion
    ADD CONSTRAINT ae_constante_validacion_pkey PRIMARY KEY (id);


--
-- Name: ae_datos_a_solicitar ae_datos_a_solicitar_pkey; Type: CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_datos_a_solicitar
    ADD CONSTRAINT ae_datos_a_solicitar_pkey PRIMARY KEY (id);


--
-- Name: ae_datos_del_recurso ae_datos_del_recurso_pkey; Type: CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_datos_del_recurso
    ADD CONSTRAINT ae_datos_del_recurso_pkey PRIMARY KEY (id);


--
-- Name: ae_datos_reserva ae_datos_reserva_pkey; Type: CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_datos_reserva
    ADD CONSTRAINT ae_datos_reserva_pkey PRIMARY KEY (id);


--
-- Name: ae_dias_del_mes ae_dias_del_mes_pkey; Type: CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_dias_del_mes
    ADD CONSTRAINT ae_dias_del_mes_pkey PRIMARY KEY (id);


--
-- Name: ae_dias_semana ae_dias_semana_pkey; Type: CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_dias_semana
    ADD CONSTRAINT ae_dias_semana_pkey PRIMARY KEY (id);


--
-- Name: ae_disponibilidades ae_disponibilidades_pkey; Type: CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_disponibilidades
    ADD CONSTRAINT ae_disponibilidades_pkey PRIMARY KEY (id);


--
-- Name: ae_llamadas ae_llamadas_aers_id_key; Type: CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_llamadas
    ADD CONSTRAINT ae_llamadas_aers_id_key UNIQUE (aers_id);


--
-- Name: ae_llamadas ae_llamadas_pkey; Type: CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_llamadas
    ADD CONSTRAINT ae_llamadas_pkey PRIMARY KEY (id);


--
-- Name: ae_meses ae_meses_pkey; Type: CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_meses
    ADD CONSTRAINT ae_meses_pkey PRIMARY KEY (id);


--
-- Name: ae_parametros_accion ae_parametros_accion_pkey; Type: CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_parametros_accion
    ADD CONSTRAINT ae_parametros_accion_pkey PRIMARY KEY (id);


--
-- Name: ae_parametros_autocompletar ae_parametros_autocompletar_pkey; Type: CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_parametros_autocompletar
    ADD CONSTRAINT ae_parametros_autocompletar_pkey PRIMARY KEY (id);


--
-- Name: ae_parametros_validacion ae_parametros_validacion_pkey; Type: CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_parametros_validacion
    ADD CONSTRAINT ae_parametros_validacion_pkey PRIMARY KEY (id);


--
-- Name: ae_plantillas ae_plantillas_pkey; Type: CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_plantillas
    ADD CONSTRAINT ae_plantillas_pkey PRIMARY KEY (id);


--
-- Name: ae_preguntas_captcha ae_preguntas_pk; Type: CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_preguntas_captcha
    ADD CONSTRAINT ae_preguntas_pk PRIMARY KEY (clave);


--
-- Name: ae_recursos_aud ae_recursos_aud_pkey; Type: CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_recursos_aud
    ADD CONSTRAINT ae_recursos_aud_pkey PRIMARY KEY (id);


--
-- Name: ae_recursos ae_recursos_pkey; Type: CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_recursos
    ADD CONSTRAINT ae_recursos_pkey PRIMARY KEY (id);


--
-- Name: ae_reservas ae_reservas_pkey; Type: CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_reservas
    ADD CONSTRAINT ae_reservas_pkey PRIMARY KEY (id);


--
-- Name: ae_roles_usuario_recurso ae_roles_usuario_recurso_pkey; Type: CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_roles_usuario_recurso
    ADD CONSTRAINT ae_roles_usuario_recurso_pkey PRIMARY KEY (usuario_id, recurso_id);


--
-- Name: ae_serv_autocomp_por_dato ae_serv_autocomp_por_dato_pkey; Type: CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_serv_autocomp_por_dato
    ADD CONSTRAINT ae_serv_autocomp_por_dato_pkey PRIMARY KEY (id);


--
-- Name: ae_serv_autocompletar ae_serv_autocompletar_pkey; Type: CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_serv_autocompletar
    ADD CONSTRAINT ae_serv_autocompletar_pkey PRIMARY KEY (id);


--
-- Name: ae_servicio_por_recurso ae_servicio_por_recurso_pkey; Type: CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_servicio_por_recurso
    ADD CONSTRAINT ae_servicio_por_recurso_pkey PRIMARY KEY (id);


--
-- Name: ae_textos_agenda ae_textos_agenda_pkey; Type: CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_textos_agenda
    ADD CONSTRAINT ae_textos_agenda_pkey PRIMARY KEY (id);


--
-- Name: ae_textos ae_textos_pk; Type: CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_textos
    ADD CONSTRAINT ae_textos_pk PRIMARY KEY (codigo, idioma);


--
-- Name: ae_textos_recurso ae_textos_recurso_pkey; Type: CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_textos_recurso
    ADD CONSTRAINT ae_textos_recurso_pkey PRIMARY KEY (id);


--
-- Name: ae_tokens_reservas ae_tokens_reservas_pkey; Type: CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_tokens_reservas
    ADD CONSTRAINT ae_tokens_reservas_pkey PRIMARY KEY (id);


--
-- Name: ae_tramites_agendas ae_tramites_agenda_pkey; Type: CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_tramites_agendas
    ADD CONSTRAINT ae_tramites_agenda_pkey PRIMARY KEY (id);


--
-- Name: ae_validaciones ae_validaciones_pkey; Type: CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_validaciones
    ADD CONSTRAINT ae_validaciones_pkey PRIMARY KEY (id);


--
-- Name: ae_validaciones_por_dato ae_validaciones_por_dato_pkey; Type: CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_validaciones_por_dato
    ADD CONSTRAINT ae_validaciones_por_dato_pkey PRIMARY KEY (id);


--
-- Name: ae_validaciones_por_recurso ae_validaciones_por_recurso_pkey; Type: CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_validaciones_por_recurso
    ADD CONSTRAINT ae_validaciones_por_recurso_pkey PRIMARY KEY (id);


--
-- Name: ae_valor_constante_val_rec ae_valor_constante_val_rec_pkey; Type: CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_valor_constante_val_rec
    ADD CONSTRAINT ae_valor_constante_val_rec_pkey PRIMARY KEY (id);


--
-- Name: ae_valores_del_dato ae_valores_del_dato_pkey; Type: CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_valores_del_dato
    ADD CONSTRAINT ae_valores_del_dato_pkey PRIMARY KEY (id);


--
-- Name: ae_acciones_miperfil_recurso ae_acciones_miperfil_recurso_pkey; Type: CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_acciones_miperfil_recurso
    ADD CONSTRAINT ae_acciones_miperfil_recurso_pkey PRIMARY KEY (id);


--
-- Name: ae_acciones ae_acciones_pkey; Type: CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_acciones
    ADD CONSTRAINT ae_acciones_pkey PRIMARY KEY (id);


--
-- Name: ae_acciones_por_dato ae_acciones_por_dato_pkey; Type: CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_acciones_por_dato
    ADD CONSTRAINT ae_acciones_por_dato_pkey PRIMARY KEY (id);


--
-- Name: ae_acciones_por_recurso ae_acciones_por_recurso_pkey; Type: CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_acciones_por_recurso
    ADD CONSTRAINT ae_acciones_por_recurso_pkey PRIMARY KEY (id);


--
-- Name: ae_agendas ae_agendas_pkey; Type: CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_agendas
    ADD CONSTRAINT ae_agendas_pkey PRIMARY KEY (id);


--
-- Name: ae_agrupaciones_datos ae_agrupaciones_datos_pkey; Type: CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_agrupaciones_datos
    ADD CONSTRAINT ae_agrupaciones_datos_pkey PRIMARY KEY (id);


--
-- Name: ae_anios ae_anios_pkey; Type: CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_anios
    ADD CONSTRAINT ae_anios_pkey PRIMARY KEY (id);


--
-- Name: ae_atencion ae_atencion_pkey; Type: CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_atencion
    ADD CONSTRAINT ae_atencion_pkey PRIMARY KEY (id);


--
-- Name: ae_frases_captcha ae_captchas_pk; Type: CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_frases_captcha
    ADD CONSTRAINT ae_captchas_pk PRIMARY KEY (clave);


--
-- Name: ae_comunicaciones ae_comunicaciones_pkey; Type: CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_comunicaciones
    ADD CONSTRAINT ae_comunicaciones_pkey PRIMARY KEY (id);


--
-- Name: ae_constante_validacion ae_constante_validacion_pkey; Type: CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_constante_validacion
    ADD CONSTRAINT ae_constante_validacion_pkey PRIMARY KEY (id);


--
-- Name: ae_datos_a_solicitar ae_datos_a_solicitar_pkey; Type: CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_datos_a_solicitar
    ADD CONSTRAINT ae_datos_a_solicitar_pkey PRIMARY KEY (id);


--
-- Name: ae_datos_del_recurso ae_datos_del_recurso_pkey; Type: CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_datos_del_recurso
    ADD CONSTRAINT ae_datos_del_recurso_pkey PRIMARY KEY (id);


--
-- Name: ae_datos_reserva ae_datos_reserva_pkey; Type: CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_datos_reserva
    ADD CONSTRAINT ae_datos_reserva_pkey PRIMARY KEY (id);


--
-- Name: ae_dias_del_mes ae_dias_del_mes_pkey; Type: CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_dias_del_mes
    ADD CONSTRAINT ae_dias_del_mes_pkey PRIMARY KEY (id);


--
-- Name: ae_dias_semana ae_dias_semana_pkey; Type: CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_dias_semana
    ADD CONSTRAINT ae_dias_semana_pkey PRIMARY KEY (id);


--
-- Name: ae_disponibilidades ae_disponibilidades_pkey; Type: CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_disponibilidades
    ADD CONSTRAINT ae_disponibilidades_pkey PRIMARY KEY (id);


--
-- Name: ae_llamadas ae_llamadas_aers_id_key; Type: CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_llamadas
    ADD CONSTRAINT ae_llamadas_aers_id_key UNIQUE (aers_id);


--
-- Name: ae_llamadas ae_llamadas_pkey; Type: CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_llamadas
    ADD CONSTRAINT ae_llamadas_pkey PRIMARY KEY (id);


--
-- Name: ae_meses ae_meses_pkey; Type: CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_meses
    ADD CONSTRAINT ae_meses_pkey PRIMARY KEY (id);


--
-- Name: ae_parametros_accion ae_parametros_accion_pkey; Type: CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_parametros_accion
    ADD CONSTRAINT ae_parametros_accion_pkey PRIMARY KEY (id);


--
-- Name: ae_parametros_autocompletar ae_parametros_autocompletar_pkey; Type: CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_parametros_autocompletar
    ADD CONSTRAINT ae_parametros_autocompletar_pkey PRIMARY KEY (id);


--
-- Name: ae_parametros_validacion ae_parametros_validacion_pkey; Type: CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_parametros_validacion
    ADD CONSTRAINT ae_parametros_validacion_pkey PRIMARY KEY (id);


--
-- Name: ae_plantillas ae_plantillas_pkey; Type: CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_plantillas
    ADD CONSTRAINT ae_plantillas_pkey PRIMARY KEY (id);


--
-- Name: ae_preguntas_captcha ae_preguntas_pk; Type: CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_preguntas_captcha
    ADD CONSTRAINT ae_preguntas_pk PRIMARY KEY (clave);


--
-- Name: ae_recursos_aud ae_recursos_aud_pkey; Type: CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_recursos_aud
    ADD CONSTRAINT ae_recursos_aud_pkey PRIMARY KEY (id);


--
-- Name: ae_recursos ae_recursos_pkey; Type: CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_recursos
    ADD CONSTRAINT ae_recursos_pkey PRIMARY KEY (id);


--
-- Name: ae_reservas ae_reservas_pkey; Type: CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_reservas
    ADD CONSTRAINT ae_reservas_pkey PRIMARY KEY (id);


--
-- Name: ae_roles_usuario_recurso ae_roles_usuario_recurso_pkey; Type: CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_roles_usuario_recurso
    ADD CONSTRAINT ae_roles_usuario_recurso_pkey PRIMARY KEY (usuario_id, recurso_id);


--
-- Name: ae_serv_autocomp_por_dato ae_serv_autocomp_por_dato_pkey; Type: CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_serv_autocomp_por_dato
    ADD CONSTRAINT ae_serv_autocomp_por_dato_pkey PRIMARY KEY (id);


--
-- Name: ae_serv_autocompletar ae_serv_autocompletar_pkey; Type: CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_serv_autocompletar
    ADD CONSTRAINT ae_serv_autocompletar_pkey PRIMARY KEY (id);


--
-- Name: ae_servicio_por_recurso ae_servicio_por_recurso_pkey; Type: CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_servicio_por_recurso
    ADD CONSTRAINT ae_servicio_por_recurso_pkey PRIMARY KEY (id);


--
-- Name: ae_textos_agenda ae_textos_agenda_pkey; Type: CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_textos_agenda
    ADD CONSTRAINT ae_textos_agenda_pkey PRIMARY KEY (id);


--
-- Name: ae_textos ae_textos_pk; Type: CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_textos
    ADD CONSTRAINT ae_textos_pk PRIMARY KEY (codigo, idioma);


--
-- Name: ae_textos_recurso ae_textos_recurso_pkey; Type: CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_textos_recurso
    ADD CONSTRAINT ae_textos_recurso_pkey PRIMARY KEY (id);


--
-- Name: ae_tokens_reservas ae_tokens_reservas_pkey; Type: CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_tokens_reservas
    ADD CONSTRAINT ae_tokens_reservas_pkey PRIMARY KEY (id);


--
-- Name: ae_tramites_agendas ae_tramites_agenda_pkey; Type: CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_tramites_agendas
    ADD CONSTRAINT ae_tramites_agenda_pkey PRIMARY KEY (id);


--
-- Name: ae_validaciones ae_validaciones_pkey; Type: CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_validaciones
    ADD CONSTRAINT ae_validaciones_pkey PRIMARY KEY (id);


--
-- Name: ae_validaciones_por_dato ae_validaciones_por_dato_pkey; Type: CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_validaciones_por_dato
    ADD CONSTRAINT ae_validaciones_por_dato_pkey PRIMARY KEY (id);


--
-- Name: ae_validaciones_por_recurso ae_validaciones_por_recurso_pkey; Type: CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_validaciones_por_recurso
    ADD CONSTRAINT ae_validaciones_por_recurso_pkey PRIMARY KEY (id);


--
-- Name: ae_valor_constante_val_rec ae_valor_constante_val_rec_pkey; Type: CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_valor_constante_val_rec
    ADD CONSTRAINT ae_valor_constante_val_rec_pkey PRIMARY KEY (id);


--
-- Name: ae_valores_del_dato ae_valores_del_dato_pkey; Type: CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_valores_del_dato
    ADD CONSTRAINT ae_valores_del_dato_pkey PRIMARY KEY (id);


--
-- Name: ae_acciones_miperfil_recurso ae_acciones_miperfil_recurso_pkey; Type: CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_acciones_miperfil_recurso
    ADD CONSTRAINT ae_acciones_miperfil_recurso_pkey PRIMARY KEY (id);


--
-- Name: ae_acciones ae_acciones_pkey; Type: CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_acciones
    ADD CONSTRAINT ae_acciones_pkey PRIMARY KEY (id);


--
-- Name: ae_acciones_por_dato ae_acciones_por_dato_pkey; Type: CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_acciones_por_dato
    ADD CONSTRAINT ae_acciones_por_dato_pkey PRIMARY KEY (id);


--
-- Name: ae_acciones_por_recurso ae_acciones_por_recurso_pkey; Type: CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_acciones_por_recurso
    ADD CONSTRAINT ae_acciones_por_recurso_pkey PRIMARY KEY (id);


--
-- Name: ae_agendas ae_agendas_pkey; Type: CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_agendas
    ADD CONSTRAINT ae_agendas_pkey PRIMARY KEY (id);


--
-- Name: ae_agrupaciones_datos ae_agrupaciones_datos_pkey; Type: CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_agrupaciones_datos
    ADD CONSTRAINT ae_agrupaciones_datos_pkey PRIMARY KEY (id);


--
-- Name: ae_anios ae_anios_pkey; Type: CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_anios
    ADD CONSTRAINT ae_anios_pkey PRIMARY KEY (id);


--
-- Name: ae_atencion ae_atencion_pkey; Type: CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_atencion
    ADD CONSTRAINT ae_atencion_pkey PRIMARY KEY (id);


--
-- Name: ae_frases_captcha ae_captchas_pk; Type: CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_frases_captcha
    ADD CONSTRAINT ae_captchas_pk PRIMARY KEY (clave);


--
-- Name: ae_comunicaciones ae_comunicaciones_pkey; Type: CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_comunicaciones
    ADD CONSTRAINT ae_comunicaciones_pkey PRIMARY KEY (id);


--
-- Name: ae_constante_validacion ae_constante_validacion_pkey; Type: CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_constante_validacion
    ADD CONSTRAINT ae_constante_validacion_pkey PRIMARY KEY (id);


--
-- Name: ae_datos_a_solicitar ae_datos_a_solicitar_pkey; Type: CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_datos_a_solicitar
    ADD CONSTRAINT ae_datos_a_solicitar_pkey PRIMARY KEY (id);


--
-- Name: ae_datos_del_recurso ae_datos_del_recurso_pkey; Type: CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_datos_del_recurso
    ADD CONSTRAINT ae_datos_del_recurso_pkey PRIMARY KEY (id);


--
-- Name: ae_datos_reserva ae_datos_reserva_pkey; Type: CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_datos_reserva
    ADD CONSTRAINT ae_datos_reserva_pkey PRIMARY KEY (id);


--
-- Name: ae_dias_del_mes ae_dias_del_mes_pkey; Type: CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_dias_del_mes
    ADD CONSTRAINT ae_dias_del_mes_pkey PRIMARY KEY (id);


--
-- Name: ae_dias_semana ae_dias_semana_pkey; Type: CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_dias_semana
    ADD CONSTRAINT ae_dias_semana_pkey PRIMARY KEY (id);


--
-- Name: ae_disponibilidades ae_disponibilidades_pkey; Type: CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_disponibilidades
    ADD CONSTRAINT ae_disponibilidades_pkey PRIMARY KEY (id);


--
-- Name: ae_llamadas ae_llamadas_aers_id_key; Type: CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_llamadas
    ADD CONSTRAINT ae_llamadas_aers_id_key UNIQUE (aers_id);


--
-- Name: ae_llamadas ae_llamadas_pkey; Type: CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_llamadas
    ADD CONSTRAINT ae_llamadas_pkey PRIMARY KEY (id);


--
-- Name: ae_meses ae_meses_pkey; Type: CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_meses
    ADD CONSTRAINT ae_meses_pkey PRIMARY KEY (id);


--
-- Name: ae_parametros_accion ae_parametros_accion_pkey; Type: CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_parametros_accion
    ADD CONSTRAINT ae_parametros_accion_pkey PRIMARY KEY (id);


--
-- Name: ae_parametros_autocompletar ae_parametros_autocompletar_pkey; Type: CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_parametros_autocompletar
    ADD CONSTRAINT ae_parametros_autocompletar_pkey PRIMARY KEY (id);


--
-- Name: ae_parametros_validacion ae_parametros_validacion_pkey; Type: CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_parametros_validacion
    ADD CONSTRAINT ae_parametros_validacion_pkey PRIMARY KEY (id);


--
-- Name: ae_plantillas ae_plantillas_pkey; Type: CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_plantillas
    ADD CONSTRAINT ae_plantillas_pkey PRIMARY KEY (id);


--
-- Name: ae_preguntas_captcha ae_preguntas_pk; Type: CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_preguntas_captcha
    ADD CONSTRAINT ae_preguntas_pk PRIMARY KEY (clave);


--
-- Name: ae_recursos_aud ae_recursos_aud_pkey; Type: CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_recursos_aud
    ADD CONSTRAINT ae_recursos_aud_pkey PRIMARY KEY (id);


--
-- Name: ae_recursos ae_recursos_pkey; Type: CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_recursos
    ADD CONSTRAINT ae_recursos_pkey PRIMARY KEY (id);


--
-- Name: ae_reservas ae_reservas_pkey; Type: CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_reservas
    ADD CONSTRAINT ae_reservas_pkey PRIMARY KEY (id);


--
-- Name: ae_roles_usuario_recurso ae_roles_usuario_recurso_pkey; Type: CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_roles_usuario_recurso
    ADD CONSTRAINT ae_roles_usuario_recurso_pkey PRIMARY KEY (usuario_id, recurso_id);


--
-- Name: ae_serv_autocomp_por_dato ae_serv_autocomp_por_dato_pkey; Type: CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_serv_autocomp_por_dato
    ADD CONSTRAINT ae_serv_autocomp_por_dato_pkey PRIMARY KEY (id);


--
-- Name: ae_serv_autocompletar ae_serv_autocompletar_pkey; Type: CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_serv_autocompletar
    ADD CONSTRAINT ae_serv_autocompletar_pkey PRIMARY KEY (id);


--
-- Name: ae_servicio_por_recurso ae_servicio_por_recurso_pkey; Type: CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_servicio_por_recurso
    ADD CONSTRAINT ae_servicio_por_recurso_pkey PRIMARY KEY (id);


--
-- Name: ae_textos_agenda ae_textos_agenda_pkey; Type: CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_textos_agenda
    ADD CONSTRAINT ae_textos_agenda_pkey PRIMARY KEY (id);


--
-- Name: ae_textos ae_textos_pk; Type: CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_textos
    ADD CONSTRAINT ae_textos_pk PRIMARY KEY (codigo, idioma);


--
-- Name: ae_textos_recurso ae_textos_recurso_pkey; Type: CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_textos_recurso
    ADD CONSTRAINT ae_textos_recurso_pkey PRIMARY KEY (id);


--
-- Name: ae_tokens_reservas ae_tokens_reservas_pkey; Type: CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_tokens_reservas
    ADD CONSTRAINT ae_tokens_reservas_pkey PRIMARY KEY (id);


--
-- Name: ae_tramites_agendas ae_tramites_agenda_pkey; Type: CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_tramites_agendas
    ADD CONSTRAINT ae_tramites_agenda_pkey PRIMARY KEY (id);


--
-- Name: ae_validaciones ae_validaciones_pkey; Type: CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_validaciones
    ADD CONSTRAINT ae_validaciones_pkey PRIMARY KEY (id);


--
-- Name: ae_validaciones_por_dato ae_validaciones_por_dato_pkey; Type: CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_validaciones_por_dato
    ADD CONSTRAINT ae_validaciones_por_dato_pkey PRIMARY KEY (id);


--
-- Name: ae_validaciones_por_recurso ae_validaciones_por_recurso_pkey; Type: CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_validaciones_por_recurso
    ADD CONSTRAINT ae_validaciones_por_recurso_pkey PRIMARY KEY (id);


--
-- Name: ae_valor_constante_val_rec ae_valor_constante_val_rec_pkey; Type: CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_valor_constante_val_rec
    ADD CONSTRAINT ae_valor_constante_val_rec_pkey PRIMARY KEY (id);


--
-- Name: ae_valores_del_dato ae_valores_del_dato_pkey; Type: CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_valores_del_dato
    ADD CONSTRAINT ae_valores_del_dato_pkey PRIMARY KEY (id);


--
-- Name: ae_acciones_miperfil_recurso ae_acciones_miperfil_recurso_pkey; Type: CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_acciones_miperfil_recurso
    ADD CONSTRAINT ae_acciones_miperfil_recurso_pkey PRIMARY KEY (id);


--
-- Name: ae_acciones ae_acciones_pkey; Type: CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_acciones
    ADD CONSTRAINT ae_acciones_pkey PRIMARY KEY (id);


--
-- Name: ae_acciones_por_dato ae_acciones_por_dato_pkey; Type: CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_acciones_por_dato
    ADD CONSTRAINT ae_acciones_por_dato_pkey PRIMARY KEY (id);


--
-- Name: ae_acciones_por_recurso ae_acciones_por_recurso_pkey; Type: CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_acciones_por_recurso
    ADD CONSTRAINT ae_acciones_por_recurso_pkey PRIMARY KEY (id);


--
-- Name: ae_agendas ae_agendas_pkey; Type: CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_agendas
    ADD CONSTRAINT ae_agendas_pkey PRIMARY KEY (id);


--
-- Name: ae_agrupaciones_datos ae_agrupaciones_datos_pkey; Type: CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_agrupaciones_datos
    ADD CONSTRAINT ae_agrupaciones_datos_pkey PRIMARY KEY (id);


--
-- Name: ae_anios ae_anios_pkey; Type: CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_anios
    ADD CONSTRAINT ae_anios_pkey PRIMARY KEY (id);


--
-- Name: ae_atencion ae_atencion_pkey; Type: CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_atencion
    ADD CONSTRAINT ae_atencion_pkey PRIMARY KEY (id);


--
-- Name: ae_frases_captcha ae_captchas_pk; Type: CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_frases_captcha
    ADD CONSTRAINT ae_captchas_pk PRIMARY KEY (clave);


--
-- Name: ae_comunicaciones ae_comunicaciones_pkey; Type: CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_comunicaciones
    ADD CONSTRAINT ae_comunicaciones_pkey PRIMARY KEY (id);


--
-- Name: ae_constante_validacion ae_constante_validacion_pkey; Type: CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_constante_validacion
    ADD CONSTRAINT ae_constante_validacion_pkey PRIMARY KEY (id);


--
-- Name: ae_datos_a_solicitar ae_datos_a_solicitar_pkey; Type: CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_datos_a_solicitar
    ADD CONSTRAINT ae_datos_a_solicitar_pkey PRIMARY KEY (id);


--
-- Name: ae_datos_del_recurso ae_datos_del_recurso_pkey; Type: CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_datos_del_recurso
    ADD CONSTRAINT ae_datos_del_recurso_pkey PRIMARY KEY (id);


--
-- Name: ae_datos_reserva ae_datos_reserva_pkey; Type: CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_datos_reserva
    ADD CONSTRAINT ae_datos_reserva_pkey PRIMARY KEY (id);


--
-- Name: ae_dias_del_mes ae_dias_del_mes_pkey; Type: CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_dias_del_mes
    ADD CONSTRAINT ae_dias_del_mes_pkey PRIMARY KEY (id);


--
-- Name: ae_dias_semana ae_dias_semana_pkey; Type: CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_dias_semana
    ADD CONSTRAINT ae_dias_semana_pkey PRIMARY KEY (id);


--
-- Name: ae_disponibilidades ae_disponibilidades_pkey; Type: CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_disponibilidades
    ADD CONSTRAINT ae_disponibilidades_pkey PRIMARY KEY (id);


--
-- Name: ae_llamadas ae_llamadas_aers_id_key; Type: CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_llamadas
    ADD CONSTRAINT ae_llamadas_aers_id_key UNIQUE (aers_id);


--
-- Name: ae_llamadas ae_llamadas_pkey; Type: CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_llamadas
    ADD CONSTRAINT ae_llamadas_pkey PRIMARY KEY (id);


--
-- Name: ae_meses ae_meses_pkey; Type: CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_meses
    ADD CONSTRAINT ae_meses_pkey PRIMARY KEY (id);


--
-- Name: ae_parametros_accion ae_parametros_accion_pkey; Type: CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_parametros_accion
    ADD CONSTRAINT ae_parametros_accion_pkey PRIMARY KEY (id);


--
-- Name: ae_parametros_autocompletar ae_parametros_autocompletar_pkey; Type: CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_parametros_autocompletar
    ADD CONSTRAINT ae_parametros_autocompletar_pkey PRIMARY KEY (id);


--
-- Name: ae_parametros_validacion ae_parametros_validacion_pkey; Type: CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_parametros_validacion
    ADD CONSTRAINT ae_parametros_validacion_pkey PRIMARY KEY (id);


--
-- Name: ae_plantillas ae_plantillas_pkey; Type: CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_plantillas
    ADD CONSTRAINT ae_plantillas_pkey PRIMARY KEY (id);


--
-- Name: ae_preguntas_captcha ae_preguntas_pk; Type: CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_preguntas_captcha
    ADD CONSTRAINT ae_preguntas_pk PRIMARY KEY (clave);


--
-- Name: ae_recursos_aud ae_recursos_aud_pkey; Type: CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_recursos_aud
    ADD CONSTRAINT ae_recursos_aud_pkey PRIMARY KEY (id);


--
-- Name: ae_recursos ae_recursos_pkey; Type: CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_recursos
    ADD CONSTRAINT ae_recursos_pkey PRIMARY KEY (id);


--
-- Name: ae_reservas ae_reservas_pkey; Type: CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_reservas
    ADD CONSTRAINT ae_reservas_pkey PRIMARY KEY (id);


--
-- Name: ae_roles_usuario_recurso ae_roles_usuario_recurso_pkey; Type: CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_roles_usuario_recurso
    ADD CONSTRAINT ae_roles_usuario_recurso_pkey PRIMARY KEY (usuario_id, recurso_id);


--
-- Name: ae_serv_autocomp_por_dato ae_serv_autocomp_por_dato_pkey; Type: CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_serv_autocomp_por_dato
    ADD CONSTRAINT ae_serv_autocomp_por_dato_pkey PRIMARY KEY (id);


--
-- Name: ae_serv_autocompletar ae_serv_autocompletar_pkey; Type: CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_serv_autocompletar
    ADD CONSTRAINT ae_serv_autocompletar_pkey PRIMARY KEY (id);


--
-- Name: ae_servicio_por_recurso ae_servicio_por_recurso_pkey; Type: CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_servicio_por_recurso
    ADD CONSTRAINT ae_servicio_por_recurso_pkey PRIMARY KEY (id);


--
-- Name: ae_textos_agenda ae_textos_agenda_pkey; Type: CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_textos_agenda
    ADD CONSTRAINT ae_textos_agenda_pkey PRIMARY KEY (id);


--
-- Name: ae_textos ae_textos_pk; Type: CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_textos
    ADD CONSTRAINT ae_textos_pk PRIMARY KEY (codigo, idioma);


--
-- Name: ae_textos_recurso ae_textos_recurso_pkey; Type: CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_textos_recurso
    ADD CONSTRAINT ae_textos_recurso_pkey PRIMARY KEY (id);


--
-- Name: ae_tokens_reservas ae_tokens_reservas_pkey; Type: CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_tokens_reservas
    ADD CONSTRAINT ae_tokens_reservas_pkey PRIMARY KEY (id);


--
-- Name: ae_tramites_agendas ae_tramites_agenda_pkey; Type: CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_tramites_agendas
    ADD CONSTRAINT ae_tramites_agenda_pkey PRIMARY KEY (id);


--
-- Name: ae_validaciones ae_validaciones_pkey; Type: CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_validaciones
    ADD CONSTRAINT ae_validaciones_pkey PRIMARY KEY (id);


--
-- Name: ae_validaciones_por_dato ae_validaciones_por_dato_pkey; Type: CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_validaciones_por_dato
    ADD CONSTRAINT ae_validaciones_por_dato_pkey PRIMARY KEY (id);


--
-- Name: ae_validaciones_por_recurso ae_validaciones_por_recurso_pkey; Type: CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_validaciones_por_recurso
    ADD CONSTRAINT ae_validaciones_por_recurso_pkey PRIMARY KEY (id);


--
-- Name: ae_valor_constante_val_rec ae_valor_constante_val_rec_pkey; Type: CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_valor_constante_val_rec
    ADD CONSTRAINT ae_valor_constante_val_rec_pkey PRIMARY KEY (id);


--
-- Name: ae_valores_del_dato ae_valores_del_dato_pkey; Type: CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_valores_del_dato
    ADD CONSTRAINT ae_valores_del_dato_pkey PRIMARY KEY (id);


--
-- Name: ae_captchas ae_captchas_pk; Type: CONSTRAINT; Schema: global; Owner: sae
--

ALTER TABLE ONLY global.ae_captchas
    ADD CONSTRAINT ae_captchas_pk PRIMARY KEY (clave);


--
-- Name: ae_configuracion ae_configuracion_pk; Type: CONSTRAINT; Schema: global; Owner: sae
--

ALTER TABLE ONLY global.ae_configuracion
    ADD CONSTRAINT ae_configuracion_pk PRIMARY KEY (id);


--
-- Name: ae_empresas ae_empresas_pkey; Type: CONSTRAINT; Schema: global; Owner: sae
--

ALTER TABLE ONLY global.ae_empresas
    ADD CONSTRAINT ae_empresas_pkey PRIMARY KEY (id);


--
-- Name: ae_novedades ae_novedades_pk; Type: CONSTRAINT; Schema: global; Owner: sae
--

ALTER TABLE ONLY global.ae_novedades
    ADD CONSTRAINT ae_novedades_pk PRIMARY KEY (id);


--
-- Name: ae_oficinas ae_oficinas_pkey; Type: CONSTRAINT; Schema: global; Owner: sae
--

ALTER TABLE ONLY global.ae_oficinas
    ADD CONSTRAINT ae_oficinas_pkey PRIMARY KEY (id);


--
-- Name: ae_oficinas ae_oficinas_un1; Type: CONSTRAINT; Schema: global; Owner: sae
--

ALTER TABLE ONLY global.ae_oficinas
    ADD CONSTRAINT ae_oficinas_un1 UNIQUE (tramite_id, nombre);


--
-- Name: ae_organismos ae_organismos_pkey; Type: CONSTRAINT; Schema: global; Owner: sae
--

ALTER TABLE ONLY global.ae_organismos
    ADD CONSTRAINT ae_organismos_pkey PRIMARY KEY (id);


--
-- Name: ae_organismos ae_organismos_un1; Type: CONSTRAINT; Schema: global; Owner: sae
--

ALTER TABLE ONLY global.ae_organismos
    ADD CONSTRAINT ae_organismos_un1 UNIQUE (codigo);


--
-- Name: ae_rel_usuarios_empresas ae_rel_usuarios_empresas_pkey; Type: CONSTRAINT; Schema: global; Owner: sae
--

ALTER TABLE ONLY global.ae_rel_usuarios_empresas
    ADD CONSTRAINT ae_rel_usuarios_empresas_pkey PRIMARY KEY (usuario_id, empresa_id);


--
-- Name: ae_rel_usuarios_organismos ae_rel_usuarios_organismos_pkey; Type: CONSTRAINT; Schema: global; Owner: sae
--

ALTER TABLE ONLY global.ae_rel_usuarios_organismos
    ADD CONSTRAINT ae_rel_usuarios_organismos_pkey PRIMARY KEY (usuario_id, org_id);


--
-- Name: ae_rel_usuarios_roles ae_rel_usuarios_roles_pkey; Type: CONSTRAINT; Schema: global; Owner: sae
--

ALTER TABLE ONLY global.ae_rel_usuarios_roles
    ADD CONSTRAINT ae_rel_usuarios_roles_pkey PRIMARY KEY (usuario_id, empresa_id, rol_nombre);


--
-- Name: ae_textos ae_textos_pk; Type: CONSTRAINT; Schema: global; Owner: sae
--

ALTER TABLE ONLY global.ae_textos
    ADD CONSTRAINT ae_textos_pk PRIMARY KEY (codigo);


--
-- Name: ae_tokens ae_tokens_pkey; Type: CONSTRAINT; Schema: global; Owner: sae
--

ALTER TABLE ONLY global.ae_tokens
    ADD CONSTRAINT ae_tokens_pkey PRIMARY KEY (token);


--
-- Name: ae_tramites ae_tramites_pkey; Type: CONSTRAINT; Schema: global; Owner: sae
--

ALTER TABLE ONLY global.ae_tramites
    ADD CONSTRAINT ae_tramites_pkey PRIMARY KEY (id);


--
-- Name: ae_trazabilidad ae_trazabilidad_pk; Type: CONSTRAINT; Schema: global; Owner: sae
--

ALTER TABLE ONLY global.ae_trazabilidad
    ADD CONSTRAINT ae_trazabilidad_pk PRIMARY KEY (id);


--
-- Name: ae_unidadesejecutoras ae_unidadesejecutoras_pkey; Type: CONSTRAINT; Schema: global; Owner: sae
--

ALTER TABLE ONLY global.ae_unidadesejecutoras
    ADD CONSTRAINT ae_unidadesejecutoras_pkey PRIMARY KEY (id);


--
-- Name: ae_unidadesejecutoras ae_unidadesejecutoras_un1; Type: CONSTRAINT; Schema: global; Owner: sae
--

ALTER TABLE ONLY global.ae_unidadesejecutoras
    ADD CONSTRAINT ae_unidadesejecutoras_un1 UNIQUE (codigo);


--
-- Name: ae_usuarios ae_usuarios_pkey; Type: CONSTRAINT; Schema: global; Owner: sae
--

ALTER TABLE ONLY global.ae_usuarios
    ADD CONSTRAINT ae_usuarios_pkey PRIMARY KEY (id);


--
-- Name: ae_usuarios ae_usuarios_un1; Type: CONSTRAINT; Schema: global; Owner: sae
--

ALTER TABLE ONLY global.ae_usuarios
    ADD CONSTRAINT ae_usuarios_un1 UNIQUE (codigo);


--
-- Name: ae_datos_reserva_aeds_id_idx; Type: INDEX; Schema: empresa1; Owner: sae
--

CREATE INDEX ae_datos_reserva_aeds_id_idx ON empresa1.ae_datos_reserva USING btree (aeds_id);


--
-- Name: ae_datos_reserva_aers_id_idx; Type: INDEX; Schema: empresa1; Owner: sae
--

CREATE INDEX ae_datos_reserva_aers_id_idx ON empresa1.ae_datos_reserva USING btree (aers_id);


--
-- Name: ae_disponibilidades_fecha_idx; Type: INDEX; Schema: empresa1; Owner: sae
--

CREATE INDEX ae_disponibilidades_fecha_idx ON empresa1.ae_disponibilidades USING btree (fecha);


--
-- Name: ae_reservas_aetr_id_idx; Type: INDEX; Schema: empresa1; Owner: sae
--

CREATE INDEX ae_reservas_aetr_id_idx ON empresa1.ae_reservas USING btree (aetr_id);


--
-- Name: ae_reservas_disponibilidades_disponibilidad; Type: INDEX; Schema: empresa1; Owner: sae
--

CREATE INDEX ae_reservas_disponibilidades_disponibilidad ON empresa1.ae_reservas_disponibilidades USING btree (aedi_id);


--
-- Name: ae_reservas_disponibilidades_reserva; Type: INDEX; Schema: empresa1; Owner: sae
--

CREATE INDEX ae_reservas_disponibilidades_reserva ON empresa1.ae_reservas_disponibilidades USING btree (aers_id);


--
-- Name: ae_datos_reserva_aeds_id_idx; Type: INDEX; Schema: empresa2; Owner: sae
--

CREATE INDEX ae_datos_reserva_aeds_id_idx ON empresa2.ae_datos_reserva USING btree (aeds_id);


--
-- Name: ae_datos_reserva_aers_id_idx; Type: INDEX; Schema: empresa2; Owner: sae
--

CREATE INDEX ae_datos_reserva_aers_id_idx ON empresa2.ae_datos_reserva USING btree (aers_id);


--
-- Name: ae_disponibilidades_fecha_idx; Type: INDEX; Schema: empresa2; Owner: sae
--

CREATE INDEX ae_disponibilidades_fecha_idx ON empresa2.ae_disponibilidades USING btree (fecha);


--
-- Name: ae_reservas_aetr_id_idx; Type: INDEX; Schema: empresa2; Owner: sae
--

CREATE INDEX ae_reservas_aetr_id_idx ON empresa2.ae_reservas USING btree (aetr_id);


--
-- Name: ae_reservas_disponibilidades_disponibilidad; Type: INDEX; Schema: empresa2; Owner: sae
--

CREATE INDEX ae_reservas_disponibilidades_disponibilidad ON empresa2.ae_reservas_disponibilidades USING btree (aedi_id);


--
-- Name: ae_reservas_disponibilidades_reserva; Type: INDEX; Schema: empresa2; Owner: sae
--

CREATE INDEX ae_reservas_disponibilidades_reserva ON empresa2.ae_reservas_disponibilidades USING btree (aers_id);


--
-- Name: ae_datos_reserva_aeds_id_idx; Type: INDEX; Schema: empresa3; Owner: sae
--

CREATE INDEX ae_datos_reserva_aeds_id_idx ON empresa3.ae_datos_reserva USING btree (aeds_id);


--
-- Name: ae_datos_reserva_aers_id_idx; Type: INDEX; Schema: empresa3; Owner: sae
--

CREATE INDEX ae_datos_reserva_aers_id_idx ON empresa3.ae_datos_reserva USING btree (aers_id);


--
-- Name: ae_disponibilidades_fecha_idx; Type: INDEX; Schema: empresa3; Owner: sae
--

CREATE INDEX ae_disponibilidades_fecha_idx ON empresa3.ae_disponibilidades USING btree (fecha);


--
-- Name: ae_reservas_aetr_id_idx; Type: INDEX; Schema: empresa3; Owner: sae
--

CREATE INDEX ae_reservas_aetr_id_idx ON empresa3.ae_reservas USING btree (aetr_id);


--
-- Name: ae_reservas_disponibilidades_disponibilidad; Type: INDEX; Schema: empresa3; Owner: sae
--

CREATE INDEX ae_reservas_disponibilidades_disponibilidad ON empresa3.ae_reservas_disponibilidades USING btree (aedi_id);


--
-- Name: ae_reservas_disponibilidades_reserva; Type: INDEX; Schema: empresa3; Owner: sae
--

CREATE INDEX ae_reservas_disponibilidades_reserva ON empresa3.ae_reservas_disponibilidades USING btree (aers_id);


--
-- Name: ae_datos_reserva_aeds_id_idx; Type: INDEX; Schema: empresa4; Owner: sae
--

CREATE INDEX ae_datos_reserva_aeds_id_idx ON empresa4.ae_datos_reserva USING btree (aeds_id);


--
-- Name: ae_datos_reserva_aers_id_idx; Type: INDEX; Schema: empresa4; Owner: sae
--

CREATE INDEX ae_datos_reserva_aers_id_idx ON empresa4.ae_datos_reserva USING btree (aers_id);


--
-- Name: ae_disponibilidades_fecha_idx; Type: INDEX; Schema: empresa4; Owner: sae
--

CREATE INDEX ae_disponibilidades_fecha_idx ON empresa4.ae_disponibilidades USING btree (fecha);


--
-- Name: ae_reservas_aetr_id_idx; Type: INDEX; Schema: empresa4; Owner: sae
--

CREATE INDEX ae_reservas_aetr_id_idx ON empresa4.ae_reservas USING btree (aetr_id);


--
-- Name: ae_reservas_disponibilidades_disponibilidad; Type: INDEX; Schema: empresa4; Owner: sae
--

CREATE INDEX ae_reservas_disponibilidades_disponibilidad ON empresa4.ae_reservas_disponibilidades USING btree (aedi_id);


--
-- Name: ae_reservas_disponibilidades_reserva; Type: INDEX; Schema: empresa4; Owner: sae
--

CREATE INDEX ae_reservas_disponibilidades_reserva ON empresa4.ae_reservas_disponibilidades USING btree (aers_id);


--
-- Name: ae_datos_reserva_aeds_id_idx; Type: INDEX; Schema: empresa5; Owner: sae
--

CREATE INDEX ae_datos_reserva_aeds_id_idx ON empresa5.ae_datos_reserva USING btree (aeds_id);


--
-- Name: ae_datos_reserva_aers_id_idx; Type: INDEX; Schema: empresa5; Owner: sae
--

CREATE INDEX ae_datos_reserva_aers_id_idx ON empresa5.ae_datos_reserva USING btree (aers_id);


--
-- Name: ae_disponibilidades_fecha_idx; Type: INDEX; Schema: empresa5; Owner: sae
--

CREATE INDEX ae_disponibilidades_fecha_idx ON empresa5.ae_disponibilidades USING btree (fecha);


--
-- Name: ae_reservas_aetr_id_idx; Type: INDEX; Schema: empresa5; Owner: sae
--

CREATE INDEX ae_reservas_aetr_id_idx ON empresa5.ae_reservas USING btree (aetr_id);


--
-- Name: ae_reservas_disponibilidades_disponibilidad; Type: INDEX; Schema: empresa5; Owner: sae
--

CREATE INDEX ae_reservas_disponibilidades_disponibilidad ON empresa5.ae_reservas_disponibilidades USING btree (aedi_id);


--
-- Name: ae_reservas_disponibilidades_reserva; Type: INDEX; Schema: empresa5; Owner: sae
--

CREATE INDEX ae_reservas_disponibilidades_reserva ON empresa5.ae_reservas_disponibilidades USING btree (aers_id);


--
-- Name: ae_empresas_org_id_idx; Type: INDEX; Schema: global; Owner: sae
--

CREATE INDEX ae_empresas_org_id_idx ON global.ae_empresas USING btree (org_id);


--
-- Name: ae_rel_usuarios_roles_empresa_id_idx; Type: INDEX; Schema: global; Owner: sae
--

CREATE INDEX ae_rel_usuarios_roles_empresa_id_idx ON global.ae_rel_usuarios_roles USING btree (empresa_id);


--
-- Name: ae_rel_usuarios_roles_rol_idx; Type: INDEX; Schema: global; Owner: sae
--

CREATE INDEX ae_rel_usuarios_roles_rol_idx ON global.ae_rel_usuarios_roles USING btree (rol_nombre) WHERE ((rol_nombre)::text = 'RA_AE_ADMINISTRADOR'::text);


--
-- Name: ae_rel_usuarios_roles_usuario_id_idx; Type: INDEX; Schema: global; Owner: sae
--

CREATE INDEX ae_rel_usuarios_roles_usuario_id_idx ON global.ae_rel_usuarios_roles USING btree (usuario_id);


--
-- Name: idx_clave_org; Type: INDEX; Schema: global; Owner: sae
--

CREATE UNIQUE INDEX idx_clave_org ON global.ae_configuracion USING btree (clave, org_id);


--
-- Name: ae_acciones_miperfil_recurso accion_recurso_fkey; Type: FK CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_acciones_miperfil_recurso
    ADD CONSTRAINT accion_recurso_fkey FOREIGN KEY (recurso_id) REFERENCES empresa1.ae_recursos(id);


--
-- Name: ae_reservas ae_reservas_token; Type: FK CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_reservas
    ADD CONSTRAINT ae_reservas_token FOREIGN KEY (aetr_id) REFERENCES empresa1.ae_tokens_reservas(id);


--
-- Name: ae_tokens_reservas ae_tokens_reservas_recurso; Type: FK CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_tokens_reservas
    ADD CONSTRAINT ae_tokens_reservas_recurso FOREIGN KEY (aere_id) REFERENCES empresa1.ae_recursos(id);


--
-- Name: ae_disponibilidades fk1c09bf24104398e1; Type: FK CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_disponibilidades
    ADD CONSTRAINT fk1c09bf24104398e1 FOREIGN KEY (aere_id) REFERENCES empresa1.ae_recursos(id);


--
-- Name: ae_disponibilidades fk1c09bf249a9bb7b2; Type: FK CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_disponibilidades
    ADD CONSTRAINT fk1c09bf249a9bb7b2 FOREIGN KEY (aepl_id) REFERENCES empresa1.ae_plantillas(id);


--
-- Name: ae_dias_semana fk28a15dc69a9bb7b2; Type: FK CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_dias_semana
    ADD CONSTRAINT fk28a15dc69a9bb7b2 FOREIGN KEY (aepl_id) REFERENCES empresa1.ae_plantillas(id);


--
-- Name: ae_agrupaciones_datos fk3360aa44104398e1; Type: FK CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_agrupaciones_datos
    ADD CONSTRAINT fk3360aa44104398e1 FOREIGN KEY (aere_id) REFERENCES empresa1.ae_recursos(id);


--
-- Name: ae_datos_a_solicitar fk3ce7cc09104398e1; Type: FK CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_datos_a_solicitar
    ADD CONSTRAINT fk3ce7cc09104398e1 FOREIGN KEY (aere_id) REFERENCES empresa1.ae_recursos(id);


--
-- Name: ae_datos_a_solicitar fk3ce7cc091876ae95; Type: FK CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_datos_a_solicitar
    ADD CONSTRAINT fk3ce7cc091876ae95 FOREIGN KEY (aead_id) REFERENCES empresa1.ae_agrupaciones_datos(id);


--
-- Name: ae_parametros_autocompletar fk3e0b63a4cc9035ed; Type: FK CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_parametros_autocompletar
    ADD CONSTRAINT fk3e0b63a4cc9035ed FOREIGN KEY (aeserv_id) REFERENCES empresa1.ae_serv_autocompletar(id);


--
-- Name: ae_constante_validacion fk3f09314323ebf200; Type: FK CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_constante_validacion
    ADD CONSTRAINT fk3f09314323ebf200 FOREIGN KEY (aeva_id) REFERENCES empresa1.ae_validaciones(id);


--
-- Name: ae_valores_del_dato fk42202b5436760caf; Type: FK CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_valores_del_dato
    ADD CONSTRAINT fk42202b5436760caf FOREIGN KEY (aeds_id) REFERENCES empresa1.ae_datos_a_solicitar(id);


--
-- Name: ae_parametros_accion fk4da30a11e4b40066; Type: FK CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_parametros_accion
    ADD CONSTRAINT fk4da30a11e4b40066 FOREIGN KEY (aeac_id) REFERENCES empresa1.ae_acciones(id);


--
-- Name: ae_datos_del_recurso fk5ff42436104398e1; Type: FK CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_datos_del_recurso
    ADD CONSTRAINT fk5ff42436104398e1 FOREIGN KEY (aere_id) REFERENCES empresa1.ae_recursos(id);


--
-- Name: ae_validaciones_por_dato fk66d0a85036760caf; Type: FK CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_validaciones_por_dato
    ADD CONSTRAINT fk66d0a85036760caf FOREIGN KEY (aeds_id) REFERENCES empresa1.ae_datos_a_solicitar(id);


--
-- Name: ae_validaciones_por_dato fk66d0a8508d2f46a5; Type: FK CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_validaciones_por_dato
    ADD CONSTRAINT fk66d0a8508d2f46a5 FOREIGN KEY (aevr_id) REFERENCES empresa1.ae_validaciones_por_recurso(id);


--
-- Name: ae_acciones_por_dato fk6e5144f336760caf; Type: FK CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_acciones_por_dato
    ADD CONSTRAINT fk6e5144f336760caf FOREIGN KEY (aeds_id) REFERENCES empresa1.ae_datos_a_solicitar(id);


--
-- Name: ae_acciones_por_dato fk6e5144f362f9440d; Type: FK CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_acciones_por_dato
    ADD CONSTRAINT fk6e5144f362f9440d FOREIGN KEY (aear_id) REFERENCES empresa1.ae_acciones_por_recurso(id);


--
-- Name: ae_reservas_disponibilidades fk79b9a11211242882; Type: FK CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_reservas_disponibilidades
    ADD CONSTRAINT fk79b9a11211242882 FOREIGN KEY (aers_id) REFERENCES empresa1.ae_reservas(id);


--
-- Name: ae_reservas_disponibilidades fk79b9a112406004b7; Type: FK CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_reservas_disponibilidades
    ADD CONSTRAINT fk79b9a112406004b7 FOREIGN KEY (aedi_id) REFERENCES empresa1.ae_disponibilidades(id);


--
-- Name: ae_valor_constante_val_rec fk8a30e71e8d2f46a5; Type: FK CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_valor_constante_val_rec
    ADD CONSTRAINT fk8a30e71e8d2f46a5 FOREIGN KEY (aevr_id) REFERENCES empresa1.ae_validaciones_por_recurso(id);


--
-- Name: ae_textos_agenda fk96b86f5fe4ef2a07; Type: FK CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_textos_agenda
    ADD CONSTRAINT fk96b86f5fe4ef2a07 FOREIGN KEY (aeag_id) REFERENCES empresa1.ae_agendas(id);


--
-- Name: ae_validaciones_por_recurso fk9e323ab1104398e1; Type: FK CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_validaciones_por_recurso
    ADD CONSTRAINT fk9e323ab1104398e1 FOREIGN KEY (aere_id) REFERENCES empresa1.ae_recursos(id);


--
-- Name: ae_validaciones_por_recurso fk9e323ab123ebf200; Type: FK CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_validaciones_por_recurso
    ADD CONSTRAINT fk9e323ab123ebf200 FOREIGN KEY (aeva_id) REFERENCES empresa1.ae_validaciones(id);


--
-- Name: ae_datos_reserva fk9ecc9f5911242882; Type: FK CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_datos_reserva
    ADD CONSTRAINT fk9ecc9f5911242882 FOREIGN KEY (aers_id) REFERENCES empresa1.ae_reservas(id);


--
-- Name: ae_datos_reserva fk9ecc9f5936760caf; Type: FK CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_datos_reserva
    ADD CONSTRAINT fk9ecc9f5936760caf FOREIGN KEY (aeds_id) REFERENCES empresa1.ae_datos_a_solicitar(id);


--
-- Name: ae_llamadas fka0da2cfc104398e1; Type: FK CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_llamadas
    ADD CONSTRAINT fka0da2cfc104398e1 FOREIGN KEY (aere_id) REFERENCES empresa1.ae_recursos(id);


--
-- Name: ae_llamadas fka0da2cfc11242882; Type: FK CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_llamadas
    ADD CONSTRAINT fka0da2cfc11242882 FOREIGN KEY (aers_id) REFERENCES empresa1.ae_reservas(id);


--
-- Name: ae_recursos fka1b7fd05e4ef2a07; Type: FK CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_recursos
    ADD CONSTRAINT fka1b7fd05e4ef2a07 FOREIGN KEY (aeag_id) REFERENCES empresa1.ae_agendas(id);


--
-- Name: ae_serv_autocomp_por_dato fkacfc169736760caf; Type: FK CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_serv_autocomp_por_dato
    ADD CONSTRAINT fkacfc169736760caf FOREIGN KEY (aeds_id) REFERENCES empresa1.ae_datos_a_solicitar(id);


--
-- Name: ae_serv_autocomp_por_dato fkacfc1697bcb2c28e; Type: FK CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_serv_autocomp_por_dato
    ADD CONSTRAINT fkacfc1697bcb2c28e FOREIGN KEY (aesr_id) REFERENCES empresa1.ae_servicio_por_recurso(id);


--
-- Name: ae_acciones_por_recurso fkade6372e104398e1; Type: FK CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_acciones_por_recurso
    ADD CONSTRAINT fkade6372e104398e1 FOREIGN KEY (aere_id) REFERENCES empresa1.ae_recursos(id);


--
-- Name: ae_acciones_por_recurso fkade6372ee4b40066; Type: FK CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_acciones_por_recurso
    ADD CONSTRAINT fkade6372ee4b40066 FOREIGN KEY (aeac_id) REFERENCES empresa1.ae_acciones(id);


--
-- Name: ae_parametros_validacion fkc717af5423ebf200; Type: FK CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_parametros_validacion
    ADD CONSTRAINT fkc717af5423ebf200 FOREIGN KEY (aeva_id) REFERENCES empresa1.ae_validaciones(id);


--
-- Name: ae_dias_del_mes fkd1fddf1a9a9bb7b2; Type: FK CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_dias_del_mes
    ADD CONSTRAINT fkd1fddf1a9a9bb7b2 FOREIGN KEY (aepl_id) REFERENCES empresa1.ae_plantillas(id);


--
-- Name: ae_servicio_por_recurso fkd75adbaf104398e1; Type: FK CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_servicio_por_recurso
    ADD CONSTRAINT fkd75adbaf104398e1 FOREIGN KEY (aere_id) REFERENCES empresa1.ae_recursos(id);


--
-- Name: ae_servicio_por_recurso fkd75adbafcc9035ed; Type: FK CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_servicio_por_recurso
    ADD CONSTRAINT fkd75adbafcc9035ed FOREIGN KEY (aeserv_id) REFERENCES empresa1.ae_serv_autocompletar(id);


--
-- Name: ae_atencion fkd841909c11242882; Type: FK CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_atencion
    ADD CONSTRAINT fkd841909c11242882 FOREIGN KEY (aers_id) REFERENCES empresa1.ae_reservas(id);


--
-- Name: ae_anios fke2ce44e59a9bb7b2; Type: FK CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_anios
    ADD CONSTRAINT fke2ce44e59a9bb7b2 FOREIGN KEY (aepl_id) REFERENCES empresa1.ae_plantillas(id);


--
-- Name: ae_meses fke3736bee9a9bb7b2; Type: FK CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_meses
    ADD CONSTRAINT fke3736bee9a9bb7b2 FOREIGN KEY (aepl_id) REFERENCES empresa1.ae_plantillas(id);


--
-- Name: ae_plantillas fkf9c6590b104398e1; Type: FK CONSTRAINT; Schema: empresa1; Owner: sae
--

ALTER TABLE ONLY empresa1.ae_plantillas
    ADD CONSTRAINT fkf9c6590b104398e1 FOREIGN KEY (aere_id) REFERENCES empresa1.ae_recursos(id);


--
-- Name: ae_acciones_miperfil_recurso accion_recurso_fkey; Type: FK CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_acciones_miperfil_recurso
    ADD CONSTRAINT accion_recurso_fkey FOREIGN KEY (recurso_id) REFERENCES empresa2.ae_recursos(id);


--
-- Name: ae_reservas ae_reservas_token; Type: FK CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_reservas
    ADD CONSTRAINT ae_reservas_token FOREIGN KEY (aetr_id) REFERENCES empresa2.ae_tokens_reservas(id);


--
-- Name: ae_tokens_reservas ae_tokens_reservas_recurso; Type: FK CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_tokens_reservas
    ADD CONSTRAINT ae_tokens_reservas_recurso FOREIGN KEY (aere_id) REFERENCES empresa2.ae_recursos(id);


--
-- Name: ae_disponibilidades fk1c09bf24104398e1; Type: FK CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_disponibilidades
    ADD CONSTRAINT fk1c09bf24104398e1 FOREIGN KEY (aere_id) REFERENCES empresa2.ae_recursos(id);


--
-- Name: ae_disponibilidades fk1c09bf249a9bb7b2; Type: FK CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_disponibilidades
    ADD CONSTRAINT fk1c09bf249a9bb7b2 FOREIGN KEY (aepl_id) REFERENCES empresa2.ae_plantillas(id);


--
-- Name: ae_dias_semana fk28a15dc69a9bb7b2; Type: FK CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_dias_semana
    ADD CONSTRAINT fk28a15dc69a9bb7b2 FOREIGN KEY (aepl_id) REFERENCES empresa2.ae_plantillas(id);


--
-- Name: ae_agrupaciones_datos fk3360aa44104398e1; Type: FK CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_agrupaciones_datos
    ADD CONSTRAINT fk3360aa44104398e1 FOREIGN KEY (aere_id) REFERENCES empresa2.ae_recursos(id);


--
-- Name: ae_datos_a_solicitar fk3ce7cc09104398e1; Type: FK CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_datos_a_solicitar
    ADD CONSTRAINT fk3ce7cc09104398e1 FOREIGN KEY (aere_id) REFERENCES empresa2.ae_recursos(id);


--
-- Name: ae_datos_a_solicitar fk3ce7cc091876ae95; Type: FK CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_datos_a_solicitar
    ADD CONSTRAINT fk3ce7cc091876ae95 FOREIGN KEY (aead_id) REFERENCES empresa2.ae_agrupaciones_datos(id);


--
-- Name: ae_parametros_autocompletar fk3e0b63a4cc9035ed; Type: FK CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_parametros_autocompletar
    ADD CONSTRAINT fk3e0b63a4cc9035ed FOREIGN KEY (aeserv_id) REFERENCES empresa2.ae_serv_autocompletar(id);


--
-- Name: ae_constante_validacion fk3f09314323ebf200; Type: FK CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_constante_validacion
    ADD CONSTRAINT fk3f09314323ebf200 FOREIGN KEY (aeva_id) REFERENCES empresa2.ae_validaciones(id);


--
-- Name: ae_valores_del_dato fk42202b5436760caf; Type: FK CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_valores_del_dato
    ADD CONSTRAINT fk42202b5436760caf FOREIGN KEY (aeds_id) REFERENCES empresa2.ae_datos_a_solicitar(id);


--
-- Name: ae_parametros_accion fk4da30a11e4b40066; Type: FK CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_parametros_accion
    ADD CONSTRAINT fk4da30a11e4b40066 FOREIGN KEY (aeac_id) REFERENCES empresa2.ae_acciones(id);


--
-- Name: ae_datos_del_recurso fk5ff42436104398e1; Type: FK CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_datos_del_recurso
    ADD CONSTRAINT fk5ff42436104398e1 FOREIGN KEY (aere_id) REFERENCES empresa2.ae_recursos(id);


--
-- Name: ae_validaciones_por_dato fk66d0a85036760caf; Type: FK CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_validaciones_por_dato
    ADD CONSTRAINT fk66d0a85036760caf FOREIGN KEY (aeds_id) REFERENCES empresa2.ae_datos_a_solicitar(id);


--
-- Name: ae_validaciones_por_dato fk66d0a8508d2f46a5; Type: FK CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_validaciones_por_dato
    ADD CONSTRAINT fk66d0a8508d2f46a5 FOREIGN KEY (aevr_id) REFERENCES empresa2.ae_validaciones_por_recurso(id);


--
-- Name: ae_acciones_por_dato fk6e5144f336760caf; Type: FK CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_acciones_por_dato
    ADD CONSTRAINT fk6e5144f336760caf FOREIGN KEY (aeds_id) REFERENCES empresa2.ae_datos_a_solicitar(id);


--
-- Name: ae_acciones_por_dato fk6e5144f362f9440d; Type: FK CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_acciones_por_dato
    ADD CONSTRAINT fk6e5144f362f9440d FOREIGN KEY (aear_id) REFERENCES empresa2.ae_acciones_por_recurso(id);


--
-- Name: ae_reservas_disponibilidades fk79b9a11211242882; Type: FK CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_reservas_disponibilidades
    ADD CONSTRAINT fk79b9a11211242882 FOREIGN KEY (aers_id) REFERENCES empresa2.ae_reservas(id);


--
-- Name: ae_reservas_disponibilidades fk79b9a112406004b7; Type: FK CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_reservas_disponibilidades
    ADD CONSTRAINT fk79b9a112406004b7 FOREIGN KEY (aedi_id) REFERENCES empresa2.ae_disponibilidades(id);


--
-- Name: ae_valor_constante_val_rec fk8a30e71e8d2f46a5; Type: FK CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_valor_constante_val_rec
    ADD CONSTRAINT fk8a30e71e8d2f46a5 FOREIGN KEY (aevr_id) REFERENCES empresa2.ae_validaciones_por_recurso(id);


--
-- Name: ae_textos_agenda fk96b86f5fe4ef2a07; Type: FK CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_textos_agenda
    ADD CONSTRAINT fk96b86f5fe4ef2a07 FOREIGN KEY (aeag_id) REFERENCES empresa2.ae_agendas(id);


--
-- Name: ae_validaciones_por_recurso fk9e323ab1104398e1; Type: FK CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_validaciones_por_recurso
    ADD CONSTRAINT fk9e323ab1104398e1 FOREIGN KEY (aere_id) REFERENCES empresa2.ae_recursos(id);


--
-- Name: ae_validaciones_por_recurso fk9e323ab123ebf200; Type: FK CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_validaciones_por_recurso
    ADD CONSTRAINT fk9e323ab123ebf200 FOREIGN KEY (aeva_id) REFERENCES empresa2.ae_validaciones(id);


--
-- Name: ae_datos_reserva fk9ecc9f5911242882; Type: FK CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_datos_reserva
    ADD CONSTRAINT fk9ecc9f5911242882 FOREIGN KEY (aers_id) REFERENCES empresa2.ae_reservas(id);


--
-- Name: ae_datos_reserva fk9ecc9f5936760caf; Type: FK CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_datos_reserva
    ADD CONSTRAINT fk9ecc9f5936760caf FOREIGN KEY (aeds_id) REFERENCES empresa2.ae_datos_a_solicitar(id);


--
-- Name: ae_llamadas fka0da2cfc104398e1; Type: FK CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_llamadas
    ADD CONSTRAINT fka0da2cfc104398e1 FOREIGN KEY (aere_id) REFERENCES empresa2.ae_recursos(id);


--
-- Name: ae_llamadas fka0da2cfc11242882; Type: FK CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_llamadas
    ADD CONSTRAINT fka0da2cfc11242882 FOREIGN KEY (aers_id) REFERENCES empresa2.ae_reservas(id);


--
-- Name: ae_recursos fka1b7fd05e4ef2a07; Type: FK CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_recursos
    ADD CONSTRAINT fka1b7fd05e4ef2a07 FOREIGN KEY (aeag_id) REFERENCES empresa2.ae_agendas(id);


--
-- Name: ae_serv_autocomp_por_dato fkacfc169736760caf; Type: FK CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_serv_autocomp_por_dato
    ADD CONSTRAINT fkacfc169736760caf FOREIGN KEY (aeds_id) REFERENCES empresa2.ae_datos_a_solicitar(id);


--
-- Name: ae_serv_autocomp_por_dato fkacfc1697bcb2c28e; Type: FK CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_serv_autocomp_por_dato
    ADD CONSTRAINT fkacfc1697bcb2c28e FOREIGN KEY (aesr_id) REFERENCES empresa2.ae_servicio_por_recurso(id);


--
-- Name: ae_acciones_por_recurso fkade6372e104398e1; Type: FK CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_acciones_por_recurso
    ADD CONSTRAINT fkade6372e104398e1 FOREIGN KEY (aere_id) REFERENCES empresa2.ae_recursos(id);


--
-- Name: ae_acciones_por_recurso fkade6372ee4b40066; Type: FK CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_acciones_por_recurso
    ADD CONSTRAINT fkade6372ee4b40066 FOREIGN KEY (aeac_id) REFERENCES empresa2.ae_acciones(id);


--
-- Name: ae_parametros_validacion fkc717af5423ebf200; Type: FK CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_parametros_validacion
    ADD CONSTRAINT fkc717af5423ebf200 FOREIGN KEY (aeva_id) REFERENCES empresa2.ae_validaciones(id);


--
-- Name: ae_dias_del_mes fkd1fddf1a9a9bb7b2; Type: FK CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_dias_del_mes
    ADD CONSTRAINT fkd1fddf1a9a9bb7b2 FOREIGN KEY (aepl_id) REFERENCES empresa2.ae_plantillas(id);


--
-- Name: ae_servicio_por_recurso fkd75adbaf104398e1; Type: FK CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_servicio_por_recurso
    ADD CONSTRAINT fkd75adbaf104398e1 FOREIGN KEY (aere_id) REFERENCES empresa2.ae_recursos(id);


--
-- Name: ae_servicio_por_recurso fkd75adbafcc9035ed; Type: FK CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_servicio_por_recurso
    ADD CONSTRAINT fkd75adbafcc9035ed FOREIGN KEY (aeserv_id) REFERENCES empresa2.ae_serv_autocompletar(id);


--
-- Name: ae_atencion fkd841909c11242882; Type: FK CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_atencion
    ADD CONSTRAINT fkd841909c11242882 FOREIGN KEY (aers_id) REFERENCES empresa2.ae_reservas(id);


--
-- Name: ae_anios fke2ce44e59a9bb7b2; Type: FK CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_anios
    ADD CONSTRAINT fke2ce44e59a9bb7b2 FOREIGN KEY (aepl_id) REFERENCES empresa2.ae_plantillas(id);


--
-- Name: ae_meses fke3736bee9a9bb7b2; Type: FK CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_meses
    ADD CONSTRAINT fke3736bee9a9bb7b2 FOREIGN KEY (aepl_id) REFERENCES empresa2.ae_plantillas(id);


--
-- Name: ae_plantillas fkf9c6590b104398e1; Type: FK CONSTRAINT; Schema: empresa2; Owner: sae
--

ALTER TABLE ONLY empresa2.ae_plantillas
    ADD CONSTRAINT fkf9c6590b104398e1 FOREIGN KEY (aere_id) REFERENCES empresa2.ae_recursos(id);


--
-- Name: ae_acciones_miperfil_recurso accion_recurso_fkey; Type: FK CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_acciones_miperfil_recurso
    ADD CONSTRAINT accion_recurso_fkey FOREIGN KEY (recurso_id) REFERENCES empresa3.ae_recursos(id);


--
-- Name: ae_reservas ae_reservas_token; Type: FK CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_reservas
    ADD CONSTRAINT ae_reservas_token FOREIGN KEY (aetr_id) REFERENCES empresa3.ae_tokens_reservas(id);


--
-- Name: ae_tokens_reservas ae_tokens_reservas_recurso; Type: FK CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_tokens_reservas
    ADD CONSTRAINT ae_tokens_reservas_recurso FOREIGN KEY (aere_id) REFERENCES empresa3.ae_recursos(id);


--
-- Name: ae_disponibilidades fk1c09bf24104398e1; Type: FK CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_disponibilidades
    ADD CONSTRAINT fk1c09bf24104398e1 FOREIGN KEY (aere_id) REFERENCES empresa3.ae_recursos(id);


--
-- Name: ae_disponibilidades fk1c09bf249a9bb7b2; Type: FK CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_disponibilidades
    ADD CONSTRAINT fk1c09bf249a9bb7b2 FOREIGN KEY (aepl_id) REFERENCES empresa3.ae_plantillas(id);


--
-- Name: ae_dias_semana fk28a15dc69a9bb7b2; Type: FK CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_dias_semana
    ADD CONSTRAINT fk28a15dc69a9bb7b2 FOREIGN KEY (aepl_id) REFERENCES empresa3.ae_plantillas(id);


--
-- Name: ae_agrupaciones_datos fk3360aa44104398e1; Type: FK CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_agrupaciones_datos
    ADD CONSTRAINT fk3360aa44104398e1 FOREIGN KEY (aere_id) REFERENCES empresa3.ae_recursos(id);


--
-- Name: ae_datos_a_solicitar fk3ce7cc09104398e1; Type: FK CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_datos_a_solicitar
    ADD CONSTRAINT fk3ce7cc09104398e1 FOREIGN KEY (aere_id) REFERENCES empresa3.ae_recursos(id);


--
-- Name: ae_datos_a_solicitar fk3ce7cc091876ae95; Type: FK CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_datos_a_solicitar
    ADD CONSTRAINT fk3ce7cc091876ae95 FOREIGN KEY (aead_id) REFERENCES empresa3.ae_agrupaciones_datos(id);


--
-- Name: ae_parametros_autocompletar fk3e0b63a4cc9035ed; Type: FK CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_parametros_autocompletar
    ADD CONSTRAINT fk3e0b63a4cc9035ed FOREIGN KEY (aeserv_id) REFERENCES empresa3.ae_serv_autocompletar(id);


--
-- Name: ae_constante_validacion fk3f09314323ebf200; Type: FK CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_constante_validacion
    ADD CONSTRAINT fk3f09314323ebf200 FOREIGN KEY (aeva_id) REFERENCES empresa3.ae_validaciones(id);


--
-- Name: ae_valores_del_dato fk42202b5436760caf; Type: FK CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_valores_del_dato
    ADD CONSTRAINT fk42202b5436760caf FOREIGN KEY (aeds_id) REFERENCES empresa3.ae_datos_a_solicitar(id);


--
-- Name: ae_parametros_accion fk4da30a11e4b40066; Type: FK CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_parametros_accion
    ADD CONSTRAINT fk4da30a11e4b40066 FOREIGN KEY (aeac_id) REFERENCES empresa3.ae_acciones(id);


--
-- Name: ae_datos_del_recurso fk5ff42436104398e1; Type: FK CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_datos_del_recurso
    ADD CONSTRAINT fk5ff42436104398e1 FOREIGN KEY (aere_id) REFERENCES empresa3.ae_recursos(id);


--
-- Name: ae_validaciones_por_dato fk66d0a85036760caf; Type: FK CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_validaciones_por_dato
    ADD CONSTRAINT fk66d0a85036760caf FOREIGN KEY (aeds_id) REFERENCES empresa3.ae_datos_a_solicitar(id);


--
-- Name: ae_validaciones_por_dato fk66d0a8508d2f46a5; Type: FK CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_validaciones_por_dato
    ADD CONSTRAINT fk66d0a8508d2f46a5 FOREIGN KEY (aevr_id) REFERENCES empresa3.ae_validaciones_por_recurso(id);


--
-- Name: ae_acciones_por_dato fk6e5144f336760caf; Type: FK CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_acciones_por_dato
    ADD CONSTRAINT fk6e5144f336760caf FOREIGN KEY (aeds_id) REFERENCES empresa3.ae_datos_a_solicitar(id);


--
-- Name: ae_acciones_por_dato fk6e5144f362f9440d; Type: FK CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_acciones_por_dato
    ADD CONSTRAINT fk6e5144f362f9440d FOREIGN KEY (aear_id) REFERENCES empresa3.ae_acciones_por_recurso(id);


--
-- Name: ae_reservas_disponibilidades fk79b9a11211242882; Type: FK CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_reservas_disponibilidades
    ADD CONSTRAINT fk79b9a11211242882 FOREIGN KEY (aers_id) REFERENCES empresa3.ae_reservas(id);


--
-- Name: ae_reservas_disponibilidades fk79b9a112406004b7; Type: FK CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_reservas_disponibilidades
    ADD CONSTRAINT fk79b9a112406004b7 FOREIGN KEY (aedi_id) REFERENCES empresa3.ae_disponibilidades(id);


--
-- Name: ae_valor_constante_val_rec fk8a30e71e8d2f46a5; Type: FK CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_valor_constante_val_rec
    ADD CONSTRAINT fk8a30e71e8d2f46a5 FOREIGN KEY (aevr_id) REFERENCES empresa3.ae_validaciones_por_recurso(id);


--
-- Name: ae_textos_agenda fk96b86f5fe4ef2a07; Type: FK CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_textos_agenda
    ADD CONSTRAINT fk96b86f5fe4ef2a07 FOREIGN KEY (aeag_id) REFERENCES empresa3.ae_agendas(id);


--
-- Name: ae_validaciones_por_recurso fk9e323ab1104398e1; Type: FK CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_validaciones_por_recurso
    ADD CONSTRAINT fk9e323ab1104398e1 FOREIGN KEY (aere_id) REFERENCES empresa3.ae_recursos(id);


--
-- Name: ae_validaciones_por_recurso fk9e323ab123ebf200; Type: FK CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_validaciones_por_recurso
    ADD CONSTRAINT fk9e323ab123ebf200 FOREIGN KEY (aeva_id) REFERENCES empresa3.ae_validaciones(id);


--
-- Name: ae_datos_reserva fk9ecc9f5911242882; Type: FK CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_datos_reserva
    ADD CONSTRAINT fk9ecc9f5911242882 FOREIGN KEY (aers_id) REFERENCES empresa3.ae_reservas(id);


--
-- Name: ae_datos_reserva fk9ecc9f5936760caf; Type: FK CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_datos_reserva
    ADD CONSTRAINT fk9ecc9f5936760caf FOREIGN KEY (aeds_id) REFERENCES empresa3.ae_datos_a_solicitar(id);


--
-- Name: ae_llamadas fka0da2cfc104398e1; Type: FK CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_llamadas
    ADD CONSTRAINT fka0da2cfc104398e1 FOREIGN KEY (aere_id) REFERENCES empresa3.ae_recursos(id);


--
-- Name: ae_llamadas fka0da2cfc11242882; Type: FK CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_llamadas
    ADD CONSTRAINT fka0da2cfc11242882 FOREIGN KEY (aers_id) REFERENCES empresa3.ae_reservas(id);


--
-- Name: ae_recursos fka1b7fd05e4ef2a07; Type: FK CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_recursos
    ADD CONSTRAINT fka1b7fd05e4ef2a07 FOREIGN KEY (aeag_id) REFERENCES empresa3.ae_agendas(id);


--
-- Name: ae_serv_autocomp_por_dato fkacfc169736760caf; Type: FK CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_serv_autocomp_por_dato
    ADD CONSTRAINT fkacfc169736760caf FOREIGN KEY (aeds_id) REFERENCES empresa3.ae_datos_a_solicitar(id);


--
-- Name: ae_serv_autocomp_por_dato fkacfc1697bcb2c28e; Type: FK CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_serv_autocomp_por_dato
    ADD CONSTRAINT fkacfc1697bcb2c28e FOREIGN KEY (aesr_id) REFERENCES empresa3.ae_servicio_por_recurso(id);


--
-- Name: ae_acciones_por_recurso fkade6372e104398e1; Type: FK CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_acciones_por_recurso
    ADD CONSTRAINT fkade6372e104398e1 FOREIGN KEY (aere_id) REFERENCES empresa3.ae_recursos(id);


--
-- Name: ae_acciones_por_recurso fkade6372ee4b40066; Type: FK CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_acciones_por_recurso
    ADD CONSTRAINT fkade6372ee4b40066 FOREIGN KEY (aeac_id) REFERENCES empresa3.ae_acciones(id);


--
-- Name: ae_parametros_validacion fkc717af5423ebf200; Type: FK CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_parametros_validacion
    ADD CONSTRAINT fkc717af5423ebf200 FOREIGN KEY (aeva_id) REFERENCES empresa3.ae_validaciones(id);


--
-- Name: ae_dias_del_mes fkd1fddf1a9a9bb7b2; Type: FK CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_dias_del_mes
    ADD CONSTRAINT fkd1fddf1a9a9bb7b2 FOREIGN KEY (aepl_id) REFERENCES empresa3.ae_plantillas(id);


--
-- Name: ae_servicio_por_recurso fkd75adbaf104398e1; Type: FK CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_servicio_por_recurso
    ADD CONSTRAINT fkd75adbaf104398e1 FOREIGN KEY (aere_id) REFERENCES empresa3.ae_recursos(id);


--
-- Name: ae_servicio_por_recurso fkd75adbafcc9035ed; Type: FK CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_servicio_por_recurso
    ADD CONSTRAINT fkd75adbafcc9035ed FOREIGN KEY (aeserv_id) REFERENCES empresa3.ae_serv_autocompletar(id);


--
-- Name: ae_atencion fkd841909c11242882; Type: FK CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_atencion
    ADD CONSTRAINT fkd841909c11242882 FOREIGN KEY (aers_id) REFERENCES empresa3.ae_reservas(id);


--
-- Name: ae_anios fke2ce44e59a9bb7b2; Type: FK CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_anios
    ADD CONSTRAINT fke2ce44e59a9bb7b2 FOREIGN KEY (aepl_id) REFERENCES empresa3.ae_plantillas(id);


--
-- Name: ae_meses fke3736bee9a9bb7b2; Type: FK CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_meses
    ADD CONSTRAINT fke3736bee9a9bb7b2 FOREIGN KEY (aepl_id) REFERENCES empresa3.ae_plantillas(id);


--
-- Name: ae_plantillas fkf9c6590b104398e1; Type: FK CONSTRAINT; Schema: empresa3; Owner: sae
--

ALTER TABLE ONLY empresa3.ae_plantillas
    ADD CONSTRAINT fkf9c6590b104398e1 FOREIGN KEY (aere_id) REFERENCES empresa3.ae_recursos(id);


--
-- Name: ae_acciones_miperfil_recurso accion_recurso_fkey; Type: FK CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_acciones_miperfil_recurso
    ADD CONSTRAINT accion_recurso_fkey FOREIGN KEY (recurso_id) REFERENCES empresa4.ae_recursos(id);


--
-- Name: ae_reservas ae_reservas_token; Type: FK CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_reservas
    ADD CONSTRAINT ae_reservas_token FOREIGN KEY (aetr_id) REFERENCES empresa4.ae_tokens_reservas(id);


--
-- Name: ae_tokens_reservas ae_tokens_reservas_recurso; Type: FK CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_tokens_reservas
    ADD CONSTRAINT ae_tokens_reservas_recurso FOREIGN KEY (aere_id) REFERENCES empresa4.ae_recursos(id);


--
-- Name: ae_disponibilidades fk1c09bf24104398e1; Type: FK CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_disponibilidades
    ADD CONSTRAINT fk1c09bf24104398e1 FOREIGN KEY (aere_id) REFERENCES empresa4.ae_recursos(id);


--
-- Name: ae_disponibilidades fk1c09bf249a9bb7b2; Type: FK CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_disponibilidades
    ADD CONSTRAINT fk1c09bf249a9bb7b2 FOREIGN KEY (aepl_id) REFERENCES empresa4.ae_plantillas(id);


--
-- Name: ae_dias_semana fk28a15dc69a9bb7b2; Type: FK CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_dias_semana
    ADD CONSTRAINT fk28a15dc69a9bb7b2 FOREIGN KEY (aepl_id) REFERENCES empresa4.ae_plantillas(id);


--
-- Name: ae_agrupaciones_datos fk3360aa44104398e1; Type: FK CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_agrupaciones_datos
    ADD CONSTRAINT fk3360aa44104398e1 FOREIGN KEY (aere_id) REFERENCES empresa4.ae_recursos(id);


--
-- Name: ae_datos_a_solicitar fk3ce7cc09104398e1; Type: FK CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_datos_a_solicitar
    ADD CONSTRAINT fk3ce7cc09104398e1 FOREIGN KEY (aere_id) REFERENCES empresa4.ae_recursos(id);


--
-- Name: ae_datos_a_solicitar fk3ce7cc091876ae95; Type: FK CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_datos_a_solicitar
    ADD CONSTRAINT fk3ce7cc091876ae95 FOREIGN KEY (aead_id) REFERENCES empresa4.ae_agrupaciones_datos(id);


--
-- Name: ae_parametros_autocompletar fk3e0b63a4cc9035ed; Type: FK CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_parametros_autocompletar
    ADD CONSTRAINT fk3e0b63a4cc9035ed FOREIGN KEY (aeserv_id) REFERENCES empresa4.ae_serv_autocompletar(id);


--
-- Name: ae_constante_validacion fk3f09314323ebf200; Type: FK CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_constante_validacion
    ADD CONSTRAINT fk3f09314323ebf200 FOREIGN KEY (aeva_id) REFERENCES empresa4.ae_validaciones(id);


--
-- Name: ae_valores_del_dato fk42202b5436760caf; Type: FK CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_valores_del_dato
    ADD CONSTRAINT fk42202b5436760caf FOREIGN KEY (aeds_id) REFERENCES empresa4.ae_datos_a_solicitar(id);


--
-- Name: ae_parametros_accion fk4da30a11e4b40066; Type: FK CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_parametros_accion
    ADD CONSTRAINT fk4da30a11e4b40066 FOREIGN KEY (aeac_id) REFERENCES empresa4.ae_acciones(id);


--
-- Name: ae_datos_del_recurso fk5ff42436104398e1; Type: FK CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_datos_del_recurso
    ADD CONSTRAINT fk5ff42436104398e1 FOREIGN KEY (aere_id) REFERENCES empresa4.ae_recursos(id);


--
-- Name: ae_validaciones_por_dato fk66d0a85036760caf; Type: FK CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_validaciones_por_dato
    ADD CONSTRAINT fk66d0a85036760caf FOREIGN KEY (aeds_id) REFERENCES empresa4.ae_datos_a_solicitar(id);


--
-- Name: ae_validaciones_por_dato fk66d0a8508d2f46a5; Type: FK CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_validaciones_por_dato
    ADD CONSTRAINT fk66d0a8508d2f46a5 FOREIGN KEY (aevr_id) REFERENCES empresa4.ae_validaciones_por_recurso(id);


--
-- Name: ae_acciones_por_dato fk6e5144f336760caf; Type: FK CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_acciones_por_dato
    ADD CONSTRAINT fk6e5144f336760caf FOREIGN KEY (aeds_id) REFERENCES empresa4.ae_datos_a_solicitar(id);


--
-- Name: ae_acciones_por_dato fk6e5144f362f9440d; Type: FK CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_acciones_por_dato
    ADD CONSTRAINT fk6e5144f362f9440d FOREIGN KEY (aear_id) REFERENCES empresa4.ae_acciones_por_recurso(id);


--
-- Name: ae_reservas_disponibilidades fk79b9a11211242882; Type: FK CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_reservas_disponibilidades
    ADD CONSTRAINT fk79b9a11211242882 FOREIGN KEY (aers_id) REFERENCES empresa4.ae_reservas(id);


--
-- Name: ae_reservas_disponibilidades fk79b9a112406004b7; Type: FK CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_reservas_disponibilidades
    ADD CONSTRAINT fk79b9a112406004b7 FOREIGN KEY (aedi_id) REFERENCES empresa4.ae_disponibilidades(id);


--
-- Name: ae_valor_constante_val_rec fk8a30e71e8d2f46a5; Type: FK CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_valor_constante_val_rec
    ADD CONSTRAINT fk8a30e71e8d2f46a5 FOREIGN KEY (aevr_id) REFERENCES empresa4.ae_validaciones_por_recurso(id);


--
-- Name: ae_textos_agenda fk96b86f5fe4ef2a07; Type: FK CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_textos_agenda
    ADD CONSTRAINT fk96b86f5fe4ef2a07 FOREIGN KEY (aeag_id) REFERENCES empresa4.ae_agendas(id);


--
-- Name: ae_validaciones_por_recurso fk9e323ab1104398e1; Type: FK CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_validaciones_por_recurso
    ADD CONSTRAINT fk9e323ab1104398e1 FOREIGN KEY (aere_id) REFERENCES empresa4.ae_recursos(id);


--
-- Name: ae_validaciones_por_recurso fk9e323ab123ebf200; Type: FK CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_validaciones_por_recurso
    ADD CONSTRAINT fk9e323ab123ebf200 FOREIGN KEY (aeva_id) REFERENCES empresa4.ae_validaciones(id);


--
-- Name: ae_datos_reserva fk9ecc9f5911242882; Type: FK CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_datos_reserva
    ADD CONSTRAINT fk9ecc9f5911242882 FOREIGN KEY (aers_id) REFERENCES empresa4.ae_reservas(id);


--
-- Name: ae_datos_reserva fk9ecc9f5936760caf; Type: FK CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_datos_reserva
    ADD CONSTRAINT fk9ecc9f5936760caf FOREIGN KEY (aeds_id) REFERENCES empresa4.ae_datos_a_solicitar(id);


--
-- Name: ae_llamadas fka0da2cfc104398e1; Type: FK CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_llamadas
    ADD CONSTRAINT fka0da2cfc104398e1 FOREIGN KEY (aere_id) REFERENCES empresa4.ae_recursos(id);


--
-- Name: ae_llamadas fka0da2cfc11242882; Type: FK CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_llamadas
    ADD CONSTRAINT fka0da2cfc11242882 FOREIGN KEY (aers_id) REFERENCES empresa4.ae_reservas(id);


--
-- Name: ae_recursos fka1b7fd05e4ef2a07; Type: FK CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_recursos
    ADD CONSTRAINT fka1b7fd05e4ef2a07 FOREIGN KEY (aeag_id) REFERENCES empresa4.ae_agendas(id);


--
-- Name: ae_serv_autocomp_por_dato fkacfc169736760caf; Type: FK CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_serv_autocomp_por_dato
    ADD CONSTRAINT fkacfc169736760caf FOREIGN KEY (aeds_id) REFERENCES empresa4.ae_datos_a_solicitar(id);


--
-- Name: ae_serv_autocomp_por_dato fkacfc1697bcb2c28e; Type: FK CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_serv_autocomp_por_dato
    ADD CONSTRAINT fkacfc1697bcb2c28e FOREIGN KEY (aesr_id) REFERENCES empresa4.ae_servicio_por_recurso(id);


--
-- Name: ae_acciones_por_recurso fkade6372e104398e1; Type: FK CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_acciones_por_recurso
    ADD CONSTRAINT fkade6372e104398e1 FOREIGN KEY (aere_id) REFERENCES empresa4.ae_recursos(id);


--
-- Name: ae_acciones_por_recurso fkade6372ee4b40066; Type: FK CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_acciones_por_recurso
    ADD CONSTRAINT fkade6372ee4b40066 FOREIGN KEY (aeac_id) REFERENCES empresa4.ae_acciones(id);


--
-- Name: ae_parametros_validacion fkc717af5423ebf200; Type: FK CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_parametros_validacion
    ADD CONSTRAINT fkc717af5423ebf200 FOREIGN KEY (aeva_id) REFERENCES empresa4.ae_validaciones(id);


--
-- Name: ae_dias_del_mes fkd1fddf1a9a9bb7b2; Type: FK CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_dias_del_mes
    ADD CONSTRAINT fkd1fddf1a9a9bb7b2 FOREIGN KEY (aepl_id) REFERENCES empresa4.ae_plantillas(id);


--
-- Name: ae_servicio_por_recurso fkd75adbaf104398e1; Type: FK CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_servicio_por_recurso
    ADD CONSTRAINT fkd75adbaf104398e1 FOREIGN KEY (aere_id) REFERENCES empresa4.ae_recursos(id);


--
-- Name: ae_servicio_por_recurso fkd75adbafcc9035ed; Type: FK CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_servicio_por_recurso
    ADD CONSTRAINT fkd75adbafcc9035ed FOREIGN KEY (aeserv_id) REFERENCES empresa4.ae_serv_autocompletar(id);


--
-- Name: ae_atencion fkd841909c11242882; Type: FK CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_atencion
    ADD CONSTRAINT fkd841909c11242882 FOREIGN KEY (aers_id) REFERENCES empresa4.ae_reservas(id);


--
-- Name: ae_anios fke2ce44e59a9bb7b2; Type: FK CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_anios
    ADD CONSTRAINT fke2ce44e59a9bb7b2 FOREIGN KEY (aepl_id) REFERENCES empresa4.ae_plantillas(id);


--
-- Name: ae_meses fke3736bee9a9bb7b2; Type: FK CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_meses
    ADD CONSTRAINT fke3736bee9a9bb7b2 FOREIGN KEY (aepl_id) REFERENCES empresa4.ae_plantillas(id);


--
-- Name: ae_plantillas fkf9c6590b104398e1; Type: FK CONSTRAINT; Schema: empresa4; Owner: sae
--

ALTER TABLE ONLY empresa4.ae_plantillas
    ADD CONSTRAINT fkf9c6590b104398e1 FOREIGN KEY (aere_id) REFERENCES empresa4.ae_recursos(id);


--
-- Name: ae_acciones_miperfil_recurso accion_recurso_fkey; Type: FK CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_acciones_miperfil_recurso
    ADD CONSTRAINT accion_recurso_fkey FOREIGN KEY (recurso_id) REFERENCES empresa5.ae_recursos(id);


--
-- Name: ae_reservas ae_reservas_token; Type: FK CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_reservas
    ADD CONSTRAINT ae_reservas_token FOREIGN KEY (aetr_id) REFERENCES empresa5.ae_tokens_reservas(id);


--
-- Name: ae_tokens_reservas ae_tokens_reservas_recurso; Type: FK CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_tokens_reservas
    ADD CONSTRAINT ae_tokens_reservas_recurso FOREIGN KEY (aere_id) REFERENCES empresa5.ae_recursos(id);


--
-- Name: ae_disponibilidades fk1c09bf24104398e1; Type: FK CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_disponibilidades
    ADD CONSTRAINT fk1c09bf24104398e1 FOREIGN KEY (aere_id) REFERENCES empresa5.ae_recursos(id);


--
-- Name: ae_disponibilidades fk1c09bf249a9bb7b2; Type: FK CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_disponibilidades
    ADD CONSTRAINT fk1c09bf249a9bb7b2 FOREIGN KEY (aepl_id) REFERENCES empresa5.ae_plantillas(id);


--
-- Name: ae_dias_semana fk28a15dc69a9bb7b2; Type: FK CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_dias_semana
    ADD CONSTRAINT fk28a15dc69a9bb7b2 FOREIGN KEY (aepl_id) REFERENCES empresa5.ae_plantillas(id);


--
-- Name: ae_agrupaciones_datos fk3360aa44104398e1; Type: FK CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_agrupaciones_datos
    ADD CONSTRAINT fk3360aa44104398e1 FOREIGN KEY (aere_id) REFERENCES empresa5.ae_recursos(id);


--
-- Name: ae_datos_a_solicitar fk3ce7cc09104398e1; Type: FK CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_datos_a_solicitar
    ADD CONSTRAINT fk3ce7cc09104398e1 FOREIGN KEY (aere_id) REFERENCES empresa5.ae_recursos(id);


--
-- Name: ae_datos_a_solicitar fk3ce7cc091876ae95; Type: FK CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_datos_a_solicitar
    ADD CONSTRAINT fk3ce7cc091876ae95 FOREIGN KEY (aead_id) REFERENCES empresa5.ae_agrupaciones_datos(id);


--
-- Name: ae_parametros_autocompletar fk3e0b63a4cc9035ed; Type: FK CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_parametros_autocompletar
    ADD CONSTRAINT fk3e0b63a4cc9035ed FOREIGN KEY (aeserv_id) REFERENCES empresa5.ae_serv_autocompletar(id);


--
-- Name: ae_constante_validacion fk3f09314323ebf200; Type: FK CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_constante_validacion
    ADD CONSTRAINT fk3f09314323ebf200 FOREIGN KEY (aeva_id) REFERENCES empresa5.ae_validaciones(id);


--
-- Name: ae_valores_del_dato fk42202b5436760caf; Type: FK CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_valores_del_dato
    ADD CONSTRAINT fk42202b5436760caf FOREIGN KEY (aeds_id) REFERENCES empresa5.ae_datos_a_solicitar(id);


--
-- Name: ae_parametros_accion fk4da30a11e4b40066; Type: FK CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_parametros_accion
    ADD CONSTRAINT fk4da30a11e4b40066 FOREIGN KEY (aeac_id) REFERENCES empresa5.ae_acciones(id);


--
-- Name: ae_datos_del_recurso fk5ff42436104398e1; Type: FK CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_datos_del_recurso
    ADD CONSTRAINT fk5ff42436104398e1 FOREIGN KEY (aere_id) REFERENCES empresa5.ae_recursos(id);


--
-- Name: ae_validaciones_por_dato fk66d0a85036760caf; Type: FK CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_validaciones_por_dato
    ADD CONSTRAINT fk66d0a85036760caf FOREIGN KEY (aeds_id) REFERENCES empresa5.ae_datos_a_solicitar(id);


--
-- Name: ae_validaciones_por_dato fk66d0a8508d2f46a5; Type: FK CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_validaciones_por_dato
    ADD CONSTRAINT fk66d0a8508d2f46a5 FOREIGN KEY (aevr_id) REFERENCES empresa5.ae_validaciones_por_recurso(id);


--
-- Name: ae_acciones_por_dato fk6e5144f336760caf; Type: FK CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_acciones_por_dato
    ADD CONSTRAINT fk6e5144f336760caf FOREIGN KEY (aeds_id) REFERENCES empresa5.ae_datos_a_solicitar(id);


--
-- Name: ae_acciones_por_dato fk6e5144f362f9440d; Type: FK CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_acciones_por_dato
    ADD CONSTRAINT fk6e5144f362f9440d FOREIGN KEY (aear_id) REFERENCES empresa5.ae_acciones_por_recurso(id);


--
-- Name: ae_reservas_disponibilidades fk79b9a11211242882; Type: FK CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_reservas_disponibilidades
    ADD CONSTRAINT fk79b9a11211242882 FOREIGN KEY (aers_id) REFERENCES empresa5.ae_reservas(id);


--
-- Name: ae_reservas_disponibilidades fk79b9a112406004b7; Type: FK CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_reservas_disponibilidades
    ADD CONSTRAINT fk79b9a112406004b7 FOREIGN KEY (aedi_id) REFERENCES empresa5.ae_disponibilidades(id);


--
-- Name: ae_valor_constante_val_rec fk8a30e71e8d2f46a5; Type: FK CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_valor_constante_val_rec
    ADD CONSTRAINT fk8a30e71e8d2f46a5 FOREIGN KEY (aevr_id) REFERENCES empresa5.ae_validaciones_por_recurso(id);


--
-- Name: ae_textos_agenda fk96b86f5fe4ef2a07; Type: FK CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_textos_agenda
    ADD CONSTRAINT fk96b86f5fe4ef2a07 FOREIGN KEY (aeag_id) REFERENCES empresa5.ae_agendas(id);


--
-- Name: ae_validaciones_por_recurso fk9e323ab1104398e1; Type: FK CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_validaciones_por_recurso
    ADD CONSTRAINT fk9e323ab1104398e1 FOREIGN KEY (aere_id) REFERENCES empresa5.ae_recursos(id);


--
-- Name: ae_validaciones_por_recurso fk9e323ab123ebf200; Type: FK CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_validaciones_por_recurso
    ADD CONSTRAINT fk9e323ab123ebf200 FOREIGN KEY (aeva_id) REFERENCES empresa5.ae_validaciones(id);


--
-- Name: ae_datos_reserva fk9ecc9f5911242882; Type: FK CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_datos_reserva
    ADD CONSTRAINT fk9ecc9f5911242882 FOREIGN KEY (aers_id) REFERENCES empresa5.ae_reservas(id);


--
-- Name: ae_datos_reserva fk9ecc9f5936760caf; Type: FK CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_datos_reserva
    ADD CONSTRAINT fk9ecc9f5936760caf FOREIGN KEY (aeds_id) REFERENCES empresa5.ae_datos_a_solicitar(id);


--
-- Name: ae_llamadas fka0da2cfc104398e1; Type: FK CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_llamadas
    ADD CONSTRAINT fka0da2cfc104398e1 FOREIGN KEY (aere_id) REFERENCES empresa5.ae_recursos(id);


--
-- Name: ae_llamadas fka0da2cfc11242882; Type: FK CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_llamadas
    ADD CONSTRAINT fka0da2cfc11242882 FOREIGN KEY (aers_id) REFERENCES empresa5.ae_reservas(id);


--
-- Name: ae_recursos fka1b7fd05e4ef2a07; Type: FK CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_recursos
    ADD CONSTRAINT fka1b7fd05e4ef2a07 FOREIGN KEY (aeag_id) REFERENCES empresa5.ae_agendas(id);


--
-- Name: ae_serv_autocomp_por_dato fkacfc169736760caf; Type: FK CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_serv_autocomp_por_dato
    ADD CONSTRAINT fkacfc169736760caf FOREIGN KEY (aeds_id) REFERENCES empresa5.ae_datos_a_solicitar(id);


--
-- Name: ae_serv_autocomp_por_dato fkacfc1697bcb2c28e; Type: FK CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_serv_autocomp_por_dato
    ADD CONSTRAINT fkacfc1697bcb2c28e FOREIGN KEY (aesr_id) REFERENCES empresa5.ae_servicio_por_recurso(id);


--
-- Name: ae_acciones_por_recurso fkade6372e104398e1; Type: FK CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_acciones_por_recurso
    ADD CONSTRAINT fkade6372e104398e1 FOREIGN KEY (aere_id) REFERENCES empresa5.ae_recursos(id);


--
-- Name: ae_acciones_por_recurso fkade6372ee4b40066; Type: FK CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_acciones_por_recurso
    ADD CONSTRAINT fkade6372ee4b40066 FOREIGN KEY (aeac_id) REFERENCES empresa5.ae_acciones(id);


--
-- Name: ae_parametros_validacion fkc717af5423ebf200; Type: FK CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_parametros_validacion
    ADD CONSTRAINT fkc717af5423ebf200 FOREIGN KEY (aeva_id) REFERENCES empresa5.ae_validaciones(id);


--
-- Name: ae_dias_del_mes fkd1fddf1a9a9bb7b2; Type: FK CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_dias_del_mes
    ADD CONSTRAINT fkd1fddf1a9a9bb7b2 FOREIGN KEY (aepl_id) REFERENCES empresa5.ae_plantillas(id);


--
-- Name: ae_servicio_por_recurso fkd75adbaf104398e1; Type: FK CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_servicio_por_recurso
    ADD CONSTRAINT fkd75adbaf104398e1 FOREIGN KEY (aere_id) REFERENCES empresa5.ae_recursos(id);


--
-- Name: ae_servicio_por_recurso fkd75adbafcc9035ed; Type: FK CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_servicio_por_recurso
    ADD CONSTRAINT fkd75adbafcc9035ed FOREIGN KEY (aeserv_id) REFERENCES empresa5.ae_serv_autocompletar(id);


--
-- Name: ae_atencion fkd841909c11242882; Type: FK CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_atencion
    ADD CONSTRAINT fkd841909c11242882 FOREIGN KEY (aers_id) REFERENCES empresa5.ae_reservas(id);


--
-- Name: ae_anios fke2ce44e59a9bb7b2; Type: FK CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_anios
    ADD CONSTRAINT fke2ce44e59a9bb7b2 FOREIGN KEY (aepl_id) REFERENCES empresa5.ae_plantillas(id);


--
-- Name: ae_meses fke3736bee9a9bb7b2; Type: FK CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_meses
    ADD CONSTRAINT fke3736bee9a9bb7b2 FOREIGN KEY (aepl_id) REFERENCES empresa5.ae_plantillas(id);


--
-- Name: ae_plantillas fkf9c6590b104398e1; Type: FK CONSTRAINT; Schema: empresa5; Owner: sae
--

ALTER TABLE ONLY empresa5.ae_plantillas
    ADD CONSTRAINT fkf9c6590b104398e1 FOREIGN KEY (aere_id) REFERENCES empresa5.ae_recursos(id);


--
-- Name: SCHEMA empresa1; Type: ACL; Schema: -; Owner: sae
--

GRANT ALL ON SCHEMA empresa1 TO postgres;
GRANT ALL ON SCHEMA empresa1 TO PUBLIC;


--
-- Name: SCHEMA empresa2; Type: ACL; Schema: -; Owner: sae
--

GRANT ALL ON SCHEMA empresa2 TO postgres;
GRANT ALL ON SCHEMA empresa2 TO PUBLIC;


--
-- Name: SCHEMA empresa3; Type: ACL; Schema: -; Owner: sae
--

GRANT ALL ON SCHEMA empresa3 TO postgres;
GRANT ALL ON SCHEMA empresa3 TO PUBLIC;


--
-- Name: SCHEMA empresa4; Type: ACL; Schema: -; Owner: sae
--

GRANT ALL ON SCHEMA empresa4 TO postgres;
GRANT ALL ON SCHEMA empresa4 TO PUBLIC;


--
-- Name: SCHEMA empresa5; Type: ACL; Schema: -; Owner: sae
--

GRANT ALL ON SCHEMA empresa5 TO postgres;
GRANT ALL ON SCHEMA empresa5 TO PUBLIC;


--
-- PostgreSQL database dump complete
--

