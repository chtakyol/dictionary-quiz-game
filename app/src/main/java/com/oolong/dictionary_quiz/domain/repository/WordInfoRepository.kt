package com.oolong.dictionary_quiz.domain.repository

import com.oolong.dictionary_quiz.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {

    suspend fun getWordInfo()

    suspend fun getAllWordInfo(): List<WordInfo>
}
