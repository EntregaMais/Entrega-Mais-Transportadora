@echo off 
Rem Executa docker compose build
mvn clean && mvn install && docker compose build