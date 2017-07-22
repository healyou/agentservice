------------------ agentType data ------------------
INSERT INTO agent_type (code, name) VALUES
    ('worker', 'Рабочий агент'),
    ('server', 'Серверный агент');

------------------ communicationGoal data ------------------
INSERT INTO communication_goal (code, name) VALUES
    ('task_decision', 'Решение задачи');

------------------ messageType data ------------------
INSERT INTO message_type (code, name, message_order, com_goal_id) VALUES
    ('search_task_solution', 'Поиск решения задачи', 1, 1),
    ('search_solution', 'Поиск решения', 2, 1),
    ('solution_answer', 'Ответ на запрос решения задачи', 3, 1),
    ('task_solution_answer', 'Ответ на задачу', 4, 1);

------------------ messageBodyType data ------------------
INSERT INTO message_body_type (code, name) VALUES
    ('json', 'Тело сообщения формата Json');