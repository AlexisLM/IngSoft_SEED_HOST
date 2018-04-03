--
-- PostgreSQL database dump
--

-- Dumped from database version 10.3
-- Dumped by pg_dump version 10.3

-- Started on 2018-04-01 00:31:56 CST

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 6 (class 2615 OID 16394)
-- Name: fodupa; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA fodupa;


ALTER SCHEMA fodupa OWNER TO postgres;

--
-- TOC entry 1 (class 3079 OID 13014)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 3021 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 2 (class 3079 OID 16510)
-- Name: pgcrypto; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS pgcrypto WITH SCHEMA fodupa;


--
-- TOC entry 3022 (class 0 OID 0)
-- Dependencies: 2
-- Name: EXTENSION pgcrypto; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION pgcrypto IS 'cryptographic functions';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 210 (class 1259 OID 16470)
-- Name: Carrera; Type: TABLE; Schema: fodupa; Owner: postgres
--

CREATE TABLE fodupa."Carrera" (
    id integer NOT NULL,
    nombre character varying(100) NOT NULL,
    CONSTRAINT carrera_nombre_check CHECK (((nombre)::text = ANY (ARRAY[('Actuaria'::character varying)::text, ('Biologia'::character varying)::text, ('Ciencias Ambientales'::character varying)::text, ('Ciencias de la Computacion'::character varying)::text, ('Ciencias de la Tierra'::character varying)::text, ('Fisica'::character varying)::text, ('Fisica Biomedica'::character varying)::text, ('Manejo Sustentable de Zonas Costeras'::character varying)::text, ('Matematicas'::character varying)::text, ('Matematicas Aplicadas'::character varying)::text, ('Neurociencias'::character varying)::text])))
);


ALTER TABLE fodupa."Carrera" OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 16468)
-- Name: Carrera_id_seq; Type: SEQUENCE; Schema: fodupa; Owner: postgres
--

CREATE SEQUENCE fodupa."Carrera_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE fodupa."Carrera_id_seq" OWNER TO postgres;

--
-- TOC entry 3023 (class 0 OID 0)
-- Dependencies: 209
-- Name: Carrera_id_seq; Type: SEQUENCE OWNED BY; Schema: fodupa; Owner: postgres
--

ALTER SEQUENCE fodupa."Carrera_id_seq" OWNED BY fodupa."Carrera".id;


--
-- TOC entry 201 (class 1259 OID 16408)
-- Name: Categoria; Type: TABLE; Schema: fodupa; Owner: postgres
--

CREATE TABLE fodupa."Categoria" (
    id integer NOT NULL,
    descripcion text,
    nombre character varying(50) NOT NULL
);


ALTER TABLE fodupa."Categoria" OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 16406)
-- Name: Categoria_id_seq; Type: SEQUENCE; Schema: fodupa; Owner: postgres
--

CREATE SEQUENCE fodupa."Categoria_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE fodupa."Categoria_id_seq" OWNER TO postgres;

--
-- TOC entry 3024 (class 0 OID 0)
-- Dependencies: 200
-- Name: Categoria_id_seq; Type: SEQUENCE OWNED BY; Schema: fodupa; Owner: postgres
--

ALTER SEQUENCE fodupa."Categoria_id_seq" OWNED BY fodupa."Categoria".id;


--
-- TOC entry 208 (class 1259 OID 16448)
-- Name: Comentario; Type: TABLE; Schema: fodupa; Owner: postgres
--

CREATE TABLE fodupa."Comentario" (
    fecha date NOT NULL,
    "idUsuario" integer NOT NULL,
    "idPregunta" integer NOT NULL,
    contenido text NOT NULL,
    CONSTRAINT comentario_contenido_check CHECK ((contenido ~* '^[\w¿?+-_.*/\\{}()%&#$@|!¡;,:áé\níóúÁÉÍÓÚñÑ"]+$'::text))
);


ALTER TABLE fodupa."Comentario" OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 16446)
-- Name: Comentario_idPregunta_seq; Type: SEQUENCE; Schema: fodupa; Owner: postgres
--

