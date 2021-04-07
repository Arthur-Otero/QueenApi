package com.example.queenapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import java.lang.RuntimeException

class DetailActivity : AppCompatActivity() {
    val image by lazy { findViewById<ImageView>(R.id.imageView) }
    val text by lazy { findViewById<TextView>(R.id.textView) }
    val winner by lazy { findViewById<LinearLayout>(R.id.winner) }
    val missCongeniality by lazy { findViewById<LinearLayout>(R.id.miss) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val extras = intent.extras
        if (extras != null) {
            try {
                Picasso.get().load(extras.getString("IMAGE")).into(image)
            }catch (error:RuntimeException){
                image.setImageResource(R.drawable.ic_launcher_foreground)
            }
            text.text = extras.getString("QUOTE")
            if (!extras.getBoolean("WINNER"))
                winner.visibility = GONE
            if (!extras.getBoolean("MISS"))
                missCongeniality.visibility = GONE
        }
    }
}