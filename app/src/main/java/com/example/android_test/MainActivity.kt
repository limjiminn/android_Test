package com.example.android_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //hell 텍스트를 "안녕"으로 변환
        val tv:TextView = findViewById(R.id.tv_hello)
        val btn:Button = findViewById(R.id.btn_kor)

         btn.setOnClickListener {
             tv.text = "안녕"
         }

    }
}