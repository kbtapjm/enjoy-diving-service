-- 롤
INSERT INTO ROLE(id, role, role_name, reg_date) VALUES (1, 'ADMIN', '슈퍼운영자', NOW());
INSERT INTO ROLE(id, role, role_name, reg_date) VALUES (2, 'MANAGER', '일반운영자', NOW());
INSERT INTO ROLE(id, role, role_name, reg_date) VALUES (3, 'USER', '사용자', NOW());

-- 유저 기본
INSERT INTO USER_BASIC(id, name, nickname, gender, country, status, profile, introduce, reg_date) VALUES (1, '슈퍼운영자', '검은몽스', 0, 'kr', 0, '/profile.jpg', 'hi', now());
--INSERT INTO USER_BASIC(id, name, nickname, gender, country, status, profile, introduce, reg_date) VALUES (2, '일반운영자', '젠킨스', 1, 'kr', 0, '/profile.jpg', 'hi', now());
--INSERT INTO USER_BASIC(id, name, nickname, gender, country, status, profile, introduce, reg_date) VALUES (3, '사용자', '코비', 0, 'kr', 0, '/profile.jpg', 'hi', now());
--
---- 유저 다이브
INSERT INTO USER_DIVE(id, dive_group, dive_level, signature, team, reg_date) VALUES (1, 'PADI', 'advanced open water dive', 'admin_sign', 'team1', NOW());
--INSERT INTO USER_DIVE(id, dive_group, dive_level, signature, team, reg_date) VALUES (2, 'TDI', 'open water dive', 'manager_sign', 'team2', NOW());
--INSERT INTO USER_DIVE(id, dive_group, dive_level, signature, team, reg_date) VALUES (3, 'PADI', 'open water dive', 'user_sign', 'team3', NOW());
--
---- 유저
INSERT INTO USER(id, email, password, reg_date, user_basic_id, user_dive_id) VALUES (1, 'kbtapjm@gmail.com', '$2a$10$MIMaITTnAT2RUtq4zNRjuuXhs5ao40U0QMzjHatZaArWxFqgrMntO', now(), 1, 1);
--INSERT INTO USER(id, email, password, reg_date, user_basic_id, user_dive_id) VALUES (2, 'manager@gmail.com', '$2a$10$MIMaITTnAT2RUtq4zNRjuuXhs5ao40U0QMzjHatZaArWxFqgrMntO', now(), 2, 2);
--INSERT INTO USER(id, email, password, reg_date, user_basic_id, user_dive_id) VALUES (3, 'user@gmail.com', '$2a$10$MIMaITTnAT2RUtq4zNRjuuXhs5ao40U0QMzjHatZaArWxFqgrMntO', now(), 3, 3);
--
---- 유저 롤
INSERT INTO USER_ROLE(user_id, role_id, reg_date) VALUES (1, 1, now());
--INSERT INTO USER_ROLE(user_id, role_id, reg_date) VALUES (2, 2, now());
--INSERT INTO USER_ROLE(user_id, role_id, reg_date) VALUES (3, 3, now());


