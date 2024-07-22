CREATE TABLE `account` (
  account_id VARCHAR(255) NOT NULL,
   dtype VARCHAR(31),
   account_type VARCHAR(255),
   client_id VARCHAR(255),
   balance DOUBLE PRECISION NOT NULL,
   withdraw_allowed BOOLEAN,
   CONSTRAINT pk_account PRIMARY KEY (account_id)
);

CREATE TABLE `transaction` (
  transaction_id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   transaction_type VARCHAR(255),
   account_id VARCHAR(255),
   client_id VARCHAR(255),
   amount DOUBLE PRECISION NOT NULL,
   created_at BIGINT,
   CONSTRAINT pk_transaction PRIMARY KEY (transaction_id)
);