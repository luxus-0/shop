--liquibase formatted sql
--changeset lnowogorski:4
alter table product add constraint ui_product_slug unique(slug);