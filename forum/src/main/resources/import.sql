INSERT INTO tb_author(name,email,password) VALUES('Gustavo Henrique','gustavo@email.com','$2y$10$m4UoM9Od3PoXahOIJ6VGg.vx3cdg5bplJ7A3d.NSdVwahxWBEf2hO');

INSERT INTO tb_course(name, category) VALUES('Kotlin','Backend');
INSERT INTO tb_course(name, category) VALUES('Java','Backend');
INSERT INTO tb_course(name, category) VALUES('HTML','Frontend');
INSERT INTO tb_course(name, category) VALUES('CSS','Frontend');

INSERT INTO tb_role(name) VALUES('ADMIN');
INSERT INTO tb_role(name) VALUES('USER');

INSERT INTO tb_author_role(user_id,role_id) VALUES(1, 1);