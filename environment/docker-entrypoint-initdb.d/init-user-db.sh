#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    GRANT ALL PRIVILEGES ON DATABASE "backend-challenge" TO "admin-s2it";

    CREATE SEQUENCE providers_id_seq;

    CREATE TABLE providers (
        id integer NOT NULL DEFAULT nextval('providers_id_seq'),
        name VARCHAR NOT NULL,
        address VARCHAR NOT NULL
    );

    ALTER SEQUENCE providers_id_seq
    OWNED BY providers.id;
EOSQL