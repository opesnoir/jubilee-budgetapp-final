INSERT INTO users (username, password, email, enabled, apikey) VALUES ('AdminAppel!', '$2a$12$9qsU207uA2.YFl573A/qS.dJvm5FwWyzUMDjhMdijPYIJdoXdWjnq','user1@test.nl', TRUE, 'apikeyvoornu');

INSERT INTO authorities (username, authority) VALUES ('AdminAppel!', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('AdminAppel!', 'ROLE_ADMIN');