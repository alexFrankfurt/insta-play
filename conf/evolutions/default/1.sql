# Users SCHEMA

# --- !Ups

CREATE TABLE Users (
  login VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  is_admin boolean NOT NULL,
  PRIMARY KEY (login)
);

# --- !Downs

DROP TABLE Users;
