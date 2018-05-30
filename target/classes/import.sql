-- INSERT INTO user(id,enabled,login,password,roles) VALUES (1, 1, 'a', 'aa', 'USER');
-- INSERT INTO user VALUES (2, 1, 'b', 'bb', 'ADMIN');
INSERT INTO user(id,enabled,login,password,roles, ciudad, edad, resumen, email) VALUES (1, 1, 'a', '$2a$04$N78IYN6VzrWZzpsy6Xvz2uCdUm7Su9FDpAqhXjAcSzCgreVM2sUnC', 'USER', 'Madrid', 30, 'Resumen', 'usuarioa@usuarioa.com');
INSERT INTO user(id,enabled,login,password,roles, ciudad, edad, resumen, email) VALUES (2, 1, 'b', '$2a$04$NwYuA6rd/UbCs3H8mntvPuqyFuUsX8sTKI1WDYwqrXhncXWIklscW', 'ADMIN', 'Madrid', 25, 'Resumen', 'usuariob@usuariob.com');
--editor-->editor
INSERT INTO user(id,enabled,login,password,roles, ciudad, edad, resumen, email) VALUES (3, 1, 'editor', '$2a$10$GIi3Q.lkOTn4DhUqBsKHAubEirPzxP0FVeqsWVoEjJDmqeGM7DgPC', 'EDITOR', 'Madrid', 35, 'Resumen', 'editor@editor.com');
--editor2-->editor2
INSERT INTO user(id,enabled,login,password,roles, ciudad, edad, resumen, email) VALUES (4, 1, 'editor2', '$2a$10$URS0jZF3NS7tL2OuEKXJDuempTTXQnaMwrQNll.fFw.m9AIWqUG72', 'EDITOR', 'Barcelona', 41, 'Resumen', 'editor2@editor2.com');

INSERT INTO reporte(id, reportador, reportado, comentario, visto, baneado) VALUES (1, 'Pepe', 'Ana Maria', 'Reportada_por_pesada', 0, 0);
INSERT INTO reporte(id, reportador, reportado, comentario, visto, baneado) VALUES (2, 'Laura', 'Miguel', 'Motivo_del_reporte', 0, 0);
INSERT INTO reporte(id, reportador, reportado, comentario, visto, baneado) VALUES (3, 'Luis', 'Patricia', 'Motivo_del_reporte', 1, 0);
INSERT INTO reporte(id, reportador, reportado, comentario, visto, baneado) VALUES (4, 'Sandra', 'Roberto', 'Motivo_del_reporte', 1, 1);


INSERT INTO quest(id, titulo, descripcion, url) VALUES (1, 'Titulo Quest1', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation', 'url_del_json');
INSERT INTO quest(id, titulo, descripcion, url) VALUES (2, 'Titulo Quest2', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation', 'url_del_json');
INSERT INTO quest(id, titulo, descripcion, url) VALUES (3, 'Aventura en el bosque', 'Ponte en situación. Imagina que un grupo de asesinos os secuestran a ti y a una persona más y escapais encontrandoos en un bosque, ¿cómo harías para salir de allí?', '/static/jsons/esqueleto3.json');
INSERT INTO quest(id, titulo, descripcion, url) VALUES (4, 'Titulo Quest4', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation', 'url_del_json');
