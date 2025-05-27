
CREATE TABLE IF NOT EXISTS parentesco (
    id SERIAL PRIMARY KEY,
    name_parentesco VARCHAR(100) NOT NULL
    );

CREATE TABLE IF NOT EXISTS gender (
     id SERIAL PRIMARY KEY,
     name_gender VARCHAR(50) NOT NULL
    );

CREATE TABLE IF NOT EXISTS difficulty (
    id SERIAL PRIMARY KEY,
    name_difficulty VARCHAR(50) NOT NULL
    );

CREATE TABLE IF NOT EXISTS activity_type (
    id SERIAL PRIMARY KEY,
    name_type VARCHAR(100) NOT NULL
    );


CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    roles VARCHAR(50) DEFAULT 'user',
    parentesco_id INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (parentesco_id) REFERENCES parentesco(id)
    );


CREATE TABLE IF NOT EXISTS patient (
    id SERIAL PRIMARY KEY,
    firstname VARCHAR(100) NOT NULL,
    secondname VARCHAR(100),
    surname VARCHAR(100) NOT NULL,
    age INTEGER CHECK (age >= 0),
    photo_url TEXT,
    gender_id INTEGER,
    users_id INTEGER,
    FOREIGN KEY (gender_id) REFERENCES gender(id),
    FOREIGN KEY (users_id) REFERENCES users(id)
    );


CREATE TABLE IF NOT EXISTS sesion (
    id SERIAL PRIMARY KEY,
    patient_id INTEGER NOT NULL,
    title VARCHAR(150) NOT NULL,
    date DATE NOT NULL,
    difficulty_id INTEGER NOT NULL,
    result TEXT,
    mode VARCHAR(50),
    description TEXT,
    FOREIGN KEY (patient_id) REFERENCES patient(id) ON DELETE CASCADE,
    FOREIGN KEY (difficulty_id) REFERENCES difficulty(id)
    );


CREATE TABLE IF NOT EXISTS activity (
    id SERIAL PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    description TEXT,
    type_id INTEGER,
    difficulty_id INTEGER,
    resource_url TEXT,
    FOREIGN KEY (type_id) REFERENCES activity_type(id),
    FOREIGN KEY (difficulty_id) REFERENCES difficulty(id)
    );


CREATE TABLE IF NOT EXISTS session_activity (
    sesion_id INTEGER,
    activity_id INTEGER,
    PRIMARY KEY (sesion_id, activity_id),
    FOREIGN KEY (sesion_id) REFERENCES sesion(id) ON DELETE CASCADE,
    FOREIGN KEY (activity_id) REFERENCES activity(id) ON DELETE CASCADE
    );
