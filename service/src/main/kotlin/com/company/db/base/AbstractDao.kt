package com.company.db.base

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import java.util.*

/**
 * @author Nikita Gorodilov
 */
abstract class AbstractDao {

    @Autowired
    protected lateinit var jdbcTemplate: JdbcTemplate
        get

    protected fun <T: Entity> query(query: String, rowMapper: RowMapper<T>): List<T> {
        return jdbcTemplate.query(query, rowMapper)
    }

    // todo почему вызов через эту функцию даёт null, а вызов прямой вызов jdbcTemplate.queryForObject норм работает
    protected fun <T: Entity> queryForObject(query: String, rowMapper: RowMapper<T>, vararg args: Any): T {
        return jdbcTemplate.queryForObject(query, rowMapper, args)
    }
}