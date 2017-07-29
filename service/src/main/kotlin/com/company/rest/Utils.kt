package com.company.rest

/**
 * Вспомогательные функции
 *
 * @author Nikita Gorodilov
 */
object Utils {

    /**
     * Проверка параметров на нулевое значение
     *
     * @return true - если один из параметров null
     */
    fun isNull(vararg args: Any?): Boolean {
        args.forEach {
            if (it == null) {
                return true
            }
        }

        return false
    }
}