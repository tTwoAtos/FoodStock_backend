CREATE TABLE IF NOT EXISTS invitations (
    id BIGSERIAL PRIMARY KEY,
    community_id BIGINT NOT NULL,
    email VARCHAR(255) NOT NULL,
    code VARCHAR(50) NOT NULL UNIQUE,
    CONSTRAINT fk_community FOREIGN KEY (community_id) REFERENCES community(id),
    CONSTRAINT fk_user FOREIGN KEY (email) REFERENCES "user"(email),
    CONSTRAINT unique_community_email UNIQUE (community_id, email)
);

ALTER TABLE public."user" RENAME TO users;

ALTER TABLE users
ALTER COLUMN logged_in_community_id TYPE INTEGER USING logged_in_community_id::integer;