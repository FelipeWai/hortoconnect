CREATE TABLE favorites (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGSERIAL NOT NULL,
    product_id BIGSERIAL NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE
);