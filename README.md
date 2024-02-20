# Gerencia Seção de votação

Projeto responsável por gerenciar votações em determinadas seções e suas pautas, a votação é permitida para associados cadastrados com CPF válido.

### 🛠 Como Executar 

#### Requisitos:
* Java 17 ou superior
* Maven (Gerenciador de dependências)
* Docker (Gerenciador de containers)
* Gerenciador de banco para Postgres. Ex.: DBeaver


#### Passos para Executar:

1 - Clone o repositório: git clone https://github.com/kamilasst/gerencia-sessao-votacao.git 

2 - Entre no diretório do projeto: _cd gerencia-sessao-votacao_

3 - **Execute o comando git checkout master**

4 - Execute o comando docker _docker-compose up -d_ responsável por executar o arquivo gerencia-sessao-votacao/docker-compose.yml que será responsável por baixar a imagem do banco de dados postgres que será utilizado no projeto

5 - Abra o DBeaver ou similar e configure a conexão de banco igual o arquivo  gerencia-sessao-votacao/docker-compose.yml

6 - Execute o script responsável por criar as tabelas de banco de dados da aplicação localizado em: gerencia-sessao-votacao/scripts/create-database-postgres.sql

7 - Compile o projeto: _mvn compile_

8 - Execute a classe principal do projeto, responsável por colocar a aplicação disponível: Classe GerenciaSessaoVotacaoApplication.java, botão direito Run, projeto deverá subir em http://localhost:8080

9 - Chamada dos endpoints: Se preferir fazer chamadas via postman, é só importar os curls dos endpoints no próximo passo.


#### 📌 Principais funcionalidades:

##### 1 - Criar associado: 
Responsável por criar os associados que são as pessoas que possuem competência para votar na seção

```java
curl --location 'http://localhost:8080/gerencia/associado' \
--header 'Content-Type: application/json' \
--data '{
    "nome":"Luiz",
    "cpf":"45205006056"
}'
```


##### 2 - Criar pauta: 
Responsável por criar Pauta na aplicação

```java
curl --location 'http://localhost:8080/gerencia/pauta' \
--header 'Content-Type: application/json' \
--data '{
    "titulo":"Pauta titulo 1",
    "descricao":"Descrição da Pauta Titulo 1"
}'
```

##### 3 - Criar/Abrir Seção votação:
Responsável por criar uma seção de votação para uma determinada Pauta na aplicação

```java
curl --location 'http://localhost:8080/gerencia/sessaoVotacao' \
--header 'Content-Type: application/json' \
--data '{
  "titulo": "Título Sessao 1",
  "tituloPauta": "Pauta titulo 1",
  "dataHoraAbertura": "2024-02-19T10:15:30",
  "dataHoraFechamento": "2024-02-20T10:15:30"
}'
```

##### 4 - Votar: 
Responsável consolidar o voto do associado em uma determinada seção de votação vinculada a uma pauta

```java
curl --location 'http://localhost:8080/gerencia/voto/votar' \
--header 'Content-Type: application/json' \
--data '{
    "cpfAssociado": "74389353020",
     "tituloSessao": "Título Sessao 1",
     "tituloPauta": "Pauta titulo 1",
     "voto": "SIM"
}'
```

##### 5 - Resultado de votação: 
Responsável por fornecer um relatório da seção de votação para uma determinada pauta, informando todos os dados referente às mesmas, bem como a quantidade de votos SIM ou NAO.

```java
curl --location --request GET 'http://localhost:8080/gerencia/voto/resultadoVotacao' \
--header 'Content-Type: application/json' \
--data '{
    "tituloPauta": "Pauta titulo 1",
    "tituloSessao": "Título Sessao 1"
}'
```

---

##### 📌 Problemas e Sugestões
Se encontrar algum problema ou tiver alguma sugestão, por favor abra uma nova issue neste repositório ou entre em contato com kamilasantosdev@gmail.com  
