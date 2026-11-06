package com.raphael.carteirinha.core.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class SafeHttpLoggingInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val endpoint = request.url.encodedPath

        Log.d(TAG, "--> " + request.method + " " + endpoint)

        val response = chain.proceed(request)

        val responseBody = runCatching {
            response.peekBody(MAX_LOG_BYTES).string()
        }.getOrDefault("<corpo da resposta indisponível>")

        val safeBody = SENSITIVE_FIELDS.replace(responseBody) { match ->
            match.groupValues[1] + "[REDACTED]" + match.groupValues[2]
        }

        Log.d(
            TAG,
            "<-- " + response.code + " " + endpoint + " " +
                safeBody.take(MAX_LOG_CHARACTERS)
        )

        return response
    }

    private companion object {
        const val TAG = "CarteirinhaAPI"
        const val MAX_LOG_BYTES = 1024L * 1024L
        const val MAX_LOG_CHARACTERS = 6000

        val SENSITIVE_FIELDS = Regex(
            """("(?:token|senha|password|authorization|accessToken|refreshToken)"\s*:\s*")[^"]*(")""",
            RegexOption.IGNORE_CASE
        )
    }
}
