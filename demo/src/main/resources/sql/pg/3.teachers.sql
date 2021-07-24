-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE SEQUENCE example_teachers_seq;

CREATE TABLE IF NOT EXISTS example_teachers (
	id INT NOT NULL DEFAULT NEXTVAL ('example_teachers_seq') ,
	name VARCHAR(32) NOT NULL ,
	gender VARCHAR(4) NOT NULL ,
	phone_number VARCHAR(18) NOT NULL ,
	family_name VARCHAR(32) NOT NULL ,
	age SMALLINT NOT NULL DEFAULT '0' ,
	birthday DATE NULL  ,
	PRIMARY KEY (id)
)
;
