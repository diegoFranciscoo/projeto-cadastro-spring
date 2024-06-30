# Cadastro de Usuário

Esse projeto é a versão 2.0 do Cadastro-Usúario, ela foi desenvolvida em Spring Web, Spring JPA (Utilizando Banco em memória H2).

## Descrição

Projeto de cadastro de usúario e perguntas, para ser registrada uma pergunta é necessario haver um usúario cadastrado, um usúario pode fazer uma ou diversas perguntas.

## Regras

O usúario precisa ter pelo menos 18 anos para se registrar.<br>
O email do usúario precisa ser valido, exemplo: xxxxxxxxxx@gmail.com.

## Como executar

- O programa é executado na URI http://localhost:8080/users para Usúarios e http://localhost:8080/questions para perguntas.
- Metodo GET, apenas precisa colocar a URI padrão para listar todos Usúarios ou Perguntas.
- Metodo PUT, para Usúario requer um Body com Nome, Email, Idade, Altura e Id.
- Metodo PUT, para Perguntas requer um Body com UserId, Pergunta, Id.
- Metodo DEL, precisa colocar a URI padrão, mas acrescentando /{id} que deseja deletar.
- Metodo Post, Para Usúario requer um body com Nome, Email, Idade e Altura.
- Metodo Post, Para Perguntas requer um body com Pergunta e UserId.
