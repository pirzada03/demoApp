package com.example.demoapp.data


sealed interface ApiResult<out T>

object ApiLoading : ApiResult<Nothing>
class ApiSuccess<T>(val data: T, val statusCode: Int = -1) : ApiResult<T>
class ApiError @JvmOverloads constructor(
    val message: String = "",
    var errorMessageResId: Int = 0,
    val code: Int = -1,
    val callType: Int = -1
) : ApiResult<Nothing> {

    val error = message
}