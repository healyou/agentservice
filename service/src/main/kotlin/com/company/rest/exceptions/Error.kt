package com.company.rest.exceptions

/**
 * @author Nikita Gorodilov
 */
enum class Error constructor(val code: Int, val description: String) {
    NOT_AUTHORIZED(401, "Not Authorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found."),
    SERVER_ERROR(501, "Server error during operation."),
    NOT_VALID(107, "Not valid");

    override fun toString(): String {
        return code.toString() + ": " + description
    }
}
