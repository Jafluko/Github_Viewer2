package com.nekivai.github_viewer2.data.repositories

import com.nekivai.github_viewer2.common.GitException
import com.nekivai.github_viewer2.common.Response
import com.nekivai.github_viewer2.common.UiText
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.net.ssl.SSLHandshakeException

open class Repository(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    protected suspend fun <Dto, Domain> wrapRequest(
        request: suspend () -> Dto?,
        mapper: suspend (Dto) -> Domain,
    ): Response<Domain> {
        return withContext(dispatcher) {
            try {
                when(val response = request()) {
                    null -> {
                        throw GitException.ResponseDtoEqualNullException
                    }
                    else -> {
                        try {
                            Response.Success(data = mapper(response))
                        } catch (e: Exception) {
                            throw GitException.MapperException(cause = e)
                        }
                    }
                }
            } catch (e: CancellationException) {
                e.printStackTrace()
                return@withContext Response.Error(UiText.fromThrowable(e))
            } catch (e: IOException) {
                e.printStackTrace()
                return@withContext Response.Error(UiText.fromThrowable(e))
            } catch (e: HttpException) {
                return@withContext Response.Error(UiText.fromThrowable(e))
            } catch (e: SSLHandshakeException) {
                return@withContext Response.Error(UiText.fromThrowable(e))
            } catch (e: GitException) {
                return@withContext Response.Error(UiText.fromThrowable(e))
            }  catch (e: Exception) {
                return@withContext Response.Error(UiText.fromThrowable(e))
            }
        }
    }
}