CREATE SEQUENCE fodupa."Comentario_idPregunta_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE fodupa."Comentario_idPregunta_seq" OWNER TO postgres;

--
-- TOC entry 3025 (class 0 OID 0)
-- Dependencies: 207
-- Name: Comentario_idPregunta_seq; Type: SEQUENCE OWNED BY; Schema: fodupa; Owner: postgres
--

ALTER SEQUENCE fodupa."Comentario_idPregunta_seq" OWNED BY fodupa."Comentario"."idPregunta";


--
-- TOC entry 206 (class 1259 OID 16444)
-- Name: Comentario_idUsuario_seq; Type: SEQUENCE; Schema: fodupa; Owner: postgres
--

CREATE SEQUENCE fodupa."Comentario_idUsuario_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE fodupa."Comentario_idUsuario_seq" OWNER TO postgres;

--
-- TOC entry 3026 (class 0 OID 0)
-- Dependencies: 206
-- Name: Comentario_idUsuario_seq; Type: SEQUENCE OWNED BY; Schema: fodupa; Owner: postgres
--

ALTER SEQUENCE fodupa."Comentario_idUsuario_seq" OWNED BY fodupa."Comentario"."idUsuario";


--
-- TOC entry 213 (class 1259 OID 16480)
-- Name: Estudiar; Type: TABLE; Schema: fodupa; Owner: postgres
--

CREATE TABLE fodupa."Estudiar" (
    "idCarrera" integer NOT NULL,
    "idUsuario" integer NOT NULL
);


ALTER TABLE fodupa."Estudiar" OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 16476)
-- Name: Estudiar_idCarrera_seq; Type: SEQUENCE; Schema: fodupa; Owner: postgres
--

CREATE SEQUENCE fodupa."Estudiar_idCarrera_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE fodupa."Estudiar_idCarrera_seq" OWNER TO postgres;

--
-- TOC entry 3027 (class 0 OID 0)
-- Dependencies: 211
-- Name: Estudiar_idCarrera_seq; Type: SEQUENCE OWNED BY; Schema: fodupa; Owner: postgres
--

ALTER SEQUENCE fodupa."Estudiar_idCarrera_seq" OWNED BY fodupa."Estudiar"."idCarrera";


--
-- TOC entry 212 (class 1259 OID 16478)
-- Name: Estudiar_idUsuario_seq; Type: SEQUENCE; Schema: fodupa; Owner: postgres
--

CREATE SEQUENCE fodupa."Estudiar_idUsuario_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE fodupa."Estudiar_idUsuario_seq" OWNER TO postgres;

--
-- TOC entry 3028 (class 0 OID 0)
-- Dependencies: 212
-- Name: Estudiar_idUsuario_seq; Type: SEQUENCE OWNED BY; Schema: fodupa; Owner: postgres
--

ALTER SEQUENCE fodupa."Estudiar_idUsuario_seq" OWNED BY fodupa."Estudiar"."idUsuario";


--
-- TOC entry 205 (class 1259 OID 16423)
-- Name: Pregunta; Type: TABLE; Schema: fodupa; Owner: postgres
--

CREATE TABLE fodupa."Pregunta" (
    id integer NOT NULL,
    titulo character varying(50) NOT NULL,
    descripcion text,
    "idCategoria" integer NOT NULL,
    "idUsuario" integer NOT NULL,
    fecha date NOT NULL,
    CONSTRAINT pregunta_descripcion_check CHECK ((descripcion ~* '^[\w¿?+-_.*/\\{}()%&#$@|!¡;,:áé\níóúÁÉÍÓÚñÑ"]*$'::text)),
    CONSTRAINT pregunta_titulo_check CHECK (((titulo)::text ~* '^[A-Za-záéíóúÁÉÍÓÚñÑ¿?"]{5,}[A-Za-záéíóúÁÉÍÓÚñÑ¿?"]{0,45}$'::text))
);


ALTER TABLE fodupa."Pregunta" OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 16419)
-- Name: Pregunta_idCategoria_seq; Type: SEQUENCE; Schema: fodupa; Owner: postgres
--

