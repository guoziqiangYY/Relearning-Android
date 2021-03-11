package com.guo.yy.relearning.activity.fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.transition.Fade
import androidx.transition.TransitionInflater
import com.guo.yy.relearning.R
import com.guo.yy.relearning.activity.fragment.share.DetailTransition
import kotlinx.android.synthetic.main.layout_fragment_anim.*


/**
 *    author : Guo
 *    date   : 2021/1/28
 *    desc   :
 */
class AnimationFragment:Fragment(R.layout.layout_fragment_anim) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(requireContext()).inflateTransition(R.transition.shared_image)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewCompat.setTransitionName(iv_detail, "iv_detail")

        /**
         * 如果想要等待一段时间在进行过渡转场，那么可以在该回调中调用 这个方法。调用一个延迟开始过渡转场
         */
//        postponeEnterTransition()
//        postponeEnterTransition(2000L,TimeUnit.MILLISECONDS)

        iv_detail.setImageResource(R.drawable.test1)
    }
}