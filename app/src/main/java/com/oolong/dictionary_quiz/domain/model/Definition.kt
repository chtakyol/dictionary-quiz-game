package com.oolong.dictionary_quiz.domain.model

data class Definition(
    val definition: String?
) {

    fun getDefinitionAsString(): String {
        return "definition"
    }
}
