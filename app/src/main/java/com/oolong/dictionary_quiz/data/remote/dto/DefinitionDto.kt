package com.oolong.dictionary_quiz.data.remote.dto

import com.oolong.dictionary_quiz.domain.model.Definition

data class DefinitionDto(
    val definition: String?,
) {
    fun toDefinition(): Definition {
        return Definition(
            definition = definition,
        )
    }
}