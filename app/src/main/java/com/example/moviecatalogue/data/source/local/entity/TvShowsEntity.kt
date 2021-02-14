package com.example.moviecatalogue.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoritetv")
data class TvShowsEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tvId")
    var id: Int,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "date")
    var date: String,

    @ColumnInfo(name = "language")
    var language: String,

    @ColumnInfo(name = "image")
    var image: String,
)