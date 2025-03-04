ALTER TABLE "community"
DROP CONSTRAINT community_pkey,
DROP COLUMN id;

ALTER TABLE "community"
ADD COLUMN id bigserial not null PRIMARY KEY;

ALTER TABLE "community"
ALTER COLUMN insee_code DROP NOT NULL;