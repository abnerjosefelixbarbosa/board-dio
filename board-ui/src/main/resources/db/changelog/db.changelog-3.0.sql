CREATE TABLE IF NOT EXISTS card_tb (
    id BIGSERIAL NOT NULL,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    card_date VARCHAR(50) NOT NULL,
    column_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (column_id) REFERENCES column_tb(id) ON DELETE CASCADE 
);