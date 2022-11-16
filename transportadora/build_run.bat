@echo off 
Rem Executa comandos Maven Build e Maven Install e docker compose no projeto raiz
mvn clean && mvn install && docker compose build && docker compose up