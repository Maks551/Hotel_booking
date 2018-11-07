DELETE FROM RESERVED_ROOMS;
DELETE FROM ROOMS;
DELETE FROM USERS;
ALTER SEQUENCE IF EXISTS global_seq RESTART WITH 100000;

INSERT INTO USERS (NAME, EMAIL, PASSWORD) VALUES
  ('User1', 'first@gmail.com', 'password1'),
  ('User2', 'second@gmail.com', 'password2'),
  ('User3', 'third@gmail.com', 'password3'),
  ('User4', 'fourth@gmail.com', 'password4');

INSERT INTO ROOMS (NUMBER, CATEGORY, PRICE, BREAKFAST, CLEANING) VALUES
  (101, 1, 1700, false, true ),
  (102, 1, 1650, true , false ),
  (103, 2, 860, false, false ),
  (104, 2, 900, true , false ),
  (201, 1, 2100, true , true ),
  (202, 1, 2400, true , false ),
  (203, 2, 930, false, true ),
  (204, 3, 450, false, false );

INSERT INTO RESERVED_ROOMS (START_DATE, END_DATE, USER_ID, ROOM_ID) VALUES
  ('2018-11-07', '2018-11-07', 100000, 100004),
  ('2018-11-09', '2018-11-11', 100001, 100010),
  ('2018-11-07', '2018-11-08', 100002, 100011),
  ('2018-11-08', '2018-11-10', 100003, 100007),
  ('2018-11-11', '2018-11-11', 100000, 100004),
  ('2018-11-15', '2018-11-15', 100000, 100008);