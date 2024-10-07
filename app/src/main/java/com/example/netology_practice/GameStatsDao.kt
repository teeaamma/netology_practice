package com.example.netology_practice

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GameStatsDao {
    @Insert
    fun insert(game: GameStatsEntity)

    @Query("SELECT * FROM GameStats ORDER BY game_id DESC LIMIT 10")
    fun getLastGames(): List<GameStatsEntity>
}