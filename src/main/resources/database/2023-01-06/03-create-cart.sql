--liquibase formatted sql
--changeset lnowogorski:15
create table if not exists cart(
  id bigint primary key not null generated by default as identity,
  created timestamp not null
);