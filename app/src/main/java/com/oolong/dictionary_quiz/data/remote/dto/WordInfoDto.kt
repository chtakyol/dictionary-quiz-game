package com.oolong.dictionary_quiz.data.remote.dto

import com.oolong.dictionary_quiz.data.local.WordInfoEntity
import com.oolong.dictionary_quiz.domain.model.WordInfo

data class WordInfoDto(
    val meanings: List<MeaningDto>,
    val word: String
) {
    fun toWordInfo(): WordInfo {
        return WordInfo(
            meanings = meanings.map { it.toMeaning() },
            word = word
        )
    }

    fun toWordInfoEntity(): WordInfoEntity {
        return WordInfoEntity(
            meanings = meanings.map { it.toMeaning() },
            word = word
        )
    }
}