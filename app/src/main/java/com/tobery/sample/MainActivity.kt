package com.tobery.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tobery.systembarx.SystemBarXUtil

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SystemBarXUtil.fitsSystemWindows(this)
    }
}