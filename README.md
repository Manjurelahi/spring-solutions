# spring-solutions

A collection of small Spring Boot applications and examples covering common Spring features and microservice patterns. Each example is maintained as its own Maven project; this repository does not have a single root build.

## Project map

### Java 8 examples (`spring-boot/jdk8`)

| Project | Description |
| --- | --- |
| `hello-world` | Minimal Spring Boot web application |
| `http-status-code` | Returning HTTP status codes from a controller |
| `NEWS-REST-API` | News API example |
| `e-commerce-product-order` | Product and order services |

### Java 21 examples (`spring-boot/jdk21`)

| Project | Description |
| --- | --- |
| `Eureka` | Eureka service discovery with e-commerce services |
| `JWT` | JWT authentication example with a Spring backend and Angular frontend |
| `MicroserviceDesignPatterns/API_Gateway` | API gateway and date service |
| `MicroserviceDesignPatterns/CircuitBreaker` | Circuit breaker and retry examples |
| `MicroserviceDesignPatterns/Saga/Choreography` | E-commerce Saga using choreography |
| `MicroserviceDesignPatterns/Saga/Orchestration` | E-commerce Saga using orchestration |

The Saga projects are Maven multi-module builds. Their parent `pom.xml` files are in the `Choreography` and `Orchestration` directories; each includes shared and service modules.

## Requirements

- Java 8 for projects under `spring-boot/jdk8`
- Java 21 for projects under `spring-boot/jdk21`
- Maven for the Spring projects
- Node.js and npm for the Angular frontend in `spring-boot/jdk21/JWT`

Check each project's `pom.xml` and configuration files for its specific dependencies and runtime requirements. Some distributed examples may also need supporting services such as Kafka or Eureka.

## Build and test

Run Maven commands from the directory containing the project's `pom.xml`:

```shell
cd spring-boot/jdk8/hello-world
mvn test
```

To run a Spring Boot application, use the same project directory:

```shell
mvn spring-boot:run
```

For a multi-module Saga example, run Maven from its parent directory to build and test all modules:

```shell
cd spring-boot/jdk21/MicroserviceDesignPatterns/Saga/Choreography
mvn test
```

Repeat with `Orchestration` to build the orchestration example. Individual services may require additional configuration or infrastructure before they can run; see their `src/main/resources` files.
