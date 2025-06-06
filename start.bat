@echo off
REM This script starts the Docker Compose stack for both the root and migration directories.

echo Starting Docker Compose in the root directory...
docker compose -f docker-compose.yml up -d

echo All Docker Compose stacks started.