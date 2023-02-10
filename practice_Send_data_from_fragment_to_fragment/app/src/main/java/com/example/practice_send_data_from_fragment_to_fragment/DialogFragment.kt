package com.example.practice_send_data_from_fragment_to_fragment

import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
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
            bundle.putString("dataContent", viewBinding.tvAddHistoryMainContentBox.text.toString()) //내용
            bundle.putString("dataMoney", viewBinding.tvAddHistoryMainMoneyBox.text.toString()) //금액 입력
            bundle.putString("dataMemo", viewBinding.tvAddHistoryMainMemoBox.text.toString()) //한 줄 메모
            ((activity as MainActivity).supportFragmentManager
                .findFragmentById(R.id.frameLayout) as MainFragment).arguments = bundle
            ((activity as MainActivity).supportFragmentManager
                .findFragmentById(R.id.frameLayout) as MainFragment).initAddData()

//            mainFragment.initAddData()
//
//            val fragmentManager = requireActivity().supportFragmentManager
//            fragmentManager.beginTransaction()
//                .replace(R.id.frameLayout, mainFragment)
//                .addToBackStack(null)
//                .commit()
            dismiss()

            //EditText 부분 init
            viewBinding.tvAddHistoryMainContentBox.setText("") //내용
            viewBinding.tvAddHistoryMainMoneyBox.setText("") //금액 입력
            viewBinding.tvAddHistoryMainMemoBox.setText("") //한 줄 메모
        }

        viewBinding.btnAddHistoryMainBigDone.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("dataContent", viewBinding.tvAddHistoryMainContentBox.text.toString()) //내용
            bundle.putString("dataMoney", viewBinding.tvAddHistoryMainMoneyBox.text.toString()) //금액 입력
            bundle.putString("dataMemo", viewBinding.tvAddHistoryMainMemoBox.text.toString()) //한 줄 메모
            ((activity as MainActivity).supportFragmentManager
                .findFragmentById(R.id.frameLayout) as MainFragment).arguments = bundle
            ((activity as MainActivity).supportFragmentManager
                .findFragmentById(R.id.frameLayout) as MainFragment).initAddData()

//            mainFragment.initAddData()
//
//            val fragmentManager = requireActivity().supportFragmentManager
//            fragmentManager.beginTransaction()
//                .replace(R.id.frameLayout, mainFragment)
//                .addToBackStack(null)
//                .commit()
            dismiss()

            //EditText 부분 init
            viewBinding.tvAddHistoryMainContentBox.setText("") //내용
            viewBinding.tvAddHistoryMainMoneyBox.setText("") //금액 입력
            viewBinding.tvAddHistoryMainMemoBox.setText("") //한 줄 메모
        }
        return viewBinding.root
    }

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
}