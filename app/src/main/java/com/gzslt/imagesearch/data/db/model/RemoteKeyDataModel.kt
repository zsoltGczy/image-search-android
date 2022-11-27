package com.gzslt.imagesearch.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "RemoteKeys")
data class RemoteKeyDataModel(
    @PrimaryKey val imageId: String,
    val prevKey: Int?,
    val nextKey: Int?,
)
