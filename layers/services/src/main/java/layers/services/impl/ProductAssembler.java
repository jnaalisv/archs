package layers.services.impl;

import layers.model.products.Product;
import layers.services.ProductDetail;

public class ProductAssembler {

    public static Product forUpdate(ProductDetail productDetail) {
        return new Product(productDetail.id, productDetail.name, productDetail.version);
    }

    public static ProductDetail from(Product product) {
        return new ProductDetail(product.getId(), product.getName(), product.getVersion());
    }
}
