

package com.ali.moviesApp.main.common

class Constants {
    object ApiKey{
        const val KEY = "9c466cb7e03e478b8abf6b97dfdbb961"
    }
    object HttpRequestErrorCode {
        const val UN_AUTHORIZED = 401
        const val SERVER_ERROR = 500
        const val INVALID_INPUT = 400
        const val NOT_FOUND = 404
        const val PERMISSION_DENIED = 403
        const val CONNECTION_ERROR = -1
    }

    object ErrorApi {
        //deleted or unauthorized 401
        const val UNAUTHORIZED = "Un Authorized"

        //500
        const val SERVER_ERROR = "Server Issue"

        //400
        const val BODY_ERROR = "Body Error"

        //400
        const val BAD_REQUEST = "Bad Request"
        const val INVALID_INPUT = "Invalid Input"

        //404
        const val NOT_FOUND = "Service Not Available"

        //403
        const val PERMISSION_DENIED = "permission Denied"

        //no internet connection
        const val CONNECTION_ERROR = "Internet connection issue"
    }

    data class CustomErrorThrow(val key: String, val value: String) : Throwable()
}