package com.guo.yy.relearning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.guo.yy.relearning.activity.WrapperLifecycle

class MainActivity : AppCompatActivity() {

    private val GAME_STATE_KEY: String = "GAME_STATE_KEY"
    private val TEXT_VIEW_KEY: String = "TEXT_VIEW_KEY"

    lateinit var textView: TextView
    // some transient state for the activity instance
    var gameState: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG, "onCreate1 gameState:${gameState}")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gameState = savedInstanceState?.getString(GAME_STATE_KEY)
        textView = findViewById<TextView>(R.id.tv_helloworld)
        Log.i(TAG, "onCreate2 gameState:${gameState}")
        WrapperLifecycle(this)
        Log.i(TAG, "onCreate3 gameState:${gameState}")

    }


    /**
     * 可以在onCreate中恢复数据，也可以在这里恢复数据，两个地方的bundle是同一个
     * 可以看到，在这个毁掉中，bundle不会为null，所以在这里恢复会是更好的选择
     * super()更是会恢复视图层级结构
     */
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        Log.i(TAG, "onRestoreInstanceState1 gameState:${gameState}")

        super.onRestoreInstanceState(savedInstanceState)
        textView.text = savedInstanceState.getString(TEXT_VIEW_KEY)
        gameState = savedInstanceState.getString(GAME_STATE_KEY)
        Log.i(TAG, "onRestoreInstanceState2 gameState:${gameState}")


    }


    /**
     * 当该Activity可能被临时销毁时候。可以在这里保存一些想要恢复的数据
     */
    override fun onSaveInstanceState(outState: Bundle) {
        Log.i(TAG, "onSaveInstanceState")
        outState?.run {
            putString(GAME_STATE_KEY, gameState)
            putString(TEXT_VIEW_KEY, textView.text.toString())

        }
        super.onSaveInstanceState(outState)
    }

    /**
     * onCreate() 退出后，Activity 将进入“已启动”状态，并对用户可见。
     * 此回调包含 Activity 进入前台与用户进行互动之前的最后准备工作。
     */
    override fun onStart() {
        Log.i(TAG, "onStart1")
        super.onStart()
        Log.i(TAG, "onStart2")

    }


    /**
     * 系统会在 Activity 开始与用户互动之前调用此回调。此时，该 Activity 位于 Activity 堆栈的顶部，
     * 并会捕获所有用户输入。应用的大部分核心功能都是在 onResume() 方法中实现的。
     */
    override fun onResume() {
        Log.i(TAG, "onResume1")

        super.onResume()
        Log.i(TAG, "onResume2")

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
        Log.i(TAG, "onPause1")

        super.onPause()
        Log.i(TAG, "onPause2")

    }

    /**
     * 系统调用的下一个回调将是 @link{onRestart()}（如果 Activity 重新与用户互动）
     * 或者 @link{onDestroy()} （如果 Activity 彻底终止）。
     */
    override fun onStop() {
        Log.i(TAG, "onStop1")

        super.onStop()
        Log.i(TAG, "onStop2")

    }

    /**
     * 后边跟onStart()
     */
    override fun onRestart() {
        Log.i(TAG, "onRestart1")

        super.onRestart()
        Log.i(TAG, "onRestart2")

    }


    override fun onDestroy() {
        Log.i(TAG, "onDestroy1")

        super.onDestroy()
        Log.i(TAG, "onDestroy2")

    }
}