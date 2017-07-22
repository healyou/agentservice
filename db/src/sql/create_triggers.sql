-------------- AGENT_V TRIGGERS --------------
--- добавление записи в agent_v ---
CREATE TRIGGER IF NOT EXISTS agent_v_insert_t
  INSTEAD OF INSERT ON agent_v
BEGIN
  INSERT INTO agent (mas_id, name, type_id) VALUES
    (new.mas_id, new.name, new.type_id);
END;

--- изменение записи в agent_v ---
CREATE TRIGGER IF NOT EXISTS agent_v_update_t
  INSTEAD OF UPDATE ON agent_v
BEGIN
  UPDATE agent SET mas_id=new.mas_id,
                   name=new.name,
                   type_id=new.type_id,
                   is_deleted=new.is_deleted
  WHERE id=NEW.id;
END;

--- удаление записи из agent_v ---
CREATE TRIGGER IF NOT EXISTS agent_v_delete_t
  INSTEAD OF DELETE ON agent_v
BEGIN
  DELETE FROM agent WHERE id=old.id;
END;

-------------- MESSAGE_V TRIGGERS --------------
--- добавление записи в message_v ---
CREATE TRIGGER IF NOT EXISTS message_v_insert_t
  INSTEAD OF INSERT ON message_v
BEGIN
  INSERT INTO message (sender_id, communication_goal_id, message_type_id, viewed_date, body_type_id, body) VALUES
    (new.sender_id, new.communication_goal_id, new.message_type_id, new.viewed_date, new.body_type_id, new.body);
END;

--- изменение записи в message_v ---
CREATE TRIGGER IF NOT EXISTS message_v_update_t
  INSTEAD OF UPDATE ON message_v
BEGIN
  UPDATE message SET sender_id=new.sender_id,
    communication_goal_id=new.communication_goal_id,
    message_type_id=new.message_type_id,
    viewed_date=new.viewed_date,
    body_type_id=new.body_type_id,
    body=new.body
  WHERE id=NEW.id;
END;

--- удаление записи из message_v ---
CREATE TRIGGER IF NOT EXISTS message_v_delete_t
  INSTEAD OF DELETE ON message_v
BEGIN
  DELETE FROM message WHERE id=old.id;
END;