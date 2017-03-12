# Layers, aka multitier, n-tier or layered architecture
Persistence, domain logic and presentation layers have been physically separated to different modules. Each layer only
depends on the layer below. Java's visibility modifiers have been used to prevent some implementation detail leak to 
upper layers.
- a bit more complicated than monolithic architecture
- layering helps to separate concerns
- Implementation details such as framework libraries and all public classes(including entities from persistence) still 
leak to upper layers as compile time dependencies.
- Entities are now in *persistence*-subproject, which means domain logic is split in two places.

## Update: gradle java-library -plugin to declare api and implementation dependencies
- dependencies declared in *implementation*-configuration don't leak anymore to consuming projects compile class path. 
This affects both third party libraries(hibernate-core, spring-orm) as well as subprojects(persistence)

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
