package com.guo.yy.relearning.activity

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.guo.yy.relearning.MyLog
import com.guo.yy.relearning.R
import com.guo.yy.relearning.TAG

/**
 *    author : Guo
 *    date   : 2021/1/21
 *    desc   :
 */
open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        MyLog.i(TAG, "onCreate before super")
        super.onCreate(savedInstanceState)
        MyLog.i(TAG, "onCreate after super")
    }


    /**
     * 可以在onCreate中恢复数据，也可以在这里恢复数据，两个地方的bundle是同一个
     * 可以看到，在这个毁掉中，bundle不会为null，所以在这里恢复会是更好的选择
     * super()更是会恢复视图层级结构
     */
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        MyLog.i(TAG, "onRestoreInstanceState before super")
        super.onRestoreInstanceState(savedInstanceState)
        MyLog.i(TAG, "onRestoreInstanceState after super")
    }


    /**
     * 当该Activity可能被临时销毁时候。可以在这里保存一些想要恢复的数据
     */
    override fun onSaveInstanceState(outState: Bundle) {
        MyLog.i(TAG, "onSaveInstanceState before super")
        super.onSaveInstanceState(outState)
        MyLog.i(TAG, "onSaveInstanceState after super")

    }

    /**
     * onCreate() 退出后，Activity 将进入“已启动”状态，并对用户可见。
     * 此回调包含 Activity 进入前台与用户进行互动之前的最后准备工作。
     */
    override fun onStart() {
        MyLog.i(TAG, "onStart before super")
        super.onStart()
        MyLog.i(TAG, "onStart after super")
    }


    /**
     * 系统会在 Activity 开始与用户互动之前调用此回调。此时，该 Activity 位于 Activity 堆栈的顶部，
     * 并会捕获所有用户输入。应用的大部分核心功能都是在 onResume() 方法中实现的。
     */
    override fun onResume() {
        MyLog.i(TAG, "onResume before super")
        super.onResume()
        MyLog.i(TAG, "onResume after super")

    }

    /**
     * 当 Activity 失去焦点并进入“已暂停”状态时，系统就会调用 onPause()。
     * 例如，当用户点按“返回”或“最近使用的应用”按钮时，就会出现此状态。当系统为您的 Activity 调用 onPause() 时，
     * 从技术上来说，这意味着您的 Activity 仍然部分可见，但大多数情况下，这表明用户正在离开该 Activity，该 Activity 很快将进入“已停止”或“已恢复”状态。
     * 如果用户希望界面继续更新，则处于“已暂停”状态的 Activity 也可以继续更新界面。例如，显示导航地图屏幕或播放媒体播放器的 Activity 就属于此类 Activity。即使此类 Activity 失去了焦点，用户仍希望其界面继续更新。
     *
     * 您不应使用 onPause() 来保存应用或用户数据、进行网络呼叫或执行数据库事务。有关保存数据的信息，请参阅保存和恢复 Activity 状态。
     */
    override fun onPause() {
        MyLog.i(TAG, "onPause before super")
        super.onPause()
        MyLog.i(TAG, "onPause after super")

    }

    /**
     * 系统调用的下一个回调将是 @link{onRestart()}（如果 Activity 重新与用户互动）
     * 或者 @link{onDestroy()} （如果 Activity 彻底终止）。
     */
    override fun onStop() {
        MyLog.i(TAG, "onStop before super")
        super.onStop()
        MyLog.i(TAG, "onStop after super")

    }

    /**
     * 后边跟onStart()
     */
    override fun onRestart() {
        MyLog.i(TAG, "onRestart before super")
        super.onRestart()
        MyLog.i(TAG, "onRestart after super")

    }


    override fun onDestroy() {
        MyLog.i(TAG, "onDestroy before super")
        super.onDestroy()
        MyLog.i(TAG, "onDestroy after super")

    }
}