CREATE TABLE Cats
(
    id            SERIAL PRIMARY KEY,
    name          VARCHAR(255),
    birthday_date DATE,
    breed         VARCHAR(255),
    color         varchar(255),
    master_id     BIGINT
);

CREATE TABLE friends
(
    first_friend_id  BIGINT,
    second_friend_id BIGINT,
    FOREIGN KEY (first_friend_id) REFERENCES Cats (id),
    FOREIGN KEY (second_friend_id) REFERENCES Cats (id)
);

CREATE TABLE Masters
(
    id            SERIAL PRIMARY KEY,
    name          VARCHAR(255),
    birthday_date DATE
);

