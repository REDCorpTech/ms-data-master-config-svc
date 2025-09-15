-- Drop table if exists
DROP TABLE IF EXISTS "ms-data-master-config-svc".t_config_enum;

-- Drop indexes if exist
DROP INDEX IF EXISTS "ms-data-master-config-svc".idx_config_enum_created_at;
DROP INDEX IF EXISTS "ms-data-master-config-svc".idx_config_enum_updated_at;

-- Create table
CREATE TABLE "ms-data-master-config-svc".t_config_enum (
                                                           id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                                           name VARCHAR,
                                                           description VARCHAR,
                                                           type VARCHAR,
                                                           created_by VARCHAR,
                                                           created_at TIMESTAMP,
                                                           updated_by VARCHAR,
                                                           updated_at TIMESTAMP
);

-- Create indexes
CREATE INDEX idx_config_enum_created_at
    ON "ms-data-master-config-svc".t_config_enum(created_at);

CREATE INDEX idx_config_enum_updated_at
    ON "ms-data-master-config-svc".t_config_enum(updated_at);

-- CREATE EXTENSION for UUID (note: extension only once per DB, not per schema)
CREATE EXTENSION IF NOT EXISTS "pgcrypto";
