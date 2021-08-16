# Projeto New Code

O projeto tem como objetivo a construção de uma API, que implemente as
funcionalidades necessárias para o funcionamento de uma editora.


## Deployment

- Faça o download do repositório.
- Importe como projeto maven na ide de sua preferência.
- As dependências estão descritas no pom.xml.
- Executar o mvn install para o download das dependências.
- A aplicação pode ser executada a partir da classe

```bash
  package br.com.project.newCode.NewCodeApplication
```

  
## Features

- Cadastro de autores: 

funcionalidade de cadastro de novos autores no sistema. Cada autor
possui um nome, e-mail e uma descrição, constando no sistema o instante em que o autor foi
cadastrado. Algumas restrições são necessárias: o nome, e-mail e descrição são obrigatórios,
o e-mail deve ter formato válido, o instante não pode ser nulo e a descrição não pode passar
de 400 caracteres. Ao criar um novo autor, o status 201 é esperado. O e-mail do autor
precisa ser único no sistema, ou seja, ao tentar cadastrar um autor existente, deve-se retornar
um erro de validação.

- Cadastro de Categorias:

funcionalidade de cadastro de categorias no sistema. Cada categoria
possui um nome, que é obrigatório e não pode ser duplicado. Ao criar uma categoria o status
201 é esperado. O nome da categoria precisa ser único no sistema, ou seja, ao tentar
cadastrar uma categoria existente, deve-se retornar um erro de validação.

- Cadastro de livros:

funcionalidade de cadastro de novos livros no sistema. Cada livro possui
um título, um resumo do que vai ser encontrado no livro, preço do livro, número de páginas,
isbn (identificador do livro), data de sua publicação, um livro pertence a uma categoria, um
livro é de um autor. O título é obrigatório e único, o resumo é obrigatório e tem no máximo
500 caracteres, o preço é obrigatório e o mínimo é de 10 reais, isbn é obrigatório e único, a
categoria e o autor não podem ser nulos. Ao cadastrar um livro o status 201 é esperado. Caso
alguma restrição não seja atendida deve-se retornar um erro de validação. A partir da classe
livro, há outras duas classes, livro digital e livro físico. Para o livro físico, tem-se o atributo
tipo de entrega, e para o livro digital tem se o atributo dispositivo.

- Exibição de lista de livros:

ao acessar a página de exibição, uma lista dos livros cadastrados
é retornada – um json com a lista de livros com id e o nome do livro.

- Exibição de detalhes de um livro:

ao acessar a página de detalhes de um livro, todas as
informações do livros são retornadas – um json com as informações cadastradas deste livro.
Criação de um endpoint que em função de um id do livro, retorne os detalhes deste livro.
Caso o id procurado não exista, o status 404 é esperado.
  
## Tecnologias utilizadas


  - Java 11
  - Spring Boot
  - MySql
  - Maven
  - JUnit5
  - Mockito
  - Swagger
