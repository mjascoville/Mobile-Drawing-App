package com.example.drawingapp
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.drawingapp.DrawingView
import com.example.drawingapp.R

class MainActivity : AppCompatActivity() {
    private lateinit var drawingView: DrawingView
    private var currentTool = "Line"
    private var currentColor = Color.BLACK

    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawingView = findViewById(R.id.drawingView)

        val toolButton: Button = findViewById(R.id.toolButton)
        toolButton.setOnClickListener {
            currentTool = if (currentTool == "Line") "Circle" else "Line"
            drawingView.setTool(currentTool)
            toolButton.text = "Tool: $currentTool"
        }

        val colorButton: Button = findViewById(R.id.colorButton)
        colorButton.setOnClickListener {
            currentColor = if (currentColor == Color.BLACK) Color.RED else Color.BLACK
            drawingView.setColor(currentColor)
            colorButton.text = if (currentColor == Color.BLACK) "Color: Black" else "Color: Red"
        }


        drawingView.setTool(currentTool)
        drawingView.setColor(currentColor)
    }
}
