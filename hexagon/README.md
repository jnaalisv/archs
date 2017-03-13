# Hexagon, or Ports and Adapters architecture
Application Core does not depend on infrastructure code. Control flows into core through primary ports(exposed public 
api), and out of the core through secondary ports such as repository interfaces that an outside module implements.

### Notes
- How to separate primary and secondary ports? Currently the module's public api forms both, which means
that primary adapters have access to secondary ports too.

#### Model has a framework dependencies 
(spring-tx, spring-context) and usage of ```javax.persistence```-api implies hibernate which could lead to dependency 
on hibernate dirty tracking semantics.

#### The framework dependencies could be refactored away
    1. spring-tx could be replaced by a secondary port (service interface) offering tx management
    2. spring-context and usage of component-scan could be replaced by explicit bean declarations in api-package.
    3. jpa-api could be removed and no hibernate state transition tracking semantics assumed. But then persistence
    layer would have to declare entity classes and translate them to domain objects.
    4. Framework dependencies are a convenience trade off
    
#### What next?
- assume the framework dependencies are a necesary evil
- solve the port confusion problem or evolve this project into a more traditional layered approach while keeping some of 
the good stuff. Since jpa-api implies hibernate, the secondary persistence adapter could be rolled into model
1. Fix the port confusion with better architecture
2. Develop the layered approach further

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
