package com.example.practice_send_data_from_fragment_to_fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practice_send_data_from_fragment_to_fragment.databinding.ItemSpendListBinding
import java.util.*

class DataItemAdapter(var items: ArrayList<Data_item>) :
    RecyclerView.Adapter<DataItemAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun OnItemClick(data: Data_item)
//        fun onClick(v: View, position: Int)
    }


    var itemClickListener: OnItemClickListener? = null //초기값 null값


    inner class ViewHolder(val binding: ItemSpendListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {

            binding.root.setOnClickListener {
                itemClickListener?.OnItemClick(items[adapterPosition]) //?는 null일 수 도 있다고 알려주는 역할

                Log.d("touch3", adapterPosition.toString())

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemSpendListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.binding.apply {

            //이미지는 이런식으로 담아야함.
//            itemSpendListImageView.setImageResource(items[position].img)
            itemSpendListTextView.text = items[position].text
//            textScore.text = items[position].score.toString()
//            textDate.text = items[position].date.toString()
//            textPrice.text = "₩" + items[position].price.toString() + " / 박"
//            textViewDifficulty.text= "난이도 ${position+1}"

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}