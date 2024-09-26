CREATE TABLE IF NOT EXISTS user (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    phoneNumber BIGINT NOT NULL,
    address VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL
);

ALTER TABLE user AUTO_INCREMENT = 1;

INSERT INTO user (phoneNumber, address, password, email, name)
VALUES (0435153999, '123 Main St, Test City', 'test123', 'test@test.com', 'John Doe');

