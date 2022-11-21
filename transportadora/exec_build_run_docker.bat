@echo off 
Rem Executa docker compose build e up
mvn clean && mvn install && docker compose build && docker compose up