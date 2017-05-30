package layers.http;

import layers.services.ProductDetail;
import layers.services.PurchaseOrderDetail;
import layers.services.PurchaseOrderService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/purchase-orders")
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    public PurchaseOrderController(final PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<PurchaseOrderDetail> getPurchaseOrders() {
        return purchaseOrderService.getPurchaseOrders();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PurchaseOrderDetail> save(@RequestBody PurchaseOrderDetail purchaseOrder) {

        purchaseOrderService.create(purchaseOrder);

        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>(purchaseOrder, responseHeaders, HttpStatus.CREATED);
    }
}
