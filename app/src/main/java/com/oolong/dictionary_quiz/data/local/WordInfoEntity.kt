package com.oolong.dictionary_quiz.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.oolong.dictionary_quiz.domain.model.Meaning
import com.oolong.dictionary_quiz.domain.model.WordInfo

@Entity
data class WordInfoEntity(
    val word: String,
    val meanings: List<Meaning>,
    @PrimaryKey val id: Int? = null
) {
    fun toWordInfo(): WordInfo {
        return WordInfo(
            meanings = meanings,
            word = word,
        )
    }
}