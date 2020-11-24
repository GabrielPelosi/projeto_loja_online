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
#### Funcionalidades para utilizadores não registrados
* Registrar como utilizador
* Ver os produtos
* Ver as categorias
#### Funcionalidades para utilizadores registrados
* Realizar pedidos
* Acompanhar seu pedido realizado
* Ver todos os seus pedidos já concluidos

# Infraestrutura

A api desenvolvida utilizou banco de dados postgres, docker para ambiente de desonvolvimento e dependencias do spring para manipular os dados e garantir a segurança do servidor.

# Execução em ambiente de desenvolvimento

Entrar no root do projeto e digitar os comandos:
```
docker-compose up
gralde bootRun
```
