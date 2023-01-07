--liquibase formatted sql
--changeset lnowogorski:22
--alter table "order" add  payment_id bigint;
update "order" set payment_id=1 where payment_id=1;
alter table "order" alter column payment_id type bigint;