package com.capsule.android.healthylifestyle.ui.main

import com.capsule.android.healthylifestyle.model.Article
import com.capsule.android.healthylifestyle.repository.ArticlesRepository
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewPresenter @Inject constructor(
    private val repository: ArticlesRepository,
    private val mainThread: Scheduler
) {
    private lateinit var view: ViewContract
    private lateinit var disposable: Disposable

    fun setView(v: ViewContract) {
        view = v
    }

    fun onTargetReady() {
        view.showProgress()
        disposable = repository.getArticles("headline:(\"fitness\") AND news_desk:(\"Health & Fitness\")")
            .subscribeOn(Schedulers.io())
            .observeOn(mainThread)
            .subscribe(
            {
                view.dismissProgress()
                view.populateArticles(it)
            },
            {
                view.dismissProgress()
                view.showError(it.localizedMessage)
            }
        )
    }

    fun onDetach() {
        disposable.dispose()
    }

    interface ViewContract {
        fun showProgress()
        fun dismissProgress()
        fun showError(localizedMessage: String?)
        fun populateArticles(articles: List<Article>)
    }
}