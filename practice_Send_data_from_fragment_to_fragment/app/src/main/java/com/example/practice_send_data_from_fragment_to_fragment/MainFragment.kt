package com.example.practice_send_data_from_fragment_to_fragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practice_send_data_from_fragment_to_fragment.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var viewBinding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //DialogFragment의 EditText에서 정보 받아오기
        viewBinding = FragmentMainBinding.inflate(inflater, container, false)
        val data = arguments?.getString("dataJ")
        viewBinding.fragmentMainRecyclerView.text = data

        //button 눌렀을 때 dialog 띄우기
        val bottomSheetDialogFragment = DialogFragment()
        viewBinding.fragmentMainButton.setOnClickListener {
            bottomSheetDialogFragment.show(parentFragmentManager, bottomSheetDialogFragment.tag)
        }

        return viewBinding.root
    }

}