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
- Auto generate simple CRUD endpoints?
- Add controllers for List and Accountlist repositories. 

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