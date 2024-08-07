# Bank App JWT

Bank App JWT - это REST API приложение на основе Spring Boot c Spring Security и Json Web Token.
Используется cборка Spring приложения через авто-конфигурацию. Для скрытия очевидных операций и
для улучшения читабельности кода использован lombok.
Для работы с данными используется:
- СУБД h2
- Spring Boot JPA
- Миграции БД с flywaydb
- Документация с Swagger

## Документация
Основныe endpoints:

| Метод  | Путь                                | Описание                               |
|--------|-------------------------------------|----------------------------------------|
| GET    | /accounts                           | Получение списка счетов                |
| POST   | /accounts                           | Создание нового счета                  |
| GET    | /accounts/{account_id}              | Получение информации об одном счете    |
| DELETE | /accounts/{account_id}              | Удаление счета                         |
| POST   | /accounts/{account_id}/withdraw     | Снятие денег со счета                  |
| POST   | /accounts/{account_id}/deposit      | Внесение денег на счет                 |
| GET    | /accounts/{account_id}/transactions | Получение списка всех транзакций       |
| POST   | /accounts/{account_id}/transfer     | Перевод денег с одного счета на другой |
| POST   | /authenticate                       | Авторизация пользователя               |
| POST   | /register                           | Регистрация пользователя               |



Документация находится в Swagger после запуска приложения:
http://localhost:8080/swagger-ui/index.html

## Требования

- Java 17
- Maven 3.8.x

## Запуск проекта

1. **Клонировать репозиторий:**
    ```bash
    git clone https://github.com/ShortMikelon/Solid-Bank-App.git
    ```
    
2. **Перейти в каталог проекта:**
    ```bash
    cd solidbankapp
    ```
    
3. **Перейти на ветку feature/jwt**
   ```bash
    git checkout feature/jwt
    ```
   
4. **Запуск приложения:**
    ```bash
    mvn spring-boot:run
    ```

