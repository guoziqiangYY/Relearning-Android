package com.guo.yy.relearning.activity

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.guo.yy.relearning.R
import com.guo.yy.relearning.activity.fragment.CreateAFragment
import com.guo.yy.relearning.activity.fragment.MyFragmentFactory

/**
 *    author : Guo
 *    date   : 2021/1/28
 *    desc   :
 */
class MyFragmentActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        //如果要设置自定义的FragmentFactory，需要在super.onCreat()前调用，如想要再次更换，再子的在更换
        supportFragmentManager.fragmentFactory =
            MyFragmentFactory("restore arg")
        super.onCreate(savedInstanceState)


        setContentView(R.layout.layout_activity_fragmenttest)


        if (savedInstanceState == null) {
            val bundle = bundleOf("some_int" to 1)
            supportFragmentManager
                .commit {
                    setCustomAnimations(
                        R.anim.slide_in,
                        R.anim.fade_out,
                        R.anim.fade_in,
                        R.anim.slide_out
                    )
                    setReorderingAllowed(true)
                    add<CreateAFragment>(R.id.fcv_create_by_programmatically, "", bundle)
//                    addToBackStack("")
                }

//            supportFragmentManager.beginTransaction()
//                .setReorderingAllowed(true)
//                .add(R.id.fcv_create_by_programmatically,CreateAFragment::class.java,null)
//                .commit()


        }

    }


}