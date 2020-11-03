package com.capsule.android.healthylifestyle.repository

import com.capsule.android.healthylifestyle.api.NewyorkTimesService
import com.capsule.android.healthylifestyle.model.Article
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.reactivex.Single
import javax.inject.Inject

interface ArticlesRepository {
    fun getArticles(searchKey: String) : Single<List<Article>>
}

class ArticleRepositoryImpl @Inject constructor(
    private val service: NewyorkTimesService
) : ArticlesRepository {
    override fun getArticles(searchKey: String): Single<List<Article>> {
        return service.searchArticles(fq = searchKey, api_key = "Z6FWFMNFXSBVEAe5AVSmOEHwjGojb7J3").map { it.response.articles }
    }

}

@Module
@InstallIn(ApplicationComponent::class)
abstract class ArticlesRepositoryModule {
    @Binds
    abstract fun bindArticleRepository(
        articleRepositoryImpl: ArticleRepositoryImpl
    ) : ArticlesRepository
}