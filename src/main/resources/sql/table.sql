CREATE TABLE accounts (
    id VARCHAR (36) NOT NULL PRIMARY KEY,
    customer_id VARCHAR (36) NOT NULL,
    name VARCHAR (50) NOT NULL,
    type VARCHAR (50) NOT NULL,
    status VARCHAR (255) NOT NULL,
    amount BIGINT NOT NULL,
    created_date TIMESTAMP NOT NULL,
    updated_date TIMESTAMP
);

CREATE TABLE customers (
    customer_id VARCHAR (36) NOT NULL PRIMARY KEY,
    name VARCHAR (50) NOT NULL,
    status VARCHAR (255) NOT NULL,
    birthdate TIMESTAMP NOT NULL,
    created_date TIMESTAMP NOT NULL,
    updated_date TIMESTAMP
);

CREATE TABLE products (
    product_id VARCHAR (36) NOT NULL PRIMARY KEY,
    name VARCHAR (50) NOT NULL,
    created_date TIMESTAMP NOT NULL,
    updated_date TIMESTAMP NOT NULL
);

CREATE TABLE product_price (
    id VARCHAR (36) NOT NULL PRIMARY KEY,
    product_id VARCHAR (36) NOT NULL,
    price BIGINT NOT NULL,
    created_date TIMESTAMP NOT NULL,
    updated_date TIMESTAMP
);

CREATE TABLE wishlist (
    wishlist_id VARCHAR (36) NOT NULL PRIMARY KEY,
    product_id VARCHAR (36) NOT NULL,
    customer_id VARCHAR (36) NOT NULL,
    created_date TIMESTAMP NOT NULL,
    updated_date TIMESTAMP
);

CREATE TABLE orders (
    id VARCHAR (36) NOT NULL PRIMARY KEY,
    product_id VARCHAR (36) NOT NULL,
    customer_id VARCHAR (36) NOT NULL,
    price BIGINT,
    created_date TIMESTAMP NOT NULL,
    updated_date TIMESTAMP
);

CREATE TABLE payment (
    id VARCHAR (36) NOT NULL PRIMARY KEY,
    order_id VARCHAR (36) NOT NULL,
    status VARCHAR (255) NOT NULL,
    price BIGINT,
    created_date TIMESTAMP NOT NULL,
    updated_date TIMESTAMP
);