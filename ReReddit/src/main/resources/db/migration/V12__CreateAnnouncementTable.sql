CREATE TABLE announcement
(
    id   int     NOT NULL AUTO_INCREMENT,
    sender int NOT NULL,
    title varchar(200) NOT NULL,
    body varchar(200) NOT NULL,
    timestamp DATETIME NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (id),
    UNIQUE (timestamp)
);