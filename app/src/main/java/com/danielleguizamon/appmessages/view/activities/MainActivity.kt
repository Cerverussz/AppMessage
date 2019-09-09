package com.danielleguizamon.appmessages.view.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.danielleguizamon.appmessages.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        while (true) {
//            try {
//                Thread.sleep(1000)
//            } catch (e: InterruptedException) {
//                e.printStackTrace()
//            }
//
//            sumTwoNumbers(50, 30)
//        }

    }

    private fun sumTwoNumbers(x: Int, y: Int) {
       // Log.d("--- Sum", (x + y).toString())
    }
}
