CREATE TABLE IF NOT EXISTS block_tb (
    id BIGSERIAL NOT NULL,
    block_description VARCHAR(250) NOT NULL,
    block_date VARCHAR(50) NULL,
    unblock_description VARCHAR(250) NULL,
    unblock_date VARCHAR(50) NULL,
    card_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (card_id) REFERENCES card_tb (id) ON DELETE CASCADE
);