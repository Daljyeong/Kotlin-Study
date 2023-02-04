package com.example.practice_send_data_from_fragment_to_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.practice_send_data_from_fragment_to_fragment.databinding.FragmentDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class DialogFragment : BottomSheetDialogFragment() {
    private lateinit var viewBinding: FragmentDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //방법 (3)
        viewBinding = FragmentDialogBinding.inflate(inflater, container, false)

        //dialog의 '완료' 버튼 눌렀을 때 실행되는 부분
        viewBinding.fragmentDialogButton.setOnClickListener {
            val mainFragment = MainFragment()
            val bundle = Bundle()
            bundle.putString("dataJ", viewBinding.fragmentDialogEditText.text.toString())
            mainFragment.arguments = bundle
            val fragmentManager = requireActivity().supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.frameLayout, mainFragment)
                .addToBackStack(null)
                .commit()
            dismiss()
        }

        return viewBinding.root
    }
}