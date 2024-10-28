CREATE DATABASE IF NOT EXISTS steam;

USE steam;

CREATE TABLE Jogo (
    id BIGINT PRIMARY KEY,
    spec_recomendada TEXT,
    genero VARCHAR(256),
    desenvolvedora VARCHAR(256),
    editora VARCHAR(256),
    classificacao_etaria INTEGER,
    data_lancamento DATE,
    gratuidade BOOLEAN,
    descricao TEXT,
    spec_minima TEXT,
    nome VARCHAR(256)
);

CREATE TABLE Categoria (
    id INTEGER PRIMARY KEY,
    nome VARCHAR(64)
);

CREATE TABLE Imagem (
    id INTEGER,
    capa BOOLEAN,
    fk_Jogo_id BIGINT,
    url VARCHAR(256),
    PRIMARY KEY (id, fk_Jogo_id)
);

CREATE TABLE Video (
    id INTEGER,
    nome VARCHAR(256),
    url VARCHAR(256),
    fk_Jogo_id BIGINT,
    PRIMARY KEY (id, fk_Jogo_id)
);

CREATE TABLE Conquista (
    id VARCHAR(512),
    percentual_obtencao_usuarios DECIMAL,
    fk_Jogo_id BIGINT,
    PRIMARY KEY (id, fk_Jogo_id)
);

CREATE TABLE Usuario (
    id BIGINT PRIMARY KEY,
    apelido VARCHAR(256),
    nome VARCHAR(256),
    url_perfil VARCHAR(256),
    pais_origem VARCHAR(32),
    avatar VARCHAR(256)
);

CREATE TABLE JogoUsuario (
    fk_Usuario_id BIGINT,
    tempo_jogado NUMERIC,
    fk_Jogo_id BIGINT,
    PRIMARY KEY (fk_Jogo_id, fk_Usuario_id)
);

CREATE TABLE JogoCategoria (
    fk_Categoria_id INTEGER,
    fk_Jogo_id BIGINT,
    PRIMARY KEY (fk_Jogo_id, fk_Categoria_id)
);

CREATE TABLE UsuarioConquista (
    fk_Usuario_id BIGINT,
    fk_Conquista_id VARCHAR(512),
    PRIMARY KEY (fk_Conquista_id, fk_Usuario_id)
);
 
ALTER TABLE Imagem ADD CONSTRAINT FK_Imagem_1
    FOREIGN KEY (fk_Jogo_id)
    REFERENCES Jogo (id)
    ON DELETE CASCADE;
 
ALTER TABLE Video ADD CONSTRAINT FK_Video_1
    FOREIGN KEY (fk_Jogo_id)
    REFERENCES Jogo (id)
    ON DELETE CASCADE;
 
ALTER TABLE Conquista ADD CONSTRAINT FK_Conquista_1
    FOREIGN KEY (fk_Jogo_id)
    REFERENCES Jogo (id);
 
ALTER TABLE JogoUsuario ADD CONSTRAINT FK_JogoUsuario_1
    FOREIGN KEY (fk_Usuario_id)
    REFERENCES Usuario (id)
   	ON DELETE RESTRICT;
 
ALTER TABLE JogoUsuario ADD CONSTRAINT FK_JogoUsuario_2
    FOREIGN KEY (fk_Jogo_id)
    REFERENCES Jogo (id)
    ON DELETE RESTRICT;
 
ALTER TABLE JogoCategoria ADD CONSTRAINT FK_JogoCategoria_1
    FOREIGN KEY (fk_Categoria_id)
    REFERENCES Categoria (id)
    ON DELETE RESTRICT;
 
ALTER TABLE JogoCategoria ADD CONSTRAINT FK_JogoCategoria_2
    FOREIGN KEY (fk_Jogo_id)
    REFERENCES Jogo (id)
   	ON DELETE RESTRICT;
 
ALTER TABLE UsuarioConquista ADD CONSTRAINT FK_UsuarioConquista_1
    FOREIGN KEY (fk_Usuario_id)
    REFERENCES Usuario (id)
    ON DELETE RESTRICT;
 
ALTER TABLE UsuarioConquista ADD CONSTRAINT FK_UsuarioConquista_2
    FOREIGN KEY (fk_Conquista_id)
    REFERENCES Conquista (id)
    ON DELETE RESTRICT;

-- dar permissão ao usuário `steam_user` para acessar a base `steam`:
CREATE USER IF NOT EXISTS 'steam_user'@'%' IDENTIFIED BY '123';
GRANT ALL PRIVILEGES ON steam.* TO 'steam_user'@'%';
FLUSH PRIVILEGES;