--
CREATE TABLE IF NOT EXISTS customers (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    cpf BIGINT NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    customer_type customer_type_enum NOT NULL,
    balance NUMERIC(10, 2) DEFAULT 0.0 NOT NULL
);

--
CREATE UNIQUE INDEX IF NOT EXISTS cpf_index ON customers (cpf);

--
CREATE UNIQUE INDEX IF NOT EXISTS email_index ON customers (email);

--
CREATE TABLE IF NOT EXISTS transactions (
    id SERIAL PRIMARY KEY,
    payer INT,
    payee INT,
    value DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    FOREIGN KEY (payer) REFERENCES customers(id),
    FOREIGN KEY (payee) REFERENCES customers(id)
);