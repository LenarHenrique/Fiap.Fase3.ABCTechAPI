CREATE TABLE assistances (
  id bigint NOT NULL AUTO_INCREMENT
        PRIMARY KEY,
  description varchar(500) NOT NULL,
  name varchar(255) NOT NULL
);

INSERT INTO assistances (name, description) values ('Troca de aparelho','Troca de aparelho decodificador de sinal');
INSERT INTO assistances (name, description) values ('Troca de cabo interno','Troca de cabo interno');
INSERT INTO assistances (name, description) values ('Troca de fiação interna','Substituição da fiação interna da residencia');
INSERT INTO assistances (name, description) values ('Manutenção em fogão','Reparo sem necessidade de compra de peça');
INSERT INTO assistances (name, description) values ('Manutenção em geladeira','Reparo sem necessidade de compra de peça');
INSERT INTO assistances (name, description) values ('Manutenção em maquina de lavar','Reparo sem necessidade de compra de peça');
