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

    /**
     * Создание запроса in по Id Entity для поиска
     *
     * @return example: [1,2,3]
     */
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

    /**
     * Id последней введённой записи в таблицу
     *
     * @param tableName имя таблицы
     */
    protected fun getLastInsertId(tableName: String): Long {
        return jdbcTemplate.queryForObject("select seq from sqlite_sequence where name = ? ;", Long::class.java, tableName)
    }
    // TODO вынести сюда вызовы через jdbcTemplate
}