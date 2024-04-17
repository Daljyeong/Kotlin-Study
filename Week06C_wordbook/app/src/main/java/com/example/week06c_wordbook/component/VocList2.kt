package com.example.week06c_wordbook.component

import android.speech.tts.TextToSpeech
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week06c_wordbook.model.VocData
import com.example.week06c_wordbook.model.VocDataViewModel
import java.util.Locale

// TTS 서비스 사용

@Composable
fun vocList2(vocDataViewModel: VocDataViewModel = viewModel()) {

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
        itemsIndexed(vocDataViewModel.vocList) { index: Int, item: VocData ->
            VocItem(vocData = item) {
                speakWord(item)
                vocDataViewModel.changeOpenStatus(index)
            }
        }
    }
}