CREATE TABLE subcategory_quality (
    subcategory_id BIGSERIAL NOT NULL,
    quality_id BIGSERIAL NOT NULL,
    PRIMARY KEY (subcategory_id, quality_id),
    CONSTRAINT fk_subcategory_quality_subcategory FOREIGN KEY (subcategory_id) REFERENCES subcategory(id) ON DELETE CASCADE,
    CONSTRAINT fk_subcategory_quality_quality FOREIGN KEY (quality_id) REFERENCES quality(id) ON DELETE CASCADE
);

CREATE TABLE subcategory_size (
    subcategory_id BIGSERIAL NOT NULL,
    size_id BIGSERIAL NOT NULL,
    PRIMARY KEY (subcategory_id, size_id),
    CONSTRAINT fk_subcategory_size_subcategory FOREIGN KEY (subcategory_id) REFERENCES subcategory(id) ON DELETE CASCADE,
    CONSTRAINT fk_subcategory_size_size FOREIGN KEY (size_id) REFERENCES size(id) ON DELETE CASCADE
);