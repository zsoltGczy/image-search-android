package com.gzslt.imagesearch.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Images")
data class ImageDataModel(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    val imageId: String,
    val secret: String,
    val serverId: String,
    val title: String,
    val description: String,
    val date: String,
    val ownerName: String,
    val tags: String,
)
