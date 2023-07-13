#!/usr/bin/env bash

echo -e "\033[0;31mTrying to start local environment ...\033[0m"

if [[ -z `which docker-compose` ]]; then
    echo "docker-compose not found, abort execution ..."
    exit 1
fi

cd deploy
docker-compose up -d app-db
echo -e "\033[0;31mDONE\033[0m"
