package com.example.netology_practice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_SplashAPIGFG)
        setContentView(R.layout.activity_main)


        val mouse_count = findViewById<SeekBar>(R.id.mouse_count)
        mouse_count.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, p: Boolean) {

                val selectedValue = progress
                findViewById<TextView>(R.id.title_count).text = "Выберите количество мышей: $selectedValue"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        val mouse_speed = findViewById<SeekBar>(R.id.mouse_speed)
        mouse_speed.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, p: Boolean) {

                val selectedValue = progress
                findViewById<TextView>(R.id.title_speed).text = "Выберите скорость мышей: $selectedValue"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        findViewById<Button>(R.id.start_game_button).setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("mouse_speed", mouse_speed.progress)
            intent.putExtra("mouse_count", mouse_count.progress)
            startActivity(intent)

        }

        findViewById<Button>(R.id.stats_button).setOnClickListener {
            val intent = Intent(this, StatsActivity::class.java)
            startActivity(intent)
        }
    }
}