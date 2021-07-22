CREATE TABLE users (
    user_id    VARCHAR(36) PRIMARY KEY,
    username   VARCHAR(255) NOT NULL,
    password   VARCHAR(500) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    interests  VARCHAR(255) NOT NULL,
    city       VARCHAR(255) NOT NULL,
    age        INT          NOT NULL,
    gender        VARCHAR(1)   NOT NULL DEFAULT 'M'
);

