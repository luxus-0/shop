--liquibase formatted sql
--changeset lnowogorski:15
create table if not exists cart(
  id bigint not null primary key,
  created timestamp not null
);