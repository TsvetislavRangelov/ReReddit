CREATE TABLE user
(
    id   int     NOT NULL AUTO_INCREMENT,
    username varchar(50) NOT NULL,
    role varchar(10),
    PRIMARY KEY (id),
    UNIQUE (id),
    UNIQUE (username)
);

CREATE TABLE post
(
    id         int NOT NULL AUTO_INCREMENT,
    author_id        int NOT NULL,
    header       varchar(200),
    body TEXT,
    ups int,
    downs int,
    comments int,
    PRIMARY KEY (id),
    FOREIGN KEY (author_id) REFERENCES user (id)
);