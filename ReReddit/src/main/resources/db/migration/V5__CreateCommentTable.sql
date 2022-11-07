CREATE TABLE comment
(
    id         int NOT NULL AUTO_INCREMENT,
    author_id        int NOT NULL,
    body  TEXT,
    post_id int NOT NULL,
    ups int,
    downs int,
    parent_id int NOT NULL,
    created_at DATE NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (author_id) REFERENCES user (id),
    FOREIGN KEY (post_id) REFERENCES post (id),
    FOREIGN KEY (parent_id) REFERENCES comment (id),
    UNIQUE(id)
);