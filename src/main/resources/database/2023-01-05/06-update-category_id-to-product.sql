--liquibase formatted sql
--changeset lnowogorski:11
update product set  category_id=1 where id > 0;