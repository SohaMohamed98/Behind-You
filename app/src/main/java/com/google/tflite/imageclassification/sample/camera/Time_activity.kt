package com.google.tflite.imageclassification.sample.camera

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Toast
import com.google.tflite.imageclassification.sample.R
import kotlinx.android.synthetic.main.activity_time_activity.*
import java.text.SimpleDateFormat
import java.util.*

class Time_activity : AppCompatActivity() {

    //text to speech
    lateinit var mTTS: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_activity)


        //TEXT TO SPEECH
        mTTS = TextToSpeech(applicationContext, TextToSpeech.OnInitListener { status ->
            if (status != TextToSpeech.ERROR) {
                mTTS.language = Locale.UK
            }
        })

        time()
        bt.setOnClickListener {
            time()
        }
    }

    private fun time(){

        val date = Calendar.getInstance().time
        val formatter = SimpleDateFormat.getTimeInstance() //or use getDateInstance()
        val formatedDate = formatter.format(date)

        txt_time.text = formatedDate
        textToSpeak()

    }


    private fun textToSpeak() {

        val toSpeak = txt_time.text.toString()
        if (toSpeak == "") {
            Toast.makeText(this, "Enter text", Toast.LENGTH_SHORT).show()
        } else {
            mTTS.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null)
        }
    }
}
