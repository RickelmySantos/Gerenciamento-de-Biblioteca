#!/bin/bash

set -e
set -u

create_schema() {
    local database=$1
	local schema=$2
	echo "  Creating schema '$schema' on '$database'"
	psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$database" <<-EOSQL
        DROP SCHEMA IF EXISTS $schema CASCADE;
        CREATE SCHEMA $schema AUTHORIZATION $POSTGRES_USER;
EOSQL
}

if [ -n "$POSTGRES_EXTRA_SCHEMAS" ]; then
	echo "Initializing extra schemas requested: $POSTGRES_EXTRA_SCHEMAS"
	for schema in $(echo $POSTGRES_EXTRA_SCHEMAS| tr ',' ' '); do
    	create_schema $POSTGRES_DB $schema
	done
	echo "Extra schemas created"
fi

