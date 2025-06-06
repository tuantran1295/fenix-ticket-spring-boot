
-- -- Grant connect on target database
-- GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES ON DATABASE fenixdb TO fenix;

-- Schema
-- CREATE TABLE IF NOT EXISTS users (
--                                      id SERIAL PRIMARY KEY,
--                                      username VARCHAR(50) NOT NULL UNIQUE,
--     password VARCHAR(100) NOT NULL
--     );
--
-- CREATE TABLE IF NOT EXISTS tickets (
--                                        id SERIAL PRIMARY KEY,
--                                        title VARCHAR(250) NOT NULL,
--     description VARCHAR(250) NOT NULL,
--     status VARCHAR(250) NOT NULL
--     );

-- Privileges: give fenix full rights on all future tables,
-- and on all existing tables, explicit for migration idempotence.

-- -- GRANT ON TABLES IN schema public
-- GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO fenix;
-- ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO fenix;
--
-- -- Grant on sequences (for SERIAL PK)
-- GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO fenix;
-- ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT USAGE, SELECT ON SEQUENCES TO fenix;

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

    ON CONFLICT (username) DO NOTHING;

