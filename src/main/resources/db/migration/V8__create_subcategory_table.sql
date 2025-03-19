CREATE TABLE subcategory (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    category_id BIGSERIAL NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_subcategory_category FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE CASCADE
);