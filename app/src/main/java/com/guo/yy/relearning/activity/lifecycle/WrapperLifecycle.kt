package com.guo.yy.relearning.activity.lifecycle

import android.content.Context
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.guo.yy.relearning.MyLog
import com.guo.yy.relearning.TAG

/**
 *    author : Guo
 *    date   : 2021/1/20
 *    desc   :
 *
 *    实现了LifecycleObserver组件的生命周期感知类。
 *    需要提供addObserver和removeObserver
 *    需要注意的是，可视化过程中，即onCreate，onStart，onResume是在activity的生命周期后调用。
 *                 不可视化过程中，即onPause，onStop，onDestory是在activity的生命周期前调用的。
 *
 */
class WrapperLifecycle(bound: LifecycleOwner) : LifecycleObserver {

    private var lifecycle: Lifecycle = bound.lifecycle

    init {
        lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onWrapperCreate() {
        MyLog.i(TAG, "onWrapperCreate")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onWrapperStart() {
        MyLog.i(TAG, "onWrapperStart")

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onWrapperResume() {
        MyLog.i(TAG, "onWrapperResume")

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onWrapperPause() {
        MyLog.i(TAG, "onWrapperPause")

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onWrapperStop() {
        MyLog.i(TAG, "onWrapperStop")

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onWrapperDestory() {
        MyLog.i(TAG, "onWrapperDestory")

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun onWrapperAny() {
        MyLog.i(TAG, "onWrapperAny")

    }


    fun unbind() {
        lifecycle.removeObserver(this)
    }

}