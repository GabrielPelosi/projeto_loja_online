# Loja Online

Esse projeto foi desenvolvido em Spring boot para atender uma necessidade fictícia de uma loja que precisa atender seus clientes e realizar encomendas dos mesmo através da internet.

## Backlog

#### Funcionalidades de Admin
* Registrar produtos como admin
* Editar produtos como admin
* Remover produtos como admin
* Registrar categorias como admin
* Editar categorias como admin
* Remover categorias como admin
* Verificar todos os pedidos independente do estado como admin
* Ver todas as categories registradas.
#### Funcionalidades para utilizadores não registrados
* Registrar como utilizador
* Ver os produtos
#### Funcionalidades para utilizadores registrados
* Realizar pedidos
* Ver todos os seus pedidos já concluidos

## [Link para acessar o backlog](https://github.com/GabrielPelosi/projeto_loja_online/projects/1)

# Requisitos funcionais
  * Open api para gerar os endpoints e os modelos de requisição e resposta da api.
  * Docker para configurar e rodas o banco de dados.
  * Linguagem utilizada *Java*
  * Framework para desenvolvimento *Spring Boot*
  * Gerenciamento do banco de dados foi feito com Liquibase

# Execução em ambiente de desenvolvimento

Entrar no root do projeto e digitar os comandos:
```
docker-compose up
gralde bootRun
```
