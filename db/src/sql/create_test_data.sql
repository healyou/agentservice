INSERT INTO agent (mas_id, name, type_id) VALUES ('test1', 'test1', 2);
INSERT INTO agent (mas_id, name, type_id) VALUES ('test2', 'test2', 1);
INSERT INTO agent (mas_id, name, type_id) VALUES ('masId', 'name', 1);

INSERT INTO message (sender_id, message_goal_type_id, message_type_id, body_type_id, body)
  VALUES (3, 1, 1, 1, '{}');
INSERT INTO message (sender_id, message_goal_type_id, message_type_id, body_type_id, body)
  VALUES (4, 1, 1, 1, '{}');
INSERT INTO message (sender_id, message_goal_type_id, message_type_id, body_type_id, body)
  VALUES (5, 1, 1, 1, '{}');

INSERT INTO message_recipient (message_id, recipient_id) VALUES (3, 3);
INSERT INTO message_recipient (message_id, recipient_id) VALUES (3, 4);
INSERT INTO message_recipient (message_id, recipient_id) VALUES (3, 5);
INSERT INTO message_recipient (message_id, recipient_id) VALUES (4, 3);
INSERT INTO message_recipient (message_id, recipient_id) VALUES (4, 4);
INSERT INTO message_recipient (message_id, recipient_id) VALUES (4, 5);
INSERT INTO message_recipient (message_id, recipient_id) VALUES (5, 3);
INSERT INTO message_recipient (message_id, recipient_id) VALUES (5, 4);
INSERT INTO message_recipient (message_id, recipient_id) VALUES (5, 5);