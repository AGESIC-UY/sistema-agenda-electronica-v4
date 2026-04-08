--
-- PostgreSQL database dump
--

-- Dumped from database version 13.7 (Debian 13.7-1.pgdg110+1)
-- Dumped by pg_dump version 15.3

-- Started on 2023-12-14 15:41:11

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

DROP DATABASE sae_tenant;
--
-- TOC entry 2991 (class 1262 OID 24715)
-- Name: sae_tenant; Type: DATABASE; Schema: -; Owner: sae
--

CREATE DATABASE sae_tenant WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';


ALTER DATABASE sae_tenant OWNER TO sae;

\connect sae_tenant

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
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: sae
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO sae;

--
-- TOC entry 2992 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: sae
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 200 (class 1259 OID 24716)
-- Name: configuracion; Type: TABLE; Schema: public; Owner: sae
--

CREATE TABLE public.configuracion (
    tenant_id integer NOT NULL,
    dominio text NOT NULL,
    url_conexion_db text NOT NULL,
    email_user text NOT NULL,
    email_password text NOT NULL,
    email_host text NOT NULL,
    email_port integer NOT NULL,
    sso_state text NOT NULL,
    return_path text NOT NULL,
    sso_base_url text NOT NULL,
    sso_client_id text NOT NULL,
    sso_secret text NOT NULL,
    sso_return_url text NOT NULL,
    sso_return_url_sae_publico text NOT NULL,
    sso_home_page text NOT NULL,
    sso_logout_url text NOT NULL,
    sso_redirect_url text NOT NULL,
    sso_redirect_url_sae_publico text NOT NULL,
    ds_jndi_name text NOT NULL,
    type_handler_admin text NOT NULL,
    type_handler_pub text NOT NULL
);


ALTER TABLE public.configuracion OWNER TO sae;

--
-- TOC entry 2985 (class 0 OID 24716)
-- Dependencies: 200
-- Data for Name: configuracion; Type: TABLE DATA; Schema: public; Owner: sae
--

--
-- TOC entry 2853 (class 2606 OID 24723)
-- Name: configuracion configuracion_pkey; Type: CONSTRAINT; Schema: public; Owner: sae
--

ALTER TABLE ONLY public.configuracion
    ADD CONSTRAINT configuracion_pkey PRIMARY KEY (tenant_id);


--
-- TOC entry 2854 (class 1259 OID 24724)
-- Name: idx_dominio; Type: INDEX; Schema: public; Owner: sae
--

CREATE UNIQUE INDEX idx_dominio ON public.configuracion USING btree (dominio);


--
-- TOC entry 2993 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: sae
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2023-12-14 15:41:11

--
-- PostgreSQL database dump complete
--

