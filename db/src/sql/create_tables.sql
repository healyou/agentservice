----------------------- agent type -----------------------
CREATE TABLE if not exists agent_type
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
        create_date    TEXT               NOT NULL DEFAULT (strftime('%Y-%m-%d %H:%M:%f')), -- Дата создания(регистрации агента)
        is_deleted     TEXT               NOT NULL DEFAULT ('N') CHECK(is_deleted='N' OR is_deleted='Y'), -- Удалено ли значение
        FOREIGN KEY(type_id) REFERENCES agent_type(id)
);

----------------------- communication goal -----------------------
CREATE TABLE if not exists communication_goal
-- Цель общения агентов
(
        id      INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, -- Идентификатор
        code    TEXT                              NOT NULL, -- Системное имя
        name    TEXT                              NOT NULL, -- Читаемое имя
        is_deleted TEXT                           NOT NULL DEFAULT ('N') CHECK(is_deleted='N' OR is_deleted='Y') -- Удалено ли значение
);

----------------------- communication goal -----------------------
CREATE TABLE if not exists message_type
-- Тип сообщения
(
        id      INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, -- Идентификатор
        code    TEXT                              NOT NULL, -- Системное имя
        name    TEXT                              NOT NULL, -- Читаемое имя
        message_order   INTEGER                   NOT NULL, -- Порядок следования сообщений
        com_goal_id INTEGER                       NOT NULL, -- Цель общения
        is_deleted TEXT                           NOT NULL DEFAULT ('N') CHECK(is_deleted='N' OR is_deleted='Y'), -- Удалено ли значение
        FOREIGN KEY(com_goal_id) REFERENCES communication_goal(id)
);


----------------------- message body type -----------------------
CREATE TABLE if not exists message_body_type
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
        create_date    TEXT                       NOT NULL DEFAULT (strftime('%Y-%m-%d %H:%M:%f')), -- Дата создания
        viewed_date    TEXT                               , -- Дата просмотра
        is_viewed      TEXT                       NOT NULL DEFAULT ('N') CHECK(is_viewed='N' OR is_viewed='Y'), -- Просмотрено ли сообщение агентом
        body_type_id   INTEGER                    NOT NULL, -- Тип тела сообщения
        body           TEXT                               , -- Тело сообщения
        FOREIGN KEY(sender_id) REFERENCES agent(id),
        FOREIGN KEY(communication_goal_id) REFERENCES communication_goal(id),
        FOREIGN KEY(message_type_id) REFERENCES message_type(id),
        FOREIGN KEY(body_type_id) REFERENCES message_body_type(id)
);

----------------------- message recipient -----------------------
CREATE TABLE if not exists message_recipient
        -- Получатель сообщения
(
        id      INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, -- Идентификатор
        message_id    INTEGER                     NOT NULL, -- Сообщение
        recipient_id  INTEGER                     NOT NULL, -- Получатель
        FOREIGN KEY(message_id) REFERENCES message(id),
        FOREIGN KEY(recipient_id) REFERENCES agent(id)
);