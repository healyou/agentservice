package com.company.db.base

import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

/**
 * @author Nikita Gorodilov
 */
abstract class AbstractRowMapper<T: Entity>: RowMapper<T> {

    protected fun getLong(resultSet: ResultSet, columnName: String): Long = resultSet.getLong(columnName)

    protected fun getString(resultSet: ResultSet, columnName: String): String = resultSet.getString(columnName)
}