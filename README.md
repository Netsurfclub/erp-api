erp-api
=============

### Enterprise resource planning application's backend.

[Contributing Guide](.github/CONTRIBUTING.md)

Prerequisites
-------------

To avoid any unexpected application behaviour, make sure you have installed the following tools with the proper version numbers:

- [Eclipse Temurin JDK](https://adoptium.net/temurin/releases)
- [Maven 3.9.6](https://maven.apache.org/download.cgi)

Running project locally
-----------------------

```bash
mvn clean package

mvn spring-boot:run -Dspring-boot.run.profiles=default
```
