package com.gzslt.imagesearch.main.imagedetails.model

data class ImageDetailsUiModel(
    val id: Long,
    val imageUrl: String,
    val title: String,
    val tags: List<String>,
    val description: String,
    val ownerName: String,
    val date: String,
)
