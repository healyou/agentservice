package com.company.db.base

/**
 * @author Nikita Gorodilov
 */
interface IDictionary<out T: Codable<out Any>>: Entity {

    val code: T
    val name: String
    val isDeleted: Boolean
}