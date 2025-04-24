-- 1. Remover a constraint UNIQUE
ALTER TABLE payments
DROP CONSTRAINT uc_user;

-- 2. Remover a chave estrangeira
ALTER TABLE payments
DROP CONSTRAINT fk_user;

-- 3. Reverter o tipo da coluna user_id
ALTER TABLE payments
ALTER COLUMN user_id TYPE VARCHAR(255) USING user_id::VARCHAR;

-- 1. Excluir a tabela payments
DROP TABLE IF EXISTS payments;