CREATE TABLE product_picture (
    id BIGSERIAL PRIMARY KEY,
    url VARCHAR(255) NOT NULL UNIQUE,
    product_id BIGSERIAL NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES product(id)
);