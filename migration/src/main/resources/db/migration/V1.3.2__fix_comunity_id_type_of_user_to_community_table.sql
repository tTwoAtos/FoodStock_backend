ALTER TABLE user_to_community
ALTER COLUMN community_id TYPE INT USING community_id::INT;