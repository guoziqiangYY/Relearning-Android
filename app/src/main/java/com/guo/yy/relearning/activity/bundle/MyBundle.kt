package com.guo.yy.relearning.activity.bundle

import android.os.Parcel
import android.os.Parcelable

/**
 *    author : Guo
 *    date   : 2021/1/27
 *    desc   :
 *
 *    使用bundle发送数据的时候，需要注意不要太大，最好在几KB以内。
 *
 *    在跨进程的时候更要注意数据的大小，因为安卓中，跨进程通信使用的是binder。而binder事务缓冲区大小固定为1MB。并且是由所有的书屋所共享。该限制是进程级别的，
 *    如onSaveInstanceState、startActivity、与系统的互动（如通知栏推送）都会占用。超过大小，会报TransactionTooLargeException。
 *
 *    在onSaveInstanceState中保存数据，建议保持在50k以内。
 *
 *
 */
data class MyBundle (var name:String?=null,var age:Int):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(p: Parcel?, p1: Int) {
        p?.writeString(name)
        p?.writeInt(age)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MyBundle> {
        override fun createFromParcel(parcel: Parcel): MyBundle {
            return MyBundle(parcel)
        }

        override fun newArray(size: Int): Array<MyBundle?> {
            return arrayOfNulls(size)
        }
    }
}