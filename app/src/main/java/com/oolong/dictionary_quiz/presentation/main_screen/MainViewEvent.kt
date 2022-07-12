package com.oolong.dictionary_quiz.presentation.main_screen

sealed class MainViewEvent {
    data class LetterButtonClicked(val letter: String): MainViewEvent()
    object OKButtonClicked: MainViewEvent()
    object DeleteButtonClicked: MainViewEvent()
}
