--liquibase formatted sql
--changeset lnowogorski:1
create table IF NOT EXISTS product (
    id int primary key not null,
    name varchar(255) not null,
    category varchar(255) not null,
    description text not null,
    price decimal(9, 2) not null,
    currency varchar(3) not null
);