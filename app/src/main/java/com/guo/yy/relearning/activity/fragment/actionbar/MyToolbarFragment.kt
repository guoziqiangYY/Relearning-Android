package com.guo.yy.relearning.activity.fragment.actionbar

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.guo.yy.relearning.MyLog
import com.guo.yy.relearning.R
import com.guo.yy.relearning.TAG
import kotlinx.android.synthetic.main.layout_fragment_toolbar.*

/**
 *    author : Guo
 *    date   : 2021/1/29
 *    desc   :
 */
class MyToolbarFragment : Fragment(R.layout.layout_fragment_toolbar) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        /**
         * 可以先清除现有的menu菜单
         */
//        tb_toolbar.menu.clear()

        /**
         * 再创建新的菜单
         */
        tb_toolbar.inflateMenu(R.menu.menu_fragment_toolbar)

        tb_toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_settings -> {
                    MyLog.i(TAG, "R.id.action_settings")
                    true
                }
                R.id.action_alarm -> {
                    MyLog.i(TAG, "R.id.action_alarm")
                    true
                }
                else -> {
                    false
                }
            }
        }


        tb_toolbar.setNavigationIcon(R.drawable.icon_back)

        tb_toolbar.setNavigationOnClickListener {
            MyLog.i(TAG, "back")
           requireActivity().onBackPressed()
        }

    }
}