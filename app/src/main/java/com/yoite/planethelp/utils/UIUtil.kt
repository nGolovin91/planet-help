package com.yoite.planethelp.utils

import android.content.Context
import android.util.TypedValue
import android.view.View
import android.view.ViewTreeObserver
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.WindowManager
import android.view.View.MeasureSpec






fun onRenderFinished(view: View, action: Runnable) {
    view.viewTreeObserver.addOnGlobalLayoutListener(
        object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                view.viewTreeObserver.removeOnGlobalLayoutListener(this)

                action.run()
            }
        }
    )
}

/**
 * Преобразовывает значение из DP в пиксели.
 *
 * @param dp значение в dp
 * @param context the context
 * @return значение в px
 */
fun dpToPx(dp: Float, context: Context): Int {
    return dpToPxFloat(dp, context).toInt()
}

/**
 * Преобразовывает значение из DP в пиксели без округления.
 *
 * @param dp значение в dp
 * @param context the context
 * @return значение в px
 */
fun dpToPxFloat(dp: Float, context: Context): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics)
}

fun loadBitmapFromView(v: View): Bitmap? {

    if (v.measuredHeight <= 0) {
        val specWidth = MeasureSpec.makeMeasureSpec(0 /* any */, MeasureSpec.UNSPECIFIED)
        v.measure(specWidth, specWidth)
        val questionWidth = v.measuredWidth

//        v.measure(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        val b = Bitmap.createBitmap(v.measuredWidth, v.measuredHeight, Bitmap.Config.ARGB_8888)
        val c = Canvas(b)
        v.layout(0, 0, v.measuredWidth, v.measuredHeight)
        v.draw(c)
        return b
    }
    return null
}