package com.example.week06c_wordbook.component

import android.speech.tts.TextToSpeech
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week06c_wordbook.model.VocData
import com.example.week06c_wordbook.model.VocDataViewModel
import java.util.Locale

// TTS 서비스 사용

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun vocList3(vocDataViewModel: VocDataViewModel = viewModel()) {

    val context = LocalContext.current

    var ttsReady by rememberSaveable {
        mutableStateOf(false)
    }
    var tts: TextToSpeech? by rememberSaveable {
        mutableStateOf(null)
    }

    DisposableEffect(LocalLifecycleOwner.current) {
        tts = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                ttsReady = true
                tts!!.language = Locale.US
            }
        }

        onDispose {
            tts?.stop()
            tts?.shutdown()
        }
    }

    val speakWord = { vocData: VocData ->
        if (ttsReady) {
            tts?.speak(vocData.word, TextToSpeech.QUEUE_ADD, null, null)
        }
    }

    LazyColumn {
        itemsIndexed(vocDataViewModel.vocList,
            key = { _, voc -> voc.word }) { index: Int, item: VocData ->

            // SwipeToDismiss 적용
            val state = rememberDismissState(
                confirmValueChange = {
                    if (it == DismissValue.DismissedToStart) {
                        vocDataViewModel.vocList.remove(item)
                        true
                    } else {
                        false
                    }
                }
            )

            SwipeToDismiss(state = state,
                background = {
                    val color = when (state.dismissDirection) {
                        DismissDirection.EndToStart -> Color.LightGray // 오른쪽->왼쪽으로 swipe했을 때
                        else -> Color.Transparent
                    }
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxSize()
                            .background(color) // color 값을 box에 적용해줌
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete Icon",
                            modifier = Modifier.align(Alignment.CenterEnd)
                        )
                    }
                },
                dismissContent = {
                    VocItem(vocData = item) {
                        speakWord(item)
                        vocDataViewModel.changeOpenStatus(index)
                    }
                }
            )
        }
    }
}