package com.example.practice_send_data_from_fragment_to_fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.example.practice_send_data_from_fragment_to_fragment.databinding.FragmentAddHistoryMainBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
//import com.example.practice_send_data_from_fragment_to_fragment.databinding.FragmentDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DialogFragment : BottomSheetDialogFragment() {
    private lateinit var viewBinding: FragmentAddHistoryMainBinding
    var icLiving = false
    var icFood = false
    var icCafe = false
    var icTransportation = false
    var icFashion = false
    var icCommunication = false
    var icHealth = false
    var icLearn = false
    var icCulture = false
    var icBeauty = false
    var icPet = false
    var icGift = false

    var recyclerSum = 0

    //dialog 높이 지정
    override fun onStart() {
        super.onStart()
        view?.viewTreeObserver?.addOnGlobalLayoutListener {
            val behavior = BottomSheetBehavior.from(requireView().parent as View)
            behavior.peekHeight = 1700
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //방법 (3)
        viewBinding = FragmentAddHistoryMainBinding.inflate(inflater, container, false)

        //아이콘 단일선택 클릭 구현한 부분
        viewBinding.icUnselectedLiving.setOnClickListener {
            icLiving = radioButton(viewBinding.icUnselectedLiving, icLiving, R.drawable.ic_selected_living, R.drawable.ic_unselected_living)
        }
        viewBinding.icUnselectedFood.setOnClickListener {
            icFood = radioButton(viewBinding.icUnselectedFood, icFood, R.drawable.ic_selected_food, R.drawable.ic_unselected_food)
        }
        viewBinding.icUnselectedCafe.setOnClickListener {
            icCafe = radioButton(viewBinding.icUnselectedCafe, icCafe, R.drawable.ic_selected_cafe, R.drawable.ic_unselected_cafe)
        }
        viewBinding.icUnselectedTransportation.setOnClickListener {
            icTransportation = radioButton(viewBinding.icUnselectedTransportation, icTransportation, R.drawable.ic_selected_transportation, R.drawable.ic_unselected_transportation)
        }
        viewBinding.icUnselectedFashion.setOnClickListener {
            icFashion = radioButton(viewBinding.icUnselectedFashion, icFashion, R.drawable.ic_selected_fashion, R.drawable.ic_unselected_fashion)
        }
        viewBinding.icUnselectedCommunication.setOnClickListener {
            icCommunication = radioButton(viewBinding.icUnselectedCommunication, icCommunication, R.drawable.ic_selected_communication, R.drawable.ic_unselected_communication)
        }
        viewBinding.icUnselectedHealth.setOnClickListener {
            icHealth = radioButton(viewBinding.icUnselectedHealth, icHealth, R.drawable.ic_selected_health, R.drawable.ic_unselected_health)
        }
        viewBinding.icUnselectedLearn.setOnClickListener {
            icLearn = radioButton(viewBinding.icUnselectedLearn, icLearn, R.drawable.ic_selected_learn, R.drawable.ic_unselected_learn)
        }
        viewBinding.icUnselectedCulture.setOnClickListener {
            icCulture = radioButton(viewBinding.icUnselectedCulture, icCulture, R.drawable.ic_selected_culture, R.drawable.ic_unselected_culture)
        }
        viewBinding.icUnselectedBeauty.setOnClickListener {
            icBeauty = radioButton(viewBinding.icUnselectedBeauty, icBeauty, R.drawable.ic_selected_beauty, R.drawable.ic_unselected_beauty)
        }
        viewBinding.icUnselectedPet.setOnClickListener {
            icPet = radioButton(viewBinding.icUnselectedPet, icPet, R.drawable.ic_selected_pet, R.drawable.ic_unselected_pet)
        }
        viewBinding.icUnselectedGift.setOnClickListener {
            icGift = radioButton(viewBinding.icUnselectedGift, icGift, R.drawable.ic_selected_gift, R.drawable.ic_unselected_gift)
        }

        //dialog의 우측 상단 '완료' 버튼 눌렀을 때 실행되는 부분
        viewBinding.btnAddHistoryMainDone.setOnClickListener {
            val bundle = Bundle()

            bundle.putInt("dataIcn", resultRadio())

            bundle.putString("dataContent", viewBinding.tvAddHistoryMainContentBox.text.toString()) //내용
            bundle.putString("dataMoney", viewBinding.tvAddHistoryMainMoneyBox.text.toString()) //금액 입력
            bundle.putString("dataMemo", viewBinding.tvAddHistoryMainMemoBox.text.toString()) //한 줄 메모
            ((activity as MainActivity).supportFragmentManager
                .findFragmentById(R.id.frameLayout) as MainFragment).arguments = bundle
            ((activity as MainActivity).supportFragmentManager
                .findFragmentById(R.id.frameLayout) as MainFragment).initAddData()

            //프로그레스 바 높이 설정
            ((activity as MainActivity).supportFragmentManager
                .findFragmentById(R.id.frameLayout) as MainFragment).setProgressBarHeight(progressBarHeight(viewBinding.tvAddHistoryMainMoneyBox.text.toString()))

            dismiss()

            //EditText 부분 init
            viewBinding.tvAddHistoryMainContentBox.setText("") //내용
            viewBinding.tvAddHistoryMainMoneyBox.setText("") //금액 입력
            viewBinding.tvAddHistoryMainMemoBox.setText("") //한 줄 메모
        }

        //dialog의 중앙 하단 '완료' 버튼 눌렀을 때 실행되는 부분
        viewBinding.btnAddHistoryMainBigDone.setOnClickListener {
            val bundle = Bundle()

            bundle.putInt("dataIcn", resultRadio())

            bundle.putString("dataContent", viewBinding.tvAddHistoryMainContentBox.text.toString()) //내용
            bundle.putString("dataMoney", viewBinding.tvAddHistoryMainMoneyBox.text.toString()) //금액 입력
            bundle.putString("dataMemo", viewBinding.tvAddHistoryMainMemoBox.text.toString()) //한 줄 메모
            ((activity as MainActivity).supportFragmentManager
                .findFragmentById(R.id.frameLayout) as MainFragment).arguments = bundle
            ((activity as MainActivity).supportFragmentManager
                .findFragmentById(R.id.frameLayout) as MainFragment).initAddData()

            //프로그레스 바 높이 설정
            ((activity as MainActivity).supportFragmentManager
                .findFragmentById(R.id.frameLayout) as MainFragment).setProgressBarHeight(progressBarHeight(viewBinding.tvAddHistoryMainMoneyBox.text.toString()))

            dismiss()

            //EditText 부분 init
            viewBinding.tvAddHistoryMainContentBox.setText("") //내용
            viewBinding.tvAddHistoryMainMoneyBox.setText("") //금액 입력
            viewBinding.tvAddHistoryMainMemoBox.setText("") //한 줄 메모
        }
        return viewBinding.root
    }

    //아이콘들이 radioButton(단일선택)처럼 작동하게 하는 함수
    fun radioButton(iconId: ImageButton, checkNum: Boolean, selectedImg: Int, unselectedImg: Int): Boolean{
        return if(checkNum){ //selected -> unselected
            initRadioBox()
//            iconId.setImageResource(unselectedImg)
            false
        }else{ //unselected -> selected
            initRadioBox()
            iconId.setImageResource(selectedImg)
            true
        }
    }

    //모든 아이콘의 이미지를 unselected, Boolean 변수를 false로 init하는 함수
    fun initRadioBox() {
        viewBinding.icUnselectedLiving.setImageResource(R.drawable.ic_unselected_living)
        viewBinding.icUnselectedFood.setImageResource(R.drawable.ic_unselected_food)
        viewBinding.icUnselectedCafe.setImageResource(R.drawable.ic_unselected_cafe)
        viewBinding.icUnselectedTransportation.setImageResource(R.drawable.ic_unselected_transportation)
        viewBinding.icUnselectedFashion.setImageResource(R.drawable.ic_unselected_fashion)
        viewBinding.icUnselectedCommunication.setImageResource(R.drawable.ic_unselected_communication)

        viewBinding.icUnselectedHealth.setImageResource(R.drawable.ic_unselected_health)
        viewBinding.icUnselectedLearn.setImageResource(R.drawable.ic_unselected_learn)
        viewBinding.icUnselectedCulture.setImageResource(R.drawable.ic_unselected_culture)
        viewBinding.icUnselectedBeauty.setImageResource(R.drawable.ic_unselected_beauty)
        viewBinding.icUnselectedPet.setImageResource(R.drawable.ic_unselected_pet)
        viewBinding.icUnselectedGift.setImageResource(R.drawable.ic_unselected_gift)

        icLiving = false
        icFood = false
        icCafe = false
        icTransportation = false
        icFashion = false
        icCommunication = false
        icHealth = false
        icLearn = false
        icCulture = false
        icBeauty = false
        icPet = false
        icGift = false
    }

    //Boolean값이 true인 아이콘(사용자가 최종으로 선택한 아이콘)의 halfselected 이미지를 return하는 함수
    fun resultRadio(): Int{
        if(icLiving)
            return R.drawable.ic_halfselected_living
        if(icFood)
            return R.drawable.ic_halfselected_food
        if(icCafe)
            return R.drawable.ic_halfselected_cafe
        if (icTransportation)
            return R.drawable.ic_halfselected_transportation
        if (icFashion)
            return R.drawable.ic_halfselected_fashion
        if (icCommunication)
            return R.drawable.ic_halfselected_communication
        if (icHealth)
            return R.drawable.ic_halfselected_health
        if (icLearn)
            return R.drawable.ic_halfselected_learn
        if (icCulture)
            return R.drawable.ic_halfselected_culture
        if (icBeauty)
            return R.drawable.ic_halfselected_beauty
        if (icPet)
            return R.drawable.ic_halfselected_pet
        if (icGift)
            return R.drawable.ic_halfselected_gift

        return 0
    }

    //프로그레스바 높이 return하는 함수
    @SuppressLint("ResourceType")
    fun progressBarHeight(recyclerNowInput:String): Int {
        val recyclerNow: Int = recyclerNowInput.toInt() //리사이클러뷰로 입력된 금액 하나

        //HomeFragment의 오늘 소비 가능 금액
        val todaySpend = ((activity as MainActivity).supportFragmentManager
            .findFragmentById(R.id.frameLayout) as MainFragment).returnTodayInt()

        recyclerSum += recyclerNow //리사이클러뷰로 입력된 총 금액

//        Toast.makeText(context, "입력된 금액 하나 = $recyclerNow", Toast.LENGTH_SHORT).show()
//        Toast.makeText(context, "오늘 소비 가능 = $todaySpend", Toast.LENGTH_SHORT).show()
//        Toast.makeText(context, "리클 총금액 = $recyclerSum", Toast.LENGTH_SHORT).show()
//
        val result = (recyclerSum * 100) / todaySpend
        Toast.makeText(context, "높이 = $result", Toast.LENGTH_SHORT).show()

        //프로그레스바 높이 = 리사이클러뷰로 입력된 총 금액 / 오늘 소비 가능 금액 * 100
        return (recyclerSum * 100) / todaySpend
    }
}