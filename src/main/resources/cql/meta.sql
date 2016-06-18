CREATE KEYSPACE spoon
WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

CREATE TABLE discounts (
  longitude double,
  latitude double,
  location_name text,
  description text,
  sale int,
  PRIMARY KEY (longitude, latitude)
);