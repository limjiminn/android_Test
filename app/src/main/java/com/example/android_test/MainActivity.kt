package com.example.android_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.lang.Math.abs
import java.util.*

class MainActivity : AppCompatActivity() {
    //메인함수
    fun main(){
        //xml 파일의 레이아웃을 불러와라
        setContentView(R.layout.activity_main)



    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        main()

        //1.hell 텍스트를 "안녕"으로 변환
        //2.시간 표시 로직 적용하기
        var sec : Int = 0
        var isRunning = false
        val tv:TextView = findViewById(R.id.tv_random)
        val tv_t:TextView = findViewById(R.id.tv_timer)
        val tv_p:TextView = findViewById(R.id.tv_point)
        val btn:Button = findViewById(R.id.btn_main)
        //널러블? //timer는 변수가 아닌 함수
        var timerTask: Timer? = null
        //3.목표시간 랜덤으로 정하기
            //랜덤함수 사용하기
            val random_box = Random()
//            val num = random_box.nextInt(11) //0부터 10까지
            val num = random_box.nextInt(1001)
                        //소수로 만들어서 소수점 2자리 까지 랜덤
            tv.text = ((num.toFloat())/100).toString()


         btn.setOnClickListener {
             isRunning = !isRunning
             //러닝이 true면 돌고 아니면 스탑
             if(isRunning == true){
                 //1000을 넣어야 1초마다 함수가 돌아간다
                 timerTask = kotlin.concurrent.timer(period = 10){
                     sec++
                     //실시간으로 화면이 바뀌는것 처리하려면 사용
                     runOnUiThread {
                         //int를 string 으로 변환
                                //소수점 2까지 나오게하기
                         tv_t.text = (sec.toFloat()/100).toString()
                     }
                 }
             }else {
                 //타이머 멈추기
                 timerTask?.cancel()
                        //abs : 절댓값
                 val point = abs(sec-num).toFloat()/100
                 tv_p.text = point.toString()
             }

        }

    }
}