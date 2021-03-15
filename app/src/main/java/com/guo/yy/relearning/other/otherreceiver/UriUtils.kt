

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import java.text.SimpleDateFormat
import java.util.*

/**
 *    author : Guo
 *    date   : 2021/2/25
 *    desc   :
 */
class UriUtils {
    companion object {

         fun generateDCIMUri(context: Context, fileName: String, dirName:String): Uri? {
            var insertUri: Uri? = null
            val timeStamp: String =
                SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
            val imageFileName = "${fileName}_$timeStamp"
            val values = ContentValues()
            values.put(MediaStore.Images.Media.DESCRIPTION, imageFileName)
            values.put(MediaStore.Images.Media.DISPLAY_NAME, imageFileName)
            values.put(MediaStore.Images.Media.DATE_MODIFIED, System.currentTimeMillis() / 1000)
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//                values.put(MediaStore.Images.Media.RELATIVE_PATH, "DCIM/${dirName}")
//            }
            val external: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            //这里不同的android版本，会需要不同的权限，例如在android10，该操作不需要WRITE_EXTERNAL_STORAGE，但是在android8，则需要这个权限
            insertUri = context.contentResolver.insert(external, values)

            return insertUri
        }




        fun getFilePathFromUri(context: Context,uri: Uri?): String? {
            if (null == uri) return null
            val scheme = uri.scheme
            var data: String? = null
            if (scheme == null) data = uri.path else if (ContentResolver.SCHEME_FILE == scheme) {
                data = uri.path
            } else if (ContentResolver.SCHEME_CONTENT == scheme) {
                context.contentResolver
                    .query(uri, arrayOf(MediaStore.Images.ImageColumns.DATA), null, null, null)
                    ?.let {
                        if (it.moveToFirst()) {
                            val index: Int = it.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
                            if (index > -1) {
                                data = it.getString(index)
                            }
                        }
                        it.close()
                    }
            }
            return data
        }

         fun toSysCapture(activity: Activity,photoUri:Uri,code:Int){
            val intent2Capture = Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent2Capture.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
            activity.startActivityForResult(intent2Capture, code)
        }



         fun dealActivityResult(){

        }


    }
}