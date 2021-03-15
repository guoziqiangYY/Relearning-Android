import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.FragmentActivity

import java.util.*

/**
 *    author : Guo
 *    date   : 2021/2/26
 */
class OtherPageLauncher(private val mActivity: FragmentActivity?) {

    var mBundle: Bundle = Bundle()

    companion object {

        fun with(activity: FragmentActivity): OtherPageLauncher {
            return OtherPageLauncher(activity)
        }

        fun with(context: Context): OtherPageLauncher {
            val fragmentActivity = getFragmentActivity(context)
            return OtherPageLauncher(fragmentActivity)
        }

        /**
         * 获取上下文中的 Activity 对象
         */
        fun getFragmentActivity(mContext: Context): FragmentActivity? {
            var context = mContext
            if (context is FragmentActivity) {
                return context
            } else if (context is ContextWrapper) {
                context.baseContext
            } else {
                return null
            }
            return null
        }


    }


    fun target(@PageTarget action: String): OtherPageLauncher {
        mBundle.putString("action", action)
        return this
    }


    /**
     * 这里可以在外部添加额外的参数，主要针对于bundle
     */
    fun extra(settings: OtherPageLauncher.() -> Unit): OtherPageLauncher {
        apply(settings)
        return this
    }


    fun request(callback: OnOtherPageResultCallback) {
        // 如果传入 Activity 为空或者 Activity 状态非法
        if (mActivity == null
            || mActivity.isFinishing
            || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && mActivity.isDestroyed)
        ) {
            return
        }

        // 申请没有授予过的权限
        // 申请没有授予过的权限
        OtherPageFragment.beginRequest(mActivity, mBundle, callback)
    }


}