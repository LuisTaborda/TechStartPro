create table categoria(
	id serial not null,
	nome character varying(255),
	constraint categoria_id_pk primary key(id)
)

create table produto(
	id serial not null,
	nome character varying(255) not null,
	descricao character varying(255) not null,
	valor decimal not null,
	constraint produto_id_pk primary key(id)
)

create table produto_categoria(
	id serial not null,
	produto_id integer not null,
	categoria_id integer not null,
	constraint produto_id_fk foreign key(produto_id) references produto(id),
	constraint categoria_id_fk foreign key(categoria_id) references categoria(id)
)