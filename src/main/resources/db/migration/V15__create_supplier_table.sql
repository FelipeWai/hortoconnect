CREATE TABLE suppliers (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    cnpj VARCHAR(18) UNIQUE NOT NULL,
    sale_phone VARCHAR(20) NOT NULL,
    contact_phone VARCHAR(20) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);