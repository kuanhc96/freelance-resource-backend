CREATE TABLE `package` (
  package_id INT NOT NULL AUTO_INCREMENT,
  package_guid VARCHAR(100) NOT NULL,
  discount_code VARCHAR(50) NOT NULL,
  subject_guid VARCHAR(100) NOT NULL,
  number_of_lessons INT NOT NULL,
  discount_rate DECIMAL(3,2) NOT NULL,
  PRIMARY KEY (package_id),
  CONSTRAINT uq_package_guid    UNIQUE (package_guid),
  CONSTRAINT uq_discount_code   UNIQUE (discount_code),
  CONSTRAINT chk_discount_rate  CHECK (discount_rate BETWEEN 0.01 AND 1.00),
  CONSTRAINT fk_package_subject FOREIGN KEY (subject_guid)
    REFERENCES subjects(subject_guid)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);
