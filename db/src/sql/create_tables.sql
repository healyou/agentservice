----------------------- agent type -----------------------
CREATE TABLE if not exists agentType
-- Типы агентов в многоагентной системе
(
        id      INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, -- Идентификатор
        code    TEXT                              NOT NULL, -- Системное имя
        name    TEXT                              NOT NULL, -- Читаемое имя
        is_deleted TEXT NOT NULL DEFAULT ('N') CHECK(is_deleted='N' OR is_deleted='Y') -- Удалено ли значение
);


----------------------- agent -----------------------
CREATE TABLE if not exists agent
-- Таблицы агентов в системе
(
        id             INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, -- Идентификатор
        mas_id         TEXT               NOT NULL, -- Уникальный идентификатор агента в многоагентной системе
        name           TEXT               NOT NULL, -- Имя агента
        type_id        INTEGER            NOT NULL, -- Тип агента
        create_date    TEXT               NOT NULL DEFAULT (datetime('now')), -- Дата создания(регистрации агента)
        is_deleted     TEXT               NOT NULL DEFAULT ('N') CHECK(is_deleted='N' OR is_deleted='Y'), -- Удалено ли значение
        FOREIGN KEY(type_id) REFERENCES agentType(id)
);

----------------------- communication goal -----------------------
CREATE TABLE if not exists communicationGoal
-- Цель общения агентов
(
        id      INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, -- Идентификатор
        code    TEXT                              NOT NULL, -- Системное имя
        name    TEXT                              NOT NULL, -- Читаемое имя
        is_deleted TEXT                           NOT NULL DEFAULT ('N') CHECK(is_deleted='N' OR is_deleted='Y') -- Удалено ли значение
);

----------------------- communication goal -----------------------
CREATE TABLE if not exists messageType
-- Тип сообщения
(
        id      INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, -- Идентификатор
        code    TEXT                              NOT NULL, -- Системное имя
        name    TEXT                              NOT NULL, -- Читаемое имя
        message_order   INTEGER                   NOT NULL, -- Порядок следования сообщений
        com_goal_id INTEGER                       NOT NULL, -- Цель общения
        is_deleted TEXT                           NOT NULL DEFAULT ('N') CHECK(is_deleted='N' OR is_deleted='Y'), -- Удалено ли значение
        FOREIGN KEY(com_goal_id) REFERENCES communicationGoal(id)
);


----------------------- message body type -----------------------
CREATE TABLE if not exists messageBodyType
-- Тип тела сообщения
(
        id      INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, -- Идентификатор
        code    TEXT                              NOT NULL, -- Системное имя
        name    TEXT                              NOT NULL, -- Читаемое имя
        is_deleted TEXT                           NOT NULL DEFAULT ('N') CHECK(is_deleted='N' OR is_deleted='Y') -- Удалено ли значение
);

----------------------- message -----------------------
CREATE TABLE if not exists message
        -- Сообщение
(
        id      INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, -- Идентификатор
        sender_id     INTEGER                     NOT NULL, -- Отправитель
        communication_goal_id INTEGER             NOT NULL, -- Цель общения
        message_type_id INTEGER                   NOT NULL, -- Тип сообщения
        create_date    TEXT                       NOT NULL DEFAULT (datetime('now')), -- Дата создания
        is_viewed      TEXT                       NOT NULL DEFAULT ('N') CHECK(is_viewed='N' OR is_viewed='Y'), -- Просмотрено ли сообщение агентом
        body_type_id   INTEGER                    NOT NULL, -- Тип тела сообщения
        body           TEXT                               , -- Тело сообщения
        FOREIGN KEY(sender_id) REFERENCES agent(id),
        FOREIGN KEY(communication_goal_id) REFERENCES communicationGoal(id),
        FOREIGN KEY(message_type_id) REFERENCES messageType(id),
        FOREIGN KEY(body_type_id) REFERENCES messageBodyType(id)
);

----------------------- message recipient -----------------------
CREATE TABLE if not exists messageRecipient
        -- Получатель сообщения
(
        id      INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, -- Идентификатор
        message_id    INTEGER                     NOT NULL, -- Сообщение
        recipient_id  INTEGER                     NOT NULL, -- Получатель
        FOREIGN KEY(message_id) REFERENCES message(id),
        FOREIGN KEY(recipient_id) REFERENCES agent(id)
);