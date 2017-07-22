import groovy.sql.Sql

// --------------- Подключение к БД ---------------
def sql = Sql.newInstance("jdbc:sqlite:" + parentDir + addrDB + nameDB, "org.sqlite.JDBC")

// todo как называть правильно таблицы и поля(мелкие или большие буквы и _ ?)

def sqlScript = ""
[
        "/src/sql/delete_tables.sql",
        "/src/sql/create_tables.sql",
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