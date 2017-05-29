CREATE TABLE PurchaseOrder (
  id INT8 NOT NULL,
  version INT8 NOT NULL DEFAULT 0,
  createTime TIMESTAMP NOT NULL DEFAULT localtimestamp,
  PRIMARY KEY (id)
);

CREATE TABLE OrderLine (
  id INT8 NOT NULL,
  amount NUMERIC(19, 2),
  purchaseOrder_id INT8 REFERENCES PurchaseOrder ON DELETE CASCADE,
  product_id INT8 REFERENCES Product ON DELETE RESTRICT,
  PRIMARY KEY (id)
);


