package com.yfve.common.util

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * @description:日期转换工具类
 * @author:  THUANG8
 * @date :   2021/9/21 13:52
 */
class DateUtils {

    /**
     * 英文简写如：2010
     */
    var FORMAT_Y = "yyyy"

    /**
     * 英文简写如：12:01
     */
    var FORMAT_HM = "HH:mm"

    /**
     * 英文简写如：1-12 12:01
     */
    var FORMAT_MDHM = "MM-dd HH:mm"

    /**
     * 英文简写（默认）如：2010-12-01
     */
    var FORMAT_YMD = "yyyy-MM-dd"

    /**
     * 英文全称  如：2010-12-01 23:15
     */
    var FORMAT_YMDHM = "yyyy-MM-dd HH:mm"

    /**
     * 英文全称  如：2010-12-01 23:15:06
     */
    var FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss"

    /**
     * 精确到毫秒的完整时间    如：yyyy-MM-dd HH:mm:ss.S
     */
    var FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S"

    /**
     * 精确到毫秒的完整时间    如：yyyy-MM-dd HH:mm:ss.S
     */
    var FORMAT_FULL_SN = "yyyyMMddHHmmssS"

    /**
     * 中文简写  如：2010年12月01日
     */
    var FORMAT_YMD_CN = "yyyy年MM月dd日"

    /**
     * 中文简写  如：2010年12月01日  12时
     */
    var FORMAT_YMDH_CN = "yyyy年MM月dd日 HH时"

    /**
     * 中文简写  如：2010年12月01日  12时12分
     */
    var FORMAT_YMDHM_CN = "yyyy年MM月dd日 HH时mm分"

    /**
     * 中文全称  如：2010年12月01日  23时15分06秒
     */
    var FORMAT_YMDHMS_CN = "yyyy年MM月dd日  HH时mm分ss秒"

    /**
     * 精确到毫秒的完整中文时间
     */
    var FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒"

    var calendar: Calendar? = null
    private val FORMAT = "yyyy-MM-dd HH:mm:ss"


    fun str2Date(str: String?): Date? {
        return str2Date(str, null)
    }


    @SuppressLint("SimpleDateFormat")
    fun str2Date(str: String?, format: String?): Date? {
        var format = format
        if (str == null || str.length == 0) {
            return null
        }
        if (format == null || format.length == 0) {
            format = FORMAT
        }
        var date: Date? = null
        try {
            val sdf = SimpleDateFormat(format)
            date = sdf.parse(str)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return date
    }


    fun str2Calendar(str: String?): Calendar? {
        return str2Calendar(str, null)
    }


    fun str2Calendar(str: String?, format: String?): Calendar? {
        val date: Date = str2Date(str, format) ?: return null
        val c: Calendar = Calendar.getInstance()
        c.time = date
        return c
    }


    fun date2Str(c: Calendar?): String? { // yyyy-MM-dd HH:mm:ss
        return date2Str(c, null)
    }


    fun date2Str(c: Calendar?, format: String?): String? {
        return if (c == null) {
            null
        } else date2Str(c.getTime(), format)
    }


    fun date2Str(d: Date?): String? { // yyyy-MM-dd HH:mm:ss
        return date2Str(d, null)
    }


    fun date2Str(d: Date?, format: String?): String? { // yyyy-MM-dd HH:mm:ss
        var format = format
        if (d == null) {
            return null
        }
        if (format == null || format.length == 0) {
            format = FORMAT
        }
        val sdf = SimpleDateFormat(format)
        return sdf.format(d)
    }


    fun getCurDateStr(): String? {
        val c: Calendar = Calendar.getInstance()
        c.time = Date()
        return c.get(Calendar.YEAR).toString() + "-" + (c.get(Calendar.MONTH) + 1) + "-" +
                c.get(Calendar.DAY_OF_MONTH) + "-" +
                c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) +
                ":" + c.get(Calendar.SECOND)
    }


    /**
     * 获得当前日期的字符串格式
     * @param format    格式化的类型
     * @return  返回格式化之后的事件
     */
    fun getCurDateStr(format: String?): String? {
        val c: Calendar = Calendar.getInstance()
        return date2Str(c, format)
    }


    /**
     *
     * @param time 当前的时间
     * @return  格式到秒
     */
    //
    @SuppressLint("SimpleDateFormat")
    fun getMillon(time: Long): String? {
        return SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(time)
    }


    /**
     *
     * @param time  当前的时间
     * @return  当前的天
     */
    @SuppressLint("SimpleDateFormat")
    fun getDay(time: Long): String? {
        return SimpleDateFormat("yyyy-MM-dd").format(time)
    }


    /**
     *
     * @param time 时间
     * @return 返回一个毫秒
     */
    // 格式到毫秒
    @SuppressLint("SimpleDateFormat")
    fun getSMillon(time: Long): String? {
        return SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS").format(time)
    }


