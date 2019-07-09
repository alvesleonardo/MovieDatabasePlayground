package com.leonardoalves.services_base.comunication

import com.leonardoalves.services_base.NetworkError
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import java.io.IOException
import java.net.ConnectException
import java.net.NoRouteToHostException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class HandleConnectivityIssue<T> : ObservableTransformer<T, T> {

    override fun apply(observable: Observable<T>): ObservableSource<T> =
        observable.onErrorResumeNext(this::handleIfNetworkingError)

    private fun handleIfNetworkingError(throwable: Throwable) =
        if (isNetworkingError(throwable)) asNetworkingError(throwable)
        else Observable.error(throwable)


    private fun asNetworkingError(throwable: Throwable) = Observable.error<T>(
        mapToDomainError(throwable)
    )

    private fun mapToDomainError(error: Throwable): NetworkError {
        if (isConnectionTimeout(error)) return NetworkError.NetworkTimeout
        if (cannotReachHost(error)) return NetworkError.HostUnreachable
        return NetworkError.NetworkUnknownError
    }

    private fun isNetworkingError(error: Throwable) =
        isConnectionTimeout(error) || cannotReachHost(error) || isRequestCanceled(error)

    private fun isRequestCanceled(throwable: Throwable) =
        throwable is IOException && throwable.message?.contentEquals("Canceled") ?: false

    private fun cannotReachHost(error: Throwable): Boolean {
        return error is UnknownHostException || error is ConnectException ||  error is NoRouteToHostException
    }

    private fun isConnectionTimeout(error: Throwable) = error is SocketTimeoutException

}