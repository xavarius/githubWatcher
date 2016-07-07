package com.maciejmalak.githubstatistics.activities

interface IAddUser {
    fun closeActivity(username : String)
    fun showUsernameValidationError()
    fun getUserNameFieldText() : String?
}