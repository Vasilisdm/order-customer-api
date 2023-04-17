MERGE INTO customers
    (id, first_name, last_name, email, created_at)
    KEY (email)
    VALUES
        (1, 'John', 'Doe', 'johndoe@domain.com', CURRENT_TIMESTAMP);