package com.example.spinbottlegame

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.Button
import android.widget.ImageView
import androidx.core.graphics.rotationMatrix
import java.lang.Exception
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var bottle: ImageView
    private lateinit var player : MediaPlayer


    var rotate: Boolean = false
    var random: Random = Random()
    var end = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        bottle = findViewById(R.id.ivBottle)


        bottle.setOnClickListener {
            startAnimation()
        }

    }


        fun startAnimation() {
            if (!rotate) {
                var newDirection = random.nextInt(3600) + 360
                var pvX =  bottle . width /2
                var pvY = bottle.height / 2
                var animation: Animation = RotateAnimation(
                    end.toFloat(),
                    newDirection.toFloat(),
                    pvX.toFloat(),
                    pvY.toFloat()
                )
                animation.duration = 3000
                animation.fillAfter = true
                end = newDirection
                bottle.startAnimation(animation)

                animation.setAnimationListener(object : Animation.AnimationListener{
                    override fun onAnimationRepeat(animation: Animation?) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onAnimationEnd(animation: Animation?) {
                       player.stop()
                    }

                    override fun onAnimationStart(animation: Animation?) {
                        try {
                            val soundUri = Uri.parse("android.resource://com.example.spinbottlegame/" + R.raw.spinsound)
                            player = MediaPlayer.create(applicationContext,soundUri)
                            player.start()
                        } catch (e: Exception){
                            e.printStackTrace()
                        }
                    }
                })









            }
        }

    fun spnbtn(view: View) {
        startAnimation()
    }


}

