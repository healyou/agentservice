package com.company

import com.company.db.base.AbstractDao
import org.springframework.stereotype.Component

/**
 * Создание тестовых типов данных в бд перед началом тестирования
 *
 * @author Nikita Gorodilov
 */
@Component
class TestCreateTypesDao : AbstractDao() {

    companion object {
        /* Коды для типов агентов */
        val testAgentCode1 = "testAgentCode1"
        val testAgentCode2 = "testAgentCode2"
        /* Коды для типов тел сообщений */
        val testMessageBodyCode1 = "testMessageBodyCode1"
        /* Коды для типов целей общения сообщений */
        val testMessageGoalCode1 = "testMessageGoalCode1"
        /* Коды для типов сообщений */
        val testMessageCode1 = "testMessageCode1"
        val testMessageCode2 = "testMessageCode2"
    }

    /**
     * Создаём тестовые типы данных из класса
     */
    fun createTypes() {
        val allSql = """
                    ------------------ agentType data ------------------
INSERT INTO agent_type (code, name) VALUES
    ('$testAgentCode1', 'Рабочий агент'),
    ('$testAgentCode2', 'Серверный агент');

------------------ communicationGoal data ------------------
INSERT INTO message_goal_type (code, name) VALUES
    ('$testMessageGoalCode1', 'Решение задачи');

------------------ messageType data ------------------
INSERT INTO message_type (code, name, message_order, message_goal_type_id) VALUES
    ('$testMessageCode1', 'Поиск решения задачи', 1, 1),
    ('$testMessageCode2', 'Поиск решения', 2, 1);

------------------ messageBodyType data ------------------
INSERT INTO message_body_type (code, name) VALUES
    ('$testMessageBodyCode1', 'Тело сообщения формата Json');
                """.trimIndent()

        allSql.split(';')
                .filter { it != "\n" && !it.isEmpty() }
                .forEach { jdbcTemplate.update(it) }
    }
}