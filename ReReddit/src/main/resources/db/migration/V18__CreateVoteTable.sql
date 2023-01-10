CREATE TABLE vote
(
    id        int         NOT NULL AUTO_INCREMENT,
    user_id   int         NOT NULL,
    post_id   int         NOT NULL,
    type char(1)          NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (post_id) REFERENCES post (id)
);