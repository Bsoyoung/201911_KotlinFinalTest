package com.example.a201911_kotlinfinaltest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.lotto.*

class LottoActivity : BaseActivity() {

    var thisWeekLottoNumArrayList = ArrayList<Int>()
    var thisWeekLottoNumTextViewArrayList = ArrayList<TextView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lotto)
        setupEvent()
        setValues()
    }

    override fun setupEvent() {

        buyOneLottoBtn.setOnClickListener {

            //숫자를 랜덤으로 6개 생성. 1~45 / 중복안됨.
            setThisWeekLottoNum()

        }

    }

    fun setThisWeekLottoNum(){

        for(lottoNumTxt in thisWeekLottoNumTextViewArrayList){
            var randomNum = 0
            while(true){
                randomNum = (Math.random()*45+1).toInt()
                var isDupIOk = true

                for(num in thisWeekLottoNumArrayList){
                    if(num == randomNum){
                        //중복되는 숫자 발견!
                        isDupIOk = false
                        break
                    }
                }

                if(isDupIOk==true)
                {
                    thisWeekLottoNumArrayList.add(randomNum)
                    break
                }

            }

            lottoNumTxt.text = randomNum.toString()

        }




    }

    override fun setValues() {
        thisWeekLottoNumTextViewArrayList.add(lottoNumTxt1)
        thisWeekLottoNumTextViewArrayList.add(lottoNumTxt2)
        thisWeekLottoNumTextViewArrayList.add(lottoNumTxt3)
        thisWeekLottoNumTextViewArrayList.add(lottoNumTxt4)
        thisWeekLottoNumTextViewArrayList.add(lottoNumTxt5)
        thisWeekLottoNumTextViewArrayList.add(lottoNumTxt6)
    }

}
