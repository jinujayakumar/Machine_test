package com.example.kolomachinetest.api

import androidx.paging.*
import com.bumptech.glide.load.HttpException
import com.example.kolomachinetest.data.Character
import java.io.IOException

private const val TMDB_STARTING_PAGE_INDEX = 1

private class CharacterPagingSource(
    private val service: MarvelApis
) : PagingSource<Int, MarvelApis>() {
    override fun getRefreshKey(state: PagingState<Int, MarvelApis>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val pageIndex = params.key ?: TMDB_STARTING_PAGE_INDEX
        return try {
            val response = RemoteApiBuilder.getCharacterList(pageIndex)
            val movies = response.body()?.data?.results
            val nextKey =
                if (movies == null || movies.isEmpty()) {
                    null
                } else {
                    // By default, initial load size = 3 * NETWORK PAGE SIZE
                    // ensure we're not requesting duplicating items at the 2nd request
                    pageIndex + (params.loadSize / NETWORK_PAGE_SIZE)
                }
            LoadResult.Page(
                data = movies!!,
                prevKey = if (pageIndex == TMDB_STARTING_PAGE_INDEX) null else pageIndex,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}

const val NETWORK_PAGE_SIZE = 25

internal class MoviesRemoteDataSourceImpl(
    private val movieService: MarvelApis
) : MoviesRemoteDataSource {

    override fun getMovies(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MoviesPagingSource(service = movieService)
            }
        ).flow
    }
}