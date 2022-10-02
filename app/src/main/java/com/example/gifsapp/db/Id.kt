package com.example.gifsapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Id(
    @PrimaryKey val id: String
)
