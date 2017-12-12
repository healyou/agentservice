package com.company.rest.utils

/**
 * Константы для описание функций сервиса в wadl файле
 *
 * @author Nikita Gorodilov
 */
object Documentation {

    const val loginIsOptional = "Метод доступен без входа в систему"
    const val loginIsRequired = "Метод не доступен без входа в систему"
    const val passwordParamValue = "Пароль для выполнения регистрации(выдаётся администратором)"
    const val masIdParamValue = "Уникальный идентификатор агента"
    const val allParamsIsRequired = "Все параметры метода являются обязательными"
    const val allParamsIsOptional = "Все параметры метода являются не обязательными"
    const val agentTypeParamValue = "Тип агента(поле code таблицы agent_type)"
    const val isDeletedParamValue = "Удалено ли значение(удалено - невозможно выполнять некоторые функции)"
    const val goalTypeParamValue = "Цель общения(поле code таблице message_goal_type)"
    const val messageTypeParamValue = "Тип сообщения(поле code таблице message_type)"
    const val messageBodyTypeParamValue = "Тип сообщения(поле code таблице message_body_type)"
    const val isViewedParamValue = "Просмотрена ли запись агентом"

    const val registrationResponseValue =
            "403 - Ошибка регистрации\n" +
                    "200 - Успешная регистрации агента"
    const val loginResponseValue =
            "403 - Ошибка входа в систему\n" +
                    "200 - Успешная вход в систему"
    const val logoutResponseValue =
            "403 - Ошибка входа в систему\n" +
                    "401 - Не авторизован в системе\n" +
                    "200 - Успешная вход в систему"
    const val getCurrentAgentResponseValue =
            "403 - Не найден текущий агент\n" +
                    "401 - Не авторизован в системе\n" +
                    "200 - Агент найден"
    const val getAgentsResponseValue =
            "403 - Не найден текущий агент\n" +
                    "401 - Не авторизован в системе\n" +
                    "200 - Агенты найдены"
    const val getMessagesResponseValue =
            "403 - Ошибка поиска сообщений\n" +
                    "401 - Не авторизован в системе\n" +
                    "200 - Сообщения найдены"
    const val sendMessageResponseValue =
            "403 - Ошибка отправки сообщения\n" +
                    "401 - Не авторизован в системе\n" +
                    "200 - Сообщение отправлено"
    const val getTypesResponseValue =
            "200 - Типы найдены"
    const val getPostMessageTypesReturnValue =
            "403 - Ошибка поиска данных\n" +
                    "200 - Типы сообщений найдены"

    const val messageTypesReturnValue =
            "[\n" +
            "    {\n" +
            "        \"id\": 3,\n" +
            "        \"code\": \"SOLUTION_ANSWER\",\n" +
            "        \"name\": \"Ответ на запрос решения задачи\",\n" +
            "        \"messageOrder\": 3,\n" +
            "        \"messageGoalType\": {\n" +
            "            \"id\": 1,\n" +
            "            \"code\": \"TASK_DECISION\",\n" +
            "            \"name\": \"Решение задачи\",\n" +
            "            \"deleted\": false\n" +
            "        },\n" +
            "        \"deleted\": false\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": 4,\n" +
            "        \"code\": \"TASK_SOLUTION_ANSWER\",\n" +
            "        \"name\": \"Ответ на задачу\",\n" +
            "        \"messageOrder\": 4,\n" +
            "        \"messageGoalType\": {\n" +
            "            \"id\": 1,\n" +
            "            \"code\": \"TASK_DECISION\",\n" +
            "            \"name\": \"Решение задачи\",\n" +
            "            \"deleted\": false\n" +
            "        },\n" +
            "        \"deleted\": false\n" +
            "    }\n" +
            "]"
    const val messageGoalTypesReturnValue =
            "[\n" +
            "    {\n" +
            "        \"id\": 1,\n" +
            "        \"code\": \"TASK_DECISION\",\n" +
            "        \"name\": \"Решение задачи\",\n" +
            "        \"deleted\": false\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": 2,\n" +
            "        \"code\": \"TEST_MESSAGE_GOAL_TYPE_1\",\n" +
            "        \"name\": \"Тестовая цель общения 1\",\n" +
            "        \"deleted\": false\n" +
            "    }\n" +
            "]"
    const val messageBodyTypesReturnValue =
            "[\n" +
            "    {\n" +
            "        \"id\": 1,\n" +
            "        \"code\": \"JSON\",\n" +
            "        \"name\": \"Тело сообщения формата Json\",\n" +
            "        \"deleted\": false\n" +
            "    }\n" +
            "]"
    const val agentTypesReturnValue =
            "[\n" +
            "    {\n" +
            "        \"id\": 3,\n" +
            "        \"code\": \"TEST_AGENT_TYPE_1\",\n" +
            "        \"name\": \"Тип тестового агента 1\",\n" +
            "        \"deleted\": false\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": 4,\n" +
            "        \"code\": \"TEST_AGENT_TYPE_2\",\n" +
            "        \"name\": \"Тип тестового агента 2\",\n" +
            "        \"deleted\": false\n" +
            "    }\n" +
            "]"
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
    const val agentsJsonReturnValue =
            "[\n" +
            "    {\n" +
            "        \"id\": 1,\n" +
            "        \"masId\": \"test_agent_1_masId\",\n" +
            "        \"name\": \"Тестовый агент 1\",\n" +
            "        \"type\": {\n" +
            "            \"id\": 3,\n" +
            "            \"code\": \"TEST_AGENT_TYPE_1\",\n" +
            "            \"name\": \"Тип тестового агента 1\",\n" +
            "            \"deleted\": false\n" +
            "        },\n" +
            "        \"createDate\": 1512886542731,\n" +
            "        \"deleted\": false\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": 2,\n" +
            "        \"masId\": \"test_agent_2_masId\",\n" +
            "        \"name\": \"Тестовый агент 2\",\n" +
            "        \"type\": {\n" +
            "            \"id\": 4,\n" +
            "            \"code\": \"TEST_AGENT_TYPE_2\",\n" +
            "            \"name\": \"Тип тестового агента 2\",\n" +
            "            \"deleted\": false\n" +
            "        },\n" +
            "        \"createDate\": 1512886542731,\n" +
            "        \"deleted\": false\n" +
            "    }" +
            "]"
    const val messageJsonReturnValue =
            "{\n" +
            "        \"id\": 1,\n" +
            "        \"sender\": {\n" +
            "            \"id\": 1,\n" +
            "            \"masId\": \"test_agent_1_masId\",\n" +
            "            \"name\": \"Тестовый агент 1\",\n" +
            "            \"type\": {\n" +
            "                \"id\": 3,\n" +
            "                \"code\": \"TEST_AGENT_TYPE_1\",\n" +
            "                \"name\": \"Тип тестового агента 1\",\n" +
            "                \"deleted\": false\n" +
            "            },\n" +
            "            \"createDate\": 1512886542731,\n" +
            "            \"deleted\": false\n" +
            "        },\n" +
            "        \"recipients\": [\n" +
            "            {\n" +
            "                \"id\": 1,\n" +
            "                \"recipient\": {\n" +
            "                    \"id\": 2,\n" +
            "                    \"masId\": \"test_agent_2_masId\",\n" +
            "                    \"name\": \"Тестовый агент 2\",\n" +
            "                    \"type\": {\n" +
            "                        \"id\": 4,\n" +
            "                        \"code\": \"TEST_AGENT_TYPE_2\",\n" +
            "                        \"name\": \"Тип тестового агента 2\",\n" +
            "                        \"deleted\": false\n" +
            "                    },\n" +
            "                    \"createDate\": 1512886542731,\n" +
            "                    \"deleted\": false\n" +
            "                },\n" +
            "                \"viewedDate\": 1512886847776\n" +
            "            }\n" +
            "        ],\n" +
            "        \"goalType\": {\n" +
            "            \"id\": 1,\n" +
            "            \"code\": \"TASK_DECISION\",\n" +
            "            \"name\": \"Решение задачи\",\n" +
            "            \"deleted\": false\n" +
            "        },\n" +
            "        \"type\": {\n" +
            "            \"id\": 2,\n" +
            "            \"code\": \"SEARCH_SOLUTION\",\n" +
            "            \"name\": \"Поиск решения\",\n" +
            "            \"messageOrder\": 2,\n" +
            "            \"messageGoalType\": {\n" +
            "                \"id\": 1,\n" +
            "                \"code\": \"TASK_DECISION\",\n" +
            "                \"name\": \"Решение задачи\",\n" +
            "                \"deleted\": false\n" +
            "            },\n" +
            "            \"deleted\": false\n" +
            "        },\n" +
            "        \"createDate\": 1512886847653,\n" +
            "        \"bodyType\": {\n" +
            "            \"id\": 1,\n" +
            "            \"code\": \"JSON\",\n" +
            "            \"name\": \"Тело сообщения формата Json\",\n" +
            "            \"deleted\": false\n" +
            "        },\n" +
            "        \"body\": \"{\\\"name\\\":\\\"f5cbd418-0dd4-4568-b8f3-4b185be09b55\\\",\\\"data\\\":\\\"\\\\u0001\\\\u0002\\\\u0003\\\"}\"\n" +
            "    }"
    const val messagesJsonReturnValue =
            "[\n" +
            "    {\n" +
            "        \"id\": 1,\n" +
            "        \"sender\": {\n" +
            "            \"id\": 1,\n" +
            "            \"masId\": \"test_agent_1_masId\",\n" +
            "            \"name\": \"Тестовый агент 1\",\n" +
            "            \"type\": {\n" +
            "                \"id\": 3,\n" +
            "                \"code\": \"TEST_AGENT_TYPE_1\",\n" +
            "                \"name\": \"Тип тестового агента 1\",\n" +
            "                \"deleted\": false\n" +
            "            },\n" +
            "            \"createDate\": 1512886542731,\n" +
            "            \"deleted\": false\n" +
            "        },\n" +
            "        \"recipients\": [\n" +
            "            {\n" +
            "                \"id\": 1,\n" +
            "                \"recipient\": {\n" +
            "                    \"id\": 2,\n" +
            "                    \"masId\": \"test_agent_2_masId\",\n" +
            "                    \"name\": \"Тестовый агент 2\",\n" +
            "                    \"type\": {\n" +
            "                        \"id\": 4,\n" +
            "                        \"code\": \"TEST_AGENT_TYPE_2\",\n" +
            "                        \"name\": \"Тип тестового агента 2\",\n" +
            "                        \"deleted\": false\n" +
            "                    },\n" +
            "                    \"createDate\": 1512886542731,\n" +
            "                    \"deleted\": false\n" +
            "                },\n" +
            "                \"viewedDate\": 1512886847776\n" +
            "            }\n" +
            "        ],\n" +
            "        \"goalType\": {\n" +
            "            \"id\": 1,\n" +
            "            \"code\": \"TASK_DECISION\",\n" +
            "            \"name\": \"Решение задачи\",\n" +
            "            \"deleted\": false\n" +
            "        },\n" +
            "        \"type\": {\n" +
            "            \"id\": 2,\n" +
            "            \"code\": \"SEARCH_SOLUTION\",\n" +
            "            \"name\": \"Поиск решения\",\n" +
            "            \"messageOrder\": 2,\n" +
            "            \"messageGoalType\": {\n" +
            "                \"id\": 1,\n" +
            "                \"code\": \"TASK_DECISION\",\n" +
            "                \"name\": \"Решение задачи\",\n" +
            "                \"deleted\": false\n" +
            "            },\n" +
            "            \"deleted\": false\n" +
            "        },\n" +
            "        \"createDate\": 1512886847653,\n" +
            "        \"bodyType\": {\n" +
            "            \"id\": 1,\n" +
            "            \"code\": \"JSON\",\n" +
            "            \"name\": \"Тело сообщения формата Json\",\n" +
            "            \"deleted\": false\n" +
            "        },\n" +
            "        \"body\": \"{\\\"name\\\":\\\"f5cbd418-0dd4-4568-b8f3-4b185be09b55\\\",\\\"data\\\":\\\"\\\\u0001\\\\u0002\\\\u0003\\\"}\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": 3,\n" +
            "        \"sender\": {\n" +
            "            \"id\": 1,\n" +
            "            \"masId\": \"test_agent_1_masId\",\n" +
            "            \"name\": \"Тестовый агент 1\",\n" +
            "            \"type\": {\n" +
            "                \"id\": 3,\n" +
            "                \"code\": \"TEST_AGENT_TYPE_1\",\n" +
            "                \"name\": \"Тип тестового агента 1\",\n" +
            "                \"deleted\": false\n" +
            "            },\n" +
            "            \"createDate\": 1512886542731,\n" +
            "            \"deleted\": false\n" +
            "        },\n" +
            "        \"recipients\": [\n" +
            "            {\n" +
            "                \"id\": 3,\n" +
            "                \"recipient\": {\n" +
            "                    \"id\": 2,\n" +
            "                    \"masId\": \"test_agent_2_masId\",\n" +
            "                    \"name\": \"Тестовый агент 2\",\n" +
            "                    \"type\": {\n" +
            "                        \"id\": 4,\n" +
            "                        \"code\": \"TEST_AGENT_TYPE_2\",\n" +
            "                        \"name\": \"Тип тестового агента 2\",\n" +
            "                        \"deleted\": false\n" +
            "                    },\n" +
            "                    \"createDate\": 1512886542731,\n" +
            "                    \"deleted\": false\n" +
            "                },\n" +
            "                \"viewedDate\": 1512894648608\n" +
            "            }\n" +
            "        ],\n" +
            "        \"goalType\": {\n" +
            "            \"id\": 1,\n" +
            "            \"code\": \"TASK_DECISION\",\n" +
            "            \"name\": \"Решение задачи\",\n" +
            "            \"deleted\": false\n" +
            "        },\n" +
            "        \"type\": {\n" +
            "            \"id\": 2,\n" +
            "            \"code\": \"SEARCH_SOLUTION\",\n" +
            "            \"name\": \"Поиск решения\",\n" +
            "            \"messageOrder\": 2,\n" +
            "            \"messageGoalType\": {\n" +
            "                \"id\": 1,\n" +
            "                \"code\": \"TASK_DECISION\",\n" +
            "                \"name\": \"Решение задачи\",\n" +
            "                \"deleted\": false\n" +
            "            },\n" +
            "            \"deleted\": false\n" +
            "        },\n" +
            "        \"createDate\": 1512894648508,\n" +
            "        \"bodyType\": {\n" +
            "            \"id\": 1,\n" +
            "            \"code\": \"JSON\",\n" +
            "            \"name\": \"Тело сообщения формата Json\",\n" +
            "            \"deleted\": false\n" +
            "        },\n" +
            "        \"body\": \"{\\\"name\\\":\\\"7b654f14-0760-4d80-baba-ba9db5d8ce61\\\",\\\"data\\\":\\\"\\\\u0001\\\\u0002\\\\u0003\\\"}\"\n" +
            "    }" +
            "]"
}