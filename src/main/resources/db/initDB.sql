DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS rooms;
DROP TABLE IF EXISTS bookings;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE users (
  id            INTEGER DEFAULT global_seq.nextval PRIMARY KEY,
  name          VARCHAR NOT NULL,
  email         VARCHAR NOT NULL,
  password      VARCHAR NOT NULL,
  enabled       BOOL DEFAULT TRUE NOT NULL
);
CREATE UNIQUE INDEX users_email_idx ON users (email);

CREATE TABLE user_roles
(
  user_id          INTEGER NOT NULL,
  role             VARCHAR NOT NULL,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE rooms (
  id            INTEGER DEFAULT global_seq.nextval PRIMARY KEY,
  number        INTEGER NOT NULL,
  category      INTEGER NOT NULL,
  price         INTEGER NOT NULL
);
CREATE UNIQUE INDEX rooms_number_idx ON rooms (number);

CREATE TABLE bookings (
  id            INTEGER DEFAULT global_seq.nextval PRIMARY KEY,
  start_date    DATE DEFAULT now() NOT NULL,
  end_date      DATE NOT NULL,
  all_price     INTEGER NOT NULL,
  breakfast     BOOL default false,
  cleaning      BOOL default false,
  user_id       INTEGER NOT NULL,
  room_id       INTEGER NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
  FOREIGN KEY (room_id) REFERENCES rooms (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX booking_unique_user_datetime_idx
  ON bookings (user_id, start_date);