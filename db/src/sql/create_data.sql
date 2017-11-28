------------------ agentType data ------------------
INSERT INTO agent_type (code, name) VALUES
    ('worker', 'Рабочий агент'),
    ('server', 'Серверный агент');

------------------ communicationGoal data ------------------
INSERT INTO message_goal_type (code, name) VALUES
    ('task_decision', 'Решение задачи');

------------------ messageType data ------------------
INSERT INTO message_type (code, name, message_order, message_goal_type_id) VALUES
    ('search_task_solution', 'Поиск решения задачи', 1, 1),
    ('search_solution', 'Поиск решения', 2, 1),
    ('solution_answer', 'Ответ на запрос решения задачи', 3, 1),
    ('task_solution_answer', 'Ответ на задачу', 4, 1);

------------------ messageBodyType data ------------------
INSERT INTO message_body_type (code, name) VALUES
    ('json', 'Тело сообщения формата Json');

------------------ parameter data ------------------
INSERT INTO parameter (key, value) VALUES
    ('agent.service.password', 'psw');

--------------- integration test data --------------
INSERT INTO agent_type (code, name) VALUES
    ('test_agent_type_1', 'Тип тестового агента 1'),
    ('test_agent_type_2', 'Тип тестового агента 2');
INSERT INTO agent (mas_id, name, type_id) VALUES ('test_agent_1_masId', 'Тестовый агент 1', 3);
INSERT INTO agent (mas_id, name, type_id) VALUES ('test_agent_2_masId', 'Тестовый агент 2', 4);