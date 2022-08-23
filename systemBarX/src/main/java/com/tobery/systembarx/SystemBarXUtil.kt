package com.tobery.systembarx

import android.app.Activity
import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

object SystemBarXUtil {

    /**
     * set the statusBar color
     * @param activity Current page
     * @param colorId  statusBar color
     */
    fun setStatusBarColor(activity: Activity,@ColorRes colorId: Int){
        activity.window.statusBarColor = ContextCompat.getColor(activity,colorId)
    }

    /**
     * change the statusBar icon and text color
     * @param activity Current page
     * @param darkText Whether make the statusBar icon and text to dark color
     */
    fun setStatusBarTextColor(activity: Activity,darkText: Boolean){
        WindowInsetsControllerCompat(activity.window,activity.window.decorView).isAppearanceLightStatusBars = darkText
    }

    /**
     * set page full screen
     * @param activity page that need to be changed
     */
    fun fitsSystemWindows(activity: Activity){
        WindowCompat.setDecorFitsSystemWindows(activity.window,false)
    }

    /**
     * change the foreground color of the navigation
     * @param activity
     * @param darkMode change the navigationBar foreground color model
     */
    fun setNavigationBarColor(activity: Activity,darkMode: Boolean){
        WindowInsetsControllerCompat(activity.window,activity.window.decorView).isAppearanceLightNavigationBars = darkMode
    }

    /**
     * change the navigationBar visibility
     * @param activity Current page
     * @param isHide whether to hide the navigationBar
     */
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
    fun getImeVisible(activity: Activity,callback: WindowsKeyboardCallback? = null){
        ViewCompat.setOnApplyWindowInsetsListener(activity.window.decorView) { _, insets ->
            val imeVisible = insets.isVisible(WindowInsetsCompat.Type.ime())
            callback?.onKeyboardChanged(imeVisible)
            insets
        }
    }

    /**
     * change the statusBar visibility
     * @param activity Current page
     * @param isHide whether to hide the statusBar
     */
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
     * get navigationBar height
     * @param view
     */
    fun getNavigationBarHeight(view: View): Int{
        val insets = ViewCompat.getRootWindowInsets(view)
        return insets?.getInsets(WindowInsetsCompat.Type.navigationBars())?.bottom ?: 0
    }



}
abstract class WindowsKeyboardCallback{
    abstract fun onKeyboardChanged(show: Boolean)
}