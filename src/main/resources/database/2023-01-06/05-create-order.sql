--liquibase formatted sql
--changeset lnowogorski:17
create table if not exists "order"(
    id bigint not null primary key,
    placeDate timestamp not null,
    orderStatus varchar(32) not null,
    grossAmount decimal(6,2) not null,
    firstName varchar(255) not null,
    lastName varchar(255) not null,
    street varchar(255) not null,
    zipCode varchar(255) not null,
    city varchar(255) not null,
    email varchar(255) not null,
    phone varchar(255) not null
);

create table if not exists order_row(
    id bigint not null primary key,
    order_id bigint not null,
    product_id bigint not null,
    quantity int not null,
    price decimal(6,2) not null,
    constraint fk_order_row_order_id foreign key (order_id) references "order"(id),
    constraint fk_order_row_product_id foreign key(product_id) references product(id)
);