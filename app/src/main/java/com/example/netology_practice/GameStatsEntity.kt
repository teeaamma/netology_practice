package com.example.netology_practice

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "GameStats")
data class GameStatsEntity(
    @PrimaryKey(autoGenerate = true) var game_id : Int = 0,
    var hit_count : Int = 0,
    var count: Int = 0,
    var time: Int = 0,
)
