package com.example.moviecatalogue.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoritemovies")
data class MoviesEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "moviesId")
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