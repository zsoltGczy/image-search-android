package com.gzslt.imagesearch.data.api.interceptor

import com.gzslt.imagesearch.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class CommonQueryParameterInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = chain.request().url.newBuilder()
            .addQueryParameter(
                QUERY_PARAMETER_API_KEY_KEY, BuildConfig.FLICKR_API_KEY
            )
            .addQueryParameter(
                QUERY_PARAMETER_SORT_KEY, QUERY_PARAMETER_SORT_VALUE
            )
            .addQueryParameter(
                QUERY_PARAMETER_PRIVACY_FILTER_KEY, QUERY_PARAMETER_PRIVACY_FILTER_VALUE
            )
            .addQueryParameter(
                QUERY_PARAMETER_CONTENT_TYPE_KEY, QUERY_PARAMETER_CONTENT_TYPE_VALUE
            )
            .addQueryParameter(
                QUERY_PARAMETER_MEDIA_KEY, QUERY_PARAMETER_MEDIA_VALUE
            )
            .addQueryParameter(
                QUERY_PARAMETER_EXTRAS_KEY, QUERY_PARAMETER_EXTRAS_VALUE
            )
            .addQueryParameter(
                QUERY_PARAMETER_FORMAT_KEY, QUERY_PARAMETER_FORMAT_VALUE
            )
            .addQueryParameter(
                QUERY_PARAMETER_NOJSONCALLBACK_KEY, QUERY_PARAMETER_NOJSONCALLBACK_VALUE
            ).build()

        request = request.newBuilder().url(url).build()

        return chain.proceed(request)
    }

    companion object {
        private const val QUERY_PARAMETER_API_KEY_KEY = "api_key"
        private const val QUERY_PARAMETER_SORT_KEY = "sort"
        private const val QUERY_PARAMETER_SORT_VALUE = "date-posted-desc"
        private const val QUERY_PARAMETER_PRIVACY_FILTER_KEY = "privacy_filter"
        private const val QUERY_PARAMETER_PRIVACY_FILTER_VALUE = "1"
        private const val QUERY_PARAMETER_CONTENT_TYPE_KEY = "content_type"
        private const val QUERY_PARAMETER_CONTENT_TYPE_VALUE = "4"
        private const val QUERY_PARAMETER_MEDIA_KEY = "media"
        private const val QUERY_PARAMETER_MEDIA_VALUE = "photos"
        private const val QUERY_PARAMETER_EXTRAS_KEY = "extras"
        private const val QUERY_PARAMETER_EXTRAS_VALUE =
            "description,date_taken,tags,owner_name"
        private const val QUERY_PARAMETER_FORMAT_KEY = "format"
        private const val QUERY_PARAMETER_FORMAT_VALUE = "json"
        private const val QUERY_PARAMETER_NOJSONCALLBACK_KEY = "nojsoncallback"
        private const val QUERY_PARAMETER_NOJSONCALLBACK_VALUE = "1"
    }
}
