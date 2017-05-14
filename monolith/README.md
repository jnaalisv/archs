# Monolith
#### Simple monolithic architecture example without hard module boundaries.
- Application is divided by responsibility into 4 root level packages: `application`, `http`, `model` and `persistence`. 
- Easy and fast to develop
- Domain logic can easily creep to controllers or persistence layer
- RestControllers have unnecessary tight coupling to Hibernate framework, repositories, entities and Spring transaction management.

##### application-package
- Contains Application Services that are responsible for managing use cases. 
- Demarcates transactions 
- Depends on `persistence`-package for repository implementations.
- For a more modular approach, should not depend on `persistence`-package

##### model-package
- Business logic is implemented here in domain objects and services
- No dependencies, except JPA, which is not ideal but practical

##### persistence-package
- Repository implementations
- Depens on `model`, hibernate and spring-orm

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
