DROP TABLE IF EXISTS customer CASCADE ;
DROP TABLE IF EXISTS product CASCADE ;
DROP TABLE IF EXISTS customer_product;

CREATE TABLE customer
(
    customer_id         SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL ,
    last_name  VARCHAR(50) NOT NULL,
    CONSTRAINT first_last_name_idx UNIQUE (first_name, last_name)
);

CREATE TABLE product
(
    product_id            SERIAL PRIMARY KEY,
    product_name  VARCHAR(50) NOT NULL ,
    product_price DECIMAL(18, 2) NOT NULL,
    CONSTRAINT product_name_idx UNIQUE (product_name)
);

CREATE TABLE customer_product
(
    customer_id INT REFERENCES customer (customer_id) ON UPDATE CASCADE ON DELETE CASCADE,
    product_id INT REFERENCES product (product_id) ON UPDATE CASCADE ON DELETE CASCADE,
    purchase_date DATE NOT NULL DEFAULT CURRENT_DATE
);
