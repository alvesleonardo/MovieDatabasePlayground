package com.leonardoalves.services_base

sealed class NetworkingIssue : Throwable() {

    object HostUnreachable : NetworkingIssue()
    object NetworkTimeout : NetworkingIssue()

    override fun toString() = when (this) {
        HostUnreachable -> "Can't reach remote host"
        NetworkTimeout -> "Networking time out"
    }

}