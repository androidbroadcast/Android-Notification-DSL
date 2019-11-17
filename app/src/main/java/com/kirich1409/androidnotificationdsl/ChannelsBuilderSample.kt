package com.kirich1409.androidnotificationdsl

import android.content.Context
import com.kirich1409.androidnotificationdsl.channels.createChannelsAndGroups

fun buildChannels(context: Context) = createChannelsAndGroups(context) {
    group("01_group1", "Group 1") {
        // Empty group
    }

    group("02_group2", "Group 2") {
        channel("02_01_channel1", "Channel 1")
    }

    channel("00_01_channel2", "Channel 2")
}