package com.company.db.base

import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author Nikita Gorodilov
 */
abstract class AbstractRowMapper<T: Entity>: RowMapper<T> {

    protected fun getLong(resultSet: ResultSet, columnName: String): Long = resultSet.getLong(columnName)

    protected fun getString(resultSet: ResultSet, columnName: String): String = resultSet.getString(columnName)

    protected fun getDate(resultSet: ResultSet, columnName: String): Date {
        return SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(resultSet.getString(columnName));
    }
}