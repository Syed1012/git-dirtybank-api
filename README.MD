# Initial setup for Sonar workshop

This guide launches **Postgres** and **SonarQube** locally via Docker Compose for the DirtyBank API workshop.

## Prerequisites
- Docker Desktop (or Docker Engine) + Docker Compose
- Ports available:
    - **5400** on your host (mapped to Postgres container 5432)
    - **9000** on your host (SonarQube UI)

> If 5400 is busy on your machine, change the left side of the port mapping below (e.g. `5500:5432`).

## Compose file location
`dirtybank-api/local-bank/docker-compose.yml`

## docker-compose.yml

```yaml
services:
  postgres:
    image: postgres:17
    container_name: local-bank-postgres
    environment:
      POSTGRES_DB: bank
      POSTGRES_USER: bank_user
      POSTGRES_PASSWORD: bank_pass
    ports:
      - "5400:5432"           # host:container
    volumes:
      - postgres_data:/var/lib/postgresql/data

  sonarqube:
    image: sonarqube:latest
    container_name: local-bank-sonarqube
    depends_on:
      - postgres
    ports:
      - "9000:9000"
    environment:
      SONAR_ES_BOOTSTRAP_CHECKS_DISABLE: "true"
      SONAR_JDBC_URL: jdbc:postgresql://postgres:5432/sonar
      SONAR_JDBC_USERNAME: sonar
      SONAR_JDBC_PASSWORD: sonar
    volumes:
      - sonarqube_data:/opt/sonarqube/data
      - sonarqube_extensions:/opt/sonarqube/extensions
      - sonarqube_logs:/opt/sonarqube/logs
volumes:
  postgres_data:
  sonarqube_data:
  sonarqube_extensions:
  sonarqube_logs:
