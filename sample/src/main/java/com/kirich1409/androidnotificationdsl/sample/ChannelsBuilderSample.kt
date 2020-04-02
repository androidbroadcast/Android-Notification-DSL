package com.kirich1409.androidnotificationdsl.sample

import android.content.Context
import com.kirich1409.androidnotificationdsl.channels.createChannelsAndGroups

fun buildChannels(context: Context) = createChannelsAndGroups(context) {
    channel(CHANNEL_1_ID, CHANNEL_1_NAME)

    group(CHANNEL_GROUP_1_ID, CHANNEL_GROUP_1_NAME) {
        // Empty group
    }

    group(CHANNEL_GROUP_2_ID, CHANNEL_GROUP_2_NAME) {
        channel(CHANNEL_2_ID, CHANNEL_2_NAME)
    }
}

private const val CHANNEL_GROUP_1_ID = "01_group1"
private const val CHANNEL_GROUP_1_NAME = "Group 1"

private const val CHANNEL_GROUP_2_ID = "02_group1"
private const val CHANNEL_GROUP_2_NAME = "Group 2"

private const val CHANNEL_1_ID = "00_01_channel1"
private const val CHANNEL_1_NAME = "Channel 1"

private const val CHANNEL_2_ID = "01_02_channel1"
private const val CHANNEL_2_NAME = "Channel 2"