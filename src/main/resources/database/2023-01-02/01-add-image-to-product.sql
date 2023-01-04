--liquibase formatted sql
--changeset lnowogorski:2
alter table product add column if not exists image varchar(255);