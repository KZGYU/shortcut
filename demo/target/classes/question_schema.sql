CREATE TABLE IF NOT EXISTS questions (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    question VARCHAR(255),
    answer_id INT,
    FOREIGN KEY (answer_id) REFERENCES shortcuts(id)
);
