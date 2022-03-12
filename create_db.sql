CREATE TABLE app_user (
  id SERIAL,
  username varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  PRIMARY KEY (id)
);