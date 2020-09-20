INSERT INTO user (id, username, password, name, email) VALUES (1, 'admin', '520520', '陈炳江', 'admin@163.com');
INSERT INTO user (id, username, password, name, email)  VALUES (2, 'icegone', '520520', '陈炳江', 'icegone@163.com');

INSERT INTO authority (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO authority (id, name) VALUES (2, 'ROLE_USER');

INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (2, 2);
