
import androidx.annotation.StringDef

/**
 *    author : Guo
 *    date   : 2021/2/26
 *    desc   :
 */

const val ACTION_ALBUM: String = "action_album"
const val ACTION_CAPTURE: String = "action_capture"
const val ACTION_QR: String = "action_qr"


/**
 *
 */
@StringDef(ACTION_ALBUM, ACTION_CAPTURE, ACTION_QR)
@kotlin.annotation.Target(AnnotationTarget.FIELD,AnnotationTarget.VALUE_PARAMETER,AnnotationTarget.FUNCTION,AnnotationTarget.TYPE)
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
annotation class PageTarget