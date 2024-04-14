package com.example.week06c_wordbook.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.week06c_wordbook.component.MainTitle
import com.example.week06c_wordbook.component.vocList2

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    // 단어장 만들기
    Column {
        // 단어장 title
        MainTitle()

        // 단어 list
//        vocList()
        vocList2()
    }
}