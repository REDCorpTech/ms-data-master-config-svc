-- Drop table if exists
DROP TABLE IF EXISTS "ms-data-master-config-svc".t_otp_send;

-- Create table
CREATE TABLE "ms-data-master-config-svc".t_otp_send (
                                    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                    email_send VARCHAR(255) NOT NULL,
                                    otp_number VARCHAR(6) NOT NULL,
                                    expiry_time TIMESTAMP NOT NULL,
                                    created_at TIMESTAMP DEFAULT now()
);

-- Create index for faster queries by email + created_at
CREATE INDEX idx_otp_send_email_created_at
    ON "ms-data-master-config-svc".t_otp_send(email_send, created_at);

-- Ensure UUID generator extension exists
CREATE EXTENSION IF NOT EXISTS "pgcrypto";
