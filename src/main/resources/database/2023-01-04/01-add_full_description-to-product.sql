--liquibase formatted sql
--changeset lnowogorski:5
alter table product add column if not exists full_description text default null;