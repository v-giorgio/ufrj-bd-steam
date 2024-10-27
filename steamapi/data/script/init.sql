-- PRIMEIRA VERSAO; FALTAM AJUSTES

-- criar a base e usá-la:
CREATE DATABASE IF NOT EXISTS steam;
USE steam;

-- criar as tabelas:
CREATE TABLE Jogo (
    id INTEGER PRIMARY KEY,
    descricao VARCHAR(256),
    nome VARCHAR(64)
    spec_minima VARCHAR(256),
    spec_recomendada VARCHAR(256),
    genero VARCHAR(16),
    desenvolvedora VARCHAR(64),
    editora VARCHAR(64),
    classificacao_etaria INTEGER,
    data_lancamento DATE,
    gratuidade BOOLEAN,
);

CREATE TABLE Categoria (
    id INTEGER PRIMARY KEY,
    nome VARCHAR(32)
);

CREATE TABLE Imagem (
    id INTEGER PRIMARY KEY,
    capa BOOLEAN,
    fk_Jogo_id INTEGER,
    url VARCHAR(256)
);

CREATE TABLE Video (
    id INTEGER PRIMARY KEY,
    nome VARCHAR(256),
    url VARCHAR(256),
    fk_Jogo_id INTEGER
);

CREATE TABLE Conquista (
    id INTEGER PRIMARY KEY,
    percentual_obtencao_usuarios NUMERIC,
    fk_Jogo_id INTEGER
);

CREATE TABLE Usuario (
    id INTEGER PRIMARY KEY,
    apelido VARCHAR(64),
    nome VARCHAR(128),
    url_perfil VARCHAR(256),
    pais_origem VARCHAR(32),
    avatar VARCHAR(256)
);

CREATE TABLE JogoUsuario (
    fk_Usuario_id INTEGER,
    tempo_jogado NUMERIC,
    fk_Jogo_id INTEGER,
    PRIMARY KEY (fk_Jogo_id, fk_Usuario_id)
);

CREATE TABLE JogoCategoria (
    fk_Categoria_id INTEGER,
    fk_Jogo_id INTEGER,
    PRIMARY KEY (fk_Jogo_id, fk_Categoria_id)
);

CREATE TABLE UsuarioConquista (
    fk_Usuario_id INTEGER,
    fk_Conquista_id INTEGER,
    PRIMARY KEY (fk_Conquista_id, fk_Usuario_id)
);
 
-- criar as constraints de chave estrangeira:
ALTER TABLE Imagem ADD CONSTRAINT FK_Imagem_1
    FOREIGN KEY (fk_Jogo_id)
    REFERENCES Jogo (id);
 
ALTER TABLE Video ADD CONSTRAINT FK_Video_1
    FOREIGN KEY (fk_Jogo_id)
    REFERENCES Jogo (id);
 
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
GRANT ALL PRIVILEGES ON steam.* TO 'steam_user'@'%';
FLUSH PRIVILEGES;