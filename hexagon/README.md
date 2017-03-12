# Hexagon, or Ports and Adapters architecture
Application Core does not depend on infrastructure code. Control flows into core through primary ports(exposed public 
api), and out of the core through secondary ports such as repository interfaces that an outside module implements.

### Notes
- How to separate primary and secondary ports? Currently the module's public api forms both, which means
that primary adapters have access to secondary ports too.

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
