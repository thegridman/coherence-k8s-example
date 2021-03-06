CREATE USER College IDENTIFIED BY Coherence2020;

GRANT CONNECT, RESOURCE, DBA TO College;

DROP TABLE COLLEGE.ADDRESS;
DROP TABLE COLLEGE.STUDENT;

CREATE TABLE COLLEGE.ADDRESS
(
    ROLL VARCHAR2(255 char) NOT NULL PRIMARY KEY,
    LINE_ONE VARCHAR2(255 char),
    LINE_TWO VARCHAR2(255 char),
    CITY VARCHAR2(255 char),
    POSTAL_CODE VARCHAR2(255 char),
    COUNTRY VARCHAR2(255 char)
);

CREATE TABLE COLLEGE.STUDENT
(
	ROLL VARCHAR2(255 char) NOT NULL PRIMARY KEY,
    FIRST_NAME VARCHAR2(255 char),
   	LAST_NAME VARCHAR2(255 char),
	CLASS_NAME VARCHAR2(255 char),
	ADDRESS_ROLL VARCHAR2(255 char)
		CONSTRAINT STUDENT_ADDRESS
			REFERENCES COLLEGE.ADDRESS
);
