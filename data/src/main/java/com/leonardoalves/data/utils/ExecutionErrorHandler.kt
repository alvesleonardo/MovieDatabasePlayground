package com.leonardoalves.data.utils

import com.leonardoalves.data_base.comunication.HandleConnectivityIssue
import com.leonardoalves.data_base.comunication.HandleErrorByHttpStatus
import io.reactivex.Observable
import io.reactivex.ObservableTransformer

class ExecutionErrorHandler<T>() : ObservableTransformer<T, T> {

    override fun apply(upstream: Observable<T>) =
        upstream
            .compose(HandleErrorByHttpStatus())
            .compose(HandleConnectivityIssue())
}