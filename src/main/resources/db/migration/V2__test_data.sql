INSERT INTO suppliers (name, phone, email)
VALUES ('Lipták Fivérek Kft.', '+36-66-441-611', NULL),
       ('MikroTik', NULL, NULL);

INSERT INTO products (name, supplier_id, price, unit, photo, on_stock)
VALUES ('6-os csavar', 1, 120, 'darab', NULL, 1000),
       ('Mimo-Omni', 2, 150000, 'darab', NULL, 2),
       ('Sárgaréz', 1, 5000, 'kilogramm', NULL, 2000),
       ('Vörösréz', 1, 3000, 'kilogramm', NULL, 3000);

INSERT INTO users (username, password, first_name, last_name, email, is_deleted)
VALUES ('jbence', '$2a$10$HF7OVtgqLl7SQpb3Cdue.u.JNEtPmnzcHcXq5fqIp3VihXENxofNO', 'Bence', 'Juhász', 'juhasz.bence@netsurfclub.hu', 0),
       ('jgabor', '$2a$10$0Qhoqu0M5gl6zpWAc90QH.fz7JG8rjtE..VnVXSEQV15G3USUZAAu', 'Gábor', 'Juhász', 'juhasz.gabor@netsurfclub.hu', 0),
       ('pappl', '$2a$10$SNQfcKuFrPgXWGjeWotgLOVLs9VuNBdQllxwVYHZeZOdLhsruiB..', 'László', 'Papp', 'papp.laszlo@netsurfclub.hu', 0),
       ('dunaif', '$2a$10$mpQ8nod5twYJ7Mbx9zeKoecGbe14PxG6rgWEcRyL0WgXRzxnE5hkG', 'Ferenc', 'Dunai', 'dunai.ferenc@netsurfclub.hu', 0);
