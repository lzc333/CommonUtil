package com.yfve.common.util

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.NumberFormat

/**
 * 对数值进行转换：整数位数、小数位数、是否分组以及是否四舍五入
 * ps: 分组符号是 “,”
 */
object NumberFormatUtils {
    private val decimalFormat = NumberFormat.getInstance() as DecimalFormat

    /**
     * Format the value.
     *
     * @param value            原始值
     * @param isGrouping       是否分组
     * @param minIntegerDigits 整数部分中允许的最小位数 :0xx
     * @param fractionDigits   小数部分中允许的位数  xx.xx
     * @param isHalfUp         正确到四舍五入朝向最近的邻居
     * @return 格式化之后的值
     */
    fun format(
        value: Double,
        isGrouping: Boolean,
        minIntegerDigits: Int,
        fractionDigits: Int,
        isHalfUp: Boolean
    ): String {
        val temp = decimalFormat
        temp.isGroupingUsed = isGrouping
        temp.roundingMode = if (isHalfUp) RoundingMode.HALF_UP else RoundingMode.DOWN
        temp.minimumIntegerDigits = minIntegerDigits
        temp.minimumFractionDigits = fractionDigits
        temp.maximumFractionDigits = fractionDigits
        return temp.format(value)
    }

    /**
     * Format the value.
     *
     * @param value          原始值
     * @param fractionDigits 小数部分中允许的位数  xx.xx
     * @return 格式化之后的值
     */
    fun <T : Number> format(value: T, fractionDigits: Int): String {
        return format(number2Double(value), false, 1, fractionDigits, true)
    }

    /**
     * Format the value.
     *
     * @param value          原始值
     * @param fractionDigits 小数部分中允许的位数  xx.xx
     * @param isHalfUp         正确到四舍五入朝向最近的邻居
     * @return 格式化之后的值
     */
    fun <T : Number> format(value: T, fractionDigits: Int, isHalfUp: Boolean): String {
        return format(number2Double(value), false, 1, fractionDigits, isHalfUp)
    }

    /**
     * Format the value.
     *
     * @param value            原始值
     * @param minIntegerDigits 整数部分中允许的最小位数 :0xx
     * @param fractionDigits   小数部分中允许的位数  xx.xx
     * @param isHalfUp         正确到四舍五入朝向最近的邻居
     * @return 格式化之后的值
     */
    fun <T : Number> format(
        value: T,
        minIntegerDigits: Int,
        fractionDigits: Int,
        isHalfUp: Boolean
    ): String {
        return format(number2Double(value), false, minIntegerDigits, fractionDigits, isHalfUp)
    }

    private fun <T : Number> number2Double(value: T): Double {
        return BigDecimal(value.toString()).toDouble()
    }
}