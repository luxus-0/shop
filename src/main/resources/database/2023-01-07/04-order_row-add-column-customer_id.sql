--liquibase formatted sql
--changeset lnowogorski:19
--alter table order_row alter column product_id type bigint;
--alter table order_row add if not exists customer_id bigint;
--alter table order_row add constraint fk_order_row_customer_id foreign key (customer_id) references customer(id);