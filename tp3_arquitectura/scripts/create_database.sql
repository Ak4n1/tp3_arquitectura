
CREATE DATABASE IF NOT EXISTS tp3_arquitectura;

CREATE USER IF NOT EXISTS 'tp3_user'@'localhost' IDENTIFIED BY 'tp3_password';

GRANT ALL PRIVILEGES ON tp3_arquitectura.* TO 'tp3_user'@'localhost';

FLUSH PRIVILEGES;


