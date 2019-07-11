package com.leonardoalves.data_base

sealed class NetworkError : Throwable() {

    object HostUnreachable : NetworkError()
    object NetworkTimeout : NetworkError()
    object NetworkUnknownError : NetworkError()

    override fun toString() = when (this) {
        HostUnreachable -> "Can't reach remote host"
        NetworkTimeout -> "Networking time out"
        NetworkUnknownError -> "Unknown network problem"
    }

}