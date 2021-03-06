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
import com.oolong.dictionary_quiz.util.Utils.AnswerState
import com.oolong.dictionary_quiz.util.Utils.letters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
    var answerStateOfLetters by mutableStateOf(mutableMapOf<String, AnswerState>())
    var targetState by mutableStateOf(0)

    init {
        for (letter in letters) {
            answerStateOfLetters[letter] = AnswerState.PASSIVE
        }

        answerStateOfLetters["a"] = AnswerState.IDLE

        viewModelScope.launch(Dispatchers.IO) {
            wordInfoRepository.getWordInfo()
            getWordInfo()
            questionNumber++
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
                if (answer.uppercase() == wordInformationListSingle[questionNumber].word.uppercase()) {
                    answer = ""
                    answerStateOfLetters[letters[questionNumber - 1]] = AnswerState.TRUE
                } else {
                    answer = ""
                    answerStateOfLetters[letters[questionNumber - 1]] = AnswerState.FALSE
                }
                targetState++
                questionNumber++
                answerStateOfLetters[letters[questionNumber - 1]] = AnswerState.IDLE
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
            } else {
                wordInformationListSingle.add(wordInfo)
                Log.d(
                    "MainScreen",
                    "${wordInfo.word}: ${wordInfo.meanings.first().definitions?.first()?.definition}"
                )
            }
        }
    }
}