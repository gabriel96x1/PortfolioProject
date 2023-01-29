package com.rzs.corroutinesproject.domain

interface RoomRepository {

    suspend fun addUser()
    suspend fun delUser()
    suspend fun getAllUsers()
}