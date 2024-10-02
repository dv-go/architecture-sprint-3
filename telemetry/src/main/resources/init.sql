CREATE TABLE IF NOT EXISTS telemetry (
    id UUID PRIMARY KEY,
    device_id UUID NOT NULL,
    data_type VARCHAR(50) NOT NULL,
    data_value DOUBLE PRECISION NOT NULL,
    record_time TIMESTAMP NOT NULL,
    is_on BOOLEAN NOT NULL
    );