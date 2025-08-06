package com.example.keepalivedemo.socket.utils

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * Just :
 * @author by Zian
 * @date on 2019/08/19 16
 */
object RxUtil {
    /**
     * 轮询
     */
    fun polling(delay: Long, period: Long): Observable<Long> {
        return Observable.interval(delay, period, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}