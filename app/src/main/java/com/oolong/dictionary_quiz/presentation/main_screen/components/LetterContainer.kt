package com.oolong.dictionary_quiz.presentation.main_screen.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oolong.dictionary_quiz.util.Utils.AnswerState
import com.oolong.dictionary_quiz.util.Utils.letters

@Composable
fun LetterContainer(
    // TODO pass the answers and result map
    answerStateForEachLetter: Map<String, AnswerState> = mapOf("a" to AnswerState.PASSIVE)
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(Color.Cyan),
        contentAlignment = Alignment.Center
    ) {
        LazyRow(
            modifier = Modifier
                .padding(8.dp)
                .background(Color.White)
        ){
            for (letter in letters) {
                item {
                    answerStateForEachLetter[letter]?.let {
                        Letter(
                            letter = letter,
                            answerState = it
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Letter(
    modifier: Modifier = Modifier,
    letter: String = "a",
    answerState: AnswerState = AnswerState.PASSIVE
) {

    val circleColor = when(answerState) {
        AnswerState.PASSIVE -> { Color.Blue }
        AnswerState.IDLE -> { Color.Gray }
        AnswerState.TRUE -> { Color.Green }
        AnswerState.FALSE -> { Color.Red }
        AnswerState.PASS -> { Color.Yellow }
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .size(65.dp, 65.dp)
                .background(Color.Green),
        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height
            drawCircle(
                color = circleColor,
                center = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
                radius = size.minDimension / 3
            )
        }

        Text(
            modifier = Modifier
                .padding(16.dp),
            text = letter.uppercase()
        )
    }
}

@Composable
@Preview
fun PreviewLetterContainer() {
    LetterContainer()
}

@Composable
@Preview
fun PreviewLetter() {
    Letter()
}