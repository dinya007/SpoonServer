INSERT INTO discounts (latitude,  longitude, location_name, description, sale, owner_login)
  VALUES (55.754695, 37.621527, 'ReStore', 'Скидки на планшеты и ноутбуки', 10, 'login1');
INSERT INTO discounts (latitude,  longitude, location_name, description, sale, owner_login)
  VALUES (55.750763, 37.596108, 'Starbucks', 'Кофе по цене чая', 50, 'login2');
INSERT INTO discounts (latitude,  longitude, location_name, description, sale, owner_login)
  VALUES (55.756852, 37.614048, 'Vertu', 'Шиш вам, а не скидки', 0, 'login3');

INSERT INTO owners (name,  password, email, is_confirmed)
  VALUES ('login1', 'password1','e1@mail.com', false);
INSERT INTO owners (name,  password, email, is_confirmed)
  VALUES ('login2', 'password2','e2@mail.com', false);
INSERT INTO owners (name,  password, email, is_confirmed)
  VALUES ('login3', 'password3','e3@mail.com', true);