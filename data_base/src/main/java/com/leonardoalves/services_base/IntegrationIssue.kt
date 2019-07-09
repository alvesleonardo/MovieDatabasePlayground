package com.leonardoalves.services_base

sealed class RemoteIntegrationIssue : Throwable() {

    object UnexpectedResponse : RemoteIntegrationIssue()

    override fun toString() = when (this) {
        UnexpectedResponse -> "Broken contract"
    }

}