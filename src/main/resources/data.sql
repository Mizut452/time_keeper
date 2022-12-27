--役割テーブル
INSERT INTO roles(rolesID, rolesName) VALUES(1, 'ROLE_ADMIN');
INSERT INTO roles(rolesID, rolesName) VALUES(2, 'ROLE_GENERAL');

--パスワードは"top"
INSERT INTO userList(id, mailAddress, username, password, roleName) VALUES(1, 'top@top.com', 'top', '$2a$10$0Jh/VfaS8k9HVfA3/f6lLuviOq8jVG0lGBRQNLUG8Fl69oHA7PmE6', 'ROLE_ADMIN');
--パスワードは"second"
INSERT INTO userList(id, mailAddress, username, password, roleName) VALUES (2, 'second@second.com', 'second', '$2a$10$WtleMW4PbahhhxRTpQ0Gyu87FUxQJO0Cw6M.AV518/Cb3cTFeEy7e', 'ROLE_GENERAL');
--SECRET
INSERT INTO userList(id, mailAddress, username, password, roleName) VALUES(3, 'ssvror444@gmail.com', 'Mizut452', '$2a$10$4dqctMrn.ivlJrYtnpR8B.62omHi4Ptw47N6HTUmdbHKOV4f87vbm', 'ROLE_ADMIN');

--ユーザー役割テーブル
INSERT INTO users_role (user_id, role_id) VALUES (1, 1);
INSERT INTO users_role (user_id, role_id) VALUES (1, 2);
INSERT INTO users_role (user_id, role_id) VALUES (2, 2);
INSERT INTO users_role (user_id, role_id) VALUES (3, 1);
INSERT INTO users_role (user_id, role_id) VALUES (3, 2);

--INSERT INTO companyList(id, companyName, industry, headlocate, areOsaka, CompanyURL, CompanyLother) VALUES(1, 'SHARP', '家電メーカー', '大阪', 1, 'https://jp.sharp/', '特になし');
--INSERT INTO companyDetail(companyDetail_id, companyDetail_Cname, company_whatJob, company_strongPoint, company_weakPoint, company_treatment, company_welfare, company_flow, company_another) VALUES(1, 'SHARP', '営業', '日本屈指の大企業', '出世のしづらさ', '初任給:２０万円', '育休制度あり', 'ES提出⇒面接⇒webテ⇒最終面接', '特になし');