-- 1. Renomear payment_id para external_payment_id
ALTER TABLE payments RENAME COLUMN payment_id TO external_payment_id;

-- 2. Remover expiration_date da tabela de pagamentos
ALTER TABLE payments DROP COLUMN expiration_date;

-- 3. Garantir relacionamento OneToOne entre usuários e assinaturas
-- Adiciona UNIQUE constraint em user_id na tabela de subscriptions
ALTER TABLE subscriptions
    ADD CONSTRAINT uq_subscriptions_user_id UNIQUE (user_id);

-- 4. Criar coluna subscription_id em payments para referenciar assinatura (relacionamento reverso)
ALTER TABLE payments
    ADD COLUMN subscription_id BIGINT;

ALTER TABLE payments
    ADD CONSTRAINT fk_payments_subscription FOREIGN KEY (subscription_id)
        REFERENCES subscriptions(id);

-- 5. Criar índice por plan_id para melhorar performance de consulta por plano
CREATE INDEX idx_payments_plan_id ON payments(plan_id);