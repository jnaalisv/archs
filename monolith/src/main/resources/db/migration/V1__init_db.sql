CREATE SEQUENCE hibernate_sequence;

CREATE TABLE Product (
  id INT8 NOT NULL,
  name TEXT,
  version INT8 NOT NULL DEFAULT 0,
  PRIMARY KEY (id)
);
