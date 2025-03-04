DROP TABLE IF EXISTS emplacement;
CREATE TABLE emplacement (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(75),
  community_id BIGINT,
  CONSTRAINT fk_community FOREIGN KEY (community_id) REFERENCES community(id)
);
