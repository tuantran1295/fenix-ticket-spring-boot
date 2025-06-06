#!/bin/bash
# This script sets the correct ownership for the Oracle DB data directory
# and then starts the Docker Compose stack.


echo "Starting Docker Compose..."
docker compose -f docker-compose.yml up -d