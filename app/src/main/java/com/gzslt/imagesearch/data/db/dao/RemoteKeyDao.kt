package com.gzslt.imagesearch.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gzslt.imagesearch.data.db.model.RemoteKeyDataModel

@Dao
interface RemoteKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKeyDataModel>)

    @Query("SELECT * FROM RemoteKeys WHERE imageId = :imageId")
    suspend fun getRemoteKeyByImageId(imageId: String): RemoteKeyDataModel?

    @Query("DELETE FROM RemoteKeys")
    suspend fun clearRemoteKeys()
}
