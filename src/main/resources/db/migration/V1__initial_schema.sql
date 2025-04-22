CREATE DATABASE IF NOT EXISTS erp;

CREATE TABLE IF NOT EXISTS suppliers
(
  id    BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name  VARCHAR(255) NOT NULL,
  phone VARCHAR(25),
  email VARCHAR(75)
);

CREATE TABLE IF NOT EXISTS products
(
  id          BIGINT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name        VARCHAR(255)   NOT NULL,
  supplier_id BIGINT         NOT NULL,
  price       DECIMAL(10, 2) NOT NULL,
  unit        VARCHAR(255)   NOT NULL,
  photo       VARCHAR(255),
  on_stock    INT            NOT NULL,
  CONSTRAINT fk_supplier
    FOREIGN KEY (supplier_id) REFERENCES suppliers (id)
      ON DELETE CASCADE
      ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS users
(
  id         BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(255) NOT NULL,
  last_name  VARCHAR(255) NOT NULL,
  email      VARCHAR(75)  NOT NULL
);

CREATE TABLE IF NOT EXISTS profiles
(
  id         BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username   VARCHAR(15)  NOT NULL,
  password   VARCHAR(255) NOT NULL,
  user_id    BIGINT       NOT NULL,
  is_deleted TINYINT(1)   NOT NULL,
  CONSTRAINT fk_user
    FOREIGN KEY (user_id) REFERENCES users (id)
      ON DELETE CASCADE
      ON UPDATE CASCADE
);
