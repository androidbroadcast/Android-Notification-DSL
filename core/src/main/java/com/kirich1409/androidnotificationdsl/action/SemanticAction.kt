package com.kirich1409.androidnotificationdsl.action

import androidx.core.app.NotificationCompat
import java.lang.IllegalArgumentException

enum class SemanticAction(@NotificationCompat.Action.SemanticAction val intValue: Int) {
    ARCHIVE(NotificationCompat.Action.SEMANTIC_ACTION_ARCHIVE),
    CALL(NotificationCompat.Action.SEMANTIC_ACTION_CALL),
    DELETE(NotificationCompat.Action.SEMANTIC_ACTION_DELETE),
    MARK_AS_READ(NotificationCompat.Action.SEMANTIC_ACTION_MARK_AS_READ),
    MARK_AS_UNREAD(NotificationCompat.Action.SEMANTIC_ACTION_MARK_AS_UNREAD),
    MUTE(NotificationCompat.Action.SEMANTIC_ACTION_MUTE),
    NONE(NotificationCompat.Action.SEMANTIC_ACTION_NONE),
    REPLY(NotificationCompat.Action.SEMANTIC_ACTION_REPLY),
    THUMBS_DOWN(NotificationCompat.Action.SEMANTIC_ACTION_THUMBS_DOWN),
    THUMBS_UP(NotificationCompat.Action.SEMANTIC_ACTION_THUMBS_UP),
    UNMUTE(NotificationCompat.Action.SEMANTIC_ACTION_UNMUTE),
    ;

    companion object {

        fun fromInt(@NotificationCompat.Action.SemanticAction intValue: Int): SemanticAction = when (intValue) {
            NotificationCompat.Action.SEMANTIC_ACTION_ARCHIVE -> ARCHIVE
            NotificationCompat.Action.SEMANTIC_ACTION_CALL -> CALL
            NotificationCompat.Action.SEMANTIC_ACTION_DELETE -> DELETE
            NotificationCompat.Action.SEMANTIC_ACTION_MARK_AS_READ -> MARK_AS_READ
            NotificationCompat.Action.SEMANTIC_ACTION_MARK_AS_UNREAD -> MARK_AS_UNREAD
            NotificationCompat.Action.SEMANTIC_ACTION_MUTE -> MUTE
            NotificationCompat.Action.SEMANTIC_ACTION_NONE -> NONE
            NotificationCompat.Action.SEMANTIC_ACTION_REPLY -> REPLY
            NotificationCompat.Action.SEMANTIC_ACTION_THUMBS_DOWN -> THUMBS_DOWN
            NotificationCompat.Action.SEMANTIC_ACTION_THUMBS_UP -> THUMBS_UP
            NotificationCompat.Action.SEMANTIC_ACTION_UNMUTE -> UNMUTE
            else -> throw IllegalArgumentException("Unknown value")
        }
    }
}
