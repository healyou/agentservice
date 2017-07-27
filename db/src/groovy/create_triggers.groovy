import groovy.sql.Sql

// --------------- Подключение к БД ---------------
def sql = Sql.newInstance("jdbc:sqlite:" + parentDir + addrDB + nameDB, "org.sqlite.JDBC")

// agent_v triggers
sql.execute("--- добавление записи в agent_v ---\n" +
        "CREATE TRIGGER IF NOT EXISTS agent_v_insert_t\n" +
        "  INSTEAD OF INSERT ON agent_v\n" +
        "BEGIN\n" +
        "  INSERT INTO agent (mas_id, name, type_id) VALUES\n" +
        "    (new.mas_id, new.name, new.type_id);\n" +
        "END;\n")
sql.execute("--- изменение записи в agent_v ---\n" +
        "CREATE TRIGGER IF NOT EXISTS agent_v_update_t\n" +
        "  INSTEAD OF UPDATE ON agent_v\n" +
        "BEGIN\n" +
        "  UPDATE agent SET mas_id=new.mas_id,\n" +
        "                   name=new.name,\n" +
        "                   type_id=new.type_id,\n" +
        "                   is_deleted=new.is_deleted\n" +
        "  WHERE id=NEW.id;\n" +
        "END;")
sql.execute("CREATE TRIGGER IF NOT EXISTS agent_v_delete_t\n" +
        "  INSTEAD OF DELETE ON agent_v\n" +
        "BEGIN\n" +
        "  DELETE FROM agent WHERE id=old.id;\n" +
        "END;")

sql.execute("--- добавление записи в message_v ---\n" +
        "CREATE TRIGGER IF NOT EXISTS message_v_insert_t\n" +
        "  INSTEAD OF INSERT ON message_v\n" +
        "BEGIN\n" +
        "  INSERT INTO message (sender_id, message_goal_type_id, message_type_id, body_type_id, body) VALUES\n" +
        "    (new.sender_id, new.message_goal_type_id, new.message_type_id, new.body_type_id, new.body);\n" +
        "END;")
sql.execute("--- изменение записи в message_v ---\n" +
        "CREATE TRIGGER IF NOT EXISTS message_v_update_t\n" +
        "  INSTEAD OF UPDATE ON message_v\n" +
        "BEGIN\n" +
        "  UPDATE message SET sender_id=new.sender_id,\n" +
        "    message_goal_type_id=new.message_goal_type_id,\n" +
        "    message_type_id=new.message_type_id,\n" +
        "    viewed_date=new.viewed_date,\n" +
        "    is_viewed=new.is_viewed,\n" +
        "    body_type_id=new.body_type_id,\n" +
        "    body=new.body\n" +
        "  WHERE id=NEW.id;\n" +
        "END;")
sql.execute("--- удаление записи из message_v ---\n" +
        "CREATE TRIGGER IF NOT EXISTS message_v_delete_t\n" +
        "  INSTEAD OF DELETE ON message_v\n" +
        "BEGIN\n" +
        "  DELETE FROM message WHERE id=old.id;\n" +
        "END;")

sql.close()