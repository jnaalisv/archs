# Monolith
Simple monolithic architecture example without hard module boundaries.
- easy and fast to develop
- Domain logic can easily creep to controllers
- RestControllers have unnecessary tight coupling to Hibernate framework, repositories, entities and Spring transaction management.

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
