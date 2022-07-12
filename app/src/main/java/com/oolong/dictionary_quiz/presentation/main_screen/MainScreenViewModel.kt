package com.oolong.dictionary_quiz.presentation.main_screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oolong.dictionary_quiz.domain.model.Definition
import com.oolong.dictionary_quiz.domain.model.Meaning
import com.oolong.dictionary_quiz.domain.model.WordInfo
import com.oolong.dictionary_quiz.domain.repository.WordInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val wordInfoRepository: WordInfoRepository
): ViewModel() {

    private val defaultWordInfo = WordInfo(
        meanings = listOf(
            Meaning(
                definitions = listOf(
                    Definition(definition = "No definition")
                ),
                synonyms = listOf("No synonyms")
            )
        ),
        word = "No Word"
    )
    private var wordInformationList by mutableStateOf(listOf(defaultWordInfo))
    private var wordInformationListSingle by mutableStateOf(mutableStateListOf(defaultWordInfo))
    var questionNumber by mutableStateOf(0)
    var answer by mutableStateOf("")

    init {
        viewModelScope.launch(Dispatchers.IO) {
            wordInfoRepository.getWordInfo()
            getWordInfo()
        }
    }

    fun onEvent(event: MainViewEvent) {
        when(event) {
            is MainViewEvent.DeleteButtonClicked -> {
                answer = answer.dropLast(1)
            }
            is MainViewEvent.LetterButtonClicked -> {
                answer += event.letter
            }
            is MainViewEvent.OKButtonClicked -> {
                //TODO evaluate the answer if it's real word, evaluate if it's right, go next question
            }
        }
    }

    fun getQuestion(): String? {
        return wordInformationListSingle[questionNumber].meanings.first().definitions?.first()?.definition
    }

    private suspend fun getWordInfo() {
        wordInformationList = wordInfoRepository.getAllWordInfo()
        for ((index, wordInfo) in wordInformationList.withIndex()) {
            if(index > 0) {
                if (wordInfo.word != wordInformationList[index-1].word) {
                    wordInformationListSingle.add(wordInfo)
                    Log.d(
                        "MainScreen",
                        "${wordInfo.word}: ${wordInfo.meanings.first().definitions?.first()?.definition}"
                    )
                }
            }
        }
    }
}