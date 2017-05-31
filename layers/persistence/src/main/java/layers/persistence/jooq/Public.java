/*
 * This file is generated by jOOQ.
*/
package layers.persistence.jooq;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import layers.persistence.jooq.tables.Orderline;
import layers.persistence.jooq.tables.Product;
import layers.persistence.jooq.tables.Purchaseorder;
import layers.persistence.jooq.tables.SchemaVersion;

import org.jooq.Catalog;
import org.jooq.Sequence;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.2"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Public extends SchemaImpl {

    private static final long serialVersionUID = 1738407387;

    /**
     * The reference instance of <code>public</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * The table <code>public.orderline</code>.
     */
    public final Orderline ORDERLINE = layers.persistence.jooq.tables.Orderline.ORDERLINE;

    /**
     * The table <code>public.product</code>.
     */
    public final Product PRODUCT = layers.persistence.jooq.tables.Product.PRODUCT;

    /**
     * The table <code>public.purchaseorder</code>.
     */
    public final Purchaseorder PURCHASEORDER = layers.persistence.jooq.tables.Purchaseorder.PURCHASEORDER;

    /**
     * The table <code>public.schema_version</code>.
     */
    public final SchemaVersion SCHEMA_VERSION = layers.persistence.jooq.tables.SchemaVersion.SCHEMA_VERSION;

    /**
     * No further instances allowed
     */
    private Public() {
        super("public", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Sequence<?>> getSequences() {
        List result = new ArrayList();
        result.addAll(getSequences0());
        return result;
    }

    private final List<Sequence<?>> getSequences0() {
        return Arrays.<Sequence<?>>asList(
            Sequences.HIBERNATE_SEQUENCE);
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            Orderline.ORDERLINE,
            Product.PRODUCT,
            Purchaseorder.PURCHASEORDER,
            SchemaVersion.SCHEMA_VERSION);
    }
}
