CREATE DATABASE simple_board;

CREATE TABLE Board (
    id BIGINT(32) AUTO_INCREMENT NOT NULL,
    board_name VARCHAR(100) NOT NULL,
    status VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE Post (
    id BIGINT(32) AUTO_INCREMENT NOT NULL,
    board_id BIGINT(32) NOT NULL,
    user_name VARCHAR(50) NOT NULL,
    password VARCHAR(4) NOT NULL,
    email VARCHAR(100) NOT NULL,
    status VARCHAR(50) NOT NULL,
    title VARCHAR(100) NOT NULL,
    content TEXT NULL,  -- Keeping this as NULL as specified
    posted_at DATETIME NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (board_id) REFERENCES Board(id)
);


CREATE TABLE Reply (
    id BIGINT(32) AUTO_INCREMENT NOT NULL,
    post_id BIGINT(32) NOT NULL,
    user_name VARCHAR(50) NOT NULL,
    password VARCHAR(4) NOT NULL,
    status VARCHAR(50) NOT NULL,
    title VARCHAR(100) NOT NULL,
    content TEXT NOT NULL,
    replied_at DATETIME NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (post_id) REFERENCES Post(id)
);



