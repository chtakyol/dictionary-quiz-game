package com.oolong.dictionary_quiz.presentation.main_screen.components

import androidx.compose.animation.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oolong.dictionary_quiz.util.Utils.AnswerState
import com.oolong.dictionary_quiz.util.Utils.letters

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun LetterContainer(
    answerStateForEachLetter: Map<String, AnswerState> = mapOf("a" to AnswerState.PASSIVE),
    targetState: Int = 0
    // TODO pass the answers and result map
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(Color.Cyan),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Progress()

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
//                Button(onClick = { count++ }) {
//                    Text("Add")
//                }
                AnimatedContent(
                    targetState = targetState,
                    transitionSpec = {
                         if (targetState > initialState) {
                             slideInHorizontally { width -> width } + fadeIn() with
                                     slideOutHorizontally { width -> -width } + fadeOut()
                         } else {
                             slideInHorizontally { width -> -width } + fadeIn() with
                                     slideOutHorizontally { width -> width } + fadeOut()
                         }.using(
                             SizeTransform(clip = false)
                         )
                    }
                ) { targetCount ->
                    Letter(
                        letter = letters[targetCount],
                    )
                }
            }
        }
    }
}

@Composable
fun Progress(
    modifier: Modifier = Modifier,
    answerStateForEachLetter: Map<String, AnswerState> = mapOf("a" to AnswerState.PASSIVE)
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        repeat(letters.size) {
            Canvas(
                modifier = Modifier
                    .size(12.dp, 12.dp)
                    .background(Color.Green),
            ) {
                val canvasWidth = size.width
                val canvasHeight = size.height
                drawCircle(
                    color = Color.LightGray,
                    center = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
                    radius = size.minDimension / 3
                )
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

@Composable
@Preview
fun PreviewProgress() {
    Progress()
}