package com.oolong.dictionary_quiz.presentation.main_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.oolong.dictionary_quiz.presentation.main_screen.components.AnswerContainer
import com.oolong.dictionary_quiz.presentation.main_screen.components.LetterContainer
import com.oolong.dictionary_quiz.presentation.main_screen.components.QuestionContainer
import com.oolong.dictionary_quiz.presentation.main_screen.components.TimeContainer
import com.oolong.dictionary_quiz.presentation.main_screen.components.keyboard.KeyboardLayout

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel(),
    duration: String = "05:00",
) {
    Column {
        LetterContainer(
            answerStateForEachLetter = viewModel.answerStateOfLetters
        )
        TimeContainer(duration = duration)
        QuestionContainer(question = viewModel.getQuestion())
        AnswerContainer(answer = viewModel.answer.uppercase())
        KeyboardLayout(
            onOKButtonClick = { viewModel.onEvent(MainViewEvent.OKButtonClicked) },
            onDeleteButtonClicked = { viewModel.onEvent(MainViewEvent.DeleteButtonClicked) }
        ) {
            viewModel.onEvent(MainViewEvent.LetterButtonClicked(it))
        }
    }
}

@Composable
@Preview
fun PreviewMainScreen() {
    MainScreen()
}