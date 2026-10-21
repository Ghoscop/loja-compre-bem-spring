Loja Compre Bem API
Sobre o projeto

A Loja Compre Bem API é uma aplicação desenvolvida com Spring Boot para gerenciar os produtos de uma loja. A API permite cadastrar, consultar, atualizar e excluir produtos, além de realizar buscas por nome e listar os resultados utilizando paginação.

O projeto utiliza um banco de dados H2 para armazenar as informações dos produtos.

Tecnologias utilizadas
Java 25
Spring Boot
Spring Web
Spring Data JPA
Hibernate
Banco de dados H2
Maven
Lombok
Swagger UI
Como executar o projeto
Pré-requisitos
Java instalado.
Git, caso queira clonar o repositório.

O projeto possui Maven Wrapper, então não é necessário instalar o Maven separadamente.

1. Clonar o repositório
git clone https://github.com/Ghoscop/loja-compre-bem-spring/tree/master

2. Entrar na pasta do projeto
cd loja-compre-bem-api

3. Compilar o projeto

No Windows, execute no PowerShell:

.\mvnw.cmd clean package

4. Iniciar a aplicação
.\mvnw.cmd spring-boot:run


Após a inicialização, a API estará disponível em http://localhost:8080.

Documentação da API

A documentação dos endpoints pode ser acessada pelo Swagger UI:

http://localhost:8080/swagger-ui.html

Pelo Swagger, é possível executar as requisições e visualizar as respostas da API.

Endpoints
Método	Endpoint	Descrição
GET	/api/v1/produtos	Lista os produtos com paginação
GET	/api/v1/produtos?busca=cadeira	Busca produtos pelo nome
GET	/api/v1/produtos/{id}	Consulta um produto pelo ID
POST	/api/v1/produtos	Cadastra um produto
PUT	/api/v1/produtos/{produtoId}	Atualiza um produto
DELETE	/api/v1/produtos/{produtoId}	Exclui um produto
Paginação

A listagem de produtos possui paginação para evitar que todos os registros sejam retornados em uma única requisição.

Os parâmetros disponíveis são:

page: indica a página desejada. A primeira página é 0.
size: define a quantidade de produtos por página. O valor padrão é 20 e o limite máximo é 50.
busca: permite pesquisar produtos pelo nome, sem diferenciar letras maiúsculas de minúsculas.
Exemplos de requisições

Listar produtos com os parâmetros padrão:

GET /api/v1/produtos


Consultar a última página:

GET /api/v1/produtos?page=2


Solicitar 500 produtos, respeitando o limite máximo de 50:

GET /api/v1/produtos?size=500


Buscar produtos pelo nome:

GET /api/v1/produtos?busca=cadeira


A resposta da listagem paginada contém informações como a quantidade total de produtos, o número de páginas e os produtos da página solicitada.

Cadastro de produtos

Para cadastrar um produto, utilize o método POST no endpoint /api/v1/produtos, enviando um JSON no corpo da requisição.

Exemplo:

{
  "nome": "Abobora",
  "descricao": "Abobora com leite",
  "preco": 35,
  "categoria": "alimento",
  "imagem": "abobora.png"
}


O identificador do produto é gerado automaticamente no formato UUID.

Consulta de produtos

A consulta por ID utiliza o endpoint /api/v1/produtos/{id}.

Quando o produto existe, a API retorna seus dados. Caso o identificador não corresponda a nenhum produto cadastrado, retorna o status HTTP 404 Not Found.

Dados iniciais

Ao iniciar a aplicação, são cadastrados 60 produtos automaticamente, caso o banco de dados esteja vazio. Esses registros permitem testar a paginação e a busca sem precisar cadastrar todos os produtos manualmente.

O banco H2 está configurado para armazenar os dados em arquivo, conforme as configurações do projeto.

Testes

Os principais testes podem ser realizados pelo Swagger UI:

Listar os produtos e verificar o total de 60 registros.
Consultar a terceira página utilizando page=2.
Solicitar size=500 e verificar o limite de 50 produtos.
Pesquisar um produto utilizando o parâmetro busca.
Consultar um produto com ID existente.
Consultar um ID inexistente e verificar a resposta 404 Not Found.
Cadastrar um novo produto utilizando o método POST.
Repositório

https://github.com/jonatasEdward/loja-compre-bem-api
