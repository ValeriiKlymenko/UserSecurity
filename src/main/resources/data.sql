INSERT INTO roles (id, name) VALUES (1, 'ADMIN');
INSERT INTO roles (id, name) VALUES (2, 'USER');

INSERT INTO users (id, first_name, last_name, email, password, role_id) VALUES (5, 'Nick', 'Green', 'nick@mail.com', '$2a$10$CJgEoobU2gm0euD4ygru4ukBf9g8fYnPrMvYk.q0GMfOcIDtUhEwC', 2);
INSERT INTO users (id, first_name, last_name, email, password, role_id) VALUES (6, 'Nora', 'White', 'nora@mail.com', '$2a$10$yYQaJrHzjOgD5wWCyelp0e1Yv1KEKeqUlYfLZQ1OQvyUrnEcX/rOy', 2);
INSERT INTO users (id, first_name, last_name, email, password, role_id) VALUES (4, 'Mike', 'Brown', 'mike@mail.com', '$2a$10$CdEJ2PKXgUCIwU4pDQWICuiPjxb1lysoX7jrN.Y4MTMoY9pjfPALO', 1);