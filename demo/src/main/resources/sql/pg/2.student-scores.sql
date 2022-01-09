-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE SEQUENCE example_student_scores_seq;

CREATE TABLE IF NOT EXISTS example_student_scores (
	id BIGINT default nextval ('example_student_scores_seq') NOT NULL,
	student_id INT NOT NULL,
	course VARCHAR(48) DEFAULT '' NOT NULL,
	score INTEGER DEFAULT 0 NOT NULL,
	created TIMESTAMP(0) DEFAULT NOW() NOT NULL,
	PRIMARY KEY (id),
	CONSTRAINT example_student_scores_FK FOREIGN KEY (student_id) REFERENCES example_students(id)
)
;