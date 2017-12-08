package com.company.rest.logger

import org.slf4j.Logger

/**
 * Функции для логирования
 *
 * @author Nikita Gorodilov
 */
interface LoggerSupport {

    fun getLogger(): Logger

    /**
     * Логирование работы агента
     *
     * @param message сообщение
     * @param masId уникальный идентификатор агента
     */
    fun log(message: String, masId: String?) {
        getLogger().debug((if (masId != null) "$masId " else "") + message)
    }

    fun log(message: String) {
        getLogger().debug(message)
    }
}