--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.17
-- Dumped by pg_dump version 9.4.17
-- Started on 2018-04-08 12:21:03 CDT

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 9 (class 2615 OID 16597)
-- Name: fodupa; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA fodupa;


ALTER SCHEMA fodupa OWNER TO postgres;

--
-- TOC entry 1 (class 3079 OID 11947)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2218 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 2 (class 3079 OID 16598)
-- Name: pgcrypto; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS pgcrypto WITH SCHEMA fodupa;


--
-- TOC entry 2219 (class 0 OID 0)
-- Dependencies: 2
-- Name: EXTENSION pgcrypto; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION pgcrypto IS 'cryptographic functions';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 175 (class 1259 OID 16633)
-- Name: carrera; Type: TABLE; Schema: fodupa; Owner: postgres; Tablespace: 
--

CREATE TABLE fodupa.carrera (
    id integer NOT NULL,
    nombre character varying(100) NOT NULL
);


ALTER TABLE fodupa.carrera OWNER TO postgres;

--
-- TOC entry 176 (class 1259 OID 16637)
-- Name: carrera_id_seq; Type: SEQUENCE; Schema: fodupa; Owner: postgres
--

CREATE SEQUENCE fodupa.carrera_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE fodupa.carrera_id_seq OWNER TO postgres;

--
-- TOC entry 2220 (class 0 OID 0)
-- Dependencies: 176
-- Name: carrera_id_seq; Type: SEQUENCE OWNED BY; Schema: fodupa; Owner: postgres
--

ALTER SEQUENCE fodupa.carrera_id_seq OWNED BY fodupa.carrera.id;


--
-- TOC entry 177 (class 1259 OID 16639)
-- Name: categoria; Type: TABLE; Schema: fodupa; Owner: postgres; Tablespace: 
--

CREATE TABLE fodupa.categoria (
    id integer NOT NULL,
    descripcion text,
    nombre character varying(50) NOT NULL
);


ALTER TABLE fodupa.categoria OWNER TO postgres;

--
-- TOC entry 178 (class 1259 OID 16645)
-- Name: categoria_id_seq; Type: SEQUENCE; Schema: fodupa; Owner: postgres
--

CREATE SEQUENCE fodupa.categoria_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE fodupa.categoria_id_seq OWNER TO postgres;

--
-- TOC entry 2221 (class 0 OID 0)
-- Dependencies: 178
-- Name: categoria_id_seq; Type: SEQUENCE OWNED BY; Schema: fodupa; Owner: postgres
--

ALTER SEQUENCE fodupa.categoria_id_seq OWNED BY fodupa.categoria.id;


--
-- TOC entry 179 (class 1259 OID 16647)
-- Name: comentario; Type: TABLE; Schema: fodupa; Owner: postgres; Tablespace: 
--

CREATE TABLE fodupa.comentario (
    fecha date NOT NULL,
    idusuario integer NOT NULL,
    idpregunta integer NOT NULL,
    contenido text NOT NULL,
    CONSTRAINT comentario_contenido_check CHECK ((contenido ~* '^[\w¿?+-_.*/\{}()%&#$@|!¡;,:áé\níóúÁÉÍÓÚñÑ"]+$'::text))
);


ALTER TABLE fodupa.comentario OWNER TO postgres;

--
-- TOC entry 180 (class 1259 OID 16654)
-- Name: comentario_idpregunta_seq; Type: SEQUENCE; Schema: fodupa; Owner: postgres
--

CREATE SEQUENCE fodupa.comentario_idpregunta_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE fodupa.comentario_idpregunta_seq OWNER TO postgres;

--
-- TOC entry 2222 (class 0 OID 0)
-- Dependencies: 180
-- Name: comentario_idpregunta_seq; Type: SEQUENCE OWNED BY; Schema: fodupa; Owner: postgres
--

ALTER SEQUENCE fodupa.comentario_idpregunta_seq OWNED BY fodupa.comentario.idpregunta;


--
-- TOC entry 181 (class 1259 OID 16656)
-- Name: comentario_idusuario_seq; Type: SEQUENCE; Schema: fodupa; Owner: postgres
--

CREATE SEQUENCE fodupa.comentario_idusuario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE fodupa.comentario_idusuario_seq OWNER TO postgres;

