CREATE TABLE message
(
    id  varchar(200)     NOT NULL,
    sender varchar(200) NOT NULL,
    receiver varchar(200),
    payload TEXT NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (id),
    FOREIGN KEY (sender) REFERENCES user (username),
    FOREIGN KEY (receiver) REFERENCES user (username)
);