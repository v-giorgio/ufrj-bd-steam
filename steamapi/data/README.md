# ETL

- Instale as dependências necessárias:

```
pip install -r requirements.txt
```

- Suba o container que contém o banco MySQL:

```
docker compose up -d
```

- Para acessar o banco via terminal bash:

```
docker exec -it mysql_container mysql -u steam_user -p steam
```

- Ou, caso esteja acessando do terminal do Docker Desktop:

```
bash
```
```
mysql -u <user> -p steam
```

- `<user>` pode ser `root` ou `steam_user`.

- Insira a senha, que está no arquivo `./docker-compose.yml`, dependendo de qual usuário foi utilizado.

- Uma vez dentro do ambiente MySQL, para visualizar todas as tabelas:

```
SHOW TABLES;
```

- Para popular as tabelas com as massas contidas na pasta `./generated`, basta executar o comando:

```
python insert_sql.py
```

- Caso precise modificar o pré-processamento e gerar a massa de dados novamente, basta executar:

```
python main.py
```