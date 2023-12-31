PGDMP  3    (            	    {            football_club    16.0    16.0     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16398    football_club    DATABASE     �   CREATE DATABASE football_club WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Ukrainian_Ukraine.1251';
    DROP DATABASE football_club;
                postgres    false            �            1259    16408    club    TABLE     �   CREATE TABLE public.club (
    club_id bigint NOT NULL,
    club_name text NOT NULL,
    country text NOT NULL,
    stadium text NOT NULL
);
    DROP TABLE public.club;
       public         heap    postgres    false            �            1259    16407    club_club_id_seq    SEQUENCE     �   ALTER TABLE public.club ALTER COLUMN club_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.club_club_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    218            �            1259    16400    player    TABLE     �   CREATE TABLE public.player (
    player_id bigint NOT NULL,
    player_name text NOT NULL,
    nationality text NOT NULL,
    jersey_number integer NOT NULL
);
    DROP TABLE public.player;
       public         heap    postgres    false            �            1259    16399    player_player_id_seq    SEQUENCE     �   ALTER TABLE public.player ALTER COLUMN player_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.player_player_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    216            �          0    16408    club 
   TABLE DATA           D   COPY public.club (club_id, club_name, country, stadium) FROM stdin;
    public          postgres    false    218   &       �          0    16400    player 
   TABLE DATA           T   COPY public.player (player_id, player_name, nationality, jersey_number) FROM stdin;
    public          postgres    false    216   �       �           0    0    club_club_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.club_club_id_seq', 13, true);
          public          postgres    false    217            �           0    0    player_player_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.player_player_id_seq', 12, true);
          public          postgres    false    215            "           2606    16417    club club_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.club
    ADD CONSTRAINT club_pkey PRIMARY KEY (club_id);
 8   ALTER TABLE ONLY public.club DROP CONSTRAINT club_pkey;
       public            postgres    false    218                        2606    16426    player player_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.player
    ADD CONSTRAINT player_pkey PRIMARY KEY (player_id);
 <   ALTER TABLE ONLY public.player DROP CONSTRAINT player_pkey;
       public            postgres    false    216            �   �   x��бn�@�z�+N| �!HIiLBA ��4Q���{{�#_�H��iF�
֔���ڞĠ�лCq9E����hr�`�)�e�U�����F�M�:0�����C{�p�ɻuf\�F�Bt�,7���i���6n��z��E���#�C&�����._c��M�^ƀ�e�z(tU�[���ɍS����Sj;	��jYV�=G��G�0      �      x�̻
�@���W��[%����%,lf������8+-��@M�K�&��=#v�Fe1h�j2R;��>�����͝����e)[49Λ�<8�'�>=ʅ,Q�,���Qi��f�"�uc(�     