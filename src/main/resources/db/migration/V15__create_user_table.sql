CREATE TABLE IF NOT EXISTS user (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) ,
    password VARCHAR(255) ,
    phone VARCHAR(255) ,
    address VARCHAR(255)
);