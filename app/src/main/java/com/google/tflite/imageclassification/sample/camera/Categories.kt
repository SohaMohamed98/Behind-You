package com.google.tflite.imageclassification.sample.camera

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.Toast
import com.google.tflite.imageclassification.sample.R
import kotlinx.android.synthetic.main.activity_categories.*
import kotlinx.android.synthetic.main.activity_categories.img_wave
import kotlinx.android.synthetic.main.activity_categories.img_wave2
import kotlinx.android.synthetic.main.activity_categories.textTv
import java.util.*

class Categories : AppCompatActivity() {

    //Voice
    private val Request_code_speech_input = 100

    //text to speech
    lateinit var mTTS: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        textTv.visibility = View.GONE

        img_wave.animate().scaleXBy(.4f).scaleYBy(.4f).duration=6000
        img_wave2.animate().scaleXBy(-.4f).scaleYBy(-.4f).duration=8000


        //TEXT TO SPEECH
        mTTS = TextToSpeech(applicationContext, TextToSpeech.OnInitListener { status ->
            if (status != TextToSpeech.ERROR) {
                mTTS.language = Locale.UK
            }
        })

        speak()

        btn_talk3.setOnClickListener {

            speak()
        }


        btn_internet.setOnClickListener{

            internet()
        }

        btn_basics.setOnClickListener {
            basics()
        }

        btn_sounds.setOnClickListener {
            sounds()
        }

    }

    //Speak To Text
    private fun speak() {
        val mIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        mIntent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        mIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        mIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hi Speak Something")

        try {
            startActivityForResult(mIntent, Request_code_speech_input)

        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    //Text to Speak
    private fun textToSpeak() {

        val toSpeak = textTv.text.toString()
        if (toSpeak == "") {
            Toast.makeText(this, "Enter text", Toast.LENGTH_SHORT).show()
        } else {

            Toast.makeText(this, toSpeak, Toast.LENGTH_SHORT).show()
            mTTS.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null)
        }
    }


    private fun internet(){
        var i= Intent(this,Internet::class.java)
        startActivity(i)
    }

    private fun basics(){
        var i= Intent(this, Basics::class.java)
        startActivity(i)
    }

    private fun sounds(){
        var i= Intent(this, Sounds::class.java)
        startActivity(i)
    }



    //sound
    private fun quran(){

        val launchIntent =
                packageManager.getLaunchIntentForPackage("com.rdi2.habeeba.tamkeen")
        if (launchIntent != null) {
            startActivity(launchIntent)
        } else {
            var i = Intent(Intent.ACTION_VIEW)
            i.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.rdi2.habeeba.tamkeen"))
            startActivity(i)
        }
    }

    private fun opReader() {
        val launchIntent =
                packageManager.getLaunchIntentForPackage("com.hyperionics.avar")
        if (launchIntent != null) {
            startActivity(launchIntent)
        } else {
            var i = Intent(Intent.ACTION_VIEW)
            i.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.hyperionics.avar"))
            startActivity(i)
        }
    }

    private fun youtube(){
        var intent = Intent(this, Web_activity::class.java)
        intent.putExtra("value","youtube")
        startActivity(intent)
    }

    //Internet
    private fun map(){
        var i2= Intent(this, Maps_activity::class.java)
        startActivity(i2)
    }

    private fun googleAssistant(){

        var i = Intent(Intent.ACTION_VOICE_COMMAND).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(i)
    }

    private fun chrome(){
        var intent = Intent(this, Web_activity::class.java)
        intent.putExtra("value","google")
        startActivity(intent)

    }

    private fun gmail(){

        val launchIntent =
                packageManager.getLaunchIntentForPackage("com.google.android.gm")
        if (launchIntent != null) {
            startActivity(launchIntent)
        } else {

            var i = Intent(Intent.ACTION_VIEW)
            i.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.gm"))
            startActivity(i)
        }
    }

    //Basics
    private fun opCamera(){

        var i= Intent(this, CameraActivity::class.java)
        startActivity(i)
    }

    private fun tensor(){

        val launchIntent =
                packageManager.getLaunchIntentForPackage("org.rdi3.demo")
        if (launchIntent != null) {
            startActivity(launchIntent)
        } else {

            var i = Intent(Intent.ACTION_VIEW)
            i.setData(Uri.parse("https://play.google.com/store/apps/details?id=org.rdi3.demo"))
            startActivity(i)
        }
    }

    private fun time(){

        var i= Intent(this, Time_activity::class.java)
        startActivity(i)
    }

    private fun date(){

        var i= Intent(this, Date_activity::class.java)
        startActivity(i)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {

            Request_code_speech_input -> {

                if (resultCode == Activity.RESULT_OK && data != null) {

                    val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    textTv.text = result[0]
                    textToSpeak()

                    if(textTv.text == "Internet" || textTv.text == "internet"){
                        internet()

                    }else if(textTv.text == "Basics" || textTv.text == "basics" ||
                            textTv.text == "Basic" || textTv.text == "basic"){
                        basics()

                    }else if(textTv.text == "Sounds" || textTv.text == "sounds" ||
                            textTv.text == "Sound" || textTv.text == "sound"){
                        sounds()

                    }else if(textTv.text == "quran" || textTv.text == "quraan" ||
                            textTv.text == "Quran" || textTv.text == "Quraan"){

                        quran()
                    }else if(textTv.text == "youtube" || textTv.text == "Youtube" ||
                            textTv.text == "youTube" || textTv.text == "YouTube"){

                        youtube()
                    }else if(textTv.text == "Voice" || textTv.text == "voice" ||
                            textTv.text == "Reader" || textTv.text == "reader"){

                        opReader()
                    } else if (textTv.text == "Camera" || textTv.text == "camera") {

                       opCamera()

                    }else if(textTv.text == "Color" || textTv.text == "color" ||
                            textTv.text == "money" || textTv.text == "Money" ||
                            textTv.text == "tensor" || textTv.text == "Tensor"){
                        tensor()

                    } else if(textTv.text == "Time" || textTv.text == "time" ){

                        time()
                    }else if(textTv.text == "Date" || textTv.text == "date"){
                        date()
                    } else if (textTv.text == "Maps" || textTv.text == "Map" ||
                            textTv.text == "map" || textTv.text == "maps") {
                        map()

                    } else if (textTv.text == "chrome" || textTv.text == "Chrome"||
                            textTv.text == "google chrome" || textTv.text == "Google chrome") {

                        chrome()

                    }else if(textTv.text == "Gmail" || textTv.text == "gmail"||
                            textTv.text == "mail" || textTv.text == "Mail"){
                        gmail()

                    } else {

                        googleAssistant()
                    }
                }
            }
        }
    }
}
