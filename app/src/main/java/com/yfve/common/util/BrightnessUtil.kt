package com.yfve.common.util

import android.app.Activity
import android.content.Context
import android.provider.Settings
import androidx.annotation.IntRange

/**
 * @function 屏幕亮度工具类
 * @Description
 */

object BrightnessUtil {

    /**
     * 是否开启了自动亮度
     */
    fun isAutoBrightness(context: Context): Boolean {
        return Settings.System.getInt(
            context.contentResolver,
            Settings.System.SCREEN_BRIGHTNESS_MODE
        ) == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC

    }

    /**
     * 设置是否开启自动亮度
     * @param enable : 为true时开启，false时关闭
     * @return 设置成功返回true
     */
    fun setAutoBrightness(context: Context, enable: Boolean) = Settings.System.putInt(
        context.contentResolver, Settings.System.SCREEN_BRIGHTNESS_MODE,
        if (enable) Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC else Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL
    )


    /**
     * 系统屏幕亮度，需要WRITE_SETTINGS权限，并在代码中申请系统设置权限
     * 范围为0~255
     */
    fun setBrightness(context: Context, @IntRange(from = 0, to = 255) brightness: Int) {
        if (isAutoBrightness(context)) {
            //如果当前是自动亮度，则关闭自动亮度
            setAutoBrightness(context, false)
        }
        val uri = Settings.System.getUriFor(Settings.System.SCREEN_BRIGHTNESS)
        Settings.System.putInt(
            context.contentResolver,
            Settings.System.SCREEN_BRIGHTNESS,
            brightness
        )
        context.contentResolver.notifyChange(uri, null)

    }

    fun getBrightness(context: Context): Int {
        return Settings.System.getInt(context.contentResolver, Settings.System.SCREEN_BRIGHTNESS)
    }


    /**
     * 当前窗口亮度
     * 范围为0~1.0,1.0时为最亮，-1为系统默认设置
     */
    var Activity.windowBrightness
        get() = window.attributes.screenBrightness
        set(brightness) {
            //小于0或大于1.0默认为系统亮度
            window.attributes = window.attributes.apply {
                screenBrightness = if (brightness > 1.0 || brightness < 0) -1.0F else brightness
            }
        }


}

