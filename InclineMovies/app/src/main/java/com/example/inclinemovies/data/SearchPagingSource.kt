package com.example.inclinemovies.data

import androidx.paging.PagingSource
import com.example.inclinemovies.api.MoviesInterface
import com.example.inclinemovies.data.moviesearch.Result
import retrofit2.HttpException
import java.io.IOException

class SearchPagingSource(
    private val moviesInterface: MoviesInterface,
    private val query: String) : PagingSource<Int, Result>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val position = params.key ?: Constants.RESULT_PAGE

        return try {
            val response = moviesInterface.getSearchedMovie(Constants.API_KEY, query, position)
            val movies = response.results

            LoadResult.Page(
                data = movies,
                prevKey = if (position == Constants.RESULT_PAGE) null else position - 1,
                nextKey = if (movies.isEmpty()) null else position + 1
            )
        }catch (e: IOException){
            LoadResult.Error(e)
        }catch (e: HttpException){
            LoadResult.Error(e)
        }
    }
}