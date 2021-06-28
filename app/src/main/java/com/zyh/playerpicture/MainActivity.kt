package com.zyh.playerpicture

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    companion object{
        val AUTO_START = "auto_start"
        fun launch(context: Context, autoStart: Boolean, vararg flags: Int) {
            val startIntent = Intent(context, MainActivity::class.java)
            if (flags != null) {
                for (flag in flags) {
                    startIntent.addFlags(flag)
                }
            }
            startIntent.putExtra(AUTO_START, autoStart)
            context.startActivity(startIntent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}