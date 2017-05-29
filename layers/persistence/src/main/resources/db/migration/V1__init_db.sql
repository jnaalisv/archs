CREATE SEQUENCE hibernate_sequence;

CREATE TABLE Product (
  id INT8 NOT NULL,
  version INT8 NOT NULL DEFAULT 0,
  createTime TIMESTAMP NOT NULL DEFAULT localtimestamp,
  name TEXT,
  PRIMARY KEY (id)
);
