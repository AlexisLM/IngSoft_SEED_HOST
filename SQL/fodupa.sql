--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: fodupa; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA fodupa;


ALTER SCHEMA fodupa OWNER TO postgres;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- Name: pgcrypto; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS pgcrypto WITH SCHEMA fodupa;


--
-- Name: EXTENSION pgcrypto; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION pgcrypto IS 'cryptographic functions';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: carrera; Type: TABLE; Schema: fodupa; Owner: postgres; Tablespace: 
--

CREATE TABLE fodupa.carrera (
    id integer NOT NULL,
    nombre character varying(100) NOT NULL
);


ALTER TABLE fodupa.carrera OWNER TO postgres;

--
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
-- Name: carrera_id_seq; Type: SEQUENCE OWNED BY; Schema: fodupa; Owner: postgres
--

ALTER SEQUENCE fodupa.carrera_id_seq OWNED BY fodupa.carrera.id;


--
-- Name: categoria; Type: TABLE; Schema: fodupa; Owner: postgres; Tablespace: 
--

CREATE TABLE fodupa.categoria (
    id integer NOT NULL,
    descripcion text,
    nombre character varying(50) NOT NULL
);


ALTER TABLE fodupa.categoria OWNER TO postgres;

--
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
-- Name: categoria_id_seq; Type: SEQUENCE OWNED BY; Schema: fodupa; Owner: postgres
--

ALTER SEQUENCE fodupa.categoria_id_seq OWNED BY fodupa.categoria.id;


--
-- Name: comentario; Type: TABLE; Schema: fodupa; Owner: postgres; Tablespace: 
--

CREATE TABLE fodupa.comentario (
    fecha timestamp with time zone NOT NULL,
    idusuario integer NOT NULL,
    idpregunta integer NOT NULL,
    contenido text NOT NULL,
    CONSTRAINT comentario_contenido_check CHECK ((contenido ~* '^[A-Za-z0-9\s¿?+-_.*/\{}()\[\]%&#$@|¬!¡;,:áé\níóúÁÉÍÓÚñÑ"]+$'::text))
);


ALTER TABLE fodupa.comentario OWNER TO postgres;

--
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
-- Name: comentario_idpregunta_seq; Type: SEQUENCE OWNED BY; Schema: fodupa; Owner: postgres
--

ALTER SEQUENCE fodupa.comentario_idpregunta_seq OWNED BY fodupa.comentario.idpregunta;


--
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
-- Name: comentario_idusuario_seq; Type: SEQUENCE OWNED BY; Schema: fodupa; Owner: postgres
--

ALTER SEQUENCE fodupa.comentario_idusuario_seq OWNED BY fodupa.comentario.idusuario;


--
-- Name: estudiar; Type: TABLE; Schema: fodupa; Owner: postgres; Tablespace: 
--

CREATE TABLE fodupa.estudiar (
    idcarrera integer NOT NULL,
    idusuario integer NOT NULL
);


ALTER TABLE fodupa.estudiar OWNER TO postgres;

--
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
-- Name: estudiar_idcarrera_seq; Type: SEQUENCE OWNED BY; Schema: fodupa; Owner: postgres
--

ALTER SEQUENCE fodupa.estudiar_idcarrera_seq OWNED BY fodupa.estudiar.idcarrera;


--
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
-- Name: estudiar_idusuario_seq; Type: SEQUENCE OWNED BY; Schema: fodupa; Owner: postgres
--

ALTER SEQUENCE fodupa.estudiar_idusuario_seq OWNED BY fodupa.estudiar.idusuario;


--
-- Name: pregunta; Type: TABLE; Schema: fodupa; Owner: postgres; Tablespace: 
--

CREATE TABLE fodupa.pregunta (
    id integer NOT NULL,
    titulo character varying(50) NOT NULL,
    descripcion text,
    idcategoria integer NOT NULL,
    idusuario integer NOT NULL,
    fecha date NOT NULL,
    CONSTRAINT pregunta_descripcion_check CHECK ((descripcion ~* '^[A-Za-z0-9\s¿?+-_.*/\{}()\[\]%&#$@|¬!¡;,:áé\níóúÁÉÍÓÚñÑ"]*$'::text)),
    CONSTRAINT pregunta_titulo_check CHECK (((titulo)::text ~* '^[A-Za-z0-9áéíóúÁÉÍÓÚñÑ¿?!¡"]{2}[A-Za-z0-9áéíóúÁÉÍÓÚñÑ!¡¿?\s"]{3,48}$'::text))
);


ALTER TABLE fodupa.pregunta OWNER TO postgres;

--
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
-- Name: pregunta_id_seq; Type: SEQUENCE OWNED BY; Schema: fodupa; Owner: postgres
--

ALTER SEQUENCE fodupa.pregunta_id_seq OWNED BY fodupa.pregunta.id;


--
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
-- Name: pregunta_idcategoria_seq; Type: SEQUENCE OWNED BY; Schema: fodupa; Owner: postgres
--

