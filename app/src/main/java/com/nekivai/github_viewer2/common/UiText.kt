package com.nekivai.github_viewer2.common

import androidx.annotation.StringRes
import com.nekivai.github_viewer2.R
import kotlinx.coroutines.CancellationException
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import javax.net.ssl.SSLHandshakeException

sealed class UiText {
    data class DynamicString(val value: String) : UiText()
    data class StringResource(@StringRes val id: Int) : UiText()

    companion object {
        fun unknownError(): UiText {
            return StringResource(R.string.error_unknown)
        }

        fun ioError(): UiText {
            return StringResource(R.string.error_no_connection)
        }

        fun ioErrorServer(): UiText {
            return StringResource(R.string.error_no_connection_server)
        }

        fun httpException(): UiText {
            return StringResource(R.string.error_http)
        }
        fun baseException(): UiText {
            return StringResource(R.string.error_server_again)
        }
        fun fromThrowable(throwable: Throwable?): UiText {
            return when (throwable) {
                is CancellationException -> {
                    ioErrorServer()
                }

                is SSLHandshakeException -> {
                    ioErrorServer()
                }

                is IOException -> {
                    ioError()
                }

                is HttpException -> {
                    httpException()
                }

                else -> {
                    if (throwable?.message == null) {
                        unknownError()
                    } else {
                        DynamicString(throwable.message ?: EMPTY_STRING)
                    }
                }
            }
        }
    }
}
