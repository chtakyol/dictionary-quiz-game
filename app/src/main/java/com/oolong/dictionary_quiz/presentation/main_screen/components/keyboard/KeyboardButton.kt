package com.oolong.dictionary_quiz.presentation.main_screen.components.keyboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun KeyboardButton(
    letter: String = "q",
    onClick: (String) -> Unit
) {
    TextButton(
        modifier = Modifier
            .size(width = 32.dp, height = 64.dp)
            .background(Color.LightGray)
            .padding(1.dp),
        onClick = {
            onClick(letter)
        }
    ) {
        Text(
            text = letter.uppercase()
        )
    }
}

@Composable
fun OKButton(
    onClick: () -> Unit
) {
    TextButton(
        modifier = Modifier
            .size(width = 48.dp, height = 64.dp)
            .background(Color.LightGray),
        onClick = {
            onClick()
        }
    ) {
        Text(
            text = "OK"
        )
    }
}

@Composable
fun DeleteButton(
    onClick: () -> Unit
) {
    TextButton(
        modifier = Modifier
            .size(width = 48.dp, height = 64.dp)
            .background(Color.LightGray),
        onClick = {
            onClick()
        }
    ) {
        Text(
            text = "DEL"
        )
    }
}

@Composable
@Preview
fun PreviewKeyboardButton() {
    KeyboardButton() {}
}

@Composable
@Preview
fun PreviewOKButton() {
    OKButton {

    }
}

@Composable
@Preview
fun PreviewDeleteButton() {
    DeleteButton {

    }
}