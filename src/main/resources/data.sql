-- users
INSERT INTO users (username, password, email, enabled, apikey)
VALUES ('AdminAppel!', '$2a$12$9qsU207uA2.YFl573A/qS.dJvm5FwWyzUMDjhMdijPYIJdoXdWjnq', 'user1@test.nl', TRUE, 'apikeyvoornu');

INSERT INTO users (username, password, email, enabled, apikey)
VALUES ('Astrix!', '$2a$12$9qsU207uA2.YFl573A/qS.dJvm5FwWyzUMDjhMdijPYIJdoXdWjnq', 'astrix@test.nl', TRUE, 'apikeyvoornu');

INSERT INTO users (username, password, email, enabled, apikey)
VALUES ('Donald12', '$2a$12$9qsU207uA2.YFl573A/qS.dJvm5FwWyzUMDjhMdijPYIJdoXdWjnq', 'donale@test.nl', TRUE, 'apikeyvoornu');

-- authorities
INSERT INTO authorities (username, authority) VALUES ('AdminAppel!', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('AdminAppel!', 'ROLE_ADMIN');
INSERT INTO authorities (username, authority) VALUES ('Astrix!', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('Donald12', 'ROLE_USER');

-- accounts
INSERT INTO accounts (firstname, lastname, date_created, user_username) VALUES ('Karel', 'Appel', CURRENT_TIMESTAMP, 'AdminAppel!');
INSERT INTO accounts (balance, firstname, lastname, date_created, user_username) VALUES (745.00, 'Asterix', 'de Gallier', CURRENT_TIMESTAMP, 'Astrix!');
INSERT INTO accounts (balance, firstname, lastname, date_created, user_username) VALUES (643.00, 'Donald', 'Duck', CURRENT_TIMESTAMP, 'Donald12');

-- contracts
INSERT INTO contracts (payee, type, start_date, end_date, amount, account_id)
VALUES ('Gallië Paper', 'monthly paper', '2023-07-28', '2024-07-28', 50.00, 2);
INSERT INTO contracts (payee, type, start_date, end_date, amount, account_id)
VALUES ('Gallië Insurance', 'Adventure insurance', '2023-07-28', '2024-07-28', 800.00, 2);

INSERT INTO contracts (payee, type, start_date, end_date, amount, account_id)
VALUES ('Vitens', 'water contract', '2023-07-28', '2024-07-28', 100.00, 3);
INSERT INTO contracts (payee, type, start_date, end_date, amount, account_id)
VALUES ('Ekoplaza', 'duck food', '2023-07-28', '2025-07-28', 150.00, 3);

-- saving goals
INSERT INTO saving_goals (current_amount, description, goal, status, target_amount, account_id)
VALUES (200.00, 'an adventure to Nice with Obelix', 'holiday', 'ACTIVE', 750.00, 2);
INSERT INTO saving_goals (current_amount, description, goal, status, target_amount, account_id)
VALUES (50.00, 'new shoes and shirts', 'clothes', 'INACTIVE', 75.00, 2);

INSERT INTO saving_goals (current_amount, description, goal, status, target_amount, account_id)
VALUES (150.00, 'water scooter for Katrien', 'birthday gift', 'ACTIVE', 250.00, 3);
INSERT INTO saving_goals (current_amount, description, goal, status, target_amount, account_id)
VALUES (120.00, 'car reparation: new tires', 'car maintenance', 'COMPLETED', 120.00, 3);

-- transactions account 2 Asterix!
INSERT INTO transactions (amount, category, date, description, payee, payment_method, type, account_id)
VALUES (25.00, 'groceries', '2023-07-28', 'potatoes and meat', 'Supermarkt Galia', 'OTHER','EXPENSE', 2);
INSERT INTO transactions (amount, category, date, description, payee, payment_method, type, account_id)
VALUES (300.00, 'work', '2023-08-28', 'selling menhirs', 'Obelix & Co', 'BANK','INCOME', 2);

INSERT INTO transactions (amount, category, date, description, payee, payment_method, type, account_id)
VALUES (30.00, 'groceries', '2023-07-28', 'buying fish', 'Ekoplaza, Galia', 'CASH','EXPENSE', 2);
INSERT INTO transactions (amount, category, date, description, payee, payment_method, type, account_id)
VALUES (500.00, 'work', '2023-09-28', 'selling menhirs', 'Obelix & Co', 'BANK','INCOME', 2);

-- transactions account 3 Donald12
INSERT INTO transactions (amount, category, date, description, payee, payment_method, type, account_id)
VALUES (57.00, 'groceries', '2023-07-28', 'potatoes and desserts', 'Supermarkt Duck duck go', 'OTHER','EXPENSE', 3);
INSERT INTO transactions (amount, category, date, description, payee, payment_method, type, account_id)
VALUES (250.00, 'work', '2023-08-28', 'selling feathers', 'Duck feathers & Co', 'BANK','INCOME', 3);

INSERT INTO transactions (amount, category, date, description, payee, payment_method, type, account_id)
VALUES (50.00, 'groceries', '2023-07-28', 'buying fruit, vegetables and a cake', 'Ekoplaza, Duck Cakes', 'CASH','EXPENSE', 3);
INSERT INTO transactions (amount, category, date, description, payee, payment_method, type, account_id)
VALUES (500.00, 'work', '2023-08-28', 'working for Dagobert Duck', 'Dagobert Duck', 'BANK','INCOME', 3);
