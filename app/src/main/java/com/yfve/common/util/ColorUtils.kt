package com.yfve.common.util

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.FloatRange
import androidx.annotation.IntRange

/**
 * 颜色转换器：color-int 转换为字符串 、 字符串转换为 color-int
 */
object ColorUtils {
    /**
     * 设置颜色透明度
     * @param color 原始颜色
     * @param alpha 透明的 取值 [0,255]
     */
    fun setAlphaComponent(
        @ColorInt color: Int,
        @IntRange(from = 0x0, to = 0xFF) alpha: Int
    ): Int {
        return color and 0x00ffffff or (alpha shl 24)
    }

    /**
     * 设置颜色透明度
     * @param color 原始颜色
     * @param alpha 透明的 取值 [0.0,1.0]
     */
    fun setAlphaComponent(
        @ColorInt color: Int,
        @FloatRange(from = 0.0, to = 1.0) alpha: Float
    ): Int {
        return color and 0x00ffffff or ((alpha * 255.0f + 0.5f).toInt() shl 24)
    }

    /**
     * Color-string to color-int.
     *
     * Supported formats are:
     *
     *
     *  * `#RRGGBB`
     *  * `#AARRGGBB`
     *
     * The following names are also accepted: `red`, `blue`,
     * `green`, `black`, `white`, `gray`,
     * `cyan`, `magenta`, `yellow`, `lightgray`,
     * `darkgray`, `grey`, `lightgrey`, `darkgrey`,
     * `aqua`, `fuchsia`, `lime`, `maroon`,
     * `navy`, `olive`, `purple`, `silver`,
     * and `teal`.
     *
     * @param colorString 颜色字符串
     * @return 转换后的值
     * @throws IllegalArgumentException 不支持格式
     */
    fun string2Int(colorString: String): Int {
        return Color.parseColor(colorString)
    }

    /**
     * color 转换为字符串
     *
     * @param colorInt color
     * @return rgb字符串
     */
    fun int2RgbString(@ColorInt colorInt: Int): String {
        var temp = colorInt
        temp = temp and 0x00ffffff
        var color = Integer.toHexString(temp)
        while (color.length < 6) {
            color = "0$color"
        }
        return "#$color"
    }

    /**
     * color 转换为字符串
     *
     * @param colorInt color
     * @return Argb字符串
     */
    fun int2ArgbString(@ColorInt colorInt: Int): String {
        var color = Integer.toHexString(colorInt)
        while (color.length < 6) {
            color = "0$color"
        }
        while (color.length < 8) {
            color = "f$color"
        }
        return "#$color"
    }
}