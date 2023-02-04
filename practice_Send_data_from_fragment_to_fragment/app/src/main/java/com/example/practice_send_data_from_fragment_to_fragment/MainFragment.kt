package com.example.practice_send_data_from_fragment_to_fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practice_send_data_from_fragment_to_fragment.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var viewBinding: FragmentMainBinding
    lateinit var adapter: DataItemAdapter
    var dataList: ArrayList<Data_item> = arrayListOf()

//    private lateinit var getResultText: ActivityResultLauncher<Intent>

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentMainBinding.inflate(inflater, container, false)

        initRecyclerview()

        //DialogFragment의 EditText에서 정보 받아오기
//        val data = arguments?.getString("dataJ")
//        Log.d("data", data.toString())
//        viewBinding.fragmentMainRecyclerView.text = data

        //DialogFragment에 입력됐던 정보를 받아오는 부분
//        getResultText =
//            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//                if (result.resultCode == AppCompatActivity.RESULT_OK) {
////                val contents = result.data?.getStringExtra("dataJ")
//                    val contents = arguments?.getString("dataJ")
//                    dataList.apply {
//                        add(Data_item("$contents"))
//                    }
//                    adapter.notifyItemInserted(dataList.size)
//                }
//            }

        //button 눌렀을 때 dialog 띄우기
        val bottomSheetDialogFragment = DialogFragment()
        viewBinding.fragmentMainButton.setOnClickListener {
            val data = arguments?.getString("dataJ")
            bottomSheetDialogFragment.show(parentFragmentManager, bottomSheetDialogFragment.tag)
//            getResulttext()

//            val mIntent = Intent(context, DialogFragment::class.java)
//            getResultText.launch(mIntent)
            dataList.add(Data_item(data.toString()))
//            adapter.items = dataList
//            adapter.notifyItemInserted(dataList.size)
            adapter.notifyDataSetChanged()
        }

//        initAddData()

        return viewBinding.root
    }

//    //DialogFragment에 입력됐던 정보를 받아오는 부분
//    fun getResulttext() = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
//        val contents = arguments?.getString("dataJ")
//            dataList.apply {
//                add(Data_item("$contents"))
//            }
//            adapter.notifyItemInserted(dataList.size)
//    }


    fun initAddData() {
////        dataList.add(Dataitem(data.toString()))
////        dataList.add(Dataitem("aaa"))
////        dataList.add(Dataitem("aaa"))
////        dataList.add(Dataitem("aaa"))
////        dataList.add(Dataitem("aaa"))
    }

    private fun initRecyclerview() {
        viewBinding.fragmentMainRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        //adapter 생성
        adapter = DataItemAdapter(dataList)
        viewBinding.fragmentMainRecyclerView.adapter = adapter
    }

}