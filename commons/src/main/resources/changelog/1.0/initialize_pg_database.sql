--liquibase formatted sql
--changeset solyk:initialize_pg_database

DROP SCHEMA IF EXISTS dev_ops CASCADE;
CREATE SCHEMA dev_ops;

--rollback not required;
