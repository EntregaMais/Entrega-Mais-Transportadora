# Entrega-Mais-Transportadora

> Repositório contendo Backend do Módulo Transportadora em Java

## Pré-requisitos
---

É necessário a instalação dos seguintes recursos:
- [Java 17](https://adoptium.net/download/)
- [Maven](https://maven.apache.org/install.html)
- [Docker](https://docs.docker.com/get-docker/) 
- [WSL 2](https://docs.microsoft.com/en-us/windows/wsl/install)

## Iniciando via Docker

Na raiz do projeto, onde há o arquivo `docker-compose.yml`, execute o seguinte comando:

Limpe o diretório `target` com o seguinte comando:

```
mvn clean
```

Gere o arquivo.jar:

```
mvn install
```

Em seguida, realize o build das imagens:

```
docker compose build
```

Em seguida, suba o container com o seguinte comando:

```
docker compose up -d
```

## Iniciando via Script
Na raiz do projeto, onde há o arquivo `docker-compose.yml`, execute o seguinte comando:

```
.\exec_build_run_docker.bat
```

> Agora basta enviar requisições especificadas na API (substituindo por `localhost` pelos *hostnames* caso esteja usando em desenvolvimento)

- [Documentação API EntregaMais](https://documenter.getpostman.com/view/22927633/2s8YmSsftk)

### Iniciando a Aplicação (Após Primeiro Build)
---

Na raiz do projeto, suba o container com o seguinte comando:

```
docker compose up -d
```

### Desligando a Aplicação
---

Na raiz do projeto, remova o container com o seguinte comando:

```
docker compose down
```
### Reiniciar a Aplicação
---

Execute o comando `docker compose down` seguido de `docker compose up -d` na raiz do projeto.

```
docker compose down
```

```
docker compose up -d
```
### Reconstruir a Aplicação
---

```
.\exec_build_run_docker.bat
```