--
-- TOC entry 2223 (class 0 OID 0)
-- Dependencies: 181
-- Name: comentario_idusuario_seq; Type: SEQUENCE OWNED BY; Schema: fodupa; Owner: postgres
--

ALTER SEQUENCE fodupa.comentario_idusuario_seq OWNED BY fodupa.comentario.idusuario;


--
-- TOC entry 182 (class 1259 OID 16658)
-- Name: estudiar; Type: TABLE; Schema: fodupa; Owner: postgres; Tablespace: 
--

CREATE TABLE fodupa.estudiar (
    idcarrera integer NOT NULL,
    idusuario integer NOT NULL
);


ALTER TABLE fodupa.estudiar OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 16661)
-- Name: estudiar_idcarrera_seq; Type: SEQUENCE; Schema: fodupa; Owner: postgres
--

CREATE SEQUENCE fodupa.estudiar_idcarrera_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE fodupa.estudiar_idcarrera_seq OWNER TO postgres;

--
-- TOC entry 2224 (class 0 OID 0)
-- Dependencies: 183
-- Name: estudiar_idcarrera_seq; Type: SEQUENCE OWNED BY; Schema: fodupa; Owner: postgres
--

ALTER SEQUENCE fodupa.estudiar_idcarrera_seq OWNED BY fodupa.estudiar.idcarrera;


--
-- TOC entry 184 (class 1259 OID 16663)
-- Name: estudiar_idusuario_seq; Type: SEQUENCE; Schema: fodupa; Owner: postgres
--

CREATE SEQUENCE fodupa.estudiar_idusuario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE fodupa.estudiar_idusuario_seq OWNER TO postgres;

--
-- TOC entry 2225 (class 0 OID 0)
-- Dependencies: 184
-- Name: estudiar_idusuario_seq; Type: SEQUENCE OWNED BY; Schema: fodupa; Owner: postgres
--

ALTER SEQUENCE fodupa.estudiar_idusuario_seq OWNED BY fodupa.estudiar.idusuario;


--
-- TOC entry 185 (class 1259 OID 16665)
-- Name: pregunta; Type: TABLE; Schema: fodupa; Owner: postgres; Tablespace: 
--

CREATE TABLE fodupa.pregunta (
    id integer NOT NULL,
    titulo character varying(50) NOT NULL,
    descripcion text,
    idcategoria integer NOT NULL,
    idusuario integer NOT NULL,
    fecha date NOT NULL,
    CONSTRAINT pregunta_descripcion_check CHECK ((descripcion ~* '^[A-Za-z0-9\s¿?+-_.*/\{}()%&#$@|!¡;,:áé\níóúÁÉÍÓÚñÑ"]{0,}$'::text)),
    CONSTRAINT pregunta_titulo_check CHECK (((titulo)::text ~* '^[A-Za-záéíóúÁÉÍÓÚñÑ¿?!¡"]{2,}[A-Za-záéíóúÁÉÍÓÚñÑ!¡¿?\s"]{0,48}$'::text))
);


ALTER TABLE fodupa.pregunta OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 16677)
-- Name: pregunta_id_seq; Type: SEQUENCE; Schema: fodupa; Owner: postgres
--

CREATE SEQUENCE fodupa.pregunta_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE fodupa.pregunta_id_seq OWNER TO postgres;

--
-- TOC entry 2226 (class 0 OID 0)
-- Dependencies: 188
-- Name: pregunta_id_seq; Type: SEQUENCE OWNED BY; Schema: fodupa; Owner: postgres
--

ALTER SEQUENCE fodupa.pregunta_id_seq OWNED BY fodupa.pregunta.id;


--
-- TOC entry 186 (class 1259 OID 16673)
-- Name: pregunta_idcategoria_seq; Type: SEQUENCE; Schema: fodupa; Owner: postgres
--

CREATE SEQUENCE fodupa.pregunta_idcategoria_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE fodupa.pregunta_idcategoria_seq OWNER TO postgres;

--
-- TOC entry 2227 (class 0 OID 0)
-- Dependencies: 186
-- Name: pregunta_idcategoria_seq; Type: SEQUENCE OWNED BY; Schema: fodupa; Owner: postgres
--

ALTER SEQUENCE fodupa.pregunta_idcategoria_seq OWNED BY fodupa.pregunta.idcategoria;


