package com.guo.yy.relearning

import android.app.Activity
import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.guo.yy.relearning.activity.BaseActivity
import com.guo.yy.relearning.activity.MyActionBarActivity
import com.guo.yy.relearning.activity.lifecycle.WrapperLifecycle
import com.guo.yy.relearning.activity.MyFragmentActivity
import com.guo.yy.relearning.test.A
import com.guo.yy.relearning.test.B
import com.guo.yy.relearning.test.C
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : BaseActivity() {

    private val GAME_STATE_KEY: String = "GAME_STATE_KEY"
    private val TEXT_VIEW_KEY: String = "TEXT_VIEW_KEY"

    lateinit var textView: TextView
    // some transient state for the activity instance
    var gameState: String? = null

    var wrapperLifecycle: WrapperLifecycle? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gameState = savedInstanceState?.getString(GAME_STATE_KEY)
        textView = findViewById<TextView>(R.id.tv_helloworld)
        wrapperLifecycle =
            WrapperLifecycle(this)
        MyLog.i(TAG, "onCreate gameState:${gameState}")


//        btn_fragment
        initBtn()


        val a = A()

        a.a()


        test1(a)

        test2(a)



        val application = Application()

        val application1 = Application()

        Log.e("gzq","application:${application}== ${application1}")

        Toast.makeText(application,"1",Toast.LENGTH_LONG).show()
        Toast.makeText(application1,"2",Toast.LENGTH_LONG).show()

    }

    fun test1(a: C){
        a.a()
    }


    fun test2(b: B){
        b.a()
    }



    private fun initBtn() {

        btn_fragment.setOnClickListener{
            startActivity(Intent(this,
                MyFragmentActivity::class.java))
        }
        btn_fragment_actionbar.setOnClickListener{
            startActivity(Intent(this,
                MyActionBarActivity::class.java))
        }
        btn_switch_shortcuts.setOnClickListener{
           switchShortcuts()
        }

        btn_createPinShortcut.setOnClickListener{
            createPinShortcut()
        }




    }


    /**
     * 动态更改快捷方式 必须要25以上才可以
     *
     * 动态修改的，只能用于动态修改的，不能修改同ID的静态的
     *
     */
    @RequiresApi(Build.VERSION_CODES.N_MR1)
    private fun switchShortcuts() {
        val shortcutManager = getSystemService<ShortcutManager>(ShortcutManager::class.java)
        val shortcut = ShortcutInfo.Builder(this, "compose1")
            .setShortLabel("Website")
            .setLongLabel("Open the website")
            .setIcon(Icon.createWithResource(this, R.drawable.icon_airepay))
            .setIntent(Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.mysite.example.com/")))
            .build()
//        shortcutManager!!.dynamicShortcuts = listOf(shortcut)
//        shortcutManager?.removeAllDynamicShortcuts()
//        shortcutManager?.updateShortcuts(listOf(shortcut))
        shortcutManager!!.dynamicShortcuts = listOf(shortcut)

    }



    @RequiresApi(Build.VERSION_CODES.O)
    private fun createPinShortcut(){
        val shortcutManager = getSystemService(ShortcutManager::class.java)

        if (shortcutManager!!.isRequestPinShortcutSupported) {
            // Assumes there's already a shortcut with the ID "my-shortcut".
            // The shortcut must be enabled.
            val pinShortcutInfo = ShortcutInfo.Builder(this, "my-shortcut")
                . setShortLabel("pin website")
                .setLongLabel("pin Open the website")
                .setIcon(Icon.createWithResource(this, R.drawable.icon_airepay))
                .setIntent(Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.mysite.example.com/")))
                .build()

            // Create the PendingIntent object only if your app needs to be notified
            // that the user allowed the shortcut to be pinned. Note that, if the
            // pinning operation fails, your app isn't notified. We assume here that the
            // app has implemented a method called createShortcutResultIntent() that
            // returns a broadcast intent.
             val pinnedShortcutCallbackIntent = shortcutManager.createShortcutResultIntent(pinShortcutInfo)

            // Configure the intent so that your app's broadcast receiver gets
            // the callback successfully.For details, see PendingIntent.getBroadcast().
            val successCallback = PendingIntent.getBroadcast(this, /* request code */ 0,
                pinnedShortcutCallbackIntent, /* flags */ 0)

            shortcutManager.requestPinShortcut(pinShortcutInfo,
                successCallback.intentSender)
        }
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