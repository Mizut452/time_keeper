--パスワードは"top"
INSERT INTO userlist(id, mailaddress, username, password, rolename) VALUES(1, 'top@top.com', 'top', '$2a$10$0Jh/VfaS8k9HVfA3/f6lLuviOq8jVG0lGBRQNLUG8Fl69oHA7PmE6', 'ROLE_ADMIN');

--パスワードは"second"
INSERT INTO userlist(id, mailaddress, username, password, rolename) VALUES (2, 'second@second.com', 'second', '$2a$10$WtleMW4PbahhhxRTpQ0Gyu87FUxQJO0Cw6M.AV518/Cb3cTFeEy7e', 'ROLE_GENERAL');

INSERT INTO companyList(id, companyName, industry, headlocate, areOsaka, CompanyURL, CompanyLother) VALUES(1, 'SHARP', '家電メーカー', '大阪', 1, 'https://jp.sharp/', '特になし');
