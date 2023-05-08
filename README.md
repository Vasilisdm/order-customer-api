# Customer API

This is an experimental project for educational purposes.<br>
It exposes endpoints for CRUD operations on customers and orders.

## Technologies used

- [Flyway](https://documentation.red-gate.com/fd)
- [Jooq](https://github.com/etiennestuder/gradle-jooq-plugin)
- [Hoplite](https://github.com/sksamuel/hoplite)
- [Gradle](https://gradle.org/guides/#getting-started)
- [Logback](https://logback.qos.ch)
- [H2](https://www.h2database.com/html/main.html)
- [Ktor](https://ktor.io)
- [JUnit](https://ktor.io/docs/testing.html)

## How to build

- Clone project
- Execute `./gradlew flywayMigrate` from the within the Intellij's console, <br> or `gradle flywayMigrate` (if you
  have installed Gradle in your machine) to migrate the schema to the latest version.
- Execute either `./gradlew generateJooq` or `gradle generateJooq` for the mapping tables types to POJOs to be
  generated.
- Lastly execute either `./gradlew build` or `gradle build` for the project to build

## How to test

- You can start the application and manually perform requests from either the `CustomerRoutes` or
  the `OrderRoutes` http files which can be found under `HttpRequests` directory.
- Execute the integration tests under the `test` package.
- Or execute either `./gradlew test` or `gradle test` to execute the tests from the terminal.