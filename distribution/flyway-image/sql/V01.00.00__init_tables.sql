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


CREATE TABLE friends (
    from_user_id VARCHAR(36) NOT NULL,
    to_user_id   VARCHAR(36) NOT NULL,

    FOREIGN KEY (from_user_id)
        REFERENCES users(user_id),
    FOREIGN KEY (to_user_id)
        REFERENCES users(user_id)
);


