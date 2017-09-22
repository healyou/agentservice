INSERT INTO agent (mas_id, name, type_id) VALUES ('test1', 'test1', 2);
INSERT INTO agent (mas_id, name, type_id) VALUES ('test2', 'test2', 1);
INSERT INTO agent (mas_id, name, type_id) VALUES ('masId', 'name', 1);

INSERT INTO message (sender_id, message_goal_type_id, message_type_id, body_type_id, body)
  VALUES (1, 1, 1, 1, '{}');
INSERT INTO message (sender_id, message_goal_type_id, message_type_id, body_type_id, body)
  VALUES (2, 1, 1, 1, '{}');
INSERT INTO message (sender_id, message_goal_type_id, message_type_id, body_type_id, body)
  VALUES (3, 1, 1, 1, '{}');

INSERT INTO message_recipient (message_id, recipient_id) VALUES (1, 1);
INSERT INTO message_recipient (message_id, recipient_id) VALUES (1, 2);
INSERT INTO message_recipient (message_id, recipient_id) VALUES (1, 3);
INSERT INTO message_recipient (message_id, recipient_id) VALUES (2, 1);
INSERT INTO message_recipient (message_id, recipient_id) VALUES (2, 2);
INSERT INTO message_recipient (message_id, recipient_id) VALUES (2, 3);
INSERT INTO message_recipient (message_id, recipient_id) VALUES (3, 1);
INSERT INTO message_recipient (message_id, recipient_id) VALUES (3, 2);
INSERT INTO message_recipient (message_id, recipient_id) VALUES (3, 3);