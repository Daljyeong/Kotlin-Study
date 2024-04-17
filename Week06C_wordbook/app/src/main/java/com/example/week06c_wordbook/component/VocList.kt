package com.example.week06c_wordbook.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week06c_wordbook.model.VocData
import com.example.week06c_wordbook.model.VocDataViewModel

@Composable
fun vocList(vocDataViewModel: VocDataViewModel = viewModel()) {

    LazyColumn {
        itemsIndexed(vocDataViewModel.vocList) { index: Int, item: VocData ->
            VocItem(vocData = item) {
                vocDataViewModel.changeOpenStatus(index)
            }
        }
    }
}