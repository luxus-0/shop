--liquibase formatted sql
--changeset lnowogorski:16
create table if not exists cart_item(
    id bigint not null primary key,
    product_id bigint not null,
    quantity int,
    cart_id bigint not null,
    constraint fk_cart_item_product_id foreign key (product_id) references product(id),
    constraint fk_cart_item_cart_id foreign key (cart_id) references cart(id)
);