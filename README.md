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
