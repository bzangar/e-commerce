create table if not exists category(
    id integer primary key,
    description varchar(255),
    name varchar(255)
);

create table if not exists product(
    id integer primary key,
    description varchar(255),
    name varchar(255),
    available_quanitity double precision not null,
    price numeric(38, 2),
    category_id integer constraint fkfirst references category
);

create sequence if not exists category_seq increment by 50;
create sequence if not exists product_seq increment by 50;
