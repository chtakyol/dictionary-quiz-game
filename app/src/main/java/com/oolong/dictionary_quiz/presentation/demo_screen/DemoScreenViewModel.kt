package com.oolong.dictionary_quiz.presentation.demo_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oolong.dictionary_quiz.domain.repository.WordInfoRepository
import com.oolong.dictionary_quiz.util.QuizWords
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DemoScreenViewModel @Inject constructor(
    private val wordInfoRepository: WordInfoRepository
): ViewModel() {

    private var getWords: Job? = null
    init {
        getWordInfo()
    }

    private fun getWordInfo() {
        getWords?.cancel()
//        getWords = wordInfoRepository.getAllWordInfo()
//            .onEach { wordInfoList ->
//                for (w in wordInfoList) {
//                    Log.d("DemoScreen", "Word: ${w.word}")
//                    Log.d("DemoScreen", "Meanings: ${w.meanings[0].definitions?.get(0)?.definition}")
//                }
//            }
//            .launchIn(viewModelScope)
    }
}