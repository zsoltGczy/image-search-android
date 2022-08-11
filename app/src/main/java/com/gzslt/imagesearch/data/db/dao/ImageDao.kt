package com.gzslt.imagesearch.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gzslt.imagesearch.data.db.model.ImageDataModel

@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertImageList(articleList: List<ImageDataModel>)

    @Query("DELETE FROM Images")
    suspend fun deleteImages()
}
