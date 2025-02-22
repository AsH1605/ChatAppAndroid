package com.cookie.chatapp.data.remote.dto.room

import com.cookie.chatapp.data.remote.dto.user.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateRoomResponse(
    val status: String,
    val data: RoomMiddle
)

@Serializable
data class RoomMiddle(
    val room: GetRoomResponse
)

@Serializable
data class GetRoomResponse(
    @SerialName("_id") val id: String,
    val code: String,
    val description: String,
    val createdAt: String,
    val updatedAt: String,
    val users: List<RoomUser>
)

@Serializable
data class RoomUser(
    val unread: Int,
    @SerialName("_id") val roomUserId: String,
    val user: User
)

//"rooms": [
//    {
//        "_id": "67b75fffc0dbf75803745fa8",
//        "code": "g9vKxS",
//        "description": "New room",
//        "users": [
//        {
//            "unread": 0,
//            "_id": "67b75fffc0dbf75803745fa9",
//            "user": {
//                "_id": "67b5c9249a3dea2ce4ff1380",
//                "username": "hi",
//                "firstName": "hi@gmail.com",
//                "lastName": "1234",
//                "email": "hello"
//              }
//        }
//        ],
//        "createdAt": "2025-02-20T17:01:51.924Z",
//        "updatedAt": "2025-02-20T17:01:51.924Z",
//        "__v": 0
//    }
