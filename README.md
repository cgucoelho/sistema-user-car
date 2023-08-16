
# API Restful para sistema de usuários e carros

api para desafio para dev java, consiste em criar e gerenciar carros e usuários com login.


## Documentação da API

#### Cria um usuário

```http
  POST /api/users
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `firstName`   | `String` | O primeiro nome do usuário  |
| `lastName`    | `String` | O segundo nome do usuário  |
| `email`       | `String` | O email do usuário  |
| `birthday`    | `Date`   | A data de aniversário do usuário  |
| `login`       | `String` | O Login do usuário  |
| `password`    | `String` | A senha do usuário  |
| `phone`       | `String` | O telefone do usuário  |
| `cars`        | `List`   | Os carros do usuário  |



#### Retorna todos os usuários

```http
  GET /api/users
```
#### Retorna um usuário

```http
  GET /api/users/id
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `Long` | **Obrigatório**. O ID do usuário que você quer |

#### Altera um usuário

```http
  PUT /api/users/id
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `Long` | **Obrigatório**. O ID do usuário que você quer |

#### Deleta um usuário

```http
  DELETE /api/users/id
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `Long` | **Obrigatório**. O ID do usuário que você quer |



#### Cria um carro

```http
  POST /api/cars
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `year`        | `int`     | Ano do carro  |
| `licensePlate`| `String`  | placa do carro |
| `model`       | `String`  | modelo do carro  |
| `color`       | `String`  | cor do carro  |


```http
  GET /api/cars/id
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `Long` | **Obrigatório**. O ID do carro que você quer |

#### Altera um usuário

```http
  PUT /api/cars/id
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `Long` | **Obrigatório**. O ID do carro que você quer |

#### Deleta um usuário

```http
  DELETE /api/cars/id
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `Long` | **Obrigatório**. O ID do carro que você quer |
