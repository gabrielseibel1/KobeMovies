package com.seibel.gabriel.kobemovies.api

data class QueryResult<T>(val results: List<T>,
                          val page: Int,
                          val totalResults: Int,
                          val dates: Pair<String, String>,
                          val totalPages: Int)