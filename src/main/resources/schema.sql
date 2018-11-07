DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS ROOMS;
DROP TABLE IF EXISTS RESERVED_ROOMS;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE USERS (
  id        INTEGER DEFAULT global_seq.nextval PRIMARY KEY,
  name      VARCHAR NOT NULL,
  email     VARCHAR NOT NULL,
  password  VARCHAR NOT NULL
);
CREATE UNIQUE INDEX users_email_idx ON USERS (email);

CREATE TABLE ROOMS (
  id        INTEGER DEFAULT global_seq.nextval PRIMARY KEY,
  number    INTEGER NOT NULL,
  category  INTEGER NOT NULL,
  price     INTEGER NOT NULL,
  breakfast BOOL DEFAULT FALSE,
  cleaning  BOOL DEFAULT FALSE
);
CREATE UNIQUE INDEX rooms_number_idx ON ROOMS (number);

CREATE TABLE reserved_rooms (
  id          INTEGER DEFAULT global_seq.nextval PRIMARY KEY,
  start_date  DATE NOT NULL,
  end_date    DATE NOT NULL,
  user_id     INTEGER NOT NULL,
  room_id     INTEGER NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
  FOREIGN KEY (room_id) REFERENCES rooms (id)
);
CREATE UNIQUE INDEX reserved_unique_user_datetime_idx
  ON reserved_rooms (user_id, start_date);