package com.oolong.dictionary_quiz.presentation.main_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LetterContainer() {
    val letters = listOf("a", "b", "c", "d", "e", "f", "g", "h", "j", "k", "l", "m", "n", "o", "p")

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
                item { Text(
                    modifier = Modifier
                        .padding(16.dp),
                    text = letter.uppercase()
                ) }
            }
        }
    }
}

@Composable
@Preview
fun PreviewLetterContainer() {
    LetterContainer()
}