--liquibase formatted sql
--changeset lnowogorski:14
alter table review add moderated boolean default false;