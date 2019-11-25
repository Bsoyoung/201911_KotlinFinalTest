package com.example.a201911_kotlinfinaltest

import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.lotto.*
import java.util.*
import kotlin.collections.ArrayList

class LottoActivity : BaseActivity() {

    var lottoNumArrayList = ArrayList<Int>()
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

        //당첨번호는 모두 날리고 다시 뽑자.
        lottoNumArrayList.clear()

        //6개의 텍스트뷰에 들어갈 당첨번호를 뽑아내는 반복문
        for(lottoNumTxt in thisWeekLottoNumTextViewArrayList){

            //선정될 랜덤값이 들어갈 변수
            var randomNum = 0

            //몇번을 반복해야 중복을 피할지는 알 수 없음.
            //반복문 : 횟수가 명확하면 for. 언제까지 돌려야할지 모르면 while(true) => if (조건) break
            while(true){
                // 1~ 45 사이의 랜덤값을 뽑아서 변수에 임시 저장
                randomNum = (Math.random()*45+1).toInt()

                //일단 중복되지 않는다. (괜찮다) 라고 전제하고 검사 시작
                var isDupIOk = true

                //지금까지 만든 당첨번호를 모두 꺼내어보자
                for(num in lottoNumArrayList){
                    //지금 만든 랜던번호와 꺼내본 당첨번호가 같은가?
                    if(num == randomNum){
                        //중복되는 숫자 발견!
                        //더이상 중복검사를 통과할 수 없다.
                        isDupIOk = false
                        break
                    }
                }
                //중복검사 통과했는지 확인
                if(isDupIOk==true)
                {
                    //만약 통과했다면 당첨번호로 넣어주자.
                    lottoNumArrayList.add(randomNum)
                    //올바른 번호를 뽑았으니 무한반복을 탈출.
                    break
                }

            }
            //순서가 제멋대로라 보기가 좋지 않다.
            //lottoNumTxt.text = randomNum.toString()

        }

        //당첨 번호 6개를 작은 숫자부터 큰 숫자 순서대로 (정렬)!
        Collections.sort(lottoNumArrayList)

        //6개의 텍스트뷰 / 당첨번호를 뽑아내서 연결
        for(i in 0 .. lottoNumArrayList.size -1){
            var numTxt = thisWeekLottoNumTextViewArrayList.get(i)
            var number = lottoNumArrayList.get(i)

            numTxt.text = number.toString()
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
