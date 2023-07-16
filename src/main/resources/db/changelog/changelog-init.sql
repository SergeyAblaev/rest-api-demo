--liquibase formatted sql

--changeset Sergei:1
CREATE TABLE IF NOT EXISTS STORE_DATA
(
    ID                   bigint primary key,
    STATUS                     varchar,
    CREATE_TIMESTAMP           timestamp,
    LAST_CHANGE_TIMESTAMP      timestamp
);

CREATE SEQUENCE IF NOT EXISTS store_data_id_seq ;

INSERT INTO STORE_DATA (ID, STATUS)
VALUES (nextval('store_data_id_seq'), 'STATUS1'),
       (nextval('store_data_id_seq'), 'STATUS2'),
       (nextval('store_data_id_seq'), 'STATUS3'),
       (nextval('store_data_id_seq'), 'STATUS4'),
       (nextval('store_data_id_seq'), 'STATUS5');
