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
	produtoId integer not null,
	categoriaId integer not null,
	constraint produto_id_fk foreign key(produtoId) references produto(id),
	constraint categoria_id_fk foreign key(categoriaId) references categoria(id)
)