-- Seed data
INSERT INTO users (username, password)
VALUES ('admin', 'password')
    ON CONFLICT (username) DO NOTHING;

INSERT INTO tickets (description, status, title)
VALUES ('Example ticket description 1', 'OPEN', 'Issue Example 1'),
       ('Example ticket description 2', 'OPEN', 'Issue Example 2'),
       ('Example ticket description 3', 'CLOSE', 'Issue Example 3'),
       ('Example ticket description 4', 'OPEN', 'Issue Example 4'),
       ('Example ticket description 5', 'OPEN', 'Issue Example 5')

