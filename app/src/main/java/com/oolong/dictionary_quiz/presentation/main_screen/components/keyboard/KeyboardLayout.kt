package com.oolong.dictionary_quiz.presentation.main_screen.components.keyboard

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun KeyboardLayout(
    modifier:Modifier = Modifier,
    onOKButtonClick: () -> Unit = {},
    onDeleteButtonClicked: () -> Unit = {},
    onClick: (String) -> Unit = {}
) {
    val row1Letters = arrayOf("q", "w", "e", "r", "t", "y", "u", "i", "o", "p")
    val row2Letters = arrayOf("a", "s", "d", "f", "g", "h", "j", "k", "l")
    val row3Letters = arrayOf("z", "x", "c", "v", "b", "n", "m")

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(1.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = modifier
                .padding(2.dp)
                .fillMaxWidth(.96f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            for (letter in row1Letters) {
                KeyboardButton(
                    letter = letter,
                    onClick = onClick
                )
                Spacer(modifier.size(4.dp))
            }
        }

        Row(
            modifier = modifier
                .padding(2.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            for (letter in row2Letters) {
                KeyboardButton(
                    letter = letter,
                    onClick = onClick
                )
                Spacer(modifier.size(4.dp))
            }
        }

        Row(
            modifier = modifier
                .padding(2.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            DeleteButton(onClick = onDeleteButtonClicked)
            Spacer(modifier.size(4.dp))
            for (letter in row3Letters) {
                KeyboardButton(
                    letter = letter,
                    onClick = onClick
                )
                Spacer(modifier.size(4.dp))
            }
            OKButton(onClick = onOKButtonClick)
        }
    }
}

@Composable
@Preview
fun PreviewKeyboardLayout() {
    KeyboardLayout()
}