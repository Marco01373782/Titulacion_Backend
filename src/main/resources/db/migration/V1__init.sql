
-- Usuarios y pacientes
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    roles VARCHAR(50) DEFAULT 'user',
    parentesco VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS patient (
    id SERIAL PRIMARY KEY,
    firstname VARCHAR(100) NOT NULL,
    secondname VARCHAR(100),
    surname VARCHAR(100) NOT NULL,
    age INTEGER CHECK (age >= 0),
    gender VARCHAR(50),
    photo_url VARCHAR(250),
    users_id INTEGER
    );

-- Actividades y sesiones
CREATE TABLE IF NOT EXISTS activity (
    id SERIAL PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    description TEXT,
    type VARCHAR(50) NOT NULL,
    difficulty VARCHAR(50) NOT NULL,
    resource_url VARCHAR(250)
    );

CREATE TABLE IF NOT EXISTS sesion (
    id SERIAL PRIMARY KEY,
    patient_id INTEGER,
    title VARCHAR(150) NOT NULL,
    date DATE,
    difficulty VARCHAR(50) NOT NULL,
    result TEXT,
    mode VARCHAR(50),
    description TEXT,
    session_duration_seconds INTEGER,
    started_at TIMESTAMP,
    ended_at TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS session_activity (
    sesion_id INTEGER,
    activity_id INTEGER,
    PRIMARY KEY (sesion_id, activity_id),
    FOREIGN KEY (sesion_id) REFERENCES sesion(id) ON DELETE CASCADE,
    FOREIGN KEY (activity_id) REFERENCES activity(id) ON DELETE CASCADE
    );

-- Resultados por actividad en la sesión
CREATE TABLE IF NOT EXISTS session_activity_result (
     id SERIAL PRIMARY KEY,
     sesion_id INTEGER NOT NULL,
     activity_id INTEGER NOT NULL,
     user_id INTEGER NOT NULL,
     result TEXT,
     completed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     duration_seconds INTEGER, -- tiempo que tardó en realizar la actividad
     FOREIGN KEY (sesion_id) REFERENCES sesion(id) ON DELETE CASCADE,
    FOREIGN KEY (activity_id) REFERENCES activity(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
    );
