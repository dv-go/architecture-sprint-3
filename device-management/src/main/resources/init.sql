CREATE TABLE IF NOT EXISTS modules (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    house_id UUID NOT NULL
    );

CREATE TABLE IF NOT EXISTS device_types (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS devices (
    device_id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    device_type_id UUID NOT NULL,
    module_id UUID NOT NULL,
    target_value DOUBLE PRECISION NOT NULL,
    current_value DOUBLE PRECISION NOT NULL,
    is_on BOOLEAN NOT NULL,
    FOREIGN KEY (device_type_id) REFERENCES device_types(id),
    FOREIGN KEY (module_id) REFERENCES modules(id)
    );

INSERT INTO device_types (id, name) VALUES
('f8b12f60-5b19-4c7b-8f12-5f7c8b5d5b6d', 'Датчик температуры'),
('e3c7f9f5-b7b5-4f1f-9c4f-0b0e08bba3b8', 'Камера видеонаблюдения'),
('c2d9a3c8-0172-411e-b7c4-501dff47340d', 'Датчик света'),
('b5e0b9e5-66a1-40d6-b0f6-01f48ef407f2', 'Датчик состояния ворот')
ON CONFLICT (id) DO NOTHING;

INSERT INTO modules (id, name, house_id) VALUES
('1e5f6e6b-1234-4bba-b3e3-dc8f2197b11a', 'Модуль контроля системы отопления', '123e4567-e89b-12d3-a456-426614174000'),
('1e5f6e6b-5678-4bba-b3e3-dc8f2197b11b', 'Модуль управления воротами', '123e4567-e89b-12d3-a456-426614174000'),
('1e5f6e6b-9012-4bba-b3e3-dc8f2197b11c', 'Модуль видеонаблюдения', '123e4567-e89b-12d3-a456-426614174000'),
('1e5f6e6b-cdef-4bba-b3e3-dc8f2197b11d', 'Модуль управления светом', '123e4567-e89b-12d3-a456-426614174000')
ON CONFLICT (id) DO NOTHING;

INSERT INTO devices (device_id, name, device_type_id, module_id, target_value, current_value, is_on) VALUES
('8f14e45f-eeb1-44e3-b2e8-5a61e6dcdc22', 'Датчик температуры', 'f8b12f60-5b19-4c7b-8f12-5f7c8b5d5b6d', '1e5f6e6b-1234-4bba-b3e3-dc8f2197b11a', 22.0, 21.5, TRUE)
ON CONFLICT (device_id) DO NOTHING;