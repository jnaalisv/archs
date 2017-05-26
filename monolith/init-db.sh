#!/usr/bin/env bash
set -e

export PGCLIENTENCODING="UTF8"

PSQL_OPTS=("-v" "ON_ERROR_STOP=1")

VERBOSE="true"

function create_db() {
psql "${PSQL_OPTS[@]}" <<EOF
SELECT pg_terminate_backend(pg_stat_activity.pid)
FROM pg_stat_activity
WHERE pg_stat_activity.datname = '$1'
  AND pid <> pg_backend_pid();
DROP DATABASE IF EXISTS $1;
DROP USER IF EXISTS $2;
CREATE USER $2 WITH ENCRYPTED PASSWORD '$3';
CREATE DATABASE $1 ENCODING 'UTF8' LC_COLLATE 'fi_FI.UTF-8' LC_CTYPE 'fi_FI.UTF-8' TEMPLATE template0 OWNER $2;
EOF
}

create_db monolith_db monolith_user monolith_user
create_db monolith_db_test monolith_user_test monolith_user_test
