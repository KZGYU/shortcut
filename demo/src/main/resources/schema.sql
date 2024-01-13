CREATE TABLE IF NOT EXISTS shortcuts
(
   id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR (255),
   content VARCHAR (5000),
   command VARCHAR (255)
);
CREATE TABLE IF NOT EXISTS questions
(
   id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   question VARCHAR (255),
   answer_id INT,
   FOREIGN KEY (answer_id) REFERENCES shortcuts (id)
);
