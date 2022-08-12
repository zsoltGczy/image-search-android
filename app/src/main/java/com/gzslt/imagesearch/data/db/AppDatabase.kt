package com.gzslt.imagesearch.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gzslt.imagesearch.BuildConfig
import com.gzslt.imagesearch.data.db.dao.ImageDao
import com.gzslt.imagesearch.data.db.dao.RemoteKeyDao
import com.gzslt.imagesearch.data.db.model.ImageDataModel
import com.gzslt.imagesearch.data.db.model.RemoteKeyDataModel

@Database(
    entities = [
        ImageDataModel::class,
        RemoteKeyDataModel::class,
    ],
    version = BuildConfig.DATABASE_VERSION
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun imageDao(): ImageDao

    abstract fun remoteKeyDao(): RemoteKeyDao
}
