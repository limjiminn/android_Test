package com.example.android_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //1.hell 텍스트를 "안녕"으로 변환
        //2.시간 표시 로직 적용하기
        var sec : Int = 0
        var isRunning = false
        val tv:TextView = findViewById(R.id.tv_hello)
        val btn:Button = findViewById(R.id.btn_kor)
        //널러블? //timer는 변수가 아닌 함수
        var timerTask: Timer? = null

         btn.setOnClickListener {
             isRunning = !isRunning
             //러닝이 true면 돌고 아니면 스탑
             if(isRunning == true){
                 //1000을 넣어야 1초마다 함수가 돌아간다

                 timerTask = kotlin.concurrent.timer(period = 1000){
                     sec++
                     //실시간으로 화면이 바뀌는것 처리하려면 사용
                     runOnUiThread {
                         //int를 string 으로 변환
                         tv.text = sec.toString()
                     }

                 }
             }else {
                 //타이머 멈추기
                 timerTask?.cancel()
             }





        }

    }
}