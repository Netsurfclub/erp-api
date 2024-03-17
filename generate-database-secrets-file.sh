#!/usr/bin/bash

cd ./src/main/resources || exit

rm -rf ./database-secrets.yml

touch ./database-secrets.yml

yq e -n '.jpa-connection.host = null | .jpa-connection.port = null | .jpa-connection.database = null | .jpa-connection.username = null | .jpa-connection.password = null' > ./database-secrets.yml
