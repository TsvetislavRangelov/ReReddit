CREATE TABLE activity_log
(
    id   int     NOT NULL AUTO_INCREMENT,
    timestamp DATETIME NOT NULL,
    profile VARCHAR(200) NOT NULL,
    success BOOLEAN NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (timestamp),
    UNIQUE (profile)
);