SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS projects (
    id int PRIMARY KEY auto_increment,
    title VARCHAR,
    description VARCHAR,
);

CREATE TABLE IF NOT EXISTS services (
    id int PRIMARY KEY auto_increment,
    type VARCHAR,
    detail VARCHAR,
    projectId INTEGER
);