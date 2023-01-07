--liquibase formatted sql
--changeset lnowogorski:19
insert into shipment(name, price, type, default_shipment) values ('DHL', 14.99, 'normal', true);
insert into shipment(name, price, type, default_shipment) values ('DPD', 17.99, 'cash delivery', true);
insert into shipment(name, price, type, default_shipment) values ('GLS', 23.45, 'normal', true);
insert into shipment(name, price, type, default_shipment) values ('UPS', 35.44, 'advance payment', true);

select * from databasechangelog;

delete from databasechangelog where id > '0';