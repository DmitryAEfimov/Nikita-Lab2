# Эмуляция вэб приложения с отправкой запросов через консоль.

## Минимальные требования для локального запуска
- OS Linux/MacOS/Windows
- Java JRE 21+
- Установленный docker
- Установленный docker-compose

## Локальный запуск
### Шаг 1. Сборкa образа БД Postgres
Выполнять из корневой директории проекта
```
docker build -t pg-lab2:latest ./db
```

### Шаг 2. Запуск БД и миграции скриптов
Выполнять из корневой директории проекта
`docker-compose up [--detach]`
Флаг `--detach` означает, что контейнеры запустятся в фоне

### Шаг 3. Базовые операции с контейнерами
```
Проверка состояния (State=Up - контейнер поднят и готов прнимать запросы)
docker-compose ps -a

Просмотр логов
docker-compose logs [container_names]

Справка по работе с docker
docker --help

Справка по работе с docker-compose
docker-compose --help
```

### Шаг 4. Сборка проекта
Выполнять из корневой директории проекта
```
Для Windows
./gradlew.bat build
 
Для Linux/MacOS
./gradlew build
```

### Шаг 5. Запуск приложеня
Выполнять из корневой директории проекта
```
java -jar ./application/build/libs/lab2-app.jar
```
## Завершение работы приложения
`exit`

## Завершение работы контейнеров
`docker-compose down`
 
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
> payload команды json-like вида
>
> Пример:
`> createUser {"login": "sonik", "name": "Vasya", "age": 32, "gender": "MALE"}`
- Операции с пользователями
  - `createUser: {login!, name!, age!, gender, hairColor}`
  - `updateUser: {id!, name, age, gender, hairColor}` ❗ В запросе должны присутствовать только изменяемые атрибуты. `\"<attrName>\": null` - удаляет текущее значение атрибута
  - `deleteUser: {id!}`
  - `readUserInfo: {id!}`
  - `addFriends: {id!, [friendId]!}` ❗ В запросе должен присутствовать как минимум один пользователь на добавление в друзья
  - `deleteFriends: {id!, [friendId]!}` ❗ В запросе должен присутствовать как минимум один пользователь на удаление из друзей
- Операции со счетами
  - `createAccount: {userId!}`
  - `deleteAccount: {id!}`
  - `readAccountInfo: {id!}`
  - `showHistory: {id!, fromDate, toDate}`
  - `deposit: {id!, amount!}`
  - `withdraw: {id!, amount!}`
  - `transfer: {id!, amount!, destination!}`