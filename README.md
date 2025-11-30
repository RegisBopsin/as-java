Avaliação semestral - Programação Orientada a Objetos - Por Heloisa Guincheski e Régis Bopsin

Um CRUD com API simples utilizando principios basicos de Java, Docker, Spring boot, Spring Data JPA com Hibernate e PostgreSQL.
O projeto conta com relacionamento One-To-Many baseado em diretores que podem ter vários filmes anexados ao seu nome, o repositório contém a collection em formato JSON com várias requisições e outro arquivo .yml que permite a criação da network e seus containers para facilitar o uso.

# 🔧 Como utilizar?

1 - Abra seu terminal bash e dê um Git clone https://github.com/RegisBopsin/as-java.git para baixar o repositório.

2 - Abra o projeto (de preferência use o IntelliJ) e aguarde a instalação de todas as dependências.
 
3 - Abra o terminal do Intellij e rode o comando * sudo docker compose up -d *, este comando irá criar uma network e os containers necessários para utilizar o projeto, caso não esteja utilizando Linux, retire o SUDO e use apenas * docker compose up -d *.

4 - Em seu navegador, insira em sua URL * http://localhost:5050 *. 

5 - Utilize o seguinte login - usuário: admin@admin.com // senha: admin.

6 - Ao efetuar o login, clique em Add New Server, nomeie seu server e adicione as credenciais abaixo...

HostName: pgadmin-as // 
Database: meubanco // 
User: postgres // 
Senha: postgres 

7 - Clique em "salvar", agora, rode a aplicação em seu Intellij.

8 - Agora, basta abrir seu Postman e importar o arquivo JSON presente no repositório, ali estão todas as requisições prontas pra uso, além disso, o arquivo import.sql contém inserts de dois diretores e quatro filmes como exemplo.

🚀 Tudo pronto! 
