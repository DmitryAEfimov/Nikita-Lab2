# Эмуляция вэб приложения с отправкой запросов через консоль.

## Завершение работы
 `> exit`
 
## Оперируемые сущности
### User
| Атрибут   | Обязательность | Тип данных                | Описание                                     |
|-----------|----------------|---------------------------|----------------------------------------------|
| id        | да             | UUIDv7                    | идентификатор пользователя                   |
| login     | да             | string                    | уникальный в системе логин пользователя      |
| name      | да             | string                    | имя пользователя                             |
| age       | да             | integer                   | возраст                                      |
| gender    | нет            | string                    | пол: `MALE`/`FEMALE`                         |
| hairColor | нет            | string                    | цвет волос: `BLACK`/`BLONDE`/`RED`/`COLORED` |
| accounts  | нет            | array [Account](#Account) | пользовательские счета                       |
| friends   | нет            | array [User](#User)       | список друзей                                |

### Account
| Атрибут    | Обязательность | Тип данных                    | Описание            |
|------------|----------------|-------------------------------|---------------------|
| id         | да             | UUIDv7                        | идентификатор счета |
| user       | да             | [User](#User)                 | владелец счета      |
| balance    | да             | decimal                       | текущий баланс      |
| operations | нет            | array [Operation](#Operation) | история операций    |

### Operation
| Атрибут     | Обязательность | Тип данных          | Описание                                      |
|-------------|----------------|---------------------|-----------------------------------------------|
| id          | да             | UUIDv7              | идентификатор операции                        |
| account     | да             | [Account](#Account) | изменяемый счет                               |
| opType      | да             | string              | тип операции: `DEPOSIT`/`WITHDRAW`/`TRANSFER` |
| destination | нет            | [Account](#Account) | целевой счет                                  |
| opDate      | да             | date (RFC3339)      | дата операции                                 |
| amount      | да             | decimal             | сумма операции                                |
| commission  | нет            | decimal             | сумма комиссии за операцию                    |

## Поддерживаемые команды
> [!WARNING]
> названия команд case-sensitive
> 
> payload команды json-like вида с экранированными кавычками
>
> Пример:
`> createUser {\"login\": \"sonik\", \"name\": \"Vasya\", \"age\": 32, \"gender\": \"MALE\"}`
- Операции с пользователями
  - `createUser: {login!, name!, age!, gender, hairColor}`
  - `updateUser: {id!, name, age, gender, hairColor}` ❗ В запросе должны присутствовать только изменяемые атрибуты. `\"<attrName>\": null` - удаляет текущее значение атрибута
  - `deleteUser: {id!}`
  - `readUserInfo: {id!}`
  - `addFriend: {id!, friendId!}`
  - `deleteFriend: {id!, friendId!}`
- Операции со счетами
  - `createAccount: {userId!}`
  - `deleteAccount: {id!}`
  - `readAccountInfo: {id!}`
  - `showHistory: {id!, fromDate, toDate}`
  - `deposit: {id!, amount!}`
  - `withdraw: {id!, amount!}`
  - `transfer: {id!, amount!, destination!}`