package com.guo.yy.relearning.activity.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory

/**
 *    author : Guo
 *    date   : 2021/1/28
 *    desc   :
 *              默认情况下，FragmentManager 使用框架提供的 FragmentFactory 实例化 Fragment 的新实例。此默认工厂使用反射来查找和调用 Fragment
 *              的无参数构造函数。这意味着，您无法使用此默认工厂为 Fragment 提供依赖项。这也意味着，默认情况下，在重新创建过程中，
 *              不会使用您首次创建 Fragment 时所用的任何自定义构造函数。
 *              如需为 Fragment 提供依赖项或使用任何自定义构造函数，您必须创建自定义 FragmentFactory
 *              子类，然后替换 FragmentFactory.instantiate。您随后可以将 FragmentManager 的默认工厂替换为您的自定义工厂，它随后用于实例化 Fragment。
 *
 *
 * 您随后可以通过在 FragmentManager 上设置一个属性，将 MyFragmentFactory 指定为要在构造应用的 Fragment 时使用的工厂。
 * 您必须在 Activity 的 super.onCreate() 之前设置此属性，以确保在重新创建 Fragment 时使用 MyFragmentFactory。
 *
 */
class MyFragmentFactory(val arg: String?):FragmentFactory() {


    /**
     * 重写这个方法，来定义配置恢复时候，创建对应的Fragment实例
     */
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
//        return super.instantiate(classLoader, className)

        when (loadFragmentClass(classLoader, className)) {
            CreateAFragment::class.java -> {
                return CreateAFragment(arg)
            }
            else -> {
                return super.instantiate(classLoader, className)
            }
        }

    }
}