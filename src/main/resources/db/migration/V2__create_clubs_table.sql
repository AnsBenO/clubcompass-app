
-- Create table for clubs
CREATE TABLE clubs (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    photo_url VARCHAR(255),
    description TEXT,
    created_by BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT fk_club_user FOREIGN KEY (created_by) REFERENCES users (id) ON DELETE CASCADE
);

