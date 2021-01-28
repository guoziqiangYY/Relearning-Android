package com.guo.yy.relearning.activity.fragment.share

import android.content.Context
import android.util.AttributeSet
import androidx.transition.ChangeBounds
import androidx.transition.ChangeImageTransform
import androidx.transition.ChangeTransform
import androidx.transition.TransitionSet


/**
 *    author : Guo
 *    date   : 2021/1/28
 *    desc   :
 */
class DetailTransition(context: Context?, attrs: AttributeSet?) : TransitionSet(context, attrs) {


    init {
        init();
    }

    private fun init() {
        ordering = ORDERING_TOGETHER
        addTransition(ChangeBounds())
            .addTransition(ChangeTransform())
            .addTransition(ChangeImageTransform())
    }

}