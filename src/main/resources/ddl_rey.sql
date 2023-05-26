DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS products CASCADE;
DROP TABLE IF EXISTS shopping_cart CASCADE;
DROP TABLE IF EXISTS order_history CASCADE;

CREATE TABLE users(
    id VARCHAR PRIMARY KEY,
    username VARCHAR NOT NULL UNIQUE,
    password VARCHAR NOT NULL,
    salt VARCHAR NOT NULL
);

CREATE TABLE products(
    product_id VARCHAR PRIMARY KEY,
    name VARCHAR NOT NULL,
    description VARCHAR NOT NULL,
    price double precision NOT NULL,
    rating INT
);

CREATE TABLE shopping_cart(
    cart_id int PRIMARY KEY,
    product_id VARCHAR NOT NULL,
    user_id VARCHAR NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (product_id) REFERENCES products (product_id)
);

CREATE table order_history(
    order_history_id VARCHAR PRIMARY KEY,
    user_id VARCHAR NOT NULL,
    product_id VARCHAR NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (product_id) REFERENCES products (product_id)
);

