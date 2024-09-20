-- Insert roles
INSERT INTO roles (name) VALUES
('ADMIN'),
('USER');

-- Insert users
INSERT INTO users (username, email, password) VALUES
('admin_user', 'admin@clubcompass.com', '$2a$12$1ar4EmyojRbTMxJBR8s5ueLOpmAgyA7jxVrSgMEMLkRTSwZm/hwcW'), -- Replace with actual bcrypt hash
('john_doe', 'john.doe@example.com', '$2a$12$GZkXSbWZV1C4Q.eIGn6ESO46raPCXGTF5bjTmNFRZaGp.W8sFwBBO'),    -- Replace with actual bcrypt hash
('jane_doe', 'jane.doe@example.com', '$2a$12$Os1cmfZRgmsDloZ.lFh7rOivksh8se0zmJIbqUPk7hVqylxXJrKby');    -- Replace with actual bcrypt hash

-- Assign roles to users (admin to admin_user and user role to others)
INSERT INTO users_roles (user_id, role_id) VALUES
(1, 1), -- admin_user -> ADMIN
(2, 2), -- john_doe -> USER
(3, 2); -- jane_doe -> USER

-- Insert clubs with images (basketball and chess clubs)
INSERT INTO clubs (title, photo_url, description, created_by, created_at, updated_at) VALUES
('Basketball Club', '/assets/basketball.jpg', 'A club for basketball enthusiasts.', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Chess Club', '/assets/chess.jpg', 'Join us for weekly chess games and tournaments.', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Fitness Club', '/assets/fitness.jpg', 'Stay fit and healthy with our fitness club.', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert events for the clubs with images
INSERT INTO events (name, content, start_time, end_time, type, photo_url, club_id, created_at, updated_at) VALUES
('Basketball Tournament', 'A fun-filled basketball tournament open to all.', '2024-12-10 09:00:00', '2024-12-10 18:00:00', 'Tournament', '/assets/basketball.jpg', 1, CURRENT_DATE, CURRENT_DATE),
('Chess Championship', 'Our annual chess championship for serious players.', '2024-12-05 10:00:00', '2024-12-05 16:00:00', 'Championship', '/assets/chess.jpg', 2, CURRENT_DATE, CURRENT_DATE),
('Fitness Bootcamp', 'Intensive fitness training for all levels.', '2024-11-15 08:00:00', '2024-11-15 12:00:00', 'Workshop', '/assets/fitness.jpg', 3, CURRENT_DATE, CURRENT_DATE);
