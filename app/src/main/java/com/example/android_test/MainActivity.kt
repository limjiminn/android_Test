package com.example.android_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.lang.Math.abs
import java.util.*

class MainActivity : AppCompatActivity() {
    var p_num = 3
    var k = 1
    val point_list = mutableListOf<Float>()
    var isBlind = false
        //시작페이지
    fun start(){
        setContentView(R.layout.activity_start)
            val tv_pnum:TextView = findViewById(R.id.tv_pnum)
            val btn_minus:Button = findViewById(R.id.btn_minus)
            val btn_plus:Button = findViewById(R.id.btn_plus)
            val btn_start:Button = findViewById(R.id.btn_start)
            val btn_blind:Button = findViewById(R.id.btn_blind)

            btn_blind.setOnClickListener {
                isBlind = !isBlind
                if(isBlind == true){
                    btn_blind.text = "Blind 모드 ON"
                }else{
                    btn_blind.text = "Blind 모드 OFF"
                }
            }

            tv_pnum.text = p_num.toString()
            btn_minus.setOnClickListener {
                p_num--
                if(p_num == 0) {
                    p_num = 1
                }
                tv_pnum.text = p_num.toString()
            }
            btn_plus.setOnClickListener {
                p_num ++
                tv_pnum.text = p_num.toString()
            }
            btn_start.setOnClickListener {
                main()
            }

    }

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
        val tv_t:TextView = findViewById(R.id.tv_pnum)
        val tv_p:TextView = findViewById(R.id.tv_point)
        val tv_people:TextView = findViewById(R.id.tv_people)
        val btn:Button = findViewById(R.id.btn_blind)
        val btn_i:Button = findViewById(R.id.btn_i)
        val random_box = Random()  //랜덤함수 사용하기
        val num = random_box.nextInt(1001) // 0부터 1000까지

        tv.text = (num.toFloat()/100).toString()
        btn.text = "시작"
        tv_people.text ="참가자 $k"

        btn_i.setOnClickListener {
            point_list.clear()
            k = 1
            start()
        }

        btn.setOnClickListener {
            stage++
            if (stage == 2) {
                timerTask = kotlin.concurrent.timer(period = 10) {
                    sec++
                    runOnUiThread {
                        if(isBlind == false){
                            tv_t.text = (sec.toFloat() / 100).toString()
                        }else if(isBlind == true && stage == 2){
                            tv_t.text = "???"
                        }

                    }
                }
                btn.text = "정지"
            } else if (stage == 3) {
                tv_t.text = (sec.toFloat() / 100).toString()
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
                    end()
                }

            }
        }
    }

    fun end(){
        setContentView(R.layout.activity_end)
        val tv_last:TextView = findViewById(R.id.tv_last)
        val tv_lpoint:TextView = findViewById(R.id.tv_lpoint)
        val btn_init:Button = findViewById(R.id.btn_init)

        tv_lpoint.text = (point_list.maxOrNull()).toString()
        var index_last = point_list.indexOf(point_list.maxOrNull())
        tv_last.text = "참가자" + (index_last+1).toString()

        btn_init.setOnClickListener {
            point_list.clear()
            k = 1
            start()
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        start()

    }
}