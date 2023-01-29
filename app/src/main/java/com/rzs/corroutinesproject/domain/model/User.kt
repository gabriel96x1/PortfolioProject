package com.rzs.corroutinesproject.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid")
    val uid: Long,

    @ColumnInfo(name = "userName")
    val userName: String
)
