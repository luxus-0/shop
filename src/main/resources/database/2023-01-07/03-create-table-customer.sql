--liquibase formatted sql
--changeset lnowogorski:19
create table if not exists customer(
    id bigint not null primary key generated by default as identity,
    firstName varchar(100) not null,
    lastName varchar(100) not null,
    street varchar(100) not null,
    zipCode varchar(100) not null,
    city varchar(100) not null,
    email varchar(100) not null,
    phone varchar(100) not null
);