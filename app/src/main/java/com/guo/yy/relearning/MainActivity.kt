package com.guo.yy.relearning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.guo.yy.relearning.activity.BaseActivity
import com.guo.yy.relearning.activity.WrapperLifecycle

class MainActivity : BaseActivity() {

    private val GAME_STATE_KEY: String = "GAME_STATE_KEY"
    private val TEXT_VIEW_KEY: String = "TEXT_VIEW_KEY"

    lateinit var textView: TextView
    // some transient state for the activity instance
    var gameState: String? = null

    var wrapperLifecycle:WrapperLifecycle ?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gameState = savedInstanceState?.getString(GAME_STATE_KEY)
        textView = findViewById<TextView>(R.id.tv_helloworld)
         wrapperLifecycle = WrapperLifecycle(this)
        MyLog.i(TAG, "onCreate gameState:${gameState}")

    }


    /**
     * 可以在onCreate中恢复数据，也可以在这里恢复数据，两个地方的bundle是同一个
     * 可以看到，在这个毁掉中，bundle不会为null，所以在这里恢复会是更好的选择
     * super()更是会恢复视图层级结构
     */
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        textView.text = savedInstanceState.getString(TEXT_VIEW_KEY)
        gameState = savedInstanceState.getString(GAME_STATE_KEY)
        MyLog.i(TAG, "onRestoreInstanceState gameState:${gameState}")


    }


    /**
     * 当该Activity可能被临时销毁时候。可以在这里保存一些想要恢复的数据
     */
    override fun onSaveInstanceState(outState: Bundle) {
        MyLog.i(TAG, "onSaveInstanceState")
        outState?.run {
            putString(GAME_STATE_KEY, gameState)
            putString(TEXT_VIEW_KEY, textView.text.toString())

        }
        super.onSaveInstanceState(outState)
    }


    override fun onDestroy() {
        super.onDestroy()
        wrapperLifecycle?.unbind()
    }

}