CREATE SEQUENCE fodupa."Pregunta_idCategoria_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE fodupa."Pregunta_idCategoria_seq" OWNER TO postgres;

--
-- TOC entry 3029 (class 0 OID 0)
-- Dependencies: 203
-- Name: Pregunta_idCategoria_seq; Type: SEQUENCE OWNED BY; Schema: fodupa; Owner: postgres
--

ALTER SEQUENCE fodupa."Pregunta_idCategoria_seq" OWNED BY fodupa."Pregunta"."idCategoria";


--
-- TOC entry 204 (class 1259 OID 16421)
-- Name: Pregunta_idUsuario_seq; Type: SEQUENCE; Schema: fodupa; Owner: postgres
--

CREATE SEQUENCE fodupa."Pregunta_idUsuario_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE fodupa."Pregunta_idUsuario_seq" OWNER TO postgres;

--
-- TOC entry 3030 (class 0 OID 0)
-- Dependencies: 204
-- Name: Pregunta_idUsuario_seq; Type: SEQUENCE OWNED BY; Schema: fodupa; Owner: postgres
--

ALTER SEQUENCE fodupa."Pregunta_idUsuario_seq" OWNED BY fodupa."Pregunta"."idUsuario";


--
-- TOC entry 202 (class 1259 OID 16417)
-- Name: Pregunta_id_seq; Type: SEQUENCE; Schema: fodupa; Owner: postgres
--

CREATE SEQUENCE fodupa."Pregunta_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE fodupa."Pregunta_id_seq" OWNER TO postgres;

--
-- TOC entry 3031 (class 0 OID 0)
-- Dependencies: 202
-- Name: Pregunta_id_seq; Type: SEQUENCE OWNED BY; Schema: fodupa; Owner: postgres
--

ALTER SEQUENCE fodupa."Pregunta_id_seq" OWNED BY fodupa."Pregunta".id;


--
-- TOC entry 199 (class 1259 OID 16397)
-- Name: Usuario; Type: TABLE; Schema: fodupa; Owner: postgres
--

CREATE TABLE fodupa."Usuario" (
    id integer NOT NULL,
    correo character varying(100) NOT NULL,
    contrasena character varying(20) NOT NULL,
    nombre character varying(50) NOT NULL,
    ap_paterno character varying(50) NOT NULL,
    ap_materno character varying(50) NOT NULL,
    foto bytea,
    CONSTRAINT usuario_apmaterno_check CHECK (((nombre)::text ~* '^[A-Za-záéíóúÁÉÍÓÚñÑ]{3,}[A-Za-záéíóúÁÉÍÓÚñÑ]{0,47}$'::text)),
    CONSTRAINT usuario_appaterno_check CHECK (((ap_paterno)::text ~* '^[A-Za-záéíóúÁÉÍÓÚñÑ]{3,}[A-Za-záéíóúÁÉÍÓÚñÑ]{0,47}$'::text)),
    CONSTRAINT usuario_contrasena_check CHECK (((contrasena)::text ~* '^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$'::text)),
    CONSTRAINT usuario_correo_check CHECK (((correo)::text ~* '^[A-Za-z0-9._%-]+@ciencias.unam.mx$'::text)),
    CONSTRAINT usuario_nombre_check CHECK (((nombre)::text ~* '^[A-Za-záéíóúÁÉÍÓÚñÑ]{3,}[A-Za-záéíóúÁÉÍÓÚñÑ]{0,47}$'::text))
);


ALTER TABLE fodupa."Usuario" OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 16395)
-- Name: Usuario_id_seq; Type: SEQUENCE; Schema: fodupa; Owner: postgres
--

CREATE SEQUENCE fodupa."Usuario_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE fodupa."Usuario_id_seq" OWNER TO postgres;

--
-- TOC entry 3032 (class 0 OID 0)
-- Dependencies: 198
-- Name: Usuario_id_seq; Type: SEQUENCE OWNED BY; Schema: fodupa; Owner: postgres
--

ALTER SEQUENCE fodupa."Usuario_id_seq" OWNED BY fodupa."Usuario".id;


