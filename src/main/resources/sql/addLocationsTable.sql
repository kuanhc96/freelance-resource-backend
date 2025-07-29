CREATE TABLE locations (
    location_id INT AUTO_INCREMENT PRIMARY KEY,
    location_guid VARCHAR(100) NOT NULL UNIQUE,
    user_guid VARCHAR(100) NOT NULL,
    location_name VARCHAR(255) NOT NULL,
    country VARCHAR(255),
    city VARCHAR(255),
    street VARCHAR(255),
    area_code VARCHAR(50),
    CONSTRAINT fk_user_guid FOREIGN KEY (user_guid) REFERENCES users(user_guid),
    CONSTRAINT uq_user_location_name UNIQUE (user_guid, location_name)
);
ALTER TABLE locations
CHANGE COLUMN area_code zip_code VARCHAR(50);
