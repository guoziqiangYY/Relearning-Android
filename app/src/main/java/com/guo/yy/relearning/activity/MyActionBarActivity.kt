package com.guo.yy.relearning.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.guo.yy.relearning.MyLog
import com.guo.yy.relearning.R
import com.guo.yy.relearning.activity.fragment.actionbar.MyActionBarFragment
import com.guo.yy.relearning.TAG
import com.guo.yy.relearning.activity.fragment.actionbar.MyToolbarFragment

/**
 *    author : Guo
 *    date   : 2021/1/29
 *    desc   :
 */
class MyActionBarActivity:BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_activity_actionbar)


        initFragment()

    }

    private fun initFragment() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<MyActionBarFragment>(R.id.fcv_fragment)

        }
    }


    /**
     * 返回true，表示要拦截该事件，返回super，则会继续分发
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_settings ->{
                MyLog.i(TAG,"R.id.action_settings")
                super.onOptionsItemSelected(item)
            }
            R.id.action_alarm ->{
                MyLog.i(TAG,"R.id.action_alarm")

                supportFragmentManager.commit {
                    setCustomAnimations(
                        R.anim.slide_in,
                        R.anim.fade_out,
                        R.anim.fade_in,
                        R.anim.slide_out
                    )
                    setReorderingAllowed(true)
                    add<MyToolbarFragment>(R.id.fcv_fragment)
                    addToBackStack(null)
                }

                true
            }
            R.id.action_bug ->{
                MyLog.i(TAG,"R.id.action_bug")
                super.onOptionsItemSelected(item)
            }
            else ->{
                MyLog.i(com.guo.yy.relearning.TAG,"others")
                super.onOptionsItemSelected(item)

            }
        }


    }

}