--
-- TOC entry 2855 (class 2604 OID 16473)
-- Name: Carrera id; Type: DEFAULT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa."Carrera" ALTER COLUMN id SET DEFAULT nextval('fodupa."Carrera_id_seq"'::regclass);


--
-- TOC entry 2845 (class 2604 OID 16411)
-- Name: Categoria id; Type: DEFAULT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa."Categoria" ALTER COLUMN id SET DEFAULT nextval('fodupa."Categoria_id_seq"'::regclass);


--
-- TOC entry 2852 (class 2604 OID 16451)
-- Name: Comentario idUsuario; Type: DEFAULT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa."Comentario" ALTER COLUMN "idUsuario" SET DEFAULT nextval('fodupa."Comentario_idUsuario_seq"'::regclass);


--
-- TOC entry 2853 (class 2604 OID 16452)
-- Name: Comentario idPregunta; Type: DEFAULT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa."Comentario" ALTER COLUMN "idPregunta" SET DEFAULT nextval('fodupa."Comentario_idPregunta_seq"'::regclass);


--
-- TOC entry 2857 (class 2604 OID 16483)
-- Name: Estudiar idCarrera; Type: DEFAULT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa."Estudiar" ALTER COLUMN "idCarrera" SET DEFAULT nextval('fodupa."Estudiar_idCarrera_seq"'::regclass);


--
-- TOC entry 2858 (class 2604 OID 16484)
-- Name: Estudiar idUsuario; Type: DEFAULT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa."Estudiar" ALTER COLUMN "idUsuario" SET DEFAULT nextval('fodupa."Estudiar_idUsuario_seq"'::regclass);


--
-- TOC entry 2847 (class 2604 OID 16426)
-- Name: Pregunta id; Type: DEFAULT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa."Pregunta" ALTER COLUMN id SET DEFAULT nextval('fodupa."Pregunta_id_seq"'::regclass);


--
-- TOC entry 2848 (class 2604 OID 16427)
-- Name: Pregunta idCategoria; Type: DEFAULT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa."Pregunta" ALTER COLUMN "idCategoria" SET DEFAULT nextval('fodupa."Pregunta_idCategoria_seq"'::regclass);


--
-- TOC entry 2849 (class 2604 OID 16428)
-- Name: Pregunta idUsuario; Type: DEFAULT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa."Pregunta" ALTER COLUMN "idUsuario" SET DEFAULT nextval('fodupa."Pregunta_idUsuario_seq"'::regclass);


--
-- TOC entry 2839 (class 2604 OID 16400)
-- Name: Usuario id; Type: DEFAULT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa."Usuario" ALTER COLUMN id SET DEFAULT nextval('fodupa."Usuario_id_seq"'::regclass);


--
-- TOC entry 3010 (class 0 OID 16470)
-- Dependencies: 210
-- Data for Name: Carrera; Type: TABLE DATA; Schema: fodupa; Owner: postgres
--

COPY fodupa."Carrera" (id, nombre) FROM stdin;
1	Actuaria
2	Biologia
3	Ciencias Ambientales
4	Ciencias de la Computacion
5	Ciencias de la Tierra
6	Fisica
7	Fisica Biomedica
8	Manejo Sustentable de Zonas Costeras
9	Matematicas
10	Matematicas Aplicadas
11	Neurociencias
\.


--
-- TOC entry 3001 (class 0 OID 16408)
-- Dependencies: 201
-- Data for Name: Categoria; Type: TABLE DATA; Schema: fodupa; Owner: postgres
--

COPY fodupa."Categoria" (id, descripcion, nombre) FROM stdin;
1	\N	Cambio de carrera
2	\N	Carrera simultanea
3	\N	Inscripciones
4	\N	Otros
5	\N	Reinscripciones
6	\N	Servicio Social
7	\N	Tesis
\.


--
-- TOC entry 3008 (class 0 OID 16448)
-- Dependencies: 208
-- Data for Name: Comentario; Type: TABLE DATA; Schema: fodupa; Owner: postgres
--

COPY fodupa."Comentario" (fecha, "idUsuario", "idPregunta", contenido) FROM stdin;
\.


