package com.example.demoapp.data.model

import com.google.gson.annotations.SerializedName

data class MoviesResponse (
    @SerializedName("page"          ) var page         : Int?               = null,
    @SerializedName("results"       ) var results      : ArrayList<Movie> = arrayListOf(),
    @SerializedName("total_pages"   ) var totalPages   : Int?               = null,
    @SerializedName("total_results" ) var totalResults : Int?               = null
)

data class Movie (
    @SerializedName("id"                ) var id               : Int?           = null,
    @SerializedName("original_title"    ) var originalTitle    : String?        = null,
    @SerializedName("poster_path"       ) var posterPath       : String?        = null,
    @SerializedName("vote_average"      ) var voteAverage      : Double?        = null,
)
