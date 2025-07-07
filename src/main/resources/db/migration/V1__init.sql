
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
    user_id INTEGER,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
    );

-- Actividades y sesiones
CREATE TABLE IF NOT EXISTS activity (
    id SERIAL PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    description TEXT,
    type VARCHAR(50) NOT NULL,
    difficulty VARCHAR(50) NOT NULL,
    resource_url VARCHAR(250),
    max_score DECIMAL(5, 2) NOT NULL DEFAULT 100
    );

CREATE TABLE IF NOT EXISTS sesion (
    id SERIAL PRIMARY KEY,
    patient_id INTEGER,
    title VARCHAR(150) NOT NULL,
    difficulty VARCHAR(50) NOT NULL,
    description TEXT,
    FOREIGN KEY (patient_id) REFERENCES patient(id) ON DELETE CASCADE
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
     result DECIMAL(5,2),
     completed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     duration_seconds INTEGER, -- tiempo que tardó en realizar la actividad
     FOREIGN KEY (sesion_id) REFERENCES sesion(id) ON DELETE CASCADE,
    FOREIGN KEY (activity_id) REFERENCES activity(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
    );
CREATE TABLE IF NOT EXISTS sesion_usuario (
    id SERIAL PRIMARY KEY,
    sesion_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'BLOQUEADA',
    started_at TIMESTAMP,
    ended_at TIMESTAMP,
    session_duration_seconds INTEGER,
    result DECIMAL(5,2),
    mode VARCHAR(50),
    date DATE,
    UNIQUE(sesion_id, user_id),
    FOREIGN KEY (sesion_id) REFERENCES sesion(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
    );
CREATE TABLE IF NOT EXISTS question (
    id SERIAL PRIMARY KEY,
    activity_id INTEGER NOT NULL,
    question_text TEXT NOT NULL,
    FOREIGN KEY (activity_id) REFERENCES activity(id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS answer_option (
    id SERIAL PRIMARY KEY,
    question_id INTEGER NOT NULL,
    answer_text TEXT NOT NULL,
    is_correct BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (question_id) REFERENCES question(id) ON DELETE CASCADE
    );

