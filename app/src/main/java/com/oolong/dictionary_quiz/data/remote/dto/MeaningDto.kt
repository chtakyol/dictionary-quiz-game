package com.oolong.dictionary_quiz.data.remote.dto

import com.oolong.dictionary_quiz.domain.model.Meaning

data class MeaningDto(
    val definitions: List<DefinitionDto>?,
    val synonyms: List<String>?
) {
    fun toMeaning(): Meaning {
        return Meaning(
            definitions = definitions?.map { it.toDefinition() },
            synonyms = synonyms
        )
    }
}