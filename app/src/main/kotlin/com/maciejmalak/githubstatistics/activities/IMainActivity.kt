package com.maciejmalak.githubstatistics.activities

interface IMainActivity {
    fun launchAddUserActivity(requestCode : Int)
    fun launchUserDetailsActivity()
    fun showAddUserFailedMessage()
}