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

__TODO__
Hibernate specific instructions

## Database Migrations
Flyway is being used in this project to build the database. 

See the [flyway_prototype](https://github.com/tmrowe/flyway_prototype) project for further details.

### Postgres
The Postgres database is run within a docker container.

This will create a volume under `./data` to hold the database's data. If you would like to clear your database and 
start again delete this directory.

It is important to note that the setup scripts will only run on a clean instance. Therefore, if you need to rerun them
you will first need to delete the `./data` directory and then run `docker-compose up`.

#### Commands
- To start the container detached: `docker-compose up -d`
- To stop the container: `docker-compose down`
- Show logs: `docker logs -f postgres`
- Run PSQL: `docker exec -it postgres psql --username flyway_migration --dbname flyway`

CTRL+D to exit.

Further commands can be found on the [Docker Cheat Sheet](https://www.saltycrane.com/blog/2017/08/docker-cheat-sheet/).

__TODO__

Code:
- Take specific required dependencies from Spring Boot Starters?
- Add controllers for List and AccountList repositories. 
- Have services call other services rather than repositories.

Testing:
- Define test cases to check object equality.

Advanced Cases:
- Provide complex query. Raw SQL query.
- Provide own repository implementation.
- How to apply Spring method security.
- How to prevent a default repository method from being available. E.g. disable delete method.

Documentation:
- Database naming strategy:
    Camel case -> snake case.
    lower cased
    singular
    Join tables are named after the two tables they join separated by an underscore
    Using Spring's naming strategy for databases, as defined by these classes:
        - SpringPhysicalNamingStrategy
        - SpringImplicitNamingStrategy
- No-arg constructor:
    The no-argument constructor, which is also a JavaBean convention, is a requirement for all persistent classes. 
    Hibernate needs to create objects for you, using Java Reflection. The constructor can be private. However, package 
    or public visibility is required for runtime proxy generation and efficient data retrieval without bytecode 
    instrumentation.
- Types:
    Types specified in entities are not SQL or Java types but rather Hibernate types that are convertors between the
    two. 
- Hibernate:
    Documentation: https://docs.jboss.org/hibernate/orm/5.3/userguide/html_single/Hibernate_User_Guide.html
- Working with DTOs
- Puts (replacing the whole entity) used by default. Patches (updating part of the entity) used where required.
- Class naming conventions.
    All Entity classes should be the table name in camel case suffixed with Entity. 
    Our naming strategy removes this suffix so there is no need to specify the table name in the entities. 
    All DTO classes should be suffixed with DTO.
- Testing:
    Using Mockito extension: https://github.com/nhaarman/mockito-kotlin/wiki/Mocking-and-verifying

- Entities
    Requirements:
    
    JPA: 
        - The entity class must be annotated with the javax.persistence.Entity annotation (or be denoted as such in XML 
        mapping)
        - The entity class must have a public or protected no-argument constructor. It may define additional 
        constructors as well.
        - The entity class must be a top-level class.
        - An enum or interface may not be designated as an entity.
        - The entity class must not be final. No methods or persistent instance variables of the entity class may be 
        final.
        - If an entity instance is to be used remotely as a detached object, the entity class must implement the 
        Serializable interface.
        - Both abstract and concrete classes can be entities. Entities may extend non-entity classes as well as entity 
        classes, and non-entity classes may extend entity classes.
        - The persistent state of an entity is represented by instance variables, which may correspond to JavaBean-style 
        properties. An instance variable must be directly accessed only from within the methods of the entity by the 
        entity instance itself. The state of the entity is available to clients only through the entity’s accessor 
        methods (getter/setter methods) or other business methods.
    
    Hibernate:
    Not as strict in its requirements. The differences from the list above include:
    
        - The entity class must have a no-argument constructor, which may be public, protected or package visibility. 
        It may define additional constructors as well.
        - The entity class need not be a top-level class.
        - Technically Hibernate can persist final classes or classes with final persistent state accessor (getter/setter) 
        methods. However, it is generally not a good idea as doing so will stop Hibernate from being able to generate 
        proxies for lazy-loading the entity.
        - Hibernate does not restrict the application developer from exposing instance variables and reference them from
        outside the entity class itself. The validity of such a paradigm, however, is debatable at best.
        
Spring Data:
    Projections: 
        https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#projections
        http://www.bytestree.com/spring/spring-data-jpa-projections-5-ways-return-custom-object
    
    Repository function naming: 
        https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation



Core Components
- Spring MVC
- Spring Data
- Mockito + Kotlin extension library


__TODO__
- JPA 
    - Projections
        - Closed
        - Open
    - JPQL
- Hibernate
    - Entities
- Spring Data
- General API Principles.
    - Straight paths through the application.
    - The repository should define a series of reusable queries that can be composed together to achieve some business
    purpose.
    - A controller endpoint should define a consistent action that it performs.
    - Controllers should not be passed flags or enumerations to change their behaviour.
    - Controllers should have no knowledge of the workings of database queries so should not accept any inputs designed
    to alter underlying queries.
    - Generally speaking the repository layer should contain the most amount of configurability on options as to how we
    can retrieve data.
    - The service layer can then offer options about which repository functions it calls.
    - The controller layer should have very little customisation and be as stable as possible.
    


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

__TODO__
- Connections to entities
- Connection to views and DTOs.
- Returning Lists, Pages, Optionals.
- Use of generics to specify views to populate.
- Usage restrictions of Views. I.e. they must map to either the names in the entity.
- Custom queries and returning values with names that can then be mapped to a view.
- Implementing a repository method.

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
__TODO__

Query Parameters should be used to 

##### Headers
__TODO__

##### HTTP Method Usage
- `GET`: A side effect free operation that retrieves some resource or view.
- `POST`: The creation of a new resource.
- `PUT`: A full update of a resource.
- `PATCH`: A partial update of a resource.
- `DELETE`: The deletion of a resource.




__TODO__
- Considerations for Swagger?
- Error Codes

## Components
Parts of the application that are used at the different layers in the application

### Entity
__TODO__ Define

### Embeddable
__TODO__ Define

### DTO and View
__TODO__ Define

Should these be the same context.




__TODO__ 
Example call to the application. Something like:
- Controller /foo endpoint called with BarDTO request Body.
- BarDTO passed down into service layer to be persisted.
- Service layer converts the DTO into an Entity of the expected type.
- Service layer calls the repository layer to persist the object, in a transaction if appropriate.
- The repository layer performs the persistence and returns some response.
- The Service does any further processing on the repositories response. I.e. any other database calls or post processing.
- Object returned to the controller layer.
- The controller layer responds to the caller with a JSON object and an appropriate response code.