--
-- TOC entry 187 (class 1259 OID 16675)
-- Name: pregunta_idusuario_seq; Type: SEQUENCE; Schema: fodupa; Owner: postgres
--

CREATE SEQUENCE fodupa.pregunta_idusuario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE fodupa.pregunta_idusuario_seq OWNER TO postgres;

--
-- TOC entry 2228 (class 0 OID 0)
-- Dependencies: 187
-- Name: pregunta_idusuario_seq; Type: SEQUENCE OWNED BY; Schema: fodupa; Owner: postgres
--

ALTER SEQUENCE fodupa.pregunta_idusuario_seq OWNED BY fodupa.pregunta.idusuario;


--
-- TOC entry 189 (class 1259 OID 16679)
-- Name: usuario; Type: TABLE; Schema: fodupa; Owner: postgres; Tablespace: 
--

CREATE TABLE fodupa.usuario (
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


ALTER TABLE fodupa.usuario OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 16690)
-- Name: usuario_id_seq; Type: SEQUENCE; Schema: fodupa; Owner: postgres
--

CREATE SEQUENCE fodupa.usuario_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE fodupa.usuario_id_seq OWNER TO postgres;

--
-- TOC entry 2229 (class 0 OID 0)
-- Dependencies: 190
-- Name: usuario_id_seq; Type: SEQUENCE OWNED BY; Schema: fodupa; Owner: postgres
--

ALTER SEQUENCE fodupa.usuario_id_seq OWNED BY fodupa.usuario.id;


--
-- TOC entry 2049 (class 2604 OID 16692)
-- Name: id; Type: DEFAULT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa.carrera ALTER COLUMN id SET DEFAULT nextval('fodupa.carrera_id_seq'::regclass);


--
-- TOC entry 2050 (class 2604 OID 16693)
-- Name: id; Type: DEFAULT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa.categoria ALTER COLUMN id SET DEFAULT nextval('fodupa.categoria_id_seq'::regclass);


--
-- TOC entry 2051 (class 2604 OID 16694)
-- Name: idusuario; Type: DEFAULT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa.comentario ALTER COLUMN idusuario SET DEFAULT nextval('fodupa.comentario_idusuario_seq'::regclass);


--
-- TOC entry 2052 (class 2604 OID 16695)
-- Name: idpregunta; Type: DEFAULT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa.comentario ALTER COLUMN idpregunta SET DEFAULT nextval('fodupa.comentario_idpregunta_seq'::regclass);


--
-- TOC entry 2054 (class 2604 OID 16696)
-- Name: idcarrera; Type: DEFAULT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa.estudiar ALTER COLUMN idcarrera SET DEFAULT nextval('fodupa.estudiar_idcarrera_seq'::regclass);


--
-- TOC entry 2055 (class 2604 OID 16697)
-- Name: idusuario; Type: DEFAULT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa.estudiar ALTER COLUMN idusuario SET DEFAULT nextval('fodupa.estudiar_idusuario_seq'::regclass);


--
-- TOC entry 2056 (class 2604 OID 16698)
-- Name: id; Type: DEFAULT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa.pregunta ALTER COLUMN id SET DEFAULT nextval('fodupa.pregunta_id_seq'::regclass);


--
-- TOC entry 2057 (class 2604 OID 16699)
-- Name: idcategoria; Type: DEFAULT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa.pregunta ALTER COLUMN idcategoria SET DEFAULT nextval('fodupa.pregunta_idcategoria_seq'::regclass);


--
-- TOC entry 2058 (class 2604 OID 16700)
-- Name: idusuario; Type: DEFAULT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa.pregunta ALTER COLUMN idusuario SET DEFAULT nextval('fodupa.pregunta_idusuario_seq'::regclass);


--
-- TOC entry 2061 (class 2604 OID 16701)
-- Name: id; Type: DEFAULT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa.usuario ALTER COLUMN id SET DEFAULT nextval('fodupa.usuario_id_seq'::regclass);


--
-- TOC entry 2194 (class 0 OID 16633)
-- Dependencies: 175
-- Data for Name: carrera; Type: TABLE DATA; Schema: fodupa; Owner: postgres
--

