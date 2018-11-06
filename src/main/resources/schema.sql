DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS rooms;
DROP TABLE IF EXISTS reserved_rooms;

CREATE TABLE users (
  id        INTEGER PRIMARY KEY AUTO_INCREMENT,
  name      VARCHAR NOT NULL,
  email     VARCHAR NOT NULL,
  password  VARCHAR NOT NULL
);
CREATE UNIQUE INDEX users_email_idx ON users (email);

CREATE TABLE rooms (
  id        INTEGER PRIMARY KEY AUTO_INCREMENT,
  number    INTEGER NOT NULL,
  category  INTEGER NOT NULL,
  price     INTEGER NOT NULL,
  breakfast BOOL DEFAULT FALSE,
  cleaning  BOOL DEFAULT FALSE
);
CREATE UNIQUE INDEX rooms_number_idx ON rooms (number);

CREATE TABLE reserved_rooms (
  id          INTEGER PRIMARY KEY AUTO_INCREMENT,
  start_date  TIMESTAMP NOT NULL,
  end_date    TIMESTAMP NOT NULL,
  user_id     INTEGER NOT NULL,
  room_id     INTEGER NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
  FOREIGN KEY (room_id) REFERENCES rooms (id)
);
CREATE UNIQUE INDEX reserved_unique_user_datetime_idx
  ON reserved_rooms (user_id, start_date);