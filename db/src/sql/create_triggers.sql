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
-- todo message_v тригеры написать