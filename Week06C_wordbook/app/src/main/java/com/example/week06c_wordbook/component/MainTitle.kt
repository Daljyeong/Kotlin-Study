package com.example.week06c_wordbook.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week06c_wordbook.R
import com.example.week06c_wordbook.ui.theme.Week06C_wordbookTheme

@Composable
fun MainTitle(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth() // 가로
            .height(100.dp), // 높이
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.maintitle),
            fontSize = 40.sp
        )
    }
}

@Preview
@Composable
fun MainTitlePreview() {
    Week06C_wordbookTheme {
        MainTitle()
    }
}