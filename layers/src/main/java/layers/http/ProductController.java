package layers.http;

import layers.services.ProductDetail;
import layers.services.ProductService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ProductDetail> getProducts() {
        return productService.getProducts();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ProductDetail> save(@RequestBody ProductDetail product) {

        Serializable createdProductId = productService.create(product);

        ProductDetail createdProduct = productService.getById(createdProductId);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Location", "/products/"+createdProduct.id);
        return new ResponseEntity<>(createdProduct, responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping(value= "{productId}",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ProductDetail update(@PathVariable long productId, @RequestBody ProductDetail product) {
        productService.update(productId, product);
        return productService.getById(productId);
    }
}
