CREATE TABLE IF NOT EXISTS column_tb (
    id BIGSERIAL NOT NULL,
    name VARCHAR(100) NOT NULL,
    column_type VARCHAR(30) NOT NULL,
    column_order INT NOT NULL UNIQUE,
    board_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (board_id) REFERENCES board_tb(id) ON DELETE CASCADE,
    CHECK (column_type IN ('INITIAL', 'CANCELLATION', 'FINAL', 'PENDING'))
);