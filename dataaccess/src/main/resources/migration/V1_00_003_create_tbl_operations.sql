CREATE TYPE IF NOT EXISTS OP_TYPE AS ENUM ('deposit', 'withdraw', 'transfer');

create table operations
(
    id             UUID PRIMARY KEY                  DEFAULT uuidv7(),
    account_id     UUID                     NOT NULL,
    operation_type OP_TYPE                  NOT NULL,
    amount         NUMERIC(10, 2)           NOT NULL,
    operation_at   timestamp with time zone NOT NULL default now(),
    CONSTRAINT     fk_op_acc_id FOREIGN_KEY (account_id) REFERENCES accounts(id) ON DELETE CASCADE DEFERRABLE
);

create table operations_aud
(
    id             UUID                     NOT NULL,
    account_id     UUID                     NOT NULL,
    operation_type OP_TYPE                  NOT NULL,
    amount         NUMERIC(10, 2)           NOT NULL,
    rev            timestamp with time zone NOT NULL default now(),
    revtype        integer
);