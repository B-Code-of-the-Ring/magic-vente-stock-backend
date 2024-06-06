--
-- PostgreSQL database dump
--

-- Dumped from database version 14.7
-- Dumped by pg_dump version 14.7

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: produit; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.produit (
    produit_id integer NOT NULL,
    libelle_produit character varying(200) NOT NULL,
    prix_produit numeric(20,2) DEFAULT 0,
    quantite_produit integer DEFAULT 0,
    image_produit text
);


ALTER TABLE public.produit OWNER TO postgres;

--
-- Name: produit_jour; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.produit_jour (
    produit_jour_id integer NOT NULL,
    date_debut date,
    date_fin date,
    produit_id integer
);


ALTER TABLE public.produit_jour OWNER TO postgres;

--
-- Name: produit_jour_produit_jour_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.produit_jour_produit_jour_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.produit_jour_produit_jour_id_seq OWNER TO postgres;

--
-- Name: produit_jour_produit_jour_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.produit_jour_produit_jour_id_seq OWNED BY public.produit_jour.produit_jour_id;


--
-- Name: produit_produit_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.produit_produit_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.produit_produit_id_seq OWNER TO postgres;

--
-- Name: produit_produit_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.produit_produit_id_seq OWNED BY public.produit.produit_id;


--
-- Name: produit produit_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produit ALTER COLUMN produit_id SET DEFAULT nextval('public.produit_produit_id_seq'::regclass);


--
-- Name: produit_jour produit_jour_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produit_jour ALTER COLUMN produit_jour_id SET DEFAULT nextval('public.produit_jour_produit_jour_id_seq'::regclass);


--
-- Data for Name: produit; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.produit (produit_id, libelle_produit, prix_produit, quantite_produit, image_produit) FROM stdin;
\.


--
-- Data for Name: produit_jour; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.produit_jour (produit_jour_id, date_debut, date_fin, produit_id) FROM stdin;
\.


--
-- Name: produit_jour_produit_jour_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.produit_jour_produit_jour_id_seq', 1, false);


--
-- Name: produit_produit_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.produit_produit_id_seq', 1, false);


--
-- Name: produit_jour pk_produit_jour; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produit_jour
    ADD CONSTRAINT pk_produit_jour PRIMARY KEY (produit_jour_id);


--
-- Name: produit produit_libelle_produit_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produit
    ADD CONSTRAINT produit_libelle_produit_key UNIQUE (libelle_produit);


--
-- Name: produit produit_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produit
    ADD CONSTRAINT produit_pkey PRIMARY KEY (produit_id);


--
-- Name: produit_jour fk_produit_jour_produit; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produit_jour
    ADD CONSTRAINT fk_produit_jour_produit FOREIGN KEY (produit_id) REFERENCES public.produit(produit_id);


--
-- PostgreSQL database dump complete
--

