package com.company.db.base

import com.company.AbstractServiceTest
import org.junit.Test

/**
 * @author Nikita Gorodilov
 */
class AbstractDaoTest: AbstractServiceTest() {

    /* Формирование in запроса с переданным пустым списком должно вызывать ошибку */
    @Test(expected = RuntimeException::class)
    fun testConfigureInQueryWithEmptyData() {
        val dao = object : AbstractDao() {
            fun configureInQueryWithError() {
                configureInQuery(arrayListOf())
            }
        }
        dao.configureInQueryWithError()
    }
}