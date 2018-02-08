package com.figengungor.githubsearch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val gitHubService by lazy {
        GitHubService.create()
    }

    private var disposable: Disposable? = null

    private var adapter: RepositoryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchBtn.setOnClickListener {
            val query = searchEt.text.toString()
            if (query.isNotEmpty()) search(query) else toast("Enter some query, maybe?")
        }
    }

    private fun search(query: String) {
        hideKeyboard()
        adapter?.clearData()
        progressBar.visibility = View.VISIBLE
        disposable = gitHubService.getRepositories(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> showRepos(result) },
                        { error -> showError(error) }
                )
    }

    private fun showRepos(result: SearchResponse) {
        progressBar.visibility = View.GONE
        adapter = RepositoryAdapter(result.items)
        recyclerView.adapter = adapter
    }

    private fun showError(error: Throwable) {
        progressBar.visibility = View.GONE
        toast("Oh no! Errrr " + error.message)
    }

    override fun onDestroy() {
        disposable?.dispose()
        super.onDestroy()
    }

}
