DROP TABLE IF EXISTS companyDetail;
DROP TABLE IF EXISTS companyList;
DROP TABLE IF EXISTS userlist;
DROP TABLE IF EXISTS timekeeplist;

CREATE TABLE IF NOT EXISTS userlist (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	mailaddress VARCHAR(255) NOT NULL,
	username VARCHAR(255) NOT NULL,
	password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS roles (
    role_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    role_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS users_role (
    user_id INTEGER,
    role_id INTEGER,
    CONSTRAINT PK_usersRole_ID PRIMARY KEY (user_id, role_id),
    CONSTRAINT FK_users_ID FOREIGN KEY (user_id) REFERENCES userlist(id),
    CONSTRAINT FK_roles_ID FOREIGN KEY (role_id) REFERENCES roles(role_id)
);

CREATE TABLE IF NOT EXISTS timekeeplist (
	timekeepid INTEGER PRIMARY KEY AUTO_INCREMENT,
	username VARCHAR(255) NOT NULL UNIQUE,
	subject VARCHAR(255) NOT NULL,
	context VARCHAR(255) NOT NULL,
	totalTime VARCHAR(255) NOT NULL,
	Wdate VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS companyList (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    companyName VARCHAR(255) UNIQUE NOT NULL,
    industry VARCHAR(255),
    headlocate VARCHAR(255),
    areOsaka BOOLEAN,
    companyURL VARCHAR(255),
    companyLother VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS companyDetail (
    companyDetail_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    companyDetail_Cname VARCHAR(255),
    company_whatJob VARCHAR(255),
    company_strongPoint VARCHAR(255),
    company_weakPoint VARCHAR(255),
    company_treatment VARCHAR(255),
    company_welfare VARCHAR(255),
    company_flow VARCHAR(255),
    company_another VARCHAR(255)
);