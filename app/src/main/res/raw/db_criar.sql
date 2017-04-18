CREATE TABLE orientado (
 id integer NOT NULL PRIMARY KEY,
 nome varchar(255) NOT NULL
);
CREATE TABLE servico (
 id integer NOT NULL PRIMARY KEY AUTOINCREMENT,
 orientado_id integer NOT NULL,
 nome varchar(255) NOT NULL,
 FOREIGN KEY (orientado_id) REFERENCES orientados (id)
);
