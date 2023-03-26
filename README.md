# NaoFalindo

Uma API para o sistema de gastos pessoais.

## Endpoint

- Despesas
    - [Cadastrar](#cadastrar-despesas)
    - Listar todas
    - Apagar
    - Atualizar
    - [Detalhes](#detalhes-despesa)
- Receitas
    - Cadastrar
    - Listar todas
    - Apagar
    - Atualizar
    - Detalhes

### Cadastrar Despesas

`POST` /naofalindo/api/despesa

*Campos da requisição*

| Campo     | Tipo  | Obrigatório | Descrição
|-----------|-------|:-----------:|----------
|valor      |decimal positivo|sim| o valor da despesa
|data       |data   |sim| a data da despesa
|contaId    |inteiro|sim| o id de uma conta previamente cadastrada
|categoriaId|inteiro|sim| o id de uma categoria previamente cadastrada
|descricao  |texto  |não| um texto sobre a despesa

*Exemplo de requisição*
```
{
    "valor": 100.59
    "data": "2023-12-27"
    "contaId": 1,
    "cadegoriaId": 1,
    "descricao": "cinema com os amigos"
}
```

*Resposta*

| código | descrição
|--------|---------
|201| a despesa foi cadastrada com sucesso
|400| campos inválidos

### Detalhes Despesa

`GET` /naofalindo/api/despeda/{id}

*Exemplo de resposta*
```
{
    "valor": 100.59
    "data": "2023-12-27"
    "contaId": 1,
    "cadegoriaId": 1,
    "descricao": "cinema com os amigos"
}
```

*Resposta*

| código | descrição
|--------|---------
|200| os dados foram retornados
|404| não foi encontrado despesa com esse ID
