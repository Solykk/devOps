--liquibase formatted sql
--changeset solyk:create_tables

create table dev_ops.admin_requests
(
    ar_id serial primary key,
    ar_ip_address text not null,
    ar_message text,
    ar_created_date timestamp without time zone not null default now()
);

create table dev_ops.main_requests
(
    mr_id serial primary key,
    mr_ip_address text not null,
    mr_message text,
    mr_created_date timestamp without time zone not null default now()
);

--rollback drop table dev_ops.admin_requests;
--rollback drop table dev_ops.main_requests;
