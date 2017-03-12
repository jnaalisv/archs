# Layers, aka multitier, n-tier or layered architecture
Persistence infrastructure, domain logic and presentation layers have been physically separated to different modules (*persistence*, 
*model*, *http*). Each layer only depends on the layer below. Java's visibility modifiers have been used to prevent some implementation detail leak to 
upper layers.
- a bit more complicated than monolithic architecture
- layering helps to separate concerns
- Implementation details such as framework libraries and all public classes(including entities from persistence) still 
leak to upper layers as compile time dependencies.
- Entities are now in *persistence*-subproject, which means domain logic is split in two places. Either *persistence* 
and *model* should be merged or domain logic should not reside in ```@Entity```-annotated classes.

## Notes
#### Upgraded to gradle java-library -plugin to declare api and implementation dependencies
- dependencies declared in *implementation*-configuration don't leak anymore to consuming projects compile class path. 
This affects both third party libraries(hibernate-core, spring-orm) as well as subprojects(persistence)

#### spring-boot dependency management magic
- ```org.springframework.boot```-plugin pulls in transitive dependencies and uses versions declared in spring boot bom.
These will override original versions.

#### spring-boot-starter-test pulls in hibernate-core and other dependencies

## Todo
- remove usage of spring-boot-starter-tests from libraries
- investigate if hibernate validate on init can be used with flyway
- share config of test database (currently duplicated)

## Usage
1. To run the app
```bash
./gradlew bootRun
```

2. Query for products
```bash
curl -X GET http://localhost:8080/products 
```

2. Add a new product
```bash
curl http://localhost:8080/products  -H "Content-Type: application/json" -d '{"name":"Java Beans"}'
```
