package com.maciejmalak.githubstatistics.activities

import org.jetbrains.annotations.NotNull

interface AddUserScreen {
    fun closeActivity(@NotNull username : String)
    fun showUsernameValidationError()
    fun getUserNameFieldText() : String?
}