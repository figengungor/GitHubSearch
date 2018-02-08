package com.figengungor.githubsearch

import com.google.gson.Gson
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by figengungor on 2/7/2018.
 */
interface GitHubService {

    @GET("search/repositories")
    fun getRepositories(@Query("q") query: String,
                        @Query("sort") sort: String = "stars",
                        @Query("order") order: String = "asc"): Observable<SearchResponse>

    companion object {
        fun create(): GitHubService {
            val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

            return retrofit.create(GitHubService::class.java)
        }
    }
}