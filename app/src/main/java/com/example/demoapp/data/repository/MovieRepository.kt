package com.example.demoapp.data.repository

import com.example.demoapp.di.MoviesApiInterface
import javax.inject.Inject

class MovieRepository @Inject constructor(private val apiService: MoviesApiInterface) {
    private val token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5Y2I5NWJhMzdmOWIyMDI3NmE0MjQ2OGVlY2VlY2QxNyIsIm5iZiI6MTczMTkxNzUwOS45MTkwMDAxLCJzdWIiOiI2NzNhZjZjNTZhMDJhMjRkN2IyMWE0MGMiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.aYTc3S_MsJhT6cK2X8qMdUa4gQg6ZkfV25MroAC2bgU"
    suspend fun getTopRatedMovies() = apiService.getTop10Movies(token,"en-US")
    suspend fun getNewReleases() = apiService.getTop10Movies(token,"en-US",2)
    suspend fun getBeforeSearchMovies()=apiService.getTop10Movies(token,"en-US",3)
}

