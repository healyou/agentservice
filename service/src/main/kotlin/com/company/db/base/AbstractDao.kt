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

    protected fun <T: Entity> configureInQuery(recipients: List<T>): String {
        if (recipients.isEmpty()) {
            throw RuntimeException("Не должно быть пустого массива - так как с 2 условиями сработает лишь по одному и обновятся все записи")
        }

        val inQuery = StringBuilder("(")
        for (i in 0 until recipients.size) {
            inQuery.append(recipients[i].id!!)
            if (i != recipients.size - 1) {
                inQuery.append(',')
            }
        }

        inQuery.append(')')
        return inQuery.toString()
    }

    // todo почему вызов через эту функцию даёт null, а вызов прямой вызов jdbcTemplate.queryForObject норм работает
//    protected fun <T: Entity> queryForObject(query: String, rowMapper: RowMapper<T>, vararg args: Any): T {
//        return jdbcTemplate.queryForObject(query, rowMapper, args)
//    }
//
//    // todo почему вызов через эту функцию даёт null, а вызов прямой вызов jdbcTemplate.update норм работает
//    protected fun update(query: String, vararg args: Any) {
//        jdbcTemplate.update(query, args)
//    }
}