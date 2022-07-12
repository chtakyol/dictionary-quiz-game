package com.oolong.dictionary_quiz.data.repository

import android.util.Log
import com.oolong.dictionary_quiz.data.local.WordInfoDao
import com.oolong.dictionary_quiz.data.remote.DictionaryApi
import com.oolong.dictionary_quiz.domain.model.WordInfo
import com.oolong.dictionary_quiz.domain.repository.WordInfoRepository
import com.oolong.dictionary_quiz.util.QuizWords
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException

class WordInfoRepositoryImpl(
    private val api: DictionaryApi,
    private val dao: WordInfoDao
): WordInfoRepository {

    override suspend fun getWordInfo() {
        try {
            for (word in QuizWords.quizWords) {
                val remoteWords = api.getWordInfo(word)
                dao.insertWordInfo(remoteWords.map { it.toWordInfoEntity() })
            }
        } catch (e: HttpException) {
            Log.e("Error", "Http error")
        } catch (e: IOException) {
            Log.e("Error", "IO error")
        }
    }

    override suspend fun getAllWordInfo(): List<WordInfo> {
        return dao.getAllWordInfo().map { it.toWordInfo() }
    }
}