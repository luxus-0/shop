--liquibase formatted sql
--changeset lnowogorski:5
alter table product add full_description text default null;