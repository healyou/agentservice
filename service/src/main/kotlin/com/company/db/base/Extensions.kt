package com.company.db.base

import java.text.SimpleDateFormat
import java.util.*

/**
 * Функции, расширяющие возможности базовых классов java
 *
 * @author Nikita Gorodilov
 */
val SQLITE_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"

/********* дата *********/
/* дату в строку sqlite */
fun Date.toSqlite(): String {
    return SimpleDateFormat(SQLITE_DATE_FORMAT).format(this)
}

/* из sqlite в строку */
fun String.fromSqlite(): Date {
    return SimpleDateFormat(SQLITE_DATE_FORMAT).parse(this)
}

/********* isDeleted *********/
/* из sqlite в объект */
fun String.toIsDeleted(): Boolean {
    if (this == "N" || this == "Y") {
        return this != "N"
    }
    else {
        throw UnsupportedOperationException("Нельзя перевести строку в isDeleted: Boolean")
    }
}

/* из объекта в sqlite */
fun Boolean.toSqlite(): String {
    if (this) {
        return "Y"
    }
    else {
        return "N"
    }
}