COPY fodupa.carrera (id, nombre) FROM stdin;
14	Ciencias Ambientales
16	Ciencias de la Tierra
19	Manejo Sustentable de Zonas Costeras
22	Neurociencias
12	Actuaría
13	Biología
15	Ciencias de la Computación
17	Física
18	Física Biomédica
20	Matemáticas
21	Matemáticas Aplicadas
\.


--
-- TOC entry 2230 (class 0 OID 0)
-- Dependencies: 176
-- Name: carrera_id_seq; Type: SEQUENCE SET; Schema: fodupa; Owner: postgres
--

SELECT pg_catalog.setval('fodupa.carrera_id_seq', 22, true);


--
-- TOC entry 2196 (class 0 OID 16639)
-- Dependencies: 177
-- Data for Name: categoria; Type: TABLE DATA; Schema: fodupa; Owner: postgres
--

COPY fodupa.categoria (id, descripcion, nombre) FROM stdin;
8	\N	Cambio de carrera
10	\N	Inscripciones
11	\N	Otros
12	\N	Reinscripciones
13	\N	Servicio Social
9	\N	Carrera simultánea
14	\N	Tésis
\.


--
-- TOC entry 2231 (class 0 OID 0)
-- Dependencies: 178
-- Name: categoria_id_seq; Type: SEQUENCE SET; Schema: fodupa; Owner: postgres
--

SELECT pg_catalog.setval('fodupa.categoria_id_seq', 14, true);


--
-- TOC entry 2198 (class 0 OID 16647)
-- Dependencies: 179
-- Data for Name: comentario; Type: TABLE DATA; Schema: fodupa; Owner: postgres
--

COPY fodupa.comentario (fecha, idusuario, idpregunta, contenido) FROM stdin;
\.


--
-- TOC entry 2232 (class 0 OID 0)
-- Dependencies: 180
-- Name: comentario_idpregunta_seq; Type: SEQUENCE SET; Schema: fodupa; Owner: postgres
--

SELECT pg_catalog.setval('fodupa.comentario_idpregunta_seq', 1, false);


--
-- TOC entry 2233 (class 0 OID 0)
-- Dependencies: 181
-- Name: comentario_idusuario_seq; Type: SEQUENCE SET; Schema: fodupa; Owner: postgres
--

SELECT pg_catalog.setval('fodupa.comentario_idusuario_seq', 1, false);


--
-- TOC entry 2201 (class 0 OID 16658)
-- Dependencies: 182
-- Data for Name: estudiar; Type: TABLE DATA; Schema: fodupa; Owner: postgres
--

COPY fodupa.estudiar (idcarrera, idusuario) FROM stdin;
\.


--
-- TOC entry 2234 (class 0 OID 0)
-- Dependencies: 183
-- Name: estudiar_idcarrera_seq; Type: SEQUENCE SET; Schema: fodupa; Owner: postgres
--

SELECT pg_catalog.setval('fodupa.estudiar_idcarrera_seq', 1, false);


--
-- TOC entry 2235 (class 0 OID 0)
-- Dependencies: 184
-- Name: estudiar_idusuario_seq; Type: SEQUENCE SET; Schema: fodupa; Owner: postgres
--

SELECT pg_catalog.setval('fodupa.estudiar_idusuario_seq', 1, false);


--
-- TOC entry 2204 (class 0 OID 16665)
-- Dependencies: 185
-- Data for Name: pregunta; Type: TABLE DATA; Schema: fodupa; Owner: postgres
--

COPY fodupa.pregunta (id, titulo, descripcion, idcategoria, idusuario, fecha) FROM stdin;
5	Titulo uno	Detalles	10	1	2018-04-08
7	Titulo dos	Detalles 2	13	1	2018-04-08
8	Titulo tres		11	1	2018-04-08
\.


--
-- TOC entry 2236 (class 0 OID 0)
-- Dependencies: 188
-- Name: pregunta_id_seq; Type: SEQUENCE SET; Schema: fodupa; Owner: postgres
--

SELECT pg_catalog.setval('fodupa.pregunta_id_seq', 8, true);


--
-- TOC entry 2237 (class 0 OID 0)
-- Dependencies: 186
-- Name: pregunta_idcategoria_seq; Type: SEQUENCE SET; Schema: fodupa; Owner: postgres
--

SELECT pg_catalog.setval('fodupa.pregunta_idcategoria_seq', 1, false);


