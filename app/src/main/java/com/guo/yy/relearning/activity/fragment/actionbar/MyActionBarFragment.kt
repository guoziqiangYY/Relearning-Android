package com.guo.yy.relearning.activity.fragment.actionbar

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import com.guo.yy.relearning.MyLog
import com.guo.yy.relearning.R
import com.guo.yy.relearning.TAG
import kotlinx.android.synthetic.main.layout_fragment_actionbar.*

/**
 *    author : Guo
 *    date   : 2021/1/29
 *    desc   :
 */
class MyActionBarFragment:Fragment(R.layout.layout_fragment_actionbar) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * 向 Activity 注册
         *  您必须告知系统您的应用栏 Fragment 当前参与选项菜单的填充。
         *  为此，请在 Fragment 的 onCreate(Bundle) 方法中调用 setHasOptionsMenu(true)，
         */
        setHasOptionsMenu(true)



    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_refresh_menu.setOnClickListener {
            //通知Activity，是当前的menu无效，其作用是刷新该menu
            requireActivity().invalidateOptionsMenu()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_actionbar, menu)
        MyLog.i(TAG,"onCreateOptionsMenu")
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        MyLog.i(TAG,"onPrepareOptionsMenu")

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_settings ->{
                MyLog.i(TAG,"R.id.action_settings")
                true
            }
            R.id.action_alarm ->{
                MyLog.i(TAG,"R.id.action_alarm")
                true
            }
//            R.id.action_bug ->{
//                MyLog.i(TAG,"R.id.action_bug")
//                true
//            }
            else ->{
                MyLog.i(TAG,"R.id.action_bug")
                super.onOptionsItemSelected(item)

            }
        }


    }
}