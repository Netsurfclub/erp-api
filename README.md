erp-api
=============

### Enterprise resource planning application's backend.

[![Continuous integration](https://github.com/Netsurfclub/erp-api/actions/workflows/build.yml/badge.svg)](https://github.com/Netsurfclub/erp-api/actions/workflows/build.yml)

[Contributing Guide](.github/CONTRIBUTING.md)

Prerequisites
-------------

To avoid any unexpected application behaviour, make sure you have installed the following tools with the proper version numbers:

- [Eclipse Temurin JDK](https://adoptium.net/temurin/releases)
- [Maven 3.9.6](https://maven.apache.org/download.cgi)

Running project locally
-----------------------

### Set up Docker

Before you start, make sure you set up Docker Desktop on your machine. On a Windows machine install and turn on WSL2:

- [How to install Linux on Windows with WSL](https://learn.microsoft.com/en-us/windows/wsl/install)
- [Docker Desktop](https://www.docker.com/products/docker-desktop)

Note that:

- For local development use the **dev** Spring profile.
- The **default** Spring profile, it is configured with an H2 in-memory database for passing the Continuous integration GitHub check.

### Start application

```bash
mvn clean install

docker compose up
```

#### To manage the database manually, open [phpMyAdmin](http://localhost:8081) in your web browser.

#### To view GraphQL queries and mutations, open [GraphiQL](http://localhost:8080/graphiql) in your web browser.
