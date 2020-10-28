PGDMP                     	    x            tech_start_pro    12.3    12.3                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    57848    tech_start_pro    DATABASE     �   CREATE DATABASE tech_start_pro WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Portuguese_Brazil.1252' LC_CTYPE = 'Portuguese_Brazil.1252';
    DROP DATABASE tech_start_pro;
                tech_start_pro    false            �            1259    57851    category    TABLE     [   CREATE TABLE public.category (
    id integer NOT NULL,
    name character varying(255)
);
    DROP TABLE public.category;
       public         heap    postgres    false            �            1259    57849    category_id_seq    SEQUENCE     �   CREATE SEQUENCE public.category_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.category_id_seq;
       public          postgres    false    203                       0    0    category_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.category_id_seq OWNED BY public.category.id;
          public          postgres    false    202            �            1259    57859    product    TABLE     �   CREATE TABLE public.product (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    description character varying(255) NOT NULL,
    price numeric NOT NULL
);
    DROP TABLE public.product;
       public         heap    postgres    false            �            1259    57870    product_category    TABLE     �   CREATE TABLE public.product_category (
    id integer NOT NULL,
    product_id integer NOT NULL,
    category_id integer NOT NULL
);
 $   DROP TABLE public.product_category;
       public         heap    postgres    false            �            1259    57868    product_category_id_seq    SEQUENCE     �   CREATE SEQUENCE public.product_category_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.product_category_id_seq;
       public          postgres    false    207                        0    0    product_category_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.product_category_id_seq OWNED BY public.product_category.id;
          public          postgres    false    206            �            1259    57857    product_id_seq    SEQUENCE     �   CREATE SEQUENCE public.product_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.product_id_seq;
       public          postgres    false    205            !           0    0    product_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.product_id_seq OWNED BY public.product.id;
          public          postgres    false    204            �
           2604    57854    category id    DEFAULT     j   ALTER TABLE ONLY public.category ALTER COLUMN id SET DEFAULT nextval('public.category_id_seq'::regclass);
 :   ALTER TABLE public.category ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    202    203    203            �
           2604    57862 
   product id    DEFAULT     h   ALTER TABLE ONLY public.product ALTER COLUMN id SET DEFAULT nextval('public.product_id_seq'::regclass);
 9   ALTER TABLE public.product ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    204    205    205            �
           2604    57873    product_category id    DEFAULT     z   ALTER TABLE ONLY public.product_category ALTER COLUMN id SET DEFAULT nextval('public.product_category_id_seq'::regclass);
 B   ALTER TABLE public.product_category ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    206    207    207                      0    57851    category 
   TABLE DATA           ,   COPY public.category (id, name) FROM stdin;
    public          postgres    false    203   �                 0    57859    product 
   TABLE DATA           ?   COPY public.product (id, name, description, price) FROM stdin;
    public          postgres    false    205   �                 0    57870    product_category 
   TABLE DATA           G   COPY public.product_category (id, product_id, category_id) FROM stdin;
    public          postgres    false    207   �       "           0    0    category_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.category_id_seq', 1, false);
          public          postgres    false    202            #           0    0    product_category_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.product_category_id_seq', 1, false);
          public          postgres    false    206            $           0    0    product_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.product_id_seq', 1, false);
          public          postgres    false    204            �
           2606    57856    category category_id_pk 
   CONSTRAINT     U   ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_id_pk PRIMARY KEY (id);
 A   ALTER TABLE ONLY public.category DROP CONSTRAINT category_id_pk;
       public            postgres    false    203            �
           2606    57867    product product_id_pk 
   CONSTRAINT     S   ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_id_pk PRIMARY KEY (id);
 ?   ALTER TABLE ONLY public.product DROP CONSTRAINT product_id_pk;
       public            postgres    false    205            �
           2606    57879    product_category category_id_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.product_category
    ADD CONSTRAINT category_id_fk FOREIGN KEY (category_id) REFERENCES public.category(id);
 I   ALTER TABLE ONLY public.product_category DROP CONSTRAINT category_id_fk;
       public          postgres    false    203    207    2704            �
           2606    57874    product_category product_id_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.product_category
    ADD CONSTRAINT product_id_fk FOREIGN KEY (product_id) REFERENCES public.product(id);
 H   ALTER TABLE ONLY public.product_category DROP CONSTRAINT product_id_fk;
       public          postgres    false    2706    207    205                  x������ � �            x������ � �            x������ � �     