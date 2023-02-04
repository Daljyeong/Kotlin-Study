package com.example.practice_send_data_from_fragment_to_fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practice_send_data_from_fragment_to_fragment.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var viewBinding: FragmentMainBinding
    lateinit var adapter: DataItemAdapter
    var dataList: ArrayList<Data_item> = arrayListOf()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentMainBinding.inflate(inflater, container, false)

        initRecyclerview()

        //DialogFragment의 EditText에서 정보 받아오기
//        val data = arguments?.getString("dataJ")
//        Log.d("data", data.toString())
//        viewBinding.fragmentMainRecyclerView.text = data

        //button 눌렀을 때 dialog 띄우기
        val bottomSheetDialogFragment = DialogFragment()
        viewBinding.fragmentMainButton.setOnClickListener {
            val data = arguments?.getString("dataJ")
            bottomSheetDialogFragment.show(parentFragmentManager, bottomSheetDialogFragment.tag)

            dataList.add(Data_item(data.toString()))
//            adapter.items = dataList
            adapter.notifyDataSetChanged()
        }

//        initAddData()

        return viewBinding.root
    }

    fun initAddData() {
//        dataList.add(Dataitem(data.toString()))
//        dataList.add(Dataitem("aaa"))
//        dataList.add(Dataitem("aaa"))
//        dataList.add(Dataitem("aaa"))
//        dataList.add(Dataitem("aaa"))
    }

    private fun initRecyclerview() {
        viewBinding.fragmentMainRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)

        //adapter 생성
        adapter = DataItemAdapter(dataList)
        viewBinding.fragmentMainRecyclerView.adapter = adapter
    }

}