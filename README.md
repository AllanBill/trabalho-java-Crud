Sistema CRUD em Java
Este projeto implementa uma aplicação simples de cadastro de pessoas, utilizando Java com o padrão de arquitetura MVC (Model-View-Controller), e acesso a banco de dados via JDBC.

Estrutura do Projeto
trabalho-Java/

│

├── CrudApp/

│ ├── src/

│ │ ├── model/ # Classe Pessoa (modelo)

│ │ ├── view/ # Interface gráfica (Swing)

│ │ ├── dao/ # Acesso ao banco de dados (PessoaDAO)

│ │ └── util/ # Classe de conexão com banco (Conexao.java)

│ └── README.md # (pode ser substituído por este)

│

├── out/ # Arquivos compilados (.class)

├── .git/ # Configurações do Git

├── .idea/ # Configurações do IntelliJ

└── trabalho-Java.iml # Arquivo de projeto IntelliJ

Como Executar o Projeto
Pré-requisitos:

Java JDK 8 ou superior
MySQL instalado
IntelliJ IDEA (ou outro IDE Java)
MySQL Connector (biblioteca JDBC)
Passos:

Clone ou extraia o projeto.

Abra o projeto no IntelliJ IDEA.

Configure o banco de dados:

Crie a base e a tabela:

CREATE DATABASE crud_db; USE crud_db;

CREATE TABLE pessoas ( id INT AUTO_INCREMENT PRIMARY KEY, nome VARCHAR(100), idade INT );

Atualize a classe Conexao.java com suas credenciais:

String url = "jdbc:mysql://localhost:3306/crud_db";

String usuario = "root"; // seu usuário

String senha = "sua_senha"; // sua senha

Execute a classe Main.java.

Componentes
Pessoa.java: Define o modelo de dados.
PessoaDAO.java: Contém os métodos para criar, ler, atualizar e deletar pessoas no banco de dados.
TelaPrincipal.java: Interface gráfica com botões para cada operação.
Conexao.java: Gera e gerencia a conexão com o banco de dados.
Funcionalidades
Inserção de novos registros
Listagem de todos os registros
Edição de registros existentes
Exclusão de registros
Observações
O projeto está preparado para rodar no IntelliJ IDEA.
Certifique-se de adicionar a biblioteca mysql-connector-java ao classpath do projeto.
Caso queira usar outro banco de dados, adapte a Conexao.java e a sintaxe SQL no DAO.
