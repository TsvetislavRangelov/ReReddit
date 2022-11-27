CREATE TABLE refresh_token
(
    id        int         NOT NULL AUTO_INCREMENT,
    refresh_user_id   int         NOT NULL,
    token varchar(200) NOT NULL,
    expiry_date DATE NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (refresh_user_id, token),
    FOREIGN KEY (refresh_user_id) REFERENCES user (id)
);