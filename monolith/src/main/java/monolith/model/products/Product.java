package monolith.model.products;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    public Product() {

    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }
}
