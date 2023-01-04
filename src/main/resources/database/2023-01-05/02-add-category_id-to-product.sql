--liquibase formatted sql
--changeset lnowogorski:7
alter table product add column if not exists category_id bigint;