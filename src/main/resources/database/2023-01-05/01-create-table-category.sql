--liquibase formatted sql
--changeset lnowogorski:6
create table if not exists category(
    id bigint not null primary key,
    name varchar(255) not null,
    description text,
    slug varchar(255) not null
);