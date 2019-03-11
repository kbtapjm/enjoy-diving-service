-- 롤
INSERT INTO ROLE(id, role, role_name, reg_date) VALUES (1, 'ADMIN', '슈퍼운영자', NOW());
INSERT INTO ROLE(id, role, role_name, reg_date) VALUES (2, 'MANAGER', '일반운영자', NOW());
INSERT INTO ROLE(id, role, role_name, reg_date) VALUES (3, 'USER', '사용자', NOW());

-- 유저 기본
INSERT INTO USER_BASIC(id, name, nickname, gender, country, status, profile, introduce, reg_date) VALUES (1, '슈퍼운영자', '검은몽스', 0, 'kr', 0, '/profile.jpg', 'hi', now());
--
---- 유저 다이브
INSERT INTO USER_DIVE(id, dive_group, dive_level, signature, team, reg_date) VALUES (1, 'PADI', 'advanced open water dive', 'admin_sign', 'team1', NOW());
--
---- 유저
INSERT INTO USER(id, email, password, provider, reg_date, user_basic_id, user_dive_id) VALUES (1, 'admin@mail.com', '$2a$10$MIMaITTnAT2RUtq4zNRjuuXhs5ao40U0QMzjHatZaArWxFqgrMntO', '', now(), 1, 1);
--
---- 유저 롤
INSERT INTO USER_ROLE(user_id, role_id, reg_date) VALUES (1, 1, now());


