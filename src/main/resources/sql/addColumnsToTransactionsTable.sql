-- 1. Add UNIQUE constraint on transaction_guid
ALTER TABLE transactions
ADD CONSTRAINT uq_transactions_transaction_guid
UNIQUE (transaction_guid);

-- 2. Add subject_guid column and FK to subjects(subject_guid)
ALTER TABLE transactions
ADD COLUMN subject_guid VARCHAR(100),  -- or whatever type matches your subjects.subject_guid
ADD CONSTRAINT fk_transactions_subject
  FOREIGN KEY (subject_guid)
  REFERENCES subjects(subject_guid);

-- 3. Add package_guid column and FK to packages(package_guid)
ALTER TABLE transactions
ADD COLUMN package_guid VARCHAR(100),  -- or whatever type matches your packages.package_guid
ADD CONSTRAINT fk_transactions_package
  FOREIGN KEY (package_guid)
  REFERENCES package(package_guid);