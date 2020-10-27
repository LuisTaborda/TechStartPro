create table category(
	id serial not null,
	name character varying(255),
	constraint category_id_pk primary key(id)
)

create table product(
	id serial not null,
	name character varying(255) not null,
	description character varying(255) not null,
	price decimal not null,
	constraint product_id_pk primary key(id)
)

create table product_category(
	id serial not null,
	product_id integer not null,
	category_id integer not null,
	constraint product_id_fk foreign key(product_id) references product(id),
	constraint category_id_fk foreign key(category_id) references category(id)
)