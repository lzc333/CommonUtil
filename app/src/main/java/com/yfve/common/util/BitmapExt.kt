package com.yfve.common.util

import android.graphics.Bitmap
import android.graphics.Matrix
import kotlin.math.min

/**
 * 根据给定的宽和高进行拉伸
 *
 * @param newWidth  新图的宽
 * @param newHeight 新图的高
 * @return new Bitmap
 */
fun Bitmap.scaleBitmap(newWidth: Int, newHeight: Int): Bitmap {
    val height = this.height
    val width = this.width
    val scaleWidth = newWidth.toFloat() / width
    val scaleHeight = newHeight.toFloat() / height
    val matrix = Matrix()
    matrix.postScale(scaleWidth, scaleHeight) // 使用后乘
    val newBM = Bitmap.createBitmap(this, 0, 0, width, height, matrix, false)
    if (!this.isRecycled) {
        this.recycle()
    }
    return newBM
}

/**
 * 按比例缩放图片
 *
 * @param ratio  比例
 * @return 新的bitmap
 */
fun Bitmap.scaleBitmap(ratio: Float): Bitmap {
    val width = this.width
    val height = this.height
    val matrix = Matrix()
    matrix.preScale(ratio, ratio)
    val newBM = Bitmap.createBitmap(this, 0, 0, width, height, matrix, false)
    if (newBM == this) {
        return newBM
    }
    this.recycle()
    return newBM
}

/**
 * 裁剪
 * @return 裁剪后的图像
 */
fun Bitmap.cropBitmap(): Bitmap {
    val w = this.width // 得到图片的宽，高
    val h = this.height
    var cropWidth = min(w, h) // 裁切后所取的正方形区域边长
    cropWidth /= 2
    val cropHeight = (cropWidth / 1.2).toInt()
    return Bitmap.createBitmap(this, w / 3, 0, cropWidth, cropHeight, null, false)
}

/**
 * 选择变换
 * @param alpha  旋转角度，可正可负
 * @return 旋转后的图片
 */
fun Bitmap.rotateBitmap(alpha: Float): Bitmap {
    val width = this.width
    val height = this.height
    val matrix = Matrix()
    matrix.setRotate(alpha)
    // 围绕原地进行旋转
    val newBM = Bitmap.createBitmap(this, 0, 0, width, height, matrix, false)
    if (newBM == this) {
        return newBM
    }
    this.recycle()
    return newBM
}

/**
 * 偏移效果
 * @return 偏移后的bitmap
 */
fun Bitmap.skewBitmap(): Bitmap {
    val width = this.width
    val height = this.height
    val matrix = Matrix()
    matrix.postSkew(-0.6f, -0.3f)
    val newBM = Bitmap.createBitmap(this, 0, 0, width, height, matrix, false)
    if (newBM == this) {
        return newBM
    }
    this.recycle()
    return newBM
}