    /**
     * 在日期上增加数个整月
     * @param date 日期
     * @param n 要增加的月数
     * @return   增加数个整月
     */
    fun addMonth(date: Date?, n: Int): Date? {
        val cal: Calendar = Calendar.getInstance()
        cal.time = date
        cal.add(Calendar.MONTH, n)
        return cal.getTime()
    }


    /**
     * 在日期上增加天数
     * @param date 日期
     * @param n 要增加的天数
     * @return   增加之后的天数
     */
    fun addDay(date: Date?, n: Int): Date? {
        val cal: Calendar = Calendar.getInstance()
        cal.time = date
        cal.add(Calendar.DATE, n)
        return cal.getTime()
    }


    /**
     * 获取距现在某一小时的时刻
     *
     * @param format 格式化时间的格式
     * @param h 距现在的小时 例如：h=-1为上一个小时，h=1为下一个小时
     * @return  获取距现在某一小时的时刻
     */
    @SuppressLint("SimpleDateFormat")
    fun getNextHour(format: String?, h: Int): String? {
        val sdf = SimpleDateFormat(format)
        val date = Date()
        date.setTime(date.getTime() + h * 60 * 60 * 1000)
        return sdf.format(date)
    }


    /**
     * 获取时间戳
     * @return 获取时间戳
     */
    @SuppressLint("SimpleDateFormat")
    fun getTimeString(): String? {
        val df = SimpleDateFormat(FORMAT_FULL)
        val calendar: Calendar = Calendar.getInstance()
        return df.format(calendar.getTime())
    }


    /**
     * 功能描述：返回月
     *
     * @param date Date 日期
     * @return 返回月份
     */
    fun getMonth(date: Date?): Int {
        calendar = Calendar.getInstance()
        calendar!!.time = date
        return calendar!!.get(Calendar.MONTH) + 1
    }


    /**
     * 功能描述：返回日
     *
     * @param date Date 日期
     * @return 返回日份
     */
    fun getDay(date: Date?): Int {
        calendar = Calendar.getInstance()
        calendar!!.time = date
        return calendar!!.get(Calendar.DAY_OF_MONTH)
    }


    /**
     * 功能描述：返回小
     *
     * @param date 日期
     * @return 返回小时
     */
    fun getHour(date: Date?): Int {
        calendar = Calendar.getInstance()
        calendar!!.time = date
        return calendar!!.get(Calendar.HOUR_OF_DAY)
    }


    /**
     * 功能描述：返回分
     *
     * @param date 日期
     * @return 返回分钟
     */
    fun getMinute(date: Date?): Int {
        calendar = Calendar.getInstance()
        calendar!!.time = date
        return calendar!!.get(Calendar.MINUTE)
    }


    /**
     * 获得默认的 date pattern
     * @return  默认的格式
     */
    fun getDatePattern(): String? {
        return FORMAT_YMDHMS
    }


    /**
     * 返回秒钟
     *
     * @param date Date 日期
     * @return 返回秒钟
     */
    fun getSecond(date: Date?): Int {
        calendar = Calendar.getInstance()
        calendar!!.time = date
        return calendar!!.get(Calendar.SECOND)
    }


    /**
     * 使用预设格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @return 提取字符串的日期
     */
    fun parse(strDate: String?): Date? {
        return parse(strDate, getDatePattern())
    }


    /**
     * 功能描述：返回毫
     *
     * @param date 日期
     * @return 返回毫
     */
    fun getMillis(date: Date?): Long {
        calendar = Calendar.getInstance()
        calendar!!.time = date
        return calendar!!.getTimeInMillis()
    }


    /**
     * 按默认格式的字符串距离今天的天数
     *
     * @param date 日期字符串
     * @return 按默认格式的字符串距离今天的天数
     */
    fun countDays(date: String?): Int {
        val t: Long = Calendar.getInstance().getTime().getTime()
        val c: Calendar = Calendar.getInstance()
        c.setTime(parse(date))
        val t1: Long = c.getTime().getTime()
        return (t / 1000 - t1 / 1000).toInt() / 3600 / 24
    }


    /**
     * 使用用户格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @param pattern 日期格式
     * @return  提取字符串日期
     */
    fun parse(strDate: String?, pattern: String?): Date? {
        val df = SimpleDateFormat(pattern)
        return try {
            df.parse(strDate)
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }
    }


    /**
     * 按用户格式字符串距离今天的天数
     *
     * @param date 日期字符串
     * @param format 日期格式
     * @return  按用户格式字符串距离今天的天数
     */
    fun countDays(date: String?, format: String?): Int {
        val t: Long = Calendar.getInstance().getTime().getTime()
        val c: Calendar = Calendar.getInstance()
        c.setTime(parse(date, format))
        val t1: Long = c.getTime().getTime()
        return (t / 1000 - t1 / 1000).toInt() / 3600 / 24
    }
}