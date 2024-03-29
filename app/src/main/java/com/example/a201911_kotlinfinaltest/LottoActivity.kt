package com.example.a201911_kotlinfinaltest

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.lotto.*
import java.sql.Time
import java.util.*
import kotlin.collections.ArrayList

class LottoActivity : BaseActivity() {

    var mHandler = Handler()
    var isNowBuying = false
    //누적 사용금액
    var usedMoney = 0L
    //누적 당첨금액
    var luckyMoney = 0L

    //1~5등 당첨 횟수
    var firstRankCount=0
    var secondRankCount=0
    var thirdRankCount=0
    var fourthRankCount=0
    var fifthRankCount=0
    var wrongCount=0

    //정석 당첨번호 6개 저장 배열
    var lottoNumArrayList = ArrayList<Int>()
    //보너스 번호
    var bonusNUm = 0
    var thisWeekLottoNumTextViewArrayList = ArrayList<TextView>()

    var myNumArrayList = ArrayList<Int>()
    var myTextViewArrayList = ArrayList<TextView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lotto)
        setupEvent()
        setValues()
    }

    override fun setupEvent() {

        autoLottoBtn.setOnClickListener {

            if(!isNowBuying){
                doLottoLoop()
                isNowBuying = true
                autoLottoBtn.text = "구매 중단"
            }else{
                //반복 중단시키기
                stopLottoLoop()
                isNowBuying=false
                autoLottoBtn.text="자동 구매 재개"
            }

        }


        buyOneLottoBtn.setOnClickListener {

            //숫자를 랜덤으로 6개 생성. 1~45 / 중복안됨.
            setThisWeekLottoNum()
            checkLottoRank()

            usedMoney+=1000
            usedMoneyTxt.text = String.format(" 사용금액 : %,d원",usedMoney)
        }

    }

    //당첨 결과를 체크. 몇 등인지 확인

    fun checkLottoRank(){
        //6개 : 1등 => 20억원
        //5개
        //   보너스 번호가 맞으면 : 2등 => 6500만원
        //   보너스 번호 못맞추면 : 3등 => 150만원,
        //4개 : 4등 => 5만원
        //3개 : 5등 => 5천원

        //그 이하 : 꽝. => 0원
        
        var correctCount = 0

        for(myNum in myNumArrayList){
            for(thisWeekNum in lottoNumArrayList){
                if(thisWeekNum == myNum){
                    correctCount++
                }

            }
        }

        if(correctCount==6){
            luckyMoney+=2000000000
            firstRankCount++
//            Toast.makeText(mContext,"1등 당첨!",Toast.LENGTH_SHORT).show()
        }else if(correctCount == 5){

            var isSecondRank = false
            for(num in myNumArrayList)
            {
                if(num== bonusNUm)
                {
                    isSecondRank = true
                    break
                }
            }

            if(isSecondRank)
            {
                luckyMoney+=65000000
                secondRankCount++
            }else
            {
                luckyMoney+=1500000
                thirdRankCount++
            }

//            Toast.makeText(mContext,"3등 당첨!",Toast.LENGTH_SHORT).show()
        }else if(correctCount == 4){
            luckyMoney+=50000
            fourthRankCount++
//            Toast.makeText(mContext,"4등 당첨!",Toast.LENGTH_SHORT).show()
        }else if(correctCount == 3){
            usedMoney-=5000
            fifthRankCount++
//            Toast.makeText(mContext,"5등 당첨!",Toast.LENGTH_SHORT).show()
        }else{
            luckyMoney+=0
            wrongCount++
           // Toast.makeText(mContext,"꽝입니다!",Toast.LENGTH_SHORT).show()
        }

        luckyMoneyTxt.text = String.format("누적 당첨 금액 : %,d원",luckyMoney)
        firstRankCountTxt.text = String.format("1등 당첨 : %,d회", firstRankCount)
        secondRankCountTxt.text = String.format("2등 당첨 : %,d회", secondRankCount)
        thirdRankCountTxt.text = String.format("3등 당첨 : %,d회", thirdRankCount)
        fourthRankCountTxt.text = String.format("4등 당첨 : %,d회", fourthRankCount)
        fifthRankCountTxt.text = String.format("5등 당첨 : %,d회", fifthRankCount)
        wrongCountTxt.text = String.format("낙첨 횟수 : %,d회", wrongCount)

    }

    var LottoRunnable = object : Runnable{
        override fun run() {
            if(usedMoney<100000000){

                //숫자를 랜덤으로 6개 생성. 1~45 / 중복안됨.
                setThisWeekLottoNum()
                checkLottoRank()

                usedMoney+=1000
                usedMoneyTxt.text = String.format(" 사용금액 : %,d원",usedMoney)
                doLottoLoop()
            }else
            {
                runOnUiThread {
                    Toast.makeText(mContext,"로또 구매를 종료합니다.",Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    fun doLottoLoop(){
        mHandler.post (LottoRunnable)

    }

    fun stopLottoLoop(){
        mHandler.removeCallbacks(LottoRunnable)
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
        //보너스 번호를 추가로 뽑자. 중복을 피해야함. => 몇번이나 뽑아야 중복이 아닐지 알 수 없다.

        while(true){
            var tempRandomNum = (Math.random()*45+1).toInt()
            var isDupIsOk = true
            for(num in lottoNumArrayList){
                if(tempRandomNum == num){
                    //중복발견! 다시 뽑아야함.
                    isDupIsOk = false
                    break
                }
            }
            if(isDupIsOk) {
                bonusNUm = tempRandomNum
                break
            }


        }

        //6개의 텍스트뷰 / 당첨번호를 뽑아내서 연결
        for(i in 0 .. lottoNumArrayList.size -1){
            var numTxt = thisWeekLottoNumTextViewArrayList.get(i)
            var number = lottoNumArrayList.get(i)

            numTxt.text = number.toString()
        }

        bonusNumTxt.text = bonusNUm.toString()








    }

    override fun setValues() {
        thisWeekLottoNumTextViewArrayList.add(lottoNumTxt1)
        thisWeekLottoNumTextViewArrayList.add(lottoNumTxt2)
        thisWeekLottoNumTextViewArrayList.add(lottoNumTxt3)
        thisWeekLottoNumTextViewArrayList.add(lottoNumTxt4)
        thisWeekLottoNumTextViewArrayList.add(lottoNumTxt5)
        thisWeekLottoNumTextViewArrayList.add(lottoNumTxt6)


        myTextViewArrayList.add(myLottoNumTxt1)
        myTextViewArrayList.add(myLottoNumTxt2)
        myTextViewArrayList.add(myLottoNumTxt3)
        myTextViewArrayList.add(myLottoNumTxt4)
        myTextViewArrayList.add(myLottoNumTxt5)
        myTextViewArrayList.add(myLottoNumTxt6)

        for(myTv in myTextViewArrayList){
            myNumArrayList.add(myTv.text.toString().toInt())
        }


    }

}
