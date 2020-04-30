package com.autopass.autocard.repository.remote.passenger.codes

enum class PassengerResponseCodes(val code: Int, val message: String) {
    SUCCESS(200, "Operation done without errors"),
    NO_FOUND(204, "No element was found"),
    INVALID_PARAMETER(400, "Invalid parameter value was sent"),
    ERROR(500, "Unexpected Error")
}