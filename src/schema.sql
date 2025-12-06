create table Product(
    id int primary key,
    name varchar(100),
    price numeric,
    creation_datetime timestamp
);
create table Product_category(
    id int primary key,
    name varchar(100),
    product_id int references Product(id)
);
