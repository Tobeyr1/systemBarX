package com.tobery.systembarx

import android.app.Activity
import android.content.res.Resources
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.view.*

object SystemBarXUtil {

    /**
     * set the statusBar color
     * @param activity Current page
     * @param colorId  statusBar color
     */
    @JvmStatic
    fun setStatusBarColor(activity: Activity,@ColorRes colorId: Int){
        activity.window.statusBarColor = ContextCompat.getColor(activity,colorId)
    }

    /**
     * change the statusBar icon and text color
     * @param activity Current page
     * @param darkText Whether make the statusBar icon and text to dark color
     */
    @JvmStatic
    fun setStatusBarTextColor(activity: Activity,darkText: Boolean){
        WindowInsetsControllerCompat(activity.window,activity.window.decorView).isAppearanceLightStatusBars = darkText
    }

    /**
     * set page full screen
     * @param activity page that need to be changed
     */
    @JvmStatic
    fun fitsSystemWindows(activity: Activity){
        WindowCompat.setDecorFitsSystemWindows(activity.window,false)
    }

    /**
     * change the foreground color of the navigation
     * @param activity
     * @param darkMode change the navigationBar foreground color model
     */
    @JvmStatic
    fun setNavigationBarColor(activity: Activity,darkMode: Boolean){
        WindowInsetsControllerCompat(activity.window,activity.window.decorView).isAppearanceLightNavigationBars = darkMode
    }

    /**
     * change the navigationBar visibility
     * @param activity Current page
     * @param isHide whether to hide the navigationBar
     */
    @JvmStatic
    fun immersiveNavigationBar(activity: Activity,isHide: Boolean){
        WindowInsetsControllerCompat(activity.window,activity.window.decorView).let {
            if (isHide){
                it.hide(WindowInsetsCompat.Type.navigationBars())
            }else{
                it.show(WindowInsetsCompat.Type.navigationBars())
            }
        }
    }

    /**
     * monitor soft keyboard status
     * @param activity Current page
     * @param callback callback
     */
    @JvmStatic
    fun getImeVisible(activity: Activity,callback: WindowsKeyboardCallback? = null){
        ViewCompat.setOnApplyWindowInsetsListener(activity.window.decorView) { _, insets ->
            val imeVisible = insets.isVisible(WindowInsetsCompat.Type.ime())
            callback?.onKeyboardChanged(imeVisible)
            WindowInsetsCompat.CONSUMED
        }
    }

    /**
     * change the statusBar visibility
     * @param activity Current page
     * @param isHide whether to hide the statusBar
     */
    @JvmStatic
    fun immersiveStatusBar(activity: Activity,isHide: Boolean){
        WindowInsetsControllerCompat(activity.window,activity.window.decorView).let {
            if (isHide){
                it.hide(WindowInsetsCompat.Type.statusBars())
            }else{
                it.show(WindowInsetsCompat.Type.statusBars())
            }
        }
    }

    /**
     * change the systemBar visibility(systemBar contains navigationBar and statusBar)
     * @param activity Current page
     * @param isHide whether to hide the systemBar
     */
    @JvmStatic
    fun immersiveSystemBars(activity: Activity,isHide: Boolean){
        WindowInsetsControllerCompat(activity.window,activity.window.decorView).let {
            if (isHide){
                it.hide(WindowInsetsCompat.Type.systemBars())
            }else{
                it.show(WindowInsetsCompat.Type.systemBars())
            }
        }
    }

    /**
     * Fill the content to the status bar, keep the bottom system bar
     * @param activity Current page
     * @param view view  need to filling
     * @param statusBarColor statusBar background color,default is transparent
     */
    @JvmStatic
    fun setContentImmersion(view: View, activity: Activity,@ColorRes  statusBarColor: Int = android.R.color.transparent) {
        setStatusBarColor(activity,statusBarColor)
        WindowCompat.setDecorFitsSystemWindows(activity.window, false)
        ViewCompat.setOnApplyWindowInsetsListener(view) { bottom, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.navigationBars())
            bottom.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                bottomMargin = insets.bottom
            }
            WindowInsetsCompat.CONSUMED
        }
    }


    private fun getStatusBarHeight(resources: Resources): Int {
        var result = 0
        val resourceId: Int = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    /**
     * get statusBar height
     * @param activity Current page
     */
    @JvmStatic
    fun getStatusBarHeight(activity: Activity): Int {
        var statusHeight = 0
        ViewCompat.setOnApplyWindowInsetsListener(activity.window.decorView) { _, windowInsets ->
            statusHeight = windowInsets.getInsets(WindowInsetsCompat.Type.statusBars()).top
            WindowInsetsCompat.CONSUMED
        }
        if (statusHeight == 0) {
            statusHeight = getStatusBarHeight(activity.resources)
        }
        return statusHeight
    }

    /**
     * get navigationBar height
     * @param resources
     */
    @JvmStatic
    fun getNavigationBarHeight(resources: Resources): Int {
        val rid: Int = resources.getIdentifier("config_showNavigationBar", "bool", "android")
        return if (rid != 0) {
            val resourceId: Int =
                resources.getIdentifier("navigation_bar_height", "dimen", "android")
            resources.getDimensionPixelSize(resourceId)
        } else {
            0
        }
    }



}
abstract class WindowsKeyboardCallback{
    abstract fun onKeyboardChanged(show: Boolean)
}