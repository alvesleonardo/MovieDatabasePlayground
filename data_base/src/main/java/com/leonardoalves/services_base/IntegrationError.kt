package com.leonardoalves.services_base

sealed class IntegrationError : Throwable() {

    object RequestError : IntegrationError()
    object ServerError : IntegrationError()
    object UnexpectedResponse : IntegrationError()

    override fun toString() = when (this) {
        RequestError -> "Error processing request"
        ServerError -> "Server error"
        UnexpectedResponse -> "Broken contract"
    }

}