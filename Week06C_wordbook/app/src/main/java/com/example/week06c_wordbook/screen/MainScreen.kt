package com.example.week06c_wordbook.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.week06c_wordbook.component.MainTitle
import com.example.week06c_wordbook.component.vocList

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    // 단어장 만들기
    Column {
        MainTitle() // 단어장 title
        vocList() // 단어 list
    }
}