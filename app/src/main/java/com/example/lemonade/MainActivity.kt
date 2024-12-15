package com.example.lemonade

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.cardview.widget.CardView
import android.graphics.Typeface
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    private var currentStep = 1
    private var squeezeCount = 0
    private var requiredSqueezes = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(Color.WHITE)
        }

        // Add top app bar
        val topBar = LinearLayout(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            setBackgroundColor(Color.parseColor("#F9DA4A"))
            elevation = 4f
            setPadding(16, 16, 16, 16)
            gravity = Gravity.CENTER  // Centre le contenu du topBar
        }

        val titleText = TextView(this).apply {
            text = "Lemonade"
            textSize = 20f
            setTextColor(Color.BLACK)
            typeface = Typeface.DEFAULT_BOLD  // Met le texte en gras
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }

        topBar.addView(titleText)
        layout.addView(topBar)

        // Content container
        val contentLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }

        val instructionText = TextView(this).apply {
            text = getString(R.string.instruction_select_lemon)
            textSize = 18f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER
            setPadding(0, 0, 0, 16)
        }

        val cardView = CardView(this).apply {
            radius = 24f
            cardElevation = 8f
            setCardBackgroundColor(Color.parseColor("#DFF5D6"))
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(16, 16, 16, 16)
            }
        }

        val imageView = ImageView(this).apply {
            setImageResource(R.drawable.lemon_tree)
            contentDescription = getString(R.string.content_desc_lemon_tree)
            layoutParams = ViewGroup.LayoutParams(
                500,
                500
            )
        }

        cardView.addView(imageView)
        contentLayout.addView(instructionText)
        contentLayout.addView(cardView)
        layout.addView(contentLayout)

        setContentView(layout)

        imageView.setOnClickListener {
            when (currentStep) {
                1 -> {
                    currentStep = 2
                    requiredSqueezes = Random.nextInt(2, 5)
                    squeezeCount = 0
                    instructionText.text = getString(R.string.instruction_squeeze_lemon)
                    imageView.setImageResource(R.drawable.lemon_squeeze)
                    imageView.contentDescription = getString(R.string.content_desc_lemon)
                }
                2 -> {
                    squeezeCount++
                    if (squeezeCount >= requiredSqueezes) {
                        currentStep = 3
                        instructionText.text = getString(R.string.instruction_drink_lemonade)
                        imageView.setImageResource(R.drawable.lemon_drink)
                        imageView.contentDescription = getString(R.string.content_desc_lemonade)
                    }
                }
                3 -> {
                    currentStep = 4
                    instructionText.text = getString(R.string.instruction_restart)
                    imageView.setImageResource(R.drawable.lemon_restart)
                    imageView.contentDescription = getString(R.string.content_desc_empty_glass)
                }
                4 -> {
                    currentStep = 1
                    instructionText.text = getString(R.string.instruction_select_lemon)
                    imageView.setImageResource(R.drawable.lemon_tree)
                    imageView.contentDescription = getString(R.string.content_desc_lemon_tree)
                }
                else -> {
                    Toast.makeText(this, getString(R.string.error_unknown_step), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}