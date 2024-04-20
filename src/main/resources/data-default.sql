INSERT INTO suppliers (email, name, phone)
VALUES (NULL, 'Lipták Fivérek Kft.', '+36-66-441-611'),
       (NULL, 'MikroTik', NULL);

INSERT INTO products (name, on_stock, photo, price, unit, supplier_id)
VALUES ('6-os csavar', '1000', NULL, '120', 'darab', 1),
       ('Mimo-Omni', '2', NULL, '150000', 'darab', 2),
       ('Sárgaréz', '2000', NULL, '5000', 'kilogramm', 1),
       ('Vörösréz', '3000', NULL, '3000', 'kilogramm', 1);

INSERT INTO users (email, first_name, last_name, password, username)
VALUES ('juhasz.bence@netsurfclub.hu', 'Ferenc', 'Dunai', '12345', 'bjuhasz'),
       ('juhasz.gabor@netsurfclub.hu', 'Gábor', 'Juhász', '12345', 'gjuhasz'),
       ('papp.laszlo@netsurfclub.hu', 'László', 'Papp', '12345', 'lpapp');
