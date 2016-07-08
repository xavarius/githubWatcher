package com.maciejmalak.githubstatistics.activities

import com.maciejmalak.githubstatistics.model.Account

interface Home {
    fun launchAddUserActivity(requestCode: Int)

    fun launchUserDetailsActivity(name: String)

    fun showNewListEntry(user: Account)

    fun showAddUserFailedMessage()

    fun showDownloadAccountFailedMessage()
}