package layers.persistence.hibernate;

import layers.model.orders.OrderLine;
import layers.model.orders.PurchaseOrder;
import layers.model.orders.PurchaseOrderRepository;
import layers.model.products.ProductRepository;
import layers.persistence.HibernateConfiguration;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestPropertySource("classpath:datasource-test.properties")
@ContextConfiguration(classes = {HibernateConfiguration.class})
@RunWith(SpringRunner.class)
public class PurchaseOrderRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Sql(scripts = {
            "classpath:clear-database.sql"
    }, statements = {
        "insert into product(id, name) values (1, 'Cool Beans'),(2, 'Java Beans');"
    })
    @Test
    public void shouldAddPurchaseOrder() {

        PurchaseOrder purchaseOrder = new PurchaseOrder(0L, 0L);

        productRepository
                .getProducts()
                .forEach(product ->
                        purchaseOrder
                                .getOrderLines()
                                .add(new OrderLine(product.getId(), BigDecimal.ONE, purchaseOrder))
                );

        PurchaseOrder createdPurchaseOrder = purchaseOrderRepository.create(purchaseOrder);

        assertThat(countRowsInTableWhere("PurchaseOrder", "id = " +createdPurchaseOrder.getId())).isEqualTo(1);
        assertThat(countRowsInTableWhere("OrderLine", "purchaseOrder_id = " +createdPurchaseOrder.getId())).isEqualTo(2);
    }

    @Sql(scripts = {
            "classpath:clear-database.sql"
    }, statements = {
            "insert " +
                    "into product(id, name) " +
                    "values " +
                    "((select nextval ('hibernate_sequence')), 'Cool Beans')," +
                    "((select nextval ('hibernate_sequence')), 'Java Beans'); ",
            "insert into PurchaseOrder(version, id) values (0, (select nextval ('hibernate_sequence')));",
            "insert into OrderLine(amount, product_id, purchaseOrder_id, id) " +
                    "values (3, (select id from product where name='Cool Beans'), (select id from PurchaseOrder), (select nextval ('hibernate_sequence')));",
            "insert into OrderLine(amount, product_id, purchaseOrder_id, id) " +
                    "values (5, (select id from product where name='Java Beans'), (select id from PurchaseOrder), (select nextval ('hibernate_sequence')));",
    })
    @Test
    public void shouldReadPurchaseOrders() {
        List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.getAll();
        assertThat(purchaseOrders.size()).isEqualTo(1);

        long purchaseOrderId = jdbcTemplate.queryForObject("select id from PurchaseOrder", Long.class);

        PurchaseOrder purchaseOrder = purchaseOrderRepository.readById(purchaseOrderId).get();

        assertThat(purchaseOrder.getOrderLines().size()).isEqualTo(2);
    }

    @Sql(scripts = {
            "classpath:clear-database.sql"
    }, statements = {
            "insert " +
                    "into product(id, name) " +
                    "values " +
                    "((select nextval ('hibernate_sequence')), 'Cool Beans')," +
                    "((select nextval ('hibernate_sequence')), 'Pinto Beans')," +
                    "((select nextval ('hibernate_sequence')), 'Java Beans'); ",
            "insert into PurchaseOrder(version, id) values (0, (select nextval ('hibernate_sequence')));",
            "insert into OrderLine(amount, product_id, purchaseOrder_id, id) " +
                    "values (3, (select id from product where name='Cool Beans'), (select id from PurchaseOrder), (select nextval ('hibernate_sequence')));",
            "insert into OrderLine(amount, product_id, purchaseOrder_id, id) " +
                    "values (5, (select id from product where name='Java Beans'), (select id from PurchaseOrder), (select nextval ('hibernate_sequence')));",
    })
    @Test
    public void shouldUpdatePurchaseOrder() {
        long purchaseOrderId = jdbcTemplate.queryForObject("select id from PurchaseOrder", Long.class);
        PurchaseOrder purchaseOrder = purchaseOrderRepository.readById(purchaseOrderId).get();

        long pintoBeanProductId = jdbcTemplate.queryForObject("select id from Product where name = 'Pinto Beans'", Long.class);

        purchaseOrder.getOrderLines().clear();
        purchaseOrder.getOrderLines().add(new OrderLine(pintoBeanProductId, new BigDecimal("23"), purchaseOrder));

        purchaseOrderRepository.update(purchaseOrder);

        assertThat(countRowsInTableWhere("OrderLine", "purchaseOrder_id = " +purchaseOrder.getId())).isEqualTo(1);
        assertThat(countRowsInTableWhere("OrderLine", "product_id = " +pintoBeanProductId)).isEqualTo(1);
    }
}