--
-- TOC entry 3013 (class 0 OID 16480)
-- Dependencies: 213
-- Data for Name: Estudiar; Type: TABLE DATA; Schema: fodupa; Owner: postgres
--

COPY fodupa."Estudiar" ("idCarrera", "idUsuario") FROM stdin;
\.


--
-- TOC entry 3005 (class 0 OID 16423)
-- Dependencies: 205
-- Data for Name: Pregunta; Type: TABLE DATA; Schema: fodupa; Owner: postgres
--

COPY fodupa."Pregunta" (id, titulo, descripcion, "idCategoria", "idUsuario", fecha) FROM stdin;
\.


--
-- TOC entry 2999 (class 0 OID 16397)
-- Dependencies: 199
-- Data for Name: Usuario; Type: TABLE DATA; Schema: fodupa; Owner: postgres
--

COPY fodupa."Usuario" (id, correo, contrasena, nombre, ap_paterno, ap_materno, foto) FROM stdin;
\.


--
-- TOC entry 3033 (class 0 OID 0)
-- Dependencies: 209
-- Name: Carrera_id_seq; Type: SEQUENCE SET; Schema: fodupa; Owner: postgres
--

SELECT pg_catalog.setval('fodupa."Carrera_id_seq"', 11, true);


--
-- TOC entry 3034 (class 0 OID 0)
-- Dependencies: 200
-- Name: Categoria_id_seq; Type: SEQUENCE SET; Schema: fodupa; Owner: postgres
--

SELECT pg_catalog.setval('fodupa."Categoria_id_seq"', 7, true);


--
-- TOC entry 3035 (class 0 OID 0)
-- Dependencies: 207
-- Name: Comentario_idPregunta_seq; Type: SEQUENCE SET; Schema: fodupa; Owner: postgres
--

SELECT pg_catalog.setval('fodupa."Comentario_idPregunta_seq"', 1, false);


--
-- TOC entry 3036 (class 0 OID 0)
-- Dependencies: 206
-- Name: Comentario_idUsuario_seq; Type: SEQUENCE SET; Schema: fodupa; Owner: postgres
--

SELECT pg_catalog.setval('fodupa."Comentario_idUsuario_seq"', 1, false);


--
-- TOC entry 3037 (class 0 OID 0)
-- Dependencies: 211
-- Name: Estudiar_idCarrera_seq; Type: SEQUENCE SET; Schema: fodupa; Owner: postgres
--

SELECT pg_catalog.setval('fodupa."Estudiar_idCarrera_seq"', 1, false);


--
-- TOC entry 3038 (class 0 OID 0)
-- Dependencies: 212
-- Name: Estudiar_idUsuario_seq; Type: SEQUENCE SET; Schema: fodupa; Owner: postgres
--

SELECT pg_catalog.setval('fodupa."Estudiar_idUsuario_seq"', 1, false);


--
-- TOC entry 3039 (class 0 OID 0)
-- Dependencies: 203
-- Name: Pregunta_idCategoria_seq; Type: SEQUENCE SET; Schema: fodupa; Owner: postgres
--

SELECT pg_catalog.setval('fodupa."Pregunta_idCategoria_seq"', 1, false);


--
-- TOC entry 3040 (class 0 OID 0)
-- Dependencies: 204
-- Name: Pregunta_idUsuario_seq; Type: SEQUENCE SET; Schema: fodupa; Owner: postgres
--

SELECT pg_catalog.setval('fodupa."Pregunta_idUsuario_seq"', 1, false);


--
-- TOC entry 3041 (class 0 OID 0)
-- Dependencies: 202
-- Name: Pregunta_id_seq; Type: SEQUENCE SET; Schema: fodupa; Owner: postgres
--

SELECT pg_catalog.setval('fodupa."Pregunta_id_seq"', 1, false);


--
-- TOC entry 3042 (class 0 OID 0)
-- Dependencies: 198
-- Name: Usuario_id_seq; Type: SEQUENCE SET; Schema: fodupa; Owner: postgres
--

SELECT pg_catalog.setval('fodupa."Usuario_id_seq"', 1, false);


