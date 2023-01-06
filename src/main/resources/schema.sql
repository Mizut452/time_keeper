DROP TABLE IF EXISTS users_role;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS companyDetail;
DROP TABLE IF EXISTS companyList;
DROP TABLE IF EXISTS userList;
DROP TABLE IF EXISTS timeKeepList;

CREATE TABLE IF NOT EXISTS userList (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	mailAddress VARCHAR(255) NOT NULL,
	username VARCHAR(255) NOT NULL,
	password VARCHAR(255) NOT NULL,
	roleName VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS roles (
    rolesID INTEGER PRIMARY KEY,
    rolesName VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS users_role (
    user_id INTEGER,
    role_id INTEGER,
    CONSTRAINT PK_usersRole_ID PRIMARY KEY (user_id, role_id),
    CONSTRAINT FK_users_ID FOREIGN KEY (user_id) REFERENCES userList(id),
    CONSTRAINT FK_roles_ID FOREIGN KEY (role_id) REFERENCES roles(rolesID)
);

CREATE TABLE IF NOT EXISTS timeKeepList (
	timeKeepId INTEGER PRIMARY KEY AUTO_INCREMENT,
	username VARCHAR(255) NOT NULL,
	subject VARCHAR(255) NOT NULL,
	context VARCHAR(255) NOT NULL,
	totalTime VARCHAR(255) NOT NULL,
	whatDate VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS companyList (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    companyName VARCHAR(255) UNIQUE NOT NULL,
    industry VARCHAR(255),
    headLocate VARCHAR(255),
    areOsaka BOOLEAN,
    companyURL VARCHAR(255),
    companyListOther VARCHAR(255)
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