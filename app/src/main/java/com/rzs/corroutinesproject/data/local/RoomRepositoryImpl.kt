package com.rzs.corroutinesproject.data.local

import com.rzs.corroutinesproject.domain.RoomRepository
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : RoomRepository {
    override suspend fun addUser() {
        TODO("Not yet implemented")
    }

    override suspend fun delUser() {
        TODO("Not yet implemented")
    }

    override suspend fun getAllUsers() {
        TODO("Not yet implemented")
    }
}