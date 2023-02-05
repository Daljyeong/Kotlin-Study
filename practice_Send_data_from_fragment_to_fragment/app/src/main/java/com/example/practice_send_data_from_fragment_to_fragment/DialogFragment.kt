package com.example.practice_send_data_from_fragment_to_fragment
import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.practice_send_data_from_fragment_to_fragment.databinding.FragmentAddHistoryMainBinding
//import com.example.practice_send_data_from_fragment_to_fragment.databinding.FragmentDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
class DialogFragment : BottomSheetDialogFragment() {
    private lateinit var viewBinding: FragmentAddHistoryMainBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //방법 (3)
        viewBinding = FragmentAddHistoryMainBinding.inflate(inflater, container, false)

        //dialog의 우측 상단 '완료' 버튼 눌렀을 때 실행되는 부분
        viewBinding.btnAddHistoryMainDone.setOnClickListener {
            val mainFragment = MainFragment()
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
}