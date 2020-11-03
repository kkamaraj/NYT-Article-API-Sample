package com.capsule.android.healthylifestyle

import com.capsule.android.healthylifestyle.model.Article
import com.capsule.android.healthylifestyle.model.Headline
import com.capsule.android.healthylifestyle.repository.ArticlesRepository
import com.capsule.android.healthylifestyle.ui.main.MainViewPresenter
import com.capsule.android.healthylifestyle.ui.main.MainViewPresenter.ViewContract
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MainViewPresenterTest {
    private lateinit var articlesRepository: ArticlesRepository
    private lateinit var mainViewPresenter: MainViewPresenter
    private lateinit var view: MockView

    @Before
    fun setUp() {
        articlesRepository = MockArticleRepository(sendError = false)
        mainViewPresenter = MainViewPresenter(articlesRepository, mainThread = Schedulers.trampoline())
        view = MockView()
    }

    @Test
    fun `verify that onTargetReady triggers API call and returns articles list`() {
        mainViewPresenter.setView(view)
        mainViewPresenter.onTargetReady()
    }
}

class MockView: ViewContract {
    override fun showProgress() {
    }

    override fun dismissProgress() {
    }

    override fun showError(localizedMessage: String?) {
    }

    override fun populateArticles(articles: List<Article>) {
        assertEquals(articles, testSuccessData())
    }

}
class MockArticleRepository(val sendError:Boolean = false) : ArticlesRepository {
    override fun getArticles(searchKey: String): Single<List<Article>> {
        return if(!sendError) {
            Single.just(testSuccessData())
        } else {
            Single.error(IllegalArgumentException())
        }
    }

}

fun testSuccessData(): List<Article> {
    return listOf(Article(
        webUrl = "https://www.nytimes.com/2005/05/10/health/nutrition/fitness-without-frills-no-men-allowed.html",
        snippet = "With the surgeon general urging Americans to do at least 30 minutes a day of moderately vigorous exercise to improve their " +
                "health...",
        headline = Headline(
            main = "",
            printHeadline = ""
        )),
        Article(
            webUrl = "https://www.nytimes.com/2005/05/10/health/nutrition/fitness-without-frills-no-men-allowed.html",
            snippet = "With the surgeon general urging Americans to do at least 30 minutes a day of moderately vigorous exercise to improve their " +
                    "health...",
            headline = Headline(
                main = "",
                printHeadline = ""
            )),
        Article(
            webUrl = "https://www.nytimes.com/2005/05/10/health/nutrition/fitness-without-frills-no-men-allowed.html",
            snippet = "With the surgeon general urging Americans to do at least 30 minutes a day of moderately vigorous exercise to improve their " +
                    "health...",
            headline = Headline(
                main = "",
                printHeadline = ""
            ))
    )
}