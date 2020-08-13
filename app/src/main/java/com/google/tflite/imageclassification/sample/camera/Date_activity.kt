package com.google.tflite.imageclassification.sample.camera

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Toast
import com.google.tflite.imageclassification.sample.R
import kotlinx.android.synthetic.main.activity_date_activity.*
import java.text.SimpleDateFormat
import java.util.*

class Date_activity : AppCompatActivity() {

    //text to speech
    lateinit var mTTS: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date_activity)


        //TEXT TO SPEECH
        mTTS = TextToSpeech(applicationContext, TextToSpeech.OnInitListener { status ->
            if (status != TextToSpeech.ERROR) {
                mTTS.language = Locale.UK
            }
        })


        date()

        bd.setOnClickListener {
            date()
        }



    }

    private fun date(){
        val date = Calendar.getInstance().time
        val formatter = SimpleDateFormat.getDateInstance() //or use getDateInstance()
        val formatedDate = formatter.format(date)


        txt_date.text = formatedDate
        textToSpeak()
    }

    private fun textToSpeak() {

        val toSpeak = txt_date.text.toString()
        if (toSpeak == "") {
            Toast.makeText(this, "Enter text", Toast.LENGTH_SHORT).show()
        } else {
            mTTS.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null)
        }
    }
}
