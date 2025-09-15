-- Drop table if exists
DROP TABLE IF EXISTS "ms-data-master-config-svc".t_otp_config;

-- Drop indexes if exist
DROP INDEX IF EXISTS "ms-data-master-config-svc".idx_otp_config_created_at;
DROP INDEX IF EXISTS "ms-data-master-config-svc".idx_otp_config_updated_at;

-- Create table
CREATE TABLE "ms-data-master-config-svc".t_otp_config (
                                                        id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                                        mail_host VARCHAR(255),
                                                        mail_port INT,
                                                        mail_username VARCHAR(255),
                                                        mail_password VARCHAR(255),
                                                        mail_from_address VARCHAR(255),
                                                        mail_from_name VARCHAR(255),
                                                        mail_encryption VARCHAR(10) DEFAULT 'ssl',
                                                        created_at TIMESTAMP DEFAULT now(),
                                                        updated_at TIMESTAMP DEFAULT now()
);

-- Create indexes
CREATE INDEX idx_otp_config_created_at
    ON "ms-data-master-config-svc".t_otp_config(created_at);

CREATE INDEX idx_otp_config_updated_at
    ON "ms-data-master-config-svc".t_otp_config(updated_at);

-- CREATE EXTENSION for UUID (only once per DB)
CREATE EXTENSION IF NOT EXISTS "pgcrypto";
