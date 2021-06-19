CREATE TABLE IF NOT EXISTS example_student_scores (
	id BIGINT auto_increment NOT NULL,
	student_id INT(11) NOT NULL,
	course VARCHAR(48) DEFAULT '' NOT NULL,
	score INTEGER DEFAULT 0 NOT NULL,
	created TIMESTAMP DEFAULT NOW() NOT NULL,
	PRIMARY KEY (`id`) USING BTREE,
	CONSTRAINT example_student_scores_FK FOREIGN KEY (student_id) REFERENCES example_students(id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;