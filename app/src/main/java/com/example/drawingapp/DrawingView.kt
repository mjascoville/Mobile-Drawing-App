package com.example.drawingapp
import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.RequiresApi
import kotlin.math.sqrt

data class Line(val startX: Float, val startY: Float, val endX: Float, val endY: Float, val color: Int, val thickness: Float)
data class Circle(val centerX: Float, val centerY: Float, val radius: Float, val color: Int, val thickness: Float)

class DrawingView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {

    private val shapes = mutableListOf<Any>()
    private var currentTool: String = "Line"
    private var currentColor: Int = Color.BLACK
    private var startX: Float = 0f
    private var startY: Float = 0f
    private val paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (shape in shapes) {
            when (shape) {
                is Line -> drawLine(shape, canvas)
                is Circle -> drawCircle(shape, canvas)
            }
        }
    }

    private fun drawLine(line: Line, canvas: Canvas) {
        paint.color = line.color
        paint.strokeWidth = line.thickness
        canvas.drawLine(line.startX, line.startY, line.endX, line.endY, paint)
    }

    private fun drawCircle(circle: Circle, canvas: Canvas) {
        paint.color = circle.color
        paint.strokeWidth = circle.thickness
        canvas.drawCircle(circle.centerX, circle.centerY, circle.radius, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = event.x
                startY = event.y
            }
            MotionEvent.ACTION_UP -> {
                val endX = event.x
                val endY = event.y
                when (currentTool) {
                    "Line" -> {
                        val line = Line(startX, startY, endX, endY, currentColor, 10f)
                        shapes.add(line)
                    }
                    "Circle" -> {
                        val radius = sqrt((endX - startX) * (endX - startX) + (endY - startY) * (endY - startY))
                        val circle = Circle(startX, startY, radius, currentColor, 10f)
                        shapes.add(circle)
                    }
                }
                invalidate()
            }
        }
        return true
    }

    fun setTool(tool: String) {
        currentTool = tool
    }

    fun setColor(color: Int) {
        currentColor = color
    }

}
