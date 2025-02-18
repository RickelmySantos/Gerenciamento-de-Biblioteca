#!/bin/bash

if [[ $1 == "reset" ]]; then
    docker compose down
    echo "Recriando a infraestrutura local..."
    sudo rm -rf ./.app
    docker compose up -d --force-recreate
elif [[ $1 == "start" ]]; then
    echo "Inicializando a infraestrutura local..."
    docker compose up -d
elif [[ $1 == "start app" ]]; then
    echo "Inicializando a aplicação..."
    docker compose --profile app up -d
elif [[ $1 == "stop" ]]; then
    echo "Interrompendo a execução da infraestrutura local..."
    docker compose stop
elif [[ $1 == "stop app" ]]; then
    echo "Interrompendo a execução da aplicação..."
    docker compose stop biblioteca-api-local biblioteca-ui-local
elif [[ $1 == "build api image" ]]; then
    echo "Construindo a imagem da API..."
    docker compose build biblioteca-api-local --no-cache
elif [[ $1 == "build ui image" ]]; then
    echo "Construindo a imagem da UI..."
    docker compose build biblioteca-ui-local --no-cache
fi

