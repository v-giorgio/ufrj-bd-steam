# Steam replica

Este projeto foi desenvolvido como trabalho final da disciplina Banco de Dados (24.2) na UFRJ.

O objetivo desta aplicação é exibir dados de usuários e jogos da Steam, como uma réplica da plataforma. Os dados foram consumidos das APIs disponibilizadas pela Steam e, através de um processo ETL, adicionados em um banco de dados local. A aplicação, portanto, está dividida em uma API REST desenvolvida em Java e Spring e uma interface front-end desenvolvida em React e Typescript.

## Para rodar a aplicação:

Para rodar a aplicação localmente, é necessário a instalação de algumas ferramentas:
- Docker: para rodar o banco de dados localmente
- Java 21: para rodar a API
- Node e NPM: para rodar o front-end
- Python e pip: para rodar a ETL
- Git: para clonar este repositório
- **[Recomendado]** IntelliJ: para rodar a API facilmente
- **[Recomendado]** VSCode: para rodar a ETL e o front-end facilmente

O passo a passo para executar a aplicação está descrito em detalhes abaixo:

1. Clonar este repositório e entrar no diretório:
```
git clone https://github.com/v-giorgio/ufrj-bd-steam.git bd-steam
cd bd-steam
```

2. Iniciar o banco de dados:
```
cd steamapi/data
```
- Verificar a documentação dentro desta pasta para mais detalhes

3. Com o banco de dados pronto e populado, podemos subir a API:
- A API está na pasta `steamapi/api/steam`:
```
cd steamapi/api/steam
```

- Caso esteja utilizando o IntelliJ, basta abrir o projeto nesta pasta e executar o arquivo `SteamApplication`, utilizando o botão `Run` disponibilizado pela IDE. Esta ação irá fazer o build do projeto e rodá-lo
- Caso contrário, faça o build manual e execute manualmente:
```
./gradlew clean build
./gradlew run
```
- A aplicação deverá iniciar na porta 8080, e poderá ser acessada nesta URL: `http://localhost:8080/api/v1`

4. Com a API executando, basta iniciar o front-end:
- Entrar na pasta:
```
cd webapp/steam-client
```
- Instalar as dependências:
```
npm install
```
- Rodar a aplicação:
```
npm start 
```
- Para acessar a aplicação, basta ir na URL: `http://localhost:3000`


