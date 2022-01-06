package com.example.android_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.lang.Math.abs
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    var p_num = 3
    var k = 1
    val point_list = mutableListOf<Float>()
    //메인함수
    fun main(){
        //xml 파일의 레이아웃을 불러와라
        setContentView(R.layout.activity_main)

        //1.hell 텍스트를 "안녕"으로 변환
        //2.시간 표시 로직 적용하기
        //3.목표시간 랜덤으로 정하기
        var timerTask: Timer? = null  //널러블? //timer는 변수가 아닌 함수
        var stage = 1
        var sec : Int = 0
        val tv:TextView = findViewById(R.id.tv_random)
        val tv_t:TextView = findViewById(R.id.tv_timer)
        val tv_p:TextView = findViewById(R.id.tv_point)
        val tv_people:TextView = findViewById(R.id.tv_people)
        val btn:Button = findViewById(R.id.btn_main)
        val random_box = Random()  //랜덤함수 사용하기
        val num = random_box.nextInt(1001) // 0부터 1000까지

        tv.text = (num.toFloat()/100).toString()
        btn.text = "시작"
        tv_people.text ="참가자 $k"
        btn.setOnClickListener {
            stage++
            if (stage == 2) {
                timerTask = kotlin.concurrent.timer(period = 10) {
                    sec++
                    runOnUiThread {
                        tv_t.text = (sec.toFloat() / 100).toString()
                    }
                }
                btn.text = "정지"
            } else if (stage == 3) {
                timerTask?.cancel()
                val point = abs(sec - num).toFloat() / 100
                point_list.add(point)
                tv_p.text = point.toString()
                btn.text = "다음"
                stage = 0
            } else if (stage == 1) {
                if (k < p_num) {
                    k++
                    main()
                } else {
                    println(point_list)
                }

            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        main()

    }
}