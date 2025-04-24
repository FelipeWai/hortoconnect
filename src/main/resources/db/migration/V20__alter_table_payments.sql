-- 1. Alterar o tipo da coluna user_id para BIGINT
ALTER TABLE payments
ALTER COLUMN user_id TYPE BIGINT USING user_id::BIGINT;

-- 2. Adicionar a chave estrangeira para a tabela users
ALTER TABLE payments
    ADD CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE;

-- 3. Adicionar UNIQUE constraint para garantir apenas um pagamento por usuário
ALTER TABLE payments
    ADD CONSTRAINT uc_user UNIQUE (user_id);