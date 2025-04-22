INSERT INTO suppliers (name, phone, email)
VALUES ('Lipták Fivérek Kft.', '+36-66-441-611', NULL),
       ('MikroTik', NULL, NULL);

INSERT INTO products (name, supplier_id, price, unit, photo, on_stock)
VALUES ('6-os csavar', 1, 120.00, 'darab', NULL, 1000),
       ('Mimo-Omni', 2, 150000.00, 'darab', NULL, 2),
       ('Sárgaréz', 1, 5000.00, 'kilogramm', NULL, 2000),
       ('Vörösréz', 1, 3000.00, 'kilogramm', NULL, 3000);

INSERT INTO users (first_name, last_name, email)
VALUES ('Bence', 'Juhász', 'juhasz.bence@netsurfclub.hu'),
       ('Gábor', 'Juhász', 'juhasz.gabor@netsurfclub.hu'),
       ('László', 'Papp', 'papp.laszlo@netsurfclub.hu'),
       ('Ferenc', 'Dunai', 'dunai.ferenc@netsurfclub.hu');

INSERT INTO profiles (username, password, user_id, is_deleted)
VALUES ('jbence', '$2a$10$HF7OVtgqLl7SQpb3Cdue.u.JNEtPmnzcHcXq5fqIp3VihXENxofNO', (SELECT id FROM users WHERE email = 'juhasz.bence@netsurfclub.hu'), 0),
       ('jgabor', '$2a$10$0Qhoqu0M5gl6zpWAc90QH.fz7JG8rjtE..VnVXSEQV15G3USUZAAu', (SELECT id FROM users WHERE email = 'juhasz.gabor@netsurfclub.hu'), 0),
       ('pappl', '$2a$10$SNQfcKuFrPgXWGjeWotgLOVLs9VuNBdQllxwVYHZeZOdLhsruiB..', (SELECT id FROM users WHERE email = 'papp.laszlo@netsurfclub.hu'), 0),
       ('dunaif', '$2a$10$mpQ8nod5twYJ7Mbx9zeKoecGbe14PxG6rgWEcRyL0WgXRzxnE5hkG', (SELECT id FROM users WHERE email = 'dunai.ferenc@netsurfclub.hu'), 0);
