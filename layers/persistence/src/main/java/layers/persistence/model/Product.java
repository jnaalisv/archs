package layers.persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    public Product(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Product() {}

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }
}
