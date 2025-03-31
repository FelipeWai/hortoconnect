CREATE TABLE supplier_offer (
    id BIGSERIAL PRIMARY KEY,
    product_id BIGSERIAL NOT NULL,
    supplier_id BIGSERIAL NOT NULL,
    quality_id BIGSERIAL NOT NULL,
    size_id BIGSERIAL NOT NULL,
    min_price DOUBLE PRECISION NOT NULL CHECK (min_price >= 0),
    max_price DOUBLE PRECISION NOT NULL CHECK (max_price >= min_price),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,

    CONSTRAINT fk_supplier_offer_product FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE,
    CONSTRAINT fk_supplier_offer_supplier FOREIGN KEY (supplier_id) REFERENCES suppliers(id) ON DELETE CASCADE,
    CONSTRAINT fk_supplier_offer_quality FOREIGN KEY (quality_id) REFERENCES quality(id) ON DELETE CASCADE,
    CONSTRAINT fk_supplier_offer_size FOREIGN KEY (size_id) REFERENCES size(id) ON DELETE CASCADE
);