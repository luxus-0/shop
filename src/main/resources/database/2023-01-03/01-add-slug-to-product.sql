--liquibase formatted sql
--changeset lnowogorski:3
alter table product add if not exists slug varchar(255);