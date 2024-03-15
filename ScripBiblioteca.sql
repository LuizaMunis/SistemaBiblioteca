CREATE SCHEMA `biblioteca` ;

CREATE TABLE `biblioteca`.`leitor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `telefone` VARCHAR(11) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `biblioteca`.`livros` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(45) NOT NULL,
  `autor` VARCHAR(45) NOT NULL,
  `genero` VARCHAR(45) NOT NULL,
  `editora` VARCHAR(45) NOT NULL,
  `quantidade` VARCHAR(45) NOT NULL,
  `Disponibilidade` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `biblioteca`.`emprestimos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_leitor` INT NOT NULL,
  `id_livro` INT NOT NULL,
  `data_emprestimo` DATE NOT NULL,
  `data_devolucao` DATE NULL,
  PRIMARY KEY (`id`));
  
  ALTER TABLE `biblioteca`.`emprestimos` 
ADD COLUMN `Devolucao` VARCHAR(3) NULL AFTER `data_devolucao`,
CHANGE COLUMN `data_devolucao` `data_devolucao` DATE NOT NULL ;

ALTER TABLE `biblioteca`.`emprestimos` 
CHANGE COLUMN `data_emprestimo` `data_emprestimo` VARCHAR(15) NOT NULL ,
CHANGE COLUMN `data_devolucao` `data_devolucao` VARCHAR(15) NOT NULL ;

SELECT DISTINCT(l.nome) as Nome, COUNT(e.id) as QuantidadeLivrosEmprestado
FROM emprestimos as e
JOIN leitor as l ON l.id = e.id_leitor
JOIN livros as li ON li.id = e.id_livro
GROUP BY l.nome
ORDER BY QuantidadeLivrosEmprestado DESC, Nome ASC
LIMIT 10;