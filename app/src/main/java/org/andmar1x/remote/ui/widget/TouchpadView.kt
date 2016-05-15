package org.andmar1x.remote.ui.widget

import android.annotation.TargetApi
import android.content.Context
import android.graphics.*
import android.os.Build
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import org.andmar1x.remote.R

class TouchpadView :
        View,
        GestureDetector.OnGestureListener {

    private var gestureDetector: GestureDetector? = null

    private val borderPaint: Paint = Paint()
    private var borderOffsetX = 0.0f
    private var borderOffsetY = 0.0f
    private var borderCorner = 0.0f

    private val backgroundPaint: Paint = Paint()
    private var backgroundIcon: Bitmap? = null
    private val backgroundMatrix = Matrix()

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr) {
        init(context, attrs, defStyleAttr)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(context, attrs, defStyleAttr)
    }

    protected fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        gestureDetector = GestureDetector(context, this)

        borderPaint.color = ContextCompat.getColor(context, R.color.divider_dark);
        borderPaint.strokeWidth = context.resources.getDimension(R.dimen.touchpad_border_stroke_width)
        borderPaint.style = Paint.Style.STROKE

        borderOffsetX = context.resources.getDimension(R.dimen.touchpad_border_offset_x)
        borderOffsetY = context.resources.getDimension(R.dimen.touchpad_border_offset_y)
        borderCorner = context.resources.getDimension(R.dimen.touchpad_border_corner)

        backgroundPaint.colorFilter = PorterDuffColorFilter(ContextCompat.getColor(context, R.color.divider_dark),
                PorterDuff.Mode.SRC_IN)

        backgroundIcon = BitmapFactory.decodeResource(context.resources, R.drawable.ic_touch_app_white)
    }

    override fun onDraw(canvas: Canvas?) {
        if (canvas == null) {
            return
        }

        val halfLineWidth = borderPaint.strokeWidth * 0.5f
        val left = halfLineWidth
        val top = halfLineWidth
        val right = canvas.width - halfLineWidth
        val bottom = canvas.height - halfLineWidth

        canvas.drawRoundRect(RectF(left, top, right, bottom), borderCorner, borderCorner, borderPaint)

        if (backgroundIcon == null) {
            return
        }

        backgroundMatrix.setTranslate((canvas.width - backgroundIcon!!.width) * 0.5f,
                (canvas.height - backgroundIcon!!.height) * 0.5f)

        canvas.drawBitmap(backgroundIcon, backgroundMatrix, backgroundPaint)
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        throw UnsupportedOperationException()
    }

    override fun onDown(e: MotionEvent?): Boolean {
        throw UnsupportedOperationException()
    }

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
        throw UnsupportedOperationException()
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
        throw UnsupportedOperationException()
    }

    override fun onShowPress(e: MotionEvent?) {
        throw UnsupportedOperationException()
    }

    override fun onLongPress(e: MotionEvent?) {
        throw UnsupportedOperationException()
    }
}
