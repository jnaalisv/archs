package monolith.http.interfaces;

import monolith.application.ProductDetail;
import monolith.application.ProductService;
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

    @GetMapping(path="{productId}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ProductDetail getProduct(@PathVariable long productId) {
        return productService.findById(productId);
    }

    @PutMapping(path="{productId}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ProductDetail update(@PathVariable long productId, @RequestBody ProductDetail productDetail) {
        return productService.update(productId, productDetail);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ProductDetail> save(@RequestBody ProductDetail product) {

        ProductDetail createdProduct = productService.create(product);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Location", "/products/"+createdProduct.id);
        return new ResponseEntity<>(createdProduct, responseHeaders, HttpStatus.CREATED);
    }
}