ALTER SEQUENCE fodupa.pregunta_idcategoria_seq OWNED BY fodupa.pregunta.idcategoria;


--
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
-- Name: pregunta_idusuario_seq; Type: SEQUENCE OWNED BY; Schema: fodupa; Owner: postgres
--

ALTER SEQUENCE fodupa.pregunta_idusuario_seq OWNED BY fodupa.pregunta.idusuario;


--
-- Name: usuario; Type: TABLE; Schema: fodupa; Owner: postgres; Tablespace: 
--
CREATE TABLE fodupa.usuario (
    id integer NOT NULL,
    correo character varying(100) NOT NULL,
    contrasena character varying(64) NOT NULL,
    nombre character varying(50) NOT NULL,
    ap_paterno character varying(50) NOT NULL,
    ap_materno character varying(50) NOT NULL,
    foto bytea,
    token character varying(64) NOT NULL,
    valido boolean NOT NULL,
    CONSTRAINT usuario_apmaterno_check CHECK (((nombre)::text ~* '^[A-Za-záéíóúÁÉÍÓÚñÑ]{3}[A-Za-záéíóúÁÉÍÓÚñÑ ]{0,47}$'::text)),
    CONSTRAINT usuario_appaterno_check CHECK (((ap_paterno)::text ~* '^[A-Za-záéíóúÁÉÍÓÚñÑ]{3}[A-Za-záéíóúÁÉÍÓÚñÑ ]{0,47}$'::text)),
    CONSTRAINT usuario_contrasena_check CHECK (((contrasena)::text ~* '^[A-Za-z\d]{64}$'::text)),
    CONSTRAINT usuario_correo_check CHECK (((correo)::text ~* '^[A-Za-z0-9._-]{1,83}@ciencias.unam.mx$'::text)),
    CONSTRAINT usuario_nombre_check CHECK (((nombre)::text ~* '^[A-Za-záéíóúÁÉÍÓÚñÑ]{3}[A-Za-záéíóúÁÉÍÓÚñÑ ]{0,47}$'::text)),
    CONSTRAINT usuario_correo_unico unique(correo),
    CONSTRAINT usuario_token_check CHECK (((token)::text ~* '^[A-Za-z\d]{64}$'::text))
);

ALTER TABLE fodupa.usuario OWNER TO postgres;

--
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
-- Name: usuario_id_seq; Type: SEQUENCE OWNED BY; Schema: fodupa; Owner: postgres
--

ALTER SEQUENCE fodupa.usuario_id_seq OWNED BY fodupa.usuario.id;


