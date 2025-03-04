CREATE TABLE product_to_community (
    id SERIAL PRIMARY KEY,
    product_id VARCHAR(255) NOT NULL,
    community_id int NOT NULL,
    emplacement_id int,
    qte BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    -- Clés étrangères
    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES product(eancode) ON DELETE CASCADE,
    CONSTRAINT fk_community FOREIGN KEY (community_id) REFERENCES community(id) ON DELETE CASCADE,
    CONSTRAINT fk_emplacement FOREIGN KEY (emplacement_id) REFERENCES emplacement(id) ON DELETE SET NULL
);