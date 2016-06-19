CREATE KEYSPACE spoon
WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

CREATE TABLE discounts (
  longitude double,
  latitude double,
  location_name text,
  description text,
  sale int,
  owner_login text,
  PRIMARY KEY (longitude, latitude)
);

CREATE TABLE owners (
  login text,
  password text,
  email text,
  is_confirmed boolean,
  PRIMARY KEY (login)
);