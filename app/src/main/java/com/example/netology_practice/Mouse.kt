package com.example.netology_practice

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import kotlin.random.Random

class Mouse(var image: Bitmap,private var speed: Int) {
    var x: Int = 0
    var y: Int = 0
    var w: Int = 0
    var h: Int = 0

    private val screenWidth = Resources.getSystem().displayMetrics.widthPixels
    private val screenHeight = Resources.getSystem().displayMetrics.heightPixels
    private var targetX: Int = 0
    private var targetY: Int = 0
    private var rotationAngle: Float = 0f

    init {
        w = image.width
        h = image.height

        x = Random.nextInt(w, screenWidth - w)
        y = Random.nextInt(h, screenHeight - h)
        generateNewTarget()
    }

    fun draw(canvas: Canvas) {
        val matrix = Matrix()

        matrix.postTranslate((-w / 2).toFloat(), (-h / 2).toFloat())
        matrix.postRotate(rotationAngle)
        matrix.postTranslate((x + w / 2).toFloat(), (y + h / 2).toFloat())

        canvas.drawBitmap(image, matrix, null)
    }

    fun update() {
        if (Math.abs(x - targetX) < speed && Math.abs(y - targetY) < speed) {
            generateNewTarget()
        } else {
            moveTowardsTarget()
        }
    }

    private fun moveTowardsTarget() {

        val deltaX = (targetX - x).toFloat()
        val deltaY = (targetY - y).toFloat()

        val distance = Math.sqrt((deltaX * deltaX + deltaY * deltaY).toDouble()).toFloat()

        val xMove = (deltaX / distance * speed).toInt()
        val yMove = (deltaY / distance * speed).toInt()

        x += xMove
        y += yMove

        rotationAngle = calculateRotationAngle(deltaX, deltaY)
    }

    private fun generateNewTarget() {
        targetX = Random.nextInt(0, screenWidth - w)
        targetY = Random.nextInt(0, screenHeight - h)
    }

    private fun calculateRotationAngle(deltaX: Float, deltaY: Float): Float {
        return Math.toDegrees(Math.atan2(deltaY.toDouble(), deltaX.toDouble())).toFloat() - 90
    }

    fun touch(touchX: Int, touchY: Int): Boolean {
        return touchX >= x && touchX <= (x + w) && touchY >= y && touchY <= (y + h)
    }
}