package com.company.db.base

import com.company.AbstractServiceTest
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import java.util.*
import kotlin.test.assertEquals

/**
 * @author Nikita Gorodilov
 */
class TestEnvironment: AbstractServiceTest() {

    @Autowired
    private lateinit var env: Environment

    /* Тест добавления и извлечения свойства из бд */
    @Test
    fun testAddGetProperty() {
        val key = UUID.randomUUID().toString()
        val value = UUID.randomUUID().toString()
        env.addProperty(key, value)

        val getValue = env.getProperty(key)
        assertEquals(value, getValue)
    }
}