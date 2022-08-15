package com.gzslt.imagesearch.common.extension

import androidx.paging.LoadState

fun LoadState?.isLoading() = this is LoadState.Loading

fun LoadState?.isNotLoading() = this is LoadState.NotLoading

fun LoadState?.isError() = this is LoadState.Error
