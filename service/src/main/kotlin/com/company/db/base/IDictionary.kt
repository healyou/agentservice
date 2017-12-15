package com.company.db.base

/**
 * @author Nikita Gorodilov
 */
interface IDictionary: Entity {

    val code: String
    val name: String
    val isDeleted: Boolean
}