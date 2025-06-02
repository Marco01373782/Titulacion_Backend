CREATE TABLE IF NOT EXISTS session_activity_result (
    id SERIAL PRIMARY KEY,
    sesion_id INTEGER NOT NULL,
    activity_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    result TEXT,
    completed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (sesion_id) REFERENCES sesion(id) ON DELETE CASCADE,
    FOREIGN KEY (activity_id) REFERENCES activity(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
    );
