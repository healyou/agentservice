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

// todo message_v triggers

sql.close()