--
-- TOC entry 2238 (class 0 OID 0)
-- Dependencies: 187
-- Name: pregunta_idusuario_seq; Type: SEQUENCE SET; Schema: fodupa; Owner: postgres
--

SELECT pg_catalog.setval('fodupa.pregunta_idusuario_seq', 1, false);


--
-- TOC entry 2208 (class 0 OID 16679)
-- Dependencies: 189
-- Data for Name: usuario; Type: TABLE DATA; Schema: fodupa; Owner: postgres
--

COPY fodupa.usuario (id, correo, contrasena, nombre, ap_paterno, ap_materno, foto) FROM stdin;
1	alexis-blm@ciencias.unam.mx	Contrasena1	Alexis	López	Matías	\N
\.


--
-- TOC entry 2239 (class 0 OID 0)
-- Dependencies: 190
-- Name: usuario_id_seq; Type: SEQUENCE SET; Schema: fodupa; Owner: postgres
--

SELECT pg_catalog.setval('fodupa.usuario_id_seq', 1, false);


--
-- TOC entry 2068 (class 2606 OID 16703)
-- Name: carrera_pkey; Type: CONSTRAINT; Schema: fodupa; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY fodupa.carrera
    ADD CONSTRAINT carrera_pkey PRIMARY KEY (id);


--
-- TOC entry 2070 (class 2606 OID 16705)
-- Name: categoria_pkey; Type: CONSTRAINT; Schema: fodupa; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY fodupa.categoria
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (id);


--
-- TOC entry 2074 (class 2606 OID 16707)
-- Name: estudiar_pkey; Type: CONSTRAINT; Schema: fodupa; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY fodupa.estudiar
    ADD CONSTRAINT estudiar_pkey PRIMARY KEY (idcarrera, idusuario);


--
-- TOC entry 2072 (class 2606 OID 16714)
-- Name: none; Type: CONSTRAINT; Schema: fodupa; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY fodupa.comentario
    ADD CONSTRAINT "none" PRIMARY KEY (fecha, idusuario, idpregunta);


--
-- TOC entry 2076 (class 2606 OID 16709)
-- Name: pregunta_pkey; Type: CONSTRAINT; Schema: fodupa; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY fodupa.pregunta
    ADD CONSTRAINT pregunta_pkey PRIMARY KEY (id);


--
-- TOC entry 2078 (class 2606 OID 16711)
-- Name: usuario_pkey; Type: CONSTRAINT; Schema: fodupa; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY fodupa.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


--
-- TOC entry 2081 (class 2606 OID 16715)
-- Name: idcarrera_fk; Type: FK CONSTRAINT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa.estudiar
    ADD CONSTRAINT idcarrera_fk FOREIGN KEY (idcarrera) REFERENCES fodupa.carrera(id);


--
-- TOC entry 2083 (class 2606 OID 16720)
-- Name: idcategoria_fk; Type: FK CONSTRAINT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa.pregunta
    ADD CONSTRAINT idcategoria_fk FOREIGN KEY (idcategoria) REFERENCES fodupa.categoria(id);


--
-- TOC entry 2079 (class 2606 OID 16725)
-- Name: idpregunta_fk; Type: FK CONSTRAINT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa.comentario
    ADD CONSTRAINT idpregunta_fk FOREIGN KEY (idpregunta) REFERENCES fodupa.pregunta(id);


--
-- TOC entry 2084 (class 2606 OID 16730)
-- Name: idusuario_fk; Type: FK CONSTRAINT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa.pregunta
    ADD CONSTRAINT idusuario_fk FOREIGN KEY (idusuario) REFERENCES fodupa.usuario(id);


--
-- TOC entry 2080 (class 2606 OID 16735)
-- Name: idusuario_fk; Type: FK CONSTRAINT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa.comentario
    ADD CONSTRAINT idusuario_fk FOREIGN KEY (idusuario) REFERENCES fodupa.usuario(id);


--
-- TOC entry 2082 (class 2606 OID 16740)
-- Name: idusuario_fk; Type: FK CONSTRAINT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa.estudiar
    ADD CONSTRAINT idusuario_fk FOREIGN KEY (idusuario) REFERENCES fodupa.usuario(id);


--
-- TOC entry 2217 (class 0 OID 0)
-- Dependencies: 7
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2018-04-08 12:21:03 CDT

--
-- PostgreSQL database dump complete
--
