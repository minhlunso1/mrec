package minhna.android.mercari.view.util

import android.util.DisplayMetrics
import android.content.Context
import android.graphics.Point
import android.view.WindowManager

/**
 * Created by Minh on 3/4/2018.
 */
object Utils {

    private val WIDTH_INDEX = 0
    private val HEIGHT_INDEX = 1
    fun getScreenSize(context: Context): IntArray {
        val widthHeight = IntArray(2)
        widthHeight[WIDTH_INDEX] = 0
        widthHeight[HEIGHT_INDEX] = 0

        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay

        val size = Point()
        display.getSize(size)
        widthHeight[WIDTH_INDEX] = size.x
        widthHeight[HEIGHT_INDEX] = size.y

        if (!isScreenSizeRetrieved(widthHeight)) {
            val metrics = DisplayMetrics()
            display.getMetrics(metrics)
            widthHeight[0] = metrics.widthPixels
            widthHeight[1] = metrics.heightPixels
        }

        //Use deprecated API when there is no option left
        if (!isScreenSizeRetrieved(widthHeight)) {
            widthHeight[0] = display.width
            widthHeight[1] = display.height
        }

        return widthHeight
    }

    private fun isScreenSizeRetrieved(widthHeight: IntArray): Boolean {
        return widthHeight[WIDTH_INDEX] != 0 && widthHeight[HEIGHT_INDEX] != 0
    }

}