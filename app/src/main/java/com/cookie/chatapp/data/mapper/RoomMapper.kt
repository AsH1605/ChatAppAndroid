package com.cookie.chatapp.data.mapper

import com.cookie.chatapp.data.remote.dto.room.GetAllRoomResponse
import com.cookie.chatapp.data.remote.dto.room.GetRoomResponse
import com.cookie.chatapp.domain.models.RoomModel

fun GetAllRoomResponse.toRoom(userId: String) : List<RoomModel>{
//    val rooms = data.rooms
//    var allRooms : MutableList<RoomModel> = mutableListOf()
//    for (i in rooms.indices){
//        allRooms.add(i, rooms[i].toRoomModel(userId))
//    }
//    return allRooms.toList()

    return data.rooms.map { room->
        room.toRoomModel(userId)
    }
}

fun GetRoomResponse.toRoomModel(userId: String): RoomModel{
    var unread = 0
    for (i in users.indices){
        if (users[i].roomUserId == userId){
            unread = users[i].unread
        }
    }
    return RoomModel(
        code = code,
        totalMessages = unread
    )
}