CREATE DATABASE IF NOT EXISTS tasktracker_db;
USE tasktracker_db;
CREATE TABLE IF NOT EXISTS task (
  id VARCHAR(36) NOT NULL
);
CREATE USER 'taskuser'@'%' IDENTIFIED BY 'taskpass';
GRANT ALL ON tasktracker_db.* TO 'taskuser'@'%';