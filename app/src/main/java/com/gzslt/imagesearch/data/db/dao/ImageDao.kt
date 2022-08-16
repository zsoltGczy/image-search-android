package com.gzslt.imagesearch.data.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gzslt.imagesearch.data.db.model.ImageDataModel
import com.gzslt.imagesearch.data.db.tuple.ImageDetailsTuple
import com.gzslt.imagesearch.data.db.tuple.ImageListItemTuple

@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImageList(articleList: List<ImageDataModel>)

    @Query("SELECT id, imageId, serverId, secret FROM images ORDER BY id")
    fun getImages(): PagingSource<Int, ImageListItemTuple>

    @Query("SELECT * FROM Images WHERE imageId = :imageId")
    suspend fun getImageDetailsByImageId(imageId: String): ImageDetailsTuple

    @Query("DELETE FROM Images")
    suspend fun deleteImages()
}
