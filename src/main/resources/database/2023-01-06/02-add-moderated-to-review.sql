--liquibase formatted sql
--changeset lnowogorski:14
alter table review add if not exists moderated boolean default false;