--
-- Name: id; Type: DEFAULT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa.carrera ALTER COLUMN id SET DEFAULT nextval('fodupa.carrera_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa.categoria ALTER COLUMN id SET DEFAULT nextval('fodupa.categoria_id_seq'::regclass);


--
-- Name: idusuario; Type: DEFAULT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa.comentario ALTER COLUMN idusuario SET DEFAULT nextval('fodupa.comentario_idusuario_seq'::regclass);


--
-- Name: idpregunta; Type: DEFAULT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa.comentario ALTER COLUMN idpregunta SET DEFAULT nextval('fodupa.comentario_idpregunta_seq'::regclass);


--
-- Name: idcarrera; Type: DEFAULT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa.estudiar ALTER COLUMN idcarrera SET DEFAULT nextval('fodupa.estudiar_idcarrera_seq'::regclass);


--
-- Name: idusuario; Type: DEFAULT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa.estudiar ALTER COLUMN idusuario SET DEFAULT nextval('fodupa.estudiar_idusuario_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa.pregunta ALTER COLUMN id SET DEFAULT nextval('fodupa.pregunta_id_seq'::regclass);


--
-- Name: idcategoria; Type: DEFAULT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa.pregunta ALTER COLUMN idcategoria SET DEFAULT nextval('fodupa.pregunta_idcategoria_seq'::regclass);


--
-- Name: idusuario; Type: DEFAULT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa.pregunta ALTER COLUMN idusuario SET DEFAULT nextval('fodupa.pregunta_idusuario_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa.usuario ALTER COLUMN id SET DEFAULT nextval('fodupa.usuario_id_seq'::regclass);


--
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
-- Name: carrera_id_seq; Type: SEQUENCE SET; Schema: fodupa; Owner: postgres
--

SELECT pg_catalog.setval('fodupa.carrera_id_seq', 22, true);


--
-- Data for Name: categoria; Type: TABLE DATA; Schema: fodupa; Owner: postgres
--

COPY fodupa.categoria (id, descripcion, nombre) FROM stdin;
8	\N	Cambio de carrera
9	\N	Carrera simultánea
10	\N	Inscripciones
11	\N	Otros
12	\N	Reinscripciones
13	\N	Servicio Social
14	\N	Tésis
\.


--
-- Name: categoria_id_seq; Type: SEQUENCE SET; Schema: fodupa; Owner: postgres
--

SELECT pg_catalog.setval('fodupa.categoria_id_seq', 14, true);


--
-- Data for Name: comentario; Type: TABLE DATA; Schema: fodupa; Owner: postgres
--

COPY fodupa.comentario (fecha, idusuario, idpregunta, contenido) FROM stdin;
\.


--
-- Name: comentario_idpregunta_seq; Type: SEQUENCE SET; Schema: fodupa; Owner: postgres
--

SELECT pg_catalog.setval('fodupa.comentario_idpregunta_seq', 1, false);


--
-- Name: comentario_idusuario_seq; Type: SEQUENCE SET; Schema: fodupa; Owner: postgres
--

SELECT pg_catalog.setval('fodupa.comentario_idusuario_seq', 1, false);


--
-- Data for Name: estudiar; Type: TABLE DATA; Schema: fodupa; Owner: postgres
--

COPY fodupa.estudiar (idcarrera, idusuario) FROM stdin;
\.


--
-- Name: estudiar_idcarrera_seq; Type: SEQUENCE SET; Schema: fodupa; Owner: postgres
--

SELECT pg_catalog.setval('fodupa.estudiar_idcarrera_seq', 1, false);


--
-- Name: estudiar_idusuario_seq; Type: SEQUENCE SET; Schema: fodupa; Owner: postgres
--

SELECT pg_catalog.setval('fodupa.estudiar_idusuario_seq', 1, false);


--
-- Data for Name: pregunta; Type: TABLE DATA; Schema: fodupa; Owner: postgres
--

--COPY fodupa.pregunta (id, titulo, descripcion, idcategoria, idusuario, fecha) FROM stdin;
--5	Titulo uno	Detalles	10	1	2018-04-08
--7	Titulo dos	Detalles 2	13	1	2018-04-08
--8	Titulo tres		11	1	2018-04-08
--\.


--
-- Name: pregunta_id_seq; Type: SEQUENCE SET; Schema: fodupa; Owner: postgres
--

SELECT pg_catalog.setval('fodupa.pregunta_id_seq', 13, true);


--
-- Name: pregunta_idcategoria_seq; Type: SEQUENCE SET; Schema: fodupa; Owner: postgres
--

SELECT pg_catalog.setval('fodupa.pregunta_idcategoria_seq', 1, false);


--
-- Name: pregunta_idusuario_seq; Type: SEQUENCE SET; Schema: fodupa; Owner: postgres
--

SELECT pg_catalog.setval('fodupa.pregunta_idusuario_seq', 1, false);

--
-- Name: usuario_id_seq; Type: SEQUENCE SET; Schema: fodupa; Owner: postgres
--

SELECT pg_catalog.setval('fodupa.usuario_id_seq', 1, false);


--
-- Name: carrera_pkey; Type: CONSTRAINT; Schema: fodupa; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY fodupa.carrera
    ADD CONSTRAINT carrera_pkey PRIMARY KEY (id);


--
-- Name: categoria_pkey; Type: CONSTRAINT; Schema: fodupa; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY fodupa.categoria
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (id);


--
-- Name: estudiar_pkey; Type: CONSTRAINT; Schema: fodupa; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY fodupa.estudiar
    ADD CONSTRAINT estudiar_pkey PRIMARY KEY (idcarrera, idusuario);


--
-- Name: none; Type: CONSTRAINT; Schema: fodupa; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY fodupa.comentario
    ADD CONSTRAINT "none" PRIMARY KEY (fecha, idusuario, idpregunta);


--
-- Name: pregunta_pkey; Type: CONSTRAINT; Schema: fodupa; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY fodupa.pregunta
    ADD CONSTRAINT pregunta_pkey PRIMARY KEY (id);


--
-- Name: usuario_pkey; Type: CONSTRAINT; Schema: fodupa; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY fodupa.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


--
-- Name: idcarrera_fk; Type: FK CONSTRAINT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa.estudiar
    ADD CONSTRAINT idcarrera_fk FOREIGN KEY (idcarrera) REFERENCES fodupa.carrera(id);


--
-- Name: idcategoria_fk; Type: FK CONSTRAINT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa.pregunta
    ADD CONSTRAINT idcategoria_fk FOREIGN KEY (idcategoria) REFERENCES fodupa.categoria(id);


--
-- Name: idpregunta_fk; Type: FK CONSTRAINT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa.comentario
    ADD CONSTRAINT idpregunta_fk FOREIGN KEY (idpregunta) REFERENCES fodupa.pregunta(id);


--
-- Name: idusuario_fk; Type: FK CONSTRAINT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa.pregunta
    ADD CONSTRAINT idusuario_fk FOREIGN KEY (idusuario) REFERENCES fodupa.usuario(id);


--
-- Name: idusuario_fk; Type: FK CONSTRAINT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa.comentario
    ADD CONSTRAINT idusuario_fk FOREIGN KEY (idusuario) REFERENCES fodupa.usuario(id);


--
-- Name: idusuario_fk; Type: FK CONSTRAINT; Schema: fodupa; Owner: postgres
--

ALTER TABLE ONLY fodupa.estudiar
    ADD CONSTRAINT idusuario_fk FOREIGN KEY (idusuario) REFERENCES fodupa.usuario(id);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

