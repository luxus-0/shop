--liquibase formatted sql
--changeset lnowogorski:12
alter table product alter column category_id type bigint;