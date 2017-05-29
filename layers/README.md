# Layers, aka multitier, n-tier or layered architecture
Persistence infrastructure, domain logic and service layers have been physically separated to different modules (*persistence*, 
*model*, *services*). Each layer only depends on the layer below. Java's visibility modifiers have been used to prevent some implementation detail leak to 
upper layers.
- a bit more complicated than monolithic architecture
- layering helps to separate concerns
- Implementation details such as framework libraries and package private classes don't leak to consuming projects due to
usage of 'api and implementation separation' provided by java-library -plugin

## Notes
#### Upgraded to gradle java-library -plugin to declare api and implementation dependencies
- dependencies declared in *implementation*-configuration don't leak anymore to consuming projects compile class path. 
This affects both third party libraries(hibernate-core, spring-orm) as well as subprojects(persistence)

#### spring-boot dependency management magic
- ```org.springframework.boot```-plugin pulls in transitive dependencies and uses versions declared in spring boot bom.
These will override original versions.

#### spring-boot-starter-test pulls in hibernate-core and other dependencies

## Todo


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
