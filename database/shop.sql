PGDMP         	                z            shop    10.20    10.20 %               0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false                       1262    16393    shop    DATABASE     ?   CREATE DATABASE shop WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Russian_Russia.1251' LC_CTYPE = 'Russian_Russia.1251';
    DROP DATABASE shop;
             postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false                       0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    3                        3079    12924    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false                       0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            ?            1259    16404    items    TABLE     ?   CREATE TABLE public.items (
    id bigint NOT NULL,
    count bigint NOT NULL,
    cost numeric(15,4) NOT NULL,
    product_id bigint NOT NULL,
    sale_id bigint NOT NULL
);
    DROP TABLE public.items;
       public         postgres    false    3            ?            1259    16402    items_id_seq    SEQUENCE     u   CREATE SEQUENCE public.items_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.items_id_seq;
       public       postgres    false    199    3                       0    0    items_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.items_id_seq OWNED BY public.items.id;
            public       postgres    false    198            ?            1259    16418    items_product_id_seq    SEQUENCE     }   CREATE SEQUENCE public.items_product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.items_product_id_seq;
       public       postgres    false    199    3                       0    0    items_product_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.items_product_id_seq OWNED BY public.items.product_id;
            public       postgres    false    202            ?            1259    16425    items_sale_id_seq    SEQUENCE     z   CREATE SEQUENCE public.items_sale_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.items_sale_id_seq;
       public       postgres    false    3    199                       0    0    items_sale_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.items_sale_id_seq OWNED BY public.items.sale_id;
            public       postgres    false    203            ?            1259    16396    products    TABLE     ?   CREATE TABLE public.products (
    id bigint NOT NULL,
    name character varying(100) NOT NULL,
    price numeric(15,4) NOT NULL
);
    DROP TABLE public.products;
       public         postgres    false    3            ?            1259    16394    products_id_seq    SEQUENCE     x   CREATE SEQUENCE public.products_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.products_id_seq;
       public       postgres    false    3    197                       0    0    products_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.products_id_seq OWNED BY public.products.id;
            public       postgres    false    196            ?            1259    16412    sales    TABLE     b   CREATE TABLE public.sales (
    id bigint NOT NULL,
    date timestamp with time zone NOT NULL
);
    DROP TABLE public.sales;
       public         postgres    false    3            ?            1259    16410    sales_id_seq    SEQUENCE     u   CREATE SEQUENCE public.sales_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.sales_id_seq;
       public       postgres    false    3    201                       0    0    sales_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.sales_id_seq OWNED BY public.sales.id;
            public       postgres    false    200            
           2604    16407    items id    DEFAULT     d   ALTER TABLE ONLY public.items ALTER COLUMN id SET DEFAULT nextval('public.items_id_seq'::regclass);
 7   ALTER TABLE public.items ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    198    199    199            ~
           2604    16399    products id    DEFAULT     j   ALTER TABLE ONLY public.products ALTER COLUMN id SET DEFAULT nextval('public.products_id_seq'::regclass);
 :   ALTER TABLE public.products ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    196    197    197            ?
           2604    16415    sales id    DEFAULT     d   ALTER TABLE ONLY public.sales ALTER COLUMN id SET DEFAULT nextval('public.sales_id_seq'::regclass);
 7   ALTER TABLE public.sales ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    201    200    201                      0    16404    items 
   TABLE DATA               E   COPY public.items (id, count, cost, product_id, sale_id) FROM stdin;
    public       postgres    false    199   ?#                 0    16396    products 
   TABLE DATA               3   COPY public.products (id, name, price) FROM stdin;
    public       postgres    false    197   ?$                 0    16412    sales 
   TABLE DATA               )   COPY public.sales (id, date) FROM stdin;
    public       postgres    false    201   ?$                  0    0    items_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.items_id_seq', 34, true);
            public       postgres    false    198                       0    0    items_product_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.items_product_id_seq', 1, false);
            public       postgres    false    202                       0    0    items_sale_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.items_sale_id_seq', 1, false);
            public       postgres    false    203                       0    0    products_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.products_id_seq', 2, true);
            public       postgres    false    196                       0    0    sales_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.sales_id_seq', 18, true);
            public       postgres    false    200            ?
           2606    16409    items items_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.items
    ADD CONSTRAINT items_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.items DROP CONSTRAINT items_pkey;
       public         postgres    false    199            ?
           2606    16401    products products_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.products DROP CONSTRAINT products_pkey;
       public         postgres    false    197            ?
           2606    16417    sales sales_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.sales
    ADD CONSTRAINT sales_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.sales DROP CONSTRAINT sales_pkey;
       public         postgres    false    201            ?
           2606    16442    items items_product_id_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.items
    ADD CONSTRAINT items_product_id_fkey FOREIGN KEY (product_id) REFERENCES public.products(id);
 E   ALTER TABLE ONLY public.items DROP CONSTRAINT items_product_id_fkey;
       public       postgres    false    2690    197    199            ?
           2606    16447    items items_sale_id_fkey    FK CONSTRAINT     w   ALTER TABLE ONLY public.items
    ADD CONSTRAINT items_sale_id_fkey FOREIGN KEY (sale_id) REFERENCES public.sales(id);
 B   ALTER TABLE ONLY public.items DROP CONSTRAINT items_sale_id_fkey;
       public       postgres    false    201    199    2694               ?   x?eQK?![?a(?O????????<?tCNF??E?d9?R??1P1??b{?-?@?M)??Lڷ?d3*Z?KB????+ {??G?%5?^%?vEanK???Js (c?{y!?ӃN??JB'J?q??V??w? j????L?*y-.?????o
??Gtl#???(???%?N?>?M2??????H???5?\??q???`ˀ?N9??{Sy?l>????#????K?         '   x?3?I-.?4426?353??2 M???b???? ?K	?         ?   x?]л1?ت?r?1|?D-??#.??????oԛ?"?P??"o?&?iHr????f???Dq@+???&?6?s?
?XFxb?tZ)?8?'???:???nUYVV??&?Cł;??ҿ>?BΥ??W??98L???s???u?Zk_??M"     