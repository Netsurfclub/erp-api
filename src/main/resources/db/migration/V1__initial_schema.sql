CREATE DATABASE IF NOT EXISTS erp;

CREATE TABLE IF NOT EXISTS suppliers
(
    id    INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name  VARCHAR(255) NOT NULL,
    phone VARCHAR(25),
    email VARCHAR(75)
);

CREATE TABLE IF NOT EXISTS products
(
    id          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    supplier_id INT          NOT NULL,
    price       INT          NOT NULL,
    unit        VARCHAR(255) NOT NULL,
    photo       VARCHAR(255),
    on_stock    INT          NOT NULL,
    CONSTRAINT fk_supplier
        FOREIGN KEY (supplier_id) REFERENCES suppliers (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS users
(
    id         INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username   VARCHAR(15),
    password   VARCHAR(15)  NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    email      VARCHAR(75)  NOT NULL
);
