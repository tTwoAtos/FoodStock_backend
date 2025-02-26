DROP TABLE IF EXISTS category;
CREATE TABLE category (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  CONSTRAINT UK_46ccwnsi9409t36lurvtyljak UNIQUE (name)
);

DROP TABLE IF EXISTS category_to_community;
CREATE TABLE category_to_community (
  id BIGSERIAL PRIMARY KEY,
  category_id BIGINT NOT NULL,
  community_id VARCHAR(255) NOT NULL,
  preferencies_factor BIGINT NOT NULL
);

DROP TABLE IF EXISTS community;
CREATE TABLE community (
  id BIGINT NOT NULL,
  name VARCHAR(75),
  insee_code VARCHAR(255) PRIMARY KEY
);

DROP TABLE IF EXISTS city;
CREATE TABLE city (
  insee_code VARCHAR(255) PRIMARY KEY,
  name VARCHAR(75),
  postal_code VARCHAR(5)
);

INSERT INTO city (insee_code, name, postal_code) VALUES ('MTP', 'Montpellier', '34000');

DROP TABLE IF EXISTS product;
CREATE TABLE product (
  eancode VARCHAR(255) PRIMARY KEY,
  name VARCHAR(255),
  nb_added BIGINT NOT NULL,
  nb_scanned BIGINT NOT NULL,
  thumbnail VARCHAR(255)
);

DROP TABLE IF EXISTS product_to_category;
CREATE TABLE product_to_category (
  category_id BIGINT NOT NULL,
  product_id VARCHAR(20) NOT NULL,
  PRIMARY KEY (category_id, product_id)
);

DROP TABLE IF EXISTS role;
CREATE TABLE role (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(30) NOT NULL,
  slug VARCHAR(20) NOT NULL,
  CONSTRAINT UK_8sewwnpamngi6b1dwaa88askk UNIQUE (name),
  CONSTRAINT UK_288r9rj0foie0j86khgpq2y6d UNIQUE (slug)
);

INSERT INTO role (id, name, slug) VALUES (1, 'Admin', 'ROLE_ADMIN'), (2, 'User', 'ROLE_USER');

DROP TABLE IF EXISTS "user";
CREATE TABLE "user" (
  id BIGSERIAL PRIMARY KEY,
  birthdate DATE,
  email VARCHAR(255) NOT NULL,
  firstname VARCHAR(75),
  gender INT NOT NULL,
  lastname VARCHAR(75) NOT NULL,
  logged_in_community_id VARCHAR(255),
  password VARCHAR(255),
  role_id BIGINT NOT NULL,
  CONSTRAINT UK_ob8kqyqqgmefl0aco34akdtpe UNIQUE (email),
  CONSTRAINT FKn82ha3ccdebhokx3a8fgdqeyy FOREIGN KEY (role_id) REFERENCES role (id)
);

DROP TABLE IF EXISTS user_to_community;
CREATE TABLE user_to_community (
  id BIGSERIAL PRIMARY KEY,
  community_id VARCHAR(255),
  user_id BIGINT,
  CONSTRAINT FK19a8padw6nm0wkk3vbvo4fd9e FOREIGN KEY (user_id) REFERENCES "user" (id)
);