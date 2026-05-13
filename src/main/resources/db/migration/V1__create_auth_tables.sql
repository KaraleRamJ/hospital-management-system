CREATE TABLE roles (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,

    username VARCHAR(100) UNIQUE NOT NULL,

    email VARCHAR(150) UNIQUE NOT NULL,

    mobile VARCHAR(15) UNIQUE NOT NULL,

    password VARCHAR(255) NOT NULL,

    account_non_locked BOOLEAN DEFAULT TRUE,

    enabled BOOLEAN DEFAULT TRUE,

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP,

    is_deleted BOOLEAN DEFAULT FALSE,

    version BIGINT DEFAULT 0
);

CREATE TABLE user_roles (

    user_id BIGINT NOT NULL,

    role_id BIGINT NOT NULL,

    PRIMARY KEY (user_id, role_id),

    CONSTRAINT fk_user_roles_user
        FOREIGN KEY (user_id)
        REFERENCES users(id),

    CONSTRAINT fk_user_roles_role
        FOREIGN KEY (role_id)
        REFERENCES roles(id)
);

CREATE TABLE doctors (

    id BIGSERIAL PRIMARY KEY,

    specialization VARCHAR(100) NOT NULL,

    license_number VARCHAR(100) UNIQUE NOT NULL,

    user_id BIGINT UNIQUE NOT NULL,

    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,

    version BIGINT NOT NULL DEFAULT 0,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP,

    CONSTRAINT fk_doctor_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
);

CREATE TABLE patients (

    id BIGSERIAL PRIMARY KEY,

    first_name VARCHAR(100) NOT NULL,

    middle_name VARCHAR(100) NOT NULL,

    last_name VARCHAR(100) NOT NULL,

    aadhar_number VARCHAR(12) UNIQUE NOT NULL,

    date_of_birth DATE,

    user_id BIGINT UNIQUE NOT NULL,

    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,

    version BIGINT NOT NULL DEFAULT 0,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP,

    CONSTRAINT fk_patient_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
);

CREATE INDEX idx_patient_aadhar
ON patients(aadhar_number);

CREATE TABLE refresh_tokens (

    id BIGSERIAL PRIMARY KEY,

    token VARCHAR(500) UNIQUE NOT NULL,

    expiry_date TIMESTAMP NOT NULL,

    revoked BOOLEAN NOT NULL DEFAULT FALSE,

    user_id BIGINT NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP,

    version BIGINT NOT NULL DEFAULT 0,

    CONSTRAINT fk_refresh_token_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
);

CREATE INDEX idx_refresh_token_user
ON refresh_tokens(user_id);

CREATE INDEX idx_refresh_token_token
ON refresh_tokens(token);