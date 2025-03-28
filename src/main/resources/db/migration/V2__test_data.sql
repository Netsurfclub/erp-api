INSERT INTO suppliers (name, phone, email)
VALUES ('Lipták Fivérek Kft.', '+36-66-441-611', NULL),
       ('MikroTik', NULL, NULL);

INSERT INTO products (name, supplier_id, price, unit, photo, on_stock)
VALUES ('6-os csavar', 1, 120, 'darab', NULL, 1000),
       ('Mimo-Omni', 2, 150000, 'darab', NULL, 2),
       ('Sárgaréz', 1, 5000, 'kilogramm', NULL, 2000),
       ('Vörösréz', 1, 3000, 'kilogramm', NULL, 3000);

INSERT INTO users (username, password, first_name, last_name, email)
VALUES ('bjuhasz', '12345', 'Ferenc', 'Dunai', 'juhasz.bence@netsurfclub.hu'),
       ('gjuhasz', '12345', 'Gábor', 'Juhász', 'juhasz.gabor@netsurfclub.hu'),
       ('lpapp', '12345', 'László', 'Papp', 'papp.laszlo@netsurfclub.hu');
