--liquibase formatted sql

--changeset Sergei:1
CREATE TABLE IF NOT EXISTS TEST_TABLE
(
    ID                   bigint primary key,
    STATUS                     varchar,
    CREATE_TIMESTAMP           timestamp,
    LAST_CHANGE_TIMESTAMP      timestamp
);

CREATE SEQUENCE IF NOT EXISTS test_table_id_seq ;

--rollback drop table IF EXIST TEST_TABLE;
--rollback drop sequence if exists test_table_id_seq;