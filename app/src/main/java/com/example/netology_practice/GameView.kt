package com.example.netology_practice

import android.app.Application
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.ColorSpace.Rgb
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import kotlin.concurrent.thread

class GameView(context: Context, attributes: AttributeSet,) : SurfaceView(context, attributes), SurfaceHolder.Callback {
    private val thread: GameThread
    private val mice : MutableList<Mouse> = mutableListOf()
    private var mouseSpeed: Int = 10
    private lateinit var gameStatsDao: GameStatsDao
    private val gameStatsEntity: GameStatsEntity = GameStatsEntity()

    init {
        holder.addCallback(this)
        thread = GameThread(holder, this)
    }

    fun setMouseProperties(speed: Int, count: Int, application: Application) {
        gameStatsDao = GamesDatabase.getInstance(application).getGameStatsDao()
        mouseSpeed = speed

        for (i in 0 until count) {
            val mouse = Mouse(BitmapFactory.decodeResource(resources, R.drawable.mouse), speed)
            mice.add(mouse)
        }
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        thread.setRunning(true)
        thread.start()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        var retry = true
        while (retry) {
            try {
                thread.setRunning(false)
                thread.join()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            retry = false
        }
    }

    fun update() {
        for (mouse in mice)
            mouse.update()
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        canvas.drawColor(android.graphics.Color.WHITE)

        for (mouse in mice) {
            mouse.draw(canvas)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN){
            val touchX = event.x.toInt()
            val touchY = event.y.toInt()
            var istouch = false
            for (mouse in mice)
                if (mouse.touch(touchX, touchY)){
                    istouch = true
                    break
                }
            if (istouch)
            {
                gameStatsEntity.hit_count += 1
            }
            gameStatsEntity.count += 1
        }
        return true
    }

    fun SaveGameStats(seconds: Int){
        gameStatsEntity.time = seconds
        thread {
            gameStatsDao.insert(gameStatsEntity)
        }
    }
}