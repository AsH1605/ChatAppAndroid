package com.cookie.chatapp.data.repository

import android.util.Log
import com.cookie.chatapp.data.local.dao.UserDao
import com.cookie.chatapp.data.remote.RoomApi
import com.cookie.chatapp.data.remote.dto.room.ChatMessage
import com.cookie.chatapp.data.remote.dto.room.GetAllMessagesReq
import com.cookie.chatapp.data.remote.dto.room.JoinRoomRequest
import com.cookie.chatapp.data.remote.dto.room.UserRoom
import com.cookie.chatapp.domain.manager.PreferenceManager
import com.cookie.chatapp.domain.models.MessageModel
import com.cookie.chatapp.domain.models.RoomEvent
import com.cookie.chatapp.domain.models.UserModel
import com.cookie.chatapp.domain.repository.RoomRepository
import com.cookie.chatapp.domain.util.json
import io.socket.client.Socket
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonObject
import org.json.JSONObject

class RoomRepositoryImpl(
    private val socket: Socket,
    private val preferenceManager: PreferenceManager,
    private val userDao: UserDao,
    private val roomApi: RoomApi,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): RoomRepository {
    override suspend fun sendMessage(roomCode: String, username: String, message: String): Unit = withContext(ioDispatcher){
        val request = json.encodeToJsonElement(ChatMessage(
            userRoom = UserRoom(
                name = username,
                room = roomCode
            ),
            content = message
        ))
        val req = JSONObject().apply {
            val userRoom = JSONObject().apply {
                put("name", username)
                put("room", roomCode)
            }
            put("userRoom", userRoom)
            put("content", message)
        }
        socket.emit(ChatEvent.MESSAGE.event, req)
    }

    override suspend fun joinRoom(roomCode: String, userId: String): Unit= withContext(ioDispatcher) {
        socket.emit(ChatEvent.JOIN.event, JoinRoomRequest(
            userRoom = UserRoom(
                name = userId,
                room = roomCode
            ),
            isFirst = false
        ))
    }

    override suspend fun connect() {
        socket.connect()
    }

    override fun getRoomEvents(): Flow<RoomEvent> = callbackFlow {
        socket.on(ChatEvent.CONNECT.event, {
            trySend(RoomEvent.Connected)
        })
        socket.on(ChatEvent.MESSAGE.event){args->
            Log.d("Impl",args[0].toString())
            if (args.isNotEmpty()){
                val response = args[0] as JsonObject
                val newMsg = response["newMsg"]?.jsonObject
                if(newMsg != null){
                    val msg = json.decodeFromJsonElement<MessageModel>(newMsg)
                    trySend(RoomEvent.MessageReceived(msg))
                }
            }
        }
        socket.on(ChatEvent.JOIN.event){args->
            if (args.isNotEmpty()){
                val response = args[0] as JsonObject
                val newUser = response["userDetails"]?.jsonObject
                if (newUser!= null){
                    val user = json.decodeFromJsonElement<UserModel>(newUser)
                    trySend(RoomEvent.UserJoined(user))
                }
            }
        }
        awaitClose {
            socket.off(ChatEvent.JOIN.event)
            socket.off(ChatEvent.MESSAGE.event)
            socket.off(ChatEvent.CONNECT.event)
        }
    }

    override suspend fun disconnect() {
        socket.disconnect()
    }

    override suspend fun getAllMessages(roomCode: String): List<MessageModel> = withContext(ioDispatcher) {
        val usedId = preferenceManager.getLoggedInUserId()!!
        val idToken = userDao.getIdToken(usedId)
        roomApi.getMessages(
            idToken = idToken,
            req = GetAllMessagesReq(
                roomCode = roomCode
            )
        ).data.messages.map {message->
            MessageModel(
                content = message.content,
                status = message.status,
                isSystem = message.isSystem,
                userId = message.user.id,
                roomCode = message.roomCode,
                username = message.user.username
            )
        }.asReversed()
    }

    enum class ChatEvent(val event: String){
        CONNECT ( "connection"),
        DISCONNECT ( "disconnect"),
        JOIN ( "join"),
        MESSAGE ( "message"),
        UNREAD ( "unread"),
        LEAVE ( "leave"),
        ROOM_DELETE ( "room delete")
    }
}