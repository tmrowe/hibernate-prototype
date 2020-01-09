# Hibernate Prototype
This prototype project demonstrates using Hibernate as an ORM.

## Prerequisites
The following need to be installed on your system to run this project:

- [Java Development Kit 8+](https://www.oracle.com/technetwork/java/javase/downloads/index.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Docker](https://www.docker.com/get-started)

## Quick Setup
Steps to get the project running:

- Download and install everything under prerequisites.
- Run `docker-compose up`
- Run `mvn flyway:migrate`

You now have a running Postgres database that has all of the necessary configuration in place. The database migration 
scripts listed under `scr/main/resources/db/migration` have been run. 

You can now connect to the database using your preferred tool with these details:
```
Host: localhost
Port: 5432
User: flyway_migration
Password: 123456
Database: flyway
```

## Notable Packages
- [Spring Web](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html)
- [Spring Data](https://docs.spring.io/spring-data/jpa/docs/current/reference/html)
- [Hibernate](https://docs.jboss.org/hibernate/orm/5.3/userguide/html_single/Hibernate_User_Guide.html)
- [Flyway](https://flywaydb.org/documentation/)

## Database Migrations
Flyway is being used in this project to build the database. 

See the [flyway_prototype](https://github.com/tmrowe/flyway_prototype) project for further details.

### Postgres
The Postgres database is run within a docker container.

This will create a volume under `./data` to hold the database. If you would like to clear your database and start again 
delete this directory.

It is important to note that the setup scripts will only run on a clean instance. Therefore, if you need to rerun them,
for whatever reason, you will first need to delete the `./data` directory and then run `docker-compose up`.

#### Commands
- To start the container detached: `docker-compose up -d`
- To stop the container: `docker-compose down`
- Show logs: `docker logs -f postgres`
- Run PSQL: `docker exec -it postgres psql --username flyway_migration --dbname flyway`

CTRL+D to exit.

Further commands can be found on the [Docker Cheat Sheet](https://www.saltycrane.com/blog/2017/08/docker-cheat-sheet/).

### Conventions
The following conventions have been used for database.
- Snake case.
- Lower cased.
- Singular.
- Join tables are named after the two tables they join separated by an underscore.

Entity classes should be the table name in camel case. 

We are using Spring's naming strategy for databases, as defined by these classes:
- SpringPhysicalNamingStrategy
- SpringImplicitNamingStrategy

There is no need to specify the table name in the entities as these naming strategies handle that for us.  

## Application Layers
The primary layers that make up the API and what purpose they serve.

### Repository
This layer should handle all access to the database. 

All queries should be defined at this level and should be small and reusable so that they can be composed together in
the service layer.

When we have requirements to perform complex joins or otherwise expensive database operations we 
can write tailored queries at this layer, which can then be called by the service layer and exposed as an endpoint in 
a controller as we would any other.

We are using [Spring Data](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/) at this layer.

### Service
This layer should compose together functionality defined in the repositories, other services, and components to achieve
business requirements. Transformation between DTOs and Entities should occur at this layer.

This layer should not care about the specifics of the database management handled by the  repositories, or of the 
interfaces exposed by the controller layer.

#### Transactions
We are able to make use of the Spring Frameworks `@Transactional` annotation to facilitate rollbacks. This annotation 
can be placed on a function in a service to mark it as a transaction. Should an `Error`, a `RuntimeException`, or any 
JPA exceptions be thrown the transaction system will trigger a rollback. 

We can also set the `@Transactional` annotations `rollBackFor` property to any arbitrary `Throwable` class.

### Controller
This layer should define the APIs interface.

We are using [Spring MVC](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#spring-web) at
this layer.

#### Conventions
This layer should adhere to the following conventions: 
- Specify the MediaType produced by each endpoint using the `@RequestMapping` annotations `produces` property.
- Specify the MediaType consumed by each endpoint that accepts a body using the `@RequestMapping` annotations `consumes`
property.
- A base path should be applied to the controller that matches the principle resource it exposes access to. Example: 
the AccountController should have an `account` base.
- All input and outputs should be typed. I.e. The Kotlin `Any` and Java `*` should not be used.
- Default parameters should be set using Spring the `@RequestParam` annotations `defaultValue` property. Do NOT set
default values with Kotlin, as this will cause Kotlin to generate an additional endpoint for each default Kotlin value.
- Optional parameters should be set using Spring the `@RequestParam` annotations `required` property.
- Controllers should have no knowledge of the business concerns of the service layer.
- Controllers should have no knowledge of the database concerns of the repository layer.
- Endpoints should have a single purpose.
- Should not accept flags to change internal behaviours.
- Should not be passed parameters or values that are used to alter underlying queries.
- Custom response codes can be returned by wrapping our response in a `ResponseEntity`. Example: 
`return ResponseEntity(foo, HttpStatus.NOT_FOUND)`
- If a service layer call returns a response wrapped in an `Optional` the controller layer should check if the value is
set. If it is set it should return a successful response. If it is not set a `HttpStatus.NOT_FOUND` should be returned.

#### URL Patterns

##### Path variables 
Path variables should be used to identify the resource that is being targeted by a request.

They should be made up a key and then the path variable itself. Example: `/foo/{foo}`.

An action that is applied to a specific resource should indicate the target resource in its path. Example:
`/account/uuid/123/activate` or `/account/uuid/123/deactivate`.

An action that is applied to all resources should not indicate a specific resource. Example: `/account/activate` or 
`/account/deactivate`.

An endpoint that uses the same resource for a different action should have the same path but a different method. 
Example:

Get account 123: 
`GET /account/uuid/123`

Delete account 123
`DELETE /account/uuid/123` 

##### Query Parameters
Query Parameters should be used to modify a request. For instance apply pagination to a request to retrieve all users.

Example:
`GET /account?page=1&size=2&sortField=email&sortDirection=DESC`

##### Headers
Should be used for meta data about the request. Such as authorization.

##### HTTP Method Usage
- `GET`: A side effect free operation that retrieves some resource or view.
- `POST`: The creation of a new resource.
- `PUT`: A full update of a resource.
- `PATCH`: A partial update of a resource.
- `DELETE`: The deletion of a resource.

### Components
Parts of the application that are used at the different layers in the application.

#### Entity
Entities are annotated Plain Old Java Objects (POJOs) that represents a row in database table.

Only entities can be persisted to the database.

Should exist at the repository and service level. Entities should not be returned by controllers.

##### Embeddable
An Embeddable is a class that can be embedded into an Entity.

##### View
A projection that can be used to hold a subset or superset of an entity. 

#### DTO
A general purpose object used to move data around the system.
