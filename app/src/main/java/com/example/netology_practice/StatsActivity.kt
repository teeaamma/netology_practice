package com.example.netology_practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlin.concurrent.thread

class StatsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_SplashAPIGFG)
        setContentView(R.layout.activity_stats)
        val gameStatsDao = GamesDatabase.getInstance(application).getGameStatsDao()
        var stats_list = mutableListOf<GameStatsEntity>()
        val adapter = StatsAdapter(stats_list)
        val list = findViewById<RecyclerView>(R.id.stats_list)
        list.adapter = adapter

        thread{
            val loadedStats = gameStatsDao.getLastGames()
            stats_list.clear()
            stats_list.addAll(loadedStats)
            runOnUiThread {
                adapter.notifyDataSetChanged()
            }
        }

        findViewById<Button>(R.id.return_button).setOnClickListener {
            finish()
        }
    }
}