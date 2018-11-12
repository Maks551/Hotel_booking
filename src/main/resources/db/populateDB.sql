DELETE FROM USER_ROLES;
DELETE FROM BOOKINGS;
DELETE FROM ROOMS;
DELETE FROM USERS;
ALTER SEQUENCE IF EXISTS global_seq RESTART WITH 100000;

INSERT INTO USERS (NAME, EMAIL, PASSWORD) VALUES
  ('User1', 'first@gmail.com', 'password1'),
  ('User2', 'second@gmail.com', 'password2'),
  ('User3', 'third@gmail.com', 'password3'),
  ('Admin1', 'admin@gmail.com', 'password4');

INSERT INTO ROOMS (NUMBER, CATEGORY, PRICE) VALUES
  (101, 1, 1700),
  (102, 1, 1650),
  (103, 2, 860),
  (104, 2, 900),
  (201, 1, 2100),
  (202, 1, 2400),
  (203, 2, 930),
  (204, 3, 450);

INSERT INTO BOOKINGS (START_DATE, END_DATE, BREAKFAST, CLEANING, ALL_PRICE, USER_ID, ROOM_ID) VALUES
  ('2018-11-07', '2018-11-08', false, false, 1700, 100000, 100004),
  ('2018-11-09', '2018-11-11', true, true, 2410, 100001, 100010),
  ('2018-11-07', '2018-11-08', true, false, 650, 100002, 100011),
  ('2018-11-08', '2018-11-10', true, true, 2350, 100003, 100007),
  ('2018-11-11', '2018-11-12', true, false, 1900, 100000, 100004),
  ('2018-11-14', '2018-11-15', true, false, 2300, 100000, 100008);

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_USER', 100001),
  ('ROLE_USER', 100002),
  ('ROLE_USER', 100003),
  ('ROLE_ADMIN', 100003);