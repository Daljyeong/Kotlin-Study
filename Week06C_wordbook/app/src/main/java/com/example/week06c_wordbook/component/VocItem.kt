package com.example.week06c_wordbook.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week06c_wordbook.model.VocData
import com.example.week06c_wordbook.ui.theme.Week06C_wordbookTheme

@Composable
fun VocItem(vocData: VocData, onItemClick: () -> Unit) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                onItemClick()
            }
    ) { // ElevatedCard는 ColumnScope를 따름 => 내부의 아이템들이 column 형태로 배치됨
        Text(
            text = vocData.word,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Color.Blue,
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold
        )
        if (vocData.isOpen) {
            Text(
                text = vocData.meaning,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview
@Composable
fun VocItemPreview() {
    Week06C_wordbookTheme {
        VocItem(VocData("apple", "사과", false)) {
        }
    }
}