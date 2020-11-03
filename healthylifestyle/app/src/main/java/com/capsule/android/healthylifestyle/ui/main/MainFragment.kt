package com.capsule.android.healthylifestyle.ui.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capsule.android.healthylifestyle.R
import com.capsule.android.healthylifestyle.model.Article
import com.capsule.android.healthylifestyle.ui.main.adapter.ArticleAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment(), MainViewPresenter.ViewContract {

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var viewPresenter: MainViewPresenter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: ArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.main_fragment, container, false)
        recyclerView = root.findViewById(R.id.recycler_view)
        adapter = ArticleAdapter()
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        progressBar = root.findViewById(R.id.progress_bar)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewPresenter.onTargetReady()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewPresenter.setView(this)
    }

    override fun onDetach() {
        super.onDetach()
        viewPresenter.onDetach()
    }

    override fun showProgress() {
        progressBar.isVisible = true
    }

    override fun dismissProgress() {
        progressBar.isGone = true
    }

    override fun showError(localizedMessage: String?) {
        Toast.makeText(requireActivity().applicationContext, getString(R.string.load_error), Toast.LENGTH_LONG).show()
    }

    override fun populateArticles(articles: List<Article>) {
        adapter.setData(articles)
    }

}