--
-- TOC entry 2868 (class 2606 OID 16475)
-- Name: Carrera Carrera_pkey; Type: CONSTRAINT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa."Carrera"
    ADD CONSTRAINT "Carrera_pkey" PRIMARY KEY (id);


--
-- TOC entry 2862 (class 2606 OID 16416)
-- Name: Categoria Categoria_pkey; Type: CONSTRAINT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa."Categoria"
    ADD CONSTRAINT "Categoria_pkey" PRIMARY KEY (id);


--
-- TOC entry 2870 (class 2606 OID 16486)
-- Name: Estudiar Estudiar_pkey; Type: CONSTRAINT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa."Estudiar"
    ADD CONSTRAINT "Estudiar_pkey" PRIMARY KEY ("idCarrera", "idUsuario");


--
-- TOC entry 2864 (class 2606 OID 16433)
-- Name: Pregunta Pregunta_pkey; Type: CONSTRAINT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa."Pregunta"
    ADD CONSTRAINT "Pregunta_pkey" PRIMARY KEY (id);


--
-- TOC entry 2860 (class 2606 OID 16405)
-- Name: Usuario Usuario_pkey; Type: CONSTRAINT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa."Usuario"
    ADD CONSTRAINT "Usuario_pkey" PRIMARY KEY (id);


--
-- TOC entry 2846 (class 2606 OID 16499)
-- Name: Categoria categoria_nombre_check; Type: CHECK CONSTRAINT; Schema: fodupa; Owner: postgres
--

ALTER TABLE fodupa."Categoria"
    ADD CONSTRAINT categoria_nombre_check CHECK (((nombre)::text = ANY ((ARRAY['Servicio Social'::character varying, 'Inscripciones'::character varying, 'Reinscripciones'::character varying, 'Cambio de carrera'::character varying, 'Carrera simultanea'::character varying, 'Tesis'::character varying, 'Otros'::character varying])::text[]))) NOT VALID;


--
-- TOC entry 2866 (class 2606 OID 16457)
-- Name: Comentario none; Type: CONSTRAINT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa."Comentario"
    ADD CONSTRAINT "none" PRIMARY KEY (fecha, "idUsuario", "idPregunta");


--
-- TOC entry 2875 (class 2606 OID 16487)
-- Name: Estudiar idCarrera_fk; Type: FK CONSTRAINT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa."Estudiar"
    ADD CONSTRAINT "idCarrera_fk" FOREIGN KEY ("idCarrera") REFERENCES fodupa."Carrera"(id);


--
-- TOC entry 2872 (class 2606 OID 16439)
-- Name: Pregunta idCategoria_fk; Type: FK CONSTRAINT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa."Pregunta"
    ADD CONSTRAINT "idCategoria_fk" FOREIGN KEY ("idCategoria") REFERENCES fodupa."Categoria"(id);


--
-- TOC entry 2874 (class 2606 OID 16463)
-- Name: Comentario idPregunta_fk; Type: FK CONSTRAINT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa."Comentario"
    ADD CONSTRAINT "idPregunta_fk" FOREIGN KEY ("idPregunta") REFERENCES fodupa."Pregunta"(id);


--
-- TOC entry 2871 (class 2606 OID 16434)
-- Name: Pregunta idUsuario_fk; Type: FK CONSTRAINT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa."Pregunta"
    ADD CONSTRAINT "idUsuario_fk" FOREIGN KEY ("idUsuario") REFERENCES fodupa."Usuario"(id);


--
-- TOC entry 2873 (class 2606 OID 16458)
-- Name: Comentario idUsuario_fk; Type: FK CONSTRAINT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa."Comentario"
    ADD CONSTRAINT "idUsuario_fk" FOREIGN KEY ("idUsuario") REFERENCES fodupa."Usuario"(id);


--
-- TOC entry 2876 (class 2606 OID 16492)
-- Name: Estudiar idUsuario_fk; Type: FK CONSTRAINT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa."Estudiar"
    ADD CONSTRAINT "idUsuario_fk" FOREIGN KEY ("idUsuario") REFERENCES fodupa."Usuario"(id);


-- Completed on 2018-04-01 00:31:56 CST

--
-- PostgreSQL database dump complete
--

