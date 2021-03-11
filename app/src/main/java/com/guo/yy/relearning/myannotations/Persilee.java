package com.guo.yy.relearning.myannotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author : Guo
 * date   : 2021/3/2
 * desc   :
 *         在注解上面的注解称为元注解(meta-annotations)
 *          @Retention 指定注解的存储方式：SOURCE, // 标记的注解仅保留在源级别中，并被编译器忽略。
 *                                              用于APT技术，在编译期能获取注解与注解声明的类和类中所有成员信息，一般用于生成额外的辅助类。
 *                                        CLASS, // 标记的注解在编译时由编译器保留，但 Java 虚拟机(JVM)会忽略。
 *                                              用于字节码增强，在编译出Class后，通过修改Class数据以实现修改代码逻辑目的，对于是否需要修改的区分或者修改为不同逻辑的判断可以使用注解。
 *                                        RUNTIME // 标记的注解由 JVM 保留，因此运行时环境可以使用它。
 *                                              反射，在程序运行时，通过反射技术动态获取注解与其元素，从而完成不同的逻辑判断。
 *          @Target 指定注解可以使用的范围：TYPE, // 类    
 *                                         FIELD, // 字段或属性    
 *                                         METHOD, // 方法    
 *                                         PARAMETER, // 参数    
 *                                         CONSTRUCTOR, // 构造方法    
 *                                         LOCAL_VARIABLE, // 局部变量    
 *                                         ANNOTATION_TYPE, // 也可以使用在注解上    
 *                                         PACKAGE, // 包    
 *                                         TYPE_PARAMETER, // 类型参数    只可以用在泛型上
 *                                         TYPE_USE // 任何类型
 *         @Documented 注解表示使用了指定的注解，将使用 Javadoc 工具记录这些元素。
 *         @Inherited 注解表示注解类型可以从超类继承
 *         @Repeatable 注解表明标记的注解可以多次应用于同一声明或类型使用。
 *
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface Persilee {
    int id();
    String value() default "gzq";
}
