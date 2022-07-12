package com.oolong.dictionary_quiz.presentation.main_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AnswerContainer(
    answer: String = "Answer"
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(38.dp)
            .background(Color.Green),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = answer
        )
    }
}

@Preview
@Composable
fun PreviewAnswerContainer() {
    AnswerContainer()
}