--liquibase formatted sql
--changeset lnowogorski:13
create table if not exists review(
    id bigint not null primary key,
    product_id bigint not null,
    author_name varchar(60) not null,
    content text
);