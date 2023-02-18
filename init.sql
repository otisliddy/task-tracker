CREATE DATABASE IF NOT EXISTS tasktracker_db;
USE tasktracker_db;
CREATE USER 'taskuser'@'%' IDENTIFIED BY 'taskpass';
GRANT ALL ON tasktracker_db.* TO 'taskuser'@'%';
CREATE TABLE IF NOT EXISTS Task (
  id VARCHAR(36) NOT NULL,
  `count` INT(255) NOT NULL,
  sumDurations BIGINT(255) NOT NULL
);