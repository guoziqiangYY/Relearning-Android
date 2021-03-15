import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.SparseBooleanArray
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

import java.util.*

/**
 *    author : Guo
 *    date   : 2021/2/26
 *    desc   :
 *
 *
 *
 */
class OtherPageFragment : Fragment() {

    private var mCallBack: OnOtherPageResultCallback? = null
    private var requestCode: Int = 0
    private var generateDCIMUri: Uri? = null


    companion object {

//        const val ACTION_ALBUM: String = "action_album"
//        const val ACTION_CAPTURE: String = "action_capture"

        const val REQUEST_CODE: String = "OTHERPAGEFRAGMENT_REQUEST_CODE"


        const val BASE_ALBUM: Int = 0
        const val BASE_CAPTURE: Int = 1000
        const val BASE_QR: Int = 2000


        private val sRequestCodes = SparseBooleanArray()

        /**
         * 开启权限申请
         */
        fun beginRequest(
            activity: FragmentActivity,
            mBundle: Bundle,
            callback: OnOtherPageResultCallback?
        ) {
            val fragment = OtherPageFragment()
            val bundle = Bundle()
            var requestCode: Int
            // 请求码随机生成，避免随机产生之前的请求码，必须进行循环判断
            do {
                requestCode = getRandomRequestCode()
            } while (sRequestCodes[requestCode])
            // 标记这个请求码已经被占用
            sRequestCodes.put(requestCode, true)
            bundle.putInt(REQUEST_CODE, requestCode)
            bundle.putAll(mBundle)
            fragment.arguments = bundle
            // 设置保留实例，不会因为配置变化而重新创建
            fragment.retainInstance = true
            // 设置权限回调监听
            fragment.setCallBack(callback)
            OtherPageFragment.addFragment(activity.supportFragmentManager, fragment)

        }

        fun addFragment(manager: FragmentManager?, fragment: Fragment) {
            manager?.beginTransaction()?.add(fragment, fragment.toString())
                ?.commitAllowingStateLoss()
        }

        fun removeFragment(manager: FragmentManager?, fragment: Fragment?) {
            manager?.beginTransaction()?.remove(fragment!!)?.commitAllowingStateLoss()
        }


        /**
         * 获得随机的 RequestCode
         */
        fun getRandomRequestCode(): Int { // 请求码必须在 2 的 16 次方以内
            return Random().nextInt(Math.pow(2.0, 10.0).toInt())
        }

    }


    /**
     * 设置权限监听回调监听
     */
    fun setCallBack(callback: OnOtherPageResultCallback?) {
        mCallBack = callback
    }


    private fun toActivity() {
        arguments?.let {
            val string = requireArguments().getString("action")
            requestCode = requireArguments().getInt(REQUEST_CODE)


            val intent = Intent()
            if (string == ACTION_ALBUM) {
                intent.action = Intent.ACTION_PICK
                intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                requestCode += BASE_ALBUM

            }
            //去拍摄照片
            else if (string == ACTION_CAPTURE) {
                intent.action = MediaStore.ACTION_IMAGE_CAPTURE
                generateDCIMUri = null
                generateDCIMUri = UriUtils.generateDCIMUri(requireContext(), "capture", "others_capture")
                intent.putExtra(MediaStore.EXTRA_OUTPUT, generateDCIMUri)
                requestCode += BASE_CAPTURE
            } else if (string == ACTION_QR) {
                requestCode += BASE_QR
            }
            startActivityForResult(intent, requestCode)


        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var uri: Uri? = null
        var outputStr: String? = null
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode in 0..BASE_ALBUM && data != null) {
                uri = data.data
                outputStr = UriUtils.getFilePathFromUri(requireContext(), uri)
            } else if (requestCode in BASE_CAPTURE..2024 && generateDCIMUri != null) {
                uri = generateDCIMUri
                outputStr = UriUtils.getFilePathFromUri(requireContext(), generateDCIMUri)
            } else if (requestCode in BASE_QR..3024 && data != null) {
                outputStr = data.getStringExtra("scanResult")
            }
        }
        mCallBack?.onResult(outputStr, uri)
        // 释放对这个请求码的占用
        sRequestCodes.delete(requestCode)
        removeFragment(fragmentManager, this)
    }


    override fun onResume() {
        super.onResume()
        if (mCallBack == null) {
            OtherPageFragment.removeFragment(fragmentManager, this)
            return
        }
        toActivity()
    }


    override fun onDestroy() {
        super.onDestroy()
        // 取消引用监听器，避免内存泄漏
        mCallBack = null
    }


}