package com.guo.yy.relearning.myannotations;

import androidx.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author : Guo
 * date   : 2021/3/2
 * desc   :
 */
public class ExampleAnnotation {


    /**
     * 使用枚举，占用内存，实质是特殊的静态成员变量，在运行时会被加载成单例。
     */

    private static WeekDay mWeekDay;


    enum WeekDay {
        SATURDAY,
        SUNDAY
    }


    public static WeekDay getWeekDay() {
        return mWeekDay;
    }

    public static void setWeekDay(WeekDay day) {
        mWeekDay = day;
    }





    /**
     * 使用注解
     *
     */

    private static final int MONDAY = 0;
    private static final int TUESDAY =1;

    private static int mDefWeekDay;

    @IntDef({MONDAY,TUESDAY})
    @Target({ElementType.FIELD,ElementType.PARAMETER,ElementType.METHOD})
    @Retention(RetentionPolicy.SOURCE)
    @interface WeekDayDef{

    }


    public static  @WeekDayDef int getDefWeekDay() {
        return mDefWeekDay;
    }

    public static void setDefWeekDay(@WeekDayDef int day) {
        mDefWeekDay = day;
    }






    public static void main(String[] args) {

        ExampleAnnotation.setWeekDay(WeekDay.SUNDAY);

    }
}


