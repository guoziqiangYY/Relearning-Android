package com.guo.yy.relearning.activity.fragment

import android.os.Bundle
import android.transition.ChangeBounds
import android.view.View
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.transition.TransitionInflater
import com.guo.yy.relearning.MyLog
import com.guo.yy.relearning.R
import com.guo.yy.relearning.TAG
import kotlinx.android.synthetic.main.layout_fragment_create.*

/**
 *    author : Guo
 *    date   : 2021/1/28
 *    desc   :
 *
 *
 *    注意Fragment的转场动画，分为enter、exit、popEnter、popExit，只对当前的transaction有效，如果下一个transaction没有设置的话，那么还是没有的。
 *
 *    enter、exit，表示有新来的Fragment时候，enter<->上层Fragment进入动画，exit（必须是被remove）<->底层Fragment的退出动画
 *    popEnter、popExit，表示用户设置了backStack，popEnter（reAdd or reAttach）<->底层Fragment的进入动画， popExit<->上层Fragment的退出动画
 *
 * =============================
 *    或者换一种方法，设置了该动画的Fragment，
 *      该Fragment作为新进入的Fragment，enter动画会起作用。
 *      该Fragment作为接收新Fragment进入的底层Fragment，面对新的Fragment进入，自己淡化退出的时候，exit动画会起作用
 *
 *      该Fragment最为用户popBackStack的时候，退出的Fragment，popExit起作用
 *      该Fragment最为用户popBackStack的时候，退出的Fragment下，显露的Fragment，popEnter起作用
 */
class CreateAFragment(val arg: String?) : Fragment(R.layout.layout_fragment_create) {

    companion object {
        var count: Int = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inflater = TransitionInflater.from(requireContext())

        /**
         * 注意，该转场动画是与setCustomAnimations都起效果
         */
//        exitTransition = inflater.inflateTransition(R.transition.fade)
//        enterTransition = inflater.inflateTransition(R.transition.slide_right);


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val someInt = arguments?.getInt("some_int")
        MyLog.i(TAG, "someInt:${someInt}")

        ViewCompat.setTransitionName(iv_thumb, "iv_thumb")

        tv_add.setOnClickListener {
            parentFragmentManager
                .commit {
                    setCustomAnimations(
                        R.anim.slide_in,
                        R.anim.fade_out,
                        R.anim.fade_in,
                        R.anim.slide_out
                    )
                    addSharedElement(iv_thumb, "iv_detail")
                    setReorderingAllowed(true)
                    add<AnimationFragment>(R.id.fcv_create_by_programmatically)
                    addToBackStack(null)
                }
        }
        tv_replace.setOnClickListener {
            if (count == 0) {
                parentFragmentManager
                    .commit {
                        setCustomAnimations(
                            R.anim.slide_in,
                            R.anim.fade_out,
                            R.anim.fade_in,
                            R.anim.slide_out
                        )
                        setReorderingAllowed(true)
                        replace<CreateAFragment>(R.id.fcv_create_by_programmatically)
                        addToBackStack(null)
                    }
                count++
            } else {
                parentFragmentManager
                    .commit {
                        setReorderingAllowed(true)
                        replace<CreateAFragment>(R.id.fcv_create_by_programmatically)
                        addToBackStack(null)
                    }
            }

        }


        iv_thumb.setOnClickListener {
            parentFragmentManager
                .commit {
                    setReorderingAllowed(true)
                    addSharedElement(iv_thumb, "iv_detail")
                    replace<AnimationFragment>(R.id.fcv_create_by_programmatically)
                    addToBackStack(null)
                }
        }

    }


    /**
     *  在API28以前，在onStop()之前回调
     *  在API28及以上，在onStop()之后调用
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }
}