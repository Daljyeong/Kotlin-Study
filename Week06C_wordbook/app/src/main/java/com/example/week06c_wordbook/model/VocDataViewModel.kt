package com.example.week06c_wordbook.model

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import com.example.week06c_wordbook.R
import java.util.Scanner

class VocDataViewModel(private val application: Application) : AndroidViewModel(application) {

    var vocList = mutableStateListOf<VocData>()
        private set

    // ViewModel이 생성될 때 한 번만 실행되는 초기화 부분
    init {
        vocList.addAll(readWordFile())
    }

    // 파일을 읽은 후 wordList를 반환해주는 함수
    private fun readWordFile(): MutableList<VocData> {
        val context = application.applicationContext
        val scan = Scanner(context.resources.openRawResource(R.raw.words))
        val wordList = mutableListOf<VocData>()

        // 파일 읽어오기
        while (scan.hasNextLine()) {
            val word = scan.nextLine()
            val meaning = scan.nextLine()
            wordList.add(VocData(word, meaning)) // isOpen은 data class에서 false로 알아서 초기 설정되어 있음
        }
        scan.close()

        return wordList
    }

    fun changeOpenStatus(index: Int) {
        vocList[index] = vocList[index].copy(isOpen = !vocList[index].isOpen)
    }
}