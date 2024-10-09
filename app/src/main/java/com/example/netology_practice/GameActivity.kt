package com.example.netology_practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.Window
import android.view.WindowManager
import android.widget.Chronometer
import android.window.OnBackInvokedDispatcher
import androidx.activity.OnBackPressedCallback

class GameActivity : AppCompatActivity() {
    private lateinit var chronometer: Chronometer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_game)

        val mouseSpeed = intent.getIntExtra("mouse_speed", 10)
        val mouseCount = intent.getIntExtra("mouse_count", 1)

        val gameView = findViewById<GameView>(R.id.gameview)
        gameView.setMouseProperties(mouseSpeed, mouseCount, application)

        chronometer = findViewById(R.id.chronometer)
        chronometer.base = SystemClock.elapsedRealtime()
        chronometer.start()

        val callback = object : OnBackPressedCallback(
            true
        ) {
            override fun handleOnBackPressed() {
                chronometer.stop()
                val elapsedMillis = SystemClock.elapsedRealtime() - chronometer.base
                val elapsedSeconds = (elapsedMillis / 1000).toInt()
                val gameView = findViewById<GameView>(R.id.gameview)
                gameView.SaveGameStats(elapsedSeconds)
                finish()
            }
        }
        onBackPressedDispatcher.addCallback(callback)
    }

//    override fun onStop() {
//        chronometer.stop()
//        val elapsedMillis = SystemClock.elapsedRealtime() - chronometer.base
//        val elapsedSeconds = (elapsedMillis / 1000).toInt()
//        val gameView = findViewById<GameView>(R.id.gameview)
//        gameView.SaveGameStats(elapsedSeconds)
//        super.onStop()
//    }
}