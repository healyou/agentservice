import groovy.sql.Sql

// --------------- Подключение к БД ---------------
def sql = Sql.newInstance("jdbc:sqlite:" + parentDir + addrDB + nameDB, "org.sqlite.JDBC")

// todo отдельные файлы под связанные вещи(агент отдельно, сообщения отдельно и другое)

def sqlScript = ""
[
        "/src/sql/delete_tables.sql",
        "/src/sql/create_tables.sql",
        "/src/sql/create_views.sql",
        //"/src/sql/create_triggers.sql", // todo пока отдельным груви файлом -> почему-то не хочет всё сразу в execute пихать и выполнять
        "/src/sql/create_data.sql"
].each {
    new File((String) sourceDir + it).eachLine {
        sqlScript += it + "\n"
    }
}

for (script in sqlScript.split(';'))
    if (script.toString() != "\n" && !script.isEmpty())
        sql.execute(script)

sql.close()