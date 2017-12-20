------------------ agentType data ------------------
INSERT INTO agent_type (code, name) VALUES
    ('integration_test_agent_type_1', 'Тип тестового агента 1(Интеграционные тесты)'),
    ('integration_test_agent_type_2', 'Тип тестового агента 2(Интеграционные тесты)'),
    ('manual_test_agent_type_1', 'Тип тестового агента 1(Ручное тестировние)'),
    ('manual_test_agent_type_2', 'Тип тестового агента 2(Ручное тестировние)');

-------------- integration test data - agent --------------
INSERT INTO agent (mas_id, name, type_id) VALUES
    ('integration_test_agent_1_masId', 'Тестовый агент 1(Интеграционные тесты)', 1),
    ('integration_test_agent_2_masId', 'Тестовый агент 2(Интеграционные тесты)', 2),
    ('manual_test_agent_1_masId', 'Тестовый агент 1(Ручное тестировние)', 3),
    ('manual_test_agent_2_masId', 'Тестовый агент 2(Ручное тестировние)', 4);

------------------ communicationGoal data ------------------
INSERT INTO message_goal_type (code, name) VALUES
    ('integration_test_message_goal_type_1', 'Тестовая цель общения 1(Интеграционные тесты)'),
    ('manual_test_message_goal_type_1', 'Тестовая цель общения 1(Ручное тестировние)');

------------------ messageType data ------------------
INSERT INTO message_type (code, name, message_order, message_goal_type_id) VALUES
    ('integration_test_message_type_1_test_goal_1', 'Тестовый тип сообщения 1 для тестовой цели 1(Интеграционные тесты)', 1, 1),
    ('integration_test_message_type_2_test_goal_1', 'Тестовый тип сообщения 2 для тестовой цели 1(Интеграционные тесты)', 2, 1),
    ('manual_test_message_type_1_test_goal_2', 'Тестовый тип сообщения 1 для тестовой цели 2(Ручное тестировние)', 2, 2),
    ('manual_test_message_type_2_test_goal_2', 'Тестовый тип сообщения 2 для тестовой цели 2(Ручное тестировние)', 2, 2);

------------------ messageBodyType data ------------------
INSERT INTO message_body_type (code, name) VALUES
    ('json', 'Тело сообщения формата Json');

------------------ parameter data ------------------
INSERT INTO parameter (key, value) VALUES
    ('agent.service.password', 'psw');