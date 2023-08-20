package com.example.workout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.FrameLayout
import android.widget.Toast
import com.example.workout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


private var viewbinding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewbinding?.root)


              viewbinding?.frameLayout?.setOnClickListener {
                  var intent = Intent(this,Exercise_activity::class.java)
                          startActivity(intent)
    }
}



    override fun onDestroy() {
        super.onDestroy()
        viewbinding=null
    }
}