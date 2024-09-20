-- Create table for events
CREATE TABLE events (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    content TEXT,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    type VARCHAR(255),
    photo_url VARCHAR(255),
    created_at DATE DEFAULT CURRENT_DATE,
    updated_at DATE DEFAULT CURRENT_DATE,
    club_id BIGINT NOT NULL,
    CONSTRAINT fk_event_club FOREIGN KEY (club_id) REFERENCES clubs (id) ON DELETE CASCADE
);
