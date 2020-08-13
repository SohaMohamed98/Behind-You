package com.google.tflite.imageclassification.sample.camera

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.tflite.imageclassification.sample.R
import kotlinx.android.synthetic.main.activity_front_activity.*

class Front_activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_front_activity)

        btn_talk.setOnClickListener {
            var i = Intent(this, Categories::class.java)
            startActivity(i)
        }

    }
}
