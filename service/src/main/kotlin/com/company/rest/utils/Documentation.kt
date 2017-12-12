package com.company.rest.utils

/**
 * @author Nikita Gorodilov
 */
object Documentation {

    const val registrationResponseValue =
            "403 - Ошибка регистрации\n" +
            "200 - Успешная регистрации агента"
    const val loginResponseValue =
            "403 - Ошибка входа в систему\n" +
            "200 - Успешная вход в систему"
    const val logoutResponseValue =
            "403 - Ошибка входа в систему\n" +
            "200 - Успешная вход в систему"

    const val agentJsonReturnValue =
            "{\n" +
            "    \"id\": 4,\n" +
            "    \"masId\": \"masId1\",\n" +
            "    \"name\": \"name\",\n" +
            "    \"type\": {\n" +
            "        \"id\": 1,\n" +
            "        \"code\": \"WORKER\",\n" +
            "        \"name\": \"Рабочий агент\",\n" +
            "        \"deleted\": false\n" +
            "    },\n" +
            "    \"createDate\": 1513103012332,\n" +
            "    \"deleted\": false\n" +
            "}"
    const val loginIsOptional = "Метод доступен без входа в систему"
    const val loginRequired = "Метод не доступен без входа в систему"
    const val passwordParamValue = "Пароль для выполнения регистрации(выдаётся администратором)"
    const val masIdParamValue = "Уникальный идентификатор агента"
    const val allParamsRequired = "Все параметры метода являются обязательными"
}