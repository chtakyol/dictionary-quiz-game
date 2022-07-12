package com.oolong.dictionary_quiz.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.oolong.dictionary_quiz.data.local.Converters
import com.oolong.dictionary_quiz.data.util.GsonParser
import com.oolong.dictionary_quiz.data.local.WordInfoDao
import com.oolong.dictionary_quiz.data.local.WordInfoDatabase
import com.oolong.dictionary_quiz.data.remote.DictionaryApi
import com.oolong.dictionary_quiz.data.repository.WordInfoRepositoryImpl
import com.oolong.dictionary_quiz.domain.repository.WordInfoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DictionaryQuizModule {

    @Provides
    @Singleton
    fun provideGetWordInfoRepository(
        api: DictionaryApi,
        db: WordInfoDatabase
    ): WordInfoRepository {
        return WordInfoRepositoryImpl(api, db.dao)
    }

    @Provides
    @Singleton
    fun provideWordInfoDatabase(app: Application): WordInfoDatabase {
        return Room.databaseBuilder(
            app,
            WordInfoDatabase::class.java,
            "word_db"
        )
            .addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }

    @Provides
    @Singleton
    fun provideDictionaryApi(): DictionaryApi {
        return Retrofit.Builder()
            .baseUrl(DictionaryApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryApi::class.java)
    }
}