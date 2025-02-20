package com.cookie.chatapp.data.remote.dto.room

data class JoinRoomResponse(
    val status: String,
    val data: RoomResponse
)

//{
//    "status": "success",
//    "data": {
//    "room": {
//        "_id": "67b75fffc0dbf75803745fa8",
//        "code": "g9vKxS",
//        "description": "New room",
//        "users": [
//        {
//            "unread": 0,
//            "_id": "67b75fffc0dbf75803745fa9",
//            "user": {
//            "_id": "67b5c9249a3dea2ce4ff1380",
//            "username": "hi",
//            "firstName": "hi@gmail.com",
//            "lastName": "1234",
//            "email": "hello"
//        }
//        },
//        {
//            "unread": 0,
//            "_id": "67b76bb70dd5a35be78a718a",
//            "user": {
//            "_id": "67b76b1d0dd5a35be78a7189",
//            "username": "hiii",
//            "firstName": "hiii",
//            "lastName": "hiii",
//            "email": "hiii@gmail.com"
//        }
//        }
//        ],
//        "createdAt": "2025-02-20T17:01:51.924Z",
//        "updatedAt": "2025-02-20T17:51:51.587Z",
//        "__v": 1
//    }
//}
//}
