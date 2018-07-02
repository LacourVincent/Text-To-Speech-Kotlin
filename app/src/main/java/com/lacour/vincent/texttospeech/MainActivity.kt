package com.lacour.vincent.texttospeech

import android.content.Context
import android.media.AudioManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.KeyEvent
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var doubleBackToExitPressedOnce: Boolean? = false
    private var audio: AudioManager? = null
    private var tts_english: TTS? = null
    private var tts_french: TTS? = null
    private var tts_german: TTS? = null
    private var tts_spanish: TTS? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        audio = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        tts_english = TTS(this, "en", "GB")
        tts_french = TTS(this, "fr", "FR")
        tts_spanish = TTS(this, "es", "ES")
        tts_german = TTS(this, "de", "DE")

        button_english.setOnClickListener{
            tts_english!!.speak(textToSay.text.toString())
        }
        button_french.setOnClickListener{
            tts_french!!.speak(textToSay.text.toString())
        }
        button_spanish.setOnClickListener{
            tts_spanish!!.speak(textToSay.text.toString())
        }
        button_german.setOnClickListener{
            tts_german!!.speak(textToSay.text.toString())
        }
    }

    public override fun onDestroy() {
        tts_english!!.destroy()
        tts_french!!.destroy()
        tts_spanish!!.destroy()
        tts_german!!.destroy()
        super.onDestroy()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        //replaces the default 'Back' button action
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (doubleBackToExitPressedOnce!!) {
                super.onBackPressed()
                return true
            }
            this.doubleBackToExitPressedOnce = true
            Toast.makeText(this, getString(R.string.doubleBackToExit), Toast.LENGTH_SHORT).show()
            Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
        }
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            audio!!.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                    AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI)
        }
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            audio!!.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                    AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI)
        }
        return true
    }


}
