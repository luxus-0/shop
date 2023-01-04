--liquibase formatted sql
--changeset lnowogorski:10
insert into category (id, name, description,slug) values (1, 'Phone', 'Phone for users', 'iphone_6') on conflict do nothing;
