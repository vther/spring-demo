CREATE TABLE t_memdbtest_student (ID BIGINT IDENTITY NOT NULL, BIRTHDATE DATE, FIRSTNAME VARCHAR, LASTNAME VARCHAR, PRIMARY KEY (ID));
INSERT INTO t_memdbtest_student (ID,BIRTHDATE,FIRSTNAME,LASTNAME) VALUES (1,'2009-9-